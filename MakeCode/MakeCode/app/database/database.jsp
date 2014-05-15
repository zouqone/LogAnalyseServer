<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能节点</title>
<link href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/demo.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/app/database.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/database.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery.ztree.exhide-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.10.4.custom.js"></script>

<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';

var treeAction = baseUrl+"/FunctionAction_getCheckTreeNodeByParentCode";
var functionAddAction = baseUrl+"/FunctionAction_addFunction";
var functionUpdateAction = baseUrl+"/FunctionAction_EditFunction";
var functionDelAction = baseUrl+"/FunctionAction_delFunction";
var dbAction = baseUrl+"/DBAction_getDBInfo";
var dbNameAction = baseUrl + "/DBAction_getAllDBName";
var treeId = "treeRootNode";
var optStatus = null;

var setting  = null;

jQuery(document).ready(function(){
	//initMyZtree();
	setting = {
			 data: { key: {title: "title" },simpleData: {enable: true } }
		    ,view: {  selectedMulti: false }  //,fontCss: setFontCss  
		    ,async: {   enable: true,url: dbAction,type:"POST",  
		    	autoParam:["id=pCode", "name", "level"], otherParam : getDbInfoToTree(),dataFilter: filter }   
		    ,callback: {   beforeClick: beforeClickZtree  }  //,onCheck: onCheck
		   
		}; 
	 conn();
});

function getDbInfoToTree(){
	var dbInfoForm = jQuery("#dbInfo_id");
	var dbInfoJson = getInputItems(dbInfoForm[0]);
	return dbInfoJson;
}
function conn(){
	var dbInfoForm = jQuery("#dbInfo_id");
	var dbInfoJson = getInputItems(dbInfoForm[0]);
	$.ajax({  
    	url: dbNameAction, type: "POST",data:dbInfoJson,
        success: function(data) {     
            //alert(data);
            //var json = StringToJson(data);
            var array = data.split(",");
            zNodes=DBNameListToName(array); 
            //zNodes=DBNameListToName(json); 
            $.fn.zTree.init($("#"+treeId), setting, zNodes);  
        }     
    });    
      
}

function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	for (var i=0, l=childNodes.length; i<l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	var node = DBToTree(childNodes);
	childNodes = node.children;
	return childNodes;
}

function DBNameListToName(json){
	var dbInfoForm = jQuery("#dbInfo_id");
	var dbInfoJson = getInputItems(dbInfoForm[0]);
	if(json == null){
		return [];
	}
	var nodes = [];
	for(var i in json){
		var e = json[i];
		e= jQuery.trim(e);
		node = {};
		node.id = e;
		node.name = e;
		node.icon = baseUrl+"/images/db/db.png";
		//node.open = false;
		node.hasChild = true;
		node.isParent = true;
		nodes.push(node);
	}
	return nodes;
}

function DBToTree(json){
	if(json == null){
		return [];
	}
	var rootNode = {};
	var zNodes = [rootNode];
	var tableVoList = json.tableVoList;
	rootNode.id=json.name;
	rootNode.name=json.name;
	if(tableVoList!=null&&tableVoList.length>0){
		var level2 = [];
		for(var i in tableVoList){
			var tableVo = tableVoList[i];
			var node = {};
			node.id = tableVo.table_name;
			node.name = tableVo.table_name;
			node.comment = tableVo.comment;
			node.tableVo = tableVo;
			var level3 = columnListToNodes(tableVo);
			node.children = level3;
			node.icon = baseUrl+"/images/db/table.png";
			level2.push(node);
		}
		rootNode.children = level2;
	}
	return rootNode;
}

function columnListToNodes(tableVo){
	if(tableVo == null){
		return [];
	}
	var columnVoList = tableVo.columnVoList;
	var nodes = [];
	if(columnVoList!=null&&columnVoList.length>0){
		for(var i in columnVoList){
			var columnVo = columnVoList[i];
			var node = {};
			node.id = columnVo.field;
			node.name = columnVo.field;
			node.icon = baseUrl+"/images/db/column.png";
			nodes.push(node);
		}
	}
	return nodes;
}

//单击事件  
function beforeClickZtree(treeId, treeNode){  
    //alert(treeNode.id+","+treeNode.name); 
    var tableObj = jQuery("#node_table_id");
    var contentView = jQuery("#contentView");
    var tableDiv = jQuery("#tableDivId");
    var formTemplate = tableDiv.children("form")[0];
    
    if(treeNode.level==1){
    	var tableVo = treeNode.tableVo;
    	var columnVoList = tableVo.columnVoList;
    	var formVo = jQuery(formTemplate).clone();
    	formVo = loadForm(tableVo,formVo);
    	formVo.appendTo(tableDiv);
    	$( formVo ).draggable({containment:"window"});
    }
}

function loadForm(tableVo,formVo){
	var tablename = tableVo.table_name;
	jQuery("[name='tablename']",formVo).html(tablename);
	var tableObj = jQuery(formVo).children()[0];
	var columnVoList = tableVo.columnVoList;
	if(columnVoList!=null&&columnVoList.length>0){
		var columnTr = jQuery("[name='column']",formVo);
		for(var i = 0 ; i < columnVoList.length ; i++){
			var columnVo = columnVoList[i];
			var trObj =  columnTr.clone();
			var tds = jQuery(trObj).children("td");
			jQuery(tds[0]).html(columnVo.field);
			jQuery(tds[1]).html(columnVo.type);
			columnTr.before(trObj);
		}
		columnTr.remove();
	}
	return formVo;
}


</script>
</head>
<body>
		<table class="tree_table" >
			<tr class="log_tr title_tr">
				<td class="tool_title_td" colspan="100%">
					<div class="tool_title_div">数据库信息</div>
				</td>
			</tr>
			<tr class="log_tr tool_tr">
				<td class="log_td tool_td">
					<div style="margin: 3px;">
						<input type="button" value="连接" onclick="conn();">
						
					</div>
				</td>
				<td class="log_td tool_td">
					<div class="tool_title_div"></div>
				</td>
			</tr>
			<tr class="log_tr conn_tr">
				<td class="conn_td" colspan="100%">
					<div style="height: 5px;width: 800px;"></div>
					<form action="" name="dbInfo" id="dbInfo_id">
						<table class=" dbInfo_table">
							<tr class=" dbInfo_tr">
								<td class="dbInfo_td dbInfo_td_left">数据库类型</td>
								<td class="dbInfo_td dbInfo_td_right" style="width: 80px;"><input type="text" name="type" value="mysql"></td>
								<td class="dbInfo_td dbInfo_td_left">数据库名</td>
								<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="dbName" value="log"></td>
								<td class="dbInfo_td dbInfo_td_left">ip地址</td>
								<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="ip" value="localhost"></td>
								<td class="dbInfo_td dbInfo_td_left" >端口号</td>
								<td class="dbInfo_td dbInfo_td_right"><input type="text" name="port" value="3306"></td>
							</tr>
							<tr class=" dbInfo_tr">
								<td class="dbInfo_td dbInfo_td_left">驱动类型</td>
								<td class="dbInfo_td dbInfo_td_right" style="width: 80px;"><input type="text" name="driver" value="com.mysql.jdbc.Driver"></td>
								<td class="dbInfo_td dbInfo_td_left">用户</td>
								<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="username" value="root"></td>
								<td class="dbInfo_td dbInfo_td_left">密码</td>
								<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="password" value="1"></td>
							</tr>
						</table>
					</form>
					<div style="height: 5px;width: 800px;"></div>
				</td>
			</tr>
			<tr class="log_tr">
				<td class="log_item_td" id="log_item_id" style="vertical-align: top;width: 200px;">
					<!-- 左侧功能树 -->
					<div id="treeNodeContain" class="zTreeDemoBackground left"  style="width: 210px; height: 320px;margin:0px;padding-left: 15px;">
						<ul	id="treeRootNode" class="ztree" style="height: 300px;width: 200px;">
							
						</ul>
					</div>
				</td>
				<td id="contentView" class="log_page" style="vertical-align: top;width: 620px;">
				
				
				<div id="tableDivId" >
					<form action="">
						<table class="tableVoCss" style="width: 200px;height: 200px;">
							<tr >
								<td class="tableVoTrCss tableVoThCss" colspan="100%">
									<div name="tablename" style="width: 100%;height: 25px;font-weight: bolder;text-align: center;"></div>
								</td>
							</tr>
							<tr class="tableVoTrCss" name="column">
								<td class="tableVoTrTdCss tvLeft"></td>
								<td class="tableVoTrTdCss tvRight"></td>
							</tr>
							<tr></tr>
						</table>
					</form>
				</div>
					<form name="curform">
					<table class="node_table" id="cur_node_table_id" style="display: ;">
						<tr class="node_tr" style="display: none">
							<td class="node_td_name">parentname</td>
							<td class="node_td_input">
								<input type="hidden" name="id" value="">
								<input type="hidden" name="parentncode" value="">
								<input type="text" name="parentname" value="">
							</td>
						</tr>
						<tr class="node_tr">
							<td class="node_td_name">nodename</td>
							<td class="node_td_input"><input type="text" name="nodename" value=""></td>
						</tr>
						<tr class="node_tr">
							<td class="node_td_name">ncode</td>
							<td class="node_td_input"><input type="text" name="ncode" value=""></td>
						</tr>
						<tr class="node_tr">
							<td class="node_td_name">nodedesc</td>
							<td class="node_td_input"><input type="text" name="nodedesc" value=""></td>
						</tr >
						<tr class="node_tr">
							<td class="node_td_name">link</td>
							<td class="node_td_input"><input type="text" name="link" value=""></td>
						</tr>
						<!-- <tr></tr> -->
					</table>
					</form>
				</td>
			</tr>
			<!-- <tr></tr> -->
		</table>

</body>
</html>