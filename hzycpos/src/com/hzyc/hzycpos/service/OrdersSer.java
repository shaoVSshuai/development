package com.hzyc.hzycpos.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hzyc.hzycpos.domain.Orders;
import com.hzyc.hzycpos.domain.Orderdetail;
import com.hzyc.hzycpos.domain.User;
import com.hzyc.hzycpos.mapper.OrderdetailMapper;
import com.hzyc.hzycpos.mapper.OrdersMapper;

@Service
public class OrdersSer {
	
	@Autowired
	OrdersMapper ordersMapper;
	
	@Autowired
	OrderdetailMapper orderdetailMapper;
	
	/**
	 * 订单编号，订单时间，支付状态，订单状态，是否为组合支付，应收金额，支付方式1，方式1金额，支付方式2，
	        方式2金额，找零金额，销售店铺，收银员，会员号，产品备注，
	 * @param goods 包含所有商品的字符，格式是1--1,2--1
	 * @return
	 */
	@Transactional
	public boolean insertOrder(HttpServletRequest request,String goods,Orders order){
		List<Orderdetail> oList = new ArrayList<Orderdetail>();
		//生成订单号
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String prefixOrderCode =  sdf.format(date);
		User user = (User)request.getSession().getAttribute("user");
		String suffixOrderCode = "0000";//String.format("%04d", user.getId());
		String ordersCode = prefixOrderCode+suffixOrderCode;
		//订单时间
		SimpleDateFormat sd = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		String orderTime = sd.format(date);
		//支付状态
		String payState = "已支付";
		//订单状态
		String orderType = "堂食";
		//是否为组合支付
		order.setOrderCode(ordersCode);
		order.setOrdersTime(orderTime);
		order.setPayState(payState);
		order.setOrderType(orderType);
		
		String[] g = goods.split(",");
		for(String s:g){
			Orderdetail orderdetail = new Orderdetail();
			String goodCode = s.substring(0,s.indexOf("-"));
			String number = s.substring(s.lastIndexOf("-")+1);
			orderdetail.setOrdersCode(ordersCode);
			orderdetail.setGoodCode(goodCode);
			orderdetail.setNumber(number);
		}
		//插入订单
		ordersMapper.insertSelective(order);
		//插入详情
		orderdetailMapper.insertOrderdetailList(oList);
		return false;
	}
}
