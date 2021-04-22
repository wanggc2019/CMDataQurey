package com.wgc.CMDataQurey.util;

public class CDHQuerySql {
  public static final String CM_TSQUERY_CLUSTER_NETWORK_IO_TRANSIMIT = "SELECT stats(bytes_transmit_rate_across_network_interfaces, total) WHERE category = CLUSTER";
  
  public static final String CM_TSQUERY_CLUSTER_NETWORK_IO_RECEIVE = "SELECT stats(bytes_receive_rate_across_network_interfaces, total) WHERE category = CLUSTER";
}
