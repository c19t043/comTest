package com.kybaby.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CommonDao {
	int PAGE_SHOW_NUM = 10;
	/**
	 * 删除对象
	 * @param entity
	 */
	<T> void deleteObject(Serializable id,Class<T> clazz); 
	/**
	 * 保存对象
	 * @param entity
	 */
	public <T> void saveObject(T entity);
	/**
	 * 批量保存对象
	 * @param entitys
	 */
	public <T> void saveObjectWithBatch(List<T> entitys);
	/**
	 * 根据主键ID获取对象
	 * @param id
	 * @param clazz
	 * @return
	 */
	public <T> T getObjectByID(Serializable id,Class<T> clazz);
	/**
	 * 根据条件获取对象
	 * @param condition
	 * @param params
	 * @return
	 */
	public <T> T getObjectByCondition(String condition,Object[] params);
	/**
	 * 根据条件获取对象
	 * @param condition
	 * @param params
	 * @return
	 */
	public <T> T getObjectByCondition(String condition,Map<String,Object> params);
	/**
	 * 不分页获取对象集合
	 * @param condition 
	 * @param params
	 * @param orderby
	 * @return
	 */
	public <T> List<T> getObjectCollectionWithNoPage(String condition,Object[] params,Map<String,String> orderby);
	/**
	 * 不分页获取对象集合
	 * @param condition 
	 * @param params
	 * @param orderby
	 * @return
	 */
	public <T> List<T> getObjectCollectionWithNoPage(String condition,Map<String,Object> params,Map<String,String> orderby);
	/**
	 * 分页获取对象集合
	 * @param condition 
	 * @param params
	 * @param orderby
	 * @param pageNo
	 * @return
	 */
	public <T> List<T> getObjectCollectionWithPagination(String condition,Object[] params,Map<String,String> orderby,Integer pageNo);
	/**
	 * 修改对象
	 * @param entity
	 */
	public <T> void updateObject(T entity);
	/**
	 * 批量修改对象
	 * @param entity
	 */
	public <T> void updateObjectWithBatch(List<T> entitys);
}
