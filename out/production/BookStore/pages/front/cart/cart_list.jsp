<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String deleteUrl = basePath + "/pages/front/cart/ShopcarServletFront/delete";
    String updateUrl = basePath + "/pages/front/cart/ShopcarServletFront/update";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>网上书城</title>
    <link type="text/css" rel="stylesheet" href="css/base.css">
    <script type="text/javascript" src="js/base.js"></script>
</head>
<body>
<jsp:include page="/pages/header.jsp"/>
<div id="mainDiv">
    <h1 align="center">购物车</h1>
    <c:if test="${allBooks != null}" var="res">
        <form action="<%=updateUrl%>" method="post">
            <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><input type="checkbox" onclick="checkboxSelect(this,'bid')"></td>
                    <td>图片</td>
                    <td>书名</td>
                    <td>价格</td>
                    <td>数量</td>
                    <td>总价</td>
                </tr>
                <c:forEach items="${allBooks}" var="books">
                    <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                        <td><input type="checkbox" id="bid" name="bid" value="${books.bid}:${books.photo}"></td>
                        <td><img src="upload/books/${books.photo}" style="width:50px;height:50px;"></td>
                        <td>${books.title}</td>
                        <td>${books.price}</td>
                        <td>
                            <input type="button" value="-">
                            <input type="text" value="${allCars[books.bid]}" size="5" name="${books.bid}"
                                   id="${books.bid}">
                            <input type="button" value="+">
                        </td>
                        <td>${books.bow}</td>
                    </tr>
                </c:forEach>
            </table>
            <input type="button" value="彻底删除" onclick="deleteAll('<%=deleteUrl%>','ids','bid')">
            <input type="submit" value="更新修改图书数量">
        </form>
    </c:if>
</div>
<jsp:include page="/pages/footer.jsp"/>
</body>
</html>
