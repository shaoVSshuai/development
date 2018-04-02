<%@page import="com.hzyc.website.beans.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="WEB-INF/tags/dict.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/iconfont.css">
	<link rel="stylesheet" href="css/jquery.slider.css" />
	<link rel="stylesheet" href="css/swiper-3.4.2.min.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link href="css/slider.css" rel="stylesheet">
	<link rel="stylesheet" href="css/index.css">
	<script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.slider.min.js"></script>
	<script type="text/javascript" src="js/flexible.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<title>合众易成</title>
	
	<script>
		$(document).ready(function(){
			$(".submit-btn").click(function(){
				//所有表单选项
				var value =  "name=" + $("#name").val() + "&tel="+ $("#tel").val() + "&applyLesson=" + $("#applyLesson").val() 
						+ "&school=" + $("#school").val() + "&stuDept=" + $("#stuDept").val() +"&classes=" + $("#classes").val() 
						+ "&validCode=" + $("#phone").val() ;
				if( $("#name").val().trim() == "" || $("#tel").val().trim()=="" || $("#applyLesson").val().trim() =="" || $("#school").val().trim() == "" || $("#stuDept").val().trim() == "" || $("#classes").val().trim() == "" || $("#phone").val().trim() == "" ){
					alert("请填写完整信息..");
				}else{
					 $.ajax({
						   type: "POST",
						   url: "audCon/addAudition.hzyc",
						   data: value,
						   success: function(msg){
							   var info = "";
							   if(msg == "101"){
								   info = "提交成功!";
								   alert(info);
								   $(".cover").hide();
							   }else if(msg == "102"){
								   info = "验证失败,请检查验证码填写...";
								   alert(info);
							   }else if(msg == "103"){
								   info = "提交失败,错误代码"+msg;
								   alert(info);
							}
							
						}
					});
				}
			   
			});
			
			$(".send1").click(function(){
				var value =  $("#tel").val();
				$.ajax({
					   type: "POST",
					   url: "audCon/getMessage.hzyc",
					   data:{ 
						   phone:value
					   }
				});
			});
			
			//更新下后台数据字典,以防无法显示最新字典内容
			  $.ajax({
				   type: "POST",
				   url: "sysCon/reloadDict.hzyc"
			  });
		});
		
		
			
	</script>
	<style>
		.send1:hover{
			cursor:pointer;
		}
	</style>
	
</head>
<body>
	
<nav class="navbar navbar-default navbar-inverse site-navbar" role="navigation">
<% List<Course> cList = (List)request.getAttribute("courseList"); %>
		<div class="container"> 
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#example-navbar-collapse">
					<span class="sr-only">切换导航</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand clearfix" href="#">
					<span class="logo-img"></span>
					<span class="name">合众易成</span>
				</a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
				<ul class="nav navbar-nav clearfix">
					<li>
						<input type="text" name="name" class="user-name nameSpecial" placeholder="姓名">
					</li>
					<li>
						<input type="text" name="phone" class="user-phone phoneSpecial" placeholder="手机号码">
					</li>
					<li>
						<button class="listening send-btn">申请试听</button>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="header-wrapper">
			<div class="context">
				<img src="img/main2.jpg">
			</div>
			<div class="box">
				<h2>合众易成，做你生活中的IT导师</h2>
				<div class="box-submit clearfix fl">
					<input type="text" name="name" class="user-name name-special" placeholder="姓名">
					<input type="text" name="phone" class="user-phone phone-special" placeholder="手机号码">
					<button type="submit" class="listening listening-special">申请试听</button>
				</div>
			</div>
		</div>
	</div>
	<div class="introduce">
		<div class="container">
			<div class="company">
				<h1 class="title">长春合众易成信息技术咨询服务有限公司</h1>
				<p>
					公司目前拥有两家子公司，在无锡投资无锡创科软件股份有限公司,在吉林市有长春合众易成吉林分公司
				</p>
				<p>
					公司的创始人及技术团队在人口信息管理,电子商务,应用集成,ERP,CRM,商务智能方面具有多年丰富的软件及硬件系统的开发,实施,咨询经验。
				</p>
				<p>
					公司目前与国内多家公司合作，协助软件研发，技术革新，并提供商业智能解决方案。
				</p>
				<p>
					公司全体员工用多年积累的丰富开发和实施经验以及职业教育经验，用国际化思想,潮流化技术,精英化队伍。
				</p>
				<p>
					标准化服务为国家培养IT行业精英之才，为企业提供全方位技术解决方案及咨询服务。
				</p>
				<p>
					公司秉承”自由,平等,开放,合作,创新,共赢“的经营理念，为每一个客户着想。
				</p>
				<p>
					将公司的利益融入客户的利益，并不断更新自我，推动行业技术进步。
				</p>
				<p>
					秉承”专业兴教,教育兴产,产业兴国“的理念，共同推动中国软件产业及人才培养的发展。
				</p>
				<p>
					让更多有梦想从事IT行业的年轻精英有一个实现梦想的舞台，成为世界IT精英的摇篮！
				</p>
			</div>
		</div>
		<%if(cList!=null && cList.size()>0){
				%>
				<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="course-name">
						<div class="cut"></div>
						<h3 class="title">精品课程</h3>
					</div>
				</div>
			</div>
			<div class="row course-list">
				<%for(int i = 0 ;i<cList.size();i++){
					%>
					<div class="col-md-3">
					<li class="course-item">
						<p class="android">
							<img style="margin-top:20px;width:64px;height:67px;" src="<%=basePath %>/images/course/<%=cList.get(i).getIconName()%>" id="img"/>
						</p>
						<p class="title">
							<%=cList.get(i).getCourseName() %>
						</p>
						<p class="label">
							<%=cList.get(i).getTitle() %>
						</p>
						<p>
							<span class="wire"></span>
						</p>
						<p>
							<%=cList.get(i).getApplication() %>
						</p>
					</li>
				</div>
				<%}%>
			</div>
		</div>
			<% }%>
		
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="team-name">
						<div class="cut"></div>
						<h3 class="title">教师团队</h3>
					</div>
				</div>
			</div>
			<div class="row teacherList">
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="img/teacher.jpg">
						<div class="teacherBox">
							<p class="teacher-title">董事长</p>
							<p class="wire"></p>
							<p class="teacher-name">腾飞</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="img/people.jpg">
						<div class="teacherBox">
							<p class="teacher-title">训导总监</p>
							<p class="wire"></p>
							<p class="teacher-name">乐毅</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="img/teacher2(1).jpg">
						<div class="teacherBox">
							<p class="teacher-title">技术总监</p>
							<p class="wire"></p>
							<p class="teacher-name">隋春雨</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="img/teacher4.jpg">
						<div class="teacherBox">
							<p class="teacher-title">Web前端</br>高级讲师</p>
							<p class="wire"></p>
							<p class="teacher-name">关艺馨</p>
						</div>
					</li>
				</div>
			</div>
		</div>
		<div class="get-job">
			<div class="container">
				<div class="row">
					<div class="job-name">
						<div class="cut"></div>
						<h3 class="title title-special">就业喜报</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="swiper-container">
							<div class="swiper-wrapper">
								<div class="swiper-slide">
									<ul class="clearfix panel">
				                        <li class="job-list-item job-list-special">
				                            <img src="img/student1.jpg">
				                        </li>
				                        <li class="job-list-item job-list-special job-special">
				                            <p class="huawei">
				                                <i class="iconfont">&#xe501;</i>
				                            </p>
				                            <p class="student-name">
				                                冯李灯
				                            </p>
				                            <div class="job-cen">
				                                <p class="wire"></p>
				                            </div>
				                            <p>职位：安卓开发</p>
				                        </li>
				                        <li class="job-list-item job-list-special">
				                            <img src="img/student2.jpg">
				                        </li>
				                        <li class="job-list-item job-list-bot job-special">
				                            <p class="huawei">
				                                <i class="iconfont">&#xe61c;</i>
				                            </p>
				                            <p class="student-name">
				                                车锦松
				                            </p>
				                            <div class="job-cen">
				                                <p class="wire"></p>
				                            </div>
				                            <p>职位：前端开发</p>
				                        </li>
				                        <li class="job-list-item job-list-rig list-special">
				                            <p class="baidu">
				                                <i class="iconfont">&#xe7aa;</i>
				                            </p>
				                            <p class="student-name">
				                                郎世权
				                            </p>
				                            <div class="job-cen">
				                                <p class="wire"></p>
				                            </div>
				                            <p>职位：前端开发</p>
				                        </li>
				                        <li class="job-list-item job-list-rig">
				                            <img src="img/student3.jpg">
				                        </li>
				                        <li class="job-list-item job-list-rig list-special">
				                            <p class="huawei">
				                                <i class="iconfont">&#xe501;</i>
				                            </p>
				                            <p class="student-name">
				                                冯李灯
				                            </p>
				                            <div class="job-cen">
				                                <p class="wire"></p>
				                            </div>
				                            <p>职位：安卓开发</p>
				                        </li>
				                        <li class="job-list-item">
				                            <img src="img/student2.jpg">
				                        </li>
				                    </ul>
						        </div>
						        <div class="swiper-slide">
						        	<ul class="clearfix panel">
				                        <li class="job-list-item job-list-special">
				                            <img src="img/people.jpg">
				                        </li>
				                        <li class="job-list-item job-list-special job-special">
				                            <p class="huawei">
				                                <i class="iconfont">&#xe501;</i>
				                            </p>
				                            <p class="student-name">
				                                冯李灯
				                            </p>
				                            <div class="job-cen">
				                                <p class="wire"></p>
				                            </div>
				                            <p>职位：安卓开发</p>
				                        </li>
				                        <li class="job-list-item job-list-special">
				                            <img src="img/people.jpg">
				                        </li>
				                        <li class="job-list-item job-list-bot job-special">
				                            <p class="huawei">
				                                <i class="iconfont">&#xe61c;</i>
				                            </p>
				                            <p class="student-name">
				                                车锦松
				                            </p>
				                            <div class="job-cen">
				                                <p class="wire"></p>
				                            </div>
				                            <p>职位：前端开发</p>
				                        </li>
				                        <li class="job-list-item job-list-rig list-special">
				                            <p class="baidu">
				                                <i class="iconfont">&#xe7aa;</i>
				                            </p>
				                            <p class="student-name">
				                                郎世权
				                            </p>
				                            <div class="job-cen">
				                                <p class="wire"></p>
				                            </div>
				                            <p>职位：前端开发</p>
				                        </li>
				                        <li class="job-list-item job-list-rig">
				                            <img src="img/people.jpg">
				                        </li>
				                        <li class="job-list-item job-list-rig list-special">
				                            <p class="huawei">
				                                <i class="iconfont">&#xe501;</i>
				                            </p>
				                            <p class="student-name">
				                                冯李灯
				                            </p>
				                            <div class="job-cen">
				                                <p class="wire"></p>
				                            </div>
				                            <p>职位：安卓开发</p>
				                        </li>
				                        <li class="job-list-item">
				                            <img src="img/people.jpg">
				                        </li>
				                    </ul>
						        </div>
						        <div class="pagination"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row marker-top">
				<div class="col-md-12">
					<div class="team-name">
						<div class="cut"></div>
						<h3 class="title">市场团队</h3>
					</div>
				</div>
			</div>
			<div class="row market-list">
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="img/teacher6.jpg">
						<div class="teacherBox">
							<p class="teacher-title">社招中心</br>总经理</p>
							<p class="wire"></p>
							<p class="teacher-name">齐武春</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="img/teacher8.jpg">
						<div class="teacherBox">
							<p class="teacher-title">吉林分校</br>市场部总经理</p>
							<p class="wire"></p>
							<p class="teacher-name">安全</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="img/teacher3(1).jpg">
						<div class="teacherBox">
							<p class="teacher-title">长春分校</br>市场部总经理</p>
							<p class="wire"></p>
							<p class="teacher-name">曹彦萍</p>
						</div>
					</li>
				</div>
				<div class="col-md-3">
					<li class="course-item team-item">
						<img src="img/teacher7.jpg">
						<div class="teacherBox">
							<p class="teacher-title">校企部</br>总经理</p>
							<p class="wire"></p>
							<p class="teacher-name">王天昊</p>
						</div>
					</li>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="footer-container">
						<div class="footer-title">
							<div class="footer-cut"></div>
							<h3 class="title">专业兴教.教育兴产.产业兴国</h3>
						</div>
						<div class="join">
							<img src="img/sao.png">
							<span class="join-our">扫微信关注我们</span>
						</div>
						<p>公司地址：长春市前进大街与修正路交汇，修正服务外包大厦2209室</p>
						<p>长春合众易成咨询服务技术有限公司©</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="cover">
		<div class="login">
			<div class="clear-close clearfix">
				<div class="close">
					<i class="iconfont">&#x3443;</i>
				</div>
			</div>
			<div class="login-list">
				<div class="login-item clearfix">
					<span class="login-title">学校：</span>
					 <ex:dict type="university" classname="text-item school-name" id="school" name="school" />
					
				</div>
				<div class="login-item clearfix">
					<span class="login-title">学院：</span>
					<ex:dict type="school_dept" classname="text-item college-name"  id="stuDept" name="stuDept" />
					<span class="login-title grade-title">年级：</span>
					<select class="text-item grade" name="classes" id="classes" >
						<option>大四</option>
						<option>大三</option>
						<option>大二</option>
						<option>大一</option>
					</select>
				</div>
				<div class="login-item clearfix">
					<span class="login-title">申请课程：</span>
					<ex:dict type="class_type" classname="text-item class-name" id="applyLesson" name="applyLesson" />
					
				</div>
				<div class="login-item clearfix">
					<span class="login-title">姓名：</span>
					<input type="text" class="text-item name-item item-special" readonly="readonly" placeholder="数字字典" name="name" id="name">
				</div>
			    <div class="login-item clearfix">
					<span class="login-title">手机号：</span>
					<input type="text" class="text-item phone-item item-special" readonly="readonly" placeholder="请输入手机号" name="tel" id="tel">
				</div>
			    <div class="login-item clearfix login-item-box">
				</div>
				<div class="div-phone login-item clearfix">
			        <label for="phone" class="login-title">验证码：</label>
			        <input type="text" id="phone" placeholder="请输入验证码" readonly>
			       <button href="javascript:;" class="send1" disabled>发送验证码</button>
			    </div>
				<div class="login-item login-submit">
					<button type="button" class="submit-btn" disabled>提交</button>
				</div>
			</div>
		</div>
	</div>
	<%if(cList!=null && cList.size()>0){
		%>
		<div class="course-box">
		<%for(int i = 0;i<cList.size();i++){
			%>
			<div class="Introduce">
			<div class="bomb-box">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="bomb-img fl">
						<div class="course-img">
						<img src="<%=basePath %>/images/course/<%=cList.get(i).getIconName()%>" id="img"/>
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title"><%=cList.get(i).getCourseName() %></p>
						<div class="teacherTitle">
							<%=cList.get(i).getDescribe() %>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%}%>
	</div>
	<% }%>
	
	<div class="teacher-box">
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="img/teacher.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">董事长</p>
						<p class="wire"></p>
						<p class="teacher-name">腾飞</p>
						<div class="teacherTitle">
							拥有多 年上市企业  BI 、 DI 、 OA 、 CRM 、ERP  等大型项目研发经验 ，于 2006  年起主持研发  ezETL 、ezOLAP  等重要商业智能核心产品 ，于 2008  年参与研发北京奥运门禁监控系统 。擅长大数据开发、Oracle  数据库优化、C  语言研发、 JavaEE开发 、Android  开发 ，并在从事研发期间多次为公司攻坚技术难关，为公司获得多项行业技术专利，是能力比较全面的  IT  行业技术专家。在 IT  技能培训行业里，拥有  7  年的培训经验 ，培养了  2340  名行业技术精英 ， 是不可多得的大学生技术导师.从教期间自主设计了一整套科学合理的阶段教学方法和培训方案，成为培训行业标准。
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="img/people.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">训导总监</p>
						<p class="wire"></p>
						<p class="teacher-name">乐毅</p>
						<div class="teacherTitle">
							拥有多 年企业  OA 、 O2O  电子商务平台等大型项目研发经验 ，曾经参与跨国项目 《 几内亚户籍管理系统 》 的设计与研发 ，
							擅长 JAVAEE  开发、 Android  客户端 APP  开发、iOS  客户端 APP  开发，在参与项目期间为公司培养大量移动端开发技术力量，是能力比较全面的 IT  行业技术专家。在  IT  技能培训行业 ， 拥有  9  年的培训经验 ， 培养了  3620  名行业技术精英，是名副其实的技术名师，并在从教期间为公司培养了大量年轻教师 ，并总结形成一整套教师培养管理考核办法 ，成为行业标准。
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="img/teacher2(1).jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">技术总监</p>
						<p class="wire"></p>
						<p class="teacher-name">隋春雨</p>
						<div class="teacherTitle">
							拥有7年的软件开发经验，3年的JAVAEE教学经验，七年间曾经参与过京东商城礼品卡系统、中国移动内容计费sysmgr月报系统、中国移动内容计费业务监控系统、案例管理系统、中国银行核心参数管理系统、中国银行公共信息管理系统等大型应用软件开发，3年的讲师经历指导帮助了近千名大学生走进IT行业，是行业内为数不多的既能从事开发又能从事教学的复合型专家。
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="img/teacher4.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">Web前端高级讲师</p>
						<p class="wire"></p>
						<p class="teacher-name">关艺馨</p>
						<div class="teacherTitle">
							拥有多 年上市企业 WEB端 O2O 、 B2C 电子商务平台等大型项目研发经验 ，于 2013  年独立开发 大型电子商务网站 前端业务，于 2014  年独立开发 O2O 产品库 ，  于 2015  年独立开发 大型在线试听网站，擅长H5开发，移动端响应式开发，微信小程序开发，JavaScript开发 ，熟悉 Java、PHP等后台开发语言。担任前端开发组长，并在从事开发工作过程中协助其他组员解决难题。是能力比较全面的 WEB前端开发工程师。在 IT  技能培训行业里，拥有  2  年的培训经验 ， 培养了  200  名行业技术精英 ， 结合BAT实际应用场景独创新颖教学方案。教学思路清晰，管理严格.
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="market-box">
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="img/teacher6.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">社招中心总经理</p>
						<p class="wire"></p>
						<p class="teacher-name">齐武春</p>
						<div class="teacherTitle">
							有15年的人力资源管理咨询经验，10年的市场营销经验，曾为长春市近120余家企业做过管理培训、人资培训、代理招聘、企业营销策划，积累了丰富的行业经验，并开阔了人生视野，对多个行业的发展有独到的见解。在9年多的IT职业教育从业期间，累计为长春市各个高校做职业教育及专业认识课程456场，一对一为近1560多名学生做过人生规划和职业指导，并成功推荐指导近3000多名学生成功就业于国内知名IT企业，并通过多年的职业规划经验，总结编写了《职业规划工作指导手册》，目前该手册已经成为吉林省IT职业教育领域职业规划的的工作指导
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="img/teacher8.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">吉林分校市场部总经理</p>
						<p class="wire"></p>
						<p class="teacher-name">安全</p>
						<div class="teacherTitle">
							致力于IT咨询服务事业，拥有6年的人力资源管理咨询经验，5年的市场营销经验，曾任吉林省人才委员会高级职业规划师，IT教育服务中心特约讲师。曾在吉林大学、长春理工大学、北华大学、吉林电子信息技术学院、吉林化工学院等多家大学做过企业宣讲、大学生职业规划及就业指导课程。主持过吉林大学、北华大学、东北电力大学校园招聘。为近1000名在校大学生做过职业规划和就业指导，为全国输送近500位IT人才，近100位高级工程师。2015年在吉林市建立合众易成吉林分公司，培养了大批技术人才与销售人才，成功推荐公司学员入职北京中科软、北京亚信联创、北京江融信科技有限公司、北京宇信易诚等多家知名企业。
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="img/teacher3(1).jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">长春分校市场部总经理</p>
						<p class="wire"></p>
						<p class="teacher-name">曹艳萍</p>
						<div class="teacherTitle">
							拥有14年的教育咨询、管理工作经验，积累了丰富的行业经验。曾任长春树仁专修学院持生办主任、长春市嘉恒职业学校副校长、吉林省UNIX计算机培训中心市场总经理、北京领测国际科技有限公司市场总监、现任合众易成科技有限公司市场部经理，曾带领并指导了10届学生成功就业。目前学员遍布全国各地，华为、浪朝、海信、完美世界、神州太月、等大中型IT公司，成为IT行业精英！
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="Introduce">
			<div class="login">
				<div class="clear-close clearfix">
					<div class="close">
						<i class="iconfont">&#x3443;</i>
					</div>
				</div>
				<div class="in-box clearfix">
					<div class="in-box-img fl">
						<div class="box-img">
							<img src="img/teacher7.jpg">
						</div>
					</div>
					<div class="in-box-title fl">
						<p class="teacher-title">校企部总经理</p>
						<p class="wire"></p>
						<p class="teacher-name">王天昊</p>
						<div class="teacherTitle">
							曾任日本朝日新闻西日本本部社长助理曾任河津不动产株式会社社长助理兼西日本部总经理曾任英可思（长春）科技有限公司中国总部总经理助理拥有在海外六年的工作和学习经历，曾经工作于日本朝日新闻株式会社和不动产公司，回国后就职于一家跨国公司，具有较强的国际化背景，拥有国际领先的管理办法和指导思想，带给公司一些先进的企业文化理念和成熟的管理制度，并多次参加商务部门、工信部门、科技部门、教育部门等高端会议，掌握行业前沿发展动态，为公司未来发展方向及业务拓展制定良好策略。
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/swiper-3.4.2.min.js"></script>
	<script type="text/javascript" src="js/slider.js"></script>
</body>
</html>