package com.xriamer.store.filter;

import com.xriamer.store.factory.ServiceFrontFactory;
import com.xriamer.store.vo.Member;
import com.xriamer.utils.CookieUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "AutoLoginFilter", urlPatterns = {"/index.jsp", "/pages/front/*"})
public class AutoLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;  //取得session
        HttpSession session = request.getSession();
        if (session.getAttribute("mid") == null) {  //现在还没有登录，则应该取出全部的Cookie数据
            Map<String, String> map = CookieUtil.load(request);
            if (map.containsKey("mid") && map.containsKey("password")) {
                //如果存在有保存的mid与password两个Cookie数据，那么就可以进行业务验证
                Member mb = new Member();
                mb.setMid(map.get("mid"));
                mb.setPassword(map.get("password"));
                try {
                    if (ServiceFrontFactory.getIMemberServiceFrontInstance().login(mb)) {
                        session.setAttribute("mid", mb.getMid());
                        session.setAttribute("photo", mb.getPhoto());
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
