<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	//String username = (String)request.getAttribute("username");
	String authUserName = (String)session.getAttribute("authUserName");
	String auth = (String)session.getAttribute("auth");
	int res = 0;
	if(authUserName!=null&&!authUserName.equals("")&&"1".equals(auth)){
		res = 1;
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>logAnalyse</title>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/listMenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/index.js"></script>
<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
var res = '<%=res%>';
if(res==0){
	window.location.href = baseUrl + "/login/login.jsp";
}
var data = [
            {name:"nginx",url:"nginx"},
            {name:"apache",url:"apache"}
];
var user_data = [
{name:"user",code:"user",url:"/login/user/userList.jsp"}
,{name:"test",code:"test",url:"/test/test.jsp"}
,{name:"table",code:"table",url:"/app/table/table.jsp"}
,{name:"js",code:"js",url:"/app/jsEvent/str.jsp"}
,{name:"winbox",code:"winbox",url:"/app/winBox/openWin.jsp"}
];
var svn_data = [
{name:"auth",code:"auth",url:"/app/svn/auth.jsp"}
];
function initPage(data){
	if(data==null||data.length==0){
		return ;
	}
	var html = '';
	if("admin"=='<%=authUserName%>'){
		var userPage = showUserMode(user_data);
		html =userPage+html;
		var toolsObj = document.getElementById("tool_left_td_id");
		toolsObj.innerHTML = 
			'<a class="tool_a"  onclick="setItem(this,\'log_item_id\',\'user\',user_data)">用户管理</a>\r\n'
			+toolsObj.innerHTML;
	}else{
		html += showLogMode(data);
	}

	var log_item_obj = document.getElementById("log_item_id");
	log_item_obj.innerHTML=html;
	setPageHeight();
}

</script>
</head>
<body onload="initPage(data)">

<!-- <table><tr><td><div id="demo"></div></td></tr></table> -->
<div class="page_div" id="page_div_id">
	<h1> SVN服务器日志分析 </h1>
	<div class="tool_div">
		<table class="tool_table">
			<tr class="tool_tr">
				<td class="tool_left_td" id="tool_left_td_id">
					<a class="tool_a"  onclick="setItem(this,'log_item_id','log',data)" >日志分析</a>
					<!-- <a class="tool_a"  onclick="setItem(this,'log_item_id','ticket',data)" >车票管理</a> -->
					<a class="tool_a"  onclick="setItem(this,'log_item_id','svn',svn_data)" >svn管理</a>
				</td>
				<td class="tool_right_td">
				登陆用户：<span class="user_p"><%=authUserName%> </span>
				<a class="user_a" href="<%=request.getContextPath()%>/UserAction_logout">退出</a>
				</td>
			</tr>
		</table>
	</div>
	<div class="log_context" id="log_context_id">
		<table class="svn_table">
			<tr class="log_tr">
				<td class="log_item_td" id="log_item_id">
					<!-- <div class="log_item" name="log_nginx" onclick="showLogPage(this)" url="nginx/index.html">
						<p>nginx</p>
					</div> -->
				</td>
				<td class="log_page">
					<iframe src="" id="log_iframe"></iframe>
				</td>
			</tr>
			<!-- <tr></tr> -->
		</table>
	</div>
</div>
</body>
</html>