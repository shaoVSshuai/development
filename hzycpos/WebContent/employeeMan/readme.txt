复制代码

(1)、required:true               必输字段
(2)、remote:"remote-valid.jsp"   使用ajax方法调用remote-valid.jsp验证输入值
(3)、email:true                  必须输入正确格式的电子邮件
(4)、url:true                    必须输入正确格式的网址
(5)、date:true                   必须输入正确格式的日期，日期校验ie6出错，慎用
(6)、dateISO:true                必须输入正确格式的日期(ISO)，例如：2009-06-23，1998/01/22 只验证格式，不验证有效性
(7)、number:true                 必须输入合法的数字(负数，小数)
(8)、digits:true                 必须输入整数
(9)、creditcard:true             必须输入合法的信用卡号
(10)、equalTo:"#password"        输入值必须和#password相同
(11)、accept:                    输入拥有合法后缀名的字符串（上传文件的后缀）
(12)、maxlength:5                输入长度最多是5的字符串(汉字算一个字符)
(13)、minlength:10               输入长度最小是10的字符串(汉字算一个字符)
(14)、rangelength:[5,10]         输入长度必须介于 5 和 10 之间的字符串")(汉字算一个字符)
(15)、range:[5,10]               输入值必须介于 5 和 10 之间
(16)、max:5                      输入值不能大于5
(17)、min:10                     输入值不能小于10


<p>
		<span class="form_name">请输入姓名</span>
		<input type="text" class="form_style">
	</p>
	<p>
		<span class="form_name">请输入密码</span>
		<input type="password" class="form_style">
	</p>
	<p>
		<input type="submit" class="form_sub">
	</p>
	<p>
		<select class="form_sel">
			<option value="选择一">选择一</option>
			<option value="选择二">选择二</option>
			<option value="选择三">选择三</option>
		</select>
	</p>
	<p>
		<label for="file"class="
		 btn btn-default">上传文件</label>

		<inputid="file"type="file"style="display:none">
	</p>
	<table id="table-7" cellpadding="0" cellspacing="0"> 
		<thead>
			<th>姓名</th>
			<th>编号</th>
			<th>电话号码</th>
		</thead>
		<tbody>
			<tr>
				<td>李逍遥</td>
				<td>09</td>
				<td>1718000000</td>
			</tr>
			<tr style="border:1px solid #0099cd;">
				<td>恢复句</td>
				<td>29</td>
				<td>1718000000</td>
			</tr>
			<tr>
				<td>李俩了</td>
				<td>07</td>
				<td>1718000000</td>
			</tr>
			<tr>
				<td>接电话</td>
				<td>05</td>
				<td>1718000000</td>
			</tr>
		</tbody>
	</table>