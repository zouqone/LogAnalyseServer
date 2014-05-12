
/**
 * 从数据库查询出所有的数据
 */
function getData(){
	ajaxRequest({
		url:baseUrl+"/UserVoAction",
		data:{opt:"queryAll"},
		type:"POST",
		success:function(response){
			var datas = StringToJson(response);
			showList(datas);
		}
	});
}

/**
 * 将数据以列表形式展现
 * @param datas
 */
function showList(datas,table_ID){
	if(datas==null||datas.length<=0){
		return ;
	}
	if(table_ID==null||table_ID==''){
		table_ID="showListId";
	}
	var showListObj = document.getElementById(table_ID);
	if(showListObj==null){
		return ;
	}
	var tableObj = showListObj;
	for(var i = 0 ; i< datas.length ; i++){
		data = datas[i];
		var html = "";
		//var Rows = tableObj.rows;
		//var counts = Rows.length;
		//var newRow = tableObj.insertRow(counts);
		html +=''//'<tr class="user_table_tr">'
			+'<td>'+'<input type="checkbox" name="id" value="'+data.id+'">'+'</td>'
			+'<td>'+(i+1)+'</td>'
			+'<td>'+data.username+'</td>'
			+'<td>'+data.password+'</td>'
			+'<td>'+data.age+'</td>'
			+'<td>'+(data.sex==1?"男":"女")+'</td>'
			+'<td>'+data.address+'</td>'
			+'<td>'+data.email+'</td>'
			+'<td>'+data.info+'</td>'
			//+'</tr>'
			;
		var newDiv = document.createElement('div');
		newDiv.innerHTML="<table><tr>"+html+"</table></tr>";
		var newTr = newDiv.childNodes[0].tBodies[0].rows[0];
		tableObj.tBodies[0].appendChild(newTr);//兼容IE
		newTr.className="user_table_tr";
	}
}

function showAddDiv(id){
	var addListObj = document.getElementById(id);
	addListObj.style.display="block";
	addData();
}

/**
 * 新增数据输入框
 */
function addData(table_ID){ //addListId
	if(table_ID==null){
		table_ID="addListId";
	}
	var addListObj = document.getElementById(table_ID);
	var tableObj = addListObj;
	var Rows = tableObj.rows;
	var counts = Rows.length;
	//var newRow = tableObj.insertRow(counts);
	var html =''//'<tr class="user_table_tr">'
		+'<td>'+'<input type="checkbox" name="id" value="">'+'</td>'
		+'<td>'+counts+'</td>'
		+'<td>'+'<input class="user_input_text" type="text" name="username" value="">'+'</td>'
		+'<td>'+'<input class="user_input_text" type="text" name="password" value="">'+'</td>'
		+'<td>'+'<input class="user_input_text" type="text" name="age" value="">'+'</td>'
		+'<td>'+'<select name="sex" class="user_input_text"><option>请选择</option><option value="1">男</option><option value="0">女</option></select>'+'</td>'
		+'<td>'+'<input class="user_input_text" type="text" name="address" value="">'+'</td>'
		+'<td>'+'<input class="user_input_text" type="text" name="email" value="">'+'</td>'
		+'<td>'+'<input class="user_input_text" type="text" name="info" value="">'+'</td>'
		//+'</tr>'
		;
		//addListObj.innerHTML=addListObj.innerHTML+html;
	var newDiv = document.createElement('div');
	newDiv.innerHTML="<table><tr>"+html+"</table></tr>";
	var newTr = newDiv.childNodes[0].tBodies[0].rows[0];
	tableObj.tBodies[0].appendChild(newTr);
	newTr.className="user_table_tr";
	return newTr;
}

function ImportDataToInput(datas,table_ID){
	
	if(datas==null||datas.length<=0){
		return ;
	}
	if(table_ID==null||table_ID==''){
		table_ID="editListId";
	}
	
	for(var i = 0 ; i< datas.length ; i++){
		data = datas[i];
		var trObj = addData(table_ID);
		var items = getInputObjs(trObj);
		if(items!=null){
			for(k in items){
				item = items[k];
				item.value=data[k];
			}
		}
	}
}

/**
 * 展现修改列表
 * @param checkbox_name
 * @param table_id
 * @param div_id
 */
function ShowModDataDiv(checkbox_name,table_id,mod_table_id,div_id){
	var addListObj = document.getElementById(div_id);
	addListObj.style.display="block";
	
	var tableObj = document.getElementById(table_id);
	var checkboxs =getcheckBoxbyName(checkbox_name,tableObj);
	var ids = [];
	if(checkboxs!=null&&checkboxs.length>0){
		for(var i=0;i<checkboxs.length;i++){
			var inputs = checkboxs[i];
			if(inputs.checked){
				ids[ids.length]=inputs.value;
			}
		}
	}
	
	if(ids.length==0){
		if(checkboxs!=null&&checkboxs.length>0){
			for(var i=0;i<checkboxs.length;i++){
				var inputs = checkboxs[i];
				ids[ids.length]=inputs.value;
			}
		}
	}
	ajaxRequest({
		url:baseUrl+"/UserVoAction",
		data:{opt:"query",ids:ids},
		type:"POST",
		success:function(response){
			var datas = StringToJson(response);
			ImportDataToInput(datas,mod_table_id);
		}
	});
	
}

/**
 * 通过元素名称和上级节点id删除记录
 * @param objName
 * @param id
 */
function delData(objName,id){
	var tableObj = document.getElementById(id);
	var checkboxs =getcheckBoxbyName(objName,tableObj);
	var ids = [];
	if(checkboxs!=null&&checkboxs.length>0){
		for(var i=0;i<checkboxs.length;i++){
			var inputs = checkboxs[i];
			if(inputs.checked){
				ids[ids.length]=inputs.value;
			}
		}
	}
	if(ids.length==0){
		alert("请至少选择一天记录");
	}else{
		ajaxRequest({
			url:baseUrl+"/UserVoAction",
			data:{opt:"del",ids:ids},
			type:"POST",
			success:function(response){
				var datas = StringToJson(response);
				delteAllData('id','showListId');
				showList(datas);		
			}
		});
	}
}

/**
 * 清除列表中的所有数据
 */
function delteAllData(objName,id){
	var tableObj = document.getElementById(id);
	var checkboxs =getcheckBoxbyName(objName,tableObj);
	if(checkboxs!=null&&checkboxs.length>0){
		for(var i=0;i<checkboxs.length;i++){
			var inputs = checkboxs[i];
			var trObj = inputs.parentNode.parentNode;
			trObj.parentNode.removeChild(trObj);
		}
	}
}

/**
 * 通过元素名称和上级节点id删除新增数据输入框
 * @param objName
 * @param id
 */
function deleteData(objName,id){
	var tableObj = document.getElementById(id);
	var checkboxs =getcheckBoxbyName(objName,tableObj);
	var k = 0;
	if(checkboxs!=null&&checkboxs.length>0){
		for(var i=0;i<checkboxs.length;i++){
			var inputs = checkboxs[i];
			if(inputs.checked){
				var trObj = inputs.parentNode.parentNode;
				trObj.parentNode.removeChild(trObj);
				k++;
			}
		}
	}
	if(k==0){
		alert("请至少选择一天记录");
	}
}

/**
 * 删除数据输入框，关闭当前页面
 * @param checkbox_name
 * @param table_id
 * @param div_id
 */
function cancelData(checkbox_name,table_id,div_id){
	var tableObj = document.getElementById(table_id);
	var checkboxs =getcheckBoxbyName(checkbox_name,tableObj);
	if(checkboxs!=null&&checkboxs.length>0){
		for(var i=0;i<checkboxs.length;i++){
			var inputs = checkboxs[i];
			var trObj = inputs.parentNode.parentNode;
			trObj.parentNode.removeChild(trObj);
		}
	}
	var addListObj = document.getElementById(div_id);
	addListObj.style.display="none";
}

/**
 * 通过元素名称和全选对象实现全选事件
 * @param objName
 * @param obj
 */
function toggleCheckBox(objName,obj){
	var checkboxAllObj = obj.children[0];
	var tableObj =obj.parentNode.parentNode.parentNode;
	var checkboxs =getcheckBoxbyName(objName,tableObj);
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
 * 通过元素名称和上级节点id保存数据
 * @param objName
 * @param id
 */
function saveData(objName,id,div_id,show_name,show_tale_id){
	var tableObj = document.getElementById(id);
	var checkboxs =getcheckBoxbyName(objName,tableObj);
	var arrays =[];
	if(checkboxs!=null&&checkboxs.length>0){
		for(var i=0;i<checkboxs.length;i++){
			var checkboxInput = checkboxs[i];
			var trObj = checkboxInput.parentNode.parentNode;
			arrays[i]=getInputItems(trObj);
		}
	}
	var dataStr = JsonToString(arrays);
	var checkbox_name = objName;
	var table_id = id;
	ajaxRequest({
		url:baseUrl+"/UserVoAction",
		data:{opt:"adds",UserVoList:dataStr},
		type:"POST",
		success:function(response){
			alert(response)	;
			cancelData(checkbox_name,table_id,div_id);
			delteAllData(show_name,show_tale_id);
			getData();
		}
	});
}

/**
 * 通过元素名称和上级节点id保存数据更新列表
 * @param objName
 * @param id
 */
function updateData(objName,id,div_id,show_name,show_tale_id){
	var tableObj = document.getElementById(id);
	var checkboxs =getcheckBoxbyName(objName,tableObj);
	var arrays =[];
	if(checkboxs!=null&&checkboxs.length>0){
		for(var i=0;i<checkboxs.length;i++){
			var checkboxInput = checkboxs[i];
			var trObj = checkboxInput.parentNode.parentNode;
			arrays[i]=getInputItems(trObj);
		}
	}
	var dataStr = JsonToString(arrays);
	var checkbox_name = objName;
	var table_id = id;
	ajaxRequest({
		url:baseUrl+"/UserVoAction",
		data:{opt:"mods",UserVoList:dataStr},
		type:"POST",
		success:function(response){
			alert(response)	;	
			cancelData(checkbox_name,table_id,div_id);
			delteAllData(show_name,show_tale_id);
			getData();
		}
	});
}

function queryByCondition(show_name,query_div_id,table_id){
	var condition = '';
	var divObj = document.getElementById(query_div_id);
	var data=getInputItems(divObj);
	if(data!=null){
		var k = 0;
		for( i in data){
			var key = i;
			var value = data[key];
			if(value!=null&&value!=''){
				k==0?(k=1):(condition +=" and ");
				condition +=key+' like \''+value+'\'';
			}
		}
		if(condition!=null&&condition!=''){
			condition = 'where '+condition;
		}
	}
	
	ajaxRequest({
		url:baseUrl+"/UserVoAction",
		data:{opt:"queryByCondition",condition:condition},
		type:"POST",
		success:function(response){
			var datas = StringToJson(response);
			delteAllData(show_name,table_id);
			showList(datas,table_id);
		}
	});
}

