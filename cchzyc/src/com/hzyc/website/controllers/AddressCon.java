package com.hzyc.website.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.hzyc.website.services.InitService;



@Controller
@RequestMapping("/AddressUtil")
public class AddressCon {

	
	@Autowired
	private InitService initSer ;
	
	@RequestMapping("/getAddress.hzyc")
	public void getAddress(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		LinkedHashMap<String, HashMap<String, String>> aList  = new LinkedHashMap<String, HashMap<String, String>>();
		
		String targetCode = request.getParameter("targetCode");
		System.out.println((targetCode));
		if(targetCode.length() == 2){
			
			targetCode += "0000";
			aList = initSer.getAddMap();
			
		}else{

			aList = initSer.getCountyMap();
		}
		
		Gson gson = new Gson();
		HashMap<String, String> cityList = aList.get(targetCode);
		String gsonStr = gson.toJson(cityList);
		PrintWriter out = response.getWriter();
		response.setHeader("Context-type", "text/html;charset=utf-8");
		out.print(gsonStr);
		out.flush();
		out.close ();
		
	}
	
	
}
