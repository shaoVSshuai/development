package com.hzyc.website.utils;

import redis.clients.jedis.Jedis;

public class RedisPool {
	// redis ����
	private static Jedis jedis = null;
	private static final String IP = "127.0.0.1";
	private static final int PROT = 6379;
	/**
	* ����ģʽ����redis����
	* 
	* @return
	*/
	public static synchronized Jedis getClient() {
		if (jedis == null) {
			jedis = new Jedis(IP, PROT,10000);
		}
		return jedis;
	}
}