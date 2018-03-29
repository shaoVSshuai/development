<%@page import="com.hzyc.website.beans.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
 	.omit {
		max-width: 110px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		}
 </style>
 <script type="text/javascript">
 function detail(id){
		var iWidth=620;                          //弹出窗口的宽度; 
		var iHeight=420; 
     var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
     //获得窗口的水平位置 
     var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
     var a = encodeURI("<%=basePath%>/homepageInfoMan/detail.jsp?code="+id);
     alert(a);
    // window.open(, "_blank", 'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft +
     //',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no'); 	
	}
 </script>
</head>
<body>
	<% 
		List<Course> cList = (List<Course>)request.getAttribute("cList");
	%>
		<table class="layui-table" id="test">
	        <thead>
	            <tr>
	            	<th>ID</th>
	                <th>图标</th>
	                <th>课程名</th>
	                <th>标题</th>
	                <th>应用于</th>
	                <th>描述</th>
	                <th>操作</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<% 
	        		if (cList != null && cList.size() > 0) {
	        			for (int i=0; i<cList.size(); i++) {
	        	%>	
				        	<tr>
				        		<td><%=cList.get(i).getId()%></td>
				        		<td><%=cList.get(i).getIcon()%></td>
				        		<td><%=cList.get(i).getCourseName()%></td>
				        		<td><%=cList.get(i).getTitle()%></td>
				        		<td><%=cList.get(i).getApplication()%></td>
				        		<td class="omit"><%=cList.get(i).getDescribe()%></td>
				        		<td>
				        		<button onclick="detail('<%=cList.get(i).getDescribe()%>')" class="layui-btn layui-btn-small layui-btn-normal" style="margin:0" >详细</button>
				        		<button id="upd<%=i %>" class="layui-btn layui-btn-small layui-btn-warm" style="margin:0" >修改</button>
				        		<script type="text/javascript">
									$(document).ready(function(){
									$("#upd<%=i%>").click(function(){
										layer.open({
										      type: 2,
										      title: '精品课程修改',
										      maxmin: true,
										      name:'add',
										      shadeClose: false, //点击遮罩关闭层
										      area : ['800px' , '420px'],
										      content: '<%=basePath%>courseCon/selCourseById.hzyc?id=<%=cList.get(i).getId()%>'
										});
									});
									
								});
								</script>
				        		</td>
				        	</tr>
	        	<%		
	        			}
	        		} else {
	        	%>
	        	<tr>
	       			<td colspan="10" align="center">暂无查询数据</td>
	       		</tr>
	        	<%	
	        		}
	        	%>
	        </tbody>
	        <tfoot>
	        </tfoot>
		</table>
</body>
</html>