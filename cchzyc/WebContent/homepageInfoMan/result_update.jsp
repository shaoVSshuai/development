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
</style>
</head>
<body>
<form action="<%=basePath%>recInfCon/recUpdate.hzyc" target="_parent"  method="post" enctype="multipart/form-data" id="form">
	<div id="add_content">
		<div id="position">
			<div class="row">
				<label class="layui-form-label" >图标</label>
				<div><input type="file" class="hzyc-input"/></div>
				<label class="layui-form-label" >课程名</label>
				<div><input type="text" name="needNumber" class="hzyc-input"  id="recNumber"/></div>
			</div>
			<div class="row">
					<label class="layui-form-label" >标题</label>
					<div><input type="text" name="needNumber" class="hzyc-input"  id="recNumber"/></div>
					<label class="layui-form-label">应用于</label>
					<div><input type="text" name="linkman" class="hzyc-input" id="contactPerson"/></div>
				</div>
			<div class="row" id="welBo">
				<label class="layui-form-label">描述</label>
				<div><textarea cols="40" rows="7" name="posRequire" style="resize:none;" id="workAsk"></textarea></div>
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