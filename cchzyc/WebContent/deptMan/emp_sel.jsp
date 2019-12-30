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
		font-size:12px;
	}
	table td{
		padding:8px 10px;
	}

</style>
</head>
<body onload="initDept1()" >

	<form action="<%=basePath%>empCon/selEmp.hzyc"  method="post" target="result">
		  <table >
		  	<tr>
		  		<td><label  >姓名</label></td>
		  		<td><input type="text" class="layui-input" name="name"/></td>
		  		<td><label  >电话 </label></td>
		  		<td><input type="text" class="layui-input" name="tel"/></td> 
		  		<td><label  >一级部门</label> </td> 
		 		<td><select id="dept1" onchange="initDept2(this)" class="hzyc-select" name="deptOne" > </select></td>
		 		<td><label  >二级部门</label></td> 
		 		<td><select id="dept2" class="hzyc-select" onchange="initJob()" name="deptTwo" ></select></td>
		  	</tr>
		  	<tr>
		  		<td><label  >工号 </label></td>
		  		<td><input   class="layui-input" name="code" /></td>
		 		<td><label  >身份证号</label> </td>
		 		<td><input    class="layui-input" name="idcard"/></td> 
		 		<td><label   >职位 </label></td>
		 		<td>
		 			<select class="hzyc-select" id="job" name="jobCode"></select>
		 		</td>
		 		<td colspan="2" style="text-align: center">
		 			<input type="submit" value="查询" />
		 			<input type="reset"  onclick="javascript:if(confirm('重置所有选项吗?')) return; return false;" value="重置" />
		 			<input type="button" id="add" value="添加" />	
		 		</td>
		  	</tr>
		  </table>
	</form>
	
	<iframe src="emp_result.jsp" name="result" style="width:1130px;height:450px;margin-top:20px">
	
	</iframe>
	
	
	
	
	
</body>
</html>