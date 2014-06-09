<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="cn.log.tool.util.ActionHelp" %>
<%@ page import="cn.log.app.user.util.IUserVoConstants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String basePath = request.getContextPath();
List beans = (List)request.getAttribute(IUserVoConstants.BEANS);
String dataStr = null;
if(beans!=null&&beans.size()>0){
	dataStr = ActionHelp.listToString(beans);
}else{
	dataStr = "[]";
}


%>
<link href="<%=basePath%>/css/app/table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/table.js"></script>

<title>表格</title>
<script type="text/javascript">
var baseUrl = '<%=basePath%>';
var url = baseUrl+'/UserVoAction';
var listAction = url+"_"+"getList";
var insertAction = url+"_"+"insert";
var updateAction = url+"_"+"update";
var detailAction = url+"_"+"detail";
var deleteAction = url+"_"+"ajaxDelete";
var saveAction = url+"_"+"save";

var dataStr = '<%=dataStr%>';

var table_form_id = "table_form_id";

function initPage(){
	var datas =  StringToJson(dataStr);
	loadDatas(table_form_id,datas);
}
function del(table_form_id){
	var data_table = jQuery("#"+table_form_id);
	var checkboxs = getcheckBoxbyNameStatus("table_form_checkbox",data_table[0],true);
	var ids= [];
	if(checkboxs!=null&&checkboxs.length>0){
		if(confirm("确定要删除选择的数据！")==false){
			return false;
		}
		for(var i=0;i<checkboxs.length;i++){
			var inputs = checkboxs[i];
			var tr = jQuery(inputs).parents("tr")[0];
			var id = jQuery("[name='id']",tr).val();
			ids.push(id);
		}
		var idStr = ids.join();
		//alert(idStr);
		$.ajax({  
	    	url: deleteAction, type: "POST",data:{ids:idStr},
	        success: function(data) {     
	        	//alert(data);
	        	var num = Number(data);
	        	if(num>0){
	        		for(var i=0;i<checkboxs.length;i++){
	        			var inputs = checkboxs[i];
	        			var tr = jQuery(inputs).parents("tr")[0];
	        			jQuery(tr).remove();
	        		}
	        	}
	        }
		});
	}else{
		alert("请选至少择一条记录！");
	}

}
jQuery(document).ready(function(){
	initPage();
});
</script>
</head>
<body>

<!-- 顶部区域 -->

<!-- 查询区域 -->



<!-- 菜单区域  -->
<div id="menu_div_id" class="tool_menu_div" style="width: 100%">
	<ul class="menu_item_ul">
		<li><a onclick="addData('table_form_id',url,'page_span_id')">新增</a></li>
		<li><a onclick="editData('table_form_id',url)">编辑</a></li>
		<li><a onclick="del('table_form_id')">删除</a></li>
		<li><a onclick="">插入</a></li>
		<li><a onclick="freshTable(url)">刷新</a></li>
		<li><a onclick="clearTable('table_form_id')">清除</a></li>
		<li><a onclick="">表单</a></li>
	</ul>

</div>
<div style="height: 2px;width: 100%;"></div>
<!-- 数据区域 -->
<div class="table_form_div" style="height: 440px; width: auto;margin-top: 0px;">
<form name="data_form">
	<table id="table_form_id" class="data_table" style="width: auto;height: auto;">
		<tbody>
			<tr class="data_table_tr data_head" style="height: 30px;" evenClassName="data_table_tr_even">
				<td style="" class="data_table_td input_checkbox_head" onclick="toggleCheckBox('checkboxName',this)">
					<input type="checkbox" checkboxName="table_form_checkbox" name="table_form_checkbox_all">
				</td>
				<td style="width: 50px;" class="data_table_td">序号</td>
				<td style="width: 70px;" class="data_table_td">用户</td>
				<td style="width: 70px;" class="data_table_td">密码</td>
				<td style="width: 70px;" class="data_table_td">性别</td>
				<td style="width: 70px;" class="data_table_td">年龄</td>
				<td style="width: 70px;" class="data_table_td">地址</td>
				<td style="width: 70px;" class="data_table_td">邮箱</td>
				<td style="width: 100px;" class="data_table_td">其它信息</td>
			</tr>
		</tbody>
		<tbody name="data_view">
			<tr style="height: auto;"></tr>
		</tbody>
		<tbody name="template_view" style="display: none;">
			<tr style="height: 26px;" class="data_table_tr" onclick="selectTr(this);">
				<td style="" name="checkboxTd" class="data_table_td input_checkbox_head" >
					<input type="checkbox" name="table_form_checkbox" value="" onclick="selectCK(this);">
					<input type="hidden" name="id" value="">
				</td>
				<td style="text-align: center;" name="numTd" class="data_table_td">1</td>
				<td style="" class="data_table_td">
					<div name="username">username</div>
				</td>
				<td style="" class="data_table_td">
					<div name="password">password</div>
				</td>
				<td style="" class="data_table_td">
					<div name="sex">sex</div>
				</td>
				<td style="" class="data_table_td">
					<div name="age">age</div>
				</td>
				<td style="" class="data_table_td">
					<div name="address">address</div>
				</td>
				<td style="" class="data_table_td">
					<div name="email">email</div>
				</td>
				<td style="" class="data_table_td">
					<div name="info">info</div>
				</td>
			</tr>
		</tbody>
		<tbody>
			<tr style="height: auto;"></tr>
		</tbody>
	</table>
</form>
</div>
<!-- 翻页条 -->

<!-- 底部区域 -->
</body>
</html>


