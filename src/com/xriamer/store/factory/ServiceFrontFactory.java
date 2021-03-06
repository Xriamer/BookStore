package com.xriamer.store.factory;

import com.xriamer.store.service.front.IBooksServiceFront;
import com.xriamer.store.service.front.IMemberServiceFront;
import com.xriamer.store.service.front.IOrdersServiceFront;
import com.xriamer.store.service.front.IShopcarServiceFront;
import com.xriamer.store.service.front.impl.BooksServiceFrontImpl;
import com.xriamer.store.service.front.impl.MemberServiceFrontImpl;
import com.xriamer.store.service.front.impl.OrdersServiceFrontImpl;
import com.xriamer.store.service.front.impl.ShopcarServiceFrontImpl;

public class ServiceFrontFactory {
    public static IMemberServiceFront getIMemberServiceFrontInstance() {
        return new MemberServiceFrontImpl();
    }

    public static IBooksServiceFront getIBookServiceFrontInstance() {
        return new BooksServiceFrontImpl();
    }

    public static IShopcarServiceFront getIShopcarServiceFrontInstance() {
        return new ShopcarServiceFrontImpl();
    }

    public static IOrdersServiceFront getIOrdersServiceFrontInstance() {
        return new OrdersServiceFrontImpl();
    }
}
