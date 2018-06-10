<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.info!=null">编辑</s:if><s:else>添加</s:else>通告信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
	 $("#addBtn").bind('click',function(){
		if($("#paramsInfo\\.info_title").val()==''){
			alert('通告标题不能为空');
			return;
		}
		if($("#paramsInfo\\.info_content").val()==''){
			alert('通告内容不能为空');
			return;
		}
		if($("#paramsInfo\\.info_admin").val()==''){
			alert('发布人不能为空');
			return;
		}
		$("#paramsInfo\\.info_id").val(0);
		$("#info").attr('action','Admin_addInfo.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		 if($("#paramsInfo\\.info_title").val()==''){
				alert('通告标题不能为空');
				return;
			}
			if($("#paramsInfo\\.info_content").val()==''){
				alert('通告内容不能为空');
				return;
			}
			if($("#paramsInfo\\.info_admin").val()==''){
				alert('发布人不能为空');
				return;
			}
			$("#info").attr('action','Admin_saveInfo.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">奖惩管理&gt;&gt;<s:if test="#attr.info!=null">编辑</s:if><s:else>添加</s:else>奖惩</span>
</div>
<form id="info" name="info" action="Admin_addInfo.action" method="post">   
<s:hidden id="paramsInfo.info_id" name="paramsInfo.info_id" value="%{#attr.info.info_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.info!=null">编辑</s:if><s:else>添加</s:else>奖惩</TD>
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
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 通告标题：</td>
          <td width="65%">
          	<s:textfield name="paramsInfo.info_title" id="paramsInfo.info_title" value="%{#attr.info.info_title}"/>
          </td>
        </tr> 
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 通告内容：</td>
          <td width="65%">
          	<s:textarea name="paramsInfo.info_content" id="paramsInfo.info_content" value="%{#attr.info.info_content}" cssStyle="width:300px;height:60px;">
          	</s:textarea>
          </td>
        </tr> 
         <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 发布人：</td>
          <td width="65%">
          	<s:textfield name="paramsInfo.info_admin" id="paramsInfo.info_admin" value="%{#attr.info.info_admin}"/>
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
          	<s:if test="#attr.info!=null">
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