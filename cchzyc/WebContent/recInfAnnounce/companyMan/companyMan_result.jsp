<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询结果</title>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<script src="<%=basePath%>plugins/layui/layui.js"></script>
<script src="<%=basePath%>/js/jquery1.4.2.js"></script>
<style type="text/css">
	th{
		 white-space:nowrap;overflow:hidden;
	}
	table{
		width:1100px;
	}
</style>

</head>
<body>
	 <table class="layui-table" id="test">
		<thead>
			<tr>
		       	<!-- 
		            	
		        -->
				<th lay-data="{field:'id', width:45, sort: true}" >序列号</th>
				<th lay-data="{field:'compName',width:100}" >公司名</th>
		        <th lay-data="{field:'contPer', width:45}">公司性质</th>
		     	<th lay-data="{field:'tel', width:80}">行业类别</th>
		      	<th lay-data="{field:'posTime', width:80, sort: true}">公司网址</th>
		       	<th lay-data="{field:'position', width:80}">省地址</th>
		      	<th lay-data="{field:'eduBg', width:70}">市地址</th>
		       	<th lay-data="{field:'#', width:80}">详细地址</th>
		       	<th lay-data="{field:'#', width:80}">录入时间</th>
		        <th lay-data="{field:'#', width:80}">公司规模</th>
		        <th lay-data="{field:'wealth', width:115}">操作</th>
		  	</tr>
		</thead>
		<tbody>
			<tr>
		       	<!-- 
		            	
		        -->
				<th style="font-size:12px;">1000</th>
				<th style="font-size:12px;white-space:nowrap;overflow:hidden;">何明集团有限公司</th>
		        <th style="font-size:12px;">私营</th>
		     	<th style="font-size:12px;">计算机</th>
		      	<th style="font-size:12px;">4399.com</th>
		       	<th style="font-size:12px;white-space:nowrap;overflow:hidden;">吉林</th>
		      	<th style="font-size:12px;">吉林市</th>
		       	<th style="font-size:12px;white-space:nowrap;overflow:hidden;">江北龙潭区北华大学六公寓男</th>
		       	<th style="font-size:12px;white-space:nowrap;overflow:hidden;">2017-12-23</th>
		        <th style="font-size:12px;">1000人以上</th>
		        <th style="font-size:12px;">
		        	<button onclick="detail()" class="layui-btn layui-btn-small layui-btn-normal" style="margin:0" >详细</button>
				    <button onclick="upd()" class="layui-btn layui-btn-small layui-btn-warm" style="margin:0" >修改</button>
				    <button onclick="del()" class="layui-btn layui-btn-small layui-btn-danger" style="margin:0" > 删除</button>
				</th>
		  	</tr>
		</tbody>
	</table>
</body>
</html>