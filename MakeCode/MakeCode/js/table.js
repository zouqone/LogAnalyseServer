

/**
 * 通过元素名称和全选对象实现全选事件
 * @param objName
 * @param obj
 */
function toggleCheckBox(objName,obj){
	var checkboxAllObj = obj.children[0];
	var checkboxName = checkboxAllObj.getAttribute(objName);
	var tableObj =obj.parentNode.parentNode.parentNode;
	var checkboxs =getcheckBoxbyName(checkboxName,tableObj);
	if(checkboxs!=null&&checkboxs.length>0){
		if(checkboxAllObj.checked){
			for(var i=0;i<checkboxs.length;i++){
				var inputs = checkboxs[i];
				if(!inputs.checked){
					inputs.checked=true;
				}
			}
		}else{
			for(var i=0;i<checkboxs.length;i++){
				var inputs = checkboxs[i];
				if(inputs.checked){
					inputs.checked=false;
				}
			}
		}
	}
}

/**
 * 加载数据到数据区域
 * @param table_form_id
 * @param datas
 */
function loadDatas(table_form_id,datas){
	clearData(table_form_id);
	var data_table = jQuery("#"+table_form_id);
	var data_view_tbody = jQuery("[name='data_view']",data_table);
	var template_view_tbody = jQuery("[name='template_view']",data_table);
	var template_tr = template_view_tbody.children("tr");
	if(datas!=null&&datas.length>0){
		for(var i = 0 ; i < datas.length ; i++){
			var data = datas[i];
			var data_tr = template_tr.clone();
			var index = i+1;
			setTrData(data_tr,data,index);
			data_view_tbody.append(data_tr);
		}
		
	}
}

/**
 * 设置每行数据
 * @param trObj
 * @param data
 * @param index
 */
function setTrData(trObj,data,index){
	var tr = jQuery(trObj);
	if(index%2==0){
		tr.addClass("data_table_tr_even");
	}
	jQuery('[name="numTd"]',tr).html(index);
	for(var key in data){
		var value = data[key];
		jQuery("div[name='"+key+"']",tr).html(value);
		jQuery("input[name='"+key+"']",tr).val(value);
	}
	
}

/**
 * 清除数据区域数据
 * @param table_form_id
 */
function clearData(table_form_id){
	var data_table = jQuery("#"+table_form_id);
	var data_view_tbody = jQuery("[name='data_view']",data_table);
	data_view_tbody.empty();
	data_view_tbody.append('<tr style="height: auto;"></tr>');
	
}

/**
 * 点击行选择
 * @param obj
 */
function selectTr(obj){
	var checkbox = jQuery("[name='table_form_checkbox']",obj)[0];
	selectCK(checkbox);
}

/**
 * 点击复选框选择
 * @param checkbox
 */
function selectCK(checkbox){
	var tr = jQuery(checkbox).parents("tr")[0];
	if(checkbox.checked == false){
		jQuery(tr).addClass("data_table_tr_select");
		checkbox.checked=true;
	}else{
		checkbox.checked=false;
		jQuery(tr).removeClass("data_table_tr_select");
	}
}


