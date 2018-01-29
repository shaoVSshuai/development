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
	 * ������ţ�����ʱ�䣬֧��״̬������״̬���Ƿ�Ϊ���֧����Ӧ�ս�֧����ʽ1����ʽ1��֧����ʽ2��
	        ��ʽ2����������۵��̣�����Ա����Ա�ţ���Ʒ��ע��
	 * @param goods ����������Ʒ���ַ�����ʽ��1--1,2--1
	 * @return
	 */
	@Transactional
	public boolean insertOrder(HttpServletRequest request,String goods,Orders order,String discount,String totalPrice){
		List<Orderdetail> oList = new ArrayList<Orderdetail>();
		//���ɶ�����
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String prefixOrderCode =  sdf.format(date);
		User user = (User)request.getSession().getAttribute("user");
		String suffixOrderCode = "0000";//String.format("%04d", user.getId());
		String ordersCode = prefixOrderCode+suffixOrderCode;
		//����ʱ��
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderTime = sd.format(date);
		//֧��״̬
		String payState = "��֧��";
		//����״̬
		String orderType = "��ʳ";
		//�Ƿ�Ϊ���֧��
		order.setOrderCode(ordersCode);
		order.setOrdersTime(orderTime);
		order.setPayState(payState);
		order.setOrderType(orderType);
		//���ǰ̨����������null,����ۿ۱�Ϊ1
		if(discount == null){
			discount = "1";
		}
		//��ǰ̨���������ۿ�ת����double ���͵�
		Double lastDiscount = Double.parseDouble(discount);
		//��ǰ̨���������ܼ�ת����double ���͵�
		Double lasttotalPrice = Double.parseDouble(totalPrice);
		//��������ǰ������Ʒ�ļ۸񣨳������һ����
		Double temp = 0.0;
		//�����ж�ɽƷ�ǲ������һ��
		int i = 0;
		//����Ʒ��Ϣ��ֳ�����
		String[] g = goods.split(",");
		for(String s:g){
			i++;
			String s1 = "1--1--100.00,2--1--200.00---";
			Orderdetail orderdetail = new Orderdetail();
			String goodCode = s.substring(0,s.indexOf("--"));
			String number = s.substring(s.indexOf("--")+2,s.lastIndexOf("--"));
			String revenueMoney = s.substring(s.lastIndexOf("--")+2);
			//ת���ĸ�ʽ
			DecimalFormat df = new DecimalFormat("#.00");
			//�����������Ǯת����double���͵�
			Double lastRevenueMoney = Double.parseDouble(revenueMoney);
			String lastRevenueMoney1 = df.format(lastRevenueMoney*lastDiscount);
			//��������һ�����飬�����ܼ�-ǰ������Ʒ��Ǯ
			if(g.length == i){
				lastRevenueMoney = lasttotalPrice - temp;
			}
			//ǰ������Ʒ���ܼۣ��������һ����
			temp += lastRevenueMoney;
			
			orderdetail.setOrdersCode(ordersCode);
			orderdetail.setGoodCode(goodCode);
			orderdetail.setNumber(number);
			orderdetail.setRevenueMoney(lastRevenueMoney1);
		/*	orderdetail.setGoodSkind("");
			orderdetail.setRemark("");*/
			oList.add(orderdetail);
		}
		//���붩��
		ordersMapper.insertSelective(order);
		//��������
		System.out.println(oList.size()+"--");
		orderdetailMapper.insertOrderdetailList(oList);
		return false;
	}
}
