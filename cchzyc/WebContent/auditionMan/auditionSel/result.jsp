<%@page import="com.hzyc.website.utils.Page"%>
<%@page import="com.hzyc.website.beans.Audition"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="cs" uri="../../WEB-INF/tags/codeswitch.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<script src="<%=basePath%>plugins/layui/layui.js"></script>
 <script src="<%=basePath%>js/jquery1.4.2.js"></script>
 <script>
 /* 	function copy(i){
 		  var e=document.getElementById("name"+i);//对象是copy-num1  
 		 var copyData = $('#name'+i).text();            
 		       if (window.clipboardData) {
 		             window.clipboardData.clearData();
 		              window.clipboardData.setData("Text", copyData);
 		                alert('已经成功复制到剪帖板上!');
 	             }
 		  
 		  document.execCommand("Copy");
 		 alert("复制成功!");
 		alert("申领码" + i + "已复制到剪切板");
 	} */
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
 	
 	//删除单个学员
 	function del(id,name){
 		
 		if(confirm('您要删除姓名为['+name+']的试听学员?')){
 			htmlobj=$.ajax({url:"<%=basePath%>audCon/delOneAud.hzyc?id="+id,async:false});
 			var value = htmlobj.responseText;
 			if(value == "true"){
 				
 				//提交表单刷新[这里提交的父页面的查询form]
 				
 				alert("删除成功!");
 				
 		 		parent.formSubmit();

 			}else{
 				alert("删除失败...");
 			}
 		}else{
 				
 		}
 		
 	}
 	
 </script>
 <style type="text/css">
 	*{
 		font-size:13px;
 		margin:0;
 		padding:0;
 	}
 	a:hover{
 		color:#F00;
 	}
 	
 </style>
</head>
<body>
	 <div style="width:100%;height:30px;background-color: #f2f2f2;line-height:30px;font-size:13px;color:#444;padding-left:10px;">
		当前位置 >> 试听申请管理 >> 试听查询结果  
	</div>
	 <%
	 	//List<StudentInfo> list = (List<StudentInfo>)request.getAttribute("stuList");
	 %>
	 <form action="<%=basePath%>audCon/nextPage.hzyc" method="post"  id="form">
	 	<!-- 查询数据绑定 用来分页 -->
		        	<%
		        		Audition aud = (Audition)request.getAttribute("audition");
		        	    //p.getNowPage() 
		        		if(aud != null){
		        	%>
		        		<input type="hidden" name="name" class="layui-input" name="name" value="<%=aud.getName()==null ? "" : aud.getName()%>"/> 
				  		 <input type="hidden" name="tel" class="layui-input" name="tel"  value="<%=aud.getTel()==null ? "" : aud.getTel()%>"/> 
				 		 <input type="hidden"  name='school'   value="<%=aud.getSchool()==null ? "" : aud.getSchool()%>"/>
				 		  <input type="hidden"  name='stuDept'   value="<%=aud.getStuDept() == null ? "" : aud.getStuDept()%>"/>
				 		   <input type="hidden"   name="stime"   class="layui-input" id="inputDate1"  value="<%=aud.getStime()==null ? "" : aud.getStime()%>"/> 
					 		 <input type="hidden"   name="etime"    class="layui-input" id="inputDate2"  value="<%=aud.getEtime()==null ? "" : aud.getEtime()%>"/> 
					 	    <input type="hidden"    name='applyLesson' class='layui-input' value="<%=aud.getApplyLesson()==null ? "" : aud.getApplyLesson()%>" / >
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
		                <th lay-data="{field:'username', width:80}">电话</th>
		                <th lay-data="{field:'sex', width:80, sort: true}">学校</th>
		                <th lay-data="{field:'city', width:80}">学院</th>
		                <th lay-data="{field:'city', width:40}">年级</th>
		                <th lay-data="{field:'sign', width:170}">申请时间</th>
		                <th lay-data="{field:'experience', width:80, sort: true}">申请课程</th>
		                <th lay-data="{field:'score', width:80, sort: true}">申领码</th>
		                <th lay-data="{field:'wealth', width:135, sort: true}">操作</th>
		            </tr>
		        </thead>
		        <tbody>
		        
		        	<c:choose>
				   		<c:when test="${aList != null && fn:length(aList) > 0}">   
				   			 <c:forEach var="aud" varStatus="i" items="${aList}" step="1" >
				   				<c:set var="index" value="${index+1}" /> 
		        		 		<tr >
				        			<td style="font-size:13px;">${index}</td>
					                <td style="font-size:13px;">
					                	${aud.name}
					                </td>
					                <td style="font-size:13px;">
					                	${aud.tel}
					                </td>
					                <td style="font-size:13px;">
					                	<cs:CodeSwitch code="${aud.school}" />
					                </td>
					                <td style="font-size:13px;">
										<cs:CodeSwitch code="${aud.stuDept}" />
									</td>
									 <td style="font-size:13px;">
										${aud.classes}
									</td>
					                <td style="font-size:13px;">${aud.applyTime}</td>
					                <td style="font-size:13px;">
					                	<cs:CodeSwitch code="${aud.applyLesson}" />
					                </td>
					                <td style="font-size:13px;">
					                	${aud.applyCode}
					                </td>
					                <td style="font-size:13px;">
					                	<input type="button" onclick="del('${aud.id}','${aud.name}')" class="layui-btn layui-btn-small layui-btn-danger" style="margin:0" value="删除"/>  
					                </td>
			        			</tr>
		        		 </c:forEach>
				   		
				   		</c:when>
				   
				   		<c:otherwise>  
				   			<tr>
			        			<td colspan="10" align="center">暂无查询数据</td>
			        		</tr>
				   		</c:otherwise>
				  
					</c:choose>
		        </tbody>
		        <tfoot>
		        	<tr>
		        		<td colspan="10"> <!-- 当前页 -->
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