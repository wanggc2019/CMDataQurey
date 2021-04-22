package com.wgc.CMDataQurey.service;

import com.cloudera.api.model.ApiTimeSeriesData;
import com.cloudera.api.v4.TimeSeriesResource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wgc.CMDataQurey.util.CDHClient;
import com.wgc.CMDataQurey.util.PropertyUtil;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

public class CDHDataQueryService {
  private static DecimalFormat dformat = new DecimalFormat("#0.00");
  
  private static Gson gson = (new GsonBuilder()).serializeNulls().create();
  
  private String cdh_ip = PropertyUtil.getProperty("cdh_ip");
  
  private String cdh_user = PropertyUtil.getProperty("cdh_user");
  
  private String cdh_pass = PropertyUtil.getProperty("cdh_pass");
  
  private CDHClient cdh = new CDHClient();
  
  private TimeSeriesResource tsr = this.cdh.getCDHTimeSeriesResource(this.cdh_ip, this.cdh_user, this.cdh_pass);
  
  public List<ApiTimeSeriesData> queryCDHMetric(String query, Date from, Date to) {
    TimeSeriesResource tsr = getCDHTimeSeriesResource(this.cdh_ip, this.cdh_user, this.cdh_pass);
    List<ApiTimeSeriesData> datas = this.cdh.queryTimeSeries(tsr, query, from, to);
    return datas;
  }
  
  public TimeSeriesResource getCDHTimeSeriesResource(String ip, String user, String pass) {
    return this.cdh.getCDHTimeSeriesResource(this.cdh_ip, this.cdh_user, this.cdh_pass);
  }
}
