let $table = $('#projectList');

function initTable() {
    var url = "/hr/project/list";
    $table.bootstrapTable({
        url: url,
        method: 'post',
        height: 800,
        search: true,
        toolbar: '#toolbar',
        pagination: true,       //显示分页条
        // sidePagination: 'server',
        showRefresh: true,      //显示刷新按钮
        theadClasses: 'btn-primary',
        undefinedText: ' ',
        visibleSearch: true,
        pageSize: 100,
        // pageList: "[30, 100, All]",自动
        queryParams: function queryParams(params) {
            var param = {
                projectName: null,
                projectGroupEid: null,
                peopleEid: null,
                wbsCode: null,
                tePeriod: null,
            };
            // $.extend(param, params);
            return param;
        },
        responseHandler: function (res) {
            var data = {
                rows: res.data,
                total: res.data.size
            }

            return data;
        },
        columns: [
            {
                field: 'peopleEid',
                title: 'NO.',
                width: 50,
                formatter: function (value, row, index) {
                    return index + 1;
                },
            },
            {
                field: 'peopleEid',
                title: 'Eid',
                'class': 'text-left narrow',
            }, {
                field: 'projectName',
                title: 'Project Name',
                'class': 'text-left narrow',
            }, {
                field: 'wbsCode',
                title: 'WBS Code',
                'class': 'text-left narrow',
            }, {
                field: 'tePeriod',
                title: 'TE Period',
                'class': 'text-left narrow',
            }],
    });
}

initTable();