package com.xriamer.store.servlet.back;

import com.jspsmart.upload.SmartUpload;
import com.xriamer.store.factory.ServiceBackFactory;
import com.xriamer.store.vo.Admin;
import com.xriamer.store.vo.Books;
import com.xriamer.store.vo.Item;
import com.xriamer.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "BooksServletBack", urlPatterns = "/pages/back/admin/books/BooksServletBack/*")
public class BooksServletBack extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("insertPre".equals(status)) {
                path = this.insertPre(request);
            } else if ("insert".equals(status)) {
                path = this.insert(request, response);
            } else if ("list".equals(status)) {
                path = this.list(request);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    private String insert(HttpServletRequest request, HttpServletResponse response) {
        String msg = null;
        String url = null;
        SmartUpload smart = new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(), request, response);
            smart.upload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String iid = smart.getRequest().getParameter("iid");
        String title = smart.getRequest().getParameter("title");
        String price = smart.getRequest().getParameter("price");
        String writer = smart.getRequest().getParameter("writer");
        String publisher = smart.getRequest().getParameter("publisher");
        String isbn = smart.getRequest().getParameter("isbn");
        String amount = smart.getRequest().getParameter("amount");
        String note = smart.getRequest().getParameter("note");
        String status = smart.getRequest().getParameter("status");
        if (ValidateUtil.validateEmpty(title) &&
                ValidateUtil.validateRegex(price, "\\d+(\\.\\d{1,2})?") &&
                ValidateUtil.validateRegex(amount, "\\d+") &&
                ValidateUtil.validateEmpty(note) &&
                ValidateUtil.validateRegex(status, "\\d") &&
                ValidateUtil.validateRegex(iid, "\\d+") &&
                ValidateUtil.validateEmpty(writer) &&
                ValidateUtil.validateEmpty(publisher) &&
                ValidateUtil.validateEmpty(isbn)) {
            Books books = new Books();
            books.setTitle(title);
            books.setPrice(Double.parseDouble(price));
            books.setWriter(writer);
            books.setPublisher(publisher);
            books.setIsbn(isbn);
            books.setAmount(Integer.parseInt(amount));
            books.setNote(note);
            books.setStatus(Integer.parseInt(status));
            books.setPubdate(new Date());
            books.setBow(0);
            Item item = new Item();
            item.setIid(Integer.parseInt(iid));
            books.setItem(item);
            String aid = (String) request.getSession().getAttribute("aid");
            Admin admin = new Admin();
            admin.setAid(aid);
            books.setAdmin(admin);
            try {
                if (smart.getFiles().getSize() > 0) {
                    if (smart.getFiles().getFile(0).getContentType().contains("image")) {
                        books.setPhoto(UUID.randomUUID() + "." + smart.getFiles().getFile(0).getFileExt());
                    } else {
                        books.setPhoto("nophoto.jpg");
                    }
                } else {
                    books.setPhoto("nophoto.jpg");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ServiceBackFactory.getIBookServiceBackInstance().insert(books)) {
                    String filePath = super.getServletContext().getRealPath("/upload/books/") + books.getPhoto();
                    if (smart.getFiles().getSize() > 0) {
                        if (smart.getFiles().getFile(0).getContentType().contains("image")) {
                            smart.getFiles().getFile(0).saveAs(filePath);
                        }
                    }
                    msg = "商品信息发布成功！";
                } else {
                    msg = "商品信息发布失败！";
                }
                url = "/pages/back/admin/books/BooksServletBack/insertPre";
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "图书增加数据出错，无法进行商品的信息发布！";
            url = "/pages/back/admin/books/BooksServletBack/insertPre";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    private String insertPre(HttpServletRequest request) {
        try {
            Map<String, Object> map = ServiceBackFactory.getIBookServiceBackInstance().insertPre();
            request.setAttribute("allItems", map.get("allItems"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/admin/books/books_insert.jsp";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String list(HttpServletRequest request) {
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
            Map<String, Object> map = ServiceBackFactory.getIBookServiceBackInstance().list(currentPage, lineSize, column, keyWord);
            request.setAttribute("allBooks", map.get("allBooks"));
            request.setAttribute("allRecorders", map.get("BooksCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("lineSize", lineSize);
        request.setAttribute("column", column);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("columnData", columnData);
        request.setAttribute("url", "/pages/back/admin/books/BooksServletBack/list");
        return "/pages/back/admin/books/books_list.jsp";
    }

}