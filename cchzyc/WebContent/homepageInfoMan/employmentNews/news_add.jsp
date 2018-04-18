<%@page import="com.hzyc.website.beans.Course"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath %>css/common.css">
<script src="<%=basePath %>plugins/layui/layui.js"></script>
<script src="<%=basePath %>js/jquery1.4.2.js"></script>
<script src="<%=basePath %>js/area.js" charset="UTF-8"></script>
<style type="text/css">
	.row{
		margin-top:10px;
		margin-left:100px;
		width:700px;
		height: 40px;
	}
	.row label{
		position: relative;
		float: left;
	}
	.row div{
		position: relative;
		float: left;
	}
	.hzyc-select{
		width: 160px;
	}
	.hzyc-input{
		width: 160px;
	}
	#province{
		width:80px;
	}
	#city{
		width:80px;
	}
	#payStyle{
		display : none;
	}
	#anniu{
		width:180px;
		height:auto;
		margin:0 auto;
		margin-top:100px;
	}
	#img{
		width:35px;
		height:35px;		
	}
</style>
<script type="text/javascript">
		$(document).ready(function(){
			$("#sub").click(function(){
				document.getElementById("form").submit();
				//window.parent.location.reload();
			});
		});
	</script>
</head>
<body>
<form action="<%=basePath%>epmentCon/insertEmp.hzyc" target="_parent"  method="post" enctype="multipart/form-data" id="form">
	<div id="add_content">
		<div id="position">
			<div class="row">
				<label class="layui-form-label" ></label>
			</div>
			<div class="row">
				<label class="layui-form-label" >生活照</label>
				<div><input type="file" class="hzyc-input" name="img1"/>
				</div>
				<label class="layui-form-label" >学员名</label>
				<div><input type="text" name="stuName" class="hzyc-input"  id="stuName" /></div>
			</div>
			<div class="row">
				<label class="layui-form-label" >公司logo</label>
				<div><input type="file" class="hzyc-input" name="img2"/>
				</div>
				<label class="layui-form-label" >职位</label>
				<div><input type="text" name="position" class="hzyc-input"  id="lifePhotoName" /></div>
			</div>
		</div>
	</div>
	<div id="anniu">
		<ul>
			<li><input type="button"  id="sub" value="保存"/></li>
			<li><input type="reset" value="重置"/></li>
		</ul>
	</div>
	</form>
</body>
</html>