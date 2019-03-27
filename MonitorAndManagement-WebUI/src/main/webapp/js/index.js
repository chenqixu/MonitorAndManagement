// 初始化
$(function(){
    // 进度条遮罩层初始化
    $("#loadingModal").modal({backdrop:'static', keyboard:false});
    loadModal("hide");
});

function start(id) {
    // 显示遮罩层
    loadModal("show");
    $.getScript(rp+"/operate.jsp?action=start&id="+id,function(rs){});
}

function stop(id) {
    // 显示遮罩层
    loadModal("show");
    $.getScript(rp+"/operate.jsp?action=stop&id="+id,function(rs){});
}

function restart(id) {
    // 显示遮罩层
    loadModal("show");
    $('#zookeeper'+id+'status').html("正在重启……");
    $.getScript(rp+"/operate.jsp?action=restart&id="+id,function(rs){});
}

function status(id) {
    $.getScript(rp+"/operate.jsp?action=status&id="+id,function(rs){});
}

/***********进度条遮罩层*************/
// 显示或隐藏 flag:show,hide
function loadModal(flag) {
	$("#loadingModal").modal(flag);
}