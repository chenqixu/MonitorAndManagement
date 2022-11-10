<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>首页</title>

    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="modal fade" id="loadingModal">
    <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
        <div class="progress progress-striped active" style="margin-bottom: 0;">
            <div class="progress-bar" style="width: 100%;"></div>
        </div>
        <h5>正在加载...</h5>
    </div>
</div>
<div class="container" style="width: 100%;">
    <div class="row clearfix">
        <div class="col-md-2 column">
            <h1 style="color:#005AB5">
                MENU
            </h1>
        </div>
        <div class="col-md-8 column" style="background: #FFD306">
            <h1 style="color:#FFFFFF">
                &nbsp;
            </h1>
        </div>
        <div class="col-md-2 column" style="background: #FFD306">
            <h1 style="color:#FFFFFF">
                Tool UI
            </h1>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-2 column">
            <ul class="nav nav-pills nav-stacked">
                <li class="active">
                    <a href="createTask.jsp" target="content_iframe" onclick="change_type($(this))">流程创建</a>
                </li>
                <li>
                    <a href="batch.html" target="content_iframe" onclick="change_type($(this))">我的批处理流程</a>
                </li>
                <li>
                    <a href="stream.jsp" target="content_iframe" onclick="change_type($(this))">我的实时处理流程</a>
                </li>
                <li>
                    <a href="connectmgr.html" target="content_iframe" onclick="change_type($(this))">连接管理</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10 column">
            <iframe src="createTask.jsp" width="100%" height="800px" frameborder="no" name="content_iframe"></iframe>
        </div>
    </div>
</div>
<script src="<%=path%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/index.js"></script>
</body>
</html>

