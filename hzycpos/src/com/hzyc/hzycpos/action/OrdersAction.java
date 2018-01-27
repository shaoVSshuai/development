package com.hzyc.hzycpos.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hzyc.hzycpos.domain.Orderdetail;
import com.hzyc.hzycpos.domain.Orders;
import com.hzyc.hzycpos.service.OrdersSer;
import com.opensymphony.xwork2.ActionSupport;


@Controller
public class OrdersAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	@Autowired
	OrdersSer os;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Orders orders;
	private Orderdetail orderdetail;
	
	
	public void insertOrder(){
		String goods = request.getParameter("goods");
		System.out.println(orders.getPayWay1RevenueMoney()+"=========================");
		os.insertOrder(request,goods,orders);		
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpServletResponse getResponse() {
		return response;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	public Orders getOrders() {
		return orders;
	}


	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
