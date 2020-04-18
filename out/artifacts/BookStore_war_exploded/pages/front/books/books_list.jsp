<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String showUrl = basePath + "pages/front/books/BooksServletFront/show";
    String listUrl = basePath + "pages/front/books/BooksServletFront/list";
    String addCarUrl = basePath + "pages/front/cart/ShopcarServletFront/insert";

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
    <h1>图书列表</h1>
    <select onchange="goList('<%=listUrl%>',this.value)">
        <option value="0">查看全部图书</option>
        <c:forEach items="${allItems}" var="item">
            <option value="${item.iid}" ${item.iid==paramValue ? "selected":""}>${item.title}</option>
        </c:forEach>
    </select>
    <c:if test="${allBooks != null}" var="res">
        <div id="splitSearchDiv">
            <jsp:include page="/pages/split_page_plugin_search.jsp"/>
            <br>
        </div>
        <c:forEach items="${allBooks}" var="books">
            <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td rowspan="5">
                        <a href="<%=showUrl%>?bid=${books.bid}">
                            <img src="upload/books/${books.photo}" style="width:70px;height:70px;">
                        </a>
                    </td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><strong>图书名称:</strong></td>
                    <td><a href="<%=showUrl%>?bid=${books.bid}">${books.title}</a></td>
                    <td><strong>作者:</strong></td>
                    <td>${books.writer}</td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><strong>出版社:</strong></td>
                    <td>${books.publisher}</td>
                    <td><strong>浏览次数:</strong></td>
                    <td>${books.bow}</td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><strong>图书价格:</strong></td>
                    <td>${books.price}</td>
                    <td><strong>上架日期:</strong></td>
                    <td>${books.pubdate}</td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td colspan="3">${books.note}</td>
                    <td><a href="<%=addCarUrl%>?bid=${books.bid}">加入购物车</a></td>
                </tr>
            </table>
        </c:forEach>
    </c:if>
    <div id="splitBarDiv" style="float:right">
        <jsp:include page="/pages/split_page_plugin_bars.jsp"/>
    </div>
</div>
<jsp:include page="/pages/footer.jsp"/>
</body>
</html>
