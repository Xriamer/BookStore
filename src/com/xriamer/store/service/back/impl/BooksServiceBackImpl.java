package com.xriamer.store.service.back.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.back.IBooksServiceBack;
import com.xriamer.store.vo.Books;

import java.util.HashMap;
import java.util.Map;

public class BooksServiceBackImpl implements IBooksServiceBack {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public Map<String, Object> insertPre() throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allItems", DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean insert(Books books) throws Exception {
        try {
            return DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).doCreate(books);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allBooks", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage
                    , lineSize, column, keyWord));
            map.put("booksCount", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> listStatus(int status, int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allBooks", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).findAllByStatus(status, currentPage
                    , lineSize, column, keyWord));
            map.put("booksCount", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).getAllCountByStatus(status, column, keyWord));
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }


}
