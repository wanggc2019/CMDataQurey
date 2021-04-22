package com.wgc.CMDataQurey.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil {
  private static String cdhQueriesPropertyPath = "./conf/cdh_queries.properties";
  
  private static String commPropertyPath = "./conf/property.properties";
  
  private static Properties cdhQueriesProperty = new Properties();
  
  private static Properties commProperty = new Properties();
  
  private static HashMap cdhQueriesMap;
  
  static {
    InputStream input_cdhQueries = null;
    InputStream input_comm = null;
    BufferedReader br_cdhQueries = null;
    BufferedReader br_comm = null;
    input_cdhQueries = PropertyUtil.class.getClassLoader().getResourceAsStream(cdhQueriesPropertyPath);
    input_comm = PropertyUtil.class.getClassLoader().getResourceAsStream(commPropertyPath);
    try {
      br_cdhQueries = new BufferedReader(new InputStreamReader(input_cdhQueries, "utf-8"));
      br_comm = new BufferedReader(new InputStreamReader(input_comm, "utf-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } 
    try {
      if (br_cdhQueries != null) {
        cdhQueriesProperty.load(br_cdhQueries);
        cdhQueriesMap = new HashMap(cdhQueriesProperty);
      } 
      if (br_comm != null)
        commProperty.load(br_comm); 
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static String getProperty(String key, String defaultValue) {
    String s = null;
    s = commProperty.getProperty(key, defaultValue);
    return s;
  }
  
  public static String getProperty(String key) {
    String s = null;
    s = commProperty.getProperty(key);
    return s;
  }
  
  public static Map getCDHQueries() {
    return cdhQueriesMap;
  }
  
  private static String getCDHQueriesProperty(String key, String defaultValue) {
    String s = null;
    s = cdhQueriesProperty.getProperty(key, defaultValue);
    return s;
  }
  
  private static String getCDHQueriesProperty(String key) {
    String s = null;
    s = cdhQueriesProperty.getProperty(key);
    return s;
  }
}
