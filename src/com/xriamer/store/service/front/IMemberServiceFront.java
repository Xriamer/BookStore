package com.xriamer.store.service.front;

import com.xriamer.store.vo.Member;

public interface IMemberServiceFront {

    /**
     * 实现用户的注册操作,注册时要提供有mid,password,regdate,code,status;<br>
     *     本程序将调用如下的操作：<br>
     *     <li>调用IMemberDAO.findById()方法判断注册ID是否存在</>
     *     <li>调用IMemberDAO.doCreate()方法保存基本信息</li>
     * @param mb 包含有注册信息的mb类信息
     * @return   注册成功返回true,注册失败返回false
     * @throws Exception
     */
    public boolean regist(Member mb) throws Exception;
}
