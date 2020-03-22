package com.xriamer.store.dao;

import com.xriamer.store.vo.Admin;

public interface IAdminDAO extends IDAO<String, Admin> {
    /**
     * 本操作实现管理员的登录功能，在登录功能完成之后需要将上一次的登陆时间取出
     * 因为传递的是VO类的对象，所以直接将登陆日期设置到此对象中即可返回
     *
     * @param ad 包含有aid和password 数据
     * @return 登陆成功返回true, 否则返回false
     * @throws Exception
     */
    public boolean findLogin(Admin ad) throws Exception;

    /**
     * 本操作是更新最后一次的登陆日期，只需要传入要更新的管理员编号即可
     *
     * @param aid 管理员编号
     * @return 更新成功返回true，否则返回false
     * @throws Exception
     */
    public boolean doUpdateLastdate(String aid) throws Exception;

}
