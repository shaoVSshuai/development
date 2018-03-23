<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兼职信息修改</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" href="<%=basePath %>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath %>css/common.css">
<script src="<%=basePath %>plugins/layui/layui.js"></script>
<script src="<%=basePath %>js/jquery1.4.2.js"></script>
<script src="<%=basePath %>js/loadDept.js"></script>
<style type="text/css">
	.row{
		margin-top:10px;
		width:850px;
		height: 40px;
	}
	.row label{
		position: relative;
		float: left;
	}
	.row div{
		position: relative;
		float: left;
	}
	.hzyc-select{
		width: 160px;
	}
	.hzyc-input{
		width: 160px;
	}
	.bin{
		margin-left:20px;
	}
</style>
</head>
<body>
<form action="#"  method="post" enctype="multipart/form-data" >
	<div style="width:850px;margin-left: 10px;" id="rec_content">
		<!-- -------公司----------- -->
		<div style="width:850px;"><div>公司</div></div>
		<div class="row">
			<label class="layui-form-label">公司名称</label>
			<div ><input type="text" name="" class="hzyc-input" /></div>
			<label class="layui-form-label">所属行业</label>
			<div>
				<select  class="hzyc-select">
					<option>1</option>
				</select>
			</div>
			<label class="layui-form-label">联系人</label>
			<div><input type="text" name="" class="hzyc-input" /></div>
		</div>
		
		<div class="row">
			<label class="layui-form-label">招聘电话</label>
			<div><input type="text" name="" class="hzyc-input" /></div>
			
			<label class="layui-form-label">省市地址</label>
			<div>
				<select class="hzyc-select" >
					<option>省</option>
				</select>
			</div>
			<div>
				<select class="hzyc-select bin">
					<option>市</option>
				</select>
			</div>
		</div>
		<div class="row">
			<label class="layui-form-label">详细地址</label>
			<div><input type="text" name="" class="hzyc-input" /></div>
		</div>
		<!-- -------职位----------- -->
		<div style="border-top: 1px solid #a9a9a9;width:850px;" class="row"><div>职位</div></div>
		<div class="row">
			<label class="layui-form-label">招聘职位</label>
			<div>
				<select class="hzyc-select">
					<option>1</option>
				</select>
			</div>
			<label class="layui-form-label">薪资</label>
			<div>
				<select class="hzyc-select">
					<option>1</option>
				</select>
			</div>
			<label class="layui-form-label">工作经验</label>
			<div>
				<select class="hzyc-select">
					<option>1</option>
				</select>
			</div>
		</div>
		<div class="row">
			<label class="layui-form-label" >招聘人数</label>
			<div><input type="text" name="" class="hzyc-input" /></div>
			<label class="layui-form-label" >结算方式</label>
			<div><input type="text" name="" class="hzyc-input" /></div>
			<label class="layui-form-label">学历要求</label>
			<div>
				<select class="hzyc-select">
					<option>1</option>
				</select>
			</div>
		</div>
		<div class="row">
			<label class="layui-form-label">岗位描述</label>
			<div><textarea cols="40" rows="7" name="" placeholder="字数限制在250左右,需要输入兼职具体工作时间" ></textarea></div>
			<label class="layui-form-label">岗位要求</label>
			<div><textarea cols="40" rows="7" name="" placeholder="字数限制在250左右"></textarea></div>
		</div>
	</div>
		
		
	<div style="width:300px;height:auto;margin:0 auto;margin-top:100px;">
		<ul>
			<li><input type="submit" value="保存"/></li>
			<li><input type="button" value="取消" /></li>
			<li><input type="reset" value="重置"/></li>
		</ul>
	</div>
	</form>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".layui-layer-max").click(function(){
			console.log("放大");
			$("#rec_content").css("background","red");
		});
	});
	</script>
	<script>
		layui.use('form', function(){
		  var form = layui.form;
		  
		  //各种基于事件的操作，下面会有进一步介绍
		});
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
</body>
</html>