package com.hzyc.hzycsms.util;


public class T {
public static void main(String[] args) {
	System.out.println("redis�洢�ֶ�runoobkey:" + RedisPool.getClient().get("jsonString"));
}
}
