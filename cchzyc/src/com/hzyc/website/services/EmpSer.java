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
	 * ��ѯ���й滮ʦ
	 * @return
	 */
	public List<Employee> selPlanner(){
		return em.selPlanner();
	}
	
	/**
	 * ѡ���Բ�ѯԱ��
	 * @param emp
	 * @return
	 */
	public List<Employee> selEmp(Employee emp){
		//û��ѡ��ȷ����ְλ��ѯ 0:����ѡ��ȫ��
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
			 //��������
			 emp1.setAge(emp1.getBirthday());
		 }
		 return empList;
	}
	
	/**
	 * ����id��ѯԱ��
	 * 
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	public Employee selEmpById(String id,HttpServletRequest request) throws IOException {
		Employee emp = new Employee();
		emp.setId(Integer.parseInt(id));
		List<Employee> employee = em.selEmp(emp);
		//��ֹ����
		if(employee != null && employee.size() > 0){
			//��һ��
			emp = employee.get(0);
			//����ͼƬ
			//������Ƭ����
			String imgName = emp.getEmpImgName();
			String realPath = request.getSession().getServletContext().getRealPath("/");    
			System.out.println(realPath+"====");
			String filename = realPath + "empImg/" + imgName;
			File file = new File(filename);
			if(file.exists()){
				//���������ڸ�ͼƬ ������
			}else{
				//�����ݿ�ͼƬ�ŵ���������
				byte[] img = emp.getEmpImg();
				FileOutputStream fos = null;
				try {
					fos =  new FileOutputStream(filename);
					fos.write(img);
					System.out.println("д�������Ƭ....");
				} catch ( Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if(fos != null){
						fos.close();
					}
				}
			}
			//���֤��Ƭ����
			String cardImgName = emp.getCardImgName();
			String filename2 = realPath + "empImg/" + cardImgName;
			File file2 = new File(filename2);
			if(file2.exists()){
				//���������ڸ�ͼƬ ������
			}else{
				//�����ݿ�ͼƬ�ŵ���������
				byte[] img = emp.getCardImg();
				FileOutputStream fos = null;
				try {
					fos =  new FileOutputStream(filename2);
					fos.write(img);
					System.out.println("д�����֤��Ƭ....");
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
			throw new NullPointerException("Ա����ѯ�쳣");
		}
	}
	/**
	 * ���Ա��
	 *   ͼƬ�����ɹ���: 
	 *   	������Ƭ ���֤��.jpg  
	 *   	����գ� idcard+���֤��.jpg
	 *   	չʾͼƬ dis���֤��.jpg
	 * @param emp ʵ��
	 * @param img1 ������Ƭ
	 * @param img2 ���֤��Ƭ
	 * @return
	 * @throws IOException
	 */
	public boolean addEmp(Employee emp, MultipartFile img1 ,MultipartFile img2,MultipartFile img3) throws IOException{
		if(img1 != null) {
			//����ͼƬ
			byte[] imgsecond = img1.getBytes();
			String filename = img1.getOriginalFilename();
			//�ļ���׺ .jpg
			String suffix = filename.substring(filename.lastIndexOf("."));
			//�¶���ͼƬ�ļ���
			String newSecondName = emp.getIdcard() + suffix;
			emp.setEmpImg(imgsecond);
			emp.setEmpImgName(newSecondName);
		}
		if(img2 != null) {
			//���֤��Ƭ
			byte[] idimg = img2.getBytes();
			String cardfilename = img2.getOriginalFilename();
			//�ļ���׺ .jpg
			String suffix2 = cardfilename.substring(cardfilename.lastIndexOf("."));
			//�����֤�ļ���
			String newcardName = "idcard_"+emp.getIdcard() + suffix2;
			
			emp.setCardImg(idimg);
			emp.setCardImgName(newcardName);
		}
		if(img3 != null) {
			//���֤��Ƭ
			byte[] disImg = img3.getBytes();
			String cardfilename = img3.getOriginalFilename();
			//�ļ���׺ .jpg
			String suffix3 = cardfilename.substring(cardfilename.lastIndexOf("."));
			//�����֤�ļ���
			String newcardName = "idcard_"+emp.getIdcard() + suffix3;
			
			emp.setDisImgName(newcardName);
			emp.setDisImg(disImg);
		}
		
		
		return  em.insertSelective(emp) > 0 ? true : false;
	}
	
	/**
	 * ��¼
	 * 
	 * @param emp
	 * @return
	 */
	public Employee login(Employee emp){
		return em.login(emp) ;
	}
	/**
	 * ͨ�������͹��Ų�ѯԱ��
	 * @author ������
	 * @param emp
	 * @return List<Employee>
	 */
	public List<Employee> selEmpByNameOrCode(Employee emp){
		return em.selEmpByNameOrCode(emp);
	}
	
	/**
	 * @author ���ٸ�
	 * @param code Ա����code
	 * @return Ա�����е�ְλ�ļ���
	 */
	public List<EmployeeJob> selEmployeeJobByCode(String code){
		return ejm.selEmployeeJobByCode(code);
	}
}
