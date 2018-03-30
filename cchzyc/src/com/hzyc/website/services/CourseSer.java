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
	 * 查询所有数据
	 * @author ZHENGBIN
	 */
	public List<Course> display(){
		List<Course> cList = cm.selCourse();
		return cList;
	}
	
	/**
	 * @return
	 * 根据id查询课程
	 */
	public Course selCourseById(int id){
		Course course = cm.selectByPrimaryKey(id);
		return course;
	}
	/**
	 * @author 马荣福
	 * @param c 课程
	 * @param img1 图片
	 * @param request
	 * @return
	 * @throws IOException
	 * 修改数据库里的课程值
	 */
	public boolean updateCourseInfo(Course  c,MultipartFile img1 , HttpServletRequest request) throws IOException{
		int flag = 0;
		System.out.println(img1.getOriginalFilename()+"000");
		//修改了img图片
		if(img1 != null && ! img1.getOriginalFilename().equals("")  ){
			System.out.println(img1 +"===" + img1.getOriginalFilename()) ;
			byte[] by =  img1.getBytes();
			
			//如果修改图片，重新设置图片路径（删除原图片）
			String uuid = UUID.randomUUID().toString();
			System.out.println(uuid+"---------");
			//原图片名称
			String filename = img1.getOriginalFilename();
			//文件后缀 .jpg
			String suffix = filename.substring(filename.lastIndexOf("."));
			//新文件名
			String newFileName = c.getIconName() + suffix;
			
			//设置新图片
			c.setIcon(by);
			//设置图片名称
			c.setIconName(newFileName);
			
		}
		
		
		flag = cm.updateByPrimaryKeySelective(c);
		//如果数据库更新了，则更新redis
		if(flag>=1) {
			InitService init =  (InitService)BeanUtil.getBean("InitService");
			init.selCourse();
		}
		
		return flag >= 1 ? true : false;
	}
}
