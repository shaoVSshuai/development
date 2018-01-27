/*package com.hzyc.hzycpos.test;

import java.io.IOException;
import java.util.HashSet;

public class SocketSet {
	
	private static HashSet<WSServer> set = new HashSet<WSServer>();
	
	public static  void setSet(WSServer ws){
		set.add(ws);
		System.out.println("socket集合的"+set.size());
			try {
				ws.sendMessage("你下线了");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	public static void remove(WSServer ws){
		set.remove(ws);
		System.out.println("socket集合的"+set.size());
	}
}
*/