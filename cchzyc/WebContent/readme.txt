各个文件夹说明：

css:网页样式文件
errorPage:页面无法找到或出错页面
images:图片文件
js:javasrcipt文件
json:暂无
layui:layui UI库

WEB-INF/tags:自定义标签库
WEB-INF/views:jsp视图

	<!-- type代表加载sex的字典项 name用来参数绑定  defaultValue是下拉框的默认值  classname是css类样式 -->
					 	<ex:dict type="sex" name="sex" defaultvalue="${student.sex}" classname="" />

/*
	layui 常用属性
*/
//非空校验	   lay-verify= required 
//input样式	  class= layui-input 
//表格   class= layui-table 
//按钮样式										 
     layui-btn layui-btn-primary  原始按钮 
     layui-btn  默认按钮 
     layui-btn layui-btn-normal  百搭按钮 
     layui-btn layui-btn-warm  暖色按钮 
     layui-btn layui-btn-danger  警告按钮 
     layui-btn layui-btn-disabled  禁用按钮 
     layui-btn layui-btn-primary layui-btn-lg  大型按钮 
     layui-btn layui-btn-primary  默认按钮 
     layui-btn layui-btn-primary layui-btn-sm  小型按钮 
     layui-btn layui-btn-primary layui-btn-xs  迷你按钮 
     layui-btn layui-btn-lg  大型按钮 
     layui-btn  默认按钮 
     layui-btn layui-btn-sm  小型按钮 
     layui-btn layui-btn-xs  迷你按钮 
     layui-btn layui-btn-lg layui-btn-normal  大型按钮 
     layui-btn layui-btn-normal  默认按钮 
     layui-btn layui-btn-sm layui-btn-normal  小型按钮 
     //这个最适中
     <button class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon"></i> 删除</button>
     layui-btn layui-btn-xs layui-btn-normal  迷你按钮 


