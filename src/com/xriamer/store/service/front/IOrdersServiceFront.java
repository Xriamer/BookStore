package com.xriamer.store.service.front;

import com.xriamer.store.exception.EmptyShopcarException;
import com.xriamer.store.exception.UnEnoughAmountException;
import com.xriamer.store.exception.UncompleteMemberInformationException;
import com.xriamer.store.vo.Orders;

import java.sql.SQLException;
import java.util.Map;

public interface IOrdersServiceFront {
    /**
     * 创建订单操作
     *
     * @param mid 要创建订单的用户ID
     * @return 如果订单创建成功则返回true，否则返回false
     * @throws UncompleteMemberInformationException 个人信息不完整时抛出异常
     * @throws UnEnoughAmountException              没有足够的库存量时抛出异常
     * @throws EmptyShopcarException                购物车没有添加任何商品时抛出异常
     * @throws SQLException                         JDBC错误所造成的异常
     */
    public boolean insert(String mid) throws UncompleteMemberInformationException, UnEnoughAmountException, EmptyShopcarException, SQLException;

    /**
     * 查看一个用户所有订单信息
     * @param mid
     * @return
     * @throws Exception
     */
    public Map<String,Object> listByMember(String mid, int currentPage, int lineSize) throws Exception;

    /**
     * 查看一个订单信息以及订单对应的所有详情信息
     * @param mid
     * @param oid
     * @return
     * @throws Exception
     */
    public Orders show(String mid,int oid) throws Exception;
}
