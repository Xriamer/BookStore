package com.xriamer.store.factory;

import com.xriamer.store.dao.IMemberDAO;
import com.xriamer.store.dao.impl.MemberDAOImpl;

import java.sql.Connection;

public class DAOFactory {
    public static IMemberDAO getIMemberDAOInstance(Connection conn) {
        return new MemberDAOImpl(conn);
    }
}
