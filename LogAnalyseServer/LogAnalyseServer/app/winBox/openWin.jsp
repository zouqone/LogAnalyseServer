<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/app/tool.css" rel="stylesheet" type="text/css" />
<link href="../../css/app/table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/common.js"></script>
<script type="text/javascript" src="../../js/app/tool.js"></script>
<script type="text/javascript" src="../../js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../..//js/jquery/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="../../js/app/table.js"></script>
<title>winbox</title>
<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
var url = baseUrl+'/UserVoAction';
function initPage(){
	freshTable(url);
	freshPageCount(url,'page_span_id')
}
function openWin(){
	var win_box_obj = document.getElementById("win_box_id");
	win_box_obj.style.display="";
	setObjMiddleX(win_box_obj);
	setObjMiddleY(win_box_obj);
	
}


$(function() {
	$( "#win_box_id" ).draggable({handle:"div.win_header",containment:"window"});
});

function openHellow1(){
	var id = "win_box_1";
	
	createWinBox({
		title:"myWindows",
		width:"500px",
		div:id
	});
}

function openHellow2(){
	var id = "win_box_2";
	createWinBox({
		title:"查询选择窗口",
		width:"500px",
		div:id,
		headclass:"titleSearch"
	});
}
</script>

</head>
<body onload="initPage();">
<input type="button" value="openWinBox" onclick="openWin()">
<input type="button" value="ceateWinBox1" onclick="openHellow1()">
<input type="button" value="ceateWinBox2" onclick="openHellow2()">
<div class="win_div " id="win_box_id" style="width: 780px;height: auto;">
	<div class="win_titlebar win_draggable win_header win_font"  id="win_box_head_id">
		<span id="win_id_1" class="win_box_title">WinBox</span>
		<button class="win_button" type="button" onclick="closeWin(this)">
			<span class="win_button_icon"></span>
		</button>
	</div>
	<div class="win_box win_content" style="height: auto;max-height: none;min-height: 107px;width: auto;">
		<p></p>
		
		<div class="search_div" style="border: 0px;margin-bottom: 3px;">
			<span>搜索字段 : </span>
			<input id="query" type="text" value="" name="username">
			<span style="font-size: 12px;color: #99CC00">(自动搜索) </span>
			<input type="button" style="font-size: 14px;font-weight: bolder; " class="win_input_button" value="添加" 
			onclick="selectRecord(this)">
		</div>
		<div class="list_div" style="width: auto;height: auto;border: 0px;">
			<div class="table_form_div" style="width: auto;height: auto;">
				<form name="data_form">
					<table id="table_form_id" class="data_table" style="height: auto;">
						<tr class="data_table_tr data_head" evenClassName="data_table_tr_even">
							<td style="" class="data_table_td input_checkbox_head" onclick="toggleCheckBox('checkboxName',this)">
							<input type="checkbox" checkboxName="table_form_checkbox" name="table_form_checkbox_all" value=""></td>
							<td style="width: 50px;" class="data_table_td">序号</td>
							<td style="width: 70px;" class="data_table_td">用户</td>
							<td style="width: 70px;" class="data_table_td">密码</td>
							<td style="width: 70px;" class="data_table_td">性别</td>
							<td style="width: 70px;" class="data_table_td">年龄</td>
							<td style="width: 70px;" class="data_table_td">地址</td>
							<td style="width: 70px;" class="data_table_td">邮箱</td>
							<td style="width: 100px;" class="data_table_td">其它信息</td>
							<!-- <td style="width: auto;" ></td> -->
						</tr>
						<tr class="data_table_tr data_table_tr_even" id="199">
							<td style="" class="data_table_td input_checkbox_head" ><input type="checkbox" name="user" value=""></td>
							<td style="" class="data_table_td">1</td>
							<td style="" class="data_table_td"><input class="data_table_input" type="text" value="admin"></td>
							<td style="" class="data_table_td">1</td>
							<td style="" class="data_table_td">1</td>
							<td style="" class="data_table_td">22</td>
							<td style="" class="data_table_td"></td>
							<td style="" class="data_table_td"></td>
							<td style="" class="data_table_td"></td>
						</tr>
						<tr></tr>
					</table>
				</form>
				<table class="page_table">
					<tr>
						<td></td>
						<td class="page_span"><span id="page_span_id" pageSize="5" pageNum="1">
						&nbsp;共条&nbsp;<b>0</b>&nbsp;记录 &nbsp;-&nbsp;
						<a class="is_click" onclick="freshPageCount(url,'page_span_id')">首页</a>
						<a onclick="freshPageCount(url,'page_span_id')">上一页</a>
						<a onclick="freshPageCount(url,'page_span_id')">下一页</a>
						<a onclick="freshPageCount(url,'page_span_id')">末页 </a>
						 &nbsp;- &nbsp;第 1/21 页&nbsp;显示15 50条&nbsp;
						 转到第<input type="text" name="toPageNumber" value="1">页&nbsp;
						 <a onclick="freshPageCount(url,'page_span_id')">跳转 </a>
						</span></td>
						<td><a onclick="freshPageCount(url,'page_span_id')">刷新</a></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="win_font" style="height: 24px;padding-top: 5px;">已选择的记录</div>
		<div class="select_div">
			<div class="selectedRecordDiv">
				<span class="record_name">aolianguf</span>
				<span><a onclick="delSelectedRecordDiv(this)">删除</a></span>
				<input type="hidden" name="user_input_hidden" value="">
			</div>
		</div>
		
		
	</div>
	
	<div class="win_bottom">
		<input class="win_input_button" type="button" value="ok" onclick="win_ok(this)">
		
		<input class="win_input_button" type="button" value="cancel" onclick="win_cancel(this)">
	</div>
	<!-- <div class="win_resizable_handle win_resizable_n" style="z-index: 90;"></div>
	<div class="win_resizable_handle win_resizable_e" style="z-index: 90;"></div>
	<div class="win_resizable_handle win_resizable_s" style="z-index: 90;"></div>
	<div class="win_resizable_handle win_resizable_w" style="z-index: 90;"></div> -->
</div>

<div id="win_box_1" class="winBoxDiv">
	你好！
</div>
<div id="win_box_2" class="">
	你好！
</div>

</body>
</html>