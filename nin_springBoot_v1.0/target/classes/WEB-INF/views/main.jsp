<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

    <title>Spring Boot管理后台</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css"  />
    <link rel="stylesheet" href="${ctx}/assets/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/jquery-ui-1.10.3.full.min.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/datepicker.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/googleFamily.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/ui.jqgrid.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/chosen.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/custom.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/ace.min.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.min.css" />
    <link rel="stylesheet" href="${ctx}/assets/css/ace-skins.min.css" />

    <!--[if !IE]> -->
    <script src="${ctx}/assets/js/jquery-2.0.3.min.js" ></script>
    <!-- <![endif]-->
    <!--[if IE]>
    <script src="${ctx}/assets/js/jquery-1.10.2.min.js" ></script>
    <![endif]-->
    <title>Spring Boot管理后台</title>
    <script type="text/javascript">
        $(function(){
            $('#profile-feed-1').slimScroll({
                height: '250px',
                alwaysVisible : true
            });

            //单击快捷方式
            $("#fastMenuDiv").on("click","a.menu",function(){
                var url=$(this).attr("id");
                var id=$(this).attr("value");
                /*<![CDATA[*/
                if(id && url){
                    parent.addTabs({id:id, title: $(this).find(".spName").text(), url: url, close: true});
                }
                /*]]>*/
                //parent.changeUrl(url);
                //$("#showNav",window.parent.document).thymeleaf(menuName);
            })
        });
    </script>
</head>
<body>
<div class="row">
    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-header widget-header-small">
                <h4><i class="fa fa-user orange"></i>个人信息</h4>
            </div>
            <div class="widget-body">
                <div class="widget-body-inner" style="display: block;">
                    <div class="widget-main">
                        <div>
                            <div class="row">
                                <div class="col-xs-1">
                                    <img class="editable img-responsive editable-click editable-empty" style="border:0;" width="120" height="120" id="avatar" alt="Alex's Avatar" th:src="@{/assets/images/dudu.jpg}" />
                                </div>
                                <div class="col-xs-7">
                                    <h3>您好，欢迎登录Spring Boot管理系统。</h3>
                                    <!--<div class="row">
                                        <div class="col-xs-3 col-md-2">
                                            <h4>博主：<span id="relName">嘟嘟MD</span></h4>
                                        </div>
                                        <div class="col-xs-4 col-md-10">
                                            <h4>当前框架：基于bootstrap3的ACE模板改造</h4>
                                            <h4>SpringBoot干货群（427480430），大把学习电子书等着你</h4>
                                            <h4>扫扫博主公众号，获取博主最新博客通知</h4>
                                        </div>
                                    </div>-->
                                </div>
                                <!--<div class="col-xs-2" style="text-align: right;">
                                    <img th:src="@{/assets/images/qq.png}"   width="150" height="150"/>
                                </div>
                                <div class="col-xs-2">
                                    <img th:src="@{/assets/images/gongzhon.png}"  width="220"/>
                                </div>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-header  widget-header-small">
                <h4><i class="icon-star orange"></i>快捷方式</h4>
            </div>
            <div class="widget-body">
                <div class="widget-body-inner" style="display: block;">
                    <div class="widget-main">
                        <div>
                            <div id="fastMenuDiv" class="profile-users clearfix">
                                <div class="itemdiv memberdiv">
                                    <div class="inline position-relative">
                                        <div class="user">
                                            <a class="btn btn-app btn-purple menu"  id="learn" value="learn" style="font-size:14px;line-height:2.2;">
                                                <i class="icon-desktop bigger-230"></i>
                                                <span class="spName">SpringBoot教程</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>

                                <div class="itemdiv memberdiv">
                                    <div class="inline position-relative">
                                        <div class="user">
                                            <a class="btn btn-app btn-yellow  btn-sm" id="addFastMenuBtn" href="javascript:void(0);" style="font-size:14px;">
                                                <i class="fa fa-plus fa-5x"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-header widget-header-small">
                <h4>
                    <i class="icon-rss orange"></i>
                    通知公告
                </h4>
                <div class="widget-toolbar action-buttons">
                    <a href="#" data-action="reload">
                        <i class="icon-refresh blue"></i>
                    </a>
                </div>
            </div>
            <div class="widget-body">
                <div class="widget-main padding-8">
                    <div class="profile-feed" id="profile-feed-1" style="width: auto; height: 250px; overflow: hidden;">

                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="Alex Doe's avatar" th:src="@{/assets/avatars/avatar5.png}" />
                                <a class="user" href="#"> Alex Doe </a>
                                changed his profile photo.
                                <div class="time">
                                    <i class="icon-time bigger-110"></i>
                                    an hour ago
                                </div>
                            </div>
                        </div>
                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="Susan Smith's avatar" th:src="@{/assets/avatars/avatar1.png}" />
                                <a class="user" href="#"> Susan Smith </a>
                                is now friends with Alex Doe.
                                <div class="time">
                                    <i class="icon-time bigger-110"></i>
                                    2 hours ago
                                </div>
                            </div>
                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <i class="pull-left thumbicon icon-ok btn-success no-hover"></i>
                                <a class="user" href="#"> Alex Doe </a>
                                joined
                                <div class="time">
                                    <i class="icon-time bigger-110"></i>
                                    5 hours ago
                                </div>
                            </div>

                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <i class="pull-left thumbicon icon-picture btn-info no-hover"></i>
                                <a class="user" href="#"> Alex Doe </a>
                                uploaded a new photo.
                                <a href="#">Take a look</a>

                                <div class="time">
                                    <i class="icon-time bigger-110"></i>
                                    5 hours ago
                                </div>
                            </div>
                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="David Palms's avatar" th:src="@{/assets/avatars/avatar4.png}" />
                                <a class="user" href="#"> David Palms </a>

                                left a comment on Alex's wall.
                                <div class="time">
                                    <i class="icon-time bigger-110"></i>
                                    8 hours ago
                                </div>
                            </div>
                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <i class="pull-left thumbicon icon-edit btn-pink no-hover"></i>
                                <a class="user" href="#"> Alex Doe </a>
                                published a new blog post.
                                <a href="#">Read now</a>

                                <div class="time">
                                    <i class="icon-time bigger-110"></i>
                                    11 hours ago
                                </div>
                            </div>
                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <img class="pull-left" alt="Alex Doe's avatar" th:src="@{/assets/avatars/avatar5.png}" />
                                <a class="user" href="#"> Alex Doe </a>

                                upgraded his skills.
                                <div class="time">
                                    <i class="icon-time bigger-110"></i>
                                    12 hours ago
                                </div>
                            </div>

                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <i class="pull-left thumbicon icon-key btn-info no-hover"></i>
                                <a class="user" href="#"> Alex Doe </a>

                                logged in.
                                <div class="time">
                                    <i class="icon-time bigger-110"></i>
                                    12 hours ago
                                </div>
                            </div>
                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <i class="pull-left thumbicon icon-off btn-inverse no-hover"></i>
                                <a class="user" href="#"> Alex Doe </a>
                                logged out.
                                <div class="time">
                                    <i class="icon-time bigger-110"></i>
                                    16 hours ago
                                </div>
                            </div>
                        </div>

                        <div class="profile-activity clearfix">
                            <div>
                                <i class="pull-left thumbicon icon-key btn-info no-hover"></i>
                                <a class="user" href="#"> Alex Doe </a>

                                logged in.
                                <div class="time">
                                    <i class="icon-time bigger-110"></i>
                                    16 hours ago
                                </div>
                            </div>
                        </div>


                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div id="addFastMenuModal" class="bootbox modal fade " tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">配置快捷方式</h4>
            </div>
            <div class="modal-body" id="tableS"  style="padding:3px">
                <table class="table table-striped table-bordered table-hover">
                    <thead class="thin-border-bottom">
                    <tr>
                        <th style="width:80px;"><input type="checkbox" />&nbsp;&nbsp;全选</th>
                        <th>菜单</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input type="checkbox" /></td>
                        <td><a class="fastMenuClass" href="javascript:void(0);" id="1" value="custom/entCustom">企业信息管理</a></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" /></td>
                        <td><a class="fastMenuClass" href="javascript:void(0);" id="1" value="custom/entCustom">企业信息管理</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" id="saveFastMenu" class="btn btn-success">
                    <span><i class="icon-ok"></i></span> &nbsp;保&nbsp;&nbsp;存
                </button>
                <button type="button" id="cancerFastMenu" class="cancel btn btn-danger cancel">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/assets/js/ace-extra.min.js" ></script>
<!--[if lt IE 9]>

<script src="${ctx}/assets/js/html5shiv.js" ></script>
<script src="${ctx}/assets/js/respond.min.js" ></script>
<![endif]-->
<script src="${ctx}/assets/js/bootstrap.min.js" ></script>
<script src="${ctx}/assets/js/jquery-ui-1.10.3.custom.min.js" ></script>
<script src="${ctx}/assets/js/jquery.slimscroll.min.js" ></script>
<script src="${ctx}/assets/js/chosen.jquery.min.js" ></script>
<script src="${ctx}/assets/js/ace-elements.min.js" ></script>
<script src="${ctx}/assets/js/ace.min.js" ></script>
<script src="${ctx}/assets/js/md5.js" ></script>
<script src="${ctx}/assets/js/custom.js" ></script>
<script src="${ctx}/assets/js/jquery.messager.js" ></script>
</body>
</html>