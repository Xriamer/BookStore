package com.xriamer.store.dao.impl;

import com.xriamer.store.dao.IMemberDAO;
import com.xriamer.store.vo.Member;
import com.xriamer.utils.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class MemberDAOImpl extends AbstractDAOImpl implements IMemberDAO {
    public MemberDAOImpl(Connection conn){
        super(conn);
    }

//    @Override
//    public Member findByMid(String mid) throws Exception {
//        return null;
//    }

    @Override
    public boolean doCreate(Member mb) throws Exception {
        String sql="INSERT INTO member(mid,password,code,regdate,status,photo) VALUES (?,?,?,?,?,?)";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mb.getMid());
        super.pstmt.setString(2,mb.getPassword());
        super.pstmt.setString(3,mb.getCode());
        super.pstmt.setTimestamp(4,new Timestamp(mb.getRegdate().getTime()));
        super.pstmt.setInt(5,mb.getStatus());
        super.pstmt.setString(6,mb.getPhoto());
        return super.pstmt.executeUpdate()>0;
    }

    @Override
    public boolean doUpdate(Member vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws Exception {
        return false;
    }

    @Override
    public Member findById(String id) throws Exception {
        Member mb=null;
        String sql="SELECT mid,password,name,phone,address,code,status,regdate,photo FROM member WHERE mid =?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,id);
        ResultSet rs=super.pstmt.executeQuery();
        if(rs.next()){
            mb=new Member();
            mb.setMid(rs.getString(1));
            mb.setPassword(rs.getString(2));
            mb.setName(rs.getString(3));
            mb.setPhone(rs.getString(4));
            mb.setAddress(rs.getString(5));
            mb.setCode(rs.getString(6));
            mb.setRegdate(rs.getTimestamp(7));
            mb.setPhoto(rs.getString(8));
        }
        return mb;
    }

    @Override
    public List<Member> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Member> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public boolean findByCode(String mid, String code) throws Exception {
        String sql="SELECT COUNT(*) FROM member WHERE mid=? AND code=? AND status=2";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mid);
        super.pstmt.setString(2,code);
        ResultSet rs=super.pstmt.executeQuery();
        if(rs.next()){
            if(rs.getInt(1)>0)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean doUpdateStatus(String mid, Integer status) throws Exception {
        String sql="UPDATE member SET status=? WHERE mid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,status);
        super.pstmt.setString(2,mid);
        return super.pstmt.executeUpdate()>0;
    }
}
