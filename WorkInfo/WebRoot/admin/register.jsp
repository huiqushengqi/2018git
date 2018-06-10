<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<!-- Custom Theme files -->
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<!--Google Fonts-->
<link href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<!--Google Fonts-->
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
</head>
<body>
<div class="login">
	<h2>高校班主任工作信息管理系统</h2>
	<div class="login-top">
		<h1>登录</h1>
		<form id="info" method="post" action="LoginInSystem.action">
			<input type="text" id="params.user_name" name="params.user_name" value="${params.user_name}">
			<input type="password"  id="params.user_pass" name="params.user_pass">
	    </form>
	    <div class="forgot">
	    	<a href="Admin_inputUserName.action">忘记密码？</a>
	    	<input type="submit" value="Login" id="loginInBtn">
	    </div>
	</div>
	<div class="login-bottom">
		<h3>新用户 &nbsp;<a href="#">注册</a>&nbsp Here</h3>
	</div>
</div>	
<div class="copyright">
	<p>来自班主任工作信息管理系统</p>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var loginInBtn = $("#loginInBtn");
	var user_name = $("#params\\.user_name");
	var user_pass = $("#params\\.user_pass");
	var random = $("#params\\.random");
	var loginTip = $("#loginTip");
	
	loginInBtn.bind('click',function(){
		if(user_name.val()==''||user_pass.val()==''||random.val()==''){
			alert("用户名、密码和验证码不能为空！")
			return;
		}
		$("#info").submit();
		 
	});
});

</script>
</body>
</html>
