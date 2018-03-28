<%@page import="com.hzyc.website.beans.Course"%>
<%@page import="java.util.List"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath%>css/common.css" >
<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js" charset=”utf-8″></script>
<script  type="text/javascript" src="<%=basePath%>plugins/layer/layer.js" charset=”utf-8″></script>
<script type="text/javascript" src="<%=basePath%>plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery1.4.2.js" charset=”utf-8″></script>
 <style type="text/css">
 	.omit {
		max-width: 110px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		}
 </style>
</head>
<body>
	<% 
		List<Course> cList = (List<Course>)request.getAttribute("cList");
	%>
	<form action="" method="post"  id="form">
		<table class="layui-table" id="test">
	        <thead>
	            <tr>
	            	<th>ID</th>
	                <th>图标</th>
	                <th>课程名</th>
	                <th>标题</th>
	                <th>应用于</th>
	                <th>描述</th>
	                <th>操作</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<% 
	        		if (cList != null && cList.size() > 0) {
	        			for (int i=0; i<cList.size(); i++) {
	        	%>	
				        	<tr>
				        		<td><%=cList.get(i).getId()%></td>
				        		<td><%=cList.get(i).getIcon()%></td>
				        		<td><%=cList.get(i).getCourseName()%></td>
				        		<td><%=cList.get(i).getTitle()%></td>
				        		<td><%=cList.get(i).getApplication()%></td>
				        		<td class="omit"><%=cList.get(i).getDescribe()%></td>
				        		<td>
				        		<button id="detail" onclick="detail('<%=cList.get(i).getId() %>')" class="layui-btn layui-btn-small layui-btn-normal" style="margin:0" >详细</button>
				        		<button id="upd" class="layui-btn layui-btn-small layui-btn-warm" style="margin:0" >修改</button>
				        		</td>
				        	</tr>
	        	<%		
	        			}
	        		} else {
	        	%>
	        	<tr>
	       			<td colspan="10" align="center">暂无查询数据</td>
	       		</tr>
	        	<%	
	        		}
	        	%>
	        </tbody>
	        <tfoot>
	        </tfoot>
		</table>
		<script type="text/javascript">
		function 
	$(document).ready(function(){
		$("#upd").click(function(){
			parent.layer.open({
			      type: 2,
			      title: '招聘信息修改',
			      maxmin: true,
			      name:'add',
			      shadeClose: false, //点击遮罩关闭层
			      area : ['900px' , '520px'],
			      content: '<%=basePath%>recInfCon/recInfUpdate.hzyc'
			});
		});
		
	});
	</script>
	</form>
</body>
</html>