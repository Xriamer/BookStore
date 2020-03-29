package com.xriamer.store.service.back.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.back.IItemServiceBack;
import com.xriamer.store.vo.Item;

import java.util.List;
import java.util.Set;

public class ItemServiceBackImpl implements IItemServiceBack {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean insert(Item it) throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doCreate(it);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }

    }

    @Override
    public boolean update(Item it) throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doUpdate(it);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean delete(Set<Integer> ids) throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public List<Item> list() throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConnection()).findAll();
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
