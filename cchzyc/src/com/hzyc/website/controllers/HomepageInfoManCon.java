package com.hzyc.website.controllers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hzyc.website.beans.Course;
import com.hzyc.website.services.HomepageSer;
import com.hzyc.website.utils.RedisPool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("HomepageInfoManCon")
public class HomepageInfoManCon {

	@Autowired
	HomepageSer hs;
	
	/**
	 * @return
	 * ²éÑ¯¿Î³Ì
	 */
	@RequestMapping("/selCourse.hzyc")
	public List<Course> selCourse(){
		List<Course> list = hs.selCourse();
		String jsonString = JSONArray.fromObject(list).toString();
		System.out.println(jsonString+"-------------------");
		RedisPool.getClient().set("jsonString", jsonString);
		System.out.println("redis´æ´¢×Ö¶Îrunoobkey:" + RedisPool.getClient().get("jsonString"));
		return list;
	}
}
