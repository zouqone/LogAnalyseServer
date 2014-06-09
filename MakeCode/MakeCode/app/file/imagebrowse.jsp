<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/ui-lightness/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>

<style type="text/css">
#preview_wrapper{   
    display:inline-block;   
    width:300px;   
    height:300px;   
    background-color:#CCC;
}   
#preview_fake{    
    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);   
}   
#preview_size_fake{    
    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);     
    visibility:hidden;   
}   
#preview{    
    width:300px;   
    height:300px;   
}   
</style>

<script type="text/javascript">
function onUploadImgChange(sender){   
    if( !sender.value.match( /.jpg|.gif|.png|.bmp/i ) ){   
        alert('图片格式无效！');   
        return false;   
    }
    var objPreview = document.getElementById('preview'); 
    var localfile=document.getElementById("localfile");
    var objectURL = window.URL.createObjectURL(localfile.files[0]); 
    objPreview.src = objectURL;
}    

</script>

</head>
<body>

<input name="localfile"  type="file" id="localfile" size="28" onchange="onUploadImgChange(this)"/>
 
<!--以下是预览图片用的-->
<div id="preview_wrapper">
<div id="preview_fake">
<img id="preview" src=""/>
</div>
</div>
</body>
</html> 