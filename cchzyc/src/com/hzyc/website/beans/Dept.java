package com.hzyc.website.beans;

import java.util.List;

public class Dept {
    private Integer id;

    private String deptCode;

    private String deptName;

    private String deptLevel;

    private String deptRemark;
    
    //封装二级部门
    private List<Dept> deptList;
    private List<Job> jobList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel == null ? null : deptLevel.trim();
    }

    public String getDeptRemark() {
        return deptRemark;
    }

    public void setDeptRemark(String deptRemark) {
        this.deptRemark = deptRemark == null ? null : deptRemark.trim();
    }

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public List<Job> getJobList() {
		return jobList;
	}
}