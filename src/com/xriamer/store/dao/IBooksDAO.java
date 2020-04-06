package com.xriamer.store.dao;

import com.xriamer.store.vo.Books;

import java.util.List;
import java.util.Set;

public interface IBooksDAO extends IDAO<Integer, Books> {
    public List<Books> findAllByStatus(Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;

    public Integer getAllCountByStatus(Integer status, String column, String keyWord) throws Exception;

    public boolean doUpdateStatus(Set<Integer> id, Integer status) throws Exception;

    public Set<String> findAllByPhoto(Set<Integer> id) throws Exception;
}
