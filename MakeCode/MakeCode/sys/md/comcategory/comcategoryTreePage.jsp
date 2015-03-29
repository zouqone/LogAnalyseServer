<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
 String baseUrl = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=baseUrl%>/js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=baseUrl%>/js/jquery/jquery-1.10.2.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/ligerUI/js/core/base.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/ligerUI/js/plugins/ligerLayout.js" ></script>

<script type="text/javascript" src="<%=baseUrl%>/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/js/zTree_v3/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/js/zTree_v3/jquery.ztree.exhide-3.5.js"></script>

<script type="text/javascript" src="<%=baseUrl%>/js/common.js" ></script>

<script type="text/javascript">
var baseUrl = '<%=baseUrl%>';
var url = baseUrl+'/ComcategoryAction';
function addFunc(){
	ComForm.action = url+'_insert';
	ComForm.submit();
}

jQuery(function(){
	$("#layout1").ligerLayout({ leftWidth: 200});
	
});


</script>
<style type="text/css">
body {
	padding: 5px;
	margin: 0;
	padding-bottom: 15px;
}

</style>
</head>
<body>
	<div id="layout1">
	 	<div position="top" style="">工具栏</div>
		<div position="left">
			
		</div>
		<div position="center" title="标题">
			<h4>$("#layout1").ligerLayout({ leftWidth: 200});</h4>
			<br /> 如果上面有其他页面元素，layout会自适应调整

		</div>
	</div>

</body>
</html>