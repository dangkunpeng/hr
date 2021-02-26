/**
 * 获取form表单数据
 */
function ajaxPost(url, data, callback) {
    $.ajax({
        url: url,
        method: 'POST',
        contentType: 'application/json;charset=utf-8',
        async: false,//同步
        data: JSON.stringify(data),
        success: function (res) {
            if (res && res.code==200) {
                toastr.message('Operate Success!',"success",5000);
            } else {
                toastr.message('Operate Failed!',"error",5000);
            }

            if(callback){
                callback(res)
            }
        },
        error: function (res) {
            toastr.message('Operate Failed',"error",5000);
            if(callback){
                callback(res)
            }
        },
    });
}
function ajaxGet(url, data, callback) {
    $.ajax({
        url: url,
        method: 'GET',
        contentType: 'application/json;charset=utf-8',
        async: false,//同步
        data: JSON.stringify(data),
        success: function (res) {
            if (res && res.code==200) {
                toastr.message('Operate Success!',"success",5000);
            } else {
                toastr.message('Operate Failed!',"error",5000);
            }

            if(callback){
                callback(res)
            }
        },
        error: function (res) {
            toastr.message('Operate Failed',"error",5000);
            if(callback){
                callback(res)
            }
        },
    });
}
$.fn.getFormData = function (isValid) {
    var items = $(this).find('input,select,textarea'); //获取所有表单域
    var data = {};
    $.each(items, function (index, item) {
        if (!item.name) return;
        if (/^checkbox|radio$/.test(item.type) && !item.checked) return;
        var value = item.value;
        if (item.type == "checkbox") {//如果多选
            if (data[item.name]) {
                value = data[item.name] + "," + value;
            }
        }
        if (isValid) {
            //如果为true,只需要处理有数据的值
            if (value) {
                data[item.name] = value;
            }
        } else {
            data[item.name] = value;
        }
    });
    return data;
};
// 清空表单
$.fn.clearForm = function () {
    //获取所有表单域
    var items = $(this).find('input,select,textarea');
    $.each(items, function (index, item) {
        // 拼接选择器
        var selector = "input[name='" + item.name + "']";
        // 置空
        $(selector).val("");
    });
};
$(function () {
    'use strict';
    $('#nav-accordion').dcAccordion({
        eventType: 'click',
        autoClose: true,
        saveState: true,
        disableLink: true,
        speed: 'slow',
        showCount: false,
        autoExpand: true,
        classExpand: 'dcjq-current-parent'
    });
    //设置激活的li
    var activePage = $("#activePage").val();
    if (activePage) {
        $("#" + activePage).parent().parent().parent().slideDown(200);
        $("#" + activePage).parent().parent().slideDown(200);
        $("#" + activePage).parent().slideDown(200);
        $("#" + activePage).slideDown(200).addClass("active");
    }

});
var Script = function () {
    'use strict';
    jQuery('#sidebar .sub-menu > a').click(function () {
        var o = ($(this).offset());
        //   diff = 250 - o.top;
        var offset = o.top - $('#sidebar').height() / 2;
        offset = offset + 150;
        // alert(offset);
        $('#sidebar').animate({scrollTop: offset}, 500);

    });
    $(function () {
        function responsiveView() {
            var wSize = $(window).width();
            if (wSize <= 768) {
                $('#container').addClass('sidebar-close');
                $('#sidebar > ul').hide();
            }
            if (wSize > 768) {
                $('#container').removeClass('sidebar-close');
                $('#sidebar > ul').show();
            }
        }

        $(window).on('load', responsiveView);
        $(window).on('resize', responsiveView);
    });
    $('.fa-bars').click(function () {
        if ($('#sidebar > ul').is(":visible") === true) {
            $('#main-content').css({
                'margin-left': '0px'
            });
            $('#sidebar').css({
                'margin-left': '-210px'
            });
            $('#sidebar > ul').hide();
            $("#container").addClass("sidebar-closed");
        } else {
            $('#main-content').css({
                'margin-left': '210px'
            });
            $('#sidebar > ul').show();
            $('#sidebar').css({
                'margin-left': '0'
            });
            $("#container").removeClass("sidebar-closed");
        }
    });
    jQuery('.panel .tools .fa-chevron-down').click(function () {
        var el = jQuery(this).parents(".panel").children(".panel-body");
        if (jQuery(this).hasClass("fa-chevron-down")) {
            jQuery(this).removeClass("fa-chevron-down").addClass("fa-chevron-up");
            el.slideUp(200);
        } else {
            jQuery(this).removeClass("fa-chevron-up").addClass("fa-chevron-down");
            el.slideDown(200);
        }
    });
    jQuery('.panel .tools .fa-times').click(function () {
        jQuery(this).parents(".panel").parent().remove();
    });
    $('.tooltips').tooltip();
    $('.popovers').popover();
    if ($(".custom-bar-chart")) {
        $(".bar").each(function () {
            var i = $(this).find(".value").html();
            $(this).find(".value").html("");
            $(this).find(".value").animate({
                height: i
            }, 2000)
        })
    }
}();