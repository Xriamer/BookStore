package com.xriamer.utils;

import javax.servlet.http.HttpServletRequest;

public class BasePath {
    public static String getBasePath(HttpServletRequest request){
        String path=request.getContextPath();
        String basePath=request.getScheme()+"://"
                + request.getServerName()+":"
                + request.getServerName()+":"+
                + request.getServerPort()
                + path;
        return basePath;
    }

}
