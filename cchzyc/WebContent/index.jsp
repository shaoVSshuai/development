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
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>后台管理页面</title>
<link rel="stylesheet" href="<%=basePath%>plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css"
	href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
<link rel="stylesheet" href="<%=basePath%>build/css/app.css" media="all">
</head>

<body>
	<div class="layui-layout layui-layout-admin kit-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">后台管理系统</div>
			<div class="layui-logo kit-logo-mobile">K</div>
			<ul class="layui-nav layui-layout-left kit-nav">
				<li class="layui-nav-item"><a href="javascript:;">控制台</a></li>
				<li class="layui-nav-item"><a href="javascript:;">商品管理</a></li>
				<li class="layui-nav-item"><a href="javascript:;" id="pay"><i
						class="fa fa-gratipay" aria-hidden="true"></i> 捐赠我</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;">邮件管理</a>
						</dd>
						<dd>
							<a href="javascript:;">消息管理</a>
						</dd>
						<dd>
							<a href="javascript:;">授权管理</a>
						</dd>
					</dl>
				</li>
			</ul>
			<ul class="layui-nav layui-layout-right kit-nav">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://m.zhengjinfan.cn/images/0.jpg" class="layui-nav-img">
						Van </a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;">基本资料</a>
						</dd>
						<dd>
							<a href="javascript:;">安全设置</a>
						</dd>
					</dl>
				</li>
				<li class="layui-nav-item"><a href="javascript:;"><i
						class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black kit-side">
			<div class="layui-side-scroll">
				<div class="kit-side-fold"  ><i class="fa fa-navicon" style="line-height:34px;" aria-hidden="true"></i></div>
				
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="fa fa-plug" aria-hidden="true"></i><span> 学员信息管理 </span> </a>
						<dl class="layui-nav-child">
							<dd>
								<a href="stuMan/stuAdd/stu_add.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 学员信息录入</span> </a>
							</dd>
							<dd>
								<a href="stuMan/stuSel/stu_sel.jsp" target="main"> <i
									class="fa fa-user" aria-hidden="true"></i> <span> 学员信息查询</span>
								</a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span>学员导入/出</span> </a>
							</dd>
							<dd>
								<a href="stuMan/stu_distribution.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 学员分布统计</span> </a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 就业信息录入</span> </a>
							</dd>

							<dd>
								<a href="javascript:;" data-url="list4.html"
									data-icon="&#xe614;" data-title="列表四" kit-target data-id='4'><i
									class="fa fa-user" aria-hidden="true"></i> <span> form表单界面</span></a>
							</dd>
							<dd>
								<a href="javascript:;" kit-target
									data-options="{url:'test.html',icon:'&#xe6c6;',title:'表格',id:'9'}">
									<i class="layui-icon">&#xe6c6;</i> <span> 表格页面</span> </a>
							</dd>

						</dl>
					</li>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="fa fa-plug" aria-hidden="true"></i><span> 试听申请管理 </span> </a>
						<dl class="layui-nav-child">
							<dd>
								<a href="auditionMan/auditionSel/apply_top.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 试听学员查询</span> </a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i
									class="fa fa-user" aria-hidden="true"></i> 
									<span> 转入？（试听学员---> 正式学员）</span> </a>
							</dd>

						</dl>
					</li>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="fa fa-plug" aria-hidden="true"></i> <span> 部门员工管理 </span>
					</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="deptEmpMan/deptMan/frame.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 部门维护 </span> </a>
							</dd>
							<dd>
								<a href="deptEmpMan/empMan/emp_sel.jsp" target="main"> 
									<i class="layui-icon">&#xe6c6;</i> <span> 员工维护</span> </a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="fa fa-plug" aria-hidden="true"></i><span> 信息发布管理 </span> </a>
						<dl class="layui-nav-child">
							<dd>
								<a href="recInfAnnounce/recInf/recInf_entry.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 招聘信息录入</span> </a>
							</dd>
							<dd>
								<a href="recInfAnnounce/partTimeInf/partTimeInf_entry.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 兼职信息录入</span> </a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i
									class="fa fa-user" aria-hidden="true"></i> <span> 就业喜报发布
								</span> </a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i class="fa fa-user" aria-hidden="true"></i> <span>
									发布行业新闻 </span></a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 文章审核管理</span> </a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 评论管理</span> </a>
							</dd>
							
							<dd>
								<a href="recInfAnnounce/companyMan/companyMan_entry.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 公司维护</span> </a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="fa fa-plug" aria-hidden="true"></i><span> 个人管理  </span> </a>
						<dl class="layui-nav-child">
							<dd>
								<a href="loading.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 个人信息查询</span> </a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 个人信息修改</span> </a>
							</dd>

						</dl>
					</li>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="fa fa-plug" aria-hidden="true"></i><span> 首页信息管理</span> </a>
						<dl class="layui-nav-child">
							<dd>
								<a href="http://www.cchzyc.com" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 首页预览</span> </a>
							</dd>
							<dd>
							<dd>
								<a href="homepageInfoMan/course_maintenance.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 精品课程维护</span> </a>
							</dd>
								<a href="loading.jsp" target="main"> <i
									class="fa fa-user" aria-hidden="true"></i> <span> 市场团队维护</span>
								</a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i
									class="fa fa-user" aria-hidden="true"></i> <span> 教师团队维护</span>
								</a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i class="fa fa-user" aria-hidden="true"></i> <span>
									就业信息维护</span></a>
							</dd>

						</dl>
					</li>
					<li class="layui-nav-item"><a class="" href="javascript:;"><i
							class="fa fa-plug" aria-hidden="true"></i><span> 系统设置 </span> </a>
						<dl class="layui-nav-child">
							<dd>
								<a href="sysSetting/dictMan/dict_frame.jsp" target="main"> <i
									class="layui-icon">&#xe6c6;</i> <span> 数据字典管理 </span> </a>
							</dd>
							<dd>
								<a href="sysSetting/logMan/log_frame.jsp" target="main" >
									<i class="layui-icon">&#xe6c6;</i> <span>
										系统日志管理  </span> </a>
							</dd>
							<dd>
								<a href="sysSetting/roleMan/role_frame.jsp" target="main"> <i
									class="fa fa-user" aria-hidden="true"></i> <span>
										角色管理  </span> </a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i class="fa fa-user" aria-hidden="true"></i> <span> 
								权限管理  </span></a>
							</dd>
							<dd>
								 <a href="sysSetting/role/rFrame.jsp" target="main"> <i class="fa fa-user" aria-hidden="true"></i> <span>
									角色分配管理 </span></a>
							</dd>
							<dd>
								<a href="loading.jsp" target="main"> <i class="fa fa-user" aria-hidden="true"></i> <span>
									权限分配管理 </span></a>
							</dd>

						</dl>
					</li>
				</ul>
			</div>
		</div>
		<div style="position: relative; left: 201px;">
			<!-- 内容主体区域 -->
			<iframe name="main" width="1168px" height="545px" style="border: 0;"></iframe>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			2017 &copy; <a href="http://kit.zhengjinfan.cn/">kit.zhengjinfan.cn/</a>
			MIT license

		</div>
	</div>
	<script type="text/javascript">
        var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
        document.write(unescape("%3Cspan id='cnzz_stat_icon_1264021086'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1264021086%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
    </script>
	<script src="<%=basePath%>plugins/layui/layui.js"></script>
	<script>
        var message;
        layui.config({
            base: 'build/js/'
        }).use(['app', 'message'], function() {
            var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;
            //将message设置为全局以便子页面调用
            message = layui.message;
            //主入口
            app.set({
                type: 'iframe'
            }).init();
            $('#pay').on('click', function() {
                layer.open({
                    title: false,
                    type: 1,
                    content: '<img src="/build/images/pay.png" />',
                    area: ['500px', '250px'],
                    shadeClose: true
                });
            });
        });
    </script>
</body>

</html>