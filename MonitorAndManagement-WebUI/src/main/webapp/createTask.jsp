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

    <title>createTask</title>

    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container" style="width: 100%;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3><span class="label label-primary">批处理</span></h3>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="breadcrumb">
                <li>
                    <a href="javascript:void(0);" onclick="window.location.href='<%=path%>/task/data_sync.jsp'">库对库同步</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="window.location.href='<%=path%>/task/data_to_file.jsp'">库导出到文件</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="window.location.href='<%=path%>/task/oracle_to_file.jsp'">oracle库导出到文件</a>
                </li>
                <li>
                    <a>多流程执行</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3><span class="label label-primary">实时处理</span></h3>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul class="breadcrumb">
                <li>
                    <a href="javascript:void(0);" onclick="window.location.href='<%=path%>/task/kafkaSinglePartitionSync.jsp'">实时同步</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<script src="<%=path%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
</body>
</html>
