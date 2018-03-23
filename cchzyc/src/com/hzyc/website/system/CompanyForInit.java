package com.hzyc.website.system;

import java.util.ArrayList;
import java.util.List;

import com.hzyc.website.beans.Company;

public class CompanyForInit {

	private static List<Company> companyList = new ArrayList<Company>();
	
	
	public static List<Company> getList(){
		return companyList;
	}
	public static void setList(List<Company> cList){
		companyList = cList;
	}
	
	
	
}
