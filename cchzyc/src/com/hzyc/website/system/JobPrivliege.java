package com.hzyc.website.system;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.hzyc.website.beans.Job;
import com.hzyc.website.beans.Privilege;

/**
 * 缓存所有的角色---权限
 * 
 * 用于角色访问权限的验证
 * 
 * @author SHAOSHUAI
 *
 */
public class JobPrivliege {
	
	private static List<Job> list = new ArrayList<Job>();
	
	private static List<Privilege> pList = new ArrayList<Privilege>();
	
	private JobPrivliege(){
		
	}
	//只允许一个实例
	public  static List<Job> getList(){
		if(list == null ){
			return   list = new ArrayList<Job>();
		}else{
			return list;
		}
	}
	
	
	public  static List<Privilege> getPri(){
		return pList;
	}
	
	public static void setList(List<Job> alist){
		list = alist;
		Gson g = new Gson();
		System.out.println(g.toJson(list));
	}
	
	public static void setPri(List<Privilege> alist){
		pList = alist;
	}
	
	public String toString(){
		Gson g = new Gson();
		return g.toJson(list);
	}
	
	/**
	 * 根据角色code查询是不是有该权限[]
	 * 	
	 * 		  数据库中没有这个权限，说明该权限不需要过滤，那么不过滤
	 * 	逻辑：数据库中有这个权限  ， 但是用户没有这个权限，说明用户没有这个权限[需要过滤]
	 * 
	 * */
	public static boolean selPriByJobCode(String jobCode,String path){
		//这个plist是封装一级和二级菜单的
		//List<Privilege> pList = new ArrayList<Privilege>();
		//是否可以访问
		boolean isAccess = false;
		//用户访问的路径需不需要过滤       默认不需要过滤
		boolean isnoControl = false;
		for(Privilege pri : pList){
			//所有权限
				//如果数据库中有这个权限 那么[这个权限须要过滤]
				isnoControl = pri.getmUrl().equals(path);
		}
		//这个权限要控制//这个权限须要控制
		if( isnoControl){
			for(Job job : list){
				String jobcode = job.getJobCode();
				//查询到了该角色编码
				if(jobcode.equals(jobcode)){
					//该角色所有拥有的权限
					List<Privilege> p2 = job.getpList();
					for(Privilege p : p2){
						//如果访问路径 该角色拥有  
						if(p.getmUrl().equals(path) ){
							isAccess = true;
						}
					}
				}
			}
		}else{
			//else说明什么呢：这个权限数据库中都没有  比如:index.jsp 就是不需要过滤
			isAccess = true;;
		}
		System.out.println(isAccess ? "可以访问" : "无法访问...");
		return isAccess;
	}
	
	
}
