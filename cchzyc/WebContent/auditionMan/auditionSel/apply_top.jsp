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
<script src="<%=basePath%>plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery1.4.2.js" charset=”utf-8″></script>
<style>
	.layui-input{
    		width:200px;
    		height:30px;
    		display: inline;	
    }
	.layui-form-label{
		width:auto;
	}
	*{
		font-size:12px;
	}
	table td{
		padding:8px 10px;
	}
	form{
		margin-top:10px;
	}
</style>
</head>
<body>
	<div style="width:100%;height:30px;background-color: #f2f2f2;line-height:30px;font-size:13px;color:#444;padding-left:10px;">
		当前位置 >> 试听申请管理 >> 试听学员查询   &nbsp;&nbsp;<span style="color:#d00;font-size:13px;"> [提示 : 系统将会自动删除一个月前报名学员信息]</span>
	</div>
	<form action="<%=basePath%>audCon/auditionSel.hzyc"  method="post" target="result" id="form">
		  <table style="text-align: center" cellpadding="10" cellspacing="15">
		  	<tr>
		  		<td><label >姓名</label></td>
		  		<td><input type="text" name="name" class="layui-input" name="name"/></td>
		  		<td><label >电话 </label></td>
		  		<td><input type="text" name="tel" class="layui-input" name="tel"/></td> 
		  		<td><label >学校</label> </td> 
		 		<td><ex:dict type="university"  name="school" defaultvalue="全部" classname="layui-input" /></td>
		 		<td><label >学院 </label></td> 
		 		<td><ex:dict type="school_dept" name="stuDept"  defaultvalue="全部" classname="layui-input" /></td>
		  	</tr>
		  	<tr>
		  		<td><label >起始时间 </label></td>
		  		<td><input placeholder="点击展开日期" name="stime"  lay-verify="required|date"   class="layui-input" id="inputDate1"/></td>
		 		<td><label >截止时间</label> </td>
		 		<td><input placeholder="点击展开日期" name="etime"  lay-verify="required|date"   class="layui-input" id="inputDate2"/></td> 
		 		<td><label >申请课程 </label></td>
		 		<td>
		 			<ex:dict type="class_type" name="applyLesson" defaultvalue="全部" classname="layui-input" />
		 		</td>
		 		<td colspan="2"  style="text-align:left" >
		 			<input type="submit" value="查询" />
		 			<input type="reset"  onclick="javascript:if(confirm('重置所有选项吗?')) return; return false;" value="重置" />
		 		</td>
		  	</tr>
		  </table>
	</form>
	<div id="iframe-wrapper" style="width:100%;height:375px">
		<iframe src="result.jsp"  name="result" id="result" frameborder="0" scrolling="no" style="height:375px;margin-top:20px">
		
		</iframe>
	</div>
	
	
	
	<script>
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#inputDate1' //指定元素
	  });
	//执行一个laydate实例
	  laydate.render({
	    elem: '#inputDate2' //指定元素
	  });
	});
	//默认查询一次
	document.getElementById("form").submit();
	//默认iframe大小
	document.getElementById("result").style.width = document.body.clientWidth +"px";
	
	function  formSubmit(){
		
		document.getElementById("form").submit();
	}
	</script>
</body>
</html>