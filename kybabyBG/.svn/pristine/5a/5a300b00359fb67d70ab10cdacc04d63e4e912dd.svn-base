package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Properties;

public class PropertiesHandle extends BaseAction {

	private String mes="";
	private String action="";
	private String key="";        //键值
	
	private String value="";       //数据配置的key
	
	private String status="";      //状态
	
	
	
	private Properties properties; //返回的某个数据
	
	private List propertiesList=new ArrayList();
	
	
	public String execute()
	{
		if(action.equals("all"))
		{
			System.out.println("propertiesHandle.action?action=all&key="+key+"..........");
			if(key.equals("pointsRule"))
			{
				properties=propertiesBo.getPropertiesByName("积分换现金");
				if(properties!=null)
				{
					propertiesList.add(properties);
					properties=null;
				}
				properties=propertiesBo.getPropertiesByName("积分抵现");
				if(properties!=null)
				{
					propertiesList.add(properties);
					properties=null;
				}
				properties=propertiesBo.getPropertiesByName("消费返积分");
				if(properties!=null)
				{
					propertiesList.add(properties);
					properties=null;
				}
				mes="成功";
				return "success";
				
			}
			else
			{
			properties=propertiesBo.getPropertiesByName(key);
			mes="成功";
			return "success";
			}
		}
		if(action.equals("update"))
		{
			System.out.println("propertiesHandle.action?action=update&key="+key+"..........");
			properties=propertiesBo.getPropertiesByName(key);
			properties.setValue(value);
			properties.setStatus(status);
			baseBo.updateProperties(properties);
			mes="成功";
			return "success";
		}
		
		return "success";
	}


	public String getMes() {
		return mes;
	}


	public Properties getProperties() {
		return properties;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public void setKey(String key) {
		this.key = key;
	}




	public List getPropertiesList() {
		return propertiesList;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public void setStatus(String status) {
		this.status = status;
	}



	
}
