<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--[if lt IE 7 ]><thymeleaf lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]><thymeleaf lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]><thymeleaf lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]><thymeleaf lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="en" class="no-js" > <!--<![endif]-->
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="description" content="此代码内容为超萌的猫头鹰登录界面" />
    <title>超萌的猫头鹰登录界面</title>

    <c:set var="ctx" value="${pageContext.request.contextPath}" />

    <link rel="stylesheet" href="${ctx}/assets/css/jq22.css"   />
    <link rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css" />
    <script src="${ctx}/assets/js/jquery-1.10.2.min.js" ></script>
    <script src="${ctx}/assets/js/bootstrap.min.js" ></script>
    <script src="${ctx}/assets/js/jquery.messager.js" ></script>
    <script type="text/javascript">
        $(function() {
            $('#login #password').focus(function() {
                $('#owl-login').addClass('password');
            }).blur(function() {
                $('#owl-login').removeClass('password');
            });

            //简单登录操作
            $("#doLogin").click(function (e) {
                $.ajax({
                    type : "POST",
                    url : "login",
                    data : {
                        "userName" : $("#userName").val(),
                        "password" : $("#password").val()
                    },
                    dataType : "json",
                    success : function(data) {
                        if (data.result == "1") {
                            window.location.href ="index/toIndex";
                        } else {
                            $.messager.alert("提示",'账号密码不能为空！');
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<!-- begin -->
<div id="login">
    <div class="wrapper">
        <div class="login">
            <form action="#" method="post" class="container offset1 loginform">
                <div id="owl-login">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>
                <div class="pad">
                    <input type="hidden" name="_csrf" value="9IAtUxV2CatyxHiK2LxzOsT6wtBE6h8BpzOmk="/>
                    <div class="control-group">
                        <div class="controls">
                            <label for="userName" class="control-label"><i class="fa fa-user" aria-hidden="true"></i></label>
                            <input id="userName" type="text" name="email" placeholder="账号" tabindex="1" autofocus="autofocus" class="form-control input-medium" />
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label for="password" class="control-label"><i class="fa fa-key" aria-hidden="true"></i></label>
                            <input id="password" type="password" name="password" placeholder="密码" tabindex="2" class="form-control input-medium" />
                        </div>
                    </div>
                </div>
                <div class="form-actions"><a href="#" tabindex="5" class="btn pull-left btn-link text-muted">Forgot password?</a><a href="#" tabindex="6" class="btn btn-link text-muted">Sign Up</a>
                    <button type="button" id="doLogin" tabindex="4" class="btn btn-primary">Login</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- end -->
</body>
</html>