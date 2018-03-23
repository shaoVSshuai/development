<%@page import="com.hzyc.website.beans.Job"%>
<%@page import="com.hzyc.website.beans.Dictionary"%>
<%@page import="com.hzyc.website.beans.StudentInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
    <script src="<%=basePath %>plugins/layui/layui.js"></script>
     <script src="<%=basePath %>js/jquery1.4.2.js"></script>
    <script>
	    /*
	    * 删除
	    */
    	function del(id,name){
    		if(confirm("您确认删除该学员吗?ID："+ id +",姓名:"+ name)){
    			//删除
    			htmlobj=$.ajax({url:"<%=basePath%>stuCon/delStudent.hzyc?id="+id,async:false});
    			if(htmlobj.responseText == 1){
    				alert("删除成功!");
    			}else{
    				alert("删除失败!");
    			}
    		}
    	}
    	/**
    	* 修改
    	*/
    	function upd(id){
    		window.open("<%=basePath%>stuCon/selOneStudent.hzyc?id="+id,"main");
    	}
    	/*
    	* 详细
    	*/
		function detail(id){
			window.open("<%=basePath%>stuCon/selStudentDetail.hzyc?id="+id,"main");
    	}    
    
    </script>
</head>
<body>
	 <%
	 	List<Job> jobs = (List<Job>)request.getAttribute("rList");
	 %>
	 
	 <table class="layui-table" id="test">
		        <thead>
		            <tr>
		            	<th lay-data="{field:'id', width:20, sort: true}">ID</th>
		            	<th lay-data="{field:'id', width:40, sort: true}">角色编码</th>
		                <th lay-data="{field:'id', width:20, sort: true}">角色(职位)名称</th>
		                <th lay-data="{field:'username', width:40}">角色备注</th>
		                <th lay-data="{field:'sex', width:60, sort: true}">操作</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<%
		        		if(jobs != null && jobs.size() > 0 ){
			        		for(Job job : jobs){
			        %>
			        		<tr>
			        			<td><%=job.getId() %></td>
			        			<td><%=job.getJobCode()%></td>
				                <td><%=job.getJobName()%></td>
				                <td><%=job.getJobRemark()%></td>
				                <td><button>修改</button> <button >删除</button> <button >新增</button> </td>
			        		</tr>
			        <%
			        		}
		        		}else{
		        	%>
		        			<tr>
			        			<td colspan="11" align="center">暂无数据</td>
			        		</tr>
		        	<%
		        		}
		        	%>
		        	
		        	
		        
		        </tbody>
		    </table>
	<script>
        layui.use('table', function() {
            var table = layui.table;
        });
    </script>
</body>
</html>