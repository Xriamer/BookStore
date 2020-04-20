package com.xriamer.store.factory;

import com.xriamer.store.dao.*;
import com.xriamer.store.dao.impl.*;

import java.sql.Connection;

public class DAOFactory {
    public static IMemberDAO getIMemberDAOInstance(Connection conn) {
        return new MemberDAOImpl(conn);
    }

    public static IAdminDAO getIAdminDAOInstance(Connection conn) {
        return new AdminDAOImpl(conn);
    }

    public static IItemDAO getIItemDAOInstance(Connection conn) {
        return new ItemDAOImpl(conn);
    }

    public static IBooksDAO getIBookDAOInstance(Connection conn) {
        return new BooksDAOImpl(conn);
    }

    public static IShopcarDAO getIShopcarDAOInstance(Connection conn) {
        return new ShopcarDAOImpl(conn);
    }
}
