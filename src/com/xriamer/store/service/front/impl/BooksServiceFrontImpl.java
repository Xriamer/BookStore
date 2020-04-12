package com.xriamer.store.service.front.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.front.IBooksServiceFront;
import com.xriamer.store.vo.Books;

import java.util.HashMap;
import java.util.Map;

public class BooksServiceFrontImpl implements IBooksServiceFront {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allItems", DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
            map.put("allBooks", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).findAllByStatus(1, currentPage, lineSize, column, keyWord));
            map.put("booksCount", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).getAllCountByStatus(1, column, keyWord));
            return map;

        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> listByItem(int iid, int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allItems", DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
            map.put("allBooks", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).findAllByItem(iid, 1, currentPage, lineSize, column, keyWord));
            map.put("booksCount", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).getAllCountByItem(iid, 1, column, keyWord));
            return map;

        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Books show(int bid) throws Exception {
        try {
            //首先要查询出图书信息，如有编号内容，可以查询类别信息。
            Books books = DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).findById(bid);
            if (books != null) {//有图书数据
                books.setItem(DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findById(books.getItem().getIid()));
                DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).doUpdateBow(bid);
            }
            return books;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
