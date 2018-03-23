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
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
    <script src="<%=basePath%>plugins/layui/layui.js"></script>
     <script src="<%=basePath%>js/jquery1.4.2.js"></script>
      <script src="<%=basePath%>js/nextPage.js"></script>
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
    				parent.formSubmit();
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
    <style>
    	*{
    		margin:0;
    		padding:0;
    	}
    
    </style>
</head>
<body style="padding:0;margin:0;">
	 <%
	 	List<StudentInfo> list = (List<StudentInfo>)request.getAttribute("stuList");
	 %>
	  <div style="width:100%;height:30px;background-color: #f2f2f2;line-height:30px;font-size:13px;color:#444;padding-left:10px;">
		当前位置 >> 试听申请管理 >> 试听查询结果  
	</div>
	
	 <form action="<%=basePath%>stuCon/nextPage.hzyc" method="post"  id="form">
	 	<!-- 查询数据绑定 用来分页 -->
		        	<%
		        		StudentInfo aud = (StudentInfo)request.getAttribute("student");
		        	    //p.getNowPage() 
		        		if(aud != null){
		        	%>
		        		<input type="hidden" name="name" class="layui-input" name="name" value="<%=aud.getName()==null ? "" : aud.getName()%>"/> 
				  		 <input type="hidden" name="tel" class="layui-input" name="tel"  value="<%=aud.getTel()==null ? "" : aud.getTel()%>"/> 
				 		 <input type="hidden"  name='classes'   value="<%=aud.getClasses()==null ? "" : aud.getClasses()%>"/>
				 		  <input type="hidden"  name='idcard'   value="<%=aud.getIdcard() == null ? "" : aud.getIdcard()%>"/>
					 	    <!-- 绑定分页查询 -->
					 	   <input type="hidden"   name="nowPage"  id="nowPage"  class="layui-input" id="inputDate2"  value="<%=aud.getNowPage() %>"/> 
					 	   <input type="hidden" name="pageSize" id="pageSize" value="<%=aud.getPageSize() %>" />
					 	      <input type="hidden"   name="maxPage"  id="maxPage"  class="layui-input" id="inputDate2"  value="<%=aud.getMaxPage()%>"/> 
					 	   <input type="hidden"   name="startPage"  id="startPage"  class="layui-input" id="inputDate2"  value=""/> 
		        			
		        	<%		
		        			
		        		}
		        	%>
	 <table class="layui-table" id="test">
		        <thead>
		            <tr>
		            	<th lay-data="{field:'id', width:40, sort: true}">ID</th>
		                <th lay-data="{field:'id', width:40, sort: true}">姓名</th>
		                <th lay-data="{field:'username', width:80}">性别</th>
		                <th lay-data="{field:'sex', width:80, sort: true}">电话</th>
		                <th lay-data="{field:'city', width:80}">QQ</th>
		                <th lay-data="{field:'sign', width:170}">年龄</th>
		                <th lay-data="{field:'experience', width:80, sort: true}">学校</th>
		                <th lay-data="{field:'score', width:80, sort: true}">专业</th>
		                <th lay-data="{field:'classify', width:80}">班级</th>
		                <th lay-data="{field:'id', width:30, sort: true}">规划师</th>
		                <th lay-data="{field:'wealth', width:135, sort: true}">操作</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<%
		        		if(list != null && list.size() > 0 ){
			        		for(StudentInfo student : list){
			        %>
			        		<tr>
			        			<td><%=student.getId() %></td>
				                <td><%=student.getName()%></td>
				                <td><%=student.getSex()%></td>
				                <td><%=student.getTel() %></td>
				                <td><%=student.getQq()%></td>
				                <td><%=student.getAge() %></td>
				                <td><%=student.getSchool() %></td>
				                <td><%=student.getMajor() %></td>
				                <td><%=student.getClasses()%></td>
				                <td><%=student.getPlanner()%></td>
				                <td>
				                	<button onclick="detail('<%=student.getId()%>')" class="layui-btn layui-btn-small layui-btn-normal" style="margin:0" >详细</button>
				                	<button onclick="upd('<%=student.getId()%>')" class="layui-btn layui-btn-small layui-btn-warm" style="margin:0" >修改</button>
				                	<input type="button" onclick="del('<%=student.getId()%>','<%=student.getName()%>')" class="layui-btn layui-btn-small layui-btn-danger" style="margin:0" value="删除" />
				                </td>
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
		        <tfoot>
		        	<tr>
		        		<td colspan="11">
		        			<%
		        				if(aud != null){
		        			%>
		        				当前第<%=aud.getNowPage() %>页
			        			共<%=aud.getMaxPage() %> 页
			        			<a id="start" href="javascript:pageChange('<%=1%>','start')">首页</a>
			        			<a id="top" href="javascript:pageChange('<%=aud.getNowPage()%>','top')">上一页</a>
			        			<a id="bottom" href="javascript:pageChange('<%=aud.getNowPage()%>','bottom')">下一页</a>
			        			<a id="end" href="javascript:pageChange('<%=aud.getMaxPage()%>','end')">尾页</a>
		        				
		        				<script>
								//如果当前页已经为首页或是尾页,则让按钮不能点击
									var nowPage = (<%=aud.getNowPage()%>);
									var lastPage = (<%=aud.getMaxPage()%>);
									if(nowPage >= lastPage){
										$("#bottom").attr('disabled', 'true');
								       	$("#bottom").removeAttr('href');
									}
									if(nowPage <= 1){
										$("#top").attr('disabled', 'true');
										$("#top").removeAttr('href');
									}
									//如果总页数为0，那么首页尾页也不可点击
									if(lastPage <= 0){
										$("#end").attr('disabled', 'true');
										$("#end").removeAttr('href');
										$("#start").attr('disabled', 'true');
										$("#start").removeAttr('href');
									}
									 
								
								</script>
		        		
		        			<%	
		        				}
		        			%>
		        		</td>
		        	</tr>
		        </tfoot>
		    </table>
		    </form>
	<script>
        layui.use('table', function() {
            var table = layui.table;
        });
    </script>
</body>
</html>