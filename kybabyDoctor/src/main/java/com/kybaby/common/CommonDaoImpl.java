package com.kybaby.common;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CommonDaoImpl extends HibernateDaoSupport implements CommonDao{
	
	public void setSessionFactoryDI(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	/**
	 * 保存对象
	 * @param entity
	 */
	@Override
	public <T> void saveObject(T entity){
		this.getHibernateTemplate().save(entity);
	}
	/**
	 * 根据主键ID获取对象
	 * @param id
	 * @param clazz
	 * @return
	 */
	@Override
	public <T> T getObjectByID(Serializable id,Class<T> clazz){
		return this.getHibernateTemplate().get(clazz, id);
	}
	/**
	 * 修改对象
	 * @param entity
	 */
	@Override
	public <T> void updateObject(T entity){
		this.getHibernateTemplate().update(entity);
	}
	/**
	 * 分页获取对象集合
	 * @param condition 
	 * @param params
	 * @param orderby
	 * @param pageNo
	 * @return
	 */
	@Override
	public <T> List<T> getObjectCollectionWithPagination(String condition,final Object[] params,Map<String,String> orderby,final Integer pageNo){
		String oderByCondition = getOrderByCondition(orderby);
		final String finallyHql = condition+oderByCondition; 
		return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<T> doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				Query createQuery = session.createQuery(finallyHql);
				if(params!=null&&params.length>0){
					for (int i=0;i<params.length;i++){
						Object obj = params[i];
						if(obj instanceof String) {
							createQuery.setString(i, obj.toString()); continue;
						}
						if(obj instanceof Long) {
							createQuery.setLong(i, Long.parseLong(obj.toString())); continue;
						}
					}
				}
				int tmp_pageNo = pageNo == null ? 1 : pageNo;
				int startRow = (tmp_pageNo-1)*PAGE_SHOW_NUM;
				createQuery.setFirstResult(startRow);
				createQuery.setMaxResults(PAGE_SHOW_NUM);
				return createQuery.list();
			}
		});
	}
	/**
	 * 根据条件获取对象
	 * @param condition
	 * @param params
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getObjectByCondition(String condition,Object[] params){
		List<?> find = this.getHibernateTemplate().find(condition, params);
		if(find.isEmpty()) return null;
		return (T) find.get(0);
	}
	/**
	 * 不分页获取对象集合
	 * @param condition 
	 * @param params
	 * @param orderby
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getObjectCollectionWithNoPage(String condition,Object[] params,Map<String,String> orderby){
		String oderByCondition = getOrderByCondition(orderby);
		final String finallyHql = condition+oderByCondition; 
		return this.getHibernateTemplate().find(finallyHql, params);
	}
	private String getOrderByCondition(Map<String, String> orderby){
		StringBuilder sb = new StringBuilder("");
		if(orderby!=null&&orderby.size()>0){
			sb.append(" order by ");
			for (Entry<String, String> entry : orderby.entrySet()) {
				sb.append(entry.getKey()).append(" "+entry.getValue()).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}

	/**
	 * 批量保存对象
	 * @param entitys
	 */
	@Override
	public <T> void saveObjectWithBatch(List<T> entitys) {
		for (T entity : entitys) {
			this.saveObject(entity);
		}
	}
	/**
	 * 批量修改对象
	 * @param entity
	 */
	@Override
	public <T> void updateObjectWithBatch(List<T> entitys){
		for (T entity : entitys) {
			this.updateObject(entity);
		}
	}
	@Override
	public <T> List<T> getObjectCollectionWithNoPage(String condition,
			final Map<String, Object> params, Map<String, String> orderby) {
		String oderByCondition = getOrderByCondition(orderby);
		final String finallyHql = condition+oderByCondition; 
		return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query createQuery = session.createQuery(finallyHql);
				for (String key : params.keySet()) {
					createQuery.setParameter(key, params.get(key));
				}
				return createQuery.list();
			}
		});
	}
	@Override
	public <T> void deleteObject(final Serializable id,final Class<T> clazz) {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "delete  "+clazz.getSimpleName()+" c where c.id = :id";
				Query createQuery = session.createQuery(hql);
				createQuery.setParameter("id", id);
				return createQuery.executeUpdate();
			}
		});
	}
	@Override
	public <T> T getObjectByCondition(final String condition,
			final Map<String, Object> params) {
		return this.getHibernateTemplate().execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query createQuery = session.createQuery(condition);
				for (String key : params.keySet()) {
					createQuery.setParameter(key, params.get(key));
				}
				List<?> list = createQuery.list();
				if(list.isEmpty()){
					return null;
				}else{
					return (T) list.get(0);
				}
			}
		});
	}
}
