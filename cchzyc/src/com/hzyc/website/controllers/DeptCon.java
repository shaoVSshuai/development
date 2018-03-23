package com.hzyc.website.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.hzyc.website.beans.Dept;
import com.hzyc.website.beans.Job;
import com.hzyc.website.services.DeptSer;

@Controller
@RequestMapping(value="deptCon")
public class DeptCon {
	
	@Autowired
	DeptSer ds;
	
	@RequestMapping("/selLevel1.hzyc")
	public void selLevel1(HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Gson j = new Gson();
			//查询一级部门
			List<Dept> list = ds.selLevel1();
			String dept = j.toJson(list);
			out.print(dept);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if( out != null ){
				out.close();
			}
		}
		
	}
	
	@RequestMapping("/selLevel2.hzyc")
	public void selLevel2(HttpServletResponse response,HttpServletRequest request){
		PrintWriter out = null;
		try {
			String code = request.getParameter("code");
			 out = response.getWriter();
			Gson j = new Gson();
			//查询一级部门
			List<Dept> list = ds.selLevel2(code);
			String dept = j.toJson(list);
			out.print(dept);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
		
	}
	
	@RequestMapping("/selJob.hzyc")
	public void selJob(HttpServletResponse response,HttpServletRequest request){
		PrintWriter out = null;
		try {
			String code = request.getParameter("code");
			 out = response.getWriter();
			Gson j = new Gson();
			//查询一级部门
			List<Job> list = ds.selJobByDept2(code);
			String dept = j.toJson(list);
			out.print(dept);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	
}
