<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>web单据模板设计</title>
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
	    ,async: {   enable: true,url: treeAction ,async : false,type : "POST" , autoParam:["id=pCode", "name", "level"], dataFilter: filter }   
	    ,callback: {   beforeClick: beforeClickZtree  }  //,onCheck: onCheck
	   
	};  
//单击事件  
function beforeClickZtree(treeId, treeNode){ 
	
}
	function initMyZtree(){  
		var zNodes="";
	    $.ajax({  
	    	url: treeAction,async : false,type : "POST",data:{pCode:'rootdir0'},
	        success: function(data) {     
	            //alert(data);
	            zNodes=eval(data);  
	            $.fn.zTree.init($("#"+treeId), setting, zNodes);  
	        }     
	    });
	    
	    var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var nodes = treeObj.getNodes();
		if (nodes.length>0) {
			for(var i in nodes){
				treeObj.expandNode(nodes[i], true, true, true);
			}
		}
	  	
	}
	
	
</script>
<style type="text/css">
.tablecss{border-collapse: collapse;}
.bordercss{border: 1px solid #BBBBBB;};
</style>
</head>
<body>
<div id="functionId">
	<!-- 按钮区域  -->
	<input type="button" value="新增" onclick="addNode();">
	<input type="button" value="删除" onclick="delNode();">
	<input type="button" value="预览" onclick="updateNode();">
	<input type="button" value="保存" onclick="saveNode();">
	<input type="button" value="返回" onclick="freshNode();">
</div>
<div id="billDesignArea">
	
	<!-- 表单设计区域  -->
	<table class="bill_table tablecss bordercss" style="width: 100%;height: 500px;">
		<tr class="bordercss">
			<td class="bordercss" style="width: 200px;vertical-align: top;">
				<!-- 元数据 -->
				<div id="metaDataId" class="bordercss" style="width:  100%;height: 250px;">
					<div id="treeNodeContain" class="zTreeDemoBackground left"  style="overflow:auto ;width: 225px; height: 240px;margin:0px;padding-left: 15px;">
						<ul	id="treeRootNode" class="ztree" style="height: 200px;width: 150px;">
							
						</ul>
					</div>
				</div>
				<!-- 属性 -->
				<div id="PropertyId" class="bordercss" style="width: 100%;height: 250px;">
				
				</div>
			</td>
			<td class="bordercss">
				<!-- 表头 -->
				<div id="tableHeadId" class="bordercss" style="width:  100%;height: 200px;">
				
				</div>
				<!-- 表体 -->
				<div id="tableBodyId" class="bordercss" style="width:  100%;height: 300px;">
				
				</div>
			</td>
		</tr>
	</table>
</div>

</body>
</html>