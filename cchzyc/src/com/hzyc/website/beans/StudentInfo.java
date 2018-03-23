package com.hzyc.website.beans;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class StudentInfo {
	
	/**
	 * ��ҳ����
	 * */
	 //���� ������ҳ�󶨵�����
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
    //ѧ��
    private String code;
    //����
    private String name;
    //���֤��
    private String idcard;
    //�Ա�
    private String sex;
 

	//�绰
    private String tel;
    //QQ
    private String qq;
    //΢��
    private String wx;
    //���ᣨʡ��
    private String province;
    //��
    private String city;
    //��
    private String town;
    //��ͥסַ
    private String address;
	//����
    private String birthday;
    //ѧ��
    private String education;
    //ѧ��
    private String eLength;
    //����ѧУ
    private String school;
    //Ժϵ
    private String dept;
    //רҵ
    private String major;
    //��
    private Integer period;
    //�༶����˾���ڰ༶��
    private String classes;
    //�༶�䶯��ʷ
    private String classPath;
    //�滮ʦ
    private String planner;
    //�滮ʦ�䶯��ʷ
    private String plannerPath;
    //��ǰ��ʦ
    private String teacher;
    //��ʦ�䶯��ʷ
    private String teacherPath;
    //��ѧʱ��
    private Date time;
    //ѧԱ״̬
    private String state;
    //��ע
    private String remark;
    //ͼƬ·��
    private String imgName;
    //����ͼƬ
    private byte[] img;
    //������Ƭ��
    private String workImgName;
    //����ͼƬ
    private byte[] workImg;
    
    /**
     * ����
     * */
    
    public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
    
    //����
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