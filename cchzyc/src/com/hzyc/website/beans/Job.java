package com.hzyc.website.beans;

import java.util.List;

public class Job {
    private Integer id;

    private String jobCode;

    private String jobName;

    private String jobRemark;
    
    private List<Privilege> pList;

    private List<Employee> employeeList;
    
    public List<Privilege> getpList() {
		return pList;
	}

	public void setpList(List<Privilege> pList) {
		this.pList = pList;
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

    public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public void setJobCode(String jobCode) {
        this.jobCode = jobCode == null ? null : jobCode.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobRemark() {
        return jobRemark;
    }

    public void setJobRemark(String jobRemark) {
        this.jobRemark = jobRemark == null ? null : jobRemark.trim();
    }


	
}