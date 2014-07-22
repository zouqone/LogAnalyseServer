<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String basePath = request.getContextPath()+"";

%>
<title>PrintReport</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/app/ireport/stylesheet.css" title="Style">
<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
jQuery(document).ready(function(){
	
});

</script>
<style type="text/css">

</style>
</head>
<body>
<span class="title">JasperReports Flash Viewer</span>
<br>
<br>
<object width="600" height="400">
  <param name="movie" value="<%=basePath %>/js/flash/jasperreports-flash-4.5.0.swf"/>
  <embed src="<%=basePath %>/js/flash/jasperreports-flash-4.5.0.swf" 
    FlashVars="jrpxml=<%=basePath %>/servlets/xml4swf?&fetchSize=3" 
    width="600" height="400">
  </embed>
</object>

</body>
</html>