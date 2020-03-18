package com.xriamer.store.dao;

import com.xriamer.store.vo.Member;

public interface IMemberDAO extends IDAO<String, Member>{


    /**
     * 根据mid查询
     * @param mid
     * @return
     * @throws Exception
     */
   //public Member findByMid(String mid) throws Exception;

    /**
     * 判断给定的mid与给定的code是否相同
     * @param mid  要激活的用户mid
     * @param code 设置好的激活码
     * @return     如果用户ID与激活码相匹配，则可以返回true，否则返回false
     */
    public boolean findByCode(String mid,String code) throws Exception;

    /**
     * 更新指定用户操作
     * @param mid  用户id
     * @param status  用户状态(0锁定 1激活 2等待激活)
     * @return
     * @throws Exception
     */
    public boolean doUpdateStatus(String mid,Integer status)throws Exception;
}
