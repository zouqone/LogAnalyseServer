<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticket</title>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/app/ticket.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/ticket.js"></script>
<style type="text/css">
.menu1{
width:120px;
height:auto;
margin:6px 4px 0px 0px;
border:1px solid #9CDD75;
background-color:#F1FBEC;
color:#336601;
padding:6px 0px 0px 0px;
cursor:hand;
overflow-y:hidden;
filter:Alpha(opacity=30);
-moz-opacity:0.7;
}
.menu2{
width:120px;
height:18px;
margin:6px 4px 0px 0px;
background-color:#FFFFFF;
color:#999999;
border:1px solid #EEE8DD;
padding:6px 0px 0px 0px;
overflow-y:hidden;
cursor:hand;
}
.menuList{
	position: absolute;
}
</style>
</head>
<body>
<div class="ticket_page_div">
<div class="menuList">
	<ul>
		<li class="menu2" onMouseOver="this.className='menu1'" onMouseOut="this.className='menu2'">设置
			<div class="list">
				<a href="#">我的Png</a><br />
				<a href="#">我的Gif</a><br />
			</div>
		</li>
	</ul>
	<ul>
		<li class="menu2" onMouseOver="this.className='menu1'" onMouseOut="this.className='menu2'">设置
			<div class="list">
				<a href="#">我的Png</a><br />
				<a href="#">我的Gif</a><br />
			</div>
		</li>
	</ul>
</div>
		<table class="ticket_table">
			<tr class="ticket_tr ticket_title">
				<td class="ticket_td ticket_button">
					<input class="ticket_input_button" type="button" value="查询" onclick="getTickInfo('ticket_info_id','ticket_textarea_id')">
				</td>
				<td class="ticket_td">
					<input class="ticket_input_text" type="text" value="" name="ticket_info" id="ticket_info_id">
				</td>
			</tr>
			<tr class="ticket_tr ticket_info">
				<td class="ticket_td">
					结果 
				</td>
				<td class="ticket_td ">
					<textarea id="ticket_textarea_id" class="ticket_textarea_info" rows="" cols=""></textarea>
				</td>
			</tr>
			<tr class="ticket_tr"><td></td><td></td></tr>
		</table>
		
</div>
</body>
</html>