<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.user!=null && #attr.user.user_id!=0">编辑</s:if><s:else>添加</s:else>学生信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var user_sex = "<s:property value='#attr.user.user_sex' />";
	 if(user_sex!=''){
		 $("#sex"+user_sex).attr('checked','checked');
	 }else{
		 $("#sex1").attr('checked','checked');
	 }
	 
	 var num = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsUser\\.user_name").val()==''){
			alert('学号不能为空');
			return;
		}
		if($("#paramsUser\\.user_pass").val()==''){
			alert('密码不能为空');
			return;
		}
		if($("#paramsUser\\.real_name").val()==''){
			alert('姓名不能为空');
			return;
		}
		if($("#paramsUser\\.user_age").val()!=''){
			if(!num.exec($("#paramsUser\\.user_age").val())){
				alert('年龄必须为数字');
				return;
			}
		}else{
			$("#paramsUser\\.user_age").val(0);
		}
		if($("#paramsUser\\.clazz_id").val()=='0'){
			alert('班级不能为空');
			return;
		}
		if($("#paramsUser\\.user_question").val()==''){
			alert('密保问题不能为空');
			return;
		}
		if($("#paramsUser\\.user_answer").val()==''){
			alert('密保答案不能为空');
			return;
		}
		$("#paramsUser\\.user_id").val(0);
		$("#info").attr('action','Admin_addUser.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		 	if($("#paramsUser\\.real_name").val()==''){
				alert('姓名不能为空');
				return;
			}
		 	if($("#paramsUser\\.user_age").val()!=''){
		 		if(!num.exec($("#paramsUser\\.user_age").val())){
					alert('年龄必须为数字');
					return;
				}
			}else{
				$("#paramsUser\\.user_age").val(0);
			}
			if($("#paramsUser\\.clazz_id").val()=='0'){
				alert('班级不能为空');
				return;
			}
			if($("#paramsUser\\.user_question").val()==''){
				alert('密保问题不能为空');
				return;
			}
			if($("#paramsUser\\.user_answer").val()==''){
				alert('密保答案不能为空');
				return;
			}
			$("#info").attr('action','Admin_saveUser.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">学生管理&gt;&gt;<s:if test="#attr.user!=null && #attr.user.user_id!=0">编辑</s:if><s:else>添加</s:else>学生</span>
</div>
<form id="info" name="info" action="Admin_addUser.action" method="post">   
<s:hidden id="paramsUser.user_id" name="paramsUser.user_id" value="%{#attr.user.user_id}" /> 
<input type="hidden" name="paramsUser.user_photo" id="paramsUser.user_photo" value='${admin.user_photo}'/>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.user!=null && #attr.user.user_id!=0">编辑</s:if><s:else>添加</s:else>学生</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       <tr>
          <td width="12%" align="right" style="padding-right:5px"><font color="red">*</font> 学号：</td>
          <td width="38%" >
          	<s:if test="#attr.user!=null && #attr.user.user_id!=0"><s:property value="#attr.user.user_name" /></s:if>
          	<s:else><s:textfield name="paramsUser.user_name" id="paramsUser.user_name" value="%{#attr.user.user_name}"/> </s:else>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 密码：</td>
          <td>
          	<s:if test="#attr.user!=null && #attr.user.user_id!=0">
          	<s:password name="paramsUser.user_pass" id="paramsUser.user_pass" value=""  showPassword="true"/>
          	</s:if>
          	<s:else>
          	<s:password name="paramsUser.user_pass" id="paramsUser.user_pass" value="111111"  showPassword="true"/>
          	<span id="passTip" style="color:red;">初始密码：111111</span>
          	</s:else>
          </td>
        </tr>
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 密保问题：</td>
          <td>
            <s:textfield id="paramsUser.user_question" name="paramsUser.user_question" value="%{#attr.user.user_question}"/>
          </td> 
          <td align="right" style="padding-right:5px"><font color="red">*</font> 密保答案：</td>
          <td>
            <s:textfield id="paramsUser.user_answer" name="paramsUser.user_answer" value="%{#attr.user.user_answer}"/>
          </td> 
        </tr>  
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          <input type="button" onclick="window.history.back();" Class="btnstyle" value="返 回"/>&nbsp;
          	<s:if test="#attr.user!=null && #attr.user.user_id!=0">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>