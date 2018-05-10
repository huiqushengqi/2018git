<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/2
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户登录模块</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <script src="${ctx}/assets/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript">
        $(function() {

            //简单登录操作
            $("#doLogin").click(function (e) {
                $.ajax({
                    type : "POST",
                    url : "login/login_module",
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
    <div>
        <label>用户名</label>
        <input type="text" name="username" value=""/>
    </div>
    <div>
        <label>密码</label>
        <input type="password" name="passwd" value=""/>
    </div>
    <div>
        <button type="button" id="doLogin">登录</button>
    </div>
</body>
</html>
