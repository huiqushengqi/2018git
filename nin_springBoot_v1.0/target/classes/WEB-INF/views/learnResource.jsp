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
    <link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css" />
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

    <script src="${ctx}/assets/js/jquery-2.0.3.min.js" ></script>

</head>
<body>
<div class="row">
    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-header widget-header-small">
                <h4>Spring Boot</h4>
            </div>
            <div class="widget-body">
                <div class="widget-body-inner" style="display: block;">
                    <div class="widget-main">
                        <div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="panel panel-default" style="margin-bottom:10px;">
                                        <div class="panel-body">
                                            <form class="form-horizontal" role="form" onsubmit="return false;">

                                                <div class="form-group" style="margin-bottom: 0;">
                                                    <label class="col-sm-1 control-label">作者：</label>
                                                    <div class="col-sm-3" style="width: 200px;">
                                                        <input id="qryAuthor"  data-original-title="Tooltip for name" class="form-control radius" placeholder="关键字" type="text" />
                                                    </div>
                                                    <label class="col-sm-1 control-label">教程名称：</label>
                                                    <div class="col-sm-3" style="width: 200px;">
                                                        <input id="qryTitle" data-original-title="Tooltip for name" class="form-control radius" placeholder="关键字" type="text"  />
                                                    </div>

                                                    <div class="col-sm-1">
                                                        <button id="queryBtn" class="btn btn-info btn-sm btn-label-left radius">
                                                            <i class="fa fa-search"></i>查询
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12">
                                    <div style="margin-bottom: 10px;">
                                        <button  class="btn btn-success btn-sm radius" id="addLearnBtn">
                                            <i class="fa  fa-plus"></i> 新增
                                        </button>
                                        <button  class="btn btn-primary btn-sm radius" id="modifyLearnBtn">
                                            <i class="fa fa-pencil-square-o"></i> 修改
                                        </button>
                                        <button  class="btn btn-danger btn-sm radius" id="deleteLearnBtn">
                                            <i class="fa fa-trash-o fa-lg"></i> 删除
                                        </button>
                                    </div>
                                    <table id="jqGrid"></table>
                                    <div id="jqGridPager"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--新增窗口-->
<div id="addModal" class="bootbox modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增教程</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" onsubmit="return false;">

                    <div class="form-group" style="display: none;">
                        <label class="col-sm-2 control-label">id</label>
                        <div class="col-sm-8">
                            <input id="id" type="text" class="form-control" placeholder="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">作者</label>
                        <div class="col-sm-8">
                            <input id="author" type="text" class="form-control" placeholder="输入作者" />
                        </div>
                    </div>
                    <div class="form-group" >
                        <label class="col-sm-2 control-label">教程名称</label>
                        <div class="col-sm-8">
                            <input id="title" type="text" class="form-control" placeholder="输入教程名称" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">教程地址</label>
                        <div class="col-sm-8">
                            <input id="url" type="text" class="form-control" placeholder="输入教程地址" />
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button data-bb-handler="confirm" type="button" id="saveLearnBtn" class="btn btn-success radius">
                    <span><i class="icon-ok"></i></span> &nbsp;保&nbsp;&nbsp;存
                </button>
                <button data-bb-handler="cancel" type="button" id="cancelSave" class="btn btn-danger radius">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/assets/js/ace-extra.min.js" ></script>
<!--[if lt IE 9]>

<script src="${ctx}/assets/js/html5shiv.js" ></script>
<script src="${ctx}/assets/js/respond.min.js" ></script>
<![endif]-->
<script src="${ctx}/assets/js/jqGrid/jquery.jqGrid.min.js" ></script>
<script src="${ctx}/assets/js/jqGrid/i18n/grid.locale-en.js" ></script>
<script src="${ctx}/assets/js/bootstrap.min.js" ></script>
<script src="${ctx}/assets/js/jquery-ui-1.10.3.custom.min.js" ></script>
<script src="${ctx}/assets/js/jquery.slimscroll.min.js" ></script>
<script src="${ctx}/assets/js/chosen.jquery.min.js" ></script>
<script src="${ctx}/assets/js/ace-elements.min.js" ></script>
<script src="${ctx}/assets/js/ace.min.js" ></script>
<script src="${ctx}/assets/js/md5.js" ></script>
<script src="${ctx}/assets/js/custom.js" ></script>
<script src="${ctx}/assets/js/jquery.messager.js" ></script>
<script src="${ctx}/assets/js/layer/layer.js" ></script>
<script src="${ctx}/assets/js/bootbox.min.js" ></script>
<script src="${ctx}/js/learn-resource.js"></script>
</body>
</html>