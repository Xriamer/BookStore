package com.xriamer.store.dao.impl;

import com.xriamer.store.dao.IShopcarDAO;
import com.xriamer.store.vo.Shopcar;
import com.xriamer.utils.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

public class ShopcarDAOImpl extends AbstractDAOImpl implements IShopcarDAO {
    public ShopcarDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doUpdateAmount(String mid, Integer bid) throws Exception {
        String sql = "UPDATE shopcar SET amount=amount+1 WHERE mid=? AND bid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        super.pstmt.setInt(2, bid);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public Shopcar findByMemberAndBooks(String mid, Integer bid) throws Exception {
        Shopcar vo = null;
        String sql = "SELECT bid,mid,amount FROM shopcar WHERE mid=? AND bid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        super.pstmt.setInt(2, bid);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            vo = new Shopcar();
            vo.setAmount(rs.getInt(1));
        }
        return vo;
    }

    @Override
    public boolean doRemoveByMember(String mid) throws Exception {
        String sql = "DELETE FROM shopcar WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doCreateBatch(Set<Shopcar> vos) throws Exception {
        boolean flag = true;
        String sql = "INSERT INTO shopcar(bid,mid,amount) VALUES (?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        Iterator<Shopcar> iter = vos.iterator();
        while (iter.hasNext()) {
            Shopcar vo = iter.next();
            super.pstmt.setInt(1, vo.getBooks().getBid());
            super.pstmt.setString(2, vo.getMember().getMid());
            super.pstmt.setInt(3, vo.getAmount());
            super.pstmt.addBatch();
        }
        int result[] = super.pstmt.executeBatch();
        for (int x = 0; x < result.length; x++) {
            if (result[x] == 0) {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean doRemoveByMemberAndGoods(String mid, Integer bid) throws Exception {
        String sql = "DELETE FROM shopcar WHERE mid=? AND bid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        super.pstmt.setInt(2, bid);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public Map<Integer, Integer> findAllByMember(String mid) throws Exception {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        String sql = "SELECT bid,amout FROM shopcar WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            map.put(rs.getInt(1), rs.getInt(2));
        }
        return map;
    }

    @Override
    public boolean doCreate(Shopcar vo) throws Exception {
        String sql = "INSERT INTO shopcar(bid,mid,amount) VALUES (?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, vo.getBooks().getBid());
        super.pstmt.setString(2, vo.getMember().getMid());
        super.pstmt.setInt(3, vo.getAmount());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Shopcar vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Shopcar findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Shopcar> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Shopcar> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
