let $table = $('#holiday');
var clazz = "selectCell"
function changeYear() {
	var year = $("#selectedYear").val();
	if (year) {
		var url = "/holiday/query/" + year;
	}
	
	 $table.bootstrapTable({
		 url: url,
	 });
	 var opt = {url: url}
	 
	 $table.bootstrapTable('refresh', opt);
}
function grRowStyle(row, index) {
	var month = row.slfMonth;
	
	if (month % 2 == 1) {
		return {
			classes: 'text-nowrap another-class',
			css: { "background-color": "lightblue" }
		}
	} else {
		return {
			classes: 'text-nowrap another-class',
			css: { "background-color": "lightgreen" }
		}
	}
}

function formatter(value,row,index) {
	if (value) {
		var formatter = '<div id="'+value+'" onclick="clickCell('+value+')"><span >'+value+'</span></div>'
		return formatter
	} else {
		return "";
	}
	
}

function clickCell(value) {
	
	var $ele = $("#" + value);
	var selectValue = $("#selectedValue").val();
	if (selectValue.indexOf(value) > -1) {
		// 已经选择，再次点击
		console.log("已选择，点击放弃选择");
		selectValue = selectValue.replace(value,"");
		selectValue = selectValue.replace(",,",",");
		$ele.parent().removeClass(clazz);
		
	} else {
		// 未选择，点击选择
		console.log("未选择，点击选择");
		selectValue = selectValue + "," + value;
		$ele.parent().addClass(clazz);
	}
	
	$("#selectedValue").val(selectValue);
}

function initTable() {
	var year = $("#selectedYear").val();
	if (!year) {
		year=2019;
	}
	var url = "/holiday/query/" + year;
    $table.bootstrapTable({
        url: url,
        method: 'post',
        height: 800,
        search: true,
        toolbar: '#toolbar',
        pagination: true,       //显示分页条
        sidePagination: 'server',
        showRefresh: true,      //显示刷新按钮
        theadClasses:'btn-primary',
        undefinedText: ' ',
        visibleSearch:true,
        pageSize : 100,
        pageList:"[30, 100, All]",
        queryParams: function queryParams(params) {
            console.log(params);
            var param = {
                uuid: '',
                day: '',
                desc: ''
            };
            $.extend(param, params);
            console.log(param);
            return param;
        },
        columns: [
        {
            field: 'uuid',
            title:'序号',
            'class': 'text-center narrow',
            formatter:function(row,value,index){
                return index + 1;
            },
        }, {
            field: 'slfMonth',
            title: '月',
            'class': 'text-center narrow',
        },  {
            field: 'slfWeek',
            title: '周',
            'class': 'text-center narrow',
        }, {
            field: 'monday',
            title: '周一',
            'class': 'text-center narrow',
            formatter: formatter,
        }, {
            field: 'tuesday',
            title: '周二',
            'class': 'text-center narrow',
            formatter: formatter,
        }, {
            field: 'wednesday',
            title: '周三',
            'class': 'text-center narrow',
            formatter: formatter,
        }, {
            field: 'thursday',
            title: '周四',
            'class': 'text-center narrow',
            formatter: formatter,
        }, {
            field: 'friday',
            title: '周五',
            'class': 'text-center narrow',
            formatter: formatter,
        },  {
            field: 'saturday',
            title: '周六',
            'class': 'text-center narrow',
            formatter: formatter,
        },  {
            field: 'sunday',
            title: '周日',
            'class': 'text-center narrow',
            formatter: formatter,
        },],
    });
}

function initSelect() {
	var selectValue = $("#selectedValue").val();
	var rows = selectValue.split(",");
	console.log(rows);
	for(var index =0;index < rows.length;index ++) {
		var row = "#" + rows[index] ;
        $(row).parent().addClass(clazz);
	}
}

function saveSelected() {
	var data = {
		slfDate:$("#selectedValue").val(),
		slfYear:$("#selectedYear").val()
	}
    $.ajax({
        url: "/holiday/update",
        method: 'POST',
        contentType: 'application/json',
        async: false,//同步
        data: JSON.stringify(data),
        success: function (res) {
            //$table.bootstrapTable("refresh");
        },
        error: function () {
            //$table.bootstrapTable("refresh");
        },
    });
}
// $(function () {
	
	initTable();
	initSelect();
// });