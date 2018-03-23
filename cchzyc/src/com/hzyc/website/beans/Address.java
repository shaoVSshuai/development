package com.hzyc.website.beans;

import java.util.List;

public class Address {

	private String code ;
	
	private String name ; 
	
	@Override
	public String toString() {
		return "Address [code=" + code + ", name=" + name + ", pCode=" + pCode
				+ ", aList=" + aList + "]";
	}

	private String pCode ;

	private List<Address> aList ;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Address> getaList() {
		return aList;
	}

	public void setaList(List<Address> aList) {
		this.aList = aList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	
	
	
}
