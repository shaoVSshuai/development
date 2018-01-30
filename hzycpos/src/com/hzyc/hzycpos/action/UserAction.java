package com.hzyc.hzycpos.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageInfo;
import com.hzyc.hzycpos.domain.Privilege;
import com.hzyc.hzycpos.domain.PromptMessage;
import com.hzyc.hzycpos.domain.User;
import com.hzyc.hzycpos.service.PrivilegeSer;
import com.hzyc.hzycpos.service.UserSer;
import com.hzyc.hzycpos.system.PromptMan;
import com.hzyc.hzycpos.util.FileConversion;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户控制层
 * 通过实现自动引入接口实现IOC方式的request注入
 * 
 * @author ZHENGBIN
 */
@Controller
public class UserAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//model绑定
	private User user;
	
	//封装提示信息
	private PromptMessage pm;
	
	//用户查询绑定
	private List<User> userList;
	//分页信息绑定
	private PageInfo<?> pageInfo;
	
	public PageInfo<?> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<?> pageInfo) {
		this.pageInfo = pageInfo;
	}
	//用户身份证图片
	private File cardImgFace;
	//反面
	private File cardImgReverse; 
	
	public void setCardImgFace(File cardImgFace) {
		this.cardImgFace = cardImgFace;
	}

	public void setCardImgReverse(File cardImgReverse) {
		this.cardImgReverse = cardImgReverse;
	}
	@Autowired
	private UserSer userSer;
	@Autowired
	private PrivilegeSer privilegeSer;

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	//封装用户权限信息
	private List<Privilege> pList;
	
	//验证用户名是否重复
	public String validUserName(){
		String username = request.getParameter("userName");
		if(username != null){
			boolean b = userSer.validUserName(username);
			PromptMessage pm = null;
			//重复了
			if(b){
				pm = PromptMan.convert(107);
			}else{
				pm = PromptMan.convert(106);
			}
			this.pm = pm;
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	//查询用户 多条件查询
	public String selective(){
		List<User> list = userSer.selectice(user,pageInfo);
		String a = request.getParameter("user.trueName");
		System.out.println(a);
		System.out.println(user.getTrueName()+"====");
		
		this.userList  = list;
		//连续显示的页数(导航页)
		pageInfo = new PageInfo<>(list , 5); 
		System.out.println(pageInfo.getNavigatepageNums().length);
		return SUCCESS;
	}
	
	
	
	//录入用户
	public String insert(){
		try {
			//身份证正反
			userSer.insertUser(user , cardImgFace , cardImgReverse);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} 
	}
	
	//删除用户
	public String delete(){
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			userSer.deleteUser(id);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} 
	}
	
	
	/**
	 * 用户登录
	 * 
	 * @author ZHENGBIN
	 */
	public String loginVerification()throws Exception{
		long a=System.currentTimeMillis();
		this.user = new User();
		//获取用户名和密码
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		//添加到user里
		user.setUname(username);
		user.setPwd(password);
		
		user = userSer.loginVerification(user);
		response.setHeader("Access-Control-Allow-Origin", "*");
		//判断用户名密码是否正确
		if (user != null) {
			//进行账号重复登录判断以及信息保存
			userSer.userSession(request,response,user);
			//正确则进行用户-角色-权限查询
			//通过账号账号获取权限信息
			this.pList = privilegeSer.sel(user,request);
			
			System.out.println("\r<br>执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
			//跳转主页面
			System.out.println("登录成功！！！！！！！！！");
			PromptMessage pm = PromptMan.convert(102);
			this.pm = pm;
			return SUCCESS;
		} else {
			//失败则将失败信息传回
			System.out.println("登录失败！！！！！！！！！");
			PromptMessage pm = PromptMan.convert(104);
			this.pm= pm;
			//返回登录页面
			return ERROR;
		}
		
	}
	

	//绑定用户权限信息
	public List<Privilege> getpList() {
		return pList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	//get绑定message信息
	public PromptMessage getPm(){
		return this.pm;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}	
	public List<User> getUserList() {
		return userList;
	}


}
