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
import java.io.File;
import java.io.IOException;
import java.util.*;

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
            } else if ("listStatus".equals(status)) {
                path = this.listStatus(request);
            } else if ("updateStatus".equals(status)) {
                path = this.updateStatus(request);
            } else if ("updatePre".equals(status)) {
                path = this.updatePre(request);
            } else if ("update".equals(status)) {
                path = this.update(request, response);
            } else if ("delete".equals(status)) {
                path = this.delete(request);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    private String delete(HttpServletRequest request) {
        String msg = null;
        String url = null;
        String referer = request.getHeader("referer"); //取之前的数据
        String type = request.getParameter("type");    //区分类型
        String ids = request.getParameter("ids");      //包含有"id:photo"信息
        if (ValidateUtil.validateEmpty(ids)) {
            Set<Integer> allIds = new HashSet<Integer>();
            Set<String> allPhotos = new HashSet<String>();
            String result[] = ids.split("\\|");
            for (int x = 0; x < result.length; x++) {
                String temp[] = result[x].split(":");
                allIds.add(Integer.parseInt(temp[0]));
                if (!"nophoto.jpg".equals(temp[1])) {
                    allPhotos.add(temp[1]);
                }
            }
            System.out.println("----"+allIds);
            System.out.println("----"+allPhotos);
            try {
                if (ServiceBackFactory.getIBookServiceBackInstance().delete(allIds)) {
                    if (allPhotos.size() > 0) {
                        Iterator<String> iter = allPhotos.iterator();
                        while (iter.hasNext()) {
                            String filePath = super.getServletContext().getRealPath("upload/books/") + iter.next();
                            File file = new File(filePath);
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                    }
                    msg = "图书信息删除成功!";
                } else {
                    msg = "图书信息删除失败!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "要删除的数据有错误，请重新操作";
        }
        url = "/pages/back/admin/books/BooksServletBack" + referer.substring(referer.lastIndexOf("/"));
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String insert(HttpServletRequest request, HttpServletResponse response) {
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

    public String insertPre(HttpServletRequest request) {
        try {
            Map<String, Object> map = ServiceBackFactory.getIBookServiceBackInstance().insertPre();
            request.setAttribute("allItems", map.get("allItems"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/admin/books/books_insert.jsp";
    }

    public String updatePre(HttpServletRequest request) {
        String bid = request.getParameter("bid");
        String referer = request.getHeader("referer"); //取之前的数据
        if (ValidateUtil.validateEmpty(bid)) {
            try {
                Map<String, Object> map = ServiceBackFactory.getIBookServiceBackInstance().updatePre(Integer.parseInt(bid));
                request.setAttribute("allItems", map.get("allItems"));
                request.setAttribute("books", map.get("books"));
                request.setAttribute("back", "/pages/back/admin/books/BooksServletBack" + referer.substring(referer.lastIndexOf("/")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/pages/back/admin/books/books_update.jsp";
        } else {
            request.setAttribute("msg", "还未选择要更新的数据，请重新确认！");
            request.setAttribute("url", "/pages/back/admin/books/BooksServletBack" + referer.substring(referer.lastIndexOf("/")));
            return "/pages/forward.jsp";
        }
    }

    public String update(HttpServletRequest request, HttpServletResponse response) {
        String msg = null;
        String url = null;
        SmartUpload smart = new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(), request, response);
            smart.upload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String oldpic = smart.getRequest().getParameter("oldpic");
        String bid = smart.getRequest().getParameter("bid");
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
            books.setBid(Integer.parseInt(bid));
            books.setTitle(title);
            books.setPrice(Double.parseDouble(price));
            books.setWriter(writer);
            books.setPublisher(publisher);
            books.setIsbn(isbn);
            books.setAmount(Integer.parseInt(amount));
            books.setNote(note);
            books.setStatus(Integer.parseInt(status));
            Item item = new Item();
            item.setIid(Integer.parseInt(iid));
            books.setItem(item);
            try {
                if (smart.getFiles().getSize() > 0) {
                    if (smart.getFiles().getFile(0).getContentType().contains("image")) {
                        if ("nophoto.jpg".equals(oldpic)) {    //之前没有上传图片，需要重新生成名称
                            books.setPhoto(UUID.randomUUID() + "." + smart.getFiles().getFile(0).getFileExt());
                        } else {
                            books.setPhoto(oldpic);
                        }
                    } else {
                        books.setPhoto(oldpic);
                    }
                } else {
                    books.setPhoto(oldpic);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ServiceBackFactory.getIBookServiceBackInstance().update(books)) {
                    String filePath = super.getServletContext().getRealPath("/upload/books/") + books.getPhoto();
                    System.out.println(filePath);
                    if (smart.getFiles().getSize() > 0) {
                        if (smart.getFiles().getFile(0).getContentType().contains("image")) {
                            smart.getFiles().getFile(0).saveAs(filePath);
                        }
                    }
                    msg = "图书信息修改成功！";
                } else {
                    msg = "图书信息修改失败！";
                }
                //列表页面→更新表单→提交至更新完成的Servlet→forward.jsp页面
                //因此无法取得列表页面的头信息，需要在updatePre()方法中提前传入back url
                url = smart.getRequest().getParameter("back");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "图书修改数据出错，无法进行商品的信息修改！";
            url = request.getParameter("back");
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
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

    public String listStatus(HttpServletRequest request) {
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
        int status = Integer.parseInt(request.getParameter("status"));//接收状态
        try {
            Map<String, Object> map = ServiceBackFactory.getIBookServiceBackInstance().listStatus(status, currentPage, lineSize, column, keyWord);
            request.setAttribute("allBooks", map.get("allBooks"));
            request.setAttribute("allRecorders", map.get("booksCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("lineSize", lineSize);
        request.setAttribute("column", column);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("columnData", columnData);
        request.setAttribute("url", "/pages/back/admin/books/BooksServletBack/listStatus");
        request.setAttribute("paramName", "status");
        request.setAttribute("paramValue", String.valueOf(status));
        return "/pages/back/admin/books/books_list.jsp";
    }

    public String updateStatus(HttpServletRequest request) {
        String msg = null;
        String url = null;
        String referer = request.getHeader("referer"); //取之前的数据
        String type = request.getParameter("type");      //区分类型
        String ids = request.getParameter("ids");
        if (ValidateUtil.validateEmpty(ids)) {
            String result[] = ids.split("\\|");  //数据组成 id:photo
            Set<Integer> all = new HashSet<Integer>();
            for (int x = 0; x < result.length; x++) {
                String temp[] = result[x].split(":");
                all.add(Integer.parseInt(temp[0]));    //id
            }
            boolean flag = false;  //保存最终的操作状态
            try {
                if ("up".equals(type)) {
                    flag = ServiceBackFactory.getIBookServiceBackInstance().updateUp(all);
                }
                if ("down".equals(type)) {
                    flag = ServiceBackFactory.getIBookServiceBackInstance().updateDown(all);
                }
                if ("delete".equals(type)) {
                    flag = ServiceBackFactory.getIBookServiceBackInstance().updateDelete(all);
                }
                if (flag) {
                    msg = "图书状态更新成功";
                } else {
                    msg = "图书状态更新失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "要更新的数据有错误，请重新操作";
        }
        url = "/pages/back/admin/books/BooksServletBack" + referer.substring(referer.lastIndexOf("/"));
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

}