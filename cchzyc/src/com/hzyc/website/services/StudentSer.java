package com.hzyc.website.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hzyc.website.beans.StudentInfo;
import com.hzyc.website.mappers.StudentInfoMapper;

/**
 * ѧԱ��Ϣ����ҵ���
 * 
 * @author SHAOSHUAI
 *
 */
@Service
public class StudentSer {
	
	@Autowired
	StudentInfoMapper sim;
	
	
	/**
	 * ��ѯ���������ĸ���
	 * 
	 * @param stu
	 * @return
	 */
	public int selAllCount(StudentInfo stu){
		//ѡ����ȫ���༶
		if(stu.getClasses() == null || (stu.getClasses().equals("ȫ��") || stu.getClasses().equals("0"))){
			stu.setClasses(null);
		}
		//ѡ���Բ�ѯ
		List<StudentInfo> list = sim.selective(stu);
		int i = 0;
		if(list != null && list.size() > 0){
			i = list.size();
		}else{
			i = 0;
		}
		return i;
	}
	
	/**
	 * ѧԱ��Ϣ���service
	 * 
	 * @author ��˧
	 * @param stu ����ͼƬ���͵�studentʵ��
	 * @return Boolean ���ѧԱ�Ƿ�ɹ�
	 * @throws IOException ��ȡ�ļ��ֽ�����ʧ���׳�
	 */
	public boolean addStudentInfo(StudentInfo stu,MultipartFile img1) throws IOException{
		//����ͼƬ·����
		//�ҵ�ͼƬ��׺
		String filename = img1.getOriginalFilename();
		//�ļ���׺
		String suffix = filename.substring(filename.lastIndexOf("."));
		//�Զ����ļ���
		String newFileName = stu.getIdcard() + suffix;

		//����ͼƬ����
		stu.setImgName(newFileName);
		
		//ת��Ϊ�ֽ�����
		byte[] imgbyte = img1.getBytes();
		//����Ĭ��ֵ
		stu.setImg(imgbyte);
		//img��ͼƬ����
		
		//���ǰ̨ʡ����Ϣ���̨��ƥ������
		stu.setProvince(stu.getProvince()+"0000");
		//��ѧ��˾ʱ��Ϊ��ǰʱ��
		if(stu.getSchool()!=null){
			stu.setEducation("����");
		}
		
		int flag = sim.insertSelective(stu);
		return flag >= 1 ? true : false;
	}
	
	/**
	 * ɾ��ѧ����Ϣ
	 * 
	 * @param id ����
	 * @return Boolean �Ƿ�ɾ���ɹ�
	 */
	public boolean deleteStudentInfo(String id){
		
		
		int flag = sim.deleteByPrimaryKey(Integer.valueOf(id));
		return flag > 0 ? true : false;
	}
	
	/**
	 * ����Id�޸�ѧԱ��Ϣ
	 * 
	 * @author ��˧
	 * @param stu ʵ��
	 * @return Boolean �Ƿ��޸ĳɹ�
	 * @throws IOException 
	 */
	public boolean updateStudentInfo(StudentInfo  stu,MultipartFile img1 , HttpServletRequest request) throws IOException{
		int flag = 0;
		//�޸���imgͼƬ
		if(img1 != null && ! img1.getOriginalFilename().equals("")  ){
			System.out.println(img1 +"===" + img1.getOriginalFilename()) ;
			byte[] by =  img1.getBytes();
			
			//����޸�ͼƬ����������ͼƬ·����ɾ��ԭͼƬ��
			String uuid = UUID.randomUUID().toString();
			//ԭͼƬ����
			String filename = img1.getOriginalFilename();
			//�ļ���׺ .jpg
			String suffix = filename.substring(filename.lastIndexOf("."));
			//���ļ���
			String newFileName = stu.getIdcard() + suffix;
			
			//������ͼƬ
			stu.setImg(by);
			//����ͼƬ����
			stu.setImgName(newFileName);
			
		}
		
		
		flag = sim.updateByPrimaryKeySelective(stu);
		//��������ͼƬ����
		/*if(currentStu != null){
			//ԭͼƬ����
			String oldImgName = currentStu.getImgName();
			//������·��
			String serverPath = request.getContextPath();
			boolean b = deletePhoto(serverPath,oldImgName);
			if(b){
				//
				flag = sim.updateByPrimaryKeySelective(stu);
			}
		}*/
		return flag >= 1 ? true : false;
	}
	
	/**
	 * ѧ����Ϣ��ѯ[ȫ����ѯ]
	 * 
	 * @author ��˧
	 * @param stu ѧԱʵ��
	 * @return List<StudentInfo> ��������������ѧԱ
	 */
	public List<StudentInfo> selective(StudentInfo stu){
		//ѡ����ȫ���༶
		if(stu.getClasses() == null || (stu.getClasses().equals("ȫ��") || stu.getClasses().equals("0"))){
			stu.setClasses(null);
		}
		//ѡ���Բ�ѯ
		List<StudentInfo> list = sim.selective(stu);
		for(StudentInfo stu1 : list){
			//����
			stu1.setAge(stu1.getBirthday());
		}
		return list;
	}
	
	/**
	 * ѧ����Ϣ��ѯ[��ҳ]
	 * 
	 * @author ��˧
	 * @param stu ѧԱʵ��
	 * @return List<StudentInfo> ��ҳ��������������ѧԱ
	 */
	public List<StudentInfo> selectiveByPage(StudentInfo stu){
		//ѡ����ȫ���༶
		if(stu.getClasses() == null || (stu.getClasses().equals("ȫ��") || stu.getClasses().equals("0"))){
			stu.setClasses(null);
		}
		//ѡ���Բ�ѯ
		List<StudentInfo> list = sim.selectiveByPage(stu);
		for(StudentInfo stu1 : list){
			//����
			stu1.setAge(stu1.getBirthday());
		}
		return list;
	}
	
	
	/**
	 * Ψһѧ����ѯ
	 * 
	 * @param id �������ظ���id
	 * @return StudentInfo ѧԱpojo
	 * @throws IOException 
	 */
	public StudentInfo selectOneStudent(int id,HttpServletRequest request) throws IOException{
		StudentInfo student = sim.selectByPrimaryKey( id  );
		String imgName = student.getImgName();
		String realPath = request.getSession().getServletContext().getRealPath("/");    
		System.out.println(realPath+"====");
		String filename = realPath + "stuImg/" + imgName;
		File file = new File(filename);
		if(file.exists()){
			//���������ڸ�ͼƬ ������
		}else{
			//�����ݿ�ͼƬ�ŵ���������
			byte[] img = student.getImg();
			FileOutputStream fos = null;
			try {
				fos =  new FileOutputStream(filename);
				fos.write(img);
				System.out.println("д��....");
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(fos != null){
					fos.close();
				}
			}
		}
		//�����ֵ����  -- ����ת��
		//student = CodeToText.getStuText(student);
		return student;
	}
	
	/**
	 * ɾ���������ļ���ͼƬ
	 * 
	 * @param path ͼƬ·��
	 * @return Boolean �Ƿ�ɾ���ɹ�
	 * @throws NullPointerException �Ҳ���ͼƬ·���׳�
	 */ 
	public boolean deletePhoto(String path,String filename) throws NullPointerException{
		File file = new File(path+filename);
		//�Ƿ�ɾ��
		if(file.exists()){
			file.delete();
		}
		return true;
	}
	
	public List<StudentInfo> getDistribution(){
		
		List<StudentInfo> list =sim.getDistribution();
		/*String values = "";
		for(int i = 0 ; i < list.size();i++){
			
			values += i == list.size()-1 ? list.get(i).getCode()+":"+list.get(i).getName() : 
										list.get(i).getCode()+":"+list.get(i).getName() + "-";
		}*/
		return list;
	}
	
	//����ѧ������ģ��
	public void downloadDemo(HttpServletResponse response , HttpServletRequest request){
		//demo�ļ�������
		String fileName = "stuImportDemo.xls";
		String filepath = request.getSession().getServletContext().getRealPath("/") + "demo/" +fileName;
		File file = new File(filepath);  
        InputStream inputStream = null;  
        OutputStream outputStream = null;  
        byte[] b= new byte[1024];  
        int len = 0;  
        try {  
            inputStream = new FileInputStream(file);  
            outputStream = response.getOutputStream();  
              
            response.setContentType("application/force-download");  
            //�ļ����ͽ�ԭ��������
            String filename = file.getName();  
            
            response.addHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));  
            response.setContentLength( (int) file.length( ) );  
              
            while((len = inputStream.read(b)) != -1){  
                outputStream.write(b, 0, len);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            if(inputStream != null){  
                try {  
                    inputStream.close();  
                    inputStream = null;  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if(outputStream != null){  
                try {  
                    outputStream.close();  
                    outputStream = null;  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }
		
	}
	
	/**
	 * ����excel�ļ�
	 * @param file Excel�ļ�
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean importExcel(MultipartFile file) throws Exception{
		InputStream input = null;
		try {
			input = file.getInputStream();
	        Workbook bWorkbook = null;  
	        	
	        bWorkbook = Workbook.getWorkbook(input);
	        // ��õ�һ�����������
	        Sheet sheet = bWorkbook.getSheet(0);   
	        	int rows = sheet.getRows();
	        	List<StudentInfo> uList = new ArrayList<StudentInfo>();
	            //int columns = sheet.getColumns();
	        	System.out.println("rows==="+rows);
	            for(int i = 1;i < rows;i++){
	            	StudentInfo stu = new StudentInfo();
	            	//ѧ��
	            	Cell cell = sheet.getCell(0, i);
	                String code = cell.getContents();
	                stu.setCode(code);
	                //����
	                Cell cell1 = sheet.getCell(1, i);
	                String name = cell1.getContents();
	                stu.setName(name);
	                //���֤��	
	                Cell cell2 = sheet.getCell(2, i);
	                String idcard = cell2.getContents();
	                stu.setIdcard(idcard);
	                //�Ա� 
	                Cell cell3 = sheet.getCell(3, i);
	                String sex = cell3.getContents();
	                stu.setSex(sex);
	                //�绰	
	                Cell cell4 = sheet.getCell(4, i);
	                String tel = cell4.getContents();
	                stu.setTel(tel);
	                // 	QQ	΢��[ѡ��]	ʡ	��	��/��	��ͥסַ	����	ѧ��	ѧ��	ѧУ	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell5 = sheet.getCell(5, i);
	                String qq = cell5.getContents();
	                stu.setQq(qq);
	                // 	΢��[ѡ��]	ʡ	��	��/��	��ͥסַ	����	ѧ��	ѧ��	ѧУ	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell6 = sheet.getCell(6, i);
	                String wx = cell6.getContents();
	                stu.setWx(wx == null ? "" : wx);
	                // 	ʡ	��	��/��	��ͥסַ	����	ѧ��	ѧ��	ѧУ	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell7 = sheet.getCell(7, i);
	                String province = cell7.getContents();
	                stu.setProvince(province);
	                // 	��	��/��	��ͥסַ	����	ѧ��	ѧ��	ѧУ	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell8 = sheet.getCell(8, i);
	                String city = cell8.getContents();
	                stu.setCity(city);// 	��/��	��ͥסַ	����	ѧ��	ѧ��	ѧУ	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell9 = sheet.getCell(9, i);
	                String area = cell9.getContents();
	                stu.setTown(area); // ��/��	��ͥסַ	����	ѧ��	ѧ��	ѧУ	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell10 = sheet.getCell(10, i);
	                String address = cell10.getContents();
	                stu.setAddress(address);// 	��ͥסַ	����	ѧ��	ѧ��	ѧУ	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell11 = sheet.getCell(11, i);
	                String birth = cell11.getContents();
	                stu.setBirthday(birth);// ����	ѧ��	ѧ��	ѧУ	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell12 = sheet.getCell(12, i);
	                String xueli = cell12.getContents();
	                stu.setEducation(xueli); // ѧ��	ѧ��	ѧУ	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell13 = sheet.getCell(13, i);
	                String xuezhi = cell13.getContents();
	                stu.seteLength(xuezhi); // ѧ��	ѧУ	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell14 = sheet.getCell(14, i);
	                String school = cell14.getContents();
	                stu.setSchool(school); // ѧУ	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell15 = sheet.getCell(15, i);
	                String dept = cell15.getContents();
	                stu.setDept(dept); // 	����ѧԺ	רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell16 = sheet.getCell(16, i);
	                String major = cell16.getContents();
	                stu.setMajor(major);//  רҵ	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell17 = sheet.getCell(17, i);
	                String period = cell17.getContents();
	                stu.setPeriod(Integer.parseInt(period));//   	��	������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell18 = sheet.getCell(18, i);
	                String classes = cell18.getContents();
	                stu.setClasses(classes);//  ������˾�༶	�滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell19 = sheet.getCell(19, i);
	                String planner = cell19.getContents();
	                stu.setPlanner(planner);//   �滮ʦ	��ǰ��ʦ	��˾��ѧʱ��	״̬	��ע
	                Cell cell20 = sheet.getCell(20, i);
	                String teacher = cell20.getContents();
	                stu.setTeacher(teacher);//   	��ǰ��ʦ	
	               /* Cell cell21 = sheet.getCell(21, i);
	                String time = cell21.getContents();
	                stu.setTime(new SimpleDateFormat().parse(time)); //��˾��ѧʱ��	
	*/      
	                Cell cell22 = sheet.getCell(22, i);
	                String state = cell22.getContents();
	                stu.setState(state);//״̬	
	                Cell cell23 = sheet.getCell(23, i);
	                String remark = cell23.getContents();
	                stu.setRemark(remark);//��עs
	                
	                //װ��
	                uList.add(stu);
	            }
	            return sim.batchInsert(uList) >= 1 ? true : false;    
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}finally{
			if(input != null){
				input.close();
			}
		}
	}
	
	/**
	 * ����ѧԱ��Ϣ
	 * 
	 * @param student
	 * @param request
	 * @param response
	 */
	public boolean exportExcel(StudentInfo student,HttpServletResponse response){
		
		//������д������
		WritableWorkbook bWorkbook = null;
		OutputStream os = null;
		try {
			//��ѯ���п�ѡѧ��
			List<StudentInfo> uList =  selective(student);
			System.out.println(student.getClasses()+"====");
			if(uList!= null && !uList.isEmpty()){
				response.reset();
				response.setContentType("application/vnd.ms-excel"); //��֤������
				String fileName = "�����׳�ѧԱ.xls";
				/* //����һ��ֵ����attachment�����������Ĺؼ����趨�����ֵ��������ͻ�����ʵʵ����ʾ���Ϊ�Ի���������ֵ��� inline����������������������Զ���������֪�����ĳ�����ļ���
				response.addHeader("Content-Disposition","attachment; filename=\""+ new String(fileName.getBytes("gb2312"),"iso8859-1") + "\""); */
				
				response.setHeader("Content-Disposition", "attachment;"+ " filename="+ new String(fileName.getBytes(), "ISO-8859-1"));
				os = response.getOutputStream();
					//����excel����
					bWorkbook = Workbook.createWorkbook(os);
					//ͨ��excel���󴴽�һ��ѡ�����
					WritableSheet sheet = bWorkbook.createSheet("ѧԱ��Ϣ", 0);
					for(int i = 0;i < uList.size();i++){
						
						//��ʼ���Ʊ�ͷ
						sheet.addCell(new Label(0, 0, "ѧ��")); 
		                sheet.addCell(new Label(1, 0, "����"));
		                sheet.addCell(new Label(2, 0, "���֤��"));
		                sheet.addCell(new Label(3, 0, "�Ա�"));
		                sheet.addCell(new Label(4, 0, "�绰"));
		                sheet.addCell(new Label(5, 0, "QQ"));
		                sheet.addCell(new Label(6, 0, "΢��"));
		                sheet.addCell(new Label(7, 0, "ʡ"));
		                sheet.addCell(new Label(8, 0, "��"));
		                sheet.addCell(new Label(9, 0, "��/��"));
		                sheet.addCell(new Label(10, 0, "��ͥסַ"));
		                sheet.addCell(new Label(11, 0, "����"));
		                sheet.addCell(new Label(12, 0, "ѧ��	"));
		                sheet.addCell(new Label(13, 0, "ѧ��"));
		                sheet.addCell(new Label(14, 0, "ѧУ"));
		                sheet.addCell(new Label(15, 0, "����ѧԺ"));
		                sheet.addCell(new Label(16, 0, "רҵ"));
		                sheet.addCell(new Label(17, 0, "������˾�༶"));
		                sheet.addCell(new Label(18, 0, "�滮ʦ"));
		                sheet.addCell(new Label(19, 0, "��ǰ��ʦ"));
		                sheet.addCell(new Label(20, 0, "��˾��ѧʱ��"));
		                sheet.addCell(new Label(21, 0, "״̬"));
		                sheet.addCell(new Label(22, 0, "��ע"));
		             
		               
		                //��ʼ������������
		                sheet.addCell(new Label(0, i + 1,  uList.get(i).getCode()) );
		                sheet.addCell(new Label(1, i + 1,  uList.get(i).getName()) );
		                sheet.addCell(new Label(2, i + 1,  uList.get(i).getIdcard()));
		                sheet.addCell(new Label(3, i + 1,  uList.get(i).getSex()) );
		                sheet.addCell(new Label(4, i + 1,  uList.get(i).getTel()) );
		                sheet.addCell(new Label(5, i + 1,  uList.get(i).getQq()) );
		                sheet.addCell(new Label(6, i + 1,  uList.get(i).getWx()) );
		                sheet.addCell(new Label(7, i + 1,  uList.get(i).getProvince()) );
		                sheet.addCell(new Label(8, i + 1,  uList.get(i).getCity()) );
		                sheet.addCell(new Label(9, i + 1,  uList.get(i).getTown()) );
		                sheet.addCell(new Label(10, i + 1,  uList.get(i).getAddress()) );
		                sheet.addCell(new Label(11, i + 1,  uList.get(i).getBirthday()) );
		                sheet.addCell(new Label(12, i + 1,  uList.get(i).getEducation()) );
		                sheet.addCell(new Label(13, i + 1,  uList.get(i).geteLength()) );
		                sheet.addCell(new Label(14, i + 1,  uList.get(i).getSchool()) );
		                sheet.addCell(new Label(15, i + 1,  uList.get(i).getDept()) );
		                sheet.addCell(new Label(16, i + 1,  uList.get(i).getMajor()) );
		                sheet.addCell(new Label(17, i + 1,  uList.get(i).getClasses()) );
		                sheet.addCell(new Label(18, i + 1,  uList.get(i).getPlanner()) );
		                sheet.addCell(new Label(19, i + 1,  uList.get(i).getTeacher()) );
		                sheet.addCell(new Label(20, i + 1,  String.valueOf(uList.get(i).getTime()== null ? "" : uList.get(i).getTime())) );
		                sheet.addCell(new Label(21, i + 1,  uList.get(i).getState()) );
		                sheet.addCell(new Label(22, i + 1,  uList.get(i).getRemark()) );
		                
					}
						// ����һ����Ԫ����󣬵�һ��Ϊ�У��ڶ���Ϊ�У�������Ϊֵ  
			            //Label label = new Label(0, 2, "test");  
			            // �������õĵ�Ԫ�����ѡ���  
			            //sheet.addCell(label);  
			            // д��Ŀ��·��  
			            bWorkbook.write();
				} 
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}finally {  
				try {
					//�ر�
					if(bWorkbook != null){
						bWorkbook.close();
					}
					if(os != null){
						os.close();
					}
					
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	             
	        }

	}
}
