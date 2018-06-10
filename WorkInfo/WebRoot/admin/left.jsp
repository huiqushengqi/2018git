<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">  
<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<style type="text/css">
.left{width:190px; height:280px; background-color:#EEF2FB}
table tr td{ font-size:14px; font-family:Arial, Helvetica, sans-serif;}
body {
	font:14px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}
#container {
	width: 190px;
}
H1 {
	font-size: 14px;
	margin: 0px;
	width: 190px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 190px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(images/menu_bgS2.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 190px;
	height: 26px;
	
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 190px;
	padding-left: 0px;
}
.MM {
	width: 190px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 190px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 190px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 190px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(images/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 190px;
	text-decoration: none;
}
</style>
</head>

<body>
<table width="190" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
  	<td height="23" style="text-align:center; line-height:23px; color:white; background:url(images/left_title.gif) no-repeat left bottom;font-weight:bold">${admin.user_typeDesc }：${admin.real_name}</td>
  </tr>
  <tr>
    <td width="190" valign="top" style="background:#fff;">
    	<div class="left">
			 <table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
			  <tr>
				<td width="190" valign="top">
				<div id="container">
				  <h1 class="type"><a href="javascript:void(0)">个人中心</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="modifyInfo.jsp" target="MainFrame">个人信息</a></li>
					  <li><a href="modifyPwd.jsp" target="MainFrame">修改密码</a></li>
					</ul>
				  </div>
				  <!-- 教师入口-->	
				  <c:if test="${admin.user_type==2}">
				  <h1 class="type"><a href="javascript:void(0)">班级管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listClazzs.action" target="MainFrame">班级查询</a></li>
					  <li><a href="Admin_addClazzShow.action" target="MainFrame">新增班级</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">教师管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listTeachers.action" target="MainFrame">教师查询</a></li>
					  <li><a href="Admin_addTeacherShow.action" target="MainFrame">新增教师</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">课程管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listCourses.action" target="MainFrame">课程查询</a></li>
					  <li><a href="Admin_addCourseShow.action" target="MainFrame">新增课程</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">学生管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listUsers.action" target="MainFrame">学生查询</a></li>
					  <li><a href="Admin_addUserShow.action" target="MainFrame">新增学生</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">成绩管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listScores.action" target="MainFrame">成绩查询</a></li>
					  <li><a href="Admin_addScoreShow.action" target="MainFrame">新增成绩</a></li>
					  <li><a href="importScoreP.jsp" target="MainFrame">导入成绩</a></li>
					  <li><a href="Admin_listScoresSum.action" target="MainFrame">总分查询</a></li>
					  <li><a href="Admin_listSingleScoresSectionShow.action" target="MainFrame">单科成绩分布</a></li>
					  <li><a href="Admin_listSumScoresSectionShow.action" target="MainFrame">学生总分分布</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">公告管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listInfos.action" target="MainFrame">公告查询</a></li>
					  <li><a href="Admin_addInfoShow.action" target="MainFrame">新增公告</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">费用管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listCosts.action" target="MainFrame">费用查询</a></li>
					  <li><a href="Admin_addCostShow.action" target="MainFrame">新增费用</a></li>
					</ul>
				  </div>
				  </c:if>
				  <!-- 管理员入口-->				  
				  <c:if test="${admin.user_type==3}">
				  <h1 class="type"><a href="javascript:void(0)">教师管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listTeachers.action" target="MainFrame">教师查询</a></li>
					  <li><a href="Admin_addTeacherShow.action" target="MainFrame">新增教师</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">班级管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listClazzs.action" target="MainFrame">班级查询</a></li>
					  <li><a href="Admin_addClazzShow.action" target="MainFrame">新增班级</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">课程管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listCourses.action" target="MainFrame">课程查询</a></li>
					  <li><a href="Admin_addCourseShow.action" target="MainFrame">新增课程</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">成绩管理</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listScores.action" target="MainFrame">成绩查询</a></li>
					  <li><a href="Admin_addScoreShow.action" target="MainFrame">新增成绩</a></li>
					  <li><a href="importScoreP.jsp" target="MainFrame">导入成绩</a></li>
					  <li><a href="Admin_listScoresSum.action" target="MainFrame">总分查询</a></li>
					  <li><a href="Admin_listSingleScoresSectionShow.action" target="MainFrame">单科成绩分布</a></li>
					  <li><a href="Admin_listSumScoresSectionShow.action" target="MainFrame">学生总分分布</a></li>
					</ul>
				  </div>
				  </c:if>
				   <!-- 学生入口-->
				  <c:if test="${admin.user_type==1}">
				  <h1 class="type"><a href="javascript:void(0)">课程查询</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listScources.action" target="MainFrame">已选课程查询</a></li>
					<!--  <li><a href="Admin_addScourceShow.action" target="MainFrame">新增选修课程</a></li> --> 
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">成绩查询</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listScores.action" target="MainFrame">成绩查询</a></li>
					  <li><a href="Admin_listScoresSum.action" target="MainFrame">总分成绩</a></li>
					</ul>
				  </div>
				<!--  <h1 class="type"><a href="javascript:void(0)">课程查询</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listCourses.action" target="MainFrame">课程查询</a></li>
					</ul>
				  </div>
				  -->
				  <h1 class="type"><a href="javascript:void(0)">公告查询</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listInfos.action" target="MainFrame">公告详细</a></li>
					</ul>
				  </div>
				  <h1 class="type"><a href="javascript:void(0)">费用查询</a></h1>
				  <div class="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/menu_topline.gif" width="190" height="5" /></td>
					  </tr>
					</table>
					<ul class="MM">
					  <li><a href="Admin_listCosts.action" target="MainFrame">费用详细</a></li>
					</ul>
				  </div>
				  </c:if>
				   
					<script type="text/javascript">
						var contents = document.getElementsByClassName('content');
						var toggles = document.getElementsByClassName('type');
						var myAccordion = new fx.Accordion(
							toggles, contents, {opacity: true, duration: 400}
						);
						myAccordion.showThisHideOpen(contents[0]);
					</script>
				</div>
				</td>
			  </tr>
			</table>       	
        </div>
    </td>
  </tr>
</table>
</body>
</html>
