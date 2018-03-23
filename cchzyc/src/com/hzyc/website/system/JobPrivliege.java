package com.hzyc.website.system;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.hzyc.website.beans.Job;
import com.hzyc.website.beans.Privilege;

/**
 * �������еĽ�ɫ---Ȩ��
 * 
 * ���ڽ�ɫ����Ȩ�޵���֤
 * 
 * @author SHAOSHUAI
 *
 */
public class JobPrivliege {
	
	private static List<Job> list = new ArrayList<Job>();
	
	private static List<Privilege> pList = new ArrayList<Privilege>();
	
	private JobPrivliege(){
		
	}
	//ֻ����һ��ʵ��
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
	 * ���ݽ�ɫcode��ѯ�ǲ����и�Ȩ��[]
	 * 	
	 * 		  ���ݿ���û�����Ȩ�ޣ�˵����Ȩ�޲���Ҫ���ˣ���ô������
	 * 	�߼������ݿ��������Ȩ��  �� �����û�û�����Ȩ�ޣ�˵���û�û�����Ȩ��[��Ҫ����]
	 * 
	 * */
	public static boolean selPriByJobCode(String jobCode,String path){
		//���plist�Ƿ�װһ���Ͷ����˵���
		//List<Privilege> pList = new ArrayList<Privilege>();
		//�Ƿ���Է���
		boolean isAccess = false;
		//�û����ʵ�·���費��Ҫ����       Ĭ�ϲ���Ҫ����
		boolean isnoControl = false;
		for(Privilege pri : pList){
			//����Ȩ��
				//������ݿ��������Ȩ�� ��ô[���Ȩ����Ҫ����]
				isnoControl = pri.getmUrl().equals(path);
		}
		//���Ȩ��Ҫ����//���Ȩ����Ҫ����
		if( isnoControl){
			for(Job job : list){
				String jobcode = job.getJobCode();
				//��ѯ���˸ý�ɫ����
				if(jobcode.equals(jobcode)){
					//�ý�ɫ����ӵ�е�Ȩ��
					List<Privilege> p2 = job.getpList();
					for(Privilege p : p2){
						//�������·�� �ý�ɫӵ��  
						if(p.getmUrl().equals(path) ){
							isAccess = true;
						}
					}
				}
			}
		}else{
			//else˵��ʲô�أ����Ȩ�����ݿ��ж�û��  ����:index.jsp ���ǲ���Ҫ����
			isAccess = true;;
		}
		System.out.println(isAccess ? "���Է���" : "�޷�����...");
		return isAccess;
	}
	
	
}
