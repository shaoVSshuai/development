package com.hzyc.hzycpos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.hzycpos.domain.Privilege;
import com.hzyc.hzycpos.domain.User;
import com.hzyc.hzycpos.mapper.PrivilegeMapper;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;

@Service
public class PrivilegeSer {
	
	
	
	@Autowired
	PrivilegeMapper pm;
	
	//查询一级菜单
	public List<Privilege> selLevel1(){
		return pm.selLevel1();
	}
	//查询2级菜单
	public List<Privilege> selLevel2(String id){
		return pm.selLevel2(id);
	}
	
	/**
	 * 通过角色id查权限
	 * 
	 * @author ZHENGBIN
	 * */
	public List<Privilege> selectById(int rId){
		//实例化pList用来装权限信息
		List<Privilege> pList = new ArrayList<Privilege>();
		pList = pm.selectByRid(rId);
		return pList;
	}
	
	/**
	 * 根据用户信息处理得到角色id
	 * 
	 * @author ZHENGBIN
	 * */
	public List<Privilege> sel(User user,HttpServletRequest request){
		int rId = Integer.parseInt(request.getSession().getAttribute("rId")+"");
		return selectById(rId);
	}
	
	/**
	 * 根据URL 查询自己的菜单是否包含此路径
	 * param url : 访问路径
	 * @author ZHENGBIN
	 * */
	public boolean selMineUrl(String url,HttpServletRequest request){
		int rId = Integer.parseInt(request.getSession().getAttribute("rId")+"");
		List<Privilege> list = pm.selectByRid(rId);
		for(Privilege p : list){
			//比对成功
			if(p.getmUrl() != null){
				if(p.getmUrl().equals(url)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 根据URL 查询所有菜单是否包含此路径
	 * param url : 访问路径
	 * */
	public boolean selMenuByUrl(String url){
		//所有权限
		List<Privilege> list = pm.selAll();
		for(Privilege p : list){
			//比对成功
			if(p.getmUrl() != null){
				if(p.getmUrl().equals(url)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @return
	 */
	public boolean sort(Map<Integer,String> map){
		List<Privilege> list = new ArrayList<Privilege>();
		//map是已经排好了
		for(Map.Entry<Integer, String> entry : map.entrySet()){
			//index
			int index = entry.getKey();
			//id
			String id = entry.getValue();
			
			Privilege p = new Privilege();
			//设置属性
			p.setId(Integer.parseInt(id));
			p.setSort(index);
			list.add(p);
		}
		try {
			//开始执行排序
			pm.updateSort(list);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			map = null;
		}
	}
}
