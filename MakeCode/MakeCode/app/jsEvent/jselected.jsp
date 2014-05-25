<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery Checked</title>

<link href="<%=request.getContextPath()%>/css/ui-lightness/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript">
function getselected(){
	var ck = jQuery("#ckid");
	var status = ck.attr("checked");
	jQuery("#showchecked").val(status);
}
function demo(){   alert($("#demo").attr("checked")=="checked"); }
</script>
</head>
<body>


<input type="button" value="获取状态" onclick="getselected();">
<input type="checkbox" id="ckid">
<input type="text" id="showchecked">
<input type="button" value="判断" onclick="demo();"/><input type="checkbox" id="demo"/>
</body>
</html>