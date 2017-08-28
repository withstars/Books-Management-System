<%--
  Created by IntelliJ IDEA.
  User: 君行天下
  Date: 2017/8/3
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更改密码</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"  crossorigin="anonymous">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
</head>
<body>
<nav  style="position:fixed;z-index: 999;width: 100%" class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="admin_main.html">图书管理系统</a>
        </div>
        <div class="collapse navbar-collapse" >
            <ul class="nav navbar-nav navbar-left">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        图书管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="allbooks.html">全部图书</a></li>
                        <li class="divider"></li>
                        <li><a href="book_add.html">增加图书</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        读者管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="allreaders.html">全部读者</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        借还管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">jmeter</a></li>
                        <li><a href="#">EJB</a></li>
                        <li><a href="#">Jasper Report</a></li>
                    </ul>
                </li>
                <li >
                    <a href="admin_repasswd.html" >
                        密码修改
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="login.html"><span class="glyphicon glyphicon-user"></span>&nbsp;${admin.adminId}，已登录</a></li>
                <li><a href="login.html"><span class="glyphicon glyphicon-log-in"></span>&nbsp;退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<div style="padding: 70px 550px 10px">
    <form   method="post" action="admin_repassword_do.html?adminId=${admin.adminId}" class="form-inline"  id="repasswd" style="position: fixed;top:20%;left: 30%">
        <div class="input-group">
            <input type="password" id="oldPasswd" placeholder="输入旧密码" class="form-control"  class="form-control">
            <input type="password" id="newPasswd" placeholder="输入新密码" class="form-control"  class="form-control">
            <input type="password" id="reNewPasswd" placeholder="再次输入新密码" class="form-control"  class="form-control">
            <em id="tishi" style="color: red"></em>
            <span>
                            <input type="submit" value="提交" class="btn btn-default">
            </span>

        </div>
    </form>
    <script>
        function mySubmit(flag){
            return flag;
        }

        $(document).keyup(function () {
            if($("#newPasswd").val()!=$("#reNewPasswd").val()&&$("#newPasswd").val()!=""&&$("#reNewPasswd").val()!=""&&$("#newPasswd").val().length==$("#reNewPasswd").val().length){
                $("#tishi").text("两次输入的新密码不同，请检查");
            }
            else {
                $("#tishi").text("");
            }
        })

        $("#repasswd").submit(function () {
            if($("#oldPasswd").val()==''||$("#newPasswd").val()==''||$("#reNewPasswd").val()==''){
                $("#tishi").text("请填写完毕后提交");
                return mySubmit(false);
            }
            else if($("#newPasswd").val()!=$("#reNewPasswd").val()){
                $("#tishi").text("两次输入的新密码不同，请检查");
                return mySubmit(false);
            }
        })
    </script>
</div>
</body>
</html>
