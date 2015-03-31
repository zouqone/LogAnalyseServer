<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/app/table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/common.js"></script>
<script type="text/javascript" src="../../js/app/table.js"></script>
<title>表格</title>
<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
var url = baseUrl+'/ComponentAction';
function initPage(){
	freshTable(url);
	freshPageCount(url,'page_span_id')
}
</script>
</head>
<body onload="initPage();">
<center>
<h2><span>表格的基本操作</span></h2>
</center>
<div style="width: 100%;height: 500px;border: 1px solid #BBBBBB;">
	<!-- 菜单 -->
	<div id="menu_div_id" class="tool_menu_div">
		<ul class="menu_item_ul">
			<li><a onclick="addData('table_form_id',url,'page_span_id')">新增</a></li>
			<li><a onclick="editData('table_form_id',url)">编辑</a></li>
			<li><a onclick="">删除</a></li>
			<li><a onclick="">插入</a></li>
			<li><a onclick="freshTable(url)">刷新</a></li>
			<li><a onclick="clearTable('table_form_id')">清除</a></li>
			<li><a onclick="">表单</a></li>
		</ul>

	</div>
	
	<!-- 表单 -->
	<div class="table_form_div">
		<form name="data_form">
			<table id="table_form_id" class="data_table">
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
				<td class="page_span"><span id="page_span_id" pageSize="15" pageNum="1">
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
</body>
</html>