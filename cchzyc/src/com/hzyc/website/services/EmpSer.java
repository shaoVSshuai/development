package com.hzyc.website.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hzyc.website.beans.Employee;
import com.hzyc.website.beans.EmployeeJob;
import com.hzyc.website.mappers.EmployeeJobMapper;
import com.hzyc.website.mappers.EmployeeMapper;

@Service
public class EmpSer {
	
	@Autowired
	EmployeeMapper em;
	
	@Autowired
	EmployeeJobMapper ejm;
	
	/**
	 * 查询所有规划师
	 * @return
	 */
	public List<Employee> selPlanner(){
		return em.selPlanner();
	}
	
	/**
	 * 选择性查询员工
	 * @param emp
	 * @return
	 */
	public List<Employee> selEmp(Employee emp){
		//没有选择确定的职位查询 0:代表选择全部
		if(emp.getDeptOne() == null || emp.getDeptOne().equals("0")){
			emp.setDeptOne(null);
		}
		if(emp.getDeptTwo() == null || emp.getDeptTwo().equals("0")){
			emp.setDeptTwo(null);
		}
		if(emp.getJobCode() == null || emp.getJobCode().equals("0")){
			emp.setJobCode(null);
		}
		List<Employee> empList =  em.selEmp(emp);
		 for(Employee emp1 : empList){
			 //设置年龄
			 emp1.setAge(emp1.getBirthday());
		 }
		 return empList;
	}
	
	/**
	 * 根据id查询员工
	 * 
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	public Employee selEmpById(String id,HttpServletRequest request) throws IOException {
		Employee emp = new Employee();
		emp.setId(Integer.parseInt(id));
		List<Employee> employee = em.selEmp(emp);
		//防止出错
		if(employee != null && employee.size() > 0){
			//第一条
			emp = employee.get(0);
			//处理图片
			//二寸照片处理
			String imgName = emp.getEmpImgName();
			String realPath = request.getSession().getServletContext().getRealPath("/");    
			System.out.println(realPath+"====");
			String filename = realPath + "empImg/" + imgName;
			File file = new File(filename);
			if(file.exists()){
				//服务器存在该图片 不处理
			}else{
				//将数据库图片放到服务器中
				byte[] img = emp.getEmpImg();
				FileOutputStream fos = null;
				try {
					fos =  new FileOutputStream(filename);
					fos.write(img);
					System.out.println("写入二寸照片....");
				} catch ( Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if(fos != null){
						fos.close();
					}
				}
			}
			//身份证照片处理
			String cardImgName = emp.getCardImgName();
			String filename2 = realPath + "empImg/" + cardImgName;
			File file2 = new File(filename2);
			if(file2.exists()){
				//服务器存在该图片 不处理
			}else{
				//将数据库图片放到服务器中
				byte[] img = emp.getCardImg();
				FileOutputStream fos = null;
				try {
					fos =  new FileOutputStream(filename2);
					fos.write(img);
					System.out.println("写入身份证照片....");
				} catch ( Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if(fos != null){
						fos.close();
					}
				}
			}
			return emp;
		}else{
			throw new NullPointerException("员工查询异常");
		}
	}
	/**
	 * 添加员工
	 *   图片名生成规则: 
	 *   	二寸照片 身份证号.jpg  
	 *   	身份照： idcard+身份证号.jpg
	 *   	展示图片 dis身份证号.jpg
	 * @param emp 实体
	 * @param img1 二寸照片
	 * @param img2 身份证照片
	 * @return
	 * @throws IOException
	 */
	public boolean addEmp(Employee emp, MultipartFile img1 ,MultipartFile img2,MultipartFile img3) throws IOException{
		if(img1 != null) {
			//二寸图片
			byte[] imgsecond = img1.getBytes();
			String filename = img1.getOriginalFilename();
			//文件后缀 .jpg
			String suffix = filename.substring(filename.lastIndexOf("."));
			//新二寸图片文件名
			String newSecondName = emp.getIdcard() + suffix;
			emp.setEmpImg(imgsecond);
			emp.setEmpImgName(newSecondName);
		}
		if(img2 != null) {
			//身份证照片
			byte[] idimg = img2.getBytes();
			String cardfilename = img2.getOriginalFilename();
			//文件后缀 .jpg
			String suffix2 = cardfilename.substring(cardfilename.lastIndexOf("."));
			//新身份证文件名
			String newcardName = "idcard_"+emp.getIdcard() + suffix2;
			
			emp.setCardImg(idimg);
			emp.setCardImgName(newcardName);
		}
		if(img3 != null) {
			//身份证照片
			byte[] disImg = img3.getBytes();
			String cardfilename = img3.getOriginalFilename();
			//文件后缀 .jpg
			String suffix3 = cardfilename.substring(cardfilename.lastIndexOf("."));
			//新身份证文件名
			String newcardName = "idcard_"+emp.getIdcard() + suffix3;
			
			emp.setDisImgName(newcardName);
			emp.setDisImg(disImg);
		}
		
		
		return  em.insertSelective(emp) > 0 ? true : false;
	}
	
	/**
	 * 登录
	 * 
	 * @param emp
	 * @return
	 */
	public Employee login(Employee emp){
		return em.login(emp) ;
	}
	/**
	 * 通过姓名和工号查询员工
	 * @author 张阳媚
	 * @param emp
	 * @return List<Employee>
	 */
	public List<Employee> selEmpByNameOrCode(Employee emp){
		return em.selEmpByNameOrCode(emp);
	}
	
	/**
	 * @author 马荣福
	 * @param code 员工的code
	 * @return 员工所有的职位的集合
	 */
	public List<EmployeeJob> selEmployeeJobByCode(String code){
		return ejm.selEmployeeJobByCode(code);
	}
}
