<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"
           + request.getServerName()+":"
           + request.getServerName()+":"+
           + request.getServerPort()
           + path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>>">
    <title>网上书城</title>
    <link type="text/css" rel="stylesheet" href="css/base.css">
    <script type="text/javascript" src="js/base.js"></script>
</head>
<body></body>
</html>
