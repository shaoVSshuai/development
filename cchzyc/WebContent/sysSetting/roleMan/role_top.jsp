<%@page import="com.hzyc.website.beans.Dictionary"%>
<%@page import="com.hzyc.website.system.Dict"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<script src="<%=basePath %>js/jquery1.4.2.js"></script>
<script>
	$(document).ready(function(){
		  $("#add").click(function(){
			  //获得窗口的垂直位置 
			  	var iWidth=620;                          //弹出窗口的宽度; 
           		var iHeight=420; 
	            var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
	            //获得窗口的水平位置 
	            var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
	            window.open("add.jsp", "_blank", 'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft +
	            ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no'); 
		  });
	});


</script>
</head>
<body>
        
	<div class="box_border">
			<div class="box_top"><b class="pl15">查询条件</b></div>
	</div>
	<div class="wrapper" style="width:1150px;">
		<form name="myForm" action="<%=basePath%>sysCon/selRole.hzyc" target="bottom" method="post">
		<table class="form_table" border="0" cellpadding="0" cellspacing="0">
	        <tr style="height:60px;">
	          <td>角色编号</td>
	          <td >
				<input type="text"  name="jobCode"/>
			  </td>
			   <td>角色名称</td>
	          <td >
				<input type="text" name="jobName" />
			  </td>
	          <td>
	          	<input type="checkbox"   value="1" name="isMohu" />模糊
	          </td>
	          <td rowspan="2" style="vertical-align: middle" >
	          		<input type="submit" name="button" class="btn btn82 btn_search" value="查询" style="">
	          		<input type="reset" onclick="return confirm('重置所有选项吗?');" name="button" class="btn btn82 btn_res" value="重置" style="margin-left:10px;">
	       			<input type="button" id="add" class="btn btn82 btn_search" value="新增 "  style="">
	          </td>
	        </tr>
	        
	   </table>
	   
	   </form>
   </div>
</body>
</html>