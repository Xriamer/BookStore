package com.xriamer.store.dao.impl;

import com.xriamer.store.dao.IMemberDAO;
import com.xriamer.store.vo.Member;
import com.xriamer.utils.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MemberDAOImpl extends AbstractDAOImpl implements IMemberDAO {
    public MemberDAOImpl(Connection conn) {
        super(conn);
    }

//    @Override
//    public Member findByMid(String mid) throws Exception {
//        return null;
//    }

    @Override
    public boolean doCreate(Member mb) throws Exception {
        String sql = "INSERT INTO member(mid,password,code,regdate,status,photo) VALUES (?,?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mb.getMid());
        super.pstmt.setString(2, mb.getPassword());
        super.pstmt.setString(3, mb.getCode());
        super.pstmt.setTimestamp(4, new Timestamp(mb.getRegdate().getTime()));
        super.pstmt.setInt(5, mb.getStatus());
        super.pstmt.setString(6, mb.getPhoto());
        return super.pstmt.executeUpdate() > 0;
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
        Member mb = null;
        String sql = "SELECT mid,password,name,phone,address,code,status,regdate,photo FROM member WHERE mid =?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, id);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            mb = new Member();
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
        List<Member> all=new ArrayList<Member>();
        String sql="SELECT mid,password,name,phone,address,code,regdate,photo,status FROM member WHERE "+ column +" LIKE ? LIMIT ? , ? ";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%" + keyWord + "%");
        super.pstmt.setInt(2,(currentPage-1)*lineSize);
        super.pstmt.setInt(3,lineSize);
        ResultSet rs=super.pstmt.executeQuery();
        while(rs.next()){
            Member mb=new Member();
            mb.setMid(rs.getString(1));
            mb.setPassword(rs.getString(2));
            mb.setName(rs.getString(3));
            mb.setPhone(rs.getString(4));
            mb.setAddress(rs.getString(5));
            mb.setCode(rs.getString(6));
            mb.setRegdate(rs.getTimestamp(7));
            mb.setPhoto(rs.getString(8));
            mb.setStatus(rs.getInt(9));
            all.add(mb);
        }
        return all;
    }

    @Override
    /**
     * 数据个数统计
     */
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.countHandle("member",column,keyWord);
    }

    @Override
    public boolean findByCode(String mid, String code) throws Exception {
        String sql = "SELECT COUNT(*) FROM member WHERE mid=? AND code=? AND status=2";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        super.pstmt.setString(2, code);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            if (rs.getInt(1) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean doUpdateStatus(String mid, Integer status) throws Exception {
        String sql = "UPDATE member SET status=? WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, status);
        super.pstmt.setString(2, mid);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean findLogin(Member mb) throws Exception {
        boolean flag = false;
        //必须保证用户的登录状态是1(正常状态)
        String sql = "SELECT photo FROM member WHERE mid=? AND password=? AND status=1";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mb.getMid());
        super.pstmt.setString(2, mb.getPassword());
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            flag = true;
            mb.setPhoto(rs.getString(1));
        }
        return flag;
    }

    @Override
    public List<Member> findAllByStatus(Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Member> all=new ArrayList<Member>();
        String sql="SELECT mid,password,name,phone,address,code,regdate,photo,status FROM member WHERE "+ column +" LIKE ? AND status=? LIMIT ? , ? ";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%" + keyWord + "%");
        super.pstmt.setInt(2,status);
        super.pstmt.setInt(3,(currentPage-1)*lineSize);
        super.pstmt.setInt(4,lineSize);
        ResultSet rs=super.pstmt.executeQuery();
        while(rs.next()){
            Member mb=new Member();
            mb.setMid(rs.getString(1));
            mb.setPassword(rs.getString(2));
            mb.setName(rs.getString(3));
            mb.setPhone(rs.getString(4));
            mb.setAddress(rs.getString(5));
            mb.setCode(rs.getString(6));
            mb.setRegdate(rs.getTimestamp(7));
            mb.setPhoto(rs.getString(8));
            mb.setStatus(rs.getInt(9));
            all.add(mb);
        }
        return all;
    }

    @Override
    public Integer getAllCountByStatus(Integer status, String column, String keyWord) throws Exception {
        String sql="SELECT COUNT(*) FROM member WHERE "+column+" LIKE ? AND status=?";
        super.pstmt=super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        super.pstmt.setInt(2,status);
        ResultSet rs=super.pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean doUpdateStatus(Set<String> ids, Integer status) throws Exception {
        if(ids.size()==0){
            return false;
        }
        boolean flag=true;
        String sql="UPDATE member SET status=? WHERE mid=?";
        super.pstmt=super.conn.prepareStatement(sql);
        Iterator<String> iter=ids.iterator();
        while(iter.hasNext()){
            super.pstmt.setInt(1,status);
            super.pstmt.setString(2,iter.next());
            super.pstmt.addBatch();//增加到批处理操作
        }
        int result []=super.pstmt.executeBatch();//执行批处理
        for(int x=0;x<result.length;x++){
            if(result[x]==0){
                flag=false;
            }
        }
        return true;
    }
}
