<%@page import="com.hzyc.website.beans.EmploymentNewsWithBLOBs"%>
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
    	function del(id){
    		if(confirm("您确认删除该学员吗?ID："+ id +",姓名:"+ name)){
    			//删除
    			htmlobj=$.ajax({url:"<%=basePath%>epmentCon/delEp.hzyc?id="+id,async:false});
    			if(htmlobj.responseText == 1){
    				alert("删除成功!");
    				parent.formSubmit();
    			}else{
    				alert("删除失败!");
    			}
    		}
    	}
    function pageChange(page,flag){
 		var n = document.getElementById("nowPage").value;
 		var p = document.getElementById("pageSize").value;
 		var m = document.getElementById("maxPage").value;
 		//这就是起始行
 		var sPage ;
 		//这是当前页
 		var nPage;
 		
 		switch(flag){
	 		case 'top':
	 			nPage = parseInt(page)-1;
	 			sPage = (parseInt(nPage)-1) * p;
	 			break;
	 		case 'bottom':
	 			nPage = parseInt(page)+1;
	 			sPage = ( parseInt(nPage)-1) * p;
		 		break;
	 		case 'start':
	 			nPage = 1;
	 			sPage = (parseInt(page)-1) * p;
		 		break;
	 		case 'end':
	 			nPage = page;
	 			sPage = (parseInt(page)-1) * p;
		 		break;
 		}
 		document.getElementById("startPage").value = sPage;
 		document.getElementById("nowPage").value = nPage;
 		//提交
 		document.getElementById("form").submit();
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
	 	List<EmploymentNewsWithBLOBs> list = (List<EmploymentNewsWithBLOBs>)request.getAttribute("enList");
	 %>
	  <div style="width:100%;height:30px;background-color: #f2f2f2;line-height:30px;font-size:13px;color:#444;padding-left:10px;">
		当前位置 >> 首页信息管理 >> 就业信息查询结果  
	</div>
	 <form action="<%=basePath%>epmentCon/nextPage.hzyc" method="post"  id="form">
	 	<!-- 查询数据绑定 用来分页 -->
		        	<%
		        		EmploymentNewsWithBLOBs aud = (EmploymentNewsWithBLOBs)request.getAttribute("enw");
		        	    //p.getNowPage() 
		        		if(aud != null){
		        	%>
		        		<input type="hidden" name="stuName" class="layui-input" value="<%=aud.getStuName()==null ? "" : aud.getStuName()%>"/> 
				  		 <input type="hidden" name="position" class="layui-input"   value="<%=aud.getPosition()==null ? "" : aud.getPosition()%>"/> 
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
		        	<th>ID</th>
	                <th>学员姓名</th>
	                <th>职位</th>
	                <th>公司logo</th>
	                <th>生活照</th>
	                <th>操作</th>
		            <tr>
		        </thead>
		        <tbody>
		        	<%
		        		if(list != null && list.size() > 0 ){
			        		for(int i = 0;i<list.size();i++){
			        %>
			        		<tr>
			        			<td><%=i+1 %></td>
				                <td><%=list.get(i).getStuName() %></td>
				                <td><%=list.get(i).getPosition() %></td>
				                <td><img src="<%=basePath %>/images/course/<%=list.get(i).getCompanyLogoName()%>" id="img"/></td>
				        		<td><img src="<%=basePath %>/images/course/<%=list.get(i).getLifePhotoName()%>" id="img1"/></td>
				        		
				                <td>
				                	<button onclick="upd('<%=list.get(i).getId()%>')" class="layui-btn layui-btn-small layui-btn-warm" style="margin:0" >修改</button>
				                	<input type="button" onclick="del('<%=list.get(i).getId()%>','<%=list.get(i).getStuName()%>')" class="layui-btn layui-btn-small layui-btn-danger" style="margin:0" value="删除" />
				              	<script type="text/javascript">
									$(document).ready(function(){
									$("#upd<%=i%>").click(function(){
										layer.open({
										      type: 2,
										      title: '就业信息修改',
										      maxmin: true,
										      name:'add',
										      shadeClose: false, //点击遮罩关闭层
										      area : ['800px' , '420px'],
										      content: '<%=basePath%>courseCon/selCourseById.hzyc?id=<%=list.get(i).getId()%>'
										});
									});
									
								});
								</script>
				                </td>
			        		</tr>
			        <%
			        		}
		        		}else{
		        	%>
		        			<tr>
			        			<td colspan="6" align="center">暂无数据</td>
			        		</tr>
		        	<%
		        		}
		        	%>
		        	
		        	
		        
		        </tbody>
		        <tfoot>
		        	<tr>
		        		<td colspan="6">
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