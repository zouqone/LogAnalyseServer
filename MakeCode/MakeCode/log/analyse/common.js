/*================居中显示对象==================*/

/**
 * 设置对象在X方向居中显示
 * @param msgObj
 */
function setObjMiddleX(msgObj){
	if(msgObj){
		var msgWidth = msgObj.scrollWidth;  
	    var bgLeft=window.pageXOffset || document.documentElement.scrollLeft|| document.body.scrollLeft || 0;     
	    var bgWidth=document.documentElement.clientWidth || document.body.clientWidth || 0;   
	    var msgLeft=0; 
	    if(bgWidth>msgWidth){
	    	msgLeft=bgLeft+Math.round((bgWidth-msgWidth)/2);
	    }else{
	    	msgLeft=bgLeft+10;
	    }
	    msgObj.style.position = "absolute";  
	    msgObj.style.left  = msgLeft+"px";  
	}
}

/**
 * 设置对象在Y方向居中显示
 * @param msgObj
 */
function setObjMiddleY(msgObj){
	if(msgObj){
		var msgHeight= msgObj.scrollHeight;  
	    var bgTop=window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;  
	    var bgHeight=document.documentElement.clientHeight || document.body.clientHeight || 0;  
	    var msgTop=0;  
	    if(bgHeight>msgHeight){
	    	msgTop=bgTop+Math.round((bgHeight-msgHeight)/2);
	    }else{
	    	msgTop=bgTop+10;
	    }
	    msgObj.style.position = "absolute";  
	    msgObj.style.top      = msgTop+"px";  
	}
}

/**
 * 获取浏览器的高度和宽度（包含滚动条的长度）
 * @returns {___anonymous1442_1467}
 */
function getPageSize(){
	var bgHeight=document.documentElement.clientHeight || document.body.clientHeight || 0;
	var bgWidth=document.documentElement.clientWidth || document.body.clientWidth || 0;  
	return {"x":bgWidth,"y":bgHeight};
}

/**
 * 获取浏览器的被滚动条卷去高度和宽度（滚动条的长度）
 * @returns {___anonymous1795_1816}
 */
function  getPageScrollSize(){
	var bgTop=window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
	var bgLeft=window.pageXOffset || document.documentElement.scrollLeft|| document.body.scrollLeft || 0;
	return {"x":bgLeft,"y":bgTop};
}

/**
 * 取得html元素的距离浏览器绝对位置（不包含滚动条的长度）
 * @param e
 * @returns {___anonymous1333_1348}
 */
function getAbsPoint(e){
	  var x = e.offsetLeft;
	  var y = e.offsetTop;
	  while(e = e.offsetParent){
		x += e.offsetLeft-e.scrollLeft;
	    y += e.offsetTop-e.scrollTop;
	  }
	  return {"x": x, "y": y};
};

/**
 * 去掉字符串前后空格
 * @param str
 * @returns
 */
function trim(str){   
    str = str.replace(/^(\s|\u00A0)+/,'');   
    for(var i=str.length-1; i>=0; i--){   
        if(/\S/.test(str.charAt(i))){   
            str = str.substring(0, i+1);   
            break;   
        }   
    }   
    return str;   
}







