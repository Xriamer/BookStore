<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String showUrl = basePath + "pages/front/books/BooksServletFront/show";
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
<jsp:include page="/pages/header.jsp"/>
<div id="mainDiv">
    <c:if test="${orders != null}">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" align="center" width="100%">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>订单编号</td>
                <td>${orders.oid}</td>
                <td>订单金额</td>
                <td>${orders.pay}</td>
                <td>下单时间</td>
                <td>${orders.credate}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>收件人</td>
                <td>${orders.name}</td>
                <td>联系电话</td>
                <td>${orders.phone}</td>
                <td>收件地址</td>
                <td>${orders.address}</td>
            </tr>
        </table>
        <hr>
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" align="center" width="100%">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>图书名称</td>
                <td>图书价格</td>
                <td>购买数量</td>
            </tr>
            <c:forEach items="${orders.allDetails}" var="details">
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><a href="<%=showUrl%>?bid=${details.books.bid}">${details.title}</a></td>
                    <td>${details.price}</td>
                    <td>${details.amount}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<jsp:include page="/pages/footer.jsp"/>
</body>
</html>
