package com.xriamer.store.service.front.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.front.IBooksServiceFront;

import java.util.HashMap;
import java.util.Map;

public class BooksServiceFrontImpl implements IBooksServiceFront {
    private DatabaseConnection dbc=new DatabaseConnection();

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("allItems", DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
            map.put("allBooks", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).findAllByStatus(1,currentPage,lineSize,column,keyWord));
            map.put("booksCount", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).getAllCountByStatus(1,column,keyWord));
            return  map;

        }catch (Exception e){
            throw  e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> listByItem(int iid, int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("allItems", DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll());
            map.put("allBooks", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).findAllByItem(iid,1,currentPage,lineSize,column,keyWord));
            map.put("booksCount", DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).getAllCountByItem(iid,1,column,keyWord));
            return  map;

        }catch (Exception e){
            throw  e;
        }finally {
            this.dbc.close();
        }
    }
}
