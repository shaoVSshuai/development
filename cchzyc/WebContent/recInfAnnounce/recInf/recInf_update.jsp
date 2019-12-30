<%@page import="com.hzyc.website.beans.Dictionary"%>
<%@page import="com.hzyc.website.system.CompanyForInit"%>
<%@page import="com.hzyc.website.beans.Company"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ex" uri="../../WEB-INF/dict.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招聘信息添加</title>
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
<script src="<%=basePath %>js/area.js" charset="UTF-8"></script>
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
	#province{
		width:80px;
	}
	#city{
		width:80px;
	}
	#payStyle{
		display : none;
	}
</style>
<script type="text/javascript">
		function company(){
			var id = document.getElementById("companyName").value;
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.onreadystatechange = function(){
				if(xmlhttp.readyState == 4){
					var data = xmlhttp.responseText;
					//格式化数据
					var dataArray = eval("("+data+")");
					//公司性质
					var characters = document.getElementById("characters");
					//所属行业
					var profession = document.getElementById("profession");
					//公司规模
					var scale = document.getElementById("scale");
					//公司网址
					var companyUrl = document.getElementById("companyUrl");
					//省级地址
					var province = document.getElementById("province");
					//市级地址
					var city = document.getElementById("city");
					//详细地址
					var detailAddress = document.getElementById("detailAddress");
					//公司介绍
					var companyProfile = document.getElementById("companyProfile");
					
					if (dataArray.characters == undefined || dataArray.characters == "") {
						characters.value = "暂无数据";
					} else {
						characters.value = dataArray.characters;
					}
					
					if (dataArray.profession == undefined || dataArray.profession == "") {
						profession.value = "暂无数据";
					} else {
						profession.value = dataArray.profession;
					}
					
					if (dataArray.scale == undefined || dataArray.scale == "") {
						scale.value = "暂无数据";
					} else {
						scale.value = dataArray.scale;
					}
					
					if (dataArray.netUrl == undefined || dataArray.netUrl == "") {
						companyUrl.value = "暂无数据";
					} else {
						companyUrl.value = dataArray.netUrl;
					}
					
					if (dataArray.province == undefined || dataArray.province == "") {
						province.value = "暂无数据";
					} else {
						province.value = dataArray.province;
					}
					
					if (dataArray.city == undefined || dataArray.city == "") {
						city.value = "暂无数据";
					} else {
						city.value = dataArray.city;
					}
					
					if (dataArray.detailAddress == undefined || dataArray.detailAddress == "") {
						detailAddress.value = "暂无数据";
					} else {
						detailAddress.value = dataArray.detailAddress;
					}
					
					if (dataArray.companyInfo == undefined || dataArray.companyInfo == "") {
						companyProfile.value = "暂无数据";
					} else {
						companyProfile.value = dataArray.companyInfo;
					}
				}
			};
			xmlhttp.open("post", "<%=basePath%>recInfCon/selCompanyById.hzyc", true);
			xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xmlhttp.send("id="+id);

		}

</script>
<script type="text/javascript">
function states1(){
	var data = document.getElementById("states").value;
	if (data == 10002) {
		document.getElementById("salary").innerHTML = "★结算方式";
		document.getElementById("salaryEx").style.display = "none";
		document.getElementById("payStyle").style.display = "inline";
		
	} else if (data == 10001) {
		document.getElementById("salary").innerHTML = "★薪资";
		document.getElementById("payStyle").style.display = "none";
		document.getElementById("salaryEx").style.display = "inline";
	}
	
}
</script>
</head>
<body onload="company()">

		<!-- -------公司----------- -->
		<div id="company">
			<div style="width:850px;"><div>公司信息</div></div>
			<div class="row">
				<label class="layui-form-label" >公司名称</label>
				<div>
					<%
		  			List<Company> list = CompanyForInit.getList();
		  			%>
		  			<input type="text" class="hzyc-input" readonly="readonly" name="cId" value="<%=list.get(0).getName()%>"/>
		  			<input type="hidden" id="companyName" class="hzyc-input" name="cId" value="<%=list.get(0).getId()%>"/>
				</div>
				<label class="layui-form-label">公司性质</label>
				<div>
					<input type="text" id="characters" class="hzyc-input" readonly="readonly"/>
				</div>
				<label class="layui-form-label">所属行业</label>
				<div>
					<input type="text" id="profession" class="hzyc-input" readonly="readonly"/>
				</div>
			</div>
		
			<div class="row">
				<label class="layui-form-label">公司规模</label>
				<div>
					<input type="text" id="scale" class="hzyc-input" readonly="readonly"/>
				</div>
				<label class="layui-form-label" >公司网址</label>
				<div><input type="text" class="hzyc-input" id="companyUrl" name="netUrl" readonly="readonly"/></div>
				<label class="layui-form-label" >省市地址</label>
				<div>
					<input type="text" id="province" class="hzyc-input" readonly="readonly"/>
				</div>
				<div>
					<input type="text" id="city" class="hzyc-input" readonly="readonly"/>
				</div>
				
			</div>
			<div class="row">
				<label class="layui-form-label">详细地址</label>
				<div><input type="text" class="hzyc-input" style="width:700px;" id="detailAddress" name="detailAddress" readonly="readonly" /></div>
			</div>
			<div class="row">
				<label class="layui-form-label">公司简介</label>
				<div><textarea cols="97" rows="7" style="resize:none;" id="companyProfile" name="companyInfo" readonly="readonly" ></textarea></div>
			</div>
		</div>
<form action="<%=basePath%>recInfCon/recUpdate.hzyc" target="_parent"  method="post" enctype="multipart/form-data" id="form">
	<div style="width:850px;margin-left:10px;" id="add_content">
		<!-- -------职位----------- -->
		<div id="position">
				<div style="border-top: 1px solid #a9a9a9;width:850px;margin-top: 100px;" class="row"><div>职位</div></div>
				<div class="row">
					<label class="layui-form-label">★招聘职位</label>
					<div>
						<ex:dict classname="hzyc-select" type="job_name" name="posName" defaultvalue="UI设计"/>
					</div>
					<label class="layui-form-label">★联系人</label>
					<div><input type="text" name="linkman" class="hzyc-input" id="contactPerson"/></div>
					<label class="layui-form-label">★招聘电话</label>
					<div><input type="text" name="phone" class="hzyc-input" id="telephone" /></div>
				</div>
			
				<div class="row">
					<label class="layui-form-label">★学历要求</label>
					<div>
						<ex:dict classname="hzyc-select" type="education" name="eduRequire"/>
					</div>
					<div>
						<label class="layui-form-label">★工作经验</label>
						<ex:dict classname="hzyc-select" type="experience" name="posExperience"/>
					</div>
				
					<label class="layui-form-label">★邮箱</label>
					<div><input type="text" class="hzyc-input" id="email" name="email"/></div>
				
				</div>
				<div class="row">
					<label class="layui-form-label" >★招聘人数</label>
					<div><input type="text" name="needNumber" class="hzyc-input"  id="recNumber"/></div>
					<label class="layui-form-label">★工作类型</label>
					<div>
						<ex:dict classname="hzyc-select" type="job_type" name="states" id="states" event="onchange-states1()"/>
					</div>
					<div>
						<label class="layui-form-label" id="salary">★薪资</label>
						<ex:dict classname="hzyc-select" type="salary" name="salary" id="salaryEx"/>
						<input type="text" class="hzyc-input"  name="payStyle" id="payStyle"/>
					</div>
				</div>
				<div>
					<label class="layui-form-label">★招聘状态</label>
					<div>
						<ex:dict classname="hzyc-select" type="recruit_state" name="recSt"/>
					</div>
				</div>
				<%
					List<Dictionary> dList = (List<Dictionary>)request.getAttribute("dList");
				%>
				<input type="hidden" id="dList" value="<%=dList.size()%>"/>
				<div class="row">
					<label class="layui-form-label">★福利</label>
					<%
						for (int i=0; i<dList.size(); i++) {
							if (i > 10) {
					%>
					<input type="button" value="<%=dList.get(i).getDictName()%>" id="<%=dList.get(i).getId() %>" class="welfare1 hzyc-input" onclick="welfare1(<%=dList.get(i).getId() %>)" style="margin-top:10px;"/>
					<%			
							} else {
					%>
					<input type="button" value="<%=dList.get(i).getDictName()%>" id="<%=dList.get(i).getId() %>" class="welfare1 hzyc-input" onclick="welfare1(<%=dList.get(i).getId() %>)"/>
					<%			
							}
						}
					%>
				</div>
				<input type="hidden" name="welfare" id="welSpli"/>
				<div class="row" id="welBo">
					<label class="layui-form-label">★岗位描述</label>
					<div><textarea cols="40" rows="7" name="posInfo" style="resize:none;" id="workDes"></textarea></div>
					<label class="layui-form-label">★岗位要求</label>
					<div><textarea cols="40" rows="7" name="posRequire" style="resize:none;" id="workAsk"></textarea></div>
				</div>
			</div>
		
		
		<!-- -------详细----------- -->
		
	</div>
		
		
	<div style="width:300px;height:auto;margin:0 auto;margin-top:30px;">
		<ul>
			<li><input type="button"  id="sub" value="保存"/></li>
			<li><input type="button" value="取消" /></li>
			<li><input type="reset" value="重置"/></li>
		</ul>
	</div>
	</form>
	<!--  功能函数	 -->
	<script type="text/javascript">
		//省市二级联动函数
		_init_area();
		//省市校验
		function isArea(){
			var province=$("#s_province").val();
			var city=$("#s_city").val();
			console.log(province+"---"+city);
			if(province!="省份"&&city!="地级市"){
				return true;
			}else{
				return false;
			}
		}
		//非空校验
		function isEmpty(str){
			if(str==null||str==""||str==''){
				return true;
			}else{
				return false;
			}
		}
		//网址正则
		function isUrl(url){
			
			var reg=/^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
			console.log(url+"网址正则"+reg.test(url));
			return reg.test(url);
		}
		
		//电话正则
		function isPhone(phone){
			var reg=/^1[3|4|5|6|7|8][0-9]\d{4,8}$/;
			console.log(phone+"7位或11位1开头电话号码正则"+reg.test(phone));
			return reg.test(phone);
		}

		//邮箱正则
		function isEmail(email){
			var reg =/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$/;
			console.log(email+"xx.@xx邮箱"+reg.test(email));
			return reg.test(email);
		}	

		//数字正则
		function isNumber(num){
			var reg = /^[0-9]*$/;
			console.log(num+"数字正则"+reg.test(num));
			return reg.test(num);
		}

		//中英文名字
		function isName(name){
			var reg = /^[a-zA-Z]{1,20}|[\u4e00-\u9fa5]{1,10}$/;
			console.log(name+"中英文名字"+reg.test(name));
			return reg.test(name);
		}
	</script>
	<!-- 提交from表单 -->
	<script type="text/javascript">
		$(document).ready(function(){
			//console.log("1111");
			
			$("#sub").click(function(){
				console.log("校验");
				var ans = ""; 
				
				var phone = $('#telephone').val();
				var phoneBool = isPhone(phone);
				if(!phoneBool)
					ans = ans+"电话信息有误<br/>";
					//layer.msg('电话信息有误');
				
				var email = $('#email').val();
				var emailBool = isEmail(email);
				if(!emailBool)
					ans = ans+"email信息有误<br/>";
					//layer.msg('email信息有误');

				var number = $('#recNumber').val();
				var numberBool = isNumber(number);
				if(!numberBool)
					ans = ans+"人数信息有误<br/>";
					//layer.msg('人数信息有误');

				var name = $('#contactPerson').val();
				var nameBool = isName(name);
				if(!nameBool)
					ans = ans+"联系人信息有误<br/>";
				//layer.msg('联系人信息有误');

				var workDes = $('#workDes').val();
				var workDesBool = isEmpty(workDes);
				if(workDesBool)
					ans = ans+"岗位描述为空<br/>";
				//layer.msg('岗位描述为空');
				
				var workAsk = $('#workAsk').val();
				var workAskBool = isEmpty(workAsk);
				if(workAskBool)
					ans = ans+"岗位要求为空<br/>";
				//layer.msg('岗位要求为空');
				
				if(ans==""){
					document.getElementById("form").submit();
					//window.parent.location.reload();
				}else{
					layer.msg(ans,{
					time: 6000, //20s后自动关闭
					btn: ['明白了', '知道了']
					});
				}
				//cosnole.log("areaBool:"+areaBool+";urlBool:"+urlBool+";:");
				
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