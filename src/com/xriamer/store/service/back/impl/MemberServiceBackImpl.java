package com.xriamer.store.service.back.impl;

import com.xriamer.store.dbc.DatabaseConnection;
import com.xriamer.store.factory.DAOFactory;
import com.xriamer.store.service.back.IMemberServiceBack;

import java.util.HashMap;
import java.util.Map;

public class MemberServiceBackImpl implements IMemberServiceBack {
    private DatabaseConnection dbc=new DatabaseConnection();
    @Override
    public Map<String, Object> list(int currentPage, int lineSize,String column, String keyWord) throws Exception {
        try {
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("allMembers", DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage,lineSize,column,keyWord));
            map.put("memberCount",DAOFactory.getIMemberDAOInstance(this.dbc.getConnection()).getAllCount(column,keyWord));
            return map;
        }catch (Exception e){
            throw e;
        }finally{
            this.dbc.close();
        }
    }
}
