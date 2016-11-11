package com.java.familydoctor.worktimeset.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.commons.lang.StringUtils;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.worktimeset.service.DoctorWorkTimeService;
import com.java.familydoctor.worktimeset.vo.DoctorWorkTime;
import com.java.platform.user.service.ServiceImpl;

public class DoctorWorkTimeServiceImpl extends ServiceImpl implements DoctorWorkTimeService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<DoctorWorkTime> getDoctorWorkTimeByPage(PageSortModel psm,
			DoctorWorkTime doctorWorkTime) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM DoctorWorkTime u where 1=1");
		if(doctorWorkTime != null){
			if(doctorWorkTime.getDoctorInfo() != null){
				if(StringUtils.isNotEmpty(doctorWorkTime.getDoctorInfo().getDoctorName())){
					hql.append(" and u.doctorInfo.doctorName like :doctorName");
					param.put("doctorName", "%"+doctorWorkTime.getDoctorInfo().getDoctorName()+"%");
				}
				if(doctorWorkTime.getDoctorInfo().getId() != null){
					hql.append(" and u.doctorInfo.id = :doctorId");
					param.put("doctorId", doctorWorkTime.getDoctorInfo().getId());
				}
			}
		}
		List<DoctorWorkTime> list = null;
		if(psm != null){
			list = (List<DoctorWorkTime>) listForEc(hql.toString(),psm, param);
		}else{
			list = list(hql.toString(), -1, -1, param);
		}
		
		return list;
	}

	@Override
	public DoctorWorkTime getDoctorWorkTimeById(Long id) {
		return this.get(id, DoctorWorkTime.class);
	}

	@Override
	public Long saveOrUpdateDoctorWorkTime(DoctorWorkTime doctorWorkTime) {
		if(doctorWorkTime == null) return null;
		Long id = null;
		if(doctorWorkTime.getId() == null){
			id = (Long) this.add(doctorWorkTime);
		}else{
			id = doctorWorkTime.getId();
			this.edit(doctorWorkTime);
		}
		return id;
	}

}
