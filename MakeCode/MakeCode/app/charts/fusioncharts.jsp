<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/fusionchart-3.2.3/FusionCharts.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/fusionchart-3.2.3/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';

var demo = {
		  "chart": {
			    "palette": "2",
			    "rotatenames": "0",
			    "animation": "0",
			    "numdivlines": "4",
			    "caption": "Global Export Index : 2004",
			    "basefont": "Arial",
			    "basefontsize": "12",
			    "useroundedges": "1",
			    "legendborderalpha": "0"
			  },
			  "categories": [
			    {
			      "font": "Arial",
			      "fontsize": "12",
			      "category": [
			        {
			          "label": "N. America",
			          "tooltext": "North America"
			        },
			        {
			          "label": "Asia"
			        },
			        {
			          "label": "Europe"
			        },
			        {
			          "label": "Australia"
			        },
			        {
			          "label": "Africa"
			        }
			      ]
			    }
			  ],
			  "dataset": [
			    {
			      "seriesname": "Consumer Goods",
			      "color": "9ACCF6",
			      "alpha": "90",
			      "showvalues": "0",
			      "data": [
			        {
			          "value": "30"
			        },
			        {
			          "value": "26"
			        },
			        {
			          "value": "29"
			        },
			        {
			          "value": "31"
			        },
			        {
			          "value": "34"
			        }
			      ]
			    },
			    {
			      "seriesname": "Capital Goods",
			      "color": "82CF27",
			      "showvalues": "0",
			      "alpha": "90",
			      "data": [
			        {
			          "value": "27"
			        },
			        {
			          "value": "25"
			        },
			        {
			          "value": "28"
			        },
			        {
			          "value": "26"
			        },
			        {
			          "value": "10"
			        }
			      ]
			    }
			  ],
			  "trendlines": [
			    {
			      "line": [
			        {
			          "startvalue": "20",
			          "endvalue": "35",
			          "color": "8BBA00",
			          "thickness": "1",
			          "alpha": "20",
			          "showontop": "0",
			          "displayvalue": "Trend 1",
			          "istrendzone": "1"
			        }
			      ]
			    }
			  ],
			  "styles": {
			    "definition": [
			      {
			        "name": "Anim1",
			        "type": "animation",
			        "param": "_xScale",
			        "start": "0",
			        "duration": "1"
			      },
			      {
			        "name": "Anim2",
			        "type": "animation",
			        "param": "_alpha",
			        "start": "0",
			        "duration": "1"
			      },
			      {
			        "name": "myCaptionFont",
			        "type": "font",color :"DD33CC",
			        "align": "center"
			      }
			    ],
			    "application": [
			      {
			        "toobject": "TRENDLINES",
			        "styles": "Anim1, Anim2"
			      },
			      {
			        "toobject": "Caption",
			        "styles": "myCaptionFont"
			      }
			    ]
			  }
			}
	
	;
function showChart() {

	var swfPath = baseUrl + "/js/fusionchart-3.2.3/swf/MSColumn2D.swf";
	var chart2 = new FusionCharts(swfPath, "myChartId", "400", "300", 0, 0);
	chart2.setJSONData(demo);
	chart2.render('container');
}

jQuery(document).ready(function() {
	//initMyZtree();
	showChart();
});
</script>
</head>
<body>
	<div>
		<input type="button" value="生成图表" onclick="showChart()"> 
	</div>
	<div id="container"
		style="min-width: 310px; height: auto; max-width: 600px; margin: 0 auto"></div>

</body>
</html>