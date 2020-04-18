package com.xriamer.store.service.front.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.front.IShopcarServiceFront;
import com.xriamer.store.vo.Books;

import java.util.List;
import java.util.Set;

public class ShopcarServiceFrontImpl implements IShopcarServiceFront {
    private DatabaseConnection dbc=new DatabaseConnection();
    @Override
    public List<Books> listCar(Set<Integer> ids) throws Exception {
        try {
            return DAOFactory.getIBookDAOInstance(this.dbc.getConnection()).findAllByBid(ids);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
