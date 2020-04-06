package com.xriamer.store.service.back.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.back.IBooksServiceBack;
import com.xriamer.store.vo.Books;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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


    @Override
    public boolean updateUp(Set<Integer> bid) throws Exception {
        try {
            return DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).doUpdateStatus(bid, 1);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean updateDown(Set<Integer> bid) throws Exception {
        try {
            return DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).doUpdateStatus(bid, 0);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean updateDelete(Set<Integer> bid) throws Exception {
        try {
            return DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).doUpdateStatus(bid, 2);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> updatePre(int bid) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allItems", DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
            map.put("books", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).findById(bid));
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(Books books) throws Exception {
        try {
            return DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).doUpdate(books);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }


}
