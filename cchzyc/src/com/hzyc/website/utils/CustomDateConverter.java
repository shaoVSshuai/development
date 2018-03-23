package com.hzyc.website.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;



//自定义日期转换器
public class CustomDateConverter implements Converter<String, Date> {

    public Date convert(String source) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}