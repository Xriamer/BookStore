package com.xriamer.store.dao;

import com.xriamer.store.vo.Member;

import java.util.List;
import java.util.Set;

public interface IMemberDAO extends IDAO<String, Member> {


    /**
     * 根据mid查询
     * @param mid
     * @return
     * @throws Exception
     */
    //public Member findByMid(String mid) throws Exception;

    /**
     * 判断给定的mid与给定的code是否相同
     *
     * @param mid  要激活的用户mid
     * @param code 设置好的激活码
     * @return 如果用户ID与激活码相匹配，则可以返回true，否则返回false
     */
    public boolean findByCode(String mid, String code) throws Exception;

    /**
     * 更新指定用户操作
     *
     * @param mid    用户id
     * @param status 用户状态(0锁定 1激活 2等待激活)
     * @return
     * @throws Exception
     */
    public boolean doUpdateStatus(String mid, Integer status) throws Exception;

    /**
     * 用户的登录检查操作，正常登录后可以查询出用户的照片信息，
     * 由于参数接收的是Member对象，所以可以直接将照片信息保存在Member对象
     *
     * @param mb 包含了mid与password的VO类对象
     * @return 登录成功返回true 否则返回false
     * @throws Exception
     */
    public boolean findLogin(Member mb) throws Exception;

    /**
     * 根据用户的的状态来进行数据的列表操作
     *
     * @param status
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public List<Member> findAllByStatus(Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;

    /**
     * 根据用户的状态统计所有的数据量
     *
     * @param status
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public Integer getAllCountByStatus(Integer status, String column, String keyWord) throws Exception;

    /**
     * 是进行数据的批量更新，状态由外部设置
     *
     * @param ids
     * @param status
     * @return
     * @throws Exception
     */
    public boolean doUpdateStatus(Set<String> ids, Integer status) throws Exception;

    /**
     * 完善用户基本信息
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean doUpdateMember(Member vo) throws Exception;
}
