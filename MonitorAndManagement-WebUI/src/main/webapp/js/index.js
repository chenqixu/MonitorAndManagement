// 初始化
$(function () {

});

var active_parent = $("li.active");

function change_type(_parent) {
    active_parent.removeClass("active");
    active_parent = _parent.parent();
    active_parent.addClass('active');
}

function loadingModal(modal) {
    if (modal == "show") {
        $('#loadingModal').modal('show');
    } else {
        $('#loadingModal').modal('hide');
    }
}