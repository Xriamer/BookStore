package com.xriamer.store.service.front.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.front.IShopcarServiceFront;
import com.xriamer.store.vo.Shopcar;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShopcarServiceFrontImpl implements IShopcarServiceFront {

    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public Map<String, Object> listCar(String mid) throws Exception {
        try {
            Map<String, Object> map = new HashMap<>();
            //查询出一个用户的所有购物车中的信息
            Map<Integer, Integer> cars = DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).findAllByMember(mid);
            map.put("allShopcars", cars);
            map.put("allBooks", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).findAllByBid(cars.keySet()));
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean addCar(Shopcar vo) throws Exception {
        try {
            //该项购买记录已经存在
            if (DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).findByMemberAndBooks(vo.getMember().getMid(), vo.getBooks().getBid()) != null) {
                return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doUpdateAmount(vo.getMember().getMid(), vo.getBooks().getBid());
            } else {
                vo.setAmount(1); //默认值为1
                return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doCreate(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.dbc.close();
        }
        return false;
    }


    @Override
    public boolean deleteCar(String mid, Set<Integer> bid) throws Exception {
        try {
            return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMemberAndGoods(mid, bid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.dbc.close();
        }
        return false;
    }

    @Override
    public boolean update(String mid, Set<Shopcar> vos) throws Exception {
        try {
            if (vos.size() == 0) {
                return false;
            }
            //清空已有的购物车中的记录
            if (DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMember(mid)) {
                return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doCreateBatch(vos);
            }
            return false;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
