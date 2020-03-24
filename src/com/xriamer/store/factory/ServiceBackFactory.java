package com.xriamer.store.factory;

import com.xriamer.store.service.back.IAdminServiceBack;
import com.xriamer.store.service.back.IMemberServiceBack;
import com.xriamer.store.service.back.impl.AdminServiceBackImpl;
import com.xriamer.store.service.back.impl.MemberServiceBackImpl;

public class ServiceBackFactory {
    public static IAdminServiceBack getIAdminServiceBackInstance() {
        return new AdminServiceBackImpl();
    }
    public static IMemberServiceBack getIMemberServiceBackInstance(){
        return new MemberServiceBackImpl();
    }
}
