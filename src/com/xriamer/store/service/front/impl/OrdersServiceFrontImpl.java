package com.xriamer.store.service.front.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.exception.EmptyShopcarException;
import com.xriamer.store.exception.UnEnoughAmountException;
import com.xriamer.store.exception.UncompleteMemberInformationException;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.front.IOrdersServiceFront;
import com.xriamer.store.vo.Books;
import com.xriamer.store.vo.Details;
import com.xriamer.store.vo.Member;
import com.xriamer.store.vo.Orders;

import java.sql.SQLException;
import java.util.*;

public class OrdersServiceFrontImpl implements IOrdersServiceFront {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean insert(String mid) throws UncompleteMemberInformationException, UnEnoughAmountException, EmptyShopcarException, SQLException {
        boolean flag = false;
        dbc.getConnection().setAutoCommit(false);
        try {
            //首先应该判断出当前的用户信息是否完整，要根据mid查询一个用户的完整信息
            Member member = DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById2(mid);
            //如果现在没有姓名、电话、地址，那么根本就不可能创建订单
            if (member.getName() == null || member.getPhoto() == null || member.getAddress() == null) {
                throw new UncompleteMemberInformationException("用户信息不完整，无法进行订单创建！");
            }
            //如果现在信息完整，那么判断数据库中的数据是否包含有购物车信息
            Map<Integer, Integer> cars = DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).findAllByMember(mid);
            if (cars.size() == 0) {
                throw new EmptyShopcarException("购物车里暂无购买商品，无法创建订单！");
            }
            //如果现在要是有购物信息，则需要查询出所有的图书信息，目的是为了取得图书的名称，价格，库存量等信息。
            List<Books> allBooks = DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).findAllByBid(cars.keySet());
            //判断要购买的图书的库存量是否存在
            Iterator<Books> iterBooks = allBooks.iterator();
            //在判断库存量操作的同时，还需要计算出总的花费金额
            double pay = 0.0;
            while (iterBooks.hasNext()) {
                Books books = iterBooks.next();
                // 库存量-准备要购买的图书数量
                if (books.getAmount() - cars.get(books.getBid()) < 0) {
                    throw new UnEnoughAmountException("商品没有足够的出售数量，无法创建订单！");
                }
                pay += books.getPrice() * cars.get(books.getBid());
            }
            //创建订单对象
            Orders orders = new Orders();
            orders.setMember(member);
            orders.setName(member.getName());
            orders.setPhone(member.getPhone());
            orders.setAddress(member.getAddress());
            orders.setCredate(new Date());
            orders.setPay(pay);
            flag = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).doCreateOrders(orders);
            //订单创建完成之后一定要取得当前增长的订单编号，因为订单详情需要这个编号
            orders.setOid(DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findLastInsertId());
            //创建订单详情信息
            iterBooks = null;
            iterBooks = allBooks.iterator();
            List<Details> all = new ArrayList<Details>();
            //修改所有图书的数量以及创建所有的详情对象
            while (iterBooks.hasNext()) {
                Details details = new Details();
                Books books = iterBooks.next();
                details.setBooks(books);  //订单详情与图书的关系
                details.setOrders(orders); //订单详情与订单的关系
                int amount = cars.get(books.getBid()); //购买数量
                details.setAmount(amount);
                details.setTitle(books.getTitle());
                details.setPrice(books.getPrice());
                all.add(details);
                flag = DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).doUpdateByAmount(books.getBid(), 0 - amount);
            }
            flag = DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).doCreateBatch(all);
            //清空购物车信息
            flag = DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMember(mid);
            dbc.getConnection().commit();
        } catch (SQLException e) {
            dbc.getConnection().rollback();
            e.printStackTrace();
        } finally {
            this.dbc.close();
        }
        return flag;
    }
}
