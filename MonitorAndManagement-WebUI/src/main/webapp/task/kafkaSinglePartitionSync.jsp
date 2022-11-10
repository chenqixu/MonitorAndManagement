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

    <title>kafkaSinglePartitionSync</title>

    <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container" style="width: 100%;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3><span class="label label-primary">实时同步</span></h3>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form id="createForm" class="form-horizontal" role="form" action="<%=path%>/servlet/create.do" method="post">
                <input type="text" style="display: none;" name="task_name" value="">
                <input type="text" style="display: none;" name="task_type" value="kafka_single_partition_sync">
                <div class="form-group">
                    <label for="table_name" class="col-sm-2 control-label">表名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="table_name" name="table_name" placeholder="请输入表名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="primary_keys" class="col-sm-2 control-label">主键</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="primary_keys" name="primary_keys" placeholder="请输入主键，逗号分隔">
                    </div>
                </div>
                <div class="form-group">
                    <label for="ogg_topic" class="col-sm-2 control-label">原始OGG话题</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="ogg_topic" name="ogg_topic" placeholder="请输入原始OGG话题">
                    </div>
                </div>
                <div class="form-group">
                    <label for="flat_topic" class="col-sm-2 control-label">扁平化话题</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="flat_topic" name="flat_topic" placeholder="请输入扁平化话题">
                    </div>
                </div>
                <div class="form-group">
                    <label for="analyze_hour" class="col-sm-2 control-label">定时信息收集时间</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="analyze_hour" name="analyze_hour" placeholder="请输入定时信息收集时间">
                    </div>
                </div>
                <div class="form-group">
                    <label for="redis_conf" class="col-sm-2 control-label">redis</label>
                    <div class="col-sm-10">
                        <select id="redis_conf" name="redis_conf" class="form-control">
                            <option value="redis-10.48.134.140-6380">redis-10.48.134.140-6380</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="kafkaconf" class="col-sm-2 control-label">kafka</label>
                    <div class="col-sm-10">
                        <select id="kafkaconf" name="kafkaconf" class="form-control">
                            <option value="kafka-217">kafka-217</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="srcBean" class="col-sm-2 control-label">配置数据库</label>
                    <div class="col-sm-10">
                        <select id="srcBean" name="srcBean" class="form-control">
                            <option value="edc_cfg">edc_cfg</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="adbBean" class="col-sm-2 control-label">目标数据库</label>
                    <div class="col-sm-10">
                        <select id="adbBean" name="adbBean" class="form-control">
                            <option value="adb-iop">adb-iop</option>
                            <option value="adb-lable_core">adb-lable_core</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button id="submit" type="button" class="btn btn-primary">保存</button>
                        <button id="cancel" type="button" class="btn btn-default">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    var path = "<%=path%>";
</script>
<script src="<%=path%>/js/jquery-2.1.4.min.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/task.js"></script>
</body>
</html>
