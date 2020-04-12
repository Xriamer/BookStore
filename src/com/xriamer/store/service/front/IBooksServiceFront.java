package com.xriamer.store.service.front;

import com.xriamer.store.vo.Books;

import java.util.Map;

public interface IBooksServiceFront {

    /**
     * 在显示全部图书列表的时候使用，本操作要执行如下的调用:<br>
     * <li>要调用IITemDAO.findAll()方法查询出全部的图书分类</li>
     * <ii>要调用IBooksDAO.findAllByStatus()方法查询出全部数据</ii>
     * <ii>要调用IBooksDAO.getAllCountByStatus()方法查询出全部数据</ii>
     * <li>设置的时候status设置的内容为1，表示上架商品</li>
     *
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return 返回的数据一共包含有三类内容:<br>
     * <ii>key=allItems、value=IITemDAO.findAll(),保存的是list<Item></ii>
     * <ii>key=allBooks,value=IBooksDAO.findAllByStatus,保存的是list<Books></ii>
     * <ii>key=booksCount,value=IBooksDAO.getAllCountByStatus(),保存的是Integer</ii>
     * @throws Exception
     */
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;

    /**
     * 在显示全部图书列表的时候使用，本操作要执行如下的调用:<br>
     * <li>要调用IITemDAO.findAll()方法查询出全部的图书分类</li>
     * <ii>要调用IBooksDAO.findAllByItem()方法查询出全部数据</ii>
     * <ii>要调用IBooksDAO.getAllCountByItem()方法查询出全部数据</ii>
     * <li>设置的时候status设置的内容为1，表示上架图书</li>
     *
     * @param iid  图书类型编号
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return 返回的数据一共包含有三类内容:<br>
     * <ii>key=allItems、value=IITemDAO.findAll(),保存的是list<Item></ii>
     * <ii>key=allBooks,value=IBooksDAO.findAllByItem,保存的是list<Books></ii>
     * <ii>key=booksCount,value=IBooksDAO.getAllCountByItem(),保存的是Integer</ii>
     * @throws Exception
     */
    public Map<String, Object> listByItem(int iid, int currentPage, int lineSize, String column, String keyWord) throws Exception;

    /**
     * 本操作要显示图书的完整信息，包含的操作如下:<br>
     *     <li>调用IBooksDAO.findById()方法可以根据图书编号完整查询信息</li>
     *     <li>调用IBooksDAO.doUpdateBow()方法，更新访问次数</li>
     *     <li>调用IItemDAO.findById()方法，查询出商品所属分类的名称</li>
     * @param bid  图书编号
     * @return  商品分类设置在商品对象中返回，如果有数据返回实例化对象，否则返回null值
     * @throws Exception
     */
    public Books show(int bid) throws Exception;
}
