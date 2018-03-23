<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘信息查询详细</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<script type="text/javascript" src="<%=basePath%>plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/loadCity.js" charset=”utf-8″></script>
<style type="text/css">
	th{
		width:130px;
	}
</style>
</head>
<body>
	<div>
		<table  border="1px" class="layui-table" cellpadding="20px" cellspacing="10px" >
			<colgroup>
				<col width="8%" />
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
					<td></td>
					
					<th>公司性质</th>
					<td></td>
					
					
					
					<th rowspan="4">公司简介</th>
					<td rowspan="4"></td>
					
				</tr>
				<tr>
					<th>所属行业</th>
					<td></td>
					
					<th>公司网址</th>
					<td></td>
				</tr>
				<tr>
					<th>公司规模</th>
					<td></td>
					
					<th>省市地址</th>
					<td></td>
					
				</tr>
				<tr>
					<th>详细地址</th>
					<td colspan="3"></td>		
				</tr>
				<tr></tr>
				<tr></tr>
				
				<tr>
					<th colspan="6" style="background-color:#e2e2e2">职位</th>
				</tr>
				<tr>
					<th>招聘职位</th>
					<td></td>
					
					<th>联系人</th>
					<td></td>
					
					<th rowspan="2">岗位描述</th>
					<td rowspan="2"></td>
				</tr>
				<tr>
					<th>招聘电话</th>
					<td></td>
					
					<th>薪资</th>
					<td></td>
					
				</tr>
				<tr>
					<th>工作经验</th>
					<td></td>
					
					<th>邮箱</th>
					<td></td>
					
					
					<th rowspan="2">岗位要求</th>
					<td rowspan="2"></td>
				</tr>
				<tr>
					<th>学历要求</th>
					<td></td>
					
					<th>招聘人数</th>
					<td></td>
					
				</tr>
				<tr>
					<th>工作类型</th>
					<td></td>
					
					<th>招聘状态</th>
					<td></td>
					
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
	</div>
		

</body>
</html>