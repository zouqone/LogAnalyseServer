<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>auth</title>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/app/svn.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/svn.js"></script>
<script type="text/javascript">
var basePath = '<%=request.getContextPath()%>';
var authPath = "upload/authz";
var groups = null;
</script>
</head>
<body onload="getAuthInfo('auth_info_id',authPath)">
<input type="button" value="getAuth" onclick="getAuthInfo('auth_info_id',authPath)">
	<div class="auth_group_div" style="">
	<div id="select_group_id">
		<select class="select_group_name" name="select_group_name">
			<option selected="selected"></option>
			<option></option>
		</select>
	</div>
	<table>
		<tr><td>
		<!-- 用户管理 -->
		<table class="auth_group_table" id="auth_group_table_id">
			<tr class="auth_group_table_tool">
				<td colspan="100%">
					<div class="auth_group_div_tool">
						<input type="button" name="addGroup" value="增加群组" onclick="addGroup('svn_group_table_id','user_div_id')">
						<input type="button" name="deleteGroup" value="删除群组" onclick="delGroup('svn_group_table_id','groupName')">
						<input type="button" name="addUser" value="增加用户" onclick="addUser('user_div_id')">
						<input type="button" name="deleteUser" value="删除用户" onclick="delUser('user_div_id')">
						<!-- <input type="button" name="saveGroup" value="保存" onclick="saveGroup('group_form_id')"> -->
					</div>
				</td>
			</tr>
			<tr class="auth_group_table_head">
				<td class="auth_group_table_td auth_checkbox"><input type="checkbox"></td>
				<td class="auth_group_table_td auth_group_name">用户组</td>
				<td class="auth_group_table_td" style="width: 130px;border-right: 0px;">用户</td>
				<td class="auth_group_table_td" style="border-left: 0px;">中文名</td>
			</tr>
			<tr class="auth_group_table_tr">
				<td class="auth_group_table_td group_table_td"  colspan="2">
					<!-- 用户组 -->
					<form name="group_form" id="group_form_id">
					<div class="svn_group_div" id="svn_group_div_id">
						<table class="svn_group_table" id="svn_group_table_id">
							<tr class="svn_group_table_tr">
								<td class="svn_group_table_tr_td auth_checkbox"><input
									type="checkbox" name="manage" value="manage"></td>
								<td class="svn_group_table_tr_td auth_group_name"><input
									class="group_name_input" type="text" name="manage" value="manage"
									onblur="checkNull(this)"
									onclick="showUser(this,'user_group_table_id',groups)"></td>
							</tr>
							<tr></tr>
						</table>
					</div>
					</form>
				</td>
				<td class="auth_group_table_td group_table_td"  colspan="2">
					<!-- 用户 -->
					<div id="user_div_id" class="user_div">
					<table class="user_group_table">
						<!-- <tr class="user_group_table_tr">
							<td class="user_group_table_tr_td auth_checkbox">
								<input type="checkbox" name="" value=""></td>
							<td class="user_group_table_tr_td user_group_name">
								<input class="user_name_input" type="text" name="" value=""></td>
							<td class="user_group_table_tr_td user_group_detail">
							
							</td>
						</tr>
						<tr></tr> -->
					</table>
					</div>
				</td>
			</tr>
			<!-- <tr><td></td></tr> -->
		</table>
		
	</td><td>
		<!-- 目录权限管理 -->
		<table class="auth_group_table" style="">
			<tr class="auth_group_table_tool">
				<td colspan="100%">
					<div class="auth_group_div_tool">
						<input type="button" name="addGroup" value="增加目录" onclick="addDir('svn_dir_table_id','group_div_id')">
						<input type="button" name="deleteGroup" value="删除目录" onclick="delDir('svn_dir_table_id','groupName')">
						<input type="button" name="addUser" value="增加用户组" onclick="addDirGroup('group_div_id')">
						<input type="button" name="deleteUser" value="删除用户组" onclick="delDirGroup('group_div_id')">
						<input type="button" name="saveGroup" value="保存" onclick="saveDirGroup('dir_form_id',authPath)">
					</div>
				</td>
			</tr>
			<tr class="auth_group_table_head">
				<td class="auth_group_table_td auth_checkbox"><input type="checkbox"></td>
				<td class="auth_group_table_td auth_dir_name" style="width: 150px;">目录</td>
				<td class="auth_group_table_td" style="width:120px;border-right: 0px;">用户组</td>
				<td class="auth_group_table_td" style="border-left: 0px;">权限</td>
			</tr>
			<tr class="auth_group_table_tr">
				<td class="auth_group_table_td group_table_td" colspan="2">
					<!-- 目录 -->
					<form name="dir_form" id="dir_form_id">
					<div class="svn_group_div" id="svn_dir_div_id">
						<table class="svn_group_table" id="svn_dir_table_id">
							<tr class="svn_group_table_tr">
								<td class="svn_group_table_tr_td auth_checkbox"><input
									type="checkbox" name="manage" value="manage"></td>
								<td class="svn_group_table_tr_td auth_group_name"><input
									class="group_name_input" type="text" name="manage" value="/myResport:"
									onblur="checkNull(this)"
									onclick="showUser(this,'user_group_table_id',groups)"></td>
							</tr>
							<tr></tr>
						</table>
					</div>
					</form>
				</td>
				<td class="auth_group_table_td group_table_td"  rowspan="1" colspan="2">
					<!-- 用户组 -->
					<div id="group_div_id" class="user_div">
					<table class="user_group_table" id="user_group_table_id">
						<!-- <tr class="user_group_table_tr">
							<td class="user_group_table_tr_td auth_checkbox">
								<input type="checkbox" name="" value=""></td>
							<td class="user_group_table_tr_td user_group_name">
								<input class="user_name_input" type="text" name="" value="manage">
								<select>
									<option></option>
									<option></option>
								</select>
							</td>
							<td class="user_group_table_tr_td auth_checkbox">
								<input type="checkbox" name="r" value="">
							</td>
							<td class="user_group_table_tr_td user_group_td">r</td>
							<td class="user_group_table_tr_td auth_checkbox">
								<input type="checkbox" name="w" value="">
							</td>
							<td class="user_group_table_tr_td user_group_td">w</td>
								
						</tr>
						<tr></tr> -->
					</table>
					</div>
				</td>
			</tr>
		</table>
		
	</td></tr>
	</table>
</div>


	<br>
<textarea id="auth_info_id" class="auth_info_textarea" rows="" cols=""></textarea>


</body>
</html>