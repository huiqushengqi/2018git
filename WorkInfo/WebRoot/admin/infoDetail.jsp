<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看通告信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">奖惩管理&gt;&gt;查看奖惩</span>
</div>
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">查看通告</TD>
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
          <td style="text-align:center;font-weight:bold;font-size:14px">
          	<s:property value="#attr.info.info_title"/>
          </td>
        </tr> 
        <tr>
          <td style="text-align:center;color:#666">
          	发布人：<s:property value="#attr.info.info_admin"/>&nbsp;&nbsp;
          	发布时间：<s:property value="#attr.info.info_date.substring(0,10)"/>
          </td>
        </tr>
        <tr>
          <td style="padding:5px;line-height:22px">
          	<s:property value="#attr.info.info_content"/>
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
          	<input type="button" onclick="window.history.back();" Class="btnstyle" value="返 回"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</body>
</html>