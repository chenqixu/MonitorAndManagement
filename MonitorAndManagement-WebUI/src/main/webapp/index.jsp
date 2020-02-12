<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cqx.webui.zookeeper.ZookeeperUI"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ZookeeperUI zkui = new ZookeeperUI();
%>
<!DOCTYPE html>
<html lang="zh-CN">
    <meta charset="GBK">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>index</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
  </head>
<body>
	<!-- 遮罩层 -->
	<div class="modal fade" id="loadingModal">
		<div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
			<div class="progress progress-striped active" style="margin-bottom: 0;">
				<div class="progress-bar" style="width: 100%;"></div>
			</div>
			<h5>正在加载...</h5>
		</div>
	</div>
	<!-- 主体 -->
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <table class="table">
                    <thead>
                        <tr>
                            <th>状态</th>
                            <th>名称</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            out.println(zkui.JSPbuilder());
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="<%=path%>/js/jquery.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>
    <script src="<%=path%>/js/index.js"></script>
    <script type="text/javascript">
    	var rp = "<%=path%>";
        status("2181");
        status("2182");
        status("2183");
        status("2184");
        status("2185");
        status("2186");
    </script>
</body>
</html>
