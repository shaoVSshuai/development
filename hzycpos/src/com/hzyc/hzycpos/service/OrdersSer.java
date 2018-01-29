package com.hzyc.hzycpos.service;

import java.text.DecimalFormat;
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
	public boolean insertOrder(HttpServletRequest request,String goods,Orders order,String discount,String totalPrice){
		List<Orderdetail> oList = new ArrayList<Orderdetail>();
		//生成订单号
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String prefixOrderCode =  sdf.format(date);
		User user = (User)request.getSession().getAttribute("user");
		String suffixOrderCode = "0000";//String.format("%04d", user.getId());
		String ordersCode = prefixOrderCode+suffixOrderCode;
		//订单时间
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
		//如果前台传过来的是null,则把折扣变为1
		if(discount == null){
			discount = "1";
		}
		//把前台传过来的折扣转换成double 类型的
		Double lastDiscount = Double.parseDouble(discount);
		//把前台传过来的总价转换成double 类型的
		Double lasttotalPrice = Double.parseDouble(totalPrice);
		//用来计算前几个商品的价格（除了最后一个）
		Double temp = 0.0;
		//用来判断山品是不是最后一个
		int i = 0;
		//把商品信息拆分成数组
		String[] g = goods.split(",");
		for(String s:g){
			i++;
			String s1 = "1--1--100.00,2--1--200.00---";
			Orderdetail orderdetail = new Orderdetail();
			String goodCode = s.substring(0,s.indexOf("--"));
			String number = s.substring(s.indexOf("--")+2,s.lastIndexOf("--"));
			String revenueMoney = s.substring(s.lastIndexOf("--")+2);
			//转换的格式
			DecimalFormat df = new DecimalFormat("#.00");
			//把详情里面的钱转换成double类型的
			Double lastRevenueMoney = Double.parseDouble(revenueMoney);
			String lastRevenueMoney1 = df.format(lastRevenueMoney*lastDiscount);
			//如果是最后一个详情，则用总价-前几个商品的钱
			if(g.length == i){
				lastRevenueMoney = lasttotalPrice - temp;
			}
			//前几个商品的总价（除了最后一个）
			temp += lastRevenueMoney;
			
			orderdetail.setOrdersCode(ordersCode);
			orderdetail.setGoodCode(goodCode);
			orderdetail.setNumber(number);
			orderdetail.setRevenueMoney(lastRevenueMoney1);
		/*	orderdetail.setGoodSkind("");
			orderdetail.setRemark("");*/
			oList.add(orderdetail);
		}
		//插入订单
		ordersMapper.insertSelective(order);
		//插入详情
		System.out.println(oList.size()+"--");
		orderdetailMapper.insertOrderdetailList(oList);
		return false;
	}
}
