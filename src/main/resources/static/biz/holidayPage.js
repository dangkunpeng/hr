let $table = $('#holiday');

function refreshTable() {
    $table.bootstrapTable("destroy");
    var url = "/holiday/query/page";
    $table.bootstrapTable({
        url: url,
        method: 'post',
        height: 800,
        search: true,
        toolbar: '#toolbar',
        pagination: true,       //显示分页条
        sidePagination: 'server',
        showRefresh: true,      //显示刷新按钮
        theadClasses: 'btn-primary',
        undefinedText: ' ',
        visibleSearch: true,
        queryParams: function queryParams(params) {
            var param = $("#queryForm").getFormData(true);
            $.extend(param, params);
            return param;
        },
//        responseHandler: function (res) {
//            var data = {
//                rows:res.content,
//                total:res.totalElements
//            }
//
//            return data;
//        },
        columns: [
            {
                field: 'uuid',
                title: '序号',
                'class': 'text-center narrow',
                formatter: function (row, value, index) {
                    return index + 1;
                },
            },  {
                field: 'slfDate',
                title: '日期',
                'class': 'text-center narrow',
            },  {
                field: 'slfYear',
                title: '年',
                'class': 'text-center narrow',
            }, {
                field: 'slfMonth',
                title: '月',
                'class': 'text-center narrow',
            }, {
                field: 'slfWeek',
                title: '周',
                'class': 'text-center narrow',
            },{
                field: 'slfWeekDay',
                title: '星期',
                'class': 'text-center narrow',
            }],
    });
}

refreshTable();

function clearForm() {
    $("#queryForm").clearForm();
}