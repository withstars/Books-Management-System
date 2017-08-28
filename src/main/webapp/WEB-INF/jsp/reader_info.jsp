<%--
  Created by IntelliJ IDEA.
  User: 君行天下
  Date: 2017/7/31
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${readerinfo.name}的主页</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"  crossorigin="anonymous">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="reader_main.html">我的图书馆</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li >
                    <a href="#" >
                        图书查询
                    </a>
                </li>
                <li>
                    <a href="reader_info.html" >
                        个人信息
                    </a>
                </li>
                <li >
                    <a href="#" >
                        我的借还
                    </a>
                </li>
                <li >
                    <a href="#" >
                        密码修改
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="reader_info.html"><span class="glyphicon glyphicon-user"></span>&nbsp;${readerinfo.name}，已登录</a></li>
                <li><a href="login.html"><span class="glyphicon glyphicon-log-in"></span>&nbsp;退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="col-xs-5 col-md-offset-3">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                我的信息
            </h3>
        </div>
        <div class="panel-body">
            <ul class="list-group">
                <li class="list-group-item"><span style="color: red">账号:</span>${readerinfo.readerId}</li>
                <li class="list-group-item"><span style="color: red">姓名:</span>${readerinfo.name}</li>
                <li class="list-group-item"><span style="color: red">性别:</span>${readerinfo.sex}</li>
                <li class="list-group-item"><span style="color: red">生日:</span>${readerinfo.birth}</li>
                <li class="list-group-item"><span style="color: red">地址:</span>${readerinfo.address}</li>
                <li class="list-group-item"><span style="color: red">电话:</span>${readerinfo.telcode}</li>
            </ul>
        </div>
        <a class="btn btn-success" href="reader_info_edit.html?readerId=${readerinfo.readerId}" role="button">修改</a>
    </div>
</div>


</body>
</html>
