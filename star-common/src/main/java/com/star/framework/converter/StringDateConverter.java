package com.star.framework.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


public class StringDateConverter implements Converter<String, Date> {

    public Date convert(String source) {
        if (source == null){
            return null;
        }
        String trim = source.trim();
        if (trim.length() == 0){
            return null;
        }
        try {
        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			return source.contains(":") ? sdf1.parse(trim) : sdf2.parse(trim);
		} catch (ParseException e) {
			return null ;
		}
        
    }

}
