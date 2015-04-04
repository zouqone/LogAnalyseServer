<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
 String baseUrl = request.getContextPath();
 String comcategoryid = request.getParameter("comcategoryid");
 comcategoryid = comcategoryid == null ? "" : comcategoryid;
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=baseUrl%>/js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=baseUrl%>/js/SlickGrid/slick.grid.css" type="text/css"/>
<link rel="stylesheet" href="<%=baseUrl%>/js/SlickGrid/css/smoothness/jquery-ui-1.8.16.custom.css" type="text/css"/>

<script type="text/javascript" src="<%=baseUrl%>/js/jquery/jquery-1.10.2.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/ligerUI/js/core/base.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/ligerUI/js/ligerui.all.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/common.js" ></script>

<title>表格</title>
<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
var comcategoryid = '<%=comcategoryid%>';

var componentAction = baseUrl+'/ComponentAction';

jQuery(document).ready(function(){
	jQuery('[name="comcategoryid"]').val(comcategoryid);
	jQuery("form").ligerForm();
});

function save(){
	var formObj = jQuery('#form1')[0];
	formObj.action = componentAction+"_insert"
	formObj.submit();
}
function cancel(){
	window.location.href = "component.jsp";
	
}

</script>

    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
	
</head>
<body >
	<div>
		
		<form name="form1" method="post" id="form1">
			<div></div>
			
			<table cellpadding="0" cellspacing="0" class="l-table-edit">
				<tr>
					<td align="right" class="l-table-edit-td">组件id:</td>
					<td align="left" class="l-table-edit-td"><input name="pk"
						type="text" id="txtName1" ltype="text" />
					</td>
					<td align="right" class="l-table-edit-td">组件分类id:</td>
					<td align="left" class="l-table-edit-td"><input name="comcategoryid"
						type="text" id="txtName2" ltype="text" />
					</td>
					<td align="left"></td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">编码</td>
					<td align="left" class="l-table-edit-td"><input name="code"
						type="text" id="txtName3" ltype="text" />
					</td>
					<td align="right" class="l-table-edit-td">名称</td>
					<td align="left" class="l-table-edit-td"><input name="name"
						type="text" id="txtName4" ltype="text" />
					</td>
					<td align="left"></td>
				</tr>
				
				<tr>
					<td align="right" class="l-table-edit-td">创建时间:</td>
						<td align="left" class="l-table-edit-td">
							<input name="createtime" type="text" id="txtDate" ltype="date" />
						</td>
					<td align="right" class="l-table-edit-td" valign="top">创建人:</td>
						<td align="left" class="l-table-edit-td">
							<input name="creator" type="text" id="txtName6" ltype="text" />
						</td>
					<td align="left"></td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">修改时间:</td>
						<td align="left" class="l-table-edit-td">
							<input name="modifytime" type="text" id="txtDate" ltype="date" />
						</td>
					<td align="right" class="l-table-edit-td" valign="top">修改人:</td>
						<td align="left" class="l-table-edit-td">
							<input name="modifer" type="text" id="txtName7" ltype="text" />
						</td>
					<td align="left"></td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">详情:</td>
					<td align="left" class="l-table-edit-td"> 
					<textarea cols="100" rows="6" name="detail" class="l-textarea" style="width:400px"></textarea>
					</td><td align="left"></td>
				</tr>
				
			</table>
			<div>
			<input type="button" value="提交" id="Button1"
				class="l-button l-button-submit" onclick="save()"/> 
			<input type="reset" value="重置"
				class="l-button l-button-reset" />
			<input type="button" value="取消"
				class="l-button l-button-reset" onclick="cancel()" />
		</div>
		</form>

	</div>

</body>
</html>