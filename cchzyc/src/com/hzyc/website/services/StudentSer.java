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
 * 学员信息管理业务层
 * 
 * @author SHAOSHUAI
 *
 */
@Service
public class StudentSer {
	
	@Autowired
	StudentInfoMapper sim;
	
	
	/**
	 * 查询满足条件的个数
	 * 
	 * @param stu
	 * @return
	 */
	public int selAllCount(StudentInfo stu){
		//选择了全部班级
		if(stu.getClasses() == null || (stu.getClasses().equals("全部") || stu.getClasses().equals("0"))){
			stu.setClasses(null);
		}
		//选择性查询
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
	 * 学员信息添加service
	 * 
	 * @author 邵帅
	 * @param stu 带有图片类型的student实体
	 * @return Boolean 添加学员是否成功
	 * @throws IOException 获取文件字节数组失败抛出
	 */
	public boolean addStudentInfo(StudentInfo stu,MultipartFile img1) throws IOException{
		//处理图片路径等
		//找到图片后缀
		String filename = img1.getOriginalFilename();
		//文件后缀
		String suffix = filename.substring(filename.lastIndexOf("."));
		//自定义文件名
		String newFileName = stu.getIdcard() + suffix;

		//设置图片名字
		stu.setImgName(newFileName);
		
		//转换为字节数组
		byte[] imgbyte = img1.getBytes();
		//设置默认值
		stu.setImg(imgbyte);
		//img的图片名称
		
		//解决前台省份信息与后台不匹配问题
		stu.setProvince(stu.getProvince()+"0000");
		//入学公司时间为当前时间
		if(stu.getSchool()!=null){
			stu.setEducation("本科");
		}
		
		int flag = sim.insertSelective(stu);
		return flag >= 1 ? true : false;
	}
	
	/**
	 * 删除学生信息
	 * 
	 * @param id 主键
	 * @return Boolean 是否删除成功
	 */
	public boolean deleteStudentInfo(String id){
		
		
		int flag = sim.deleteByPrimaryKey(Integer.valueOf(id));
		return flag > 0 ? true : false;
	}
	
	/**
	 * 根据Id修改学员信息
	 * 
	 * @author 邵帅
	 * @param stu 实体
	 * @return Boolean 是否修改成功
	 * @throws IOException 
	 */
	public boolean updateStudentInfo(StudentInfo  stu,MultipartFile img1 , HttpServletRequest request) throws IOException{
		int flag = 0;
		//修改了img图片
		if(img1 != null && ! img1.getOriginalFilename().equals("")  ){
			System.out.println(img1 +"===" + img1.getOriginalFilename()) ;
			byte[] by =  img1.getBytes();
			
			//如果修改图片，重新设置图片路径（删除原图片）
			String uuid = UUID.randomUUID().toString();
			//原图片名称
			String filename = img1.getOriginalFilename();
			//文件后缀 .jpg
			String suffix = filename.substring(filename.lastIndexOf("."));
			//新文件名
			String newFileName = stu.getIdcard() + suffix;
			
			//设置新图片
			stu.setImg(by);
			//设置图片名称
			stu.setImgName(newFileName);
			
		}
		
		
		flag = sim.updateByPrimaryKeySelective(stu);
		//重新生成图片名称
		/*if(currentStu != null){
			//原图片名称
			String oldImgName = currentStu.getImgName();
			//服务器路径
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
	 * 学生信息查询[全部查询]
	 * 
	 * @author 邵帅
	 * @param stu 学员实体
	 * @return List<StudentInfo> 满足条件的所有学员
	 */
	public List<StudentInfo> selective(StudentInfo stu){
		//选择了全部班级
		if(stu.getClasses() == null || (stu.getClasses().equals("全部") || stu.getClasses().equals("0"))){
			stu.setClasses(null);
		}
		//选择性查询
		List<StudentInfo> list = sim.selective(stu);
		for(StudentInfo stu1 : list){
			//年龄
			stu1.setAge(stu1.getBirthday());
		}
		return list;
	}
	
	/**
	 * 学生信息查询[分页]
	 * 
	 * @author 邵帅
	 * @param stu 学员实体
	 * @return List<StudentInfo> 分页满足条件的所有学员
	 */
	public List<StudentInfo> selectiveByPage(StudentInfo stu){
		//选择了全部班级
		if(stu.getClasses() == null || (stu.getClasses().equals("全部") || stu.getClasses().equals("0"))){
			stu.setClasses(null);
		}
		//选择性查询
		List<StudentInfo> list = sim.selectiveByPage(stu);
		for(StudentInfo stu1 : list){
			//年龄
			stu1.setAge(stu1.getBirthday());
		}
		return list;
	}
	
	
	/**
	 * 唯一学生查询
	 * 
	 * @param id 主键不重复的id
	 * @return StudentInfo 学员pojo
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
			//服务器存在该图片 不处理
		}else{
			//将数据库图片放到服务器中
			byte[] img = student.getImg();
			FileOutputStream fos = null;
			try {
				fos =  new FileOutputStream(filename);
				fos.write(img);
				System.out.println("写入....");
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(fos != null){
					fos.close();
				}
			}
		}
		//数据字典代码  -- 文字转化
		//student = CodeToText.getStuText(student);
		return student;
	}
	
	/**
	 * 删除服务器文件夹图片
	 * 
	 * @param path 图片路径
	 * @return Boolean 是否删除成功
	 * @throws NullPointerException 找不到图片路径抛出
	 */ 
	public boolean deletePhoto(String path,String filename) throws NullPointerException{
		File file = new File(path+filename);
		//是否删除
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
	
	//下载学生导入模版
	public void downloadDemo(HttpServletResponse response , HttpServletRequest request){
		//demo文件的名称
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
            //文件名就叫原本的名字
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
	 * 导入excel文件
	 * @param file Excel文件
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean importExcel(MultipartFile file) throws Exception{
		InputStream input = null;
		try {
			input = file.getInputStream();
	        Workbook bWorkbook = null;  
	        	
	        bWorkbook = Workbook.getWorkbook(input);
	        // 获得第一个工作表对象
	        Sheet sheet = bWorkbook.getSheet(0);   
	        	int rows = sheet.getRows();
	        	List<StudentInfo> uList = new ArrayList<StudentInfo>();
	            //int columns = sheet.getColumns();
	        	System.out.println("rows==="+rows);
	            for(int i = 1;i < rows;i++){
	            	StudentInfo stu = new StudentInfo();
	            	//学号
	            	Cell cell = sheet.getCell(0, i);
	                String code = cell.getContents();
	                stu.setCode(code);
	                //姓名
	                Cell cell1 = sheet.getCell(1, i);
	                String name = cell1.getContents();
	                stu.setName(name);
	                //身份证号	
	                Cell cell2 = sheet.getCell(2, i);
	                String idcard = cell2.getContents();
	                stu.setIdcard(idcard);
	                //性别 
	                Cell cell3 = sheet.getCell(3, i);
	                String sex = cell3.getContents();
	                stu.setSex(sex);
	                //电话	
	                Cell cell4 = sheet.getCell(4, i);
	                String tel = cell4.getContents();
	                stu.setTel(tel);
	                // 	QQ	微信[选填]	省	市	县/区	家庭住址	生日	学历	学制	学校	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell5 = sheet.getCell(5, i);
	                String qq = cell5.getContents();
	                stu.setQq(qq);
	                // 	微信[选填]	省	市	县/区	家庭住址	生日	学历	学制	学校	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell6 = sheet.getCell(6, i);
	                String wx = cell6.getContents();
	                stu.setWx(wx == null ? "" : wx);
	                // 	省	市	县/区	家庭住址	生日	学历	学制	学校	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell7 = sheet.getCell(7, i);
	                String province = cell7.getContents();
	                stu.setProvince(province);
	                // 	市	县/区	家庭住址	生日	学历	学制	学校	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell8 = sheet.getCell(8, i);
	                String city = cell8.getContents();
	                stu.setCity(city);// 	县/区	家庭住址	生日	学历	学制	学校	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell9 = sheet.getCell(9, i);
	                String area = cell9.getContents();
	                stu.setTown(area); // 县/区	家庭住址	生日	学历	学制	学校	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell10 = sheet.getCell(10, i);
	                String address = cell10.getContents();
	                stu.setAddress(address);// 	家庭住址	生日	学历	学制	学校	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell11 = sheet.getCell(11, i);
	                String birth = cell11.getContents();
	                stu.setBirthday(birth);// 生日	学历	学制	学校	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell12 = sheet.getCell(12, i);
	                String xueli = cell12.getContents();
	                stu.setEducation(xueli); // 学历	学制	学校	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell13 = sheet.getCell(13, i);
	                String xuezhi = cell13.getContents();
	                stu.seteLength(xuezhi); // 学制	学校	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell14 = sheet.getCell(14, i);
	                String school = cell14.getContents();
	                stu.setSchool(school); // 学校	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell15 = sheet.getCell(15, i);
	                String dept = cell15.getContents();
	                stu.setDept(dept); // 	所属学院	专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell16 = sheet.getCell(16, i);
	                String major = cell16.getContents();
	                stu.setMajor(major);//  专业	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell17 = sheet.getCell(17, i);
	                String period = cell17.getContents();
	                stu.setPeriod(Integer.parseInt(period));//   	届	所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell18 = sheet.getCell(18, i);
	                String classes = cell18.getContents();
	                stu.setClasses(classes);//  所属公司班级	规划师	当前教师	公司入学时间	状态	备注
	                Cell cell19 = sheet.getCell(19, i);
	                String planner = cell19.getContents();
	                stu.setPlanner(planner);//   规划师	当前教师	公司入学时间	状态	备注
	                Cell cell20 = sheet.getCell(20, i);
	                String teacher = cell20.getContents();
	                stu.setTeacher(teacher);//   	当前教师	
	               /* Cell cell21 = sheet.getCell(21, i);
	                String time = cell21.getContents();
	                stu.setTime(new SimpleDateFormat().parse(time)); //公司入学时间	
	*/      
	                Cell cell22 = sheet.getCell(22, i);
	                String state = cell22.getContents();
	                stu.setState(state);//状态	
	                Cell cell23 = sheet.getCell(23, i);
	                String remark = cell23.getContents();
	                stu.setRemark(remark);//备注s
	                
	                //装入
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
	 * 导出学员信息
	 * 
	 * @param student
	 * @param request
	 * @param response
	 */
	public boolean exportExcel(StudentInfo student,HttpServletResponse response){
		
		//操作可写工作簿
		WritableWorkbook bWorkbook = null;
		OutputStream os = null;
		try {
			//查询所有可选学生
			List<StudentInfo> uList =  selective(student);
			System.out.println(student.getClasses()+"====");
			if(uList!= null && !uList.isEmpty()){
				response.reset();
				response.setContentType("application/vnd.ms-excel"); //保证不乱码
				String fileName = "合众易成学员.xls";
				/* //到第一个值项是attachment，这是真正的关键，设定了这个值，浏览器就会老老实实地显示另存为对话框，如果这个值设成 inline，则无论怎样浏览器都会自动尝试用已知关联的程序打开文件。
				response.addHeader("Content-Disposition","attachment; filename=\""+ new String(fileName.getBytes("gb2312"),"iso8859-1") + "\""); */
				
				response.setHeader("Content-Disposition", "attachment;"+ " filename="+ new String(fileName.getBytes(), "ISO-8859-1"));
				os = response.getOutputStream();
					//创建excel对象
					bWorkbook = Workbook.createWorkbook(os);
					//通过excel对象创建一个选项卡对象
					WritableSheet sheet = bWorkbook.createSheet("学员信息", 0);
					for(int i = 0;i < uList.size();i++){
						
						//开始绘制表头
						sheet.addCell(new Label(0, 0, "学号")); 
		                sheet.addCell(new Label(1, 0, "姓名"));
		                sheet.addCell(new Label(2, 0, "身份证号"));
		                sheet.addCell(new Label(3, 0, "性别"));
		                sheet.addCell(new Label(4, 0, "电话"));
		                sheet.addCell(new Label(5, 0, "QQ"));
		                sheet.addCell(new Label(6, 0, "微信"));
		                sheet.addCell(new Label(7, 0, "省"));
		                sheet.addCell(new Label(8, 0, "市"));
		                sheet.addCell(new Label(9, 0, "县/区"));
		                sheet.addCell(new Label(10, 0, "家庭住址"));
		                sheet.addCell(new Label(11, 0, "生日"));
		                sheet.addCell(new Label(12, 0, "学历	"));
		                sheet.addCell(new Label(13, 0, "学制"));
		                sheet.addCell(new Label(14, 0, "学校"));
		                sheet.addCell(new Label(15, 0, "所属学院"));
		                sheet.addCell(new Label(16, 0, "专业"));
		                sheet.addCell(new Label(17, 0, "所属公司班级"));
		                sheet.addCell(new Label(18, 0, "规划师"));
		                sheet.addCell(new Label(19, 0, "当前教师"));
		                sheet.addCell(new Label(20, 0, "公司入学时间"));
		                sheet.addCell(new Label(21, 0, "状态"));
		                sheet.addCell(new Label(22, 0, "备注"));
		             
		               
		                //开始绘制主体内容
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
						// 创建一个单元格对象，第一个为列，第二个为行，第三个为值  
			            //Label label = new Label(0, 2, "test");  
			            // 将创建好的单元格放入选项卡中  
			            //sheet.addCell(label);  
			            // 写如目标路径  
			            bWorkbook.write();
				} 
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}finally {  
				try {
					//关闭
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
