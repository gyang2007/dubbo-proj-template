package com.gyang.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class DBPropertiesUtils {

    private static Logger logger = LoggerFactory.getLogger(DBPropertiesUtils.class);

    private static Properties p = new Properties();

    static{
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(DBPropertiesUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"), "UTF-8"));
            p.load(reader);
        }catch(Exception e){
            logger.error("load properties error:",e);
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("Close io exp!", e);
                }
            }
        }
    }

    public static String getProperties(String key){
        String value = p.getProperty(key, StringUtils.EMPTY);
        logger.info("config is : "+ key + " : " + value);
        return value;
    }

    public static String getProperties(String key, String defaultValue){
        String value = p.getProperty(key, defaultValue);
        logger.info("config is : "+ key + " : " + value);
        return value;
    }
}
