package com.xriamer.servlet.front;

import com.xriamer.store.factory.ServiceFrontDactory;
import com.xriamer.store.vo.Member;
import com.xriamer.utils.BasePath;
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

@WebServlet(name="MemberServletFront",urlPatterns = "/pages/MemberServletFront/*")
public class MemberServletFront extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path="/pages/errors.jsp";
        request.getRequestDispatcher(path).forward(request,response);
        String status=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"+1));
        if(status!=null){
            if("regist".equals(status)){
                path=this.regist(request);
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.doGet(request,response);
    }

    public String regist(HttpServletRequest request){
//        ValidateUtil.validateEmpty(request,"mid");
        String msg=null;//执行forward.jsp使用
        String url=null;//执行forward.jsp使用
        String mid=request.getParameter("mid");
        String password=request.getParameter("password");
        //必须验证通过，才可以进行后续的功能操作
        if(ValidateUtil.validateEmpty(mid)&&ValidateUtil.validateEmpty(password)){
            Member mb=new Member();//要将数据封装在Member对象中
            mb.setMid(mid);
            mb.setPassword(new MD5Code().getMD5ofStr(password));
            mb.setRegdate(new Date());
            mb.setPhoto("nophoto.jpg");
            mb.setCode(UUID.randomUUID().toString());//生成一个随记的Code码
            mb.setStatus(2);//现在属于待激活状态
            try {
                if(ServiceFrontDactory.getIMemberServiceFrontInstance().regist(mb)){
                msg="注册成功，请进行账户激活！";
                url="/index.jsp";
                System.out.println("【发出激活邮件】"+ BasePath.getBasePath(request)+"/pages/member_activity.jsp?mid="+mid+"&code"+mb.getCode());
                }else {
                    msg="用户注册失败，请重新填写新的注册信息！";
                    url="/pages/member_regist.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            msg="输入的用户信息不正确，请重新注册!";
            url="/pages/regist.jsp";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
}
