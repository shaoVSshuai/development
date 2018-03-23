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
<title>Insert title here</title>
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
    <script src="<%=basePath %>plugins/layui/layui.js"></script>
     <script src="<%=basePath %>js/jquery1.4.2.js"></script>
</head>
<style>
	body .layui-tree-skin-shihuang .layui-tree-branch{color: #EDCA50;font-size: 16px;}
	.layui-tree li a cite{font-size: 16px;}
</style>
<script type="text/javascript">
	function addDept(){
		window.parent.rightFrame.location.href="../deptEmpMan/deptMan/add_dept.jsp";
	}
	function addJob(){
		window.parent.rightFrame.location.href="../deptEmpMan/deptMan/add_job.jsp";
	}
</script>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>
			<button class="layui-btn" style="margin-left:30px;" onclick="addDept()">新增部门</button>  
			<button class="layui-btn" style="margin-left:30px;" onclick="addJob()">新增职位</button>
  </legend>
</fieldset>
<ul id="demo1"></ul> 
<script>
//Demo
layui.use(['tree', 'layer'], function(){
  var layer = layui.layer
  ,$ = layui.jquery; 
  
  layui.tree({
    elem: '#demo1' //指定元素
    ,skin: 'shihuang'
    ,target: 'rightFrame' //是否新选项卡打开（比如节点返回href才有效）
    ,click: function(item){ //点击节点回调
    }
    ,nodes: [ 
             <%=request.getAttribute("erwei")%>
    ]
  });
});

</script>
	
	
	
	
	
</body>
</html>