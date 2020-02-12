<%--
  Created by IntelliJ IDEA.
  User: chenqixu
  Date: 2019/5/7
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>build</title>
    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <textarea class="form-control" rows="10" style="min-width: 80%" id="source"></textarea>
            <button class="btn" type="button" id="exec">执行</button>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <h5>
                日志输出：
            </h5>
            <textarea class="form-control" rows="10" style="min-width: 80%" id="logger"></textarea>
        </div>
    </div>
</div>
<script src="<%=path%>/js/jquery.min.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/build.js"></script>
</body>
</html>
