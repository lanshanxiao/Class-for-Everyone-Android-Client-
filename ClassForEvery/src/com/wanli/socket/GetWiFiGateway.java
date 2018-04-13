package com.wanli.socket;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;

public class GetWiFiGateway {
	
	public GetWiFiGateway(Context context) {
		this.context = context;
	}
	
	private Context context;
	private WifiManager wifiManager;
	private DhcpInfo dhcpInfo;
	
	public String getGateway() {
		
		String gateway;
		wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
		dhcpInfo = wifiManager.getDhcpInfo();  
		long getewayIpL = dhcpInfo.gateway;  
		gateway = long2ip(getewayIpL);//Íø¹ØµØÖ· 
		
		
		return gateway;
	}
	
	String long2ip(long ip){  
	    StringBuffer sb=new StringBuffer();  
	    sb.append(String.valueOf((int)(ip&0xff)));  
	    sb.append('.');  
	    sb.append(String.valueOf((int)((ip>>8)&0xff)));  
	    sb.append('.');  
	    sb.append(String.valueOf((int)((ip>>16)&0xff)));  
	    sb.append('.');  
	    sb.append(String.valueOf((int)((ip>>24)&0xff)));  
	    return sb.toString();  
	}  
}
