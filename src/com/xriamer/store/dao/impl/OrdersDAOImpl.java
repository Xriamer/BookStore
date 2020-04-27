package com.xriamer.store.dao.impl;

import com.xriamer.store.dao.IOrdersDAO;
import com.xriamer.store.vo.Orders;
import com.xriamer.utils.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class OrdersDAOImpl extends AbstractDAOImpl implements IOrdersDAO {
    public OrdersDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public Integer findLastInsertId() throws SQLException {
        String sql = "SELECT LAST_INSERT_ID()";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean doCreateOrders(Orders orders) throws SQLException {
        String sql="INSERT INTO orders(mid,name,phone,address,credate,pay) VALUES (?,?,?,?,?,?)";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,orders.getMember().getMid());
        super.pstmt.setString(2,orders.getName());
        super.pstmt.setString(3,orders.getPhone());
        super.pstmt.setString(4,orders.getAddress());
        super.pstmt.setTimestamp(5,new java.sql.Timestamp(orders.getCredate().getTime()));
        super.pstmt.setDouble(6,orders.getPay());
        return super.pstmt.executeUpdate()>0;
    }

    @Override
    public boolean doCreate(Orders vo) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdate(Orders vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Orders findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Orders> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Orders> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
