package com.xriamer.store.service.back;

import com.xriamer.store.vo.Member;

import java.util.Map;
import java.util.Set;

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

    /**
     * 进行全部用户的分页数据显示，要调用IMemberDAO的如下操作:<br>
     *     <li>调用IMemberDAO.findAllByStatus()方法，查询全部数据</>
     *     <li>调用IMemberDAO.findAllCountByStatus()方法，统计出全部数据量</li>
     * @param column  模糊查询列
     * @param keyWord  模糊查询关键字
     * @param currentPage  当前所在页
     * @param lineSize  每页所显示的数据量
     * @param status  用户的状态
     * @return  由于要返回的数据有list与Integer两种类型，所以使用Map返回，包含如下内容:<br>
     *     <li>key=allMembers,value=findAllByStatus()</li>
     *     <li>key=memberCount,value=getAllCountByStatus()</li>
     * @throws Exception
     */
    public Map<String,Object> listByStatus(int status,int currentPage,int lineSize,String column,String keyWord) throws Exception;

    /**
     * 将用户状态更新为激活状态(status==1)
     * @param ids
     * @return
     * @throws Exception
     */
    public boolean updateActive(Set<String> ids) throws Exception;

    /**
     * 将用户状态更新为锁定状态(status==0)
     * @param ids
     * @return
     * @throws Exception
     */
    public boolean updateLock(Set<String> ids) throws Exception;

    /**
     * 查看一个人员的完整信息
     * @param id
     * @return
     * @throws Exception
     */
    public Member show(String id) throws Exception;
}
