<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
          prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    if (path.equals("/")) {
        path = "";
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">

    <title>系统异常统一处理</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
</head>
<body>
    <div class="col-sm-12">
        <h1>系统异常统一处理</h1>
        <h3>${errorMessage}</h3>
        <h2>快速查询Debug</h2>
        <a href="https://www.google.com/webhp?hl=zh-CN#safe=strict&hl=zh-CN&q=${errorMessage}"
           class="btn btn-primary btn-lg" target="_blank" id="Google">Google</a>
        <a href="https://www.baidu.com/s?wd=${errorMessage}" class="btn btn-info btn-lg" target="_blank" id="Baidu">Baidu</a>
        <a href="http://stackoverflow.com/search?q=${fn:substring(errorMessage,0,100)}"
           class="btn btn-default btn-lg"  target="_blank" id="StackOverFlow">StackOverFlow</a>

        <h2>异常堆栈跟踪日志StackTrace</h2>
        <code>
            <c:forEach items="${stackTrace}" var="line">
                ${line}
            </c:forEach>
        </code>
    </div>

    <!-- JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery-1.10.2.min.js" ></script>

    <script type="text/javascript">
        $(function () {
            $('#Google').click()
            $('#Baidu').click()
            $('#StackOverFlow').click()
        })
    </script>
</body>
</html>