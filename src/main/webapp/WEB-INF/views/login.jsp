<%--
  Created by IntelliJ IDEA.
  User: Darin Li
  Date: 2019/4/25
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>
        .wp {
            line-height: 300px;
            text-align: center;
            font-size: 0px;
        }

        .box {
            font-size: 16px;
            display: inline-block;
            vertical-align: middle;
            line-height: initial;
            text-align: left; /* 修正文字 */
        }
    </style>
</head>
<body>
<div class="wp">
    <div class="box">
        <form action="/clams/sysuser/login" method="post">
            <div> 用户名:<input name="username" type="text"/></div>
            <div> 密　码:<input name="password" type="text"/></div>
            <div><input type="submit"/></div>
        </form>
    </div>
</div>


</body>
</html>
