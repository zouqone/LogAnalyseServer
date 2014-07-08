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

<link href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/demo.css" type="text/css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/index.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
var res = '<%=res%>';
if(res==0){
	window.location.href = baseUrl + "/login/login.jsp";
}

var treeAction = baseUrl+"/FunctionAction_getFunctionByParentCode";
jQuery(document).ready(function(){
	initMyZtree();
});
  
var setting = {  
    view: {  selectedMulti: false },  //,fontCss: setFontCss 
    async: {  
        enable: true,  url: treeAction+"?rk="+Math.random(),type:"GET",
        autoParam:["id=pCode", "name", "level"],  
        dataFilter: filter
    },  
    callback: {  beforeClick: beforeClickZtree   }  
};  

//设置字体  
function setFontCss(treeId, treeNode) {  
    if(treeNode.level==0){  
        return {'font-weight':'bold','color':'red'};  
    }else if(treeNode.level==1){  
        return {'font-weight':'bold','color':'green'};  
    }else if(treeNode.level==2){  
        return {'font-weight':'bold','color':'blue'};  
    }else{  
        return {};  
    }  
}

function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	for (var i=0, l=childNodes.length; i<l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}

function initMyZtree(){  
	var zNodes="";
    $.ajax({  
    	url: treeAction+"?rk="+Math.random(), type: "GET",data:{pCode:'rootdir'},
        success: function(data) {     
            //alert(data);
            zNodes=eval(data);  
            $.fn.zTree.init($("#treeRootNode"), setting, zNodes);  
        }     
    });    
      
}  
  
//单击事件  
function beforeClickZtree(treeId, treeNode){  
    //alert(treeNode.id+","+treeNode.name);  
	var log_iframe_obj = document.getElementById("log_iframe");
	if(treeNode.file!=null && treeNode.file!=''){
		var url = baseUrl+treeNode.file;
		log_iframe_obj.setAttribute("src", url);
	}
}  

function freshFunctionNode(){
	initMyZtree();
}
</script>
</head>
<body ><!--  onload="initPage(data)"  -->

<!-- <table><tr><td><div id="demo"></div></td></tr></table> -->
<div class="page_div" id="page_div_id">
	<h1> SVN服务器日志分析 </h1>
	<div class="tool_div">
		<table class="tool_table">
			<!-- 工具栏 -->
			<tr class="tool_tr">
				<td class="tool_left_td" id="tool_left_td_id">
					<a class="tool_a"  onclick="freshFunctionNode()" title="刷新">
						<!-- <div  class="fresh_div" style="width: 24px;height: 24px;"></div> -->
						&nbsp;&nbsp;刷新
					</a>
					<!-- <a class="tool_a"  onclick="setItem(this,'log_item_id','ticket',data)" >车票管理</a> -->
					<!-- <a class="tool_a"  onclick="setItem(this,'log_item_id','svn',svn_data)" >svn管理</a> -->
				</td>
				<td class="tool_right_td" style="vertical-align: top;">
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
					<!-- 左侧功能树 -->
					<!-- <div class="log_item" name="log_nginx" onclick="showLogPage(this)" url="nginx/index.html">
						<p>nginx</p>
					</div> -->
					<div id="treeNodeContain" class="zTreeDemoBackground left"  style="overflow:auto ;width: 225px; height: 440px;margin:0px;padding-left: 15px;">
						<ul	id="treeRootNode" class="ztree" style="height: 400px;width: 200px;">
							
						</ul>
					</div>
				</td>
				<td class="log_page">
					<!-- 主页面 -->
					<iframe src="" id="log_iframe"></iframe>
				</td>
			</tr>
			<!-- <tr></tr> -->
		</table>
	</div>
</div>
</body>
</html>