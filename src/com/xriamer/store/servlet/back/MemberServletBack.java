package com.xriamer.store.servlet.back;

import com.xriamer.store.factory.ServiceBackFactory;
import com.xriamer.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@WebServlet(name = "MemberServletBack", urlPatterns = "/pages/back/admin/member/MemberServletBack/*")
public class MemberServletBack extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("list".equals(status)) {
                path = this.list(request);
            } else if ("listStatus".equals(status)) {
                path = this.listStatus(request);
            } else if ("updateStatus".equals(status)) {
                path = this.updateStatus(request);
            }else if("show".equalsIgnoreCase(status)){
                path=this.show(request);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    private String show(HttpServletRequest request) {
        String msg=null;
        String url=null;
        String referer=request.getHeader("referer");
        String mid=request.getParameter("mid");
        if(ValidateUtil.validateEmpty(mid)){
            try {
                request.setAttribute("member",ServiceBackFactory.getIMemberServiceBackInstance().show(mid));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/pages/back/admin/member/member_show.jsp";
        }else {
            msg="您还未选择任何数据，或者是还未选择任何数据，请重新选择！";
            url="/pages/back/admin/member/MemberServletBack"+referer.substring(referer.lastIndexOf("/"));
            request.setAttribute("msg",msg);
            request.setAttribute("url",url);
            return "/pages/forward.jsp";
        }
    }

    public String list(HttpServletRequest request) {
        int currentPage = 1;
        int lineSize = 10;
        String column = null;
        String keyWord = null;
        String columnData = "用户名:mid|真实姓名:name|联系电话:phone|地址:address";
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
            column = "mid";
        }
        if (keyWord == null) {
            keyWord = "";//表示查询全部
        }
        try {
            Map<String, Object> map = ServiceBackFactory.getIMemberServiceBackInstance().list(currentPage, lineSize, column, keyWord);
            request.setAttribute("allMembers", map.get("allMembers"));
            request.setAttribute("allRecorders", map.get("memberCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("lineSize", lineSize);
        request.setAttribute("column", column);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("columnData", columnData);
        request.setAttribute("url", "/pages/back/admin/member/MemberServletBack/list");
        return "/pages/back/admin/member/member_list.jsp";
    }

    public String updateStatus(HttpServletRequest request) {
//        Enumeration<String> enu=request.getHeaderNames();
//        while(enu.hasMoreElements()){
//            String name=enu.nextElement();
//            System.out.println("**** name= "+name+",value= "+request.getHeader(name));
//
//        }

        // allMember::name= referer,value= http://localhost:8080/pages/back/admin/member/MemberServletBack/list
        // confirm user::name= referer,value= http://localhost:8080/pages/back/admin/member/MemberServletBack/listStatus?status=1
        //取得之前的路径
        String referer = request.getHeader("referer");
        String type = request.getParameter("type");
        String msg = null;
        String url = null;
        String ids = request.getParameter("ids");
        if (ValidateUtil.validateEmpty(ids)) {
            String result [] = ids.split("\\|");
            Set<String> mid = new HashSet<String>();
            for (int x = 0; x < result.length; x++) {
                mid.add(result[x]);
            }
            try {
                boolean flag = false;
                if ("active".equalsIgnoreCase(type)) {
                    flag = ServiceBackFactory.getIMemberServiceBackInstance().updateActive(mid);
                }
                if ("lock".equalsIgnoreCase(type)) {
                    flag = ServiceBackFactory.getIMemberServiceBackInstance().updateLock(mid);
                }
                if (flag) {
                    msg = "用户更新状态成功！";
                } else {
                    msg = "用户更新状态失败！";
                }


//                boolean flag=false;
//                switch (type){
//                    case "active":{
//                        flag=ServiceBackFactory.getIMemberServiceBackInstance().updateActive(mid);
//                    }break;
//                    case "lock":{
//                        flag=ServiceBackFactory.getIMemberServiceBackInstance().updateLock(mid);
//                    }break;
//                }
//                if(flag){
//                    msg="用户状态更新成功";
//                }else{
//                    msg="用户状态更新失败";
//                }
                //---------------------------------------
//                if ("active".equalsIgnoreCase(type)) {
//                    if (ServiceBackFactory.getIMemberServiceBackInstance().updateActive(mid)) {
//                        msg="用户状态已经更新完成";
//                    }else{
//                        msg="用户状态已经更新失败";
//                    }
//                }
//                if("lock".equalsIgnoreCase(type)){
//                    if (ServiceBackFactory.getIMemberServiceBackInstance().updateActive(mid)) {
//                        msg="用户状态已经更新完成";
//                    }else{
//                        msg="用户状态已经更新失败";
//                    }
//                }
//                if (ServiceBackFactory.getIMemberServiceBackInstance().updateActive(mid)) {
//                    msg = "用户状态已经更新成功!";
//                } else {
//                    msg = "用户状态已经更新失败!";
//                }
                url = "/pages/back/admin/member/MemberServletBack" + referer.substring(referer.lastIndexOf("/"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "您还未选择更新数据，请重新操作!";
            url = "/pages/back/admin/member/MemberServletBack" + referer.substring(referer.lastIndexOf("/"));
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }


    public String listStatus(HttpServletRequest request) {
        int status = 0;
        int currentPage = 1;
        int lineSize = 10;
        String column = null;
        String keyWord = null;
        String columnData = "用户名:mid|真实姓名:name|联系电话:phone|地址:address";
        try {
            status = Integer.parseInt(request.getParameter("status"));
        } catch (Exception e) {
        }
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
            column = "mid";
        }
        if (keyWord == null) {
            keyWord = "";//表示查询全部
        }
        try {
            Map<String, Object> map = ServiceBackFactory.getIMemberServiceBackInstance().listByStatus(status, currentPage, lineSize, column, keyWord);
            request.setAttribute("allMembers", map.get("allMembers"));
            request.setAttribute("allRecorders", map.get("memberCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("lineSize", lineSize);
        request.setAttribute("column", column);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("columnData", columnData);
        request.setAttribute("url", "/pages/back/admin/member/MemberServletBack/list");
        request.setAttribute("paramName", "status");
        request.setAttribute("paramValue", status);
        return "/pages/back/admin/member/member_list.jsp";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
