/*package com.hzyc.hzycpos.test;

import java.io.IOException;
import java.util.HashSet;

public class SocketSet {
	
	private static HashSet<WSServer> set = new HashSet<WSServer>();
	
	public static  void setSet(WSServer ws){
		set.add(ws);
		System.out.println("socket���ϵ�"+set.size());
			try {
				ws.sendMessage("��������");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	public static void remove(WSServer ws){
		set.remove(ws);
		System.out.println("socket���ϵ�"+set.size());
	}
}
*/