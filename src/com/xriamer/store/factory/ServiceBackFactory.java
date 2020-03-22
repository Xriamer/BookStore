package com.xriamer.store.factory;

import com.xriamer.store.service.back.IAdminServiceBack;
import com.xriamer.store.service.back.impl.AdminServiceBackImpl;

public class ServiceBackFactory {
    public static IAdminServiceBack getIAdminServiceBackInstance() {
        return new AdminServiceBackImpl();
    }
}
