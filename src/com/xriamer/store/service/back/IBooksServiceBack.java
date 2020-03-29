package com.xriamer.store.service.back;

import com.xriamer.store.vo.Books;

import java.util.Map;

public interface IBooksServiceBack {
    /**
     * 图书增加前的数据查询操作，要查询出所有的栏目信息：
     * <li>调用IItemDAO.finaAll()方法查询出所有的分类 </li>
     * @return  数据以Map集合形式返回，包含有以下内容:<br>
     *     <li>key= allItems,value=IItemDAO.findAll()返回值</li>
     * @throws Exception
     */
    public Map<String,Object> insertPre() throws Exception;

    /**
     * 图书增加操作，增加的时候调用IBooksDAO.doCreate()方法
     * @param book 包含有要增加图书的信息
     * @return  增加成功返回true，否则返回false
     * @throws Exception
     */
    public boolean insert(Books book)throws Exception;
}
