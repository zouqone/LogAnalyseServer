<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/demo.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/app/function.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery-1.4.4.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>

<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';

var jobAction = baseUrl+"/JobDetailAction_";
function add(){
	var url = jobAction+"addJob";
	var scheduleDiv = jQuery('#scheduleId');
	var name = jQuery('[name="name"]',scheduleDiv).val();
	var group = jQuery('[name="group"]',scheduleDiv).val();
	var jobClass = jQuery('[name="jobClass"]',scheduleDiv).val();
	var data = {name : name , group : group ,jobClass : jobClass};
	$.ajax({  
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
    	url: url, type: "POST",data:data,
        success: function(data) {    
        	if(data!=null&&data!=''){
        		alert("新增成功 ID = "+data);
        	}else{
        		alert("新增失败");
        	}
        }     
    });
	
}

function query(){
	var url = jobAction+"query";
	var data = {};
	$.ajax({  
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
    	url: url, type: "POST",data:data,
        success: function(data) {    
        	if(data!=null&&data!=''){
        		//alert(""+data);
				var datas = eval("("+data+")");
				loadJobs(datas);
        	}else{
        		alert("查询失败");
        	}
        }     
    });
}

function loadJobs(datas){
	if(datas == null){return ;}
	var showArea = jQuery('#showId');
	var template = jQuery('[name="template"]',showArea);
	for(var i = 0 ; i < datas.length; i++){
		var data = datas[i];
		var tr = template.clone();
		tr.attr("name","tr"+i);
		jQuery('[name="rowno"]',tr).val(i+1);
		tr.attr("class","log_tr");
		template.before(tr);
		var elemets = jQuery('[mtype="data"]',tr);
		for(var j=0 ; j < elemets.length ; j++){
			var elemet = elemets[j];
			var name = jQuery(elemet).attr('name');
			var value = data[name];
			jQuery(elemet).val(value);
		}
	}
}
</script>
<style type="text/css">
.templatecss{display: none;}
.showTable{border: 1px solid #BBBBB;}
.bordcss{border: 1px solid #BBBBB;}
.showTable input{width:99%;border:0px;}
</style>
</head>
<body>
<input type="button" value="新增任务" onclick="add()">

<div id="scheduleId">
	<table class=" bordcss tree_table">
		<tr class="log_tr tool_tr">
			<td>任务名称</td>
			<td><input type="text" name="name"  value="test"></td>
		</tr>
		<tr class="log_tr tool_tr">
			<td>分组名称</td>
			<td><input type="text" name="group" value="group"></td>
		</tr>
		<tr class="log_tr tool_tr">
			<td>执行Java类</td>
			<td><input type="text" name="jobClass" value="cn.log.app.schedule.task.TestJob"></td>
		</tr>
	</table>
</div>
<br><br>
<input type="button" value="查询任务" onclick="query()">
<div id="showId">
	<table class="showTable bordcss tree_table" style="width:550px;">
		<tr style="text-align: center;" class="log_tr tool_tr">
			<td style="width:50px;">序号</td>
			<td style="width:100px;">任务名称</td>
			<td style="width:100px;">分组名称</td>
			<td style="width:300px;">执行Java类</td>
		</tr>
		
		<tr name="template" class="templatecss">
			<td ><input type="text" style="text-align: center;" name="rowno" value=""></td>
			<td ><input type="text" mtype="data" name="name" value=""></div>
			<td ><input type="text" mtype="data" name="group" value=""></div>
			<td ><input type="text" mtype="data" name="jobClass" value=""></div>
		</tr>
		
	</table>
</div>	
</body>
</html>