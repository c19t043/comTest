package com.kybaby.newbussiness.spservice.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;

import com.kybaby.common.CommonDao;
import com.kybaby.newbussiness.spservice.domain.SpExceptionMsg;
import com.kybaby.newbussiness.spservice.domain.SpVisitRecord;
import com.kybaby.newbussiness.spservice.generatedFile.ToolInterfaceSoap;
import com.kybaby.newbussiness.spservice.util.SpInterfaceUtil;
import com.kybaby.util.DateManage;
import com.kybaby.util.LogUtil;

public class SpAdapter {
	private ToolInterfaceSoap spInterface;
	private CommonDao dao;
	public void setDao(CommonDao dao) {
		this.dao = dao;
	}
	public void setSpInterface(ToolInterfaceSoap spInterface) {
		this.spInterface = spInterface;
	}
	/**
	 * 将对象obj中特殊的字段转换为xml串,调用中联接口,将返回数据转换为clazz对应的对象集合
	 * @param obj 需要组装成xml的对象(输入对象,接口请求参数)
	 * @param clazz 返回集合的对象的类型(输出对象,返回数据转换的对象类型)
	 * @return
	 * <P>1.接口调用失败,返回空集合</P>
	 * <P>2.接口调用成功,返回成功编码为0,--》接口调用失败,返回空集合</P>
	 * <P>3.接口调用成功,返回成功编码为1,但是必要数据为空--》接口调用失败,返回空集合::目前没抽出</P>
	 */
	 public <T> List<T> getReqData(Object obj,Class<T> clazz){
		//获取接口调用参数
		String request = SpInterfaceUtil.createXml(obj);
		//调用中联接口
		String res = invokeSpInterface(request);
		//解析返回信息
		Document doc = SpInterfaceUtil.parseText2Doc(res);
		//判断操作是否成功
		List<T> list = null;
		boolean opFail = SpInterfaceUtil.getRetErrorCode(doc);
		if(opFail){ //失败
			list = new ArrayList<T>();
			String msg = "接口调用成功,返回错误编码,错误信息为:"+SpInterfaceUtil.getRetErrorMsg(doc);
			SpExceptionMsg exMsg = new SpExceptionMsg(res,msg,DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date()));
			dao.saveObject(exMsg);
			LogUtil.error(msg);
		}else{//成功
			list = SpInterfaceUtil.xml2JavaBean(doc,clazz);
			if(isEmpty(list,clazz)){
				list = new ArrayList<T>();
			}
		}
		return list;
	}
	/**
	 * 集合中数据是否为空
	 * @param colls
	 * @return
	 */
	private <T> boolean isEmpty(List<T> colls,Class<T> clazz){
		String simpleClassName = clazz.getSimpleName();
		List<Object> registerConvertObject = getRegisterConvertObjectCollection();
		try {
			for(Object obj : registerConvertObject){
				if(simpleClassName.equals(((Class<?>)obj).getSimpleName())){
					Method method = this.getClass().getMethod("isEmptyOf"+simpleClassName, List.class);
					method.setAccessible(true);
					return (boolean) method.invoke(this, colls);
				} 
			}
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return true;
	}
	private List<Object> getRegisterConvertObjectCollection(){
		List<Object> list = new ArrayList<Object>();
		list.add(SpVisitRecord.class);
		return list;
	}
	private boolean isEmptyOfSpVisitRecord(List<?> colls){
		SpVisitRecord visitRecord = (SpVisitRecord) colls.get(0);
		return false;
	}
	/**
	 * 调用中联接口
	 * @param requestParam 请求xml
	 * @return
	 */
	private String invokeSpInterface(String requestParam){
		String res = "";
		try{
			res = spInterface.interactionOperating(requestParam);
		}catch(Exception e){
			String msg="调用接口失败,异常信息："+e.toString();
			SpExceptionMsg exMsg = new SpExceptionMsg(requestParam,msg,DateManage.formatDateStr_yyyy_MM_dd_HH_mm_ss(new Date()));
			dao.saveObject(exMsg);
		}
		return res;
	}
	public static void main(String[] args) {
		 System.out.println(SpAdapter.class.getSimpleName());
	}
}
