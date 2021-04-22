package com.wgc.CMDataQurey.util;

import com.cloudera.api.ClouderaManagerClientBuilder;
import com.cloudera.api.model.ApiTimeSeries;
import com.cloudera.api.model.ApiTimeSeriesData;
import com.cloudera.api.model.ApiTimeSeriesResponse;
import com.cloudera.api.model.ApiTimeSeriesResponseList;
import com.cloudera.api.v4.TimeSeriesResource;
import com.cloudera.api.v6.TimeSeriesResourceV6;
import com.cloudera.api.v9.RootResourceV9;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CDHClient {
  private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'+08:00'");
  
  public List<ApiTimeSeriesData> getTimeSeriesDatas(String ip, String user, String pass, String query, String from, String to) {
    TimeSeriesResourceV6 res = getTimeSeriesResourceV6(ip, user, pass);
    if (res == null)
      return null; 
    ApiTimeSeriesResponseList response = res.queryTimeSeries(query, from, to);
    List<ApiTimeSeriesData> datas = null;
    for (ApiTimeSeriesResponse resp : response.getResponses()) {
      List<ApiTimeSeries> series = resp.getTimeSeries();
      for (ApiTimeSeries serie : series)
        datas = serie.getData(); 
    } 
    return datas;
  }
  
  public TimeSeriesResource getCDHTimeSeriesResource(String ip, String user, String pass) {
    TimeSeriesResourceV6 res = getTimeSeriesResourceV6(ip, user, pass);
    return (TimeSeriesResource)res;
  }
  
  public List<ApiTimeSeriesData> queryTimeSeries(TimeSeriesResource res, String query, Date from, Date to) {
    ApiTimeSeriesResponseList response = res.queryTimeSeries(query, (from == null) ? null : dateFormat.format(from), (to == null) ? null : dateFormat.format(to));
    List<ApiTimeSeriesData> datas = new ArrayList<>();
    for (ApiTimeSeriesResponse resp : response.getResponses()) {
      List<ApiTimeSeries> series = resp.getTimeSeries();
      for (ApiTimeSeries serie : series)
        datas = serie.getData(); 
    } 
    return datas;
  }
  
  private TimeSeriesResourceV6 getTimeSeriesResourceV6(String ip, String user, String pass) {
    RootResourceV9 apiRoot = getRootResourceV9(ip, user, pass);
    return apiRoot.getTimeSeriesResource();
  }
  
  private RootResourceV9 getRootResourceV9(String ip, String user, String pass) {
    RootResourceV9 apiRoot = (new ClouderaManagerClientBuilder()).withHost(ip).withUsernamePassword(user, pass).build().getRootV9();
    return apiRoot;
  }
}
