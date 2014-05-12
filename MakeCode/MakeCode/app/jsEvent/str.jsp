<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>String</title>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/listMenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<style type="text/css">
.table_s{border:1px solid #BBBBBB;width: 800px;height: 500px;border-collapse:collapse;}
.table_s tr{}
.table_s tr td {border:1px solid #BBBBBB;}
.tr1{border:1px solid #BBBBBB;height: 50px;}
.tr1 input {height: 24px;}
</style>
<script type="text/javascript">
function format(){
	var str = jQuery("str1").val();
	var str = DateFormat(date , "");
}
</script>
</head>
<body>
<div style="margin: 5px;">
	<table class="table_s" style="">
		<tr class="tr1">
			<td>date</td>
			<td width="200px;">
			From <input id="str1" type="text"> To  &nbsp;&nbsp;&nbsp;&nbsp;
			   <input id="str2" type="text"> </td>
			<td><input type="button" value="format"></td>
		</tr>
		<tr class="tr1"></tr>
		<tr class="tr1"></tr>
		<tr class="tr1"></tr>
		<tr></tr>
	</table>
</div>
</body>
</html>