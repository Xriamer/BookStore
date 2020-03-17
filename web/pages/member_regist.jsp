<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String registUrl=basePath+"/pages/MemberServletFront/regist";
%>
<html>
<head>
    <base href="<%=basePath%>>">
    <title>网上书城</title>
    <link type="text/css" rel="stylesheet" href="css/base.css">
    <script type="text/javascript" src="js/base.js"></script>
</head>
<body>
<jsp:include page="/pages/header.jsp"/>
<div id="mainDiv">
    <form action="<%=registUrl%>" method="post" onsubmit="return validateRegist()">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3">用户注册</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>用户ID: </td>
                <td><input type="text" name="mid" id="mid" class="init" onblur="validateMid()"></td>
                <td><span id="midMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>密&nbsp;&nbsp;码 </td>
                <td><input type="text" name="password" id="password" class="init" onblur="validatePassword()"></td>
                <td><span id="passwordMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3">
                    <input type="submit" value="注册">
                    <input type="reset" value="重置">
                </td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3">用户注册</td>
            </tr>
        </table>
        </form>
</div>
<jsp:include page="/pages/footer.jsp"/>
</body>
</html>
