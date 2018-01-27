package com.hzyc.hzycpos.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hzyc.hzycpos.domain.Goods;
import com.hzyc.hzycpos.service.GoodsSer;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商品模块控制层
 * 
 * @author SHAOSHUAI
 *
 */
@Controller
public class GoodsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	GoodsSer gs;
	
	//request response对象
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	//封装商品
	private List<Goods> goodsList;
	/**
	 * 查询所有商品
	 * 
	 * @return
	 */
	/*public String selAllGoods(){
		//查询所有商品list
		this.goodsList = gs.getAllGoods();
		return SUCCESS;
	}
	*/
	
	//绑定商品list
	public List<Goods> getGoodsList(){
		return this.goodsList;
	}
	
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	
	

}
