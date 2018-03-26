package com.hzyc.hzycsms.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


/**
 * 学员信息管理业务层
 * 
 * @author SHAOSHUAI
 *
 */
//@Service
public class StudentSer {
	
	//@Autowired
	//StudentInfoMapper sim;
	
	/**
	 * 学员信息添加service
	 * 
	 * @author 邵帅
	 * @param stu 带有图片类型的student实体
	 * @return Boolean 添加学员是否成功
	 * @throws IOException 获取文件字节数组失败抛出
	 */
/*	public boolean addStudentInfo(StudentInfo stu,MultipartFile img1) throws IOException{
		//处理图片路径等
		//找到图片后缀
		String filename = img1.getOriginalFilename();
		//文件后缀
		String suffix = filename.substring(filename.lastIndexOf("."));
		//自定义文件名
		String newFileName = UUID.randomUUID().toString() + suffix;
		System.out.println(newFileName);
		//转换为字节数组
		byte[] imgbyte = img1.getBytes();
		//设置默认值
		stu.setImg(imgbyte);
		//img的图片名称
		
		//设置默认值
		stu.setBirthday("2017-05-06");
		stu.setCity("s");
		stu.setAddress("s");
		stu.setProvince("asd");
		stu.setTime(new Date());
		stu.setClasses("s");
		stu.setEducation("das");
		stu.setTeacher("sadasd");
		stu.setState("xxx");
		//入学公司时间为当前时间
		if(stu.getTime()==null){
			
		}
		
		int flag = sim.insertSelective(stu);
		return flag >= 1 ? true : false;
	}
	
	*//**
	 * 删除学生信息
	 * 
	 * @param id 主键
	 * @return Boolean 是否删除成功
	 *//*
	public boolean deleteStudentInfo(String id){
		
		
		int flag = sim.deleteByPrimaryKey(Integer.valueOf(id));
		return flag > 0 ? true : false;
	}
	
	*//**
	 * 根据Id修改学员信息
	 * 
	 * @author 邵帅
	 * @param stu 实体
	 * @return Boolean 是否修改成功
	 *//*
	public boolean updateStudentInfo(StudentInfo  stu,HttpServletRequest request){
		int flag = 0;
		//如果修改图片，重新设置图片路径（删除原图片）
		StudentInfo currentStu = selectOneStudent(stu.getId());
		if(currentStu != null){
			//原图片名称
			String oldImgName = currentStu.getImgName();
			//服务器路径
			String serverPath = request.getContextPath();
			boolean b = deletePhoto(serverPath,oldImgName);
			if(b){
				//
				flag = sim.updateByPrimaryKeySelective(stu);
			}
		}
		return flag >= 1 ? true : false;
	}
	
	*//**
	 * 学生信息查询
	 * 
	 * @author 邵帅
	 * @param stu 学员实体
	 * @return List<StudentInfo> 满足条件的所有学员
	 *//*
	public List<StudentInfo> selective(StudentInfo stu){
		//选择性查询
		List<StudentInfo> list = sim.selective(stu);
		for(StudentInfo stu1 : list){
			//年龄
			stu1.setAge(stu1.getBirthday());
		}
		return list;
	}
	
	*//**
	 * 唯一学生查询
	 * 
	 * @param id 主键不重复的id
	 * @return StudentInfo 学员pojo
	 *//*
	public StudentInfo selectOneStudent(int id){
		StudentInfo student = sim.selectByPrimaryKey( id  );
		//数据字典代码  -- 文字转化
		student = CodeToText.getStuText(student);
		return student;
	}
	
	*//**
	 * 删除服务器文件夹图片
	 * 
	 * @param path 图片路径
	 * @return Boolean 是否删除成功
	 * @throws NullPointerException 找不到图片路径抛出
	 *//* 
	public boolean deletePhoto(String path,String filename) throws NullPointerException{
		File file = new File(path+filename);
		//是否删除
		if(file.exists()){
			file.delete();
		}
		return true;
	}
	*/
}
