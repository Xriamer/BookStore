<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="headerDiv">
    <img src="images/logo.jpg" style="width: 60%">
    <a href="">书店首页</a>
    <a href="pages/front/books/BooksServletFront/list">图书列表</a>
    <a href="pages/front/cart/ShopcarServletFront/list">购物车</a>
    <c:if test="${mid==null}">
        <a href="pages/member_login.jsp">登录</a>
        <a href="pages/member_regist.jsp">注册</a>
    </c:if>
    <c:if test="${mid!=null}">
        <a href="pages/front/member/MemberInfoServletFront/updatePre">个人信息</a>
        <a href="pages/front/orders/OrdersServletFront/list">全部订单</a>
        <a href="pages/MemberServletFront/logout">安全退出</a>
        <img src="upload/member/${photo}" style="width: 50px;height: 50px;">
    </c:if>
</div>