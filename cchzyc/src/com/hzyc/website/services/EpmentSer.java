package com.hzyc.website.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hzyc.website.beans.EmploymentNewsWithBLOBs;
import com.hzyc.website.mappers.EmploymentNewsMapper;

@Service
public class EpmentSer {

	@Autowired
	private EmploymentNewsMapper enm;
	
	/**
	 * ��������ѯ
	 * @author ֣��
	 * @param enw
	 * @return List<EmploymentNewsWithBLOBs>
	 */
	public List<EmploymentNewsWithBLOBs> selConditionEmp(EmploymentNewsWithBLOBs enw){
		List<EmploymentNewsWithBLOBs> enList = enm.selAllEmp(enw);
		
		return enList;
	}
	
	public int selConditionEmpCount(EmploymentNewsWithBLOBs enw) {
		return enm.selAllEmpCount(enw);
	}
	
	/**
	 * ¼��
	 * @author ֣��
	 * @return boolean flag 
	 * @throws IOException 
	 */
	public boolean insertEmp(EmploymentNewsWithBLOBs enw,MultipartFile img1,MultipartFile img2,HttpServletRequest request) throws IOException {
		boolean flag = false;
		byte[] by1 =  img1.getBytes();
		byte[] by2 =  img2.getBytes();
		//����������
		String filename1 = img1.getOriginalFilename();
		//�����պ�׺ .jpg
		String suffix1 = filename1.substring(filename1.lastIndexOf("."));
		//��������
		String newFileName1 = System.currentTimeMillis() + suffix1;
		//��˾logo����
		String filename2 = img2.getOriginalFilename();
		//��˾logo��׺ .jpg
		String suffix2 = filename2.substring(filename2.lastIndexOf("."));
		//��˾logo��
		String newFileName2 = System.currentTimeMillis() +"1"+ suffix2;
		
		enw.setLifePhoto(by1);
		enw.setLifePhotoName(newFileName1);
		enw.setCompanyLogo(by2);
		enw.setCompanyLogoName(newFileName2);
		
		//��ȡ·����ͼƬ��������
		String path = request.getSession().getServletContext().getRealPath("/");
		FileOutputStream fos1;
		FileOutputStream fos2;
		String finalPathAndName1 = path +"images/employment/"+newFileName2;
		String finalPathAndName2 = path +"images/employment/"+newFileName1;
		fos1 = new FileOutputStream(finalPathAndName1);
		fos2 = new FileOutputStream(finalPathAndName2);
		fos1.write(by2);
		fos2.write(by1);
		fos1.close();
		fos2.close();
		
		int result = enm.insert(enw);
		if (result > 0) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * ɾ����ҵ��Ϣ
	 * @author ֣��
	 * @param id
	 * @return
	 */
	public boolean delEp(int id) {
		boolean flag = false;
		int result = enm.deleteByPrimaryKey(id);
		if (result > 0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * �޸�
	 * @author ֣��
	 * @param enw
	 * @param img1
	 * @param img2
	 * @param request
	 * @return flag
	 * @throws IOException 
	 */
	public boolean updateEp(EmploymentNewsWithBLOBs enw,MultipartFile img1,MultipartFile img2,HttpServletRequest request) throws IOException {
		boolean flag = false;
		//������ڿ����ʾû�޸�
		if(img1 != null && ! img1.getOriginalFilename().equals("")){
			//�ӷ�������ɾ��ͼƬ
			String path = request.getSession().getServletContext().getRealPath("/");
			String finalPathAndName = path +"images/employment/"+ enw.getLifePhotoName();
			File file = new File(finalPathAndName);
			file.delete();
			
			byte[] by1 =  img1.getBytes();
			//����������
			String filename1 = img1.getOriginalFilename();
			//�����պ�׺ .jpg
			String suffix1 = filename1.substring(filename1.lastIndexOf("."));
			//��������
			String newFileName1 = System.currentTimeMillis() + suffix1;
			
			enw.setLifePhoto(by1);
			enw.setLifePhotoName(newFileName1);
			
			//�ϴ�ͼƬ��������
			FileOutputStream fos;
			String finalPathAndName1 = path +"images/employment/"+ enw.getLifePhotoName();
			fos = new FileOutputStream(finalPathAndName1);
			fos.write(by1);
			fos.close();
		}
		if (img2 != null && ! img2.getOriginalFilename().equals("")) {
			//�ӷ�������ɾ��ͼƬ
			String path = request.getSession().getServletContext().getRealPath("/");
			String finalPathAndName = path +"images/employment/"+ enw.getCompanyLogoName();
			File file = new File(finalPathAndName);
			file.delete();
			
			byte[] by2 =  img2.getBytes();
			//��˾logo����
			String filename2 = img2.getOriginalFilename();
			//��˾logo��׺ .jpg
			String suffix2 = filename2.substring(filename2.lastIndexOf("."));
			//��˾logo��
			String newFileName2 = System.currentTimeMillis()+"1" + suffix2;
			
			enw.setCompanyLogo(by2);
			enw.setCompanyLogoName(newFileName2);
			
			//�ϴ�ͼƬ��������
			FileOutputStream fos;
			String finalPathAndName1 = path +"images/employment/"+ enw.getCompanyLogoName();
			fos = new FileOutputStream(finalPathAndName1);
			fos.write(by2);
			fos.close();
		}
		
		int result = enm.updateByPrimaryKey(enw);
		if (result > 0) {
			flag = true;
		}
		return flag;
	}
	
	
	/**
	 * ͨ��id��ѯ
	 * @param id
	 * @return
	 */
	public EmploymentNewsWithBLOBs selById(int id) {
		
		return enm.selectByPrimaryKey(id);
	}
	
	
	/**
	 * ��ҳ
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List<EmploymentNewsWithBLOBs> fenye(int start , int pageSize){
		return enm.fenye(start , pageSize);
	}
}
