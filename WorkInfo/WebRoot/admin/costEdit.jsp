<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.cost!=null">编辑</s:if><s:else>添加</s:else>缴费信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
	 var num = /^\d+(\.\d+)?$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsCost\\.user_id").val()=='0'){
			alert('学生姓名不能为空');
			return;
		}
		if($("#paramsCost\\.cost_date").val()==''){
			alert('缴费日期不能为空');
			return;
		}
		if($("#paramsCost\\.cost_reason").val()==''){
			alert('缴费事由不能为空');
			return;
		}
		if(!num.exec($("#paramsCost\\.cost_money").val())){
			alert('缴费金额必须为数字');
			return;
		}
		$("#paramsCost\\.cost_id").val(0);
		$("#info").attr('action','Admin_addCost.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		 if($("#paramsCost\\.user_id").val()=='0'){
				alert('学生姓名不能为空');
				return;
			}
			if($("#paramsCost\\.cost_date").val()==''){
				alert('缴费日期不能为空');
				return;
			}
			if($("#paramsCost\\.cost_reason").val()==''){
				alert('缴费事由不能为空');
				return;
			}
			if(!num.exec($("#paramsCost\\.cost_money").val())){
				alert('缴费金额必须为数字');
				return;
			}
			$("#info").attr('action','Admin_saveCost.action').submit();
			 
	});
	 
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">缴费管理&gt;&gt;<s:if test="#attr.cost!=null">编辑</s:if><s:else>添加</s:else>缴费</span>
</div>
<form id="info" name="info" action="Admin_addCost.action" method="post">   
<s:hidden id="paramsCost.cost_id" name="paramsCost.cost_id" value="%{#attr.cost.cost_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.cost!=null">编辑</s:if><s:else>添加</s:else>缴费</TD>
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
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 学生姓名：</td>
          <td width="65%">
          	<s:select list="#attr.users" id="paramsCost.user_id" name="paramsCost.user_id" 
          		value="%{#attr.cost.user_id}"  cssStyle="width:155px;"
          		listKey="user_id" listValue="real_name" headerKey="0" headerValue="请选择">
          	</s:select>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 缴费日期：</td>
          <td>
          	 <s:textfield name="paramsCost.cost_date" id="paramsCost.cost_date" value="%{#attr.cost==null?'':#attr.cost.cost_date.substring(0,10)}" 
				onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
          </td>
        </tr>
       <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 缴费事由：</td>
          <td>
          	 <s:textfield name="paramsCost.cost_reason" id="paramsCost.cost_reason" value="%{#attr.cost.cost_reason}" />
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 缴费金额：</td>
          <td>
          	 <s:textfield name="paramsCost.cost_money" id="paramsCost.cost_money" value="%{#attr.cost.cost_money}" />
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
          	<s:if test="#attr.cost!=null">
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