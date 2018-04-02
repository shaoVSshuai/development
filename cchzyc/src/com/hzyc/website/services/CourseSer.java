package com.hzyc.website.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hzyc.website.beans.Course;
import com.hzyc.website.beans.StudentInfo;
import com.hzyc.website.mappers.CourseMapper;
import com.hzyc.website.system.CourseForInit;
import com.hzyc.website.system.SystemInit;
import com.hzyc.website.utils.BeanUtil;

@Service
public class CourseSer {
	
	@Autowired
	private CourseMapper cm;
	
	/**
	 * ��ѯ��������
	 * @author ZHENGBIN
	 */
	public List<Course> display(){
		List<Course> cList = cm.selCourse();
		return cList;
	}
	
	/**
	 * @return
	 * ����id��ѯ�γ�
	 */
	public Course selCourseById(int id){
		Course course = cm.selectByPrimaryKey(id);
		return course;
	}
	/**
	 * @author ���ٸ�
	 * @param c �γ�
	 * @param img1 ͼƬ
	 * @param request
	 * @return
	 * @throws IOException
	 * �޸����ݿ���Ŀγ�ֵ
	 */
	public boolean updateCourseInfo(Course  c,MultipartFile img1 , HttpServletRequest request) throws IOException{
		int flag = 0;
		//�޸���imgͼƬ
		if(img1 != null && ! img1.getOriginalFilename().equals("")  ){
			//�ӷ�������ɾ��ͼƬ
			String path = request.getSession().getServletContext().getRealPath("/");
			String finalPathAndName = path +"images/course/"+c.getIconName();
			File file = new File(finalPathAndName);
			file.delete();
			
			System.out.println(img1 +"===" + img1.getOriginalFilename()) ;
			byte[] by =  img1.getBytes();
			
			//ԭͼƬ����
			String filename = img1.getOriginalFilename();
			//�ļ���׺ .jpg
			String suffix = filename.substring(filename.lastIndexOf("."));
			//���ļ���
			String newFileName = c.getId() + suffix;
			System.out.println(newFileName+"--------");
			//������ͼƬ
			c.setIcon(by);
			//����ͼƬ����
			c.setIconName(newFileName);
			System.out.println(c.getIconName()+"----");
			//�ϴ�ͼƬ��������
			FileOutputStream fos;
			String finalPathAndName1 = path +"images/course/"+c.getIconName();
			fos = new FileOutputStream(finalPathAndName1);
			fos.write(c.getIcon());
			fos.close();
		}
		
		
		flag = cm.updateByPrimaryKeySelective(c);
		//������ݿ�����ˣ������redis
		if(flag>=1) {
			ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			SystemInit sys = (SystemInit)appContext.getBean("systemInit");
			List<Course> cList  = sys.getInitService().selCourse();
		    CourseForInit.setList(cList);
		}
		
		return flag >= 1 ? true : false;
	}
}
