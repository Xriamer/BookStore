package com.xriamer.store.servlet.front;

import com.xriamer.store.factory.ServiceFrontFactory;
import com.xriamer.store.util.ShopcarCookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "ShopcarServletFront", urlPatterns = "/pages/front/cart/ShopcarServletFront/*")
public class ShopcarServletFront extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("insert".equals(status)) {
                path = this.insert(request, response);
            } else if ("list".equals(status)) {
                path = this.list(request);
            } else if ("update".equals(status)) {
                path = this.update(request, response);
            } else if ("delete".equals(status)) {
                path = this.delete(request, response);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        //Map<Integer, Integer> map = (Map<Integer, Integer>) request.getSession().getAttribute("allCars");
        String ids = request.getParameter("ids");
        String result[] = ids.split("\\|");
        Set<Integer> set = new HashSet<>();
        for (int x = 0; x < result.length; x++) {
            //map.remove(Integer.parseInt(result[x]));
            set.add(Integer.parseInt(result[x]));
        }
        ShopcarCookieUtil.removeCart(request, response, set);
        //request.getSession().setAttribute("allCars", map);
        return "/pages/front/cart/ShopcarServletFront/list";
    }

    private String insert(HttpServletRequest request, HttpServletResponse response) {
        //保存图书编号(Integer、key) 购买数量(Integer、key)
        Map<Integer, Integer> map = ShopcarCookieUtil.loadCart(request);//先读取出已有的数据
//        if (request.getSession().getAttribute("allCars") != null) { //表示以及存储过了Map集合
//            map = (Map<Integer, Integer>) request.getSession().getAttribute("allCars");//取出原始的保存信息
//        } else { //如果没有Session属性，可能是第一次操作
//            map = new HashMap<Integer, Integer>();
//        }
        int bid = Integer.parseInt(request.getParameter("bid"));
        int count = 1;
        if (map.containsKey(bid)) { //判断指定的key是否存在，如果存在证明此商品以及保存完毕
            count = map.get(bid) + 1;  //在原始数量上加一
        }
//        map.put(bid, count);  //map集合的特点是如果key重复，则会发生替换
//        request.getSession().setAttribute("allCars", map);
        ShopcarCookieUtil.addCart(request, response, bid, count);
        String msg = "购物车添加成功!";
        String referer = request.getHeader("referer");
        String url = "/pages/front/books/BooksServletFront" + referer.substring(referer.lastIndexOf("/"));
        System.out.println("加入购物车" + map);
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String list(HttpServletRequest request) {
        //所有已经购买的图书信息都保存在Map集合中
        //Map<Integer, Integer> map = (Map<Integer, Integer>) request.getSession().getAttribute("allCars");
        Map<Integer, Integer> map = ShopcarCookieUtil.loadCart(request);
        System.out.println("购物车" + map);
        if (map != null) {  //必须保证已经存在有购买记录，才可以进行列表
            try {
                request.setAttribute("allBooks", ServiceFrontFactory.getIShopcarServiceFrontInstance().listCar(map.keySet()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("allCars", map);
        return "/pages/front/cart/cart_list.jsp";
    }

    public String update(HttpServletRequest request, HttpServletResponse response) {
        ShopcarCookieUtil.clearCart(request, response);
        Map<Integer, Integer> map = new HashMap<>();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String temp = enu.nextElement();
            int bid = Integer.parseInt(temp);
            int count = Integer.parseInt(request.getParameter(temp));
            //map.put(bid, count);
            ShopcarCookieUtil.addCart(request, response, bid, count);
        }
        //替换已有的session数据
        request.getSession().setAttribute("allCars", map);
        return "/pages/front/cart/ShopcarServletFront/list";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }


}