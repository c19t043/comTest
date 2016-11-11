package com.java.consultmanager.consultdoctormanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.java.consultmanager.consultdoctormanager.service.ConsultDoctorInfoService;
import com.java.consultmanager.consultdoctormanager.vo.ConsultDoctorInfo;
import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.user.service.ServiceImpl;

public class ConsultDoctorInfoServiceImpl extends ServiceImpl implements
		ConsultDoctorInfoService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}

	/**
	 * 获取所有医生
	 * @return
	 */
	public List<DoctorInfo> getDoctorInfos(){
		String hql = "from DoctorInfo a where a.doctorStatus = 'Y' and a.doctorName <> ''";
		List<DoctorInfo> list = super.getPersistProxy().getOrmPersistence().getHibernateTemp().find(hql);
		return list;
	}
	
	/**
	 * 添加一条咨询医生信息
	 */
	public boolean addConsultDoctorInfo(ConsultDoctorInfo consultDoctorInfo) {
		super.add(consultDoctorInfo);
		if(consultDoctorInfo.getId()!=null) return true;
		return false;
	}

	/*private DoctorInfo getDoctorInfo(Long id){
		return super.get(id, DoctorInfo.class);
	}*/
	
	/**
	 * 修改一条咨询医生信息
	 * 
	 * @param consultDoctorInfo
	 * @return
	 */
	public boolean updateConsultDoctorInfo(ConsultDoctorInfo consultDoctorInfo) {
		ConsultDoctorInfo consultDoctorInfo_old = super.get(consultDoctorInfo.getId(), ConsultDoctorInfo.class);
		BeanUtils.copyProperties(consultDoctorInfo, consultDoctorInfo_old, new String[]{"id","doctorInfo"});
		try{
			super.edit(consultDoctorInfo_old);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	/**
	 * 根据id,获取一条咨询医生信息
	 * 
	 * @param id
	 * @return
	 */
	public ConsultDoctorInfo getConsultDoctorInfoById(Long id) {
		return super.get(id, ConsultDoctorInfo.class);
	}

	/**
	 * 获取所有咨询医生信息
	 * 
	 * @return
	 */
	public List<ConsultDoctorInfo> getConsultDoctorInfoList(
			PageSortModel model, ConsultDoctorInfo consultDoctorInfo) {

		StringBuilder sb = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();

		sb.append("from ConsultDoctorInfo a where 1=1");
		if (consultDoctorInfo != null) {
			if (consultDoctorInfo.getDoctorInfo().getDoctorName() != null) {
				sb.append(" and a.doctorInfo.doctorName like :doctorName");
				map.put("doctorName", "%"+consultDoctorInfo.getDoctorInfo()
						.getDoctorName()+"%");
			}
		}
		// sb.append(" and a.isEnable = 'Y'");

		List<ConsultDoctorInfo> list = (List<ConsultDoctorInfo>) super
				.listForEc(sb.toString(), model, map);

		return list;
	}
}
