package com.wx;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConfigManage extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5179862059368466962L;
	private String mes;
	private String action;
    private String ip="";
    private String propertyName = "";
    private String propertyValue = "";
	

	
	@Override
	public String execute(){
		System.out.println("*************config manage*****************");
		if(action.equals("getRemoteIp")){
			
			InetAddress addr;
			try {
				addr = InetAddress.getLocalHost();
				ip=addr.getHostAddress().toString();//获得本机IP
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    return "success";
		} else if(action.equals("getProperty")){
			propertyValue = wxBo.findPropertyValueByKey(propertyName);
			return "success";
		} else if(action.equals("getMchId")){
		}
		return "success";
		
	}
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	
}
