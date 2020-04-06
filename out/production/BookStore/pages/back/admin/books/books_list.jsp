<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String updateUpUrl = basePath + "pages/back/admin/books/BooksServletBack/updateStatus?type=up";
    String updateDownUrl = basePath + "pages/back/admin/books/BooksServletBack/updateStatus?type=down";
    String updateDeleteUrl = basePath + "pages/back/admin/books/BooksServletBack/updateStatus?type=delete";
    String updatePreUrl = basePath + "pages/back/admin/books/BooksServletBack/updatePre";
    String deleteUrl = basePath + "pages/back/admin/books/BooksServletBack/delete?p=p";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>在线书城</title>
    <link type="text/css" rel="stylesheet" href="css/base.css">
    <script type="text/javascript" src="js/base.js"></script>
</head>
<body>
<div id="mainDiv">
    <h1>图书列表</h1>
    <c:if test="${allBooks != null}" var="res">
        <div id="splitSearchDiv">
            <jsp:include page="/pages/split_page_plugin_search.jsp"/>
            <br>
        </div>
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td><input type="checkbox" onclick="checkboxSelect(this,'bid')"></td>
                <td>图片</td>
                <td>书名</td>
                <td>作者</td>
                <td>出版社</td>
                <td>ISBN版号</td>
                <td>发布日期</td>
                <td>库存</td>
                <td>访问量</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${allBooks}" var="books">
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><input type="checkbox" id="bid" name="bid" value="${books.bid}:${books.photo}"></td>
                    <td><img src="upload/books/${books.photo}" style="width:50px;height:50px;"></td>
                    <td>${books.title}</td>
                    <td>${books.writer}</td>
                    <td>${books.publisher}</td>
                    <td>${books.isbn}</td>
                    <td>${books.pubdate}</td>
                    <td>${books.amount}</td>
                    <td>${books.bow}</td>
                    <td>
                        <c:if test="${books.status == 0}">
                            下架
                        </c:if>
                        <c:if test="${books.status == 1}">
                            上架
                        </c:if>
                        <c:if test="${books.status == 2}">
                            回收站
                        </c:if>
                    </td>
                    <td>
                        <a href="<%=updatePreUrl%>?bid=${books.bid}">修改</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${param.status!=1}">
            <input type="button" value="图书上架" onclick="deleteAll('<%=updateUpUrl%>','ids','bid')">
        </c:if>
        <c:if test="${param.status!=0}">
            <input type="button" value="图书下架" onclick="deleteAll('<%=updateDownUrl%>','ids','bid')">
        </c:if>
        <c:if test="${param.status!=2}">
            <input type="button" value="移到回收站" onclick="deleteAll('<%=updateDeleteUrl%>','ids','bid')">
        </c:if>
        <c:if test="${param.status==2}">
            <input type="button" value="彻底删除" onclick="deleteAll('<%=deleteUrl%>','ids','bid')">
        </c:if>
        <div id="splitBarDiv" style="float:right">
            <jsp:include page="/pages/split_page_plugin_bars.jsp"/>
        </div>
    </c:if>
</div>
</body>
</html>
