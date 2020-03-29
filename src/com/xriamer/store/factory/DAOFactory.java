package com.xriamer.store.factory;

import com.xriamer.store.dao.IAdminDAO;
import com.xriamer.store.dao.IBooksDAO;
import com.xriamer.store.dao.IItemDAO;
import com.xriamer.store.dao.IMemberDAO;
import com.xriamer.store.dao.impl.AdminDAOImpl;
import com.xriamer.store.dao.impl.BooksDAOImpl;
import com.xriamer.store.dao.impl.ItemDAOImpl;
import com.xriamer.store.dao.impl.MemberDAOImpl;

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
}
