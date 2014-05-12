<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.log.user.vo.UserVo,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userList</title>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/userevent.js"></script>
<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';

function initPage(){
	getData();
}


</script>

</head>
<body onload="initPage()">
	<div class="user_div">
		<p><h3>用户管理</h3></p>
		
		<div class="user_button_div">
			<input type="button" value="新增" name=""  onclick="showAddDiv('user_add_div_id')">
			<input type="button" value="修改" name=""  onclick="ShowModDataDiv('id','showListId','editListId','user_edit_div_id')">
			<input type="button" value="删除" name=""  onclick="delData('id','showListId')">
			<!-- <input type="button" value="保存" name=""  onclick=""> -->
			<input type="button" value="查询" name=""  onclick="queryByCondition('id','query_div_id','showListId')">
		</div>
		
		<!-- 查询  -->
		<div id="query_div_id" class="user_query_button_div">
			<table  id="queryTableId" class="user_table">
				<tr class="user_table_tr">
					<td>用户</td>
					<td><input class="user_query_input_text" type="text" name="username" value=""></td>
					<td>年龄</td>
					<td><input class="user_query_input_text" type="text" name="age" value=""></td>
					<td>邮箱</td>
					<td><input class="user_query_input_text" type="text" name="email" value=""></td>
				</tr>
				<tr class="user_table_tr">
					<td>性别</td>
					<td>
						<select name="sex" class="user_query_input_text">
							<option value="">请选择</option>
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					</td>
					<td>地址</td>
					<td colspan="3">
						<input class="user_query_input_text" style="width: 100%;" 
						type="text" name="address" value="">
					</td>
					<!-- <td></td>
					<td></td> -->
				</tr>
			</table>
		</div>
		
		<!-- 列表 -->
		<table  id="showListId" class="user_table">
			<tr class="user_table_tr">
				<td style="width: 24px;" onclick="toggleCheckBox('id',this)"><input type="checkbox"></td>
				<td>序号</td>
				<td>用户</td>
				<td>密码</td>
				<td>年龄</td>
				<td>性别</td>
				<td>地址</td>
				<td>邮箱</td>
				<td>其它信息</td>
			</tr>
		</table>
		
		<!-- 新增 -->
		<div class="user_add_div" id="user_add_div_id" style="display: none;">
			<div class="user_button_div">
				<input type="button" value="增加" name=""  onclick="addData()">
				<input type="button" value="删除" name=""  onclick="deleteData('id','addListId')">
				<input type="button" value="取消" name=""  onclick="cancelData('id','addListId','user_add_div_id')">
				<input type="button" value="保存" name=""  onclick="saveData('id','addListId','user_add_div_id','id','showListId')">
			</div>
			<table  id="addListId" class="user_table user_add_table">
				<tr class="user_table_tr">
					<td style="width: 24px;" onclick="toggleCheckBox('id',this)"><input class="checkboxAll" type="checkbox"></td>
					<td style="width: 40px;">序号</td>
					<td>用户</td>
					<td>密码</td>
					<td style="width: 40px;">年龄</td>
					<td style="width: 60px;">性别</td>
					<td>地址</td>
					<td>邮箱</td>
					<td>其它信息</td>
				</tr>
			</table>
		</div>

		<!-- 修改 -->
		<div class="user_add_div" id="user_edit_div_id" style="display: none;">
			<div class="user_button_div">
				<input type="button" value="删除" name=""  onclick="deleteData('id','editListId')">
				<input type="button" value="取消" name=""  onclick="cancelData('id','editListId','user_edit_div_id')">
				<input type="button" value="保存" name=""  onclick="updateData('id','editListId','user_edit_div_id','id','showListId')">
			</div>
			<table  id="editListId" class="user_table user_add_table">
				<tr class="user_table_tr">
					<td style="width: 24px;" onclick="toggleCheckBox('id',this)"><input class="checkboxAll" type="checkbox"></td>
					<td style="width: 40px;">序号</td>
					<td>用户</td>
					<td>密码</td>
					<td style="width: 40px;">年龄</td>
					<td style="width: 60px;">性别</td>
					<td>地址</td>
					<td>邮箱</td>
					<td>其它信息</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>