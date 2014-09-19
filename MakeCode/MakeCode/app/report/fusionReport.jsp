<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fusionchart-3.2.3/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fusionchart-3.2.3/FusionCharts.jqueryplugin.js"></script>


<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
var reportAction = baseUrl+"/ReportAction_exampleReport";
var date = new　Date();
var dateStr = DateFormat(date,'yyyy-MM-dd');

var baseUrl = '<%=request.getContextPath()%>';
var treeAction = baseUrl+"/FunctionAction_getCheckTreeNodeByParentCode";

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

function initMyZtree(){ 
	var level1 = queryData();
	var datas = [];
	for( var i in level1){
		var node  = level1[i];
		var id = node.id;
		var hasChild = node.hasChild;
		if(hasChild == true){
			var level2 = queryData(id);
			node.count = level2.length;
		}else{
			node.count = 0;
		}
		var data = {"label":node.name,"value":node.count};
		datas.push(data);
	}
	showPie(datas);
	
}
function queryData(pCode){
	var zNodes="";
	pCode = pCode == null?'rootdir':pCode;
    $.ajax({  
    	url: treeAction, async : false,type: "POST",data:{pCode:pCode},
        success: function(data) {     
            //alert(data);
            zNodes=eval(data);  
        }     
    }); 
    return zNodes;
}
function showPie(datas){
	var jsonData = {
			"chart" : {
				"caption" : "FusionChart 3.2.3",
				"xAxisName" : "功能",
				"yAxisName" : "子节点数目"
			},
			"data" : datas
	};
	var swfPath = baseUrl + "/js/fusionchart-3.2.3/swf/Doughnut3D.swf";
	
	var chart = new FusionCharts(swfPath,"myChartId","800","300");
	chart.setJSONData(jsonData);
	chart.render('container');
}

jQuery(document).ready(function(){
	initMyZtree();
});

</script>
</head>
<body>

	<div>
		<input type="button" value="生成报表" onclick="report()">
		<input type="button" value="生成图表" onclick="initMyZtree()">
	</div>
	<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
	
	<div>
		<iframe src="" id="report_id"
			style="width: 1000px; height: 1000px; border: 0px;"> </iframe>
	</div>
</body>
</html>