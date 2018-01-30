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
 * �û��߼������
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
		//true �����û����ظ�
		//falseΪû
		return userMapper.validUserName(username) > 0 ? true : false;
		
	}
	
	/**
	 * �����û�
	 * @author ��˧
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
		
		//�����û� ������Ҫָ����ɫ
		int effectRow =  userMapper.insertSelective(user);
		if(effectRow > 0 ){
			//���ݿⷵ�ص�����
			int id = user.getId();
			//��ɫid
			int rid = user.getRid();
			//�����ɫ
			userMapper.insertUserRole(id,rid);
		}
	}
	/**
	 * ɾ���û� 
	 * 	ע���˴�����Ҫɾ��  �û�-��ɫ ��
	 * 	���ݿ���дɾ��������
	 * @author ��˧
	 * @param id �û�ID
	 * */
	public boolean  deleteUser(int id){
		int i = userMapper.deleteByPrimaryKey(id);
		return i > 0 ? true : false;
	}
	//ѡ���ѯ�û�
	public List<User> selectice(User  user,PageInfo<?> pageInfo){
		//��ҳ�����ʾ6��
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<User> list = userMapper.selective(user);
		return list;
	}
	
	/**
	 * md5�����㷨
	 * 
	 * @author ZHENGBIN
	 */
	public static String md5Password(String password) {
        try {
            // �õ�һ����ϢժҪ��
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // ��ÿһ��byte ��һ�������� 0xff;
            for (byte b : result) {
                // ������
                int number = b & 0xff;// ����
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // ��׼��md5���ܺ�Ľ��
            return buffer.toString();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

	   }
	
	/**
	 * �û���¼����
	 * 
	 * @author ZHENGBIN
	 */
	public User loginVerification(User user){
		
		//��user�е�����ת����md5
		user.setPwd(md5Password(user.getPwd()));
		
		//��ѯ�˺�Ϊuser.uname���˺���Ϣ
		List<User> uList = selectByUname(user);
		
		//�鵽�˲����û�����������ȷ
		if (uList.size() > 0 && uList.get(0).getPwd().equals(user.getPwd())) {
			return uList.get(0);
		}else{
			return null;
		}
	}
	/**
	 * �˺��ظ���¼�жϣ����ظ���¼����һ����¼�߳�
	 * �����ظ�����Ϣ����session
	 * 
	 * @author ZHENGBIN
	 */
	public void userSession(HttpServletRequest request,HttpServletResponse response,User user){
		ServletContext application = request.getSession().getServletContext();
		//��session�����application��ȡ����ע��
		HttpSession session = (HttpSession)application.getAttribute(user.getId()+"");
		
		//�Ѿ���½
		if (application.getAttribute(user.getId()+"")!= null) {
			session.invalidate();					
			//�����û���Ϣ�浽session��
			int rId = userRoleMapper.selectByUid(user).getRid();
			request.getSession().setAttribute("rId", rId);
			request.getSession().setAttribute("user", user);
			System.out.println("if����session");
			application.setAttribute(user.getId()+"",request.getSession());
			
			
		}else{
			//û�е�½�����û���Ϣ�浽session
			request.getSession().setAttribute("user", user);
			System.out.println("����session"+user);
			//�û���ɫid
			int rId = userRoleMapper.selectByUid(user).getRid();
			//���û���ɫid�浽session
			request.getSession().setAttribute("rId", rId);
			//��session����浽application�У�key���û�id
			application.setAttribute(user.getId()+"",request.getSession());
			 
		}
		//����token
		String token = TokenProccessor.getInstance().makeToken();//��������  
        System.out.println("��FormServlet�����ɵ�token��"+token);  
        request.getSession().setAttribute("token", token);  //�ڷ�����ʹ��session����token(����)  
        //����cookie
        Cookie coken = new Cookie("token",token);
        coken.setMaxAge(1800);
        coken.setPath("/");
        coken.setHttpOnly(true);
        response.addCookie(coken);
        System.out.println("����cookie==========");
	}
	/**
	 * ͨ���û�����ѯ�û�������Ϣ
	 * 
	 * @author ZHENGBIN
	 */
	public List<User> selectByUname(User user){
		
		//��ѯ�˺�Ϊuser.uname���˺���Ϣ
		List<User> uList = userMapper.selectByUname(user);
		
		return uList;
	}
	
}
