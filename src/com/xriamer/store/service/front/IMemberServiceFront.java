package com.xriamer.store.service.front;

import com.xriamer.store.vo.Member;

public interface IMemberServiceFront {

    /**
     * 实现用户的注册操作,注册时要提供有mid,password,regdate,code,status;<br>
     * 本程序将调用如下的操作：<br>
     * <li>调用IMemberDAO.findById()方法判断注册ID是否存在</>
     * <li>调用IMemberDAO.doCreate()方法保存基本信息</li>
     *
     * @param mb 包含有注册信息的mb类信息
     * @return 注册成功返回true, 注册失败返回false
     * @throws Exception
     */
    public boolean regist(Member mb) throws Exception;

    /**
     * 实现用户注册的激活操作，只有激活后的用户才可以登录，本操作要调用如下功能：<br>
     * <li>调用IMemberDAO.findByCode()方法判断用户的激活码是否正确</li>
     * <li>调用IMemberDAO.doUpdateStatus()方法修改激活码</li>
     * </>
     *
     * @param mb 包含有mid与code的数据
     * @return 激活成功返回true, 否则返回false
     * @throws Exception
     */
    public boolean active(Member mb) throws Exception;

    /**
     * 用户登录操作，调用IMemberDAO.findLogin()方法，只能登录激活的用户
     *
     * @param mb 包含有mid与password的VO类对象
     * @return 登陆成功返回true，否则返回false
     * @throws Exception
     */
    public boolean login(Member mb) throws Exception;

    /**
     * @param mid
     * @return
     * @throws Exception
     */
    public Member updatePre(String mid) throws Exception;

    /**
     * @param books
     * @return
     * @throws Exception
     */
    public boolean update(Member books) throws Exception;
}
