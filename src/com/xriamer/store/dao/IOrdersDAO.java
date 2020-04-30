package com.xriamer.store.dao;

import com.xriamer.store.vo.Orders;

import java.sql.SQLException;
import java.util.List;

public interface IOrdersDAO extends IDAO<Integer, Orders> {
    /**
     * 调用last_insert_id()函数 取得当前增长后的订单编号，为订单详情做准备
     *
     * @return 返回最后的
     * @throws Exception
     */
    public Integer findLastInsertId() throws SQLException;

    public boolean doCreateOrders(Orders orders) throws SQLException;

    /**
     * 根据用户编号，列出所有的订单信息
     *
     * @param mid
     * @param currentPage
     * @param lineSize
     * @return
     * @throws Exception
     */
    public List<Orders> findAllByMember(String mid, Integer currentPage, Integer lineSize) throws Exception;

    public Integer getAllCountByMember(String mid) throws Exception;

    /**
     * 查询一个用户的一个订单信息
     * @param mid
     * @param oid
     * @return
     * @throws Exception
     */
    public Orders findByIdAndMember(String mid, Integer oid) throws Exception;
}
