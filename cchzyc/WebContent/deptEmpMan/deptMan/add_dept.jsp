<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="../../WEB-INF/dict.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	String fCode = request.getParameter("flag");
%>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath%>css/common.css" >
<!-- 
	<script src="<%=basePath%>plugins/layui/layui.js"></script>
 -->
<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js" charset=”utf-8″></script>
<script  type="text/javascript" src="<%=basePath%>plugins/layer/layer.js" charset=”utf-8″></script>
<script type="text/javascript" src="<%=basePath%>plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery1.4.2.js" charset=”utf-8″></script>

<style>
	.layui-input{
    		width:200px;
    		height:30px;
    		display: inline;	
    }

</style>
<script>
	//加载一级部门
	function initDept1(){
		htmlobj=$.ajax({url:"<%=basePath%>deptCon/selLevel1.hzyc",async:false});
		var value = htmlobj.responseText;
		var dept1 = eval(value);
		var select = document.getElementById("dept1");
		//默认的全部文本
		var defaultOption = document.createElement("option");
		for(var i = 0 ; i < dept1.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = dept1[i].deptName ;
			newOption.value = dept1[i].deptCode;
			select.appendChild(newOption);
		}
	}
	//加载二级部门
	function initDept2(select){
		//这种方式其他浏览器不可取
		var select1 = document.getElementById("dept1").value;
		htmlobj=$.ajax({url:"<%=basePath%>deptCon/selLevel2.hzyc?code="+select1,async:false});
		var value = htmlobj.responseText;
		var dept2 = eval(value);
		var select = document.getElementById("dept2");
		//清除子节点
		$("#dept2").empty();
		//清空职位表
		$("#job").empty();
		//默认的全部文本
		var defaultOption = document.createElement("option");
		defaultOption.innerText = "全部";
		defaultOption.value = "0";
		select.appendChild(defaultOption); 
		for(var i = 0 ; i < dept2.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = dept2[i].deptName ;
			newOption.value = dept2[i].deptCode;
			select.appendChild(newOption);
		}
	}
	//加载二级部门的职位
	function initJob(){
		var select2 = document.getElementById("dept2").value;
		htmlobj=$.ajax({url:"<%=basePath%>deptCon/selJob.hzyc?code="+select2,async:false});
		var value = htmlobj.responseText;
		var job = eval(value);
		var select = document.getElementById("job");
		//清除子节点
		$("#job").empty();
		//默认的全部文本
		var defaultOption = document.createElement("option");
		defaultOption.innerText = "全部";
		defaultOption.value = "0";
		select.appendChild(defaultOption); 
		for(var i = 0 ; i < job.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = job[i].jobName ;
			newOption.value = job[i].jobCode;
			select.appendChild(newOption);
		}
		
		
	}
	
	
	$(document).ready(function(){
		$("#addEj").click(function(){
			if($("#tr2").css('display')=='none'){
				$("#tr2").css('display','block'); 
				$("#dept1").css('display','block'); 
				$("#depttext1").css('display','none'); 
				$("#deptTwo").attr("required", "true");
				$("#depttext1").removeAttr("required");
			}else{
				$("#tr2").css('display','none'); 
				$("#dept1").css('display','none'); 
				$("#depttext1").css('display','block');
				$("#deptTwo").attr("required", "false");
				$("#depttext1").removeAttr("required");
			}
			
		});
	});
	layui.use('layer', function(){
		  var layer = layui.layer;
		  var code = <%=fCode%> ;
		  if(code != null && code == 1){
			  layer.msg('添加成功 ！！');
		  }
		  if(code != null && code == 2){
			  layer.msg('添加失败了 ！！');
		  }
	}); 
	
</script>
<style>
	.layui-form-label{
		width:auto;
	}
	*{
		font-size:18px;
	}
	table td{
		padding:8px 10px;
	}
	table tr{
		display:block;
		margin:15px;
		margin-top: 25px;
	}
		
	table #tr2{
		display:none;
	}
	#depttext1{
		display:block;
	}
	#dept1{
		display:none;
	}
	label{
		font-size:18px;
	}
	.wrapper1{
		width:800px;
		height:300px;
		display:block;
		text-align:center;
	}
	.wrapper1 .tablewpapper1{
			width:800px;
	        height:350px;
			text-align:center;
			margin:auto;
			margin-top:40px;
			border:1px solid #FF5722;background:#fffff7;
	}
	body{
		text-align:center;
		margin-top: 30px;
		}
</style>
</head>
<body onload="initDept1()" >
		 <div class="wrapper1">
		<form action="<%=basePath%>deptEmpCon/addDept.hzyc"  method="post" class="layui-form" id="addDeptId">
		  <table class="tablewpapper1">
		  <!--部门名称默认显示的下拉框形式的，点击后面的按钮可以让下拉框变成输入框，  -->
		  	<tr>
		  		<td><label >一级部门:</label></td>
		  		<td><input type="text" id="depttext1" class="layui-input" name="deptOne" required  lay-verify="required"/>
		  			<select id="dept1" class="hzyc-select" name="deptOneCode"></select>
		  		</td>
		  		<td>	
		  			<button class="layui-btn" type="button" id="addEj" style="margin-left:50px;">添加二级部门</button>  
				</td>
		  	</tr>
		  	<tr id="tr2">
		  		<td><label >二级部门:</label></td>
		  		<td><input type="text" class="layui-input" id="deptTwo" name="deptTwo" /></td>
		  	</tr>
	  		<tr>
		  		<td><label >部门描述:</label></td>
		  		<td>
				      <textarea name="remark" placeholder="请输入内容" required  lay-verify="required" class="layui-textarea" style="width:550px;"></textarea>
				</td>
		  	</tr>
		  		<tr>
		 			<td style="text-align: center;">
		 				<button class="layui-btn" type="submit" style="margin-left:50px;">提交</button>  
			 		</td>
		 		</tr>
		  </table>
	</form>
		</div>
</body>
</html>