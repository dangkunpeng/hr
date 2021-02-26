let $table = $('#table');
let $button = $('#insert');
let $save = $('#save');

function formmatterSave(row, value,index) {
Console.log(index);
}
function insertRow() {
    $table.bootstrapTable('insertRow', {
        index: 0,
        row: {
            id: '',
            userName: '谁',
            detail: '还有啥情况'
        }
    });
}

function showConfirm() {
    $("#myConfirm").modal("show");
}

function delRow() {
    var rows = $table.bootstrapTable('getSelections');
    if (rows.length == 0) {
        alert("please select rows");
        return;
    }
    $.ajax({
        url: "/user/manage/del/user",
        method: 'POST',
        contentType: 'application/json',
        async: false,//同步
        data: JSON.stringify(rows),
        success: function (res) {
            $table.bootstrapTable("refresh");
        },
        error: function () {
            $table.bootstrapTable("refresh");
        },
    });
}

function saveRow(index) {
    var rows = $table.bootstrapTable('getData');
    if (rows.length == 0) {
        alert("please add rows");
        return;
    }
    var data = rows[index];
    $.ajax({
        url: "/user/manage/update/user",
        method: 'POST',
        contentType: 'application/json',
        async: false,//同步
        data: JSON.stringify(data),
        success: function (res) {
            $table.bootstrapTable("refresh");
        },
        error: function () {
            $table.bootstrapTable("refresh");
        },
    });
}

$.extend($.fn.bootstrapTable.defaults, {
    // clickEdit 点击编辑代码开始
    /**
     * @param {点击列的 field 名称} field
     * @param {点击列的 value 值} value
     * @param {点击列的整行数据} row
     * @param {td 元素} $element
     */
    onClickCell: function (field, value, row, $element) {
        // 单元格是否可编辑
        var cellEditAble = 'clickEditCell' == $element[0].className;
        if (cellEditAble) {
            // 如果可编辑，则增加属性contenteditable=true设置为可编辑
            $element.attr('contenteditable', true);
        }
        $element.mouseleave(function () {
            // 如果可编辑，则继续把修改后的值添加到bootstrap
            if (cellEditAble) {
                // 获取索引
                let index = $element.parent().data('index');
                // 获取内容
                let tdValue = $element.html();
                // 获取table
                var $editTable = $element.parentsUntil('table').parent();
                // cell更新
                $editTable.bootstrapTable('updateCell', {
                    index: index,       //行索引
                    field: field,       //列名
                    value: tdValue        //cell值
                })
                // console.log('单元格编辑后');
                // console.log($editTable.bootstrapTable("getData")[index]);
            }
        })
    }
    // clickEdit 点击编辑代码结束
})
$(function () {

    $table.bootstrapTable({
        url: '/user/manage/query/user',
//        url: '/magpie/query',
        method: 'post',
        height: 650,
        search: true,
        toolbar: '#toolbar',
        pagination: true,       //显示分页条
        showRefresh: true,      //显示刷新按钮
        theadClasses:'btn-primary',
        undefinedText: '暂无数据',
        visibleSearch:true,
        pageList:"[5, 10, 25, 50, 100, 200, All]",
        queryParams: function queryParams(params) {
            var param = {
                uuid: '',
                day: '',
                desc: ''
            };
            return param;
        },
        // //按需求设置不同的样式：5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
        rowStyle: function (row, index) {
            var style = 'warning';
            if (index % 2 == 0) {
            	style='success';
            }
            return { classes: style }
        },
        columns: [
        {
            field: 'id',
            title:'序号',
            width:100,
            formatter:function(row,value,index){
                return index + 1;
            },
        }, {
            field: 'id',
            title:'操作',
            width:100,
            formatter:function(row,value,index){
                return '<button id="save" onclick="saveRow('+index+')" class="btn btn-success"><span class="glyphicon glyphicon-save"></span>&nbsp;保存</button>';
            },
        }, {
            field: 'userName',
            title: '名称',
            class: 'clickEditCell'
        }, {
            field: 'password',
            title: '密码',
            class: 'clickEditCell'
        }, {
            field: 'detail',
            title: '其他',
            class: 'clickEditCell'
        },],
    });

});