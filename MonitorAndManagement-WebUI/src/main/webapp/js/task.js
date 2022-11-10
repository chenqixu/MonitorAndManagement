/**
 * 遮罩层
 * @param modal
 */
function loadingModal(modal) {
    parent.loadingModal(modal);
}

/**
 * 表单转成JSON对象
 */
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}

/**
 * 初始化
 */
$(function () {
    // submit提交按钮绑定点击事件
    $("#submit").on('click', function () {
        loadingModal('show');
        var params = $("#createForm").serializeObject();
        // 拼接任务名称
        params.task_name = params.task_type + "_" + params.table_name;
        $.ajax({
            url: "http://localhost:19090/toolui/task/create",
            data: JSON.stringify(params),
            type: "POST",
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                if (data.ret == 0) {
                    alert("保存成功！")
                } else {
                    alert("保存失败！" + data.desc);
                }
                loadingModal('hide');
                // 返回流程创建页面
                window.location.href = path + '/createTask.jsp';
            },
            error: function (data) {
                alert("发生未知异常，请联系系统管理员！")
                console.error(data);
                loadingModal('hide');
            }
        });
    });

    // cancel按钮绑定点击事件
    $("#cancel").on('click', function () {
        // 返回流程创建页面
        window.location.href = path + '/createTask.jsp';
    });
});