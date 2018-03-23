package com.hzyc.website.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Employee {
	
	private List<DeptJob> deptJobList;
	
	//二寸照片名 , 身份证照片名  , 展示照片名
	private String empImgName;
	private String cardImgName;
	private String disImgName;
	
	
	
	public List<DeptJob> getDeptJobList() {
		return deptJobList;
	}
	public void setDeptJobList(List<DeptJob> deptJobList) {
		this.deptJobList = deptJobList;
	}
	public String getEmpImgName() {
		return empImgName;
	}
	public void setEmpImgName(String empImgName) {
		this.empImgName = empImgName;
	}
	public String getCardImgName() {
		return cardImgName;
	}
	public void setCardImgName(String cardImgName) {
		this.cardImgName = cardImgName;
	}
	public String getDisImgName() {
		return disImgName;
	}
	public void setDisImgName(String disImgName) {
		this.disImgName = disImgName;
	}

	private int age;
	
    public int getAge() {
		return age;
	}
    //设置年龄
	public void setAge(String birthday) {
		if(birthday != null && birthday.length() > 4){
    		String birth = birthday.substring(0,4);
    		Date date = new Date();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    		String currentyear = sdf.format(date);
    		int age = Integer.parseInt(currentyear) - Integer.parseInt(birth);
    		this.age = age;
    	}
	}

	private Integer id;

    private String code;

    private String name;

    private String tel;

    private String sex;

    private String birthday;

    private String idcard;

  // @StringTimeFormat(pattern = "yyyy-MM-dd")
    private String time;

    private String province;

    private String city;

    private String deptTwo;

    private String deptOne;

    private String jobCode;

    private String remark;

    private byte[] disImg;

    private byte[] cardImg;

    private byte[] empImg;
    //用户密码
    private String password;
    
    public void setPassword(String password){
    	this.password = password;
    }
    public String getPassword(){
    	return password;
    }

    public byte[] getDisImg() {
        return disImg;
    }

    public void setDisImg(byte[] disImg) {
        this.disImg = disImg;
    }

    public byte[] getCardImg() {
        return cardImg;
    }

    public void setCardImg(byte[] cardImg) {
        this.cardImg = cardImg;
    }

    public byte[] getEmpImg() {
        return empImg;
    }

    public void setEmpImg(byte[] empImg) {
        this.empImg = empImg;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getTime() {
    	return time;
    }

    public void setTime(String time)  {
    		this.time  = time;
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

    public String getDeptTwo() {
        return deptTwo;
    }

    public void setDeptTwo(String deptTwo) {
        this.deptTwo = deptTwo == null ? null : deptTwo.trim();
    }

    public String getDeptOne() {
        return deptOne;
    }

    public void setDeptOne(String deptOne) {
        this.deptOne = deptOne == null ? null : deptOne.trim();
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode == null ? null : jobCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}