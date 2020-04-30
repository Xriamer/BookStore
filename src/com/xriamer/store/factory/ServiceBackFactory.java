package com.xriamer.store.factory;

import com.xriamer.store.service.back.*;
import com.xriamer.store.service.back.impl.*;

public class ServiceBackFactory {
    public static IAdminServiceBack getIAdminServiceBackInstance() {
        return new AdminServiceBackImpl();
    }

    public static IMemberServiceBack getIMemberServiceBackInstance() {
        return new MemberServiceBackImpl();
    }

    public static IItemServiceBack getIItemServiceBackInstance() {
        return new ItemServiceBackImpl();
    }

    public static IBooksServiceBack getIBookServiceBackInstance() {
        return new BooksServiceBackImpl();
    }

    public static IOrdersServiceBack getIOrdersServiceBackInstance() {
        return new OrdersServiceBackImpl();
    }
}
