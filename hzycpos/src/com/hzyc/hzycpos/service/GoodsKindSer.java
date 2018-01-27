package com.hzyc.hzycpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzyc.hzycpos.domain.Goods;
import com.hzyc.hzycpos.domain.GoodsKind;
import com.hzyc.hzycpos.mapper.GoodsKindMapper;
@Service
public class GoodsKindSer {
	
	@Autowired
	GoodsKindMapper gkm;
	
	/**
	 * 获取所有商品
	 * 
	 * @return
	 */
	public List<GoodsKind> getAllGoods(){
		//获取到的商品
		List<GoodsKind> list = gkm.selSmallKindGoods();
		if(list != null && list.size() > 0){
			return list;
		}else{
			return null;
		}
	}
}
