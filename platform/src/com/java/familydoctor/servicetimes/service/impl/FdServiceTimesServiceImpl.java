package com.java.familydoctor.servicetimes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.servicepackage.vo.FdServicePackage;
import com.java.familydoctor.servicetimes.service.FdServiceTimesService;
import com.java.familydoctor.servicetimes.vo.FdServiceTimes;
import com.java.platform.user.service.ServiceImpl;

public class FdServiceTimesServiceImpl extends ServiceImpl implements FdServiceTimesService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		
		return null;
	}

	@Override
	public Long saveOrUpdateFdServiceTimes(FdServiceTimes fdServiceTimes) {
		if(fdServiceTimes == null){
			return null;
		}
		Long id = null;
		if(fdServiceTimes.getId() == null){
			id = (Long)super.add(fdServiceTimes);
		}else{
			id = fdServiceTimes.getId();
			super.edit(fdServiceTimes);
		}
		return id;
	}

	@Override
	public FdServiceTimes getFdServiceTimesById(Long id) {
		
		return super.get(id, FdServiceTimes.class);
	}

	@Override
	public List<FdServiceTimes> getFdServiceTimesByPage(PageSortModel psm,FdServiceTimes fdServiceTimes) {
		Map<String,Object> params =  new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM FdServiceTimes f where 1=1 ");
		if(fdServiceTimes != null){
			if(fdServiceTimes.getTimeName() != null && !"".equals(fdServiceTimes.getTimeName())){
				params.put("timeName", "%"+fdServiceTimes.getTimeName()+"%");
				hql.append("AND f.timeName like  :timeName");
			}
		}
		List<FdServiceTimes> list = (List<FdServiceTimes>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}

}
