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
		//修改了img图片
		if(img1 != null && ! img1.getOriginalFilename().equals("")  ){
			//从服务器上删除图片
			String path = request.getSession().getServletContext().getRealPath("/");
			String finalPathAndName = path +"images/course/"+c.getIconName();
			File file = new File(finalPathAndName);
			file.delete();
			
			System.out.println(img1 +"===" + img1.getOriginalFilename()) ;
			byte[] by =  img1.getBytes();
			
			//原图片名称
			String filename = img1.getOriginalFilename();
			//文件后缀 .jpg
			String suffix = filename.substring(filename.lastIndexOf("."));
			//新文件名
			String newFileName = c.getId() + suffix;
			System.out.println(newFileName+"--------");
			//设置新图片
			c.setIcon(by);
			//设置图片名称
			c.setIconName(newFileName);
			System.out.println(c.getIconName()+"----");
			//上传图片到服务器
			FileOutputStream fos;
			String finalPathAndName1 = path +"images/course/"+c.getIconName();
			fos = new FileOutputStream(finalPathAndName1);
			fos.write(c.getIcon());
			fos.close();
		}
		
		
		flag = cm.updateByPrimaryKeySelective(c);
		//如果数据库更新了，则更新redis
		if(flag>=1) {
			ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			SystemInit sys = (SystemInit)appContext.getBean("systemInit");
			List<Course> cList  = sys.getInitService().selCourse();
		    CourseForInit.setList(cList);
		}
		
		return flag >= 1 ? true : false;
	}
}
