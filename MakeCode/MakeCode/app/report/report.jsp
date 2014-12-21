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
var report = 'bigdata';

var params = {spos:1,epos:30};
var paramStr = JsonToString(params);
paramStr = '{"spos":1,"epos":30}';


function printReport(){
	var type = "print";
	setParams();
	var url = baseUrl+"/app/report/jasper.jsp?type="+type+'&params='+paramStr+'&report='+report;
	var iframeObj = jQuery("#report_print");
	iframeObj.attr("src",url);
}

function browseReport(type){
	if(type==null){
		type = "html";
	}
	setParams();
	var url = baseUrl+"/app/report/jasper.jsp?type="+type+'&params='+paramStr+'&report='+report;
	var iframeObj = jQuery("#report_id");
	iframeObj.attr("src",url);
}

function setParams(){
	var spos = paramForm.spos.value;
	var epos = paramForm.epos.value;
	paramStr = '{'
		+'"spos":'+(spos==null?0:spos)
		+',"epos":'+(epos==null?30:epos)
		+'}';
	return paramStr;
}

function setIframePos(){
	var e = jQuery("#report_id")[0];
	var top = getAbsPoint(e).y;
	var bh = getPageSize().y;
	var bt = getPageScrollSize().y;
	e.height = bh+bt-top -10 ;
}
window.onresize = function(){
	setIframePos();
}
jQuery(document).ready(function(){
	browseReport();
	setIframePos();
});
</script>
<style type="text/css">

</style>
</head>
<body>
	<div>
		<input type="button" value="预览报表" onclick="printReport()">
		<input type="button" value="导出word" onclick="browseReport('doc')">
		<input type="button" value="导出Excel" onclick="browseReport('xls')">
		<input type="button" value="导出PDF" onclick="browseReport('pdf')">
		<input type="button" value="后台生成报表" onclick="report()">
	</div>
	<div>
		<form name="paramForm">
		<table>
			<tr>
				<td>起始位置：</td><td><input name='spos' value="1"></td>
				<td>结束位置：</td><td><input name='epos' value="30"></td>
				<td><input type="button" value="查询" onclick="browseReport()"></td>
			</tr>
		</table>
		</form>
	</div>
	<div>
		<iframe src="" id="report_id"
			style="width: 100%; border: 1px solid #BBBBBB;"> </iframe>
			
		<iframe src="" id="report_print"
			style="display: none;"> </iframe>
	</div>
</body>
</html>