package com.java.familydoctor.serviceitem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.serviceitem.service.FdServiceItemsService;
import com.java.familydoctor.serviceitem.vo.FdServiceItems;
import com.java.platform.user.service.ServiceImpl;
@SuppressWarnings("unchecked")
public class FdServiceItemsServiceImpl extends ServiceImpl implements FdServiceItemsService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FdServiceItems> getFdServiceItemsListByPage(PageSortModel psm,
			FdServiceItems fdServiceItems) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM FdServiceItems c WHERE 1=1 ");
		
		String itemShowName = fdServiceItems.getItemShowName();
		System.out.println("itemShowName : " + itemShowName);
		// 条件查询
		if (fdServiceItems != null) {
			if (fdServiceItems.getItemShowName() != null && !"".equals(fdServiceItems.getItemShowName().trim())) {
				params.put("itemShowName", "%" + fdServiceItems.getItemShowName().trim() + "%");
				hql.append(" AND c.itemShowName LIKE :itemShowName");
			}
		}
		List<FdServiceItems> list = (List<FdServiceItems>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}

	@Override
	public Long saveOrUpdateFdServiceItems(FdServiceItems fdServiceItems) {
		if(fdServiceItems == null) return null;
		
		Long id = null;
		if(fdServiceItems.getId() == null){
			id = (Long) super.add(fdServiceItems);
		}else{
			id = fdServiceItems.getId();
			FdServiceItems old = this.getFdServiceItemsById(id);
			BeanUtils.copyProperties(fdServiceItems, old, new String[]{"createPerson","createTime"});
			super.edit(old);
		}
		return id;
	}

	@Override
	public FdServiceItems getFdServiceItemsById(Long id) {
		return super.get(id, FdServiceItems.class);
	}

}
