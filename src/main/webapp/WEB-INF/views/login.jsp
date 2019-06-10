<%--
  Created by IntelliJ IDEA.
  User: Darin Li
  Date: 2019/4/25
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">

    <title>欢迎登录</title>

    <!--Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!--Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- HTML5 Shiv 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>

    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        /*web background*/
        .container {
            display: table;
            height: 100%;
        }

        .row {
            display: table-cell;
            vertical-align: middle;
            text-align: center;
        }
    </style>

</head>
<body style="background: #2aabd2;">

<div class="container">
    <div class="row">
        <div class="well col-lg-4 col-lg-offset-4">
            <h2 class="text-center">欢迎登录</h2>
            <form action="/clams/sysuser/login" method="post" role="form">
                <div class="input-group input-group-md">
                        <span class="input-group-addon" id="sizing-addon1">
                            <i class="glyphicon glyphicon-user" aria-hidden="true"></i>
                        </span>
                    <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户ID"/>
                </div>
                <div class="input-group input-group-md">
                        <span class="input-group-addon" id="sizing-addon2">
                            <i class="glyphicon glyphicon-lock"></i>
                        </span>
                    <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码"/>
                </div>

                <div id="login_err" class="alert alert-danger alert-dismissable" style="margin: 15px 0 0 0">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">
                        &times;
                    </button>
                    ${error_msg}
                </div>

                <br/>
                <button type="submit" class="btn btn-success btn-block">登录</button>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    var error_msg = "${error_msg}";
    if (error_msg == "" || error_msg == null) {
        $("#login_err").alert("close");
    } else {
        $("#login_err").alert();
    }
</script>
</body>
</html>

