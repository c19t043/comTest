package com.java.operationmanage.strategy;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.java.consultmanager.consultdoctormanager.vo.Position;
import com.java.operationmanage.common.BooleanMsg;
import com.java.operationmanage.common.CBSMConstant;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.operationmanage.vo.DoctorMorePracticeOrgInfo;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.operationmanage.vo.HospitalPosition;
import com.java.operationmanage.vo.OperaDoctorSchedule;
import com.java.platform.user.service.ServiceImpl;

public abstract class ScheduleStrategy{
	protected ServiceImpl service;
	public ScheduleStrategy(ServiceImpl service){
		this.service = service;
	}
	
	/**
	 * 发布前的验证,是否可以发布
	 * @param operaDoctorSchedule 医生排班信息
	 * @return
	 */
	public abstract BooleanMsg isPassWithPublishCondition(OperaDoctorSchedule operaDoctorSchedule);
	/**
	 * 添加排班记录
	 * @param operaDoctorSchedule 医生排班信息
	 * @return
	 */
	public abstract Long addSchedule(OperaDoctorSchedule operaDoctorSchedule);
	/**
	 * 修改排班记录
	 * @param operaDoctorSchedule 医生排班信息
	 * @return
	 */
	public abstract Long updateSchedule(OperaDoctorSchedule operaDoctorSchedule);
	
	
	
	
	
	/**
	 * 验证排班是否可以发布
	 * @param operaDoctorSchedule 医生排班信息
	 * @return
	 */
	protected BooleanMsg validateScheduleConditionIsOK(OperaDoctorSchedule operaDoctorSchedule){
		String scheduleType = operaDoctorSchedule.getScheduleType();
		String businessType = "";
		if(CBSMConstant.SCHEDULETYPE_CHILDCARE.equals(scheduleType)){//儿保
			businessType = CBSMConstant.BUSINESSTYPE_CHILDCARE;
		}else if(CBSMConstant.SCHEDULETYPE_PEADIATRICS.equals(scheduleType)){//儿科
			businessType = CBSMConstant.BUSINESSTYPE_PEADIATRICS;
		}
		return  this.validateScheduleConditionIsOK(businessType,operaDoctorSchedule.getOperaBaseSchedule().getHospitalBasicInfo(),
				operaDoctorSchedule.getDoctorInfo());
	}
	/**
	 * 验证排班是否可以发布
	 * @param hospitalBasicInfo 要排班的社区机构
	 * @param doctorInfo 社区机构坐诊的医生
	 * @return
	 */
	private BooleanMsg validateScheduleConditionIsOK(String businessType,HospitalBasicInfo hospitalBasicInfo,DoctorInfo doctorInfo){
		BooleanMsg booleanMsg = new BooleanMsg();
		/*
		 * 查找多点执业机构
		 */
		DoctorMorePracticeOrgInfo doctorMorePracticeOrgInfo = getDoctorMorePracticeOrgInfo(hospitalBasicInfo.getId());
		if(doctorMorePracticeOrgInfo==null) {
			booleanMsg.setMsg(hospitalBasicInfo.getHospitalLname()+"没有配置多点执业,请配置之后在发布");
			booleanMsg.isTrue(false);
			return booleanMsg; 
		}
		/*
		 * 查找医院职称分成记录
		 */
		//医生所在医院id
		HospitalPosition hospitalPosition = findHospitalPosition(doctorInfo.getId(),doctorInfo.getHospitalId(),
				hospitalBasicInfo.getId(),businessType);
		if(hospitalPosition==null){
			booleanMsg.setMsg("社区医院‘"+hospitalBasicInfo.getHospitalLname()
					+"’没有配置医生‘"+doctorInfo.getDoctorName()+"’的医院职称分成记录");
			booleanMsg.isTrue(false);
			return booleanMsg;
		}
		booleanMsg.setObject(hospitalPosition);
		booleanMsg.isTrue(true);
		return booleanMsg;
	}
	/**
	 * 查找医院职称分成记录
	 * @param doctorInfo 医生信息
	 * @param hospitalBasicInfo 社区机构
	 * @param businessType 业务类型(儿科0,儿保1)
	 * @return
	 */
	protected HospitalPosition findHospitalPosition(DoctorInfo doctorInfo,HospitalBasicInfo hospitalBasicInfo,String businessType){
		return  findHospitalPosition(doctorInfo.getId(),doctorInfo.getHospitalId(),
				hospitalBasicInfo.getId(),businessType);
	}
	/**
	 * 根据社区机构ID查找多点机构信息
	 * @param orgID 社区机构ID
	 * @return
	 */
	protected DoctorMorePracticeOrgInfo getDoctorMorePracticeOrgInfo(Long orgID){
		String hql = "from DoctorMorePracticeOrgInfo c where c.hospitalBasicInfo.id = ? ";
		return service.getPersistProxy().getOrmPersistence().findObjectByHQL(hql,new Object[]{orgID});
	}
	/**
	 * 查找医院职称分成记录
	 * @param doctorID 医生ID
	 * @param hopitalId 医生所在医院ID
	 * @param orgId 社区机构ID
	 * @param businessType 业务类型(儿科0,儿保1)
	 * @return
	 */
	protected HospitalPosition findHospitalPosition(Long doctorID,Long hopitalId,Long orgID,String businessType){
		StringBuilder sb = new StringBuilder();
		//查找医生的职称信息
		Position position = findPosition(doctorID);
		sb.append("from HospitalPosition a where a.hospitalBasicInfo.id = ?")
			.append(" and a.position.id = ?")
			.append(" and a.doctorMorePracticeOrgInfo.hospitalBasicInfo.id = ?")
			.append(" and a.businessType = ?");
		return service.getPersistProxy().getOrmPersistence().findObjectByHQL(sb.toString(),new Object[]{hopitalId,position.getId(),orgID,businessType});
	}
	/**
	 * 查找医生职称
	 * @param doctorID 医生ID
	 * @return
	 */
	protected Position findPosition(final Long doctorID){
		Position position = (Position)service.getPersistProxy().getOrmPersistence().getHibernateTemp().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuilder sb = new StringBuilder();
				sb.append("select a.id,a.name,a.positionStatus,a.rank from position a")
					.append(" left join doctor_info b on a.name = b.doctorTitle")
					.append(" where b.id = ?");
				Query query = session.createSQLQuery(sb.toString());
				query.setLong(0, doctorID);
				
				List<?> list = query.list();
				if(list.isEmpty()) return null;
				
				Object[] obj = (Object[]) list.get(0);
				Position p = new Position();
				p.setId(Long.parseLong(obj[0]+""));
				p.setName((String) obj[1]);
				p.setPositionStatus((String) obj[2]);
				p.setRank(Long.parseLong(obj[3]+""));
				
				return p;
			}
		});
		return position;
	}
}
