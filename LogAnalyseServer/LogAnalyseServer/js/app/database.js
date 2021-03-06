



function onCheck(e, treeId, treeNode) {
	//alert(treeNode.id+","+treeNode.name);
	
	var tableObj = jQuery("#node_table_id");
	var parentCodeObj = jQuery("input[name='parentncode']",tableObj);
	var parentNameObj = jQuery("input[name='parentname']",tableObj);
	if(treeNode.checked == true){
		parentCodeObj[0].value = treeNode.id;
		parentNameObj[0].value = treeNode.name;
	}else{
		parentCodeObj[0].value = null;
		parentNameObj[0].value = null;
	}
	
}

function setStatus(key){
	var status = {
			add:"新增",
			update:"修改",
			del:"删除"
	};
	var opt = key == null?"无":status[key];
	opt = "("+opt+")";
	jQuery("#opt_id").html(opt);
	jQuery("#opt_id").attr("status",key);
}

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

 
function setNodeValue(treeNode,tableId){
	tableId = tableId == null?"cur_node_table_id":tableId;
	var parentNode = treeNode.getParentNode();
	var tableObj = jQuery("#"+tableId);
	if(parentNode != null){
		jQuery("input[name='parentname']",tableObj).val(parentNode.name);
		jQuery("input[name='parentncode']",tableObj).val(parentNode.id);
	}
	jQuery("input[name='id']",tableObj).val(treeNode.uk);
	jQuery("input[name='ncode']",tableObj).val(treeNode.id);
	jQuery("input[name='nodename']",tableObj).val(treeNode.name);
	jQuery("input[name='nodedesc']",tableObj).val(treeNode.nodedesc);
	jQuery("input[name='link']",tableObj).val(treeNode.file);
}

function clearNodeValue(tableId){
	var tableObj = jQuery("#"+tableId);
	jQuery("input",tableObj).val(null);
}

/**
 * 获取已经选择的树节点
 * @param treeId
 * @returns
 */
function getSelectedNodes(treeId){
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var nodes = treeObj.getSelectedNodes();
	return nodes;
}
















