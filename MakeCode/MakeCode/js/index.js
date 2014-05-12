

/**
 * 设置iframe的高度，使浏览器的垂直方向不出现滚动条。
 */
function setPageHeight(){
	//获取浏览器的高度
	var windowHeight=document.documentElement.clientHeight || document.body.clientHeight || 0; 
	var log_iframe_obj = document.getElementById("log_iframe");
	var subtop = getAbsPoint(log_iframe_obj).y+10;
	log_iframe_obj.style.height= windowHeight - subtop + "px";
}

//当浏览器变化时，也调整iframe的高度使它适应当前窗口的高度
window.onresize = function(){ 
	setPageHeight();
};

function manageUser(obj){
	if(obj==null){
		return ;
	}
	var log_iframe_obj = document.getElementById("log_iframe");
	var url = obj.getAttribute("url");
	if(url==null){
		url="";
	}
	
	url = baseUrl+""+url;
	log_iframe_obj.setAttribute("src", url);
	
	//修改被选中的按钮的颜色
	var divLogs = obj.parentNode.childNodes;
	if(divLogs!=null&&divLogs.length>0){
		for(var i = 0 ; i < divLogs.length ; i++){
			var divLog = divLogs[i];
			divLog.className="log_item buttonbk";
		}
	}
	obj.className="log_item select_buttonbk";
}

//展现页面
function showLogPage(obj){
	if(obj==null){
		return ;
	}
	var log_iframe_obj = document.getElementById("log_iframe");
	var url = obj.getAttribute("url");
	if(url==null){
		url="";
	}
	url = baseUrl+url;
	log_iframe_obj.setAttribute("src", url);
	
	//修改被选中的按钮的颜色
	var divLogs = obj.parentNode.childNodes;
	if(divLogs!=null&&divLogs.length>0){
		for(var i = 0 ; i < divLogs.length ; i++){
			var divLog = divLogs[i];
			divLog.className="log_item box_shadow buttonbk";
		}
	}
	obj.className="log_item  box_shadow select_buttonbk";
	
}

/**
 * 展现用户模块
 * @param data
 * @returns
 */
function showLogMode(data){
	if(data==null||data.length==0){
		return null;
	}
	var html = "";
	for(var i = 0 ; i< data.length; i++){
		html+='<div class="log_item box_shadow buttonbk" name="log_'+data[i].name+'" onclick="showLogPage(this)" '
			+'url="'+'/LogAction?logType='+data[i].url+'" >'
			+'<p>'+data[i].name+'</p>'
			+'</div>\r\n';
	}
	return html;
}

/**
 * 展现用户模块
 * @returns {String}
 */
function showUserMode(datas){
	if(datas==null||datas.length==0){
		return null;
	}
	var html = "";
	for(var i = 0 ; i< datas.length; i++){
		var data = datas[i];
		html += 
			'<div class="log_item box_shadow buttonbk" name="log_'+data.code+'" onclick="showLogPage(this)" '
			+'url="'+data.url+'" >'
			+'<p>'+data.name+'</p>'
			+'</div>\r\n';
	}
	return html;
}

/**
 * 展现车票模块
 */
function showTicketMode(){
	var ticket = '<div class="log_item box_shadow buttonbk" name="item_'+'ticket'+'" onclick="showLogPage(this)" '
	+'url="'+'/app/kyfw/kyfw.jsp'+'" >'
	+'<p>'+'ticket'+'</p>'
	+'</div>\r\n';
	return ticket;
}

/**
 * 展现模块
 */
function showMode(datas){
	if(datas==null||datas.length==0){
		return null;
	}
	var html = "";
	for(var i = 0 ; i< datas.length; i++){
		var data = datas[i];
		html += 
			'<div class="log_item box_shadow buttonbk" name="item_'+data.code+'" onclick="showLogPage(this)" '
			+'url="'+data.url+'" >'
			+'<p>'+data.name+'</p>'
			+'</div>\r\n';
	}
	return html;
}

function setItem(obj,id,type,data){
	var html = '';
	if(type=="log"){
		if(data==null||data.length==0){
			return ;
		}
		html += showLogMode(data);
	}else if(type == "ticket"){
		
		html += showTicketMode();
	} else if(type == "svn"){
		html += showMode(data);
	}else {
		html += showUserMode(data);
	}
	
	
	var log_item_obj = document.getElementById(id);
	log_item_obj.innerHTML=html;
}

