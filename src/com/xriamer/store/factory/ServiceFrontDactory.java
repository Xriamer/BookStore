package com.xriamer.store.factory;

import com.xriamer.store.service.front.IMemberServiceFront;
import com.xriamer.store.service.front.impl.MemberServiceFrontImpl;

public class ServiceFrontDactory {
    public static IMemberServiceFront getIMemberServiceFrontInstance(){
        return new MemberServiceFrontImpl();
    }
}
