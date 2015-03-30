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

<script type="text/javascript" src="<%=baseUrl%>/js/jquery/jquery-1.10.2.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/ligerUI/js/core/base.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/ligerUI/js/ligerui.all.js" ></script>

<script type="text/javascript" src="<%=baseUrl%>/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/js/zTree_v3/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=baseUrl%>/js/zTree_v3/jquery.ztree.exhide-3.5.js"></script>

<script type="text/javascript" src="<%=baseUrl%>/js/common.js" ></script>
<script type="text/javascript" src="<%=baseUrl%>/js/app/treecard.js" ></script>

<script type="text/javascript">
var baseUrl = '<%=baseUrl%>';
var baseAction = baseUrl+'/ComcategoryAction';
var treeAction = baseAction+'_queryChildren';
var queryAction = baseAction+'_queryByCode';
var treeId = 'treeRootNode';
var formID = 'form1';

jQuery(document).ready(function(){
	jQuery("#layout1").ligerLayout({ leftWidth: 230,topHeight : 30});
	jQuery("form").ligerForm();
	initButtonStatus();
	clearCard();
	var setting = {  
		view: {  selectedMulti: false },  //,fontCss: setFontCss 
		async: {  
			enable: true,  url: treeAction+"?rk="+Math.random(),type:"GET",
			autoParam:["id=parentCode", "name", "level"],  
			dataFilter: filter
		},  
		callback: {  beforeClick: beforeClickZtree   }  
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
});



//单击事件  
function beforeClickZtree(treeId, treeNode){ 
	var saveInput = jQuery('input[name="save"]');
	if(saveInput.hasClass('buttonDisable')){
		var param = {code : treeNode.id};
		var datas = ajaxData(queryAction,param);
		var data = datas[0];
		loadCard(data,formID);
	}else{
		return false;
	}
	
}

function fresh(){
	
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
	var info = ajaxSave(urls,data);
	$.ligerDialog.success(info);
	if(info=="新增成功"){
		setScanStatus();
	}
	
}

function add(){
	setEditStatus();
	clearCard();
	var selectNode = getSelectNode()[0];
	var parentCode = "root";
	if(selectNode != null){
		parentCode = selectNode.id;
	}
	var data = {parentcode : parentCode};
	loadCard(data,formID);
}

function edit(){
	setEditStatus();
}
function delcard(){
	var selectNode = getSelectNode()[0];
	var data = {id : selectNode.uk};
	var urls = baseAction+'_deleteByPk';
	var info = ajaxSave(urls,data);
	$.ligerDialog.success(info);
	
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
			<form name="form1" method="post" id="form1">
				<table cellpadding="0" cellspacing="0" class="l-table-edit" >
					<tr>
						<td align="right" class="l-table-edit-td" >上级编码:</td>
						<td align="left" class="l-table-edit-td">
						<input name="parentcode" type="text" id="txtName1" ltype="text" />
						</td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td" valign="top">分类编码:</td>
						<td align="left" class="l-table-edit-td">
							<input name="code" type="text" id="txtName2" ltype="text" />
							<input name="pk" type="hidden" id="txtName0" ltype="text" />
						</td><td align="left"></td>
					</tr>   
					<tr>
						<td align="right" class="l-table-edit-td" valign="top">分类名称:</td>
						<td align="left" class="l-table-edit-td">
							<input name="name" type="text" id="txtName3" ltype="text" />
						</td><td align="left"></td>
					</tr>  
					<tr>
						<td align="right" class="l-table-edit-td">创建时间:</td>
						<td align="left" class="l-table-edit-td">
							<input name="createtime" type="text" id="txtDate" ltype="date" />
						</td><td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td" valign="top">创建人:</td>
						<td align="left" class="l-table-edit-td">
							<input name="creator" type="text" id="txtName4" ltype="text" />
						</td><td align="left"></td>
					</tr> 
					<tr>
						<td align="right" class="l-table-edit-td">修改时间:</td>
						<td align="left" class="l-table-edit-td">
							<input name="modifytime" type="text" id="txtDate" ltype="date" />
						</td><td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td" valign="top">修改人:</td>
						<td align="left" class="l-table-edit-td">
							<input name="modifer" type="text" id="txtName5" ltype="text" />
						</td><td align="left"></td>
					</tr> 
					<tr>
						<td align="right" class="l-table-edit-td">详情:</td>
						<td align="left" class="l-table-edit-td"> 
						<textarea cols="100" rows="4" name="detail" class="l-textarea" style="width:400px"></textarea>
						</td><td align="left"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>