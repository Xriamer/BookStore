package com.xriamer.store.dao;

import com.xriamer.store.vo.Orders;

import java.sql.SQLException;

public interface IOrdersDAO extends IDAO<Integer, Orders>{
    /**
     * 调用last_insert_id()函数 取得当前增长后的订单编号，为订单详情做准备
     * @return  返回最后的
     * @throws Exception
     */
    public Integer findLastInsertId() throws SQLException;
    public boolean doCreateOrders(Orders orders) throws SQLException;
}
