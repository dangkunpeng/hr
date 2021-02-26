let $table = $('#peopleList');

function openPeopleDetail() {
    $("#peopleForm").clearForm();
}

function savePeople() {
    const formData = $("#peopleForm").getFormData();
    var url = "/hr/people/save";
    ajaxPost(url, formData, saveCallBack);
}

var saveCallBack = function (res) {
    if (res && res.code == 200) {
        $table.bootstrapTable("refresh");
    }
}

function offline(eid) {

    var url = "/hr/people/offLine";
    var data = {
        peopleEid: eid
    };
    ajaxPost(url, data, saveCallBack);
}

function showProjectDetail(eid) {
    $("#projectForm").clearForm();
    $("#projectEid").val(eid);
}
function saveProject() {
    const formData = $("#projectForm").getFormData();
    var url = "/hr/project/save";
    ajaxPost(url, formData, saveCallBack);
}
function initTable() {
    var url = "/hr/people/list";
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
                peopleEid: null,
                peopleName: null,
                peopleGroupEid: null
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
                title: 'Operation',
                width: 200,
                formatter: function (value, row, index) {
                    if (row.status != 'offline') {
                        var txt = new Array();
                        txt.push('<div class="btn-group" role="group" aria-label="operation">');
                        txt.push('<button type="button" class="btn btn-success btn-md" data-toggle="modal" data-target="#myProject" onclick="showProjectDetail(\'' + value + '\')">Project</button>');
                        txt.push('<button type="button" class="btn btn-danger btn-md" onclick="offline(\'' + value + '\')">Offline</button>');
                        txt.push("</div>");
                        return txt.join('');
                    } else {
                        return "";
                    }
                },
            },
            {
                field: 'peopleEid',
                title: 'Eid',
                'class': 'text-left narrow',
            }, {
                field: 'peopleName',
                title: 'Name',
                'class': 'text-left narrow',
            }, {
                field: 'peopleGroupEid',
                title: 'Group Eid',
                'class': 'text-left narrow',
            }, {
                field: 'status',
                title: 'status',
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