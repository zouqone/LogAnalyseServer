<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/app/function.css" type="text/css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
var downUrl = baseUrl+"/DBAction_down";
var getUrl = baseUrl+"/DBAction_getFiles";
var filePath = "upload";
function down(obj){
	var fileName = jQuery(obj).attr("fileName");
	var path = filePath+"/"+fileName;
	//window.location.href = downUrl+"?path="+path;
	form.action = downUrl+"?path="+path;
	form.submit();
}
function getFile(){
	$.ajax({  
    	url: getUrl+"?rk="+Math.random(), type: "POST",data:{path:filePath},
        success: function(data) {   
        	json = StringToJson(data);
        	var trObj = jQuery("#tmpzTr");
        	for(var i in json){
        		var file = json[i];
        		var newTr = trObj.clone();
        		jQuery(newTr).children()[0].html = i+1;
        		var tdName = jQuery(newTr).children()[1];
        		jQuery("a",tdName).html(file.name);
        		jQuery("a",tdName).attr("fileName",file.name);
        		var fpath = downUrl+"?path="+filePath+"/"+file.name;
        		jQuery("a",tdName).attr("href",fpath);
        		var tdSize = jQuery(newTr).children()[2];
        		jQuery("span",tdSize).html(file.size);
        		trObj.after(newTr);
        	}
        	trObj.remove();
        }
	});
}
jQuery(document).ready(function(){
	getFile();
});
</script>
<style type="text/css"></style>
</head>
<body>
<div>
<h3>文件下载</h3>
	<form action="" name="form" method="post">
		<table class="tree_table" style="width: 500px;">
			<tr class="node_tr title_tr">
				<td class="log_td" style="width: 50px;">序号</td>
				<td class="log_td" style="width: 100px;">文件名</td>
				<td class="log_td">文件大小</td>
			</tr>
			<tr class="node_tr" id="tmpzTr">
				<td class="log_td">1</td>
				<td><a href="" fileName="" onclick="down(this)">file.txt</a></td>
				<td><span>3kb</span></td>
			</tr>
		</table>
	</div>
	</form>
</body>
</html>