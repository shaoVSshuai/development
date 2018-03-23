<%@page import="com.hzyc.website.beans.StudentInfo"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="../../../WEB-INF/tags/dict.tld"%>
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
<title>学员信息添加</title>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<script type="text/javascript" src="<%=basePath%>plugins/layui/layui.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/common.css" media="all">
<script src="<%=basePath %>js/area.js" charset="UTF-8"></script>
<style>
.layui-table td, .layui-table th {
    position: relative;
    padding: 6px 15px;
    min-height: 20px;
    line-height: 20px;
    font-size: 14px;
}
textarea{
	width: 400px;
	height: 100px;
	border-radius: 2px;
	border: 1px solid #e6e6e6;
	background-color: #fff;
}
</style>

</head>
<body >
	<form action="<%=basePath%>stuCon/updStudentInfo.hzyc" method="post" enctype="multipart/form-data">
		<input type="hidden" value="${student.id}" name="id"/>
		<table  border="1px" class="layui-table" cellpadding="20px" cellspacing="10px" >
			<colgroup>
				<col width="8%"/>
				<col width="15%" />
				<col width="8%" />
				<col width="15%" />
				<col width="4%" />
				<col width="30%" />
				
			</colgroup>
			<tbody>
				<tr>
					<th colspan="6" style="background-color:#e2e2e2">公司信息</th>
				</tr>
				<tr>
					<th>公司名</th>
					<td>
						<ex:dict classname="hzyc-select" type="company_scale" name="scale"/>
					</td>
					
					<th>公司性质</th>
					<td>
						<ex:dict classname="hzyc-select" type="company_property" name="characters"/>
					</td>
					
					<th rowspan="4">公司简介</th>
					<td rowspan="4"><textarea cols="97" rows="7" placeholder="字数限制在250左右" style="resize:none;" id="companyProfile" name="companyInfo"></textarea></td>
					
				</tr>
				<tr>
					<th>所属行业</th>
					<td>
						<ex:dict classname="hzyc-select" type="company_type" name="profession"/>
					</td>
					
					<th>公司网址</th>
					<td>
						<input type="text" class="hzyc-input" id="companyUrl" name="netUrl"/>
					</td>
				</tr>
				<tr>
					<th>公司规模</th>
					<td>
						<ex:dict classname="hzyc-select" type="company_scale" name="scale"/>
					</td>
					
					<th>省市地址</th>
					<td>
						<select class="hzyc-select"  id="s_province" name="province" style="width:110px">
						</select>
						<select class="hzyc-select" id="s_city" name="city" style="width:110px">
						</select>
					</td>
					
				</tr>
				<tr>
					<th>详细地址</th>
					<td colspan="3"><input type="text" class="hzyc-input" style="width:700px;" id="detailAdress" name="detailAddress"/></td>		
				</tr>
				<tr></tr>
				<tr></tr>
				
				<tr>
					<th colspan="6" style="background-color:#e2e2e2">职位</th>
				</tr>
				<tr>
					<th>招聘职位</th>
					<td>
						<ex:dict classname="hzyc-select" type="job_name" name="posName"/>
					</td>
					
					<th>联系人</th>
					<td><input type="text" name="linkman" class="hzyc-input" id="contactPerson"/></td>
					
					<th rowspan="2">岗位描述</th>
					<td rowspan="2"><textarea cols="40" rows="7" name="posInfo" placeholder="字数限制在250左右" style="resize:none;" id="workDes"></textarea></td>
				</tr>
				<tr>
					<th>招聘电话</th>
					<td><input type="text" name="phone" class="hzyc-input" id="telephone" /></td>
					
					<th>薪资</th>
					<td><ex:dict classname="hzyc-select" type="salary" name="salary"/></td>
					
				</tr>
				<tr>
					<th>工作经验</th>
					<td><ex:dict classname="hzyc-select" type="experience" name="posExperience"/></td>
					
					<th>邮箱</th>
					<td><input type="text" class="hzyc-input" id="email" name="email"/></td>
					
					
					<th rowspan="2">岗位要求</th>
					<td rowspan="2"><textarea cols="40" rows="7" name="posRequire" placeholder="字数限制在250左右" style="resize:none;" id="workAsk"></textarea></td>
				</tr>
				<tr>
					<th>学历要求</th>
					<td><ex:dict classname="hzyc-select" type="education" name="eduRequire"/></td>
					
					<th>招聘人数</th>
					<td><input type="text" name="needNumber" class="hzyc-input"  id="recNumber"/></td>
					
				</tr>
				<tr>
					<th>工作类型</th>
					<td><ex:dict classname="hzyc-select" type="job_type" name="states"/></td>
					
					<th>招聘状态</th>
					<td><ex:dict classname="hzyc-select" type="recruit_state" name="recSt"/></td>
					
					<th rowspan="2">操作</th>
					<td rowspan="2">
						<button class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon"></i>   修改</button> 
						<button class="layui-btn layui-btn-sm layui-btn-normal" style="background-color: #FF5722"><i class="layui-icon"></i>  删除</button> 
					</td>
				</tr>
				<tr>
					<th>福利</th>
					<td colspan="3"></td>
				</tr>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
            _init_area();
   </script>
	<script>
	layui.use('form', function(){
	  var form = layui.form;
	  //各种基于事件的操作，下面会有进一步介绍
	});
	</script>
</body>
</html>