<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能节点</title>
<link href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/demo.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/app/function.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/function.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery.ztree.exhide-3.5.js"></script>

<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';

var treeAction = baseUrl+"/FunctionAction_getCheckTreeNodeByParentCode";
var functionAddAction = baseUrl+"/FunctionAction_addFunction";
var functionUpdateAction = baseUrl+"/FunctionAction_EditFunction";
var functionDelAction = baseUrl+"/FunctionAction_delFunction";

var treeId = "treeRootNode";
var optStatus = null;
jQuery(document).ready(function(){
	initMyZtree();
});
  
var setting = {
	/* check: {
		enable: true
		//,chkStyle: "radio"
	}, */
	 data: { key: {title: "title" },simpleData: {enable: true } }
    ,view: {  selectedMulti: false }  //,fontCss: setFontCss  
    ,async: {   enable: true,url: treeAction,type:"POST",  autoParam:["id=pCode", "name", "level"], dataFilter: filter }   
    ,callback: {   beforeClick: beforeClickZtree  }  //,onCheck: onCheck
   
};  
		
function initMyZtree(){  
	var zNodes="";
    $.ajax({  
    	url: treeAction, type: "GET",data:{pCode:'rootdir0'},
        success: function(data) {     
            //alert(data);
            zNodes=eval(data);  
            $.fn.zTree.init($("#"+treeId), setting, zNodes);  
        }     
    });    
      
}  


function freshFunctionNode(){
	initMyZtree();
}

//单击事件  
function beforeClickZtree(treeId, treeNode){  
    //alert(treeNode.id+","+treeNode.name); 
    var tableObj = jQuery("#node_table_id");
    if(optStatus == "update"){
    	var code = jQuery("input[name='ncode']",tableObj).val();
    	if(code!=treeNode.id){
    		if(confirm("是否放弃当前操作？")){
        		clearNodeValue("node_table_id");
        		jQuery("input[name='parentname']",tableObj).val(treeNode.name);
        		jQuery("input[name='parentncode']",tableObj).val(treeNode.id);
        		setNodeValue(treeNode);
        		optStatus = null;
        		setStatus(optStatus);
        	}
    	}
    }else if(optStatus == null || optStatus == "add"){
    	jQuery("input[name='parentname']",tableObj).val(treeNode.name);
    	jQuery("input[name='parentncode']",tableObj).val(treeNode.id);
    	setNodeValue(treeNode);
    	optStatus = null;
    	setStatus(optStatus);
    }
}

//新增节点
function addNode(){
	var nodes = getSelectedNodes(treeId);
	var node = nodes[0];
	var tableObj = jQuery("#node_table_id");
	tableObj.css("display","");
	if(optStatus==null){
		if(node!=null){
			clearNodeValue("node_table_id");
			jQuery("input[name='parentname']",tableObj).val(node.name);
			jQuery("input[name='parentncode']",tableObj).val(node.id);
		}
	}else{
		if(confirm("是否放弃当前操作？")){
			if(node!=null){
				clearNodeValue("node_table_id");
				jQuery("input[name='parentname']",tableObj).val(node.name);
				jQuery("input[name='parentncode']",tableObj).val(node.id);
			}
		}
	}
	optStatus = "add";
	setStatus(optStatus);
}

function saveNode(){
	if(optStatus=="add"){
		var tableObj = jQuery("#node_table_id");
		var parentInputObj = jQuery("input[name='parentncode']",tableObj);
		var parentCode = parentInputObj[0].value;
		if(parentCode == null || parentCode == ''){
			alert("请选择上级节点!");
			return ;
		}
		if(jQuery("input[name='ncode']",tableObj).val()==null||jQuery("input[name='ncode']",tableObj).val()==''){
			alert("请输入节点编码!");
			return ;
		}
		var json = getInputItems(tableObj[0]);
		var dataStr = JsonToString(json);
		$.ajax({  
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	    	url: functionAddAction, type: "POST",data:{node:dataStr},
	        success: function(data) {    
	        	alert("新增节点操作成功！");
	            //alert(data);
	            //zNodes=eval(data);  
	            initMyZtree();
	            
	        }     
	    }); 
		tableObj.css("display","none");
		optStatus = null;
	}else if(optStatus == 'update'){
		var tableObj = jQuery("#node_table_id");
		var parentInputObj = jQuery("input[name='parentncode']",tableObj);
		var parentCode = parentInputObj[0].value;
		if(parentCode == null || parentCode == ''){
			alert("请选择上级节点!");
			return ;
		}
		if(jQuery("input[name='ncode']",tableObj).val()==null||jQuery("input[name='ncode']",tableObj).val()==''){
			alert("请输入节点编码!");
			return ;
		}
		var json = getInputItems(tableObj[0]);
		var dataStr = JsonToString(json);
		$.ajax({  
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	    	url: functionUpdateAction, type: "POST",data:{node:dataStr},
	        success: function(data) {    
	        	alert("修改节点操作成功！");
	            //alert(data);
	            //zNodes=eval(data);  
	            initMyZtree();
	            
	        }     
	    }); 
		tableObj.css("display","none");
		optStatus = null;
		setStatus(optStatus);
	}
}
function updateNode(){
	 var nodes=getSelectedNodes(treeId);
	 var treeNode = nodes[0];
	 if(treeNode == null){
		 alert("请选择节点!");
		 return ;
	 }
	 var tableObj = jQuery("#node_table_id");
	 var code = jQuery("input[name='ncode']",tableObj).val();
	 if(optStatus!=null){
    	if(code!=treeNode.id){
    		if(confirm("是否放弃当前操作？")){
    			optStatus = "update";
    			setStatus(optStatus);
    			setNodeValue(treeNode,"node_table_id");
    		}
    	}
	 }else{
		optStatus = "update";
		setStatus(optStatus);
		setNodeValue(treeNode,"node_table_id");
	 }
	 jQuery("#node_table_id").css("display","");	
}

function delNode(){
	 var nodes=getSelectedNodes(treeId);
	 var treeNode = nodes[0];
	 if(treeNode == null){
		 alert("请选择节点!");
		 return ;
	 }
	 var tableObj = jQuery("#node_table_id");
	 var code = jQuery("input[name='ncode']",tableObj).val();
	 var id = null;
	 if(optStatus!=null){
		 if(confirm("是否放弃当前操作？")){
 			optStatus = "del";
 			setStatus(optStatus);
 		}
	 }else if(optStatus == null || optStatus == "del"){
		optStatus = "del";
		setStatus(optStatus);
		id = treeNode.uk;
		$.ajax({  
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	    	url: functionDelAction, type: "POST",data:{ids:id},
	        success: function(data) {    
	        	alert("删除节点操作成功！");
	            //alert(data);
	            //zNodes=eval(data);  
	            initMyZtree();
	            optStatus = null;
	 			setStatus(optStatus);
	        }     
	    }); 
	 }
}
</script>
</head>
<body>
		<table class="tree_table" >
			<tr class="log_tr title_tr">
				<td class="tool_title_td" colspan="100%">
					<div class="tool_title_div">功能节点树维护</div>
				</td>
			</tr>
			<tr class="log_tr tool_tr">
				<td class="log_td tool_td">
					<div style="margin: 3px;">
						<input type="button" value="新增" onclick="addNode();">
						<input type="button" value="修改" onclick="updateNode();">
						<input type="button" value="删除" onclick="delNode();">
						<input type="button" value="保存" onclick="saveNode();">
					</div>
				</td>
				<td class="log_td tool_td">
					<div class="tool_title_div">当前所选节点</div></td>
				<td class="log_td tool_td">
					<div class="tool_title_div">当前操作<span id="opt_id"></span></div></td>
			</tr>
			<tr class="log_tr">
				<td class="log_item_td" id="log_item_id" style="vertical-align: top;">
					<!-- 左侧功能树 -->
					<!-- <div class="log_item" name="log_nginx" onclick="showLogPage(this)" url="nginx/index.html">
						<p>nginx</p>
					</div> -->
					<div id="treeNodeContain" class="zTreeDemoBackground left"  style="overflow:auto ;width: 225px; height: 240px;margin:0px;padding-left: 15px;">
						<ul	id="treeRootNode" class="ztree" style="height: 200px;width: 150px;">
							
						</ul>
					</div>
				</td>
				<td class="log_page" style="vertical-align: top;width: 270px;">
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
						<tr></tr>
					</table>
					</form>
				</td>
				<td class="log_page" style="vertical-align: top;width: 270px;">
					<form name="optform">
					<table class="node_table" id="node_table_id" style="display: none;">
						<tr class="node_tr">
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
						</tr>
						<tr class="node_tr">
							<td class="node_td_name">link</td>
							<td class="node_td_input"><input type="text" name="link" value=""></td>
						</tr>
						<tr></tr>
					</table>
					</form>

			</td>
			</tr>
			<!-- <tr></tr> -->
		</table>

</body>
</html>