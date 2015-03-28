<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
var baseUrl = '<%=request.getContextPath()%>';
var url = baseUrl+'/ComponentAction';
function addFunc(){
	ComForm.action = url+'_insert';
	ComForm.submit();
}

</script>
</head>
<body>

<input id="addbutton" onclick="addFunc()" value="新增" type="button" >
<form action="" name="ComForm">
组件编码：<input type="text"  name="code" value=""></td>
组件名称：<input type="text"  name="name" value=""></td>
</form>
</body>
</html>