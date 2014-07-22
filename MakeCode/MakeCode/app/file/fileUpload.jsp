<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-2.0.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/ajaxfileupload.js"></script>
<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
var uploadUrl = baseUrl+"/FileAction_fileUpload";
var AjaxUploadUrl = baseUrl+"/FileAction_struts2FileUpload";
function upload(){
	form.action = uploadUrl;
	form.submit();
}

function ajaxUpload(fileId){
	var importFileAction = AjaxUploadUrl;
	if(fileId==null){
		fileId =  "fileUpload";
	}
	var fileInputObj = jQuery('#'+fileId);
	if(fileInputObj.val()==null||fileInputObj.val()==''){
		alert('导入提示','请选择文件');
		return false;
	}
	//增加锁屏功能
	jQuery.ajaxFileUpload({
		url:importFileAction,
		secureuri:false,
		fileElementId:fileId,
		dataType: 'text',
		success: function (data, status){
				alert(data);			
		},
		error: function (data, status, e){
		}
	});
}
function resetImage(){
	var fileObj = jQuery('#imageUpload')[0];
	fileObj.value = "";
	//获取浏览器版本
	var browserVersion= window.navigator.userAgent.toUpperCase();
	if(browserVersion.indexOf("MSIE")>-1){                        
        fileObj.select();
        document.selection.clear();
    }                
    fileObj.outerHTML=fileObj.outerHTML;
    jQuery('#divPreviewNew').remove();
    jQuery('#imgHeadPhoto').attr("src","");
}
</script>
<style type="text/css">
.fileTable{border: 1px solid #BBBBBB;border-collapse: collapse;}
.fileTable tr{border: 1px solid #BBBBBB;}
.fileTable tr td{border: 1px solid #BBBBBB;font-size: 14px;}
.filecss{width: 150px;}
.buttoncss{width: 80px;}
</style>
</head>
<body>
<table class="fileTable">
	<tr>
		<td style="vertical-align: top;width: 350px;">
			<center><h3>文件上传</h3></center>
			<form action="" name="form" method="post" enctype="multipart/form-data"> 
				<table class="fileTable">
					<tr>
						<td>一般单文件上传</td>
						<td><input class="filecss" type="file" name="fileUpload" value=""></td>
					</tr>
					<tr>
						<td><input class="buttoncss" type="button" value="上传" onclick="upload()"></td>
						<td><input class="buttoncss" type="reset"></td>
					</tr>
				</table>
			</form>
			<br>
			<div style="height: 50px;width: 100%;">文件：${message }</div>
			<form action="" name="ajaxForm" method="post" enctype="multipart/form-data"> 
				<table class="fileTable">
					<tr>
						<td>Ajax单文件上传</td>
						<td><input class="filecss" type="file" id="fileUpload" name="fileUpload" value=""></td>
					</tr>
					<tr>
						<td><input class="buttoncss" type="button" value="上传" onclick="ajaxUpload()"></td>
						<td><input class="buttoncss" type="reset"></td>
					</tr>
				</table>
			</form>
			<br><br>
		</td>
		<td style="width: 400px;vertical-align: top;">
			<center><h3>图片预览、上传</h3></center>
			<form action="" name="ajaxForm" method="post" enctype="multipart/form-data"> 
				<table class="fileTable">
					<tr>
						<td>图片上传</td>
						<td><input class="filecss" type="file" onchange="previewImage()" id="imageUpload" name="fileUpload" value=""></td>
					</tr>
					<tr>
						<td><input class="buttoncss" type="button" value="上传" onclick="ajaxUpload('imageUpload')"></td>
						<td><input class="buttoncss" type="button" value="重置" onclick="resetImage()"></td>
					</tr>
				</table>
			</form>
			<div id="divPreview" style="width: 350px; height: 200px; border: solid 1px #d2e2e2;">
				<img id="imgHeadPhoto" style="width: 350px; height: 200px;" src=""  alt="" />
			</div>
		</td>
	</tr>
</table>
<script type="text/javascript">
function previewImage(){
	var fileTypeExts ="jpg,bmp,gif,png"; //文件类型
	var fileObj = jQuery('#imageUpload')[0];
	var imageObj = jQuery('#imgHeadPhoto')[0];
	var divPreviewObj = jQuery('#divPreview')[0];
	var filePath = fileObj.value;
	if(filePath==""||filePath==null){
		return false;
	}
	fileTypeExts = fileTypeExts.toLowerCase();
	var fileType = filePath.substring(filePath.lastIndexOf(".")+1).toLowerCase();
	
	//获取浏览器版本
	var browserVersion= window.navigator.userAgent.toUpperCase();
	if(fileTypeExts.indexOf(fileType)<0){
		alert("仅支持"+fileTypeExts+"为后缀名的文件!");
		fileObj.value = "";
		if(browserVersion.indexOf("MSIE")>-1){                        
            fileObj.select();
            document.selection.clear();
        }                
        fileObj.outerHTML=fileObj.outerHTML;
		return false;
	}
	
	//HTML5实现预览，兼容chrome、火狐7+等
	if(fileObj.files){
		if(window.FileReader){
            var reader = new FileReader(); 
            reader.onload = function(e){
            	imageObj.setAttribute("src",e.target.result);
            };  
            reader.readAsDataURL(fileObj.files[0]);
        }else if(browserVersion.indexOf("SAFARI")>-1){
            alert("不支持Safari6.0以下浏览器的图片预览!");
        }
	}else if (browserVersion.indexOf("MSIE")>-1){ //IE浏览器
		var ieMode = document.documentMode; //获取IE当前页面运行的文档模式（不一定是浏览器版本）
		if(ieMode==6){
			imageObj.setAttribute("src",fileObj.value);
		}else if(ieMode==7||ieMode==8||ieMode==9){
			fileObj.select();
			if(ieMode==9){
				fileObj.blur();
			}
			var newPreviewObj = document.getElementById(divPreviewObj.id+"New");
			if(newPreviewObj==null){
				newPreviewObj = document.createElement("div");
				newPreviewObj.setAttribute("id",divPreviewObj.id+"New");
				newPreviewObj.style.width = imageObj.width+"px";
				newPreviewObj.style.height = imageObj.height+"px";
				newPreviewObj.style.border="solid 1px #d2e2e2";
			}
			newPreviewObj.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";
			divPreviewObj.parentNode.insertBefore(newPreviewObj,divPreviewObj);
			divPreviewObj.style.display="none"; 
		}
	}else if(browserVersion.indexOf("FIREFOX")>-1){ //FireFox火狐浏览器
		var firefoxVersion= parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);
        if(firefoxVersion<7){//firefox7以下版本
        	imageObj.setAttribute("src",fileObj.files[0].getAsDataURL());
        }else{//firefox7.0+                    
        	imageObj.setAttribute("src",window.URL.createObjectURL(fileObj.files[0]));
        }
	}else{ //其他浏览器版本
		imageObj.setAttribute("src",fileObj.value);
	}
}
</script>
</body>
</html>