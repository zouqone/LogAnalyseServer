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
<link href="<%=request.getContextPath()%>/css/ui-lightness/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/app/database.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree_v3/jquery.ztree.exhide-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.form.js"></script>



<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.10.4.js"></script>

<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';

var treeAction = baseUrl+"/FunctionAction_getCheckTreeNodeByParentCode";
var functionAddAction = baseUrl+"/FunctionAction_addFunction";
var functionUpdateAction = baseUrl+"/FunctionAction_EditFunction";
var functionDelAction = baseUrl+"/FunctionAction_delFunction";
var dbAction = baseUrl+"/DBAction_getDBInfo";
var dbNameAction = baseUrl + "/DBAction_getAllDBName";
var dbMakeCodeAction = baseUrl + "/DBAction_makeCode";
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

//生成Java代码
function makeCode(obj){
	var contentViewTable = jQuery('#contentViewTable');
  	var formVos = jQuery("[dbtype='table']",contentViewTable);
  	var tableList = [];
  	for(var i = 0 ; i<formVos.length ; i++){
  		var formVo = formVos[i];
  		var dbConfigureInput = jQuery("[name=dbConfigure]",formVo);
  		var tableInfo = getTableInfo(formVo);
  		var configStr = dbConfigureInput.val();
  		var config = StringToJson(configStr);
  		config = filterConfig(config);
  		tableInfo.configVo = config;
  		tableList.push(tableInfo);
  	}
  	var tableListStr = JsonToString(tableList);
  	var url = dbMakeCodeAction;
	$.ajax({  
    	url: url+"?rk="+Math.random(), type: "POST",data:{tableList : tableListStr},
        success: function(data) {
        	alert("代码已生成！");
        }
	});
	
}

function setTableNode(obj){
	var formVo = jQuery(jQuery(obj).parents("[dbType='table']")[0]);
	var table_name = formVo.attr('name');
	var tcomment = jQuery('[name="tcomment"]',formVo).val();
	var dbInfo_makeCode = jQuery('#dbInfo_makeCode_div_id');
	var dbConfigureInput = jQuery("[name=dbConfigure]",formVo);
	var configureValue = dbConfigureInput.val();
	clearConfigTemplate(dbInfo_makeCode);
	if(configureValue == null || configureValue == ''){
		initMakeCodeConfig(table_name,dbInfo_makeCode);
	}else{
		var data = StringToJson(configureValue);
		setMakeCodeConfig(data,dbInfo_makeCode);
	}
	
	jQuery(dbInfo_makeCode).dialog({
		modal: true,
		autoOpen: true,
		width: 950,
		title:table_name+' '+tcomment,
		buttons: [
			{
				text: "Ok",
				click: function() {
					var data = getConfigureInfo(dbInfo_makeCode);
					var configureStr = JsonToString(data);
					dbConfigureInput.val(configureStr);
					$( this ).dialog( "close" );
				}
			},
			{
				text: "Cancel",
				click: function() {
					$( this ).dialog( "close" );
				}
			}
		]
	});
}

</script>
</head>
<body>
<!-- 数据库信息页面 -->
<table class="tree_table" style="width: 99%">
	<tr class="log_tr title_tr">
		<td class="tool_title_td" colspan="100%">
			<div class="tool_title_div">数据库信息</div>
		</td>
	</tr>
	<tr class="log_tr tool_tr">
		<td class="log_td tool_td">
			<div style="margin: 3px;">
				<input type="button" value="显示连接" onclick="showConnInfo(this);">
				<input type="button" value="生成代码" onclick="makeCode(this);">
				
			</div>
		</td>
		<td class="log_td tool_td">
			<div id="dbNameID" class="tool_title_div" style="text-align: center;"></div>
		</td>
	</tr>
	<tr class="log_tr conn_tr" id="dbInfo_conn_id" style="display: none;">
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
						<td class="dbInfo_td dbInfo_td_left"></td>
						<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="button" value="开始连接" onclick="conn();"></td>
					
					</tr>
				</table>
			</form>
			<div style="height: 5px;width: 800px;"></div>
		</td>
	</tr>
	<tr class="log_tr conn_tr" id="dbInfo_makeCode_id" style="display: none;">
		<td class="conn_td" colspan="100%">
			
		</td>
	</tr>			
	<tr class="log_tr">
		<td class="log_item_td" id="log_item_id" style="vertical-align: top;width: 230px;">
			<!-- 左侧功能树 -->
			<div id="treeNodeContain" class="zTreeDemoBackground left"  style="width: 210px; height: 320px;margin:0px;padding-left: 10px;">
				<ul	id="treeRootNode" class="ztree" style="height: 300px;width: 200px;">
					
				</ul>
			</div>
		</td>
		<td class="log_page" id="contentView"  style="vertical-align: top;width:auto;min-width: 720px;">
			<!-- 数据库表显示区域 -->
			<div id="contentViewDiv" style="width: 100%;height: auto;min-height: 400px;display: ;">
				<table id="contentViewTable">
				<tr name="dbTable">
					<td name="td1"></td>
					<td name="td2"></td>
					<td name="td3"></td>
					<td name="td4"></td>
				</tr>
			</table>
			</div>
			
		</td>
	</tr>
	<!-- <tr></tr> -->
</table>

<!-- 配置信息 -->
<div id="dbInfo_makeCode_div_id" style="display: ;">
	<div style="height: 5px;width: 800px;"></div>
		<form action="" name="dbInfo" id="makeCode_id">
		<table class=" dbInfo_table">
			<tr class=" dbInfo_tr" style="height: 30px;">
				<td class="dbInfo_td dbMakeCode_td_left" style="vertical-align: bottom;text-align: left ;font-size:16px;font-weight:bolder ;padding-left: 10px;" colspan="100%" >
					全选所有选项  
					<input type="checkbox" name="checkAll" checkAll="" onclick="selectAllCheckbox(this,'checkAll','checkboxName')">
				</td>
				
			</tr>
			<tr class=" dbInfo_tr" style="height: 30px;">
				<td class="dbInfo_td dbMakeCode_td_left" style="vertical-align: bottom;text-align: left ;font-size:16px;font-weight:bolder ;padding-left: 10px;" colspan="100%" >
					属性名称  
					&nbsp;&nbsp;
					<span style="display:inline-block;font-size: 12px;height:26px;font-weight: normal;vertical-align:middle;border: 0px solid #BBBBBB">
					全选</span> 
					<input type="checkbox" name="property" checkAll="property" onclick="selectAllCheckbox(this,'checkAll','checkboxName')">
				</td>
				
			</tr>
			<tr class=" dbInfo_tr">
				<td class="dbInfo_td dbMakeCode_td_left" >dao</td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="dao" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="property" checked="checked" name="dao" value=""></td>
				<td class="dbInfo_td dbMakeCode_td_left">service</td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="service" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="property" checked="checked" name="service" value=""></td>
				<td class="dbInfo_td dbMakeCode_td_left">web</td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="web" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="property" checked="checked" name="web" value=""></td>
				<td class="dbInfo_td dbMakeCode_td_left">vo</td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="vo" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="property" checked="checked" name="vo" value=""></td>
				
				<td></td>
			</tr>
			
			<tr class=" dbInfo_tr">
				<td class="dbInfo_td dbMakeCode_td_left" >util</td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="util" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="property" checked="checked" name="util" value=""></td>
				<td class="dbInfo_td dbMakeCode_td_left">configure</td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="configure" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="property" checked="checked" name="configure" value=""></td>
				<td class="dbInfo_td dbMakeCode_td_left">jsp</td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="jsp" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="property" checked="checked" name="jsp" value=""></td>
				<td class="dbInfo_td dbMakeCode_td_left"></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><!-- <input type="button" value="开始生成" onclick="makeCode(this);"> --></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"></td>
				<td></td>
			</tr>
			<tr class=" dbInfo_tr" style="height: 30px;">
				<td class="dbInfo_td dbMakeCode_td_left" style="vertical-align: bottom;text-align: left ;font-size:16px;font-weight:bolder ;padding-left: 10px;" colspan="100%" >
					后台配置路径
				    &nbsp;&nbsp;
					<span style="display:inline-block;font-size: 12px;height:26px;font-weight: normal;vertical-align:middle;border: 0px solid #BBBBBB">
					全选</span> 
					<input type="checkbox" name="javaConfigPath" checkAll="javaConfigPath" onclick="selectAllCheckbox(this,'checkAll','checkboxName')">
				</td>
			</tr>
			<tr class=" dbInfo_tr">
				<td class="dbInfo_td dbMakeCode_td_left" >package</td>
				<td class="dbInfo_td dbInfo_td_right_big" colspan="9" style="width: 200px;"><input type="text" name="packagePath" value=""></td>
				<td class="dbInfo_td dbInfo_td_right"  style="width: 20px;"><input type="checkbox" checkboxName="javaConfigPath" checked="checked" name="packagePath" value=""></td>
				<td></td>
			</tr>
			<tr class=" dbInfo_tr">
				<td class="dbInfo_td dbMakeCode_td_left">struts.xml</td>
				<td class="dbInfo_td dbInfo_td_right_big" colspan="9" style="width: 200px;"><input type="text" name="strutsxml" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="javaConfigPath" checked="checked" name="strutsxml" value=""></td>
				<td></td>
			</tr>
			<tr class=" dbInfo_tr">
				<td class="dbInfo_td dbMakeCode_td_left">spring.xml</td>
				<td class="dbInfo_td dbInfo_td_right_big"  colspan="9" style="width: 200px;"><input type="text" name="springxml" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="javaConfigPath" checked="checked" name="springxml" value=""></td>
				<td></td>
			</tr>
			<tr class=" dbInfo_tr">
				<td class="dbInfo_td dbMakeCode_td_left">jspPath</td>
				<td class="dbInfo_td dbInfo_td_right_big" colspan="9" style="width: 200px;"><input type="text" name="jspPath" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="javaConfigPath" checked="checked" name="jspPath" value=""></td>
				
				<td></td>
			</tr>
			
			<tr class=" dbInfo_tr" style="height: 30px;">
				<td class="dbInfo_td dbMakeCode_td_left" style="vertical-align: bottom;;text-align: left ;font-size:16px;font-weight:bolder ;padding-left: 10px;" colspan="100%" >
					前台配置路径
					&nbsp;&nbsp;
					<span style="display:inline-block;font-size: 12px;height:26px;font-weight: normal;vertical-align:middle;border: 0px solid #BBBBBB">
					全选</span> 
					<input type="checkbox" name="jspConfigPath" checkAll="jspConfigPath" onclick="selectAllCheckbox(this,'checkAll','checkboxName')">
				</td>
			</tr>
			<tr class=" dbInfo_tr">
				<td class="dbInfo_td dbMakeCode_td_left" >images</td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="images" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="jspConfigPath" checked="checked" name="images" value=""></td>
				<td class="dbInfo_td dbMakeCode_td_left">css</td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 100px;"><input type="text" name="css" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="jspConfigPath" checked="checked" name="css" value=""></td>
				<td class="dbInfo_td dbMakeCode_td_left">js</td>
				<td class="dbInfo_td dbInfo_td_right_big" colspan="3" style="width: 100px;"><input type="text" name="js" value=""></td>
				<td class="dbInfo_td dbInfo_td_right" style="width: 20px;"><input type="checkbox" checkboxName="jspConfigPath" checked="checked" name="js" value=""></td>
				
			</tr>
		</table>
	</form>
	<div style="height: 5px;width: 800px;"></div>
</div>
	
<!-- 表格模板 -->	
<div id="tableDivId" style="display: none;">
	<div name="div_vo"  dbType="table" style="width: 200px;min-width:200px;height: auto;">
	<input type="hidden" name="dbConfigure" value="">
	<form action="">
		<table class="tableVoCss" style="width: auto;height: auto;min-height: 100px;">
			<tr name="head" style="">
				<td class="tableVoTrCss tableVoThCss tableVoDelCss" colspan="100%" style="">
				<a class="dbTypeIcon" onclick="setTableNode(this)"></a>
				<input type="text" class="tableVoThCss headInput" name="table_name" value="tableName">
				<a class="dbdela" onclick="delTableNode(this)"></a>
				</td>
			</tr>
			<tr style="">
				<td class="tableVoTrCss" colspan="100%" style="">
					<div name="tablename" style="width: 100%;height: 20px;font-weight: bolder;text-align: center;">
						
						<span></span>
						<input type="text" class="right" name="tcomment" value="tcomment">
					</div>
					
				</td>
			</tr>
			<tr class="tableVoTrCss" name="column">
				<td class="tableVoTrTdCss tvLeft"><input type="text" class="column" name="field" value="field"></td>
				<td class="tableVoTrTdCss tvRight"><input type="text" class="column" name="type" value="type"></td>
				<td class="tableVoTrTdCss tvRight"><input type="text" class="column" name="comment" value="comment"></td>
			</tr>
			<tr></tr>
		</table>
	</form>
	</div>
</div>					
</body>
</html>