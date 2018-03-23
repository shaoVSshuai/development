<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘信息查询</title>
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
		        <th lay-data="{field:'contPer', width:45}">联系人</th>
		     	<th lay-data="{field:'tel', width:80}">电话</th>
		      	<th lay-data="{field:'posTime', width:80, sort: true}">发布时间</th>
		       	<th lay-data="{field:'position', width:80}">职位</th>
		      	<th lay-data="{field:'eduBg', width:70}">学历</th>
		       	<th lay-data="{field:'#', width:80}">公司性质</th>
		       	<th lay-data="{field:'#', width:80}">所属行业</th>
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
		        <th style="font-size:12px;">何明逗逼</th>
		     	<th style="font-size:12px;">13244231234</th>
		      	<th style="font-size:12px;">2017-10-05</th>
		       	<th style="font-size:12px;white-space:nowrap;overflow:hidden;">高级框架师</th>
		      	<th style="font-size:12px;">大学</th>
		       	<th style="font-size:12px;white-space:nowrap;overflow:hidden;">外商独资/办事处</th>
		       	<th style="font-size:12px;white-space:nowrap;overflow:hidden;">娱乐休闲</th>
		        <th style="font-size:12px;">1000人以上</th>
		        <th style="font-size:12px;">
		        	<button onclick="detail()" class="layui-btn layui-btn-small layui-btn-normal" style="margin:0" >详细</button>
				    <button id="upd" class="layui-btn layui-btn-small layui-btn-warm" style="margin:0" >修改</button>
				    <button onclick="del()" class="layui-btn layui-btn-small layui-btn-danger" style="margin:0" > 删除</button>
				</th>
		  	</tr>
		</tbody>
	</table>
	<script type="text/javascript">
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
</body>
</html>