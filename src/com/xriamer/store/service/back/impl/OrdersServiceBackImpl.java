package com.xriamer.store.service.back.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.back.IOrdersServiceBack;
import com.xriamer.store.vo.Orders;

import java.util.HashMap;
import java.util.Map;

public class OrdersServiceBackImpl implements IOrdersServiceBack {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("allOrders", DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord));
            map.put("ordersCount", DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Orders show(int oid) throws Exception {
        try {
            Orders orders = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findById(oid);
            if (orders != null) {
                orders.setAllDetails(DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).findAllByOrders(oid));
            }
            return orders;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
