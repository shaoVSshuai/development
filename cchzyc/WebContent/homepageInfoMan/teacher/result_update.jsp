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
	<%
		Course course = (Course)request.getAttribute("course");
	%>
<form action="<%=basePath%>courseCon/updateCourse.hzyc" target="_parent"  method="post" enctype="multipart/form-data" id="form">
	<input type="hidden" value="<%=course.getId() %>" name="id">
	<div id="add_content">
		<div id="position">
			<div class="row">
				<label class="layui-form-label" >原有图标</label>
				<div><img src="<%=basePath %>/images/course/<%=course.getIconName()%>" id="img"/></div>
			</div>
			<div class="row">
				<label class="layui-form-label" >更换图标</label>
				<div><input type="file" class="hzyc-input" name="img1"/>
						<input type="hidden" name="iconName" value="<%=course.getIconName()%>">
				</div>
				<label class="layui-form-label" >课程名</label>
				<div><input type="text" name="courseName" class="hzyc-input"  id="courseName" value="<%=course.getCourseName()%>"/></div>
			</div>
			<div class="row">
					<label class="layui-form-label" >标题</label>
					<div><input type="text" name="title" class="hzyc-input"  id="title" value="<%=course.getTitle() %>"/></div>
					<label class="layui-form-label">应用于</label>
					<div><input type="text" name="application" class="hzyc-input" id="application" value="<%=course.getApplication()%>"/></div>
				</div>
			<div class="row" id="welBo">
				<label class="layui-form-label">描述</label>
				<div><textarea cols="40" rows="7" name="describe" style="resize:none;" id="courseDescribe" ><%=course.getDescribe() %></textarea></div>
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