<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
 <link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
    <script src="<%=basePath %>plugins/layui/layui.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/common.css" >
    <style>
    	ul{
    		list-style:none;
    	}
    	ul li{
    		display: inline;
    	}
    	.layui-input{
    		width:200px;
    		height:30px;
    		display: inline;	
    	}
    	.layui-form-label {
		    float: left;
		    display: block;
		    padding: 9px 15px;
		    font-weight: 400;
		    width:auto;
		    text-align: right;
		}
		.file {
		    position: relative;
		    display: inline-block;
		    background: #D0EEFF;
		    border: 1px solid #99D3F5;
		    border-radius: 4px;
		    padding: 4px 12px;
		    overflow: hidden;
		    color: #1E88C7;
		    text-decoration: none;
		    text-indent: 0;
		    line-height: 20px;
		}
		.file input {
		    position: absolute;
		    font-size: 100px;
		    right: 0;
		    top: 0;
		    opacity: 0;
		}
		.file:hover {
		    background: #AADFFD;
		    border-color: #78C3F3;
		    color: #004974;
		    text-decoration: none;
		}
    </style>
</head>
<body>
    <div id="position" style="height: 30px; background-color: rgb(242, 242, 242); line-height: 30px; font-size: 13px; color: rgb(68, 68, 68); padding-left: 10px; width: 1151px;">
		当前位置 &gt;&gt; 部门员工管理&gt;&gt; 部门维护
	</div>
</body>
</html>