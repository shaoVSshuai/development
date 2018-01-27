package com.hzyc.hzycpos.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.hzycpos.domain.Config;
import com.hzyc.hzycpos.domain.Vip;
import com.hzyc.hzycpos.domain.VipKind;
import com.hzyc.hzycpos.mapper.ConfigMapper;
import com.hzyc.hzycpos.mapper.VipKindMapper;
import com.hzyc.hzycpos.mapper.VipMapper;

/**
 * ��vip���д���
 * 
 * @author ZHENGBIN
 *
 */
@Service
public class VipSer {
	
	@Autowired
	private ConfigMapper cm;
	@Autowired
	private VipKindMapper vkm;
	@Autowired
	private VipMapper vm;
	
	/**
	 * ��ѯ��ʲô���͵Ļ�Ա
	 * @return 0 Ϊ���ۻ�Ա 1 Ϊ����&��ֵ��Ա
	 * 
	 * @author ZHENGBIN
	 *
	 */
	public String selType(){
		String result = "1";
		Config c = cm.selByKey("vip_type");
		
		//���û�������ݿ����
		if (c == null) {
			result = "2";
		} else {
			//41Ϊ���� 42Ϊ��ֵ������
			if (c.getValueDict() == 41) {
				result = "0";
			} else {
			}
		}
		return result;
	}
	
	public boolean addVip(Vip vip){
		boolean flag = true;
		int hyKindId;
		List<VipKind> vkList;
		
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		SimpleDateFormat sdfHyDate=new SimpleDateFormat("yyyy-MM-dd");
		
		//��Ա��
		String hyCode = sdf.format(date);
		vip.setHyCode(hyCode);
		
		//��ȡ��ֵ���
		double hyTotal = vip.getHyTotal();
		System.out.println("------------1");
		//�ж���ɶ���͵Ļ�Ա
		String result = selType();
		
		if (hyTotal >= 0 && result == "1"){
			//������֤���Ǵ��۲���ֵ��Ա
			System.out.println("------------3");
			//���
			vip.setHyRemainMoney(hyTotal);
			System.out.println("------------4");
			//��ѯС�ڵ��ڳ�ֵ�������л�Ա����
			vkList = vkm.selByNeedMoney(hyTotal);
			System.out.println("------------2");
			if (vkList.size() != 0 && vkList != null) {
				hyKindId = vkList.get(0).getId();
				vip.setHyKindId(hyKindId);
			} else {
				//û��Ĭ��ֵ
				flag = false;
			}
		} else if (hyTotal == 0 && result == "0") {
			//������֤���Ǵ��ۻ�Ա
			
			//�����ѯ���л�Ա����
			vkList = vkm.selAllASC();
			if (vkList.size() != 0 && vkList != null) {
				hyKindId = vkList.get(0).getId();
				vip.setHyKindId(hyKindId);
			} else {
				//û��Ĭ��ֵ
				flag = false;
			}
		} else {
			//�������
			flag = false;
		}
		
		String hyCard = vip.getHyCard();
		
		String birthday = null;
		if (hyCard != null) {
			if (hyCard.length() == 18) { 
	            birthday = hyCard.substring(6, 10) + "-" 
	                    + hyCard.substring(10, 12) + "-" 
	                    + hyCard.substring(12, 14); 
	        } else if (hyCard.length() == 15) { 
	            birthday = hyCard.substring(6, 8) + "-" 
	                    + hyCard.substring(8, 10) + "-" 
	                    + hyCard.substring(10, 12); 
	        } 
		}
		vip.setHyBirthday(birthday);
		
		String hyDate = sdfHyDate.format(date);
		vip.setHyDate(hyDate);
		
		//���ϲ���ʧ�ܾ�û��Ҫ��ӵ����ݿ���
		if (flag) {
			System.out.println("----------");
			int rs = vm.insertSelective(vip);
			System.out.println("123123");
			//���ʧ��
			if (rs <= 0) {
				flag = false;
			}
		}
		
		return flag;
	}
}
