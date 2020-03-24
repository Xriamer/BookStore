package com.xriamer.store.service.back;

import java.util.Map;

public interface IMemberServiceBack {
    /**
     * 进行全部用户的分页数据显示，要调用IMemberDAO的如下操作:<br>
     *     <li>调用IMemberDAO.findall()方法，查询全部数据</>
     *     <li>调用IMemberDAO.findAllCount()方法，统计出全部数据量</li>
     * @param column  模糊查询列
     * @param keyWord  模糊查询关键字
     * @param currentPage  当前所在页
     * @param lineSize  每页所显示的数据量
     * @return  由于要返回的数据有list与Integer两种类型，所以使用Map返回，包含如下内容:<br>
     *     <li>key=allMembers,value=findAllSpilt()</li>
     *     <li>key=memberCount,value=getAllCount()</li>
     * @throws Exception
     */
    public Map<String,Object> list(int currentPage, int lineSize,String column, String keyWord) throws Exception;
}
