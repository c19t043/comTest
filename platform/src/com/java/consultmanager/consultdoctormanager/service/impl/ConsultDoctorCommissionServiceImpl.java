package com.java.consultmanager.consultdoctormanager.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.classic.Session;
import org.springframework.beans.BeanUtils;

import com.java.consultmanager.consultdoctormanager.service.ConsultDoctorCommissionService;
import com.java.consultmanager.consultdoctormanager.vo.ConsultDoctorCommission;
import com.java.consultmanager.consultdoctormanager.vo.ConsultDoctorInfo;
import com.java.consultmanager.consultdoctormanager.vo.Position;
import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.user.service.ServiceImpl;

public class ConsultDoctorCommissionServiceImpl extends ServiceImpl implements
		ConsultDoctorCommissionService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}

	@Override
	public List<DoctorInfo> getDoctorInfoListByPage(PageSortModel psm,DoctorInfo doctorInfo,String positionName) {
		
		//查询咨询医生表，已有的医生id
		List<ConsultDoctorInfo> existConsultDoctors = super.getPersistProxy().getOrmPersistence().getHibernateTemp().find("from ConsultDoctorInfo");
		StringBuilder sb = new StringBuilder();
		for(ConsultDoctorInfo doctor : existConsultDoctors){
			sb.append(doctor.getDoctorInfo().getId()).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("FROM DoctorInfo c WHERE 1=1 ");
		hql.append(" and c.authentication='已通过' ");
		hql.append(" and c.id not in ("+sb.toString()+")").append(" and c.doctorTitle='"+positionName+"'");
		// 条件查
		if (doctorInfo != null) {
			if (doctorInfo.getDoctorName() != null && !"".equals(doctorInfo.getDoctorName().trim())) {
				params.put("doctorName", "%" + doctorInfo.getDoctorName().trim() + "%");
				hql.append(" AND c.doctorName LIKE :doctorName");
			}
		}
		List<DoctorInfo> list = (List<DoctorInfo>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}
	
	/**
	 * 批量添加医生信息,
	 * 根据医生级别，分配默认报酬
	 * @param doctorIds 医生ids
	 * @param commissionId 报酬表id
	 * @return
	 */
	public boolean addDoctorInfos(String[] doctorIds, String commissionId){
		
		if(doctorIds==null||doctorIds.length==0||StringUtils.isBlank(commissionId)) return false;
		
		ConsultDoctorCommission consultDoctorCommission = super.get(Long.parseLong(commissionId), ConsultDoctorCommission.class);
		List<ConsultDoctorInfo> list = new ArrayList<ConsultDoctorInfo>();
		
		for(int i=0;i<doctorIds.length;i++){
			
			/*String hql = "from ConsultDoctorInfo a where a.doctorInfo.id = "+Long.parseLong(doctorIds[i]);
			List<ConsultDoctorInfo> existObject = super.getPersistProxy().getOrmPersistence().getHibernateTemp().find(hql);
			if(!existObject.isEmpty()){
				ConsultDoctorInfo consultDoctorInfo = existObject.get(0);
				consultDoctorInfo.setIsEnable("N");
				super.edit(consultDoctorInfo);
			}*/
			
			ConsultDoctorInfo consultDoctorInfo = new ConsultDoctorInfo();
			
			DoctorInfo doctorInfo = new DoctorInfo();
			doctorInfo.setId(Long.parseLong(doctorIds[i]));
			consultDoctorInfo.setDoctorInfo(doctorInfo);;
			
			consultDoctorInfo.setRecommendMoney(consultDoctorCommission.getConsultMoney());
			consultDoctorInfo.setConsultCommission(consultDoctorCommission.getConsultCommission());
			consultDoctorInfo.setConsultMoney(consultDoctorCommission.getConsultMoney());
			
			consultDoctorInfo.setIsEnable("Y");
			consultDoctorInfo.setIsRecommend("N");
			consultDoctorInfo.setServiceLength(24L);
			
			list.add(consultDoctorInfo);
		}
		try{
			super.getPersistProxy().getOrmPersistence().getHibernateTemp().saveOrUpdateAll(list);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	
	/**
	 * 获取所有职称描述信息
	 * @return
	 */
	public List<Position> getPositionInfo(){
		String hql = "from Position where positionStatus = 'Y'";
		List list = super.getPersistProxy().getOrmPersistence().getHibernateTemp().find(hql);
		return list;
	}
	
	/**
	 * 增加一条咨询医生级别报酬分配配置记录
	 * 
	 * @param consultDoctorCommission
	 * @return 添加成功,返回true,失败，返回false
	 */
	public boolean addDoctorCommissionInfo(
			ConsultDoctorCommission consultDoctorCommission) {
		Serializable id = super.add(consultDoctorCommission);
		if (id != null)
			return true;
		return false;
	}

	/**
	 * 根据Id,删除一条咨询医生级别报酬分配配置记录
	 * 
	 * @param id
	 * @return 删除成功,返回true,失败，返回false
	 */
	public boolean deleteDoctorCommissionInfo(Long id) {
		ConsultDoctorCommission consultDoctorCommission = super.get(id,
				ConsultDoctorCommission.class);
		super.delete(consultDoctorCommission);
		consultDoctorCommission = super.get(id, ConsultDoctorCommission.class);
		if (consultDoctorCommission == null)
			return true;
		return false;
	}

	/**
	 * 修改一条咨询医生级别报酬分配配置记录
	 * 
	 * @param consultDoctorCommission
	 * @return 修改成功,返回true,失败，返回false
	 */
	public boolean updateDoctorCommissionInfo(
			ConsultDoctorCommission consultDoctorCommission) {
		ConsultDoctorCommission consultDoctorCommission_old = super.get(
				consultDoctorCommission.getId(), ConsultDoctorCommission.class);
		try {
			BeanUtils.copyProperties(consultDoctorCommission,
					consultDoctorCommission_old, new String[] { "id",
							"position"});
			super.edit(consultDoctorCommission_old);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 根据id,获取一条咨询医生级别报酬分配配置记录
	 * 
	 * @param id
	 * @return
	 */
	public ConsultDoctorCommission getDoctorCommissionInfo(Long id) {
		return super.get(id, ConsultDoctorCommission.class);
	}

	/**
	 * 获取所有咨询医生级别报酬分配配置记录 职称名字：commission.name 1.如果name 为空，查询所有信息 2.如果name
	 * 有值,查询对应职称的信息
	 * 
	 * @return
	 */
	public List<ConsultDoctorCommission> getDoctorCommissionInfoList(
			PageSortModel model, ConsultDoctorCommission commission) {

		StringBuilder sb = new StringBuilder();
		sb.append("from ConsultDoctorCommission as a ").append(" where 1=1 ");

		Map<String, Object> params = new HashMap<String, Object>();
		if (commission != null) {
			if (StringUtils.isNotBlank(commission.getName())) {
				sb.append("and  a.position.name like :name");// with b.name like
																// :name
				params.put("name", "%" + commission.getName() + "%");
			}
		}
		//sb.append(" and a.isEnable = 'Y' ");

		List<ConsultDoctorCommission> list = (List<ConsultDoctorCommission>) super
				.listForEc(sb.toString(), model, params);
		/*if (list.size() > 0) {
			for (ConsultDoctorCommission commission_Query : list) {
				Position position = commission_Query.getPosition();
				commission_Query.setName(position.getName());
			}
		}*/

		return list;
	}
}
