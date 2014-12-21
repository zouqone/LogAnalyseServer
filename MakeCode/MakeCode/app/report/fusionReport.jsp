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
				"caption" : "FusionChart 3.2.3"
				,"xAxisName" : "功能"
				,"yAxisName" : "子节点数目"
				,"showLegend" : "1"
				,"showBorder" : "1"
			},
			"data" : datas
	};
	var swfPath = baseUrl + "/js/fusionchart-3.2.3/swf/Doughnut3D.swf";
	
	var chart = new FusionCharts(swfPath,"myChartId","400","300");
	chart.setJSONData(jsonData);
	chart.render('container');
}


function showChart(){
	var demo = {
		    "chart": {
		        "caption": "Age profile of website visitors",
		        "subcaption": "Last Year",
		        "xAxisName" : "功能","yAxisName" : "子节点数目",
				rotateYAxisName : 0,
				rotateNames : 0,
				
				"showlegend": "1",
				legendBorderColor : '000000',
				
		        "showlabels": "1",
				showYAxisValues : 1,
				numberPrefix : 'line-',
				numberSuffix : 'L',
				formatNumberScale : 1,
				labelDisplay : 'WRAP', //WRAP （重叠） ROTATE （旋转） Stagger （交错）
		        
				labelStep : 1,
				yAxisValuesStep : 2,

		        "bgColor" : "AEC0CA,FFFFFF","bgAlpha" : 0, borderAlpha : 10,
		        canvasbgColor : "A3C0CA,F0FFFF",canvasbgAlpha : 20,
		        canvasBorderColor : "BCCB00",canvasBorderThickness : 4,
		        canvasBorderAlpha : 20,
		        borderColor : "BB00BB",
		        borderThickness : '3',
				
				baseFont:'helvetica',
				baseFontSize : 14,
				baseFontColor: '932EE1',
				
				divLineThickness: 1,
				divLineIsDashed : 1,
				showAlternateHGridColor : 1,
				
				vDivLineColor : '88DDAA',
				
				dashed : 1,
				color : '33aaee',
				
				showToolTip : 1,
				toolText : 'www',
				toolTipBorderColor : 'FB11DD',
				
				outCnvbaseFontColor : 'FB11DD',
				outCnvbaseFontSize: 12,
				outCnvbaseFont : 'Arial'
		        
				
		        
		    },
		    "data": [
		        {
		            "label": "Teenage",
		            "value": "1250400"
		        },
		        {
		            "label": "Adult",
		            "value": "1463300"
		        },
		        {
		            "label": "Mid-age",
		            "value": "1050700"
		        },
		        {
		            "label": "Senior",
		            "value": "491000"
		        }
		    ],
		    styles :{
		    	definition:[{
		    		"name" : "MyFirstFontStyle",
					"type" : "FONT",
					"font" : "Verdana",
					"size" : "12",
					"color" : "ff0000",
					"bold" : "1",
					"bgcolor" : "ffffff"
			    	},{
			    		"name" : "MyFirstAnimationStyle",
						"type" : "animation",
						"start" : "0",
						"duration" : "2",
						"param" : "_xscale",
						"size" : "24",
						"color" : "#FFFFFF"
			    	},{
			    		"name" : "DataShadow",
						"type" : "Shadow",
						"alpha" : "40"
			    	
			    }],
		    	application:[
		    		{
		    			"toobject" : "Caption",
						"styles" : "MyFirstFontStyle"
		    		},{
		    			"toobject" : "xAxisName",
						"styles" : "DataShadow"
		    		}
		    	
		    ]
		    }
		}
	
	swfPath = baseUrl + "/js/fusionchart-3.2.3/swf/Line.swf";
	var chart2 = new FusionCharts(swfPath,"myChartId2","400","300",0,0);
	chart2.setJSONData(demo);
	chart2.render('container2');
}
jQuery(document).ready(function(){
	//initMyZtree();
	showChart();
});

</script>
</head>
<body>

	<div>
		<input type="button" value="生成报表" onclick="showChart()">
		<input type="button" value="生成图表" onclick="initMyZtree()">
	</div>
	<div id="container2" style="min-width: 310px; height: auto; max-width: 600px; margin: 0 auto"></div>
	<div id="container" style="min-width: 310px; height: auto; max-width: 600px; margin: 0 auto"></div>
	<div id="container3" style="min-width: 310px; height: auto; max-width: 600px; margin: 0 auto"></div>
	
	<div>
		<iframe src="" id="report_id"
			style="width: 1000px; height: 1000px; border: 0px;"> </iframe>
	</div>
</body>
</html>