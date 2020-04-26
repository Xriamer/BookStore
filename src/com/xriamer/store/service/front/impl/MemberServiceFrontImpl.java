package com.xriamer.store.service.front.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.front.IMemberServiceFront;
import com.xriamer.store.vo.Member;

public class MemberServiceFrontImpl implements IMemberServiceFront {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean regist(Member mb) throws Exception {
        try {
            if (DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById(mb.getMid()) == null) {
                return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doCreate(mb);
            }
            return false;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }

    }

    @Override
    public boolean active(Member mb) throws Exception {
        try {
            if (DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findByCode(mb.getMid(), mb.getCode())) {
                return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doUpdateStatus(mb.getMid(), 1);
            }
            return false;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean login(Member mb) throws Exception {
        try {
            return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findLogin(mb);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Member updatePre(String mid) throws Exception {
        try {
            return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findById(mid);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(Member books) throws Exception {
        try {
            return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doUpdateMember(books);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
