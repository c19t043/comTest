package com.java.userinfo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.userinfo.service.KyUserInfoService;

public class KyUserInfoServiceImpl extends ServiceImpl implements KyUserInfoService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KyUserInfo> getKyUserInfoListByPage(PageSortModel psm,
			KyUserInfo kyUserInfo) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM KyUserInfo u where 1=1");
		if(kyUserInfo != null){
			if(StringUtils.isNotEmpty(kyUserInfo.getPhone())){
				param.put("phone","%"+ kyUserInfo.getPhone() + "%");
				hql.append(" and u.phone like :phone");
			}
			if(StringUtils.isNotEmpty(kyUserInfo.getBabyName())){
				param.put("babyName","%"+ kyUserInfo.getBabyName() + "%");
				hql.append(" and u.babyName like :babyName");
			}
		}
		hql.append(" order by u.registerTime desc");
		List<KyUserInfo> list = (List<KyUserInfo>) listForEc(hql.toString(),psm, param);
		return list;
	}

}
