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
	
	//��ѯһ���˵�
	public List<Privilege> selLevel1(){
		return pm.selLevel1();
	}
	//��ѯ2���˵�
	public List<Privilege> selLevel2(String id){
		return pm.selLevel2(id);
	}
	
	/**
	 * ͨ����ɫid��Ȩ��
	 * 
	 * @author ZHENGBIN
	 * */
	public List<Privilege> selectById(int rId){
		//ʵ����pList����װȨ����Ϣ
		List<Privilege> pList = new ArrayList<Privilege>();
		pList = pm.selectByRid(rId);
		return pList;
	}
	
	/**
	 * �����û���Ϣ����õ���ɫid
	 * 
	 * @author ZHENGBIN
	 * */
	public List<Privilege> sel(User user,HttpServletRequest request){
		int rId = Integer.parseInt(request.getSession().getAttribute("rId")+"");
		return selectById(rId);
	}
	
	/**
	 * ����URL ��ѯ�Լ��Ĳ˵��Ƿ������·��
	 * param url : ����·��
	 * @author ZHENGBIN
	 * */
	public boolean selMineUrl(String url,HttpServletRequest request){
		int rId = Integer.parseInt(request.getSession().getAttribute("rId")+"");
		List<Privilege> list = pm.selectByRid(rId);
		for(Privilege p : list){
			//�ȶԳɹ�
			if(p.getmUrl() != null){
				if(p.getmUrl().equals(url)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * ����URL ��ѯ���в˵��Ƿ������·��
	 * param url : ����·��
	 * */
	public boolean selMenuByUrl(String url){
		//����Ȩ��
		List<Privilege> list = pm.selAll();
		for(Privilege p : list){
			//�ȶԳɹ�
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
		//map���Ѿ��ź���
		for(Map.Entry<Integer, String> entry : map.entrySet()){
			//index
			int index = entry.getKey();
			//id
			String id = entry.getValue();
			
			Privilege p = new Privilege();
			//��������
			p.setId(Integer.parseInt(id));
			p.setSort(index);
			list.add(p);
		}
		try {
			//��ʼִ������
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
