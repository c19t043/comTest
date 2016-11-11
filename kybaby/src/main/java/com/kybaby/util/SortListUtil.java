package com.kybaby.util;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * List对象排序通用方法
*    
   
* 类名称：SortList   
* 类描述：   
* 创建人：lihao   
* 创建时间：Nov 6, 2014 10:36:16 AM   
* 修改人：   
* 修改时间：Nov 6, 2014 10:36:16 AM   
* 修改备注：   
* @version    
*
 */
public class SortListUtil<E>{
	/**
	 * 对象集合排序
	 * 创建者：lihao
	 * 创建时间：Nov 6, 2014 10:36:23 AM
	 * @param list 要排序的集合
	 * @param method 通过对象的get方法得到排序对象，如getName
	 * @param sort 排列顺序，desc倒序；默认正序
	 */
	public void Sort(List<E> list, final String[] methods, final String sort){
		Collections.sort(list, new Comparator() {			
		    public int compare(Object a, Object b) {
		    	int ret = 0;
		    	try{
		    		for(int i =0; i<methods.length; i++ ){
		    			Method m1 = ((E)a).getClass().getMethod(methods[i], null);
		    			Method m2 = ((E)b).getClass().getMethod(methods[i], null);
		    			if(sort != null && "desc".equals(sort)){//倒序
		    				if(!m2.invoke(((E)b), null).toString().equals(m1.invoke(((E)a), null).toString())){
		    					return m2.invoke(((E)b), null).toString().compareTo(m1.invoke(((E)a), null).toString());	
		    				}else {
				    			continue;
				    		}
				    	}
				    	else{//正序
				    		//System.out.println("反射"+m2.invoke(((E)b), null).toString() + "比较"+m1.invoke(((E)a), null).toString());
				    		if(!m2.invoke(((E)b), null).toString().equals(m1.invoke(((E)a), null).toString())){
				    			return m1.invoke(((E)a), null).toString().compareTo(m2.invoke(((E)b), null).toString());
				    		}else {
				    			continue;
				    		}
				    		
				    	}
		    		}
//			    	Method m1 = ((E)a).getClass().getMethod(method, null);
//			    	Method m2 = ((E)b).getClass().getMethod(method, null);
//			    	if(sort != null && "desc".equals(sort)){//倒序
//			    		ret = m2.invoke(((E)b), null).toString().compareTo(m1.invoke(((E)a), null).toString());	
//			    	}
//			    	else{//正序
//			    		ret = m1.invoke(((E)a), null).toString().compareTo(m2.invoke(((E)b), null).toString());
//			    	}
		    	}catch(NoSuchMethodException ne){
		    		System.out.println(ne);
				}catch(IllegalAccessException ie){
					System.out.println(ie);
				}catch(InvocationTargetException it){
					System.out.println(it);
				}
		    	return ret;
		    }
		 });
	}
}
