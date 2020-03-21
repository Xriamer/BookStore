package com.xriamer.store.servlet.front;

import com.xriamer.store.factory.ServiceFrontFactory;
import com.xriamer.store.vo.Member;
import com.xriamer.utils.BasePath;
import com.xriamer.utils.CookieUtil;
import com.xriamer.utils.MD5Code;
import com.xriamer.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@WebServlet(name = "MemberServletFront", urlPatterns = "/pages/MemberServletFront/*")
public class MemberServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("regist".equals(status)) {
                path = this.regist(request);
            } else if ("active".equals(status)) {
                path = this.active(request);
            } else if ("login".equals(status)) {
                path = this.login(request, response);
            } else if ("logout".equals(status)) {
                path = this.logout(request, response);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String regist(HttpServletRequest request) {
//        ValidateUtil.validateEmpty(request,"mid");
        String msg = null;//执行forward.jsp使用
        String url = null;//执行forward.jsp使用
        String mid = request.getParameter("mid");
        String password = request.getParameter("password");
        //必须验证通过，才可以进行后续的功能操作
        if (ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(password)) {
            Member mb = new Member();//要将数据封装在Member对象中
            mb.setMid(mid);
            mb.setPassword(new MD5Code().getMD5ofStr(password));
            mb.setRegdate(new Date());
            mb.setPhoto("nophoto.jpg");
            mb.setCode(UUID.randomUUID().toString());//生成一个随记的Code码
            mb.setStatus(2);//现在属于待激活状态
            try {
                if (ServiceFrontFactory.getIMemberServiceFrontInstance().regist(mb)) {
                    msg = "注册成功，请进行账户激活！";
                    url = "/index.jsp";
                    System.out.println("【发出激活邮件】" + BasePath.getBasePath(request) + "/pages/MemberServletFront/active?mid=" + mid + "&code=" + mb.getCode());
                } else {
                    msg = "用户注册失败，请重新填写新的注册信息！";
                    url = "/pages/member_regist.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "输入的用户注册信息不正确，请重新注册!";
            url = "/pages/member_regist.jsp";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String active(HttpServletRequest request) {
        String msg = null;
        String url = null;
        String mid = request.getParameter("mid");
        String code = request.getParameter("code");
        if (ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(code)) {
            Member mb = new Member();
            mb.setMid(mid);
            mb.setCode(code);
            try {
                if (ServiceFrontFactory.getIMemberServiceFrontInstance().active(mb)) {
                    msg = "用户激活成功，请登录！";
                    url = "/pages/member_login.jsp";
                } else {
                    msg = "用户激活失败，请与管理员联系！";
                    url = "/index.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "错误的激活操作，请与管理员联系！";
            url = "/index.jsp";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) {
        String msg = null;
        String url = null;
        String mid = request.getParameter("mid");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String rand = (String) request.getSession().getAttribute("rand");
        if (ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(password)
                && ValidateUtil.validateEmpty(code) && ValidateUtil.validateEmpty(rand)) {
            if (ValidateUtil.validateSame(code, rand)) {
                Member mb = new Member();
                mb.setMid(mid);
                mb.setPassword(new MD5Code().getMD5ofStr(password));
                try {
                    if (ServiceFrontFactory.getIMemberServiceFrontInstance().login(mb)) {
                        request.getSession().setAttribute("mid", mid);
                        request.getSession().setAttribute("photo", mb.getPhoto());
                        msg = "登录成功！";
                        url = "/index.jsp";
                        if (request.getParameter("remember") != null) {
                            int expiry = Integer.parseInt(request.getParameter("remember"));
                            CookieUtil.save(response, request, "mid", mid, expiry);
                            CookieUtil.save(response, request, "password", mb.getPassword(), expiry);
                        }
                    } else {
                        msg = "登陆失败，错误的用户名或密码！";
                        url = "/pages/member_login.jsp";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                msg = "验证码输入错误，请重新登录！";
                url = "/pages/member_login.jsp";
            }
        } else {
            msg = "信息输入错误，请重新登录！";
            url = "/pages/member_login.jsp";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.clear(request, response);
        request.getSession().invalidate();
        request.setAttribute("msg", "您已经安全退出！");
        request.setAttribute("url", "/index.jsp");
        return "/pages/forward.jsp";
    }
}
