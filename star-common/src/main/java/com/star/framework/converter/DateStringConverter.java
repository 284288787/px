package com.star.framework.converter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateStringConverter implements Converter<Date, String> {

    public String convert(Date source) {
        if (source == null){
            return "";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf1.format(source);
    }

}
