package com.hzyc.hzycsms.util;


public class T {
public static void main(String[] args) {
	System.out.println("redis´æ´¢×Ö¶Îrunoobkey:" + RedisPool.getClient().get("jsonString"));
}
}
