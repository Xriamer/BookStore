package com.xriamer.store.servlet.front;

import com.xriamer.store.exception.EmptyShopcarException;
import com.xriamer.store.exception.UnEnoughAmountException;
import com.xriamer.store.exception.UncompleteMemberInformationException;
import com.xriamer.store.factory.ServiceFrontFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrdersServletFront", urlPatterns = "/pages/front/orders/OrdersServletFront/*")
public class OrdersServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        //request.getRequestDispatcher(path).forward(request, response);
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("insert".equals(status)) {
                path = this.insert(request);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String insert(HttpServletRequest request) {
        String msg = null;
        String url = null;
        String mid = (String) request.getSession().getAttribute("mid");
        try {
            if (ServiceFrontFactory.getIOrdersServiceFrontInstance().insert(mid)) {
                msg = "订单创建成功！";
                url = "/index.jsp";
            }
        } catch (UncompleteMemberInformationException e) {
            msg = "个人信息不完整，无法进行订单创建";
            url = "/pages/front/member/MemberInfoServletFront/updatePre";
            e.printStackTrace();
        } catch (UnEnoughAmountException e) {
            msg = "图书库存数量不足，请修改购买数量！";
            url = "/pages/front/cart/ShopcarServletFront/list";
            e.printStackTrace();
        } catch (EmptyShopcarException e) {
            msg = "您还未选择任何图书，请先选择图书！";
            url = "/pages/front/books/BooksServletFront/list";
            e.printStackTrace();
        } catch (SQLException throwables) {
            msg = "订单创建失败!";
            throwables.printStackTrace();
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }
}