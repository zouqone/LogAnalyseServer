<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简单拖拽</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/app/drag/dragTest.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/app/table.css" type="text/css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/app/drag/dragTest.js"></script>

<script type="text/javascript">
jQuery(document).ready(function(){
	var s= '1';
	var move = initMoveHandle('move','imgarea');
	move.bindDom();
});
</script>

</head>
<body>
	<div style="margin: 0px;width: 700px;">
		<center><h2>简单拖拽</h2></center>
		<table class="simple_table" style="margin: 10px;width: 700px;height: 400px;">
			<tr style="height: 40px;">
				<td colspan="100%" style="padding-left: 3px;">
					<input type="button" value="拖拽" >
				</td>
			</tr>
			<tr class="table_border" style="vertical-align: top;">
				<td class="table_border" width="width:50%;">
					<div class="imgarea table_border"  name="imgarea"  style="width: 100%;height: 400px;">
						<div id="demo1" name="move" class="imgdivcss" style="">
						<img class="imgcss" src="images/Desert.jpg"></div>
						<div id="demo2" name="move" class="imgdivcss" style="">
						<img class="imgcss" src="images/Tulips.jpg"></div>
						<div id="demo3" name="move" class="imgdivcss" style="">
						<img class="imgcss" src="images/Penguins.jpg"></div>
				
					</div>
				</td>
				<td class="data_table_td"  style="width: 50px;"></td>
				<td  class="data_table_td" id="rightarea" style="width: 50%;">
					<div class="imgarea table_border"  name="imgarea"  style="width: 100%;height: 400px;">
										
					</div>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>