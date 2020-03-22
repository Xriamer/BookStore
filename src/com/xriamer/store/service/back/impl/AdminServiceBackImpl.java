package com.xriamer.store.service.back.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.back.IAdminServiceBack;
import com.xriamer.store.vo.Admin;

public class AdminServiceBackImpl implements IAdminServiceBack {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean login(Admin ad) throws Exception {
        try {
            if (DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findLogin(ad)) {
                return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doUpdateLastdate(ad.getAid());
            }
            return false;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }

    }
}
