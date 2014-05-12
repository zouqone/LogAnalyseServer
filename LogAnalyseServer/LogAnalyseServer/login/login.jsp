<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<title>login</title>

<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
function initPage(){
	initLoginBox();
}

//当浏览器变化时，也调整iframe的高度使它适应当前窗口的高度
window.onresize = function(){ 
	initLoginBox();
}

function initLoginBox(){ //login_box_id page_div_id show_clock
	var login_box_obj = document.getElementById("login_box_id");
	setObjMiddleX(login_box_obj);
	setObjMiddleY(login_box_obj);
	
	var show_clock = document.getElementById("show_clock");
	var bgHeight=document.documentElement.clientHeight || document.body.clientHeight || 0;
	var bgWidth=document.documentElement.clientWidth || document.body.clientWidth || 0;   
	show_clock.style.height = bgHeight +"px"; 
	show_clock.style.width=bgWidth +"px";  
	show_clock.style.position = "absolute";  
}

function logins(){
	var Login_Form = document.getElementById("Login_Form_id");
	Login_Form.action=baseUrl+"/UserAction_login";
	Login_Form.submit();
	//var s= 1;
}

function close(){
	
}


</script>

</head>
<body onload="initPage()">
<div id="show_clock" class="show_clock_shade"></div>
<div class="login_div" id="login_box_id">
	<br>
	<center><h3>登录svn日志分析系统</h3></center>
	<form action="" name="user" id="Login_Form_id" method="post"> 
		<table class="login_table">
			<tr height="20px;"><td colspan="2"></td></tr>
			<tr><td align="right">用户名</td><td><input class="input_text" type="text" name="username" value="admin"></td></tr>
			<tr><td align="right">密码</td><td><input class="input_text" type="password" name="password" value="adminpw"></td></tr>
			<tr>
				<td colspan="2" align="center">
				<input class="input_button" name="login" type="button" value="登录" onclick="logins()">
				<input class="input_button" name="close" type="button" value="关闭" onclick="close()">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>