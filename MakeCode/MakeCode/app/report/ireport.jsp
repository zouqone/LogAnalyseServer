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

function printReport(){
	var type = "print";
	var url = baseUrl+"/app/report/jasper.jsp?type="+type;
	var iframeObj = jQuery("#report_print");
	iframeObj.attr("src",url);
}

function browseReport(type){
	if(type==null){
		type = "html";
	}
	var url = baseUrl+"/app/report/jasper.jsp?type="+type;
	var iframeObj = jQuery("#report_id");
	iframeObj.attr("src",url);
}

jQuery(document).ready(function(){
	browseReport();
});
</script>
</head>
<body>
	<div>
		<input type="button" value="刷新" onclick="browseReport()">
		<input type="button" value="预览报表" onclick="printReport()">
		<input type="button" value="导出word" onclick="browseReport('doc')">
		<input type="button" value="导出Excel" onclick="browseReport('xls')">
		<input type="button" value="导出PDF" onclick="browseReport('pdf')">
		<input type="button" value="后台生成报表" onclick="report()">
	</div>
	<div>
		<iframe src="" id="report_id"
			style="width: 100%; height: 400px; border: 0px;"> </iframe>
		<iframe src="" id="report_print"
			style="display: none;"> </iframe>
	</div>
</body>
</html>