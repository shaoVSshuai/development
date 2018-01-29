package com.hzyc.hzycpos.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


import com.hzyc.hzycpos.action.UserAction;
import com.hzyc.hzycpos.domain.User;
import com.hzyc.hzycpos.service.GoodsKindSer;
import com.hzyc.hzycpos.service.PrivilegeSer;
import com.hzyc.hzycpos.service.UserSer;
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class Test4 {
	
	 @Resource(name="goodsKindSer")  
	  private GoodsKindSer testDao;  
	
	 @Resource(name="userSer")  
	  private UserSer us;  
	
	 @Resource(name="privilegeSer")  
	  private PrivilegeSer pd;  
	
	 @Resource(name="userAction")  
	  private UserAction ua;  
	
	 
	 
	 @Test
	 public void testAction() throws Exception{
		pd.selectById(3);
		// us.insertUser(u);
	 }

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAllGoods() {
		testDao.getAllGoods();
	}
	
	@Test
	public void selByRoleId(){
		//us.selectById(1);
	}
	
	@Test
	public void testG() {
		 
		 boolean b  = pd.selMenuByUrl("index.js");
		System.out.println(b);
	}
}
