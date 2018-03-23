<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="../../WEB-INF/tags/dict.tld"%>
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
%>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath%>css/common.css" >
<!-- 
	<script src="<%=basePath%>plugins/layui/layui.js"></script>
 -->
<script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.js" charset=”utf-8″></script>
<script  type="text/javascript" src="<%=basePath%>plugins/layer/layer.js" charset=”utf-8″></script>
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
		var judgeShow=document.getElementById("judgeShow").innerHTML;
		if(judgeShow=='dept1'){
			
		var getdepts=document.getElementById("dept1Show");
			getdepts.style.display="block";
			
			
			
		}else if(judgeShow=='dept2'){
			var getdepts=document.getElementById("dept2Show");
			getdepts.style.display="block";
			
		}else if(judgeShow=='job'){
			var getdepts=document.getElementById("JobShow");
			getdepts.style.display="block";
		}else if(judgeShow=='employee'){
			var getdepts=document.getElementById("employeeShow");
			getdepts.style.display="block";
			
			
		}
		
		htmlobj=$.ajax({url:"<%=basePath%>deptCon/selLevel1.hzyc",async:false});
		var value = htmlobj.responseText;
		var dept1 = eval(value);
		var select = document.getElementById("dept1");
		//默认的全部文本
		var defaultOption = document.createElement("option");
		defaultOption.innerText = "全部";
		defaultOption.value = "0";
		select.appendChild(defaultOption);
		for(var i = 0 ; i < dept1.length ; i++){
			var newOption = document.createElement("option");
			newOption.innerText = dept1[i].deptName ;
			newOption.value = dept1[i].deptCode;
			select.appendChild(newOption);
		}
		/*判断传过来的值是职位/工作/员工*/
		var judgeShow=document.getElementById("judgeShow").innerHTML;
		if(judgeShow=='dept1'){
			
			alert("这里是要展示一级部门的界面");
			
			
			
			
		}else if(judgeShow=='dept2'){
			
			alert("这里是要展示二级部门的界面");
			
		}else if(judgeShow=='job'){
			
			alert("这里是要展示工作的界面");
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
		$("#add").click(function(){
			 layer.open({
			      type: 2,
			      title: '添加新员工',
			      maxmin: true,
			      shadeClose: false, //点击遮罩关闭层
			      area : ['900px' , '520px'],
			      content: 'add.jsp'
			});
		});
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
	        height:350px;
			text-align:center;
			margin:auto;
			margin-top:40px;
			border:1px solid #FF5722;background:#fffff7;
	}
	body{
		text-align:center;
		margin-top: 30px
		}
</style>
</head>
<body onload="initDept1()" >
		<!--这里面分三/四种样式展示:1:当点击一级部门的时候----------①一级部门编号②部门名称③部门描述
							       2:当点击二级部门的时候----------①二级部门编号②部门名称③部门描述	④更改所属的一级部门
							       3:当点击工作的时候--------------①职位编号         ②工作名称③工作描述	④更改所属的直属部门
       /*这个职责不属于部门管理*/	   4:当点击员工的时候--------------①
		  -->
		<div style="display:none;" id="judgeShow" ><%=request.getAttribute("judgeShow")%></div>
		
		<!--这里是对一级部门的操作  -->
		<div class="wrapper1" style="display:none" id="dept1Show">
				<form action="Updatedept.hzyc"  method="post" target="result">
				  <table class="tablewpapper1">
				  	<tr style="margin-top: 25px">
				  		<td><label >一级部门编号:</label></td>
				  		<td><input type="text" class="layui-input" name="name" value="A1" disabled /></td>
				  	</tr>
				  	<tr style="margin-top: 25px">
				  		<td><label >部门名称:</label></td>
				  		<td><input type="text" class="layui-input" name="name" value="人力资源部" /></td>
				  	</tr>
			  		<tr style="margin-top: 25px">
				  		<td><label >部门描述:</label></td>
				  		<td><input type="text" class="layui-input" name="name" /></td>
				  	</tr>
				  	<tr style="margin-top: 25px">
				  		<td><label >选择部门:</label></td>
				  		<td>
							<select style="width:200px;height:30px;">
								<option>人力资源部</option>
								<option>校企合作部</option>
								<option>长春培训部</option>
								<option>吉林培训部</option>
							</select>
						</td>
				  	</tr>
				  		<tr style="margin-top: 40px">
				 		<td style="text-align: center;" >
				 			<input type="submit" value="修改" style="margin-left:50px;"/>
				 		</td>
				 		<td  style="text-align: center;">
				 			<input type="submit" value="删除" style="margin-left:55px;"/>
				 		</td>
				 		</tr>
				  </table>
			</form>
		</div>
		
		<!--这里是对二级部门的操作  -->
		<div class="wrapper1" style="display:none" id="dept2Show">
				<form action="Updatedept.hzyc"  method="post" target="result">
				  <table class="tablewpapper1">
				  	<tr style="margin-top: 25px">
				  		<td><label >二级部门编号:</label></td>
				  		<td><input type="text" class="layui-input" name="name" value="A1" disabled /></td>
				  	</tr>
				  	<tr style="margin-top: 25px">
				  		<td><label >部门名称:</label></td>
				  		<td><input type="text" class="layui-input" name="name" value="人力资源部" /></td>
				  	</tr>
			  		<tr style="margin-top: 25px">
				  		<td><label >部门描述:</label></td>
				  		<td><input type="text" class="layui-input" name="name" /></td>
				  	</tr>
				  	<tr style="margin-top: 25px">
				  		<td><label >选择部门:</label></td>
				  		<td>
							<select style="width:200px;height:30px;">
								<option>人力资源部</option>
								<option>校企合作部</option>
								<option>长春培训部</option>
								<option>吉林培训部</option>
							</select>
						</td>
				  	</tr>
				  		<tr style="margin-top: 40px">
				 		<td style="text-align: center;" >
				 			<input type="submit" value="修改" style="margin-left:50px;"/>
				 		</td>
				 		<td  style="text-align: center;">
				 			<input type="submit" value="删除" style="margin-left:55px;"/>
				 		</td>
				 		</tr>
				  </table>
			</form>
		</div>
		
		
		<!--这里是对工作的的修改操作  -->
		<div class="wrapper1" style="display:none" id="JobShow">
				<form action="Updatedept.hzyc"  method="post" target="result">
				  <table class="tablewpapper1">
				  	<tr style="margin-top: 25px">
				  		<td><label >工作编号:</label></td>
				  		<td><input type="text" class="layui-input" name="name" value="A1" disabled /></td>
				  	</tr>
				  	<tr style="margin-top: 25px">
				  		<td><label >部门名称:</label></td>
				  		<td><input type="text" class="layui-input" name="name" value="人力资源部" /></td>
				  	</tr>
			  		<tr style="margin-top: 25px">
				  		<td><label >部门描述:</label></td>
				  		<td><input type="text" class="layui-input" name="name" /></td>
				  	</tr>
				  	<tr style="margin-top: 25px">
				  		<td><label >选择部门:</label></td>
				  		<td>
							<select style="width:200px;height:30px;">
								<option>人力资源部</option>
								<option>校企合作部</option>
								<option>长春培训部</option>
								<option>吉林培训部</option>
							</select>
						</td>
				  	</tr>
				  		<tr style="margin-top: 40px">
				 		<td style="text-align: center;" >
				 			<input type="submit" value="修改" style="margin-left:50px;"/>
				 		</td>
				 		<td  style="text-align: center;">
				 			<input type="submit" value="删除" style="margin-left:55px;"/>
				 		</td>
				 		</tr>
				  </table>
			</form>
		</div>
		
		
		
		<!--这里是对员工的展示操作  -->
		<div class="wrapper1" style="display:none" id="employeeShow" >
				<form action="Updatedept.hzyc"  method="post" target="result">
				  <table class="tablewpapper1">
				  	<tr style="margin-top: 25px">
				  		<td><label >员工编号:</label></td>
				  		<td><input type="text" class="layui-input" name="name" value="A1" disabled /></td>
				  	</tr>
				  	<tr style="margin-top: 25px">
				  		<td><label >部门名称:</label></td>
				  		<td><input type="text" class="layui-input" name="name" value="人力资源部" /></td>
				  	</tr>
			  		<tr style="margin-top: 25px">
				  		<td><label >部门描述:</label></td>
				  		<td><input type="text" class="layui-input" name="name" /></td>
				  	</tr>
				  	<tr style="margin-top: 25px">
				  		<td><label >选择部门:</label></td>
				  		<td>
							<select style="width:200px;height:30px;">
								<option>人力资源部</option>
								<option>校企合作部</option>
								<option>长春培训部</option>
								<option>吉林培训部</option>
							</select>
						</td>
				  	</tr>
				  		<tr style="margin-top: 40px">
				 		<td style="text-align: center;" >
				 			<input type="submit" value="修改" style="margin-left:50px;"/>
				 		</td>
				 		<td  style="text-align: center;">
				 			<input type="submit" value="删除" style="margin-left:55px;"/>
				 		</td>
				 		</tr>
				  </table>
			</form>
		</div>
		
		
		
		
		
		
		
		
</body>
</html>