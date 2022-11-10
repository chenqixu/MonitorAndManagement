/**
 * 初始化
 */
$(function () {
    // 查询任务
    // $.ajax({
    //     url: "http://localhost:19090/toolui/task/queryByType",
    //     data: "{\"task_type\":\"kafka_single_partition_sync\"}",
    //     type: "POST",
    //     dataType: "json",
    //     contentType: 'application/json',
    //     success: function (data) {
    //         console.log(data);
    //     },
    //     error: function (data) {
    //         alert("发生未知异常，请联系系统管理员！")
    //         console.error(data);
    //     }
    // });

    // 表格初始化
    $('#streamTable').DataTable({
        ajax: {
            url: "http://localhost:19090/toolui/task/queryByType",
            data: "{\"task_type\":\"kafka_single_partition_sync\"}",
            type: "POST",
            dataType: "json",
            contentType: 'application/json'
        },
        // 默认最后一列（最后更新时间）降序排列
        // order: [[2, "desc"]],
        columnDefs: [
            {
                targets: 2,
                data: "task_name",
                title: "名称",
                render: function (data, type, row, meta) {
                    return new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
                }
            },
            {
                targets: 1,
                data: null,
                title: "发表人",
                render: function (data, type, row, meta) {
                    return "<a href='" + row.user.html_url + "' target='_blank'>" + row.user.login + "</a>"
                }
            },
            {
                targets: 0,
                data: "title",
                title: "问题",
                render: function (data, type, row, meta) {
                    var labels = "";
                    if (row.labels.length) {
                        labels += "【";
                        for (var j = 0, labelslen = row.labels.length; j < labelslen; j++) {
                            labels += "<span style='color:#" + row.labels[j].color + "' >" +
                                row.labels[j].name + "</span>";
                            if (j != labelslen - 1) {
                                labels += ",";
                            }
                        }
                        labels += "】";
                    }
                    var hot = "";
                    if (labels.indexOf("置顶") > 0) {
                        hot = "<span class='hot'></span>"
                    }
                    return "<a href='" + row.html_url + "' target='_blank'>" + row.title + "</a>" + labels +
                        "<i class='icon Hui-iconfont'>&#xe622;</i>" + row.comments + hot;
                }
            }
        ],
        initComplete: function () {
        }
    });
});