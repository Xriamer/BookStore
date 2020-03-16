<%--
  Created by IntelliJ IDEA.
  User: Xriam
  Date: 3/16/2020
  Time: 7:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.xriamer.cn/c"%>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"
            + request.getServerName()+":"
            + request.getServerName()+":"+
            + request.getServerPort()
            + path ;
%>
<html>
<head>
    <base href="<%=basePath%>>">
    <title>网上书城</title>
    <link type="text/css" rel="stylesheet" href="css/base.css">
    <script type="text/javascript" src="js/base.js"></script>
</head>
<body>
<script type="text/javascript">
    window.alert("${msg}");
    window.location="<%=basePath%>${url}";
</script>
</body>
</html>
