<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>我的实时处理流程</title>

<meta name="description" content="Source code generated using layoutit.com">
<meta name="author" content="LayoutIt!">

<link rel="stylesheet" type="text/css" href="<%=path%>/css/bbootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/jquery.dataTables.min.css">
</head>
<body>
<div class="container" style="width: 100%;">
    <div class="row clearfix">
        <table id="streamTable" class="display" style="width:100%">
            <thead>
            <tr>
                <th><input type="checkbox" class="form-check-input" value="">全选</th>
                <th>名称</th>
                <th>类型</th>
                <th>状态</th>
                <th>最新异常信息</th>
                <th>KAFKA积压</th>
                <th>运行时长</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input type="checkbox" class="form-check-input" value=""></td>
                <td>user_product</td>
                <th>多线程方式</th>
                <td>run</td>
                <td>无</td>
                <td>10</td>
                <td>12d</td>
                <td>
                    <button>启动</button>
                    <button>停止</button>
                    <button>恢复</button>
                    <button>暂停</button>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox" class="form-check-input" value=""></td>
                <td>res_piece_type_goods_consume</td>
                <th>单线程方式</th>
                <td>run</td>
                <td>无</td>
                <td>10</td>
                <td>27m</td>
                <td>
                    <button>启动</button>
                    <button>停止</button>
                    <button>恢复</button>
                    <button>暂停</button>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox" class="form-check-input" value=""></td>
                <td>itv_users</td>
                <th>多线程方式</th>
                <td>pause</td>
                <td>ADB导入异常</td>
                <td>30000</td>
                <td>5d</td>
                <td>
                    <button>启动</button>
                    <button>停止</button>
                    <button>恢复</button>
                    <button>暂停</button>
                </td>
            </tr>
        </table>
    </div>
</div>
<script type="text/javascript" language="javascript" src="<%=path%>/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=path%>/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=path%>/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=path%>/js/stream.js"></script>
</body>
</html>
