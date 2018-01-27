package com.hzyc.hzycpos.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hzyc.hzycpos.domain.Goods;
import com.hzyc.hzycpos.domain.GoodsKind;
import com.hzyc.hzycpos.service.GoodsKindSer;
import com.hzyc.hzycpos.service.GoodsSer;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class GoodsKindAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	GoodsKindSer gks;
	
	//request response����
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	//��װ��Ʒ
	private List<GoodsKind> goodsType;
	
	/**
	 * ��ѯ������Ʒ
	 * 
	 * @return
	 */
	public String selAllGoods(){
		//��ѯ������Ʒlist
		long a=System.currentTimeMillis();
		
		this.goodsType = gks.getAllGoods();
		System.out.println(goodsType.size()+"============");
		response.setHeader("Access-Control-Allow-Origin", "*");
		//����õ�һ�м���:
		System.out.println("\r<br>ִ�к�ʱ : "+(System.currentTimeMillis()-a)/1000f+" �� ");
		return SUCCESS;
	}
	
	
	//����Ʒlist
	public List<GoodsKind> getGoodsType(){
		return this.goodsType;
	}
	
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	
	

}
