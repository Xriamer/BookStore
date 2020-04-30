package com.xriamer.store.dao.impl;

import com.xriamer.store.dao.IOrdersDAO;
import com.xriamer.store.vo.Member;
import com.xriamer.store.vo.Orders;
import com.xriamer.utils.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "INSERT INTO orders(mid,name,phone,address,credate,pay) VALUES (?,?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, orders.getMember().getMid());
        super.pstmt.setString(2, orders.getName());
        super.pstmt.setString(3, orders.getPhone());
        super.pstmt.setString(4, orders.getAddress());
        super.pstmt.setTimestamp(5, new java.sql.Timestamp(orders.getCredate().getTime()));
        super.pstmt.setDouble(6, orders.getPay());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public List<Orders> findAllByMember(String mid, Integer currentPage, Integer lineSize) throws Exception {
        List<Orders> all = new ArrayList<Orders>();
        String sql = "SELECT oid,mid,name,phone,address,credate,pay FROM orders WHERE mid=? LIMIT ?,?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        super.pstmt.setInt(2, (currentPage - 1) * lineSize);
        super.pstmt.setInt(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Orders orders = new Orders();
            orders.setOid(rs.getInt(1));
            Member member = new Member();
            member.setMid(rs.getString(2));
            orders.setMember(member);
            orders.setName(rs.getString(3));
            orders.setPhone(rs.getString(4));
            orders.setAddress(rs.getString(5));
            orders.setCredate(rs.getTimestamp(6));
            orders.setPay(rs.getDouble(7));
            all.add(orders);
        }
        return all;
    }

    @Override
    public Integer getAllCountByMember(String mid) throws Exception {
        String sql = "SELECT COUNT(*) FROM orders WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public Orders findByIdAndMember(String mid, Integer oid) throws Exception {
        Orders orders = null;
        String sql = "SELECT oid,mid,name,phone,address,credate,pay FROM orders WHERE mid=? AND oid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        super.pstmt.setInt(2, oid);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            orders = new Orders();
            orders.setOid(rs.getInt(1));
            Member member = new Member();
            member.setMid(rs.getString(2));
            orders.setMember(member);
            orders.setName(rs.getString(3));
            orders.setPhone(rs.getString(4));
            orders.setAddress(rs.getString(5));
            orders.setCredate(rs.getTimestamp(6));
            orders.setPay(rs.getDouble(7));
        }
        return orders;
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
        Orders orders = null;
        String sql = "SELECT oid,mid,name,phone,address,credate,pay FROM orders WHERE oid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, id);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            orders = new Orders();
            orders.setOid(rs.getInt(1));
            Member member = new Member();
            member.setMid(rs.getString(2));
            orders.setMember(member);
            orders.setName(rs.getString(3));
            orders.setPhone(rs.getString(4));
            orders.setAddress(rs.getString(5));
            orders.setCredate(rs.getTimestamp(6));
            orders.setPay(rs.getDouble(7));
        }
        return orders;
    }

    @Override
    public List<Orders> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Orders> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Orders> all = new ArrayList<Orders>();
        String sql = "SELECT oid,mid,name,phone,address,credate,pay FROM orders WHERE " + column + " LIKE ? LIMIT ?,?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, (currentPage - 1) * lineSize);
        super.pstmt.setInt(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Orders orders = new Orders();
            orders.setOid(rs.getInt(1));
            Member member = new Member();
            member.setMid(rs.getString(2));
            orders.setMember(member);
            orders.setName(rs.getString(3));
            orders.setPhone(rs.getString(4));
            orders.setAddress(rs.getString(5));
            orders.setCredate(rs.getTimestamp(6));
            orders.setPay(rs.getDouble(7));
            all.add(orders);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM orders WHERE " + column + " LIKE ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%"+keyWord+"%");
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
}
