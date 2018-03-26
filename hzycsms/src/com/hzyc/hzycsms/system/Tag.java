package com.hzyc.hzycsms.system;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.hzyc.hzycsms.bean.Dictionary;

/**
 * �Զ���select��ǩ
 * �����ֵ���
 * 
 * @author ��˧
 *
 */
public class Tag extends SimpleTagSupport {
	private String id;
	private String layFilter;
	
	public void setLayFilter(String layfilter){
		this.layFilter = layfilter;
	}
	public void setId(String id){
		this.id = id;
	}
	//�ֵ�����
	private String type;
	//��ʽclass
	private String classname;
	//name���ڲ�����
	private String name;
	//�Ƿ��������
	private String plus;
	//js�¼�
	private String event;
	//selectĬ��ֵ
	private String defaultvalue;
	//�ֵ����ת��code
	private String code;
	
	public void setCode(String code){
		this.code = code;
	}
	
	public void setDefaultvalue(String defaultvalue){
		this.defaultvalue = defaultvalue;
	}
	public void setEvent(String event){
		this.event = event;
	}
	
	public void setPlus(String plus){
		this.plus = plus;
	}
	public void setType(String type){
		this.type = type;
	}
	public void setClassname(String classname){
		this.classname = classname;
	}
	public void setName(String name){
		this.name = name;
	}
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspWriter out = getJspContext().getOut();
		StringBuilder str = new StringBuilder("<select lay-filter='"+layFilter+"' name='"+name+"' class='"+classname+"' id='"+id+"'");
		//�Ƿ���jsʱ��
		if(event != null && event.indexOf("-") >= 0){
			String[] a = event.split("-");;
			String result = a[0]+"="+ "\""+a[1]+"\"";
			str.append(result);
		}
		str.append(" >");
		if(defaultvalue!=null){
			//valueĬ��ֵ
			str.append("<option value='"+defaultvalue+"' >"+defaultvalue+"</option>");
		}
		//������ֵ��������Ҫת��
		if(code != null){
			str.append("<option value='"+ code +"' >"+Dict.getDictNameByCode(code)+"</option>");
		}
		Dictionary[] dicts = Dict.getDictByType(type);
		if(dicts != null && dicts.length > 0 ){
			for(Dictionary dict : dicts){
				str.append("<option value='"+dict.getDictCode()+"'>"+dict.getDictName()+"</option>");
			}
		}else{
			str.append("<option value='"+0+"'>��ѡ��</option>");
		}
		if(plus!= null && plus.equals("y")){
			str.append("<option value='n'>����</option>");
		}
	    str.append("</select>");
	    out.print(str);
	    out.flush();
	}
	
}
