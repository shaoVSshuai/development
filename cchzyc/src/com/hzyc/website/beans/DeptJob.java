package com.hzyc.website.beans;

import java.util.List;

public class DeptJob {
    private Integer id;

    private String jobCode;

    private String deptCode;
    
    private List<JobFirstSecondDept> deptList;
    
    private List<Job> jobList;
    
    public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public List<JobFirstSecondDept> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<JobFirstSecondDept> deptList) {
		this.deptList = deptList;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode == null ? null : jobCode.trim();
    }

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

   
}