<%@page import="java.util.List"%>
<%@page import="com.hzyc.website.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
 <script src="<%=basePath%>/js/jquery1.4.2.js"></script>
 <style>
 </style>
 <script>
 	function detail(code){
 		//code是主键
 		 window.open("<%=basePath%>deptEmpCon/selEmpDetail.hzyc?id="+code,"main");
 	}
 
 </script>
</head>
<body>
 <div style="width:100%;height:30px;background-color: #f2f2f2;line-height:30px;font-size:13px;color:#444;padding-left:10px;">
		当前位置 >> 部门员工管理>> 员工维护信息查询
 </div>
	 <%
	 	List<Employee> list = (List<Employee>)request.getAttribute("empList");
	 %>
	 
	 <table class="layui-table" id="test">
		        <thead>
		            <tr>
		            	<!-- 
		            	
		            		工号
								namevarchar(20)姓名
								telchar(11)电话
								sexvarchar(10)性别
								birthdaychar(8)年龄
								idcardchar(18)身份证号
								timedate入职时间
								provincevarchar(10)省
								cityvarchar(10)城市
								dis_imgmediumblob展示图片
								card_imgmediumblob身份证图片
								emp_imgmediumblob员工二寸照片
								dept_twovarchar(10)二级部门
								dept_onevarchar(10)所属部门
								job_codevarchar(10)职位编码
								remarkvarchar(200)备注
		            	
		            	 -->
		            	<th lay-data="{field:'id', width:40, sort: true}">工号</th>
		                <th lay-data="{field:'id', width:40, sort: true}">姓名</th>
		                <th lay-data="{field:'username', width:80}">电话</th>
		                <th lay-data="{field:'sex', width:80, sort: true}">年龄</th>
		                <th lay-data="{field:'city', width:80}">身份证号</th>
		                <th lay-data="{field:'sign', width:170}">入职时间</th>
		                <th lay-data="{field:'experience', width:80, sort: true}">籍贯</th>
		                <th lay-data="{field:'score', width:80, sort: true}">一级部门</th>
		                <th lay-data="{field:'score', width:80, sort: true}">二级部门</th>
		                 <th lay-data="{field:'score', width:80, sort: true}">所属职位</th>
		                <th lay-data="{field:'wealth', width:135, sort: true}">操作</th>
		            </tr>
		        </thead>
		        <tbody >
		        	 <%
		        		if(list != null && list.size() > 0 ){
			        		for(Employee emp : list){
			        %>
			        		<tr>
			        			<td style="font-size:12px;"><%=emp.getCode()%></td>
				                <td style="font-size:12px;"><%=emp.getName()%></td>
				                <td style="font-size:12px;"><%=emp.getTel()%></td>
				                <td style="font-size:12px;"><%=emp.getAge() %></td>
				                <td style="font-size:12px;"><%=emp.getIdcard()%></td>
				                <td style="font-size:12px;"><%=emp.getTime()%></td>
				                <td style="font-size:12px;"><%=emp.getProvince() %></td>
				                <td style="font-size:12px;"><%=emp.getDeptOne() %></td>
				                <td style="font-size:12px;"><%=emp.getDeptTwo()%></td>
				                <td style="font-size:12px;"><%=emp.getJobCode()%></td>
				                <td style="font-size:12px;">
				                	<button onclick="detail('<%=emp.getId()%>')" class="layui-btn layui-btn-small layui-btn-normal" style="margin:0" >详细</button>
				                	<button onclick="upd('<%=emp.getId()%>')" class="layui-btn layui-btn-small layui-btn-warm" style="margin:0" >修改</button>
				                	<button onclick="del('<%=emp.getId()%>','<%=emp.getName()%>')" class="layui-btn layui-btn-small layui-btn-danger" style="margin:0" > 删除</button>
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
		    </table>
	<script>
        layui.use('table', function() {
            var table = layui.table;
        });
    </script>

</body>
</html>