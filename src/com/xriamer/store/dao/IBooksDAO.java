package com.xriamer.store.dao;

import com.xriamer.store.vo.Books;

import java.util.List;

public interface IBooksDAO extends IDAO<Integer, Books> {
    public List<Books> findAllByStatus(Integer status,Integer currentPage,Integer lineSize,String column,String keyWord) throws Exception;
    public Integer getAllCountByStatus(Integer status,String column,String keyWord) throws Exception;
}
