package com.xriamer.store.dao.impl;

import com.xriamer.store.dao.IAdminDAO;
import com.xriamer.store.vo.Admin;
import com.xriamer.utils.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.Date;

public class AdminDAOImpl extends AbstractDAOImpl implements IAdminDAO {
    public AdminDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean findLogin(Admin ad) throws Exception {
        String sql = "SELECT lastdate FROM admin WHERE aid=? AND password=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, ad.getAid());
        super.pstmt.setString(2, ad.getPassword());
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            ad.setLastdate(rs.getTimestamp(1));
            return true;
        }
        return false;
    }

    @Override
    public boolean doUpdateLastdate(String aid) throws Exception {
        String sql = "UPDATE admin SET lastdate=? WHERE aid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
        super.pstmt.setString(2, aid);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doCreate(Admin vo) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdate(Admin vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws Exception {
        return false;
    }

    @Override
    public Admin findById(String id) throws Exception {
        return null;
    }

    @Override
    public List<Admin> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Admin> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
