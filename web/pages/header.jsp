<%@ page pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="headerDiv">
    <img src="images/logo.jpg" style="width: 75%">
    <a href="">书店首页</a>
    <a href="">购物车</a>
    <c:if test="${mid==null}">
    <a href="pages/member_login.jsp">登录</a>
    <a href="pages/member_regist.jsp">注册</a>
    </c:if>
    <c if test="${mid!=null}">
        <a href="">个人中心</a>
        <img src="upload/member/${photo}" style="width: 50px;height: 50px;">
    </c>
</div>