package com.hzyc.website.beans;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class StudentInfo {
	
	/**
	 * 分页属性
	 * */
	 //这是 用来分页绑定的属性
    private String nowPage;
    private String pageSize;
    private String maxPage;
    private String startPage;
	 
	
	public String getNowPage() {
		return nowPage;
	}
	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(String maxPage) {
		this.maxPage = maxPage;
	}
	public String getStartPage() {
		return startPage;
	}
	public void setStartPage(String startPage) {
		this.startPage = startPage;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "StudentInfo [id=" + id + ", code=" + code + ", name=" + name
				+ ", idcard=" + idcard + ", sex=" + sex + ", tel=" + tel
				+ ", qq=" + qq + ", wx=" + wx + ", province=" + province
				+ ", city=" + city + ", town=" + town + ", address=" + address
				+ ", birthday=" + birthday + ", education=" + education
				+ ", eLength=" + eLength + ", school=" + school + ", dept="
				+ dept + ", major=" + major + ", period=" + period
				+ ", classes=" + classes + ", classPath=" + classPath
				+ ", planner=" + planner + ", plannerPath=" + plannerPath
				+ ", teacher=" + teacher + ", teacherPath=" + teacherPath
				+ ", time=" + time + ", state=" + state + ", remark=" + remark
				+ ", imgName=" + imgName + ", img=" + Arrays.toString(img)
				+ ", workImgName=" + workImgName + ", workImg="
				+ Arrays.toString(workImg) + ", age=" + age + "]";
	}

	//ID
    private Integer id;
    //学号
    private String code;
    //姓名
    private String name;
    //身份证号
    private String idcard;
    //性别
    private String sex;
 

	//电话
    private String tel;
    //QQ
    private String qq;
    //微信
    private String wx;
    //籍贯（省）
    private String province;
    //市
    private String city;
    //区
    private String town;
    //家庭住址
    private String address;
	//生日
    private String birthday;
    //学历
    private String education;
    //学制
    private String eLength;
    //所在学校
    private String school;
    //院系
    private String dept;
    //专业
    private String major;
    //届
    private Integer period;
    //班级（公司所在班级）
    private String classes;
    //班级变动历史
    private String classPath;
    //规划师
    private String planner;
    //规划师变动历史
    private String plannerPath;
    //当前教师
    private String teacher;
    //教师变动历史
    private String teacherPath;
    //入学时间
    private Date time;
    //学员状态
    private String state;
    //备注
    private String remark;
    //图片路径
    private String imgName;
    //二寸图片
    private byte[] img;
    //工作照片名
    private String workImgName;
    //工作图片
    private byte[] workImg;
    
    /**
     * 新增
     * */
    
    public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
    
    //年龄
    private int age;
    public void setAge(String birthday){
    	if(birthday != null && birthday.length() > 4){
    		String birth = birthday.substring(0,4);
    		Date date = new Date();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    		String currentyear = sdf.format(date);
    		int age = Integer.parseInt(currentyear) - Integer.parseInt(birth);
    		this.age = age;
    	}
    }
    public int getAge(){
    	return age;
    	
    }
    
    public byte[] getImg() {
        return img;
    }

    public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public void setImg(byte[] img) {
        this.img = img;
    }

    public byte[] getWorkImg() {
        return workImg;
    }

    public void setWorkImg(byte[] workImg) {
        this.workImg = workImg;
    }
    
    public String getWorkImgName() {
        return workImgName;
    }

    public void setWorkImgName(String workImgName) {
        this.workImgName = workImgName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx == null ? null : wx.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String geteLength() {
        return eLength;
    }

    public void seteLength(String eLength) {
        this.eLength = eLength == null ? null : eLength.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath == null ? null : classPath.trim();
    }

    public String getPlanner() {
        return planner;
    }

    public void setPlanner(String planner) {
        this.planner = planner == null ? null : planner.trim();
    }

    public String getPlannerPath() {
        return plannerPath;
    }

    public void setPlannerPath(String plannerPath) {
        this.plannerPath = plannerPath == null ? null : plannerPath.trim();
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }

    public String getTeacherPath() {
        return teacherPath;
    }

    public void setTeacherPath(String teacherPath) {
        this.teacherPath = teacherPath == null ? null : teacherPath.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}