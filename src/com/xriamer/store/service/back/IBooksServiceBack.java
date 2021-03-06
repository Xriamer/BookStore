package com.xriamer.store.service.back;

import com.xriamer.store.vo.Books;

import java.util.Map;
import java.util.Set;

public interface IBooksServiceBack {
    /**
     * 图书增加前的数据查询操作，要查询出所有的栏目信息：
     * <li>调用IItemDAO.finaAll()方法查询出所有的分类 </li>
     *
     * @return 数据以Map集合形式返回，包含有以下内容:<br>
     * <li>key= allItems,value=IItemDAO.findAll()返回值</li>
     * @throws Exception
     */
    public Map<String, Object> insertPre() throws Exception;

    /**
     * 图书增加操作，增加的时候调用IBooksDAO.doCreate()方法
     *
     * @param book 包含有要增加图书的信息
     * @return 增加成功返回true，否则返回false
     * @throws Exception
     */
    public boolean insert(Books book) throws Exception;

    /**
     * 图书信息的基础列表操作,调用如下的方法:<br>
     * <li>调用iBooksDAO.findAll()方法查询所有的图书信息</li>
     * <li>调用IBooksDAO.getAllCount()方法统计全部数据量</li>
     *
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return 以Map集合返回, 包括如下内容:<br>
     * <li>key=allBooks,value=IbooksDAO.findAll()</li>
     * <li>key=allBooks,value=IbooksDAO.findAll()</li>
     * @throws Exception
     */
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;

    public Map<String, Object> listStatus(int status, int currentPage, int lineSize, String column, String keyWord) throws Exception;

    public boolean updateUp(Set<Integer> bid) throws Exception;

    public boolean updateDown(Set<Integer> bid) throws Exception;

    public boolean updateDelete(Set<Integer> bid) throws Exception;

    /**
     * 图书修改前的数据查询操作，要查询出所有的栏目信息：
     * <li>调用IItemDAO.finaAll()方法查询出所有的分类 </li>
     * <li>调用IItemDAO.findById()方法查询出所有的分类 </li>
     *
     * @return 数据以Map集合形式返回，包含有以下内容:<br>
     * <li>key= allItems,value=IItemDAO.findAll()返回值</li>
     * <li>key= books,value=IBooksDAO.findById()返回值</li>
     * @throws Exception
     */
    public Map<String, Object> updatePre(int bid) throws Exception;

    public boolean update(Books books) throws Exception;

    /**
     * 执行数据的删除操作，但是在删除之后要清除对应的图片信息
     *
     * @param ids
     * @return 返回的数据包含有两项内容 <br>
     * <li>key=flag、value=IbooksDAO.doRemoveBatch()</>
     * <li>key=allPhotos、value=IbooksDAO.findAllByPhoto()</>
     * @throws Exception
     */
    public Map<String, Object> deleteAll(Set<Integer> ids) throws Exception;

    /**
     * 根据编号删除信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean delete(Set<Integer> id) throws Exception;
}
