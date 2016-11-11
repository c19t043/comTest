package com.kybaby.newbussiness.spservice.util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.kybaby.newbussiness.spservice.exception.SpServiceException;
import com.kybaby.util.LogUtil;

public class SpInterfaceUtil {
	
	public final static String ERROR = "0";
	public final static String SUCCESS = "1";
	
	
	/**   操作是否成功  */
	public static boolean getRetCode(String xmlStr){
		Document doc = parseText2Doc(xmlStr);
		String retCode = doc.selectSingleNode("//Response/ReCode").getText();
		if(ERROR.equals(retCode)){
			return false; 
		}else{
			return true;
		}
	}
	/**   操作是否成功  */
	public static boolean getRetErrorCode(Document doc){
		String retCode = doc.selectSingleNode("//Response/ReCode").getText();
		if(ERROR.equals(retCode)){
			return true; 
		}else{
			return false;
		}
	}
	/**   获取错误信息   */
	public static String getRetErrorMsg(Document doc){
		return doc.selectSingleNode("//Response/ErrMsg").getText();
	}
//--------------------------------------------------------------------------------------
	
	/**  格式化打印XML  */
	private static void formatPrintDocument(Document doc){
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer;
		try {
			writer = new XMLWriter(System.out, format );
			writer.write(doc);
		} catch (IOException e) {
			LogUtil.error("格式化打印document对象为xml字符串错误", e);
		}
	}
	
	/**  解析xml字符串为document对象  */
	public static Document parseText2Doc(String xmlStr){
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			LogUtil.error("解析XML字符串串错误:错误xml字符串为"+xmlStr, e);
		}
		return doc;
	}
	
	/**  将response下的子元素转换Bean对象  */
	@SuppressWarnings("unchecked")
	private static Object convertDoc2JavaBean(Document doc,Class<?> clazz){
		Element reqBeanDatas_Res = getReqBeanDatas_Res(doc);
		Object obj_res= convert2JavaBean(reqBeanDatas_Res.elements(), clazz);
		return obj_res;
	}
	/**  将resultList下的元素的转换为BeanList对象  */
	@SuppressWarnings("unchecked")
	private static List<Object> convertDoc2BeanList(Document doc,Class<?> clazz){
		List<Element> reqBeanDatas_resultList = getReqBeanDatas_resultList(doc);

		List<Object> retList = new ArrayList<Object>();
		for(Element element : reqBeanDatas_resultList){
			if(element==null) continue;
			List<Element> childEle = element.elements();
			Object obj = convert2JavaBean(childEle,clazz);
			if(isNotEmpty(obj)){
				retList.add(obj);
			}
		}
		return retList;
	}
	public static Object convert2JavaBean(List<Element> elements,Class<?> clazz){
		Object obj = null;
		try {
			obj = clazz.newInstance();
		} catch (Exception e) {
			LogUtil.error(clazz.getName()+"实例化失败", e);
		}
		for (Element element : elements) {
			Element2Bean(element, obj);
		}
		return obj;
	}
	private static void Element2Bean(Element element,Object obj){
		setPropertyByBeanUtil(element,obj);
	}
	private static void setPropertyByBeanUtil(Element element,Object obj){
		String elementName = element.getName();
		String firstChar = elementName.substring(0, 1);
		elementName=elementName.replaceFirst(firstChar, firstChar.toLowerCase());
		try {
			BeanUtils.setProperty(obj, elementName, element.getTextTrim());
		} catch (Exception e) {
			LogUtil.error(obj.getClass().getName()+"类"+elementName+"字段赋值失败", e);
			System.out.println(e);
		}
	}
	@SuppressWarnings("unchecked")
	public static List<Element> getReqBeanDatas_resultList(Document doc){
		return doc.selectNodes("//Response/ResulList");
	}
	public static Element getReqBeanDatas_Res(Document doc){
		return (Element) doc.selectSingleNode("//Response");
	}
	
	/**
	 * 将response元素下子元素转换为JavaBean_List  
	 * @param xmlStr
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> xml2JavaBean(String xmlStr,Class<T> clazz)throws SpServiceException{
		
		Document doc = parseText2Doc(xmlStr);
		
		//formatPrintDocument(doc);

		boolean opFail = getRetErrorCode(doc);
		if(opFail)  throw new SpServiceException(getRetErrorMsg(doc));
		
		List<Object> retListBean = new ArrayList<Object>();

		Object bean = convertDoc2JavaBean(doc, clazz);
		
		List<Object> listBean = convertDoc2BeanList(doc, clazz);
		
		if(!listBean.isEmpty()){
			retListBean = setPropertyValueWithList(listBean, bean);
		}else{
			retListBean.add(bean);
		}
		
		return (List<T>) retListBean;
	}
	/**
	 * 将Document对象转换为对相应对象集合  
	 * @param doc
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> xml2JavaBean(Document doc,Class<T> clazz){
		List<Object> retListBean = new ArrayList<Object>();

		Object bean = convertDoc2JavaBean(doc, clazz);
		
		List<Object> listBean = convertDoc2BeanList(doc, clazz);
		
		if(listBean.isEmpty()){
			retListBean.add(bean);
		}else{
			retListBean = setPropertyValueWithList(listBean, bean);
		}
		
		return (List<T>) retListBean;
	}
	/**
	 * 将bean中的值赋值到listBean的对象中(list中的bean和bean是同一类型)
	 * @param listBean  
	 * @param bean
	 * @return
	 */
	private static List<Object> setPropertyValueWithList(List<Object> listBean,Object bean){
		Class<?> clazz = bean.getClass();
		Field[] declaredFields = clazz.getDeclaredFields();
		try {
			for (Object obj : listBean) {
				for (Field field : declaredFields) {
					String name = field.getName();
					PropertyDescriptor pd = new PropertyDescriptor(name,clazz);
					Method readMethod = pd.getReadMethod();
					Object retVal = readMethod.invoke(bean, null);
					if(retVal!=null){
						pd.getWriteMethod().invoke(obj, retVal);
					}
				}
			}
		} catch (Exception e) {
			LogUtil.error("",e);
		}
		return listBean;
	}
	
	private static boolean isNotEmpty(Object obj){
		Class<?> clazz = obj.getClass();
		Field[] declaredFields = clazz.getDeclaredFields();
		boolean isNotEmpty = false;
		try {
			for (Field field : declaredFields) {
				String name = field.getName();
				PropertyDescriptor pd = new PropertyDescriptor(name, clazz);
				Object retVal = pd.getReadMethod().invoke(obj, null);
				if(retVal!=null&&!"null".equalsIgnoreCase(retVal.toString())){
					isNotEmpty = true;
				}
			}
		} catch (Exception e) {
			LogUtil.error("",e);
		}
		return isNotEmpty;
	}
	
	@SuppressWarnings("all")
	public static String createXml(Object obj){
		Method[] methods = obj.getClass().getMethods();
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		Element rootElement = doc.addElement("Body");
		Element reqElement = rootElement.addElement("Request");
		for(Method method : methods){
			String methodName = method.getName();
			if(methodName.startsWith("getSp_")){
				try {
					String propertyName = methodName.substring("getSp_".length());
					Object retVal = method.invoke(obj, null);
					reqElement.addElement(propertyName).addText(retVal==null?"":retVal.toString()); 
				} catch (Exception e) {
					LogUtil.error("",e);
				}
			}
		}
		return doc.asXML();
	}
}
