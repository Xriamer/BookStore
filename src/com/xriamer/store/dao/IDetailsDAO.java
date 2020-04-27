package com.xriamer.store.dao;

import com.xriamer.store.vo.Details;

import java.sql.SQLException;
import java.util.List;

public interface IDetailsDAO extends IDAO<Integer, Details>{
    /**
     * 批量创建订单详情
     * @param details
     * @return
     * @throws Exception
     */
    public boolean doCreateBatch(List<Details> details) throws SQLException;
}
