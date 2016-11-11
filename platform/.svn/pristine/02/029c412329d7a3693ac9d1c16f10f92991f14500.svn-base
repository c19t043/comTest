package com.java.familydoctor.memberskill.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.memberskill.service.FdMemberSkillService;
import com.java.familydoctor.memberskill.vo.FdMemberSkill;
import com.java.platform.user.service.ServiceImpl;


public class FdMemberSkillServiceImpl extends ServiceImpl implements FdMemberSkillService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		
		return null;
	}

	@Override
	public Long saveOrUpdateFdMemberSkill(FdMemberSkill fdMemberSkill) {
		if(fdMemberSkill == null){
			return null;
		}
		Long id = null;
		if(fdMemberSkill.getId() == null){
			id = (Long)super.add(fdMemberSkill);
		}else{
			id = fdMemberSkill.getId();
			super.edit(fdMemberSkill);
		}
		return id;
	}

	@Override
	public FdMemberSkill getFdMemberSkillById(Long id) {
		
		return super.get(id, FdMemberSkill.class);
	}

	@Override
	public List<FdMemberSkill> getFdMemberSkillByPage(PageSortModel psm,
			FdMemberSkill fdMemberSkill) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM FdMemberSkill f where 1=1");
		if(fdMemberSkill != null){
			if(fdMemberSkill.getSkillName() != null && !"".equals(fdMemberSkill.getSkillName().trim())){
				params.put("skillName","%"+fdMemberSkill.getSkillName().trim()+"%");
				hql.append(" AND f.skillName LIKE :skillName");
			}
		}
		List<FdMemberSkill> list = (List<FdMemberSkill>) listForEc(String.valueOf(hql),psm, params);
		return list;
	}

}
