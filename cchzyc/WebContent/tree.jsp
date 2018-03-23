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
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath%>css/common.css" >
<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js" charset=”utf-8″></script>
<script  type="text/javascript" src="<%=basePath%>plugins/layer/layer.js" charset=”utf-8″></script>
<script type="text/javascript" src="<%=basePath%>plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery1.4.2.js" charset=”utf-8″></script>

</head>
<style>
	body .layui-tree-skin-shihuang .layui-tree-branch{color: #EDCA50;font-size: 16px;}
	.layui-tree li a cite{font-size: 16px;}
</style>
<body onload="al()">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>生成一个较深的树</legend>
</fieldset>
<ul id="demo1"></ul> 
<script>
//Demo
layui.use(['tree', 'layer'], function(){
  var layer = layui.layer
  ,$ = layui.jquery; 
 var AllVal="666one,666two,666three@777one,777two,777three@888one,888two,888three";
 /*值都在这儿呢！*/

 var arr2=AllVal.split("@"); 
	
	
  layui.tree({

	    elem: '#demo1' //指定元素
	    ,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
	    ,click: function(item){ //点击节点回调
	      layer.msg('当前节名称：'+ item.name + '<br>全部参数：'+ JSON.stringify(item));
	      console.log(item);
	    }
	    ,nodes: [ //节点
	            <%=request.getAttribute("erwei")%>
	    
	    
    ]
  });
});


</script>
		<a href="deptEmpCon/selAllDept.hzyc">得到值</a>
		
		
		
</body>
	<script type="text/javascript">
	function al(){
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.alert("添加成功 ！！");
		}); 
	}
	
</script>	
</html>