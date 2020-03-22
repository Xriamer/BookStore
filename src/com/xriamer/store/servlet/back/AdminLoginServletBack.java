package com.xriamer.store.servlet.back;

import com.xriamer.store.factory.ServiceBackFactory;
import com.xriamer.store.vo.Admin;
import com.xriamer.utils.MD5Code;
import com.xriamer.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminLoginServletBack", urlPatterns = "/pages/back/AdminLoginServletBack/*")
public class AdminLoginServletBack extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        //request.getRequestDispatcher(path).forward(request, response);
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("login".equals(status)) {
                path = this.login(request);
            } else if ("logout".equals(status)) {
                path = this.logout(request);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String login(HttpServletRequest request) {
        String msg = null;
        String url = null;
        String aid = request.getParameter("aid");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String rand = (String) request.getSession().getAttribute("rand");
        if (ValidateUtil.validateEmpty(aid) &&
                ValidateUtil.validateEmpty(password) &&
                ValidateUtil.validateEmpty(code) &&
                ValidateUtil.validateEmpty(rand)) {
            if (ValidateUtil.validateSame(code, rand)) {//如果验证码输入正确
                Admin ad = new Admin();
                ad.setAid(aid);
                ad.setPassword(new MD5Code().getMD5ofStr(password));
                try {
                    if (ServiceBackFactory.getIAdminServiceBackInstance().login(ad)) {
                        //如果用户能正常的登录，那么就在Session中保存属性
                        //用户登录后会跳转至Forward.jsp再跳转至首页的索引页面上，为了要传递属性，所以要借助Session
                        request.getSession().setAttribute("aid", aid);
                        request.getSession().setAttribute("lastdate", ad.getLastdate());
                        msg = "管理员登录成功！";
                        url = "/pages/back/admin/index.jsp";
                    } else {
                        msg = "管理员登陆失败，错误的用户名或密码，请重新登陆!";
                        url = "/pages/back/login.jsp";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                msg = "输入的验证码错误，请重新输入!";
                url = "/pages/back/login/jsp";
            }
        } else {
            msg = "管理员登录信息填写错误！";
            url = "/pages/back/login.jsp";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        request.setAttribute("msg", "管理员注销成功，再见");
        request.setAttribute("url", "/pages/back/login.jsp");
        return "/pages/forward.jsp";
    }

}