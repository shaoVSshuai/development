package com.hzyc.hzycsms.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


/**
 * ѧԱ��Ϣ����ҵ���
 * 
 * @author SHAOSHUAI
 *
 */
//@Service
public class StudentSer {
	
	//@Autowired
	//StudentInfoMapper sim;
	
	/**
	 * ѧԱ��Ϣ���service
	 * 
	 * @author ��˧
	 * @param stu ����ͼƬ���͵�studentʵ��
	 * @return Boolean ���ѧԱ�Ƿ�ɹ�
	 * @throws IOException ��ȡ�ļ��ֽ�����ʧ���׳�
	 */
/*	public boolean addStudentInfo(StudentInfo stu,MultipartFile img1) throws IOException{
		//����ͼƬ·����
		//�ҵ�ͼƬ��׺
		String filename = img1.getOriginalFilename();
		//�ļ���׺
		String suffix = filename.substring(filename.lastIndexOf("."));
		//�Զ����ļ���
		String newFileName = UUID.randomUUID().toString() + suffix;
		System.out.println(newFileName);
		//ת��Ϊ�ֽ�����
		byte[] imgbyte = img1.getBytes();
		//����Ĭ��ֵ
		stu.setImg(imgbyte);
		//img��ͼƬ����
		
		//����Ĭ��ֵ
		stu.setBirthday("2017-05-06");
		stu.setCity("s");
		stu.setAddress("s");
		stu.setProvince("asd");
		stu.setTime(new Date());
		stu.setClasses("s");
		stu.setEducation("das");
		stu.setTeacher("sadasd");
		stu.setState("xxx");
		//��ѧ��˾ʱ��Ϊ��ǰʱ��
		if(stu.getTime()==null){
			
		}
		
		int flag = sim.insertSelective(stu);
		return flag >= 1 ? true : false;
	}
	
	*//**
	 * ɾ��ѧ����Ϣ
	 * 
	 * @param id ����
	 * @return Boolean �Ƿ�ɾ���ɹ�
	 *//*
	public boolean deleteStudentInfo(String id){
		
		
		int flag = sim.deleteByPrimaryKey(Integer.valueOf(id));
		return flag > 0 ? true : false;
	}
	
	*//**
	 * ����Id�޸�ѧԱ��Ϣ
	 * 
	 * @author ��˧
	 * @param stu ʵ��
	 * @return Boolean �Ƿ��޸ĳɹ�
	 *//*
	public boolean updateStudentInfo(StudentInfo  stu,HttpServletRequest request){
		int flag = 0;
		//����޸�ͼƬ����������ͼƬ·����ɾ��ԭͼƬ��
		StudentInfo currentStu = selectOneStudent(stu.getId());
		if(currentStu != null){
			//ԭͼƬ����
			String oldImgName = currentStu.getImgName();
			//������·��
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
	 * ѧ����Ϣ��ѯ
	 * 
	 * @author ��˧
	 * @param stu ѧԱʵ��
	 * @return List<StudentInfo> ��������������ѧԱ
	 *//*
	public List<StudentInfo> selective(StudentInfo stu){
		//ѡ���Բ�ѯ
		List<StudentInfo> list = sim.selective(stu);
		for(StudentInfo stu1 : list){
			//����
			stu1.setAge(stu1.getBirthday());
		}
		return list;
	}
	
	*//**
	 * Ψһѧ����ѯ
	 * 
	 * @param id �������ظ���id
	 * @return StudentInfo ѧԱpojo
	 *//*
	public StudentInfo selectOneStudent(int id){
		StudentInfo student = sim.selectByPrimaryKey( id  );
		//�����ֵ����  -- ����ת��
		student = CodeToText.getStuText(student);
		return student;
	}
	
	*//**
	 * ɾ���������ļ���ͼƬ
	 * 
	 * @param path ͼƬ·��
	 * @return Boolean �Ƿ�ɾ���ɹ�
	 * @throws NullPointerException �Ҳ���ͼƬ·���׳�
	 *//* 
	public boolean deletePhoto(String path,String filename) throws NullPointerException{
		File file = new File(path+filename);
		//�Ƿ�ɾ��
		if(file.exists()){
			file.delete();
		}
		return true;
	}
	*/
}
