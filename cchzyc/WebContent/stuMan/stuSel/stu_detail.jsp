<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="../../WEB-INF/dict.tld"%>
<%@ taglib prefix="cs" uri="../../WEB-INF/tags/codeswitch.tld"%>
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
<script type="text/javascript" src="<%=basePath%>js/loadCity.js" charset=”utf-8″></script>
<script>
	/**
	* 修改
	*/
	function upd(id){
		window.open("<%=basePath%>stuCon/selOneStudent.hzyc?id="+id,"main");
	}

</script>
</head>
<body >
		<table  border="1px" class="layui-table" cellpadding="20px" cellspacing="10px" >
			<colgroup>
				<col width="11%" />
				<col width="15%" />
				<col width="11%" />
				<col width="15%" />
				<col width="11%" />
				<col width="37%" />
			</colgroup>
			<tbody>
				<tr>
					<th>姓名:</th>
					<td  >
					    ${student.name} 
					</td>
				    <td>
					          性别：
					 </td>
					 <td>
						<cs:CodeSwitch code="${student.sex}"  style="label"    />
				    </td>
						
				   <th rowspan="5" valign="top">学员照片:<br />
					<td rowspan="5" valign="top">
						<!-- 用来显示图片的div -->
						<div id="img-wrapper">
							<img src="<%=basePath%>stuImg/${student.imgName}"
								style="width: 130px; height: 156px;" title="学员二寸照片"
								id="stuImg" alt="暂无照片" />
						</div>
						</td>
				</tr>
				<tr>
					<th>身份证号:</th>
					<td  >${student.idcard}
					</td>
					 <td>
					         学号：
				    </td>
				    <td>
				    	${student.code} 
				    </td>
				</tr>
				<tr>
					<th>出生日期:</th>
					<td colspan="3">${student.birthday}</td>
				</tr>
				<tr>
					<th>籍贯:</th>
					<td colspan="3">
							${student.province}
							${student.city}
							${student.birthday}   
					</td>
				</tr>
				<tr>
					<th>学校:</th>
					<td colspan="3">
						${student.school }
					</td>
				</tr>
				<tr>
					<th>专业:</th>
					<td colspan="3">
						<cs:CodeSwitch style="label" code="${student.major }"/>
					</td>
					<th>家庭住址</th>
					<td>
						${student.address}
					</td>
				</tr>
				<tr>
					<th>学制:</th>
					<td colspan="3">
						${student.eLength}
						&nbsp;年制&nbsp;&nbsp;&nbsp; 
						${student.period}
						&nbsp;年入学</td>
					<th>手机:</th>
					<td>
						${student.tel }
					</td>
				</tr>
				<tr>
					<th>QQ 帐号:</th>
					<td colspan="3">
						${student.qq}
					<th>微信:</th>
					<td>
						${student.wx }
					</td>
				</tr>
				<tr>
					<th>合众班号:</th>
					<td colspan="3">
						${student.classes }
					   
					</td>
					<th>班级类别:</th>
					<td>
						${student.classes }
					</td>
				</tr>
				<tr>
					<th>学员状态:</th>
					<td colspan="3">
						<cs:CodeSwitch style="label" code="${student.state }"/>
					</td>	
					<th>规划师:</th>
					<td>
						${student.planner }
					</td>
				</tr>
				<tr>
					<th>当前教师:</th>
					<td>
						${student.teacher }
					</td>
					<th>学校班级:</th>
					<td>
						${student.planner }
					</td>
					<th>操作:</th>
					<td> <button onclick="upd('${student.id}')" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon"></i>   修改</button> 
						 <button class="layui-btn layui-btn-sm layui-btn-normal" style="background-color: #FF5722"><i class="layui-icon"></i>  删除</button> 	
				</tr>
				<tr>
					<td>
						教师变更轨迹:
					</td>
					<td colspan="3">
						李秀奇-滕飞-李吉智
					</td>
					<td>
						规划师变更轨迹:
					</td>
					<td>
						明海权-孔祥意
					</td>
				</tr>
			</tbody>
		</table>
	
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