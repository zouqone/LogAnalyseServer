<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.js"></script>

<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
var reportAction = baseUrl+"/ReportAction_exampleReport";

function report(){
	var iframeObj = jQuery("#report_id");
	$.ajax({  
    	url: reportAction, type: "POST",data: {},
        success: function(data) {     
        	iframeObj.attr("src",baseUrl+data);
        	//iframeObj.attr("src","test.html");
        }     
    }); 
}

function showReport(){
	var type = jQuery('#reportTypeID').val();
	var url = baseUrl+"/app/report/jasper.jsp?type="+type;
	var iframeObj = jQuery("#report_id");
	iframeObj.attr("src",url);
}

function browseReport(){
	var type = "print";
	var url = baseUrl+"/app/report/jasper.jsp?type="+type;
	var iframeObj = jQuery("#report_id");
	iframeObj.attr("src",url);
}
</script>
</head>
<body>
<select name="reportType" id="reportTypeID">
	<option value="html">html</option>
	<option value="pdf">pdf</option>
	<option value="xls">xls</option>
	<option value="word">word</option>
</select>
	<div>
		<input type="button" value="后台生成报表" onclick="report()">
		<input type="button" value="前台生成报表" onclick="showReport()">
		<input type="button" value="预览报表" onclick="browseReport()">
	</div>
	<div>
		<iframe src="" id="report_id"
			style="width: 1000px; height: 1000px; border: 0px;"> </iframe>
	</div>
</body>
</html>