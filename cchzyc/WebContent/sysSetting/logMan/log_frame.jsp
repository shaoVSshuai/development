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
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
<script src="<%=basePath %>plugins/layui/layui.js"></script>
<script src="<%=basePath %>js/jquery1.4.2.js"></script>
<script>
	function del(){
		var date1 = document.getElementById("test1").value;
		var date2 = document.getElementById("test2").value;
		if(date1.trim() != '' &&  date2.trim() != ''){
			var ymd = date1.split("-");
			var newdate1 = ymd[0]+ymd[1]+ymd[2];
			var ymd2 = date2.split("-");
			var newdate2 = ymd2[0]+ymd2[1]+ymd2[2];
			if(newdate1 <= newdate2){
				if(confirm('确认删除'+date1 + "至" + date2 +"的所有系统日志吗?")){
					//ajax删除
					htmlobj=$.ajax({url:"<%=basePath%>sysCon/delLogByTime.hzyc?stime="+date1+"&etime="+date2,async:false});
					if(htmlobj.responseText == '101'){
						alert("删除成功..");
						//刷新页面
					}
				}
			}else{
				alert("起始时间选择有误,请检查截止时间是否大于起始时间!");
			}
			
		}else{
			alert("请选中起始时间执行批量操作!");
		}
	}

</script>

</head>
<body>
        
	<div class="box_border">
			<div class="box_top"><b class="pl15">查询条件</b></div>
	</div>
	<div class="wrapper" style="width:1150px;height:120px;">
		<form name="myForm" action="<%=basePath%>sysCon/selLog.hzyc" target="bottom" method="post">
		<table class="form_table" border="0" cellpadding="0" cellspacing="0">
	        <tr style="height:60px;">
	          <td><label class="layui-form-label">起始日期</label> </td>
	          <td >
			      <input name="startTime" type="text" class="layui-input" id="test1">
			  </td>
	           <td><label class="layui-form-label">截止日期</label> </td>
	          <td >
			      <input name="endTime" type="text" class="layui-input" id="test2">
			  </td>
			   <td >
			      <input type="checkbox"  name="isfail" />只显示失败记录
			  </td>
	          <td  style="vertical-align: middle" >
	          		<input type="submit" name="button" class="btn btn82 btn_search" value="查询" style="">
	          		<input type="reset" onclick="return confirm('重置所有选项吗?');" name="button" class="btn btn82 btn_res" value="重置" style="margin-left:10px;">
	          </td>
	          <td >
			      <input type="button" onclick="javascript:del();" value="批量删除">
			  </td>
	        </tr>
	        
	   </table>
	    </form>
   </div>
  
	   <div style="width:100%;height:410px">
	   		 <iframe src="log_bottom.jsp" name="bottom" style="width:100%;height:410px;border:0"> </iframe>
	   
	   </div>
</body>
<script>
layui.use('laydate', function(){
  var laydate = layui.laydate;
  
  //执行一个laydate实例
  laydate.render({
    elem: '#test1'

  });
  //执行一个laydate实例
  laydate.render({
    elem: '#test2'

  });
});
</script>
</html>