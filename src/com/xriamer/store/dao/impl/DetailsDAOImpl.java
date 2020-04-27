package com.xriamer.store.dao.impl;

import com.xriamer.store.dao.IDetailsDAO;
import com.xriamer.store.vo.Details;
import com.xriamer.utils.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DetailsDAOImpl extends AbstractDAOImpl implements IDetailsDAO {
    public DetailsDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreateBatch(List<Details> details) throws SQLException {
        boolean flag = true;
        String sql = "INSERT INTO details(oid,bid,title,price,amount) VALUES (?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        Iterator<Details> iter = details.iterator();
        while (iter.hasNext()) {
            Details vo = iter.next();
            super.pstmt.setInt(1, vo.getOrders().getOid());
            super.pstmt.setInt(2, vo.getBooks().getBid());
            super.pstmt.setString(3, vo.getTitle());
            super.pstmt.setDouble(4, vo.getPrice());
            super.pstmt.setInt(5, vo.getAmount());
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
    public boolean doCreate(Details vo) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdate(Details vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public Details findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Details> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Details> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
