<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>

<style>
body{
background-color:#9BD5BF;
font-size:12px;
font-family:Arial, Helvetica, sans-serif;
margin:0px;
padding:0px;
color:white;
}
ul,li{
margin:0px;
padding:0px;
}
li{
display:inline;
list-style:none;
list-style-position:outside;
text-align:center;
font-weight:bold;
float:left;
}
a:link{
color:#336601;
text-decoration:none;
float:left;
width:100px;
padding:3px 5px 0px 5px;
}
a:visited{
color:#336601;
text-decoration:none;
float:left;
padding:3px 5px 0px 5px;
width:100px;
}
a:hover{
color:white;
float:left;
padding:3px 3px 0px 20px;
width:88px;
text-decoration:none;
background-color:#539D26;
}
a:active{
color:white;
float:left;
padding:3px 3px 0px 20px;
width:88px;
text-decoration:none;
background-color:#539D26;
}
#nav{
width:600px;
height:30px;
border-bottom:0px;
padding:0px 5px;
position:absolute;
z-index:1;
left: 198px;
top: 25px;
}
.list{
line-height:20px;
text-align:left;
padding:4px;
font-weight:normal;
}
.menu1{
width:120px;
height:auto;
margin:6px 4px 0px 0px;
border:1px solid #9CDD75;
background-color:#F1FBEC;
color:#336601;
padding:6px 0px 0px 0px;
cursor:hand;
overflow-y:hidden;
filter:Alpha(opacity=30);
-moz-opacity:0.7;
}
.menu2{
width:120px;
height:18px;
margin:6px 4px 0px 0px;
background-color:#F5F5F5;
color:#999999;
border:1px solid #EEE8DD;
padding:6px 0px 0px 0px;
overflow-y:hidden;
cursor:hand;
}
</style>

</head>
<body>
<div id="nav">
<ul>
<li class="menu2" onMouseOver="this.className='menu1'" onMouseOut="this.className='menu2'">设置
<div class="list">
<a href="#">我的Png</a><br />
<a href="#">我的Gif</a><br />
<a href="#">我的酷站</a><br />
<a href="#">我的日志</a><br />
<a href="#">我的相册</a><br />
<a href="#">我的收藏</a><br />
<a href="#">我的收藏</a><br />
<a href="#">我的收藏</a><br />
</div>
</li>
<li class="menu2" onMouseOver="this.className='menu1'" onMouseOut="this.className='menu2'">风格
<div class="list">
<a href="#">我的CHINAY</a><br />
<a href="#">我的首页</a><br />
<a href="#">我的日志</a><br />
<a href="#">我的相册</a><br />
<a href="#">我的收藏</a><br />
</div>
</li>
<li class="menu2" onMouseOver="this.className='menu1'" onMouseOut="this.className='menu2'">提醒
<div class="list">
<a href="#">我的CHINAY</a><br />
<a href="#">我的相册</a><br />
<a href="#">我的收藏</a><br />
</div>
</li>
<li class="menu2" onMouseOver="this.className='menu1'" onMouseOut="this.className='menu2'">查询
<div class="list">
<a href="#">我的CHINAY</a><br />
<a href="#">我的首页</a><br />
<a href="#">我的日志</a><br />
<a href="#">我的相册</a><br />
<a href="#">我的收藏</a><br />
<a href="#">我的日志</a><br />
<a href="#">我的相册</a><br />
<a href="#">我的收藏</a><br />
</div>
</li>
</ul>
</div>
</body>
</html>