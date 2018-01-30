package com.hzyc.hzycpos.service;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzyc.hzycpos.domain.User;
import com.hzyc.hzycpos.mapper.UserMapper;
import com.hzyc.hzycpos.mapper.UserRoleMapper;
import com.hzyc.hzycpos.util.FileConversion;
import com.hzyc.hzycpos.util.TokenProccessor;

/**
 * 用户逻辑处理层
 * 
 * @author ZHENGBIN
 */
@Service
public class UserSer {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	public boolean validUserName(String username){
		//true 代表用户名重复
		//false为没
		return userMapper.validUserName(username) > 0 ? true : false;
		
	}
	
	/**
	 * 插入用户
	 * @author 邵帅
	 * @throws Exception 
	 * */
	@Transactional
	public void insertUser(User user , File cardImgFace , File cardImgReverse) throws Exception{
		
		if(cardImgFace != null){
			byte[] b1 = FileConversion.PictureConversion(cardImgFace);
			user.setCardImgFace(b1);
		}
		if(cardImgReverse != null){
			byte[] b2 = FileConversion.PictureConversion(cardImgReverse);
			user.setCardImgReverse(b2);
		}
		
		//插入用户 另外需要指定角色
		int effectRow =  userMapper.insertSelective(user);
		if(effectRow > 0 ){
			//数据库返回的主键
			int id = user.getId();
			//角色id
			int rid = user.getRid();
			//插入角色
			userMapper.insertUserRole(id,rid);
		}
	}
	/**
	 * 删除用户 
	 * 	注：此处不需要删除  用户-角色 表
	 * 	数据库已写删除触发器
	 * @author 邵帅
	 * @param id 用户ID
	 * */
	public boolean  deleteUser(int id){
		int i = userMapper.deleteByPrimaryKey(id);
		return i > 0 ? true : false;
	}
	//选择查询用户
	public List<User> selectice(User  user,PageInfo<?> pageInfo){
		//分页插件显示6条
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<User> list = userMapper.selective(user);
		return list;
	}
	
	/**
	 * md5加密算法
	 * 
	 * @author ZHENGBIN
	 */
	public static String md5Password(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

	   }
	
	/**
	 * 用户登录处理
	 * 
	 * @author ZHENGBIN
	 */
	public User loginVerification(User user){
		
		//将user中的密码转化成md5
		user.setPwd(md5Password(user.getPwd()));
		
		//查询账号为user.uname的账号信息
		List<User> uList = selectByUname(user);
		
		//查到了并且用户名和密码正确
		if (uList.size() > 0 && uList.get(0).getPwd().equals(user.getPwd())) {
			return uList.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 账号重复登录判断，若重复登录则将上一个登录踢出
	 * 若不重复则将信息存入session
	 * 
	 * @author ZHENGBIN
	 */
	public void userSession(HttpServletRequest request,HttpServletResponse response,User user){
		ServletContext application = request.getSession().getServletContext();
		//将session对象从application中取出，注销
		HttpSession session = (HttpSession)application.getAttribute(user.getId()+"");
		
		//已经登陆
		if (application.getAttribute(user.getId()+"")!= null) {
			session.invalidate();					
			//将新用户信息存到session中
			int rId = userRoleMapper.selectByUid(user).getRid();
			request.getSession().setAttribute("rId", rId);
			request.getSession().setAttribute("user", user);
			System.out.println("if保存session");
			application.setAttribute(user.getId()+"",request.getSession());
			
			
		}else{
			//没有登陆过将用户信息存到session
			request.getSession().setAttribute("user", user);
			System.out.println("保存session"+user);
			//用户角色id
			int rId = userRoleMapper.selectByUid(user).getRid();
			//将用户角色id存到session
			request.getSession().setAttribute("rId", rId);
			//将session对象存到application中，key是用户id
			application.setAttribute(user.getId()+"",request.getSession());
			 
		}
		//保存token
		String token = TokenProccessor.getInstance().makeToken();//创建令牌  
        System.out.println("在FormServlet中生成的token："+token);  
        request.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)  
        //保存cookie
        Cookie coken = new Cookie("token",token);
        coken.setMaxAge(1800);
        coken.setPath("/");
        coken.setHttpOnly(true);
        response.addCookie(coken);
        System.out.println("保存cookie==========");
	}
	/**
	 * 通过用户名查询用户所有信息
	 * 
	 * @author ZHENGBIN
	 */
	public List<User> selectByUname(User user){
		
		//查询账号为user.uname的账号信息
		List<User> uList = userMapper.selectByUname(user);
		
		return uList;
	}
	
}
