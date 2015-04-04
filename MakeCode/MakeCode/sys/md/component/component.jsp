<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
 String baseUrl = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />

<link href="<%=baseUrl%>/js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=baseUrl%>/js/SlickGrid/slick.grid.css" type="text/css"/>
<link rel="stylesheet" href="<%=baseUrl%>/js/SlickGrid/css/smoothness/jquery-ui-1.8.16.custom.css" type="text/css"/>

<script type="text/javascript" src="<%=baseUrl%>/js/jquery/jquery-1.10.2.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/ligerUI/js/core/base.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/ligerUI/js/ligerui.all.js" ></script>

<script type="text/javascript" src="<%=baseUrl%>/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/js/zTree_v3/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/js/zTree_v3/jquery.ztree.exhide-3.5.js"></script>

<script type="text/javascript" src="<%=baseUrl%>/js/common.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/app/treecard.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/app/grid.js" ></script>

<script src="<%=baseUrl%>/js/SlickGrid/lib/jquery.event.drag-2.2.js"></script>
<script src="<%=baseUrl%>/js/SlickGrid/slick.core.js"></script>
<script src="<%=baseUrl%>/js/SlickGrid/slick.grid.js"></script>

<script type="text/javascript">
var baseUrl = '<%=baseUrl%>';
var baseAction = baseUrl+'/ComcategoryAction';
var treeAction = baseAction+'_queryChildren';
var queryAction = baseAction+'_queryByCode';
var treeId = 'treeRootNode';
var formID = 'form1';

var componentAction = baseUrl+'/ComponentAction';

jQuery(document).ready(function(){
	jQuery("#layout1").ligerLayout({ leftWidth: 230,topHeight : 30});
	//jQuery("form").ligerForm();
	initButtonStatus();
	clearCard();
	var setting = {  
		view: {  selectedMulti: false },  //,fontCss: setFontCss 
		async: {  
			enable: true,  url: treeAction+"?rk="+Math.random(),type:"GET",
			autoParam:["id=parentCode", "name", "level"]
			,async : false,  
			dataFilter: filter
		},  
		callback: {  beforeClick: beforeClickZtree  ,onAsyncSuccess: zTreeOnAsyncSuccess }  
	}; 
	var zNodes = [{
		"checked": false,"hasChild": true,"id": "root",
		"isParent": true,"name": "组件分类","open": true
	}];
	$.fn.zTree.init($("#"+treeId), setting, zNodes); 
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var nodes = treeObj.getNodes();
	if (nodes.length>0) {
		for(var i in nodes){
			treeObj.expandNode(nodes[i], true, true, true);
		}
		//nodes[0].checked = true;
		treeObj.selectNode(nodes[0],true);
	}
	
	initGrid(componentAction+'_query',null);
});



//单击事件  
function beforeClickZtree(treeId, treeNode){
	if(treeNode.uk==null){
		initGrid(componentAction+'_query');
	}else{
		var condition = " comcategoryid='"+treeNode.uk+"'";
		initGrid(componentAction+'_query',condition);
	}
	
}

function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
	freshCurrentNode = treeNode;
}

function queryDataByCode(code){
	var param = {code : code};
	var datas = ajaxData(queryAction,param);
	var data = datas[0];
	return data;
}

function fresh(){
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	freshTreeNode(treeObj,"fresh");
}

function savecard(){
	var formObj = jQuery('#'+formID);
	var formdata = getCardData(formObj[0]);
	//formdata.id = formdata.pk;
	var dataStr = JsonToString(formdata);
	var data = {data : dataStr};
	var urls = baseAction+'_modifyCategory';
	if(formdata.id == null||formdata.id==""){
		urls = baseAction+'_addCategory';
	}
	
	var callback = function(info){
		$.ligerDialog.success(info);
		if(info=="新增成功"||info=="修改成功"){
			setScanStatus();
		}
		
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		if(formdata.id == null||formdata.id==""){
			freshTreeNode(treeObj,"addSave");
		}else{
			freshTreeNode(treeObj,"editSave");
		}
	}
	
	ajaxSave(urls,data,false,callback);
	
	
}


function add(){
	
	var selectNode = getSelectNode()[0];
	var uk = "";
	if(selectNode != null&&selectNode.uk!=null){
		uk = selectNode.uk;
	}
	window.location.href = "componentInsert.jsp?comcategoryid="+uk;
	
	
}

function edit(){
	var id = jQuery();
	window.location.href = "componentInsert.jsp?id="+id;
	
}
function delcard(){
	var selectNode = getSelectNode()[0];
	var data = {id : selectNode.uk};
	var urls = baseAction+'_deleteByPk';
	var info = ajaxSave(urls,data);
	$.ligerDialog.success(info);
	if(info=="删除成功"){
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		freshTreeNode(treeObj,"delete");
	}
}
function cancel(){
	setScanStatus();
	clearCard();
	var selectNode = getSelectNode()[0];
	var param = {code : selectNode.id};
	var datas = ajaxData(queryAction,param);
	var data = datas[0];
	loadCard(data,formID);
}
</script>
<style type="text/css">
body {
	padding: 5px;
	margin: 0;
	padding-bottom: 15px;
}
.l-table-edit-td{ padding:4px;}
.l-table-edit-td{ padding:4px;}
.l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
.l-verify-tip{ left:230px; top:120px;}
.buttonDisable{display:none;}	
.inputDisable{backgroud-color : #FFFFFF;}	
</style>
</head>
<body>
	<div id="layout1">
	 	<div position="top" style="height:30px;vertical-align: bottom;padding: 5px;">
			<input type="button" name="add" value="新增" onclick="add()">
			<input type="button" name="modify" value="修改" onclick="edit()">
			<input type="button" name="delete" value="删除" onclick="delcard()">
			<input type="button" name="save" value="保存" onclick="savecard()">
			<input type="button" name="save" value="取消" onclick="cancel()">
			<input type="button" name="fresh" value="刷新" onclick="fresh()">
		</div>
		<div position="left">
			<div id="treeNodeContain" class="zTreeDemoBackground left"  style="overflow:auto ;width: 225px; height: 440px;margin:0px;padding-left: 10px;">
				<ul	id="treeRootNode" class="ztree" style="height: 400px;width: 200px;">
					
				</ul>
			</div>
		</div>
		<div position="center" title="组件分类信息">
			<div id="myGrid" style="width:100%;height:500px;"></div>
				
			
		</div>
	</div>

</body>
</html>