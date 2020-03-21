package com.xriamer.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CookieUtil {

    /**
     * 定义一个专门负责保存Cookie的方法，可以设置Cookie的名字与保存时间
     *
     * @param response 主要可以使用addCookie()方法保存Cookie对象
     * @param request  主要设置Cookie的保存路径，如果不设置，保存不上
     * @param name     要保存的Cookie名字
     * @param value    要保存的Cookie的对象
     * @param expiry   Cookie的到期时间
     */
    public static void save(HttpServletResponse response, HttpServletRequest request, String name, String value, int expiry) {
        Cookie c = new Cookie(name, value);
        c.setMaxAge(expiry);
        //c.setPath(request.getContextPath());//设置保存路径
        c.setPath("/");
        response.addCookie(c);
    }

    /**
     * 取出全部Cookie的load方法，Map方便查询我们需要查找的内容
     *
     * @param request HttpServletRequest 从看客户端的请求中取出Cookie数据
     * @return 返回Cookie数据
     */
    public static Map<String, String> load(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Cookie c[] = request.getCookies();
        if (c != null) {
            for (int x = 0; x < c.length; x++) {
                map.put(c[x].getName(), c[x].getValue());
            }
        }
        return map;
    }

    public static void clear(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = load(request);//获取Cookie所有数据
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> me = iterator.next();
            Cookie cookie = new Cookie(me.getKey(), "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
}
