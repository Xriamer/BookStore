package com.xriamer.store.service.front.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.front.IMemberServiceFront;
import com.xriamer.store.vo.Member;

public class MemberServiceFrontImpl implements IMemberServiceFront {
    private DatabaseConnection dbc=new DatabaseConnection();
    @Override
    public boolean regist(Member mb) throws Exception {
        try {
            if(DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findByMid(mb.getMid())==null){
                return DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).doCreate(mb);
            }
            return false;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }

    }
}
