package com.hzyc.website.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hzyc.website.beans.Course;
import com.hzyc.website.beans.StudentInfo;
import com.hzyc.website.mappers.CourseMapper;
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
		System.out.println(img1.getOriginalFilename()+"000");
		//�޸���imgͼƬ
		if(img1 != null && ! img1.getOriginalFilename().equals("")  ){
			System.out.println(img1 +"===" + img1.getOriginalFilename()) ;
			byte[] by =  img1.getBytes();
			
			//����޸�ͼƬ����������ͼƬ·����ɾ��ԭͼƬ��
			String uuid = UUID.randomUUID().toString();
			System.out.println(uuid+"---------");
			//ԭͼƬ����
			String filename = img1.getOriginalFilename();
			//�ļ���׺ .jpg
			String suffix = filename.substring(filename.lastIndexOf("."));
			//���ļ���
			String newFileName = c.getIconName() + suffix;
			
			//������ͼƬ
			c.setIcon(by);
			//����ͼƬ����
			c.setIconName(newFileName);
			
		}
		
		
		flag = cm.updateByPrimaryKeySelective(c);
		//������ݿ�����ˣ������redis
		if(flag>=1) {
			InitService init =  (InitService)BeanUtil.getBean("InitService");
			init.selCourse();
		}
		
		return flag >= 1 ? true : false;
	}
}
