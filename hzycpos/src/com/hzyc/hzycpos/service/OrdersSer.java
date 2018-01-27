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
	 * ������ţ�����ʱ�䣬֧��״̬������״̬���Ƿ�Ϊ���֧����Ӧ�ս�֧����ʽ1����ʽ1��֧����ʽ2��
	        ��ʽ2����������۵��̣�����Ա����Ա�ţ���Ʒ��ע��
	 * @param goods ����������Ʒ���ַ�����ʽ��1--1,2--1
	 * @return
	 */
	@Transactional
	public boolean insertOrder(HttpServletRequest request,String goods,Orders order){
		List<Orderdetail> oList = new ArrayList<Orderdetail>();
		//���ɶ�����
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String prefixOrderCode =  sdf.format(date);
		User user = (User)request.getSession().getAttribute("user");
		String suffixOrderCode = "0000";//String.format("%04d", user.getId());
		String ordersCode = prefixOrderCode+suffixOrderCode;
		//����ʱ��
		SimpleDateFormat sd = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
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
		
		String[] g = goods.split(",");
		for(String s:g){
			Orderdetail orderdetail = new Orderdetail();
			String goodCode = s.substring(0,s.indexOf("-"));
			String number = s.substring(s.lastIndexOf("-")+1);
			orderdetail.setOrdersCode(ordersCode);
			orderdetail.setGoodCode(goodCode);
			orderdetail.setNumber(number);
		}
		//���붩��
		ordersMapper.insertSelective(order);
		//��������
		orderdetailMapper.insertOrderdetailList(oList);
		return false;
	}
}
