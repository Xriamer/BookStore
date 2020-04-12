package com.xriamer.store.dao.impl;

import com.xriamer.store.dao.IBooksDAO;
import com.xriamer.store.vo.Admin;
import com.xriamer.store.vo.Books;
import com.xriamer.store.vo.Item;
import com.xriamer.utils.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BooksDAOImpl extends AbstractDAOImpl implements IBooksDAO {
    public BooksDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Books book) throws Exception {
        // iid是下拉菜单选项
        String sql = "INSERT INTO books(iid,aid,title,writer,publisher,isbn,pubdate,price,amount,bow,note,photo,status)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, book.getItem().getIid());
        super.pstmt.setString(2, book.getAdmin().getAid());
        super.pstmt.setString(3, book.getTitle());
        super.pstmt.setString(4, book.getWriter());
        super.pstmt.setString(5, book.getPublisher());
        super.pstmt.setString(6, book.getIsbn());
        super.pstmt.setTimestamp(7, new Timestamp(book.getPubdate().getTime()));
        super.pstmt.setDouble(8, book.getPrice());
        super.pstmt.setInt(9, book.getAmount());
        super.pstmt.setInt(10, book.getBow());
        super.pstmt.setString(11, book.getNote());
        super.pstmt.setString(12, book.getPhoto());
        super.pstmt.setInt(13, book.getStatus());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Books books) throws Exception {
        String sql = "UPDATE books SET iid=?,title=?,writer=?,publisher=?,isbn=?,price=?,amount=?,note=?,photo=?,status=? WHERE bid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, books.getItem().getIid());
        super.pstmt.setString(2, books.getTitle());
        super.pstmt.setString(3, books.getWriter());
        super.pstmt.setString(4, books.getPublisher());
        super.pstmt.setString(5, books.getIsbn());
        super.pstmt.setDouble(6, books.getPrice());
        super.pstmt.setInt(7, books.getAmount());
        super.pstmt.setString(8, books.getNote());
        super.pstmt.setString(9, books.getPhoto());
        super.pstmt.setInt(10, books.getStatus());
        super.pstmt.setInt(11, books.getBid());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return super.removeHandle("books", "bid", ids);
    }

    @Override
    public Books findById(Integer id) throws Exception {
        Books books = null;
        String sql = "SELECT bid,iid,aid,title,writer,publisher,isbn,pubdate,price,amount,bow,note,photo,status From books WHERE bid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, id);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            books = new Books();
            books.setBid(rs.getInt(1));
            Item item = new Item();
            item.setIid(rs.getInt(2));
            books.setItem(item);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            books.setAdmin(admin);
            books.setTitle(rs.getString(4));
            books.setWriter(rs.getString(5));
            books.setPublisher(rs.getString(6));
            books.setIsbn(rs.getString(7));
            books.setPubdate(rs.getTimestamp(8));
            books.setPrice(rs.getDouble(9));
            books.setAmount(rs.getInt(10));
            books.setBow(rs.getInt(11));
            books.setNote(rs.getString(12));
            books.setPhoto(rs.getString(13));
            books.setStatus(rs.getInt(14));
        }
        return books;
    }

    @Override
    public List<Books> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Books> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Books> all = new ArrayList<Books>();
        String sql = "SELECT bid,iid,aid,title,writer,publisher,isbn,pubdate,price,amount,bow,note,photo,status From books WHERE " + column + " LIKE ? AND status<>2 LIMIT ? , ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, (currentPage - 1) * lineSize);
        super.pstmt.setInt(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Books books = new Books();
            books.setBid(rs.getInt(1));
            Item item = new Item();
            item.setIid(rs.getInt(2));
            books.setItem(item);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            books.setAdmin(admin);
            books.setTitle(rs.getString(4));
            books.setWriter(rs.getString(5));
            books.setPublisher(rs.getString(6));
            books.setIsbn(rs.getString(7));
            books.setPubdate(rs.getTimestamp(8));
            books.setPrice(rs.getDouble(9));
            books.setAmount(rs.getInt(10));
            books.setBow(rs.getInt(11));
            books.setNote(rs.getString(12));
            books.setPhoto(rs.getString(13));
            books.setStatus(rs.getInt(14));
            all.add(books);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM books WHERE " + column + " LIKE ? AND status<>2";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public List<Books> findAllByStatus(Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Books> all = new ArrayList<Books>();
        String sql = "SELECT bid,iid,aid,title,writer,publisher,isbn,pubdate,price,amount,bow,note,photo,status From books WHERE " + column + " LIKE ? AND status=? LIMIT ? , ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, status);
        super.pstmt.setInt(3, (currentPage - 1) * lineSize);
        super.pstmt.setInt(4, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Books books = new Books();
            books.setBid(rs.getInt(1));
            Item item = new Item();
            item.setIid(rs.getInt(2));
            books.setItem(item);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            books.setAdmin(admin);
            books.setTitle(rs.getString(4));
            books.setWriter(rs.getString(5));
            books.setPublisher(rs.getString(6));
            books.setIsbn(rs.getString(7));
            books.setPubdate(rs.getTimestamp(8));
            books.setPrice(rs.getDouble(9));
            books.setAmount(rs.getInt(10));
            books.setBow(rs.getInt(11));
            books.setNote(rs.getString(12));
            books.setPhoto(rs.getString(13));
            books.setStatus(rs.getInt(14));
            all.add(books);
        }
        return all;
    }

    @Override
    public Integer getAllCountByStatus(Integer status, String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM books WHERE " + column + " LIKE ? AND status=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, status);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean doUpdateStatus(Set<Integer> id, Integer status) throws Exception {
        String sql = "UPDATE books SET status=? WHERE bid=?";
        Iterator<Integer> iter = id.iterator();
        super.pstmt = super.conn.prepareStatement(sql);
        while (iter.hasNext()) {
            super.pstmt.setInt(1, status);
            super.pstmt.setInt(2, iter.next());
            super.pstmt.addBatch();
        }
        boolean flag = true;
        int result[] = super.pstmt.executeBatch();
        for (int x = 0; x < result.length; x++) {
            if (result[x] == 0) {
                flag = false;
            }
        }
        return true;
    }

    @Override
    public Set<String> findAllByPhoto(Set<Integer> id) throws Exception {
        if (id.size() > 0) {
            return super.photoHandle("books", "photo", "bid", id);
        }
        return null;
    }

    @Override
    public List<Books> findAllByItem(Integer iid, Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Books> all = new ArrayList<Books>();
        String sql = "SELECT bid,iid,aid,title,writer,publisher,isbn,pubdate,price,amount,bow,note,photo,status From books WHERE " + column + " LIKE ? AND status=? AND iid=? LIMIT ? , ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, status);
        super.pstmt.setInt(3, iid);
        super.pstmt.setInt(4, (currentPage - 1) * lineSize);
        super.pstmt.setInt(5, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
                Books books = new Books();
                books.setBid(rs.getInt(1));
            Item item = new Item();
            item.setIid(rs.getInt(2));
            books.setItem(item);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            books.setAdmin(admin);
            books.setTitle(rs.getString(4));
            books.setWriter(rs.getString(5));
            books.setPublisher(rs.getString(6));
            books.setIsbn(rs.getString(7));
            books.setPubdate(rs.getTimestamp(8));
            books.setPrice(rs.getDouble(9));
            books.setAmount(rs.getInt(10));
            books.setBow(rs.getInt(11));
            books.setNote(rs.getString(12));
            books.setPhoto(rs.getString(13));
            books.setStatus(rs.getInt(14));
            all.add(books);
        }
        return all;
    }

    @Override
    public Integer getAllCountByItem(Integer iid, Integer status, String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM books WHERE " + column + " LIKE ? AND status=? AND iid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, status);
        super.pstmt.setInt(3, iid);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
}
