package com.xriamer.store.servlet.front;

import com.xriamer.store.factory.ServiceFrontFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "BooksServletFront", urlPatterns = "/pages/front/books/BooksServletFront/*")
public class BooksServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        //request.getRequestDispatcher(path).forward(request, response);
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("list".equals(status)) {
                path = this.list(request);
            }
//            else if("show".equals(request)){
//                path=this.show(request);
//            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

//    private String show(HttpServletRequest request) {
//
//    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String list(HttpServletRequest request) {
        String iid = request.getParameter("iid");  //先接受IID数据
        int currentPage = 1;
        int lineSize = 10;
        String column = null;
        String keyWord = null;
        String columnData = "图书名称:title|作者:writer|出版社:publisher|发布管理员:aid";
        try {
            currentPage = Integer.parseInt(request.getParameter("cp"));
        } catch (Exception e) {
        }
        try {
            lineSize = Integer.parseInt(request.getParameter("ls"));
        } catch (Exception e) {
        }
        column = request.getParameter("col");
        keyWord = request.getParameter("kw");
        if (column == null) {
            column = "title";
        }
        if (keyWord == null) {
            keyWord = "";//表示查询全部
        }
        try {
            Map<String, Object> map = null;
            if (iid == null || "0".equals(iid)) {             //属于查询全部
                map = ServiceFrontFactory.getIBookServiceFrontInstance().list(currentPage, lineSize, column, keyWord);
            //System.out.println(map);
            } else {                                      //根据item分类进行图书信息查询
                map = ServiceFrontFactory.getIBookServiceFrontInstance().listByItem(Integer.parseInt(iid), currentPage, lineSize, column, keyWord);
            //System.out.println(map);
            }
            request.setAttribute("allItems", map.get("allItems"));
            request.setAttribute("allBooks", map.get("allBooks"));
            request.setAttribute("allRecorders", map.get("booksCount"));
            //System.out.println(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("lineSize", lineSize);
        request.setAttribute("column", column);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("columnData", columnData);
        request.setAttribute("url", "/pages/back/admin/books/BooksServletBack/listStatus");
        request.setAttribute("paramName", "iid");
        request.setAttribute("paramValue", iid);
        return "/pages/front/books/books_list.jsp";
    }
}