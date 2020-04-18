<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String addCarUrl=basePath+"pages/front/shopcar/ShopcarServletFront/insert";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>网上书城</title>
    <link type="text/css" rel="stylesheet" href="css/base.css">
    <script type="text/javascript" src="js/base.js"></script>
    <script type="text/javascript" src="js/books.js"></script>
</head>
<body>
<div id="mainDiv">
    <c:if test="${books != null}">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" align="center" width="100%">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td width="35%" rowspan="11">
                    <div id="preview">
                        <img src="upload/books/${books.photo}">
                    </div>
                </td>
                <td width="15%">图书名称:</td>
                <td width="30%">${books.title}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>所属分类:</td>
                <td>${books.item.title}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>作者:</td>
                <td>${books.writer}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>出版社:</td>
                <td>${books.publisher}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>ISBN:</td>
                <td>${books.isbn}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>图书价格:</td>
                <td>${books.price}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>库存数量:</td>
                <td>${books.amount}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>浏览次数:</td>
                <td>${books.bow}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>上架日期:</td>
                <td>${books.pubdate}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3">${books.note}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3" align="center">
                    <a href="<%=addCarUrl%>?bid=${books.bid}">加入购物车</a>
                </td>
            </tr>
        </table>
    </c:if>
</div>
</body>
</html>
