package com.kybaby.newbussiness.medicalorgandbusiness.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.medicalorgandbusiness.dao.VaccineManageDao;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.AppointmentInitInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganServicePlaceSet;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.VaccineInfo;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;

/**
 * 疫苗管理Dao接口实现类
 * @author xiongchao
 */
public class VaccineManageDaoImpl extends HibernateDaoSupport implements VaccineManageDao {

	@Override
	public List<OrganInoculationOpenResourcesDetail> getOrganInoculationOpenResourcesDetailList(OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from OrganInoculationOpenResourcesDetail o where 1=1");
		/*hql.append(" and o.openEndTime < ?");//过期的时间不用查询出来
		params.add(DateManage.getDateToStr(new Date(), "HH:mm"));*/
		
		if(organInoculationOpenResourcesDetail != null) {
			if(organInoculationOpenResourcesDetail.getOrganInoculationOpenResources() != null 
					&& organInoculationOpenResourcesDetail.getOrganInoculationOpenResources().getId() != null
					&& !"".equals(organInoculationOpenResourcesDetail.getOrganInoculationOpenResources().getId())){
				hql.append(" and o.organInoculationOpenResources.id=?");
				params.add(organInoculationOpenResourcesDetail.getOrganInoculationOpenResources().getId());
			}
			if(organInoculationOpenResourcesDetail.getOpenDate() != null && !"".equals(organInoculationOpenResourcesDetail.getOpenDate().trim())){
				hql.append(" and o.openDate=?");
				params.add(organInoculationOpenResourcesDetail.getOpenDate());
			}
			if(organInoculationOpenResourcesDetail.getOrganInoculationOpenResources() != null && organInoculationOpenResourcesDetail.getOrganInoculationOpenResources().getHospitalBasicInfo() != null){
				hql.append(" and o.organInoculationOpenResources.hospitalBasicInfo.id=?");
				params.add(organInoculationOpenResourcesDetail.getOrganInoculationOpenResources().getHospitalBasicInfo().getId());
			}
		}
		hql.append(" order by o.openStartTime");
		@SuppressWarnings("unchecked")
		List<OrganInoculationOpenResourcesDetail> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<OrganInoculationOpenResources> getOrganInoculationOpenResourcesList(
			OrganInoculationOpenResources organInoculationOpenResources) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from OrganInoculationOpenResources o where 1=1");
		//hql.append(" and o.openDate > curdate()");//大于当前日期（把今天以后的可预约日期显示出来）
		hql.append(" and TO_DAYS(openDate) - TO_DAYS(NOW()) >= 0 and TO_DAYS(openDate) - TO_DAYS(NOW()) <=60 and isAvailable='Y'");
		if(organInoculationOpenResources != null) {
			if(organInoculationOpenResources.getOpenDate() != null && !"".equals(organInoculationOpenResources.getOpenDate().trim())){
				hql.append(" and o.openDate=?");
				params.add(organInoculationOpenResources.getOpenDate());
			}
			if(organInoculationOpenResources.getHospitalBasicInfo() != null && !"".equals(organInoculationOpenResources.getHospitalBasicInfo().getId())){
				hql.append(" and o.hospitalBasicInfo.id=?");
				params.add(organInoculationOpenResources.getHospitalBasicInfo().getId());
			}
		}
		@SuppressWarnings("unchecked")
		List<OrganInoculationOpenResources> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArchivesInfo getCurrentUserIdentity(Long userId, String mobile) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from ArchivesInfo a where 1=1");
		if(userId != null) {
			hql.append(" and a.userInfo.id=?");
			params.add(userId);
		}
		if(mobile != null) {
			hql.append(" and a.archivesMobile=?");
			params.add(mobile);
		}
		List<ArchivesInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据宝宝生日和手机号码查询关联信息
	 * @param mobile
	 * @param birthday
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ArchivesInfo getRelationArchivesInfo(String mobile, String birthday,Long organId) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from ArchivesInfo a where 1=1");
		if(mobile != null) {
			hql.append(" and a.archivesMobile=?");
			params.add(mobile);
		}
		if(birthday != null) {
			hql.append(" and a.childrenBirthday=?");
			params.add(birthday);
		}
		if(organId != null) {
			hql.append(" and a.hospitalBasicInfo.id=?");
			params.add(organId);
		}
		List<ArchivesInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 生成序列号
	 * @param organId
	 * @param openDate
	 * @return
	 */
	private String generateSerialNumber(Long organId, String openDate) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("select count(*) from UserInoculationAppointmentInfo u where 1=1");
		if(organId != null) {
			hql.append(" and u.hospitalBasicInfo.id=?");
			params.add(organId);
		}
		if(openDate != null) {
			hql.append(" and u.organInoculationOpenResources.openDate=?");
			params.add(openDate);
		}
		Long count = (Long)this.getHibernateTemplate().find(hql.toString(), params.toArray()).listIterator().next();
		String serialNumber = String.valueOf(count.intValue()+1);
		int digit = serialNumber.length();
		//保持序列号为5位数字
		while (digit < 5) {
			serialNumber = "0" + serialNumber;
			digit = serialNumber.length();
		}
		return serialNumber;
	}

	@Override
	public Long saveUserInoculationAppointmentInfo(Long userId, 
			UserInoculationAppointmentInfo userInoculationAppointmentInfo,
			FdServicePackage fdServicePackage) {
		//预约的窗口（根据用户类型动态分配）
		OrganServicePlaceSet organServicePlaceSet = setServicePlace(userId,fdServicePackage);
		userInoculationAppointmentInfo.setOrganServicePlaceSet(organServicePlaceSet);
		//预约用户
		UserInfo userInfo = this.getHibernateTemplate().get(UserInfo.class, userId);
		userInoculationAppointmentInfo.setUserInfo(userInfo);
		//预约机构
		userInoculationAppointmentInfo.setHospitalBasicInfo(organServicePlaceSet.getHospitalBasicInfo());
		
		OrganInoculationOpenResources oior = this.getHibernateTemplate().get(OrganInoculationOpenResources.class, userInoculationAppointmentInfo.getOrganInoculationOpenResources().getId());
		//预约编码
		userInoculationAppointmentInfo.setAppointmentCode(generateSerialNumber(oior.getHospitalBasicInfo().getId(), oior.getOpenDate()));
		//userInoculationAppointmentInfo.setAppointmentCode(String.valueOf(System.currentTimeMillis()));
		//预约状态
		userInoculationAppointmentInfo.setStatus(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
		//操作日期
		userInoculationAppointmentInfo.setOptTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
		
		Long id = (Long) this.getHibernateTemplate().save(userInoculationAppointmentInfo);
		//更新机构接种疫苗开放资源和明细的剩余数量
		updateOrganInoculationOpenResourcesAndDetailNum(userId, userInoculationAppointmentInfo, "-");
		return id;
	}
	
	/**
	 * 预约疫苗设置服务窗口
	 * @param userId
	 * @return
	 */
	private OrganServicePlaceSet setServicePlace(Long userId,FdServicePackage fdServicePackage) {
		String currentUserIdentity = "";
		//获取当前用户身份
		ArchivesInfo archivesInfo = new ArchivesInfo();
		if(fdServicePackage == null){
			archivesInfo = getCurrentUserIdentity(userId, null);
		}else{
			FdServicePackage fdPackage = this.getHibernateTemplate().get(FdServicePackage.class, fdServicePackage.getId());
			archivesInfo.setUserType(ArchivesInfo.USER_TYPE_GOLDEN_CARD);
			archivesInfo.setHospitalBasicInfo(fdPackage.getHospitalBasicInfo());
		}
		if (archivesInfo != null) {
			currentUserIdentity = archivesInfo.getUserType();
		}
		List<OrganServicePlaceSet> organServicePlaceSetList = getOrganServicePlaceSetListByUserIdentityAndOrgan(archivesInfo.getHospitalBasicInfo().getId(), OrganServicePlaceSet.SERVICE_TYPE_WINDOW, currentUserIdentity);
		return distributionOrganServicePlace(organServicePlaceSetList);
	}
	
	/**
	 * 分配机构的服务窗口（算法）
	 * @param organServicePlaceSetList
	 */
	private OrganServicePlaceSet distributionOrganServicePlace(List<OrganServicePlaceSet> organServicePlaceSetList) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		int serialNumber = 0;//排序的序号
		if (organServicePlaceSetList != null && organServicePlaceSetList.size() > 0) {
			int[] sortArray = new int[organServicePlaceSetList.size()];
			for (int i=0; i<sortArray.length; i++) {
				sortArray[i] = getOrganServicePlaceNumByAppointment(organServicePlaceSetList.get(i));
				map.put(i, organServicePlaceSetList.get(i));
			}
			int min = sortArray[0];// 创建最小数变量
	        for (int j = 0; j < sortArray.length; j++) {
	            if (min > sortArray[j]) {// 提取最小整数
	                min = sortArray[j];
	                serialNumber = j;
	            }
	        }
		}
		return (OrganServicePlaceSet) map.get(serialNumber);
	}
	
	/**
	 * 获取机构服务窗口的已预约数量
	 * @return
	 */
	private int getOrganServicePlaceNumByAppointment(OrganServicePlaceSet organServicePlaceSet) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("select count(*) from UserInoculationAppointmentInfo u where 1=1");
		hql.append(" and u.organServicePlaceSet=?");
		params.add(organServicePlaceSet);
		Long count = (Long)this.getHibernateTemplate().find(hql.toString(), params.toArray()).listIterator().next();
		return count.intValue();
	}
	
	/**
	 * 获取机构服务场所列表（根据机构、服务类型、归属用户类型确定）
	 * @param organId
	 * @param serviceType
	 * @param userIdentity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<OrganServicePlaceSet> getOrganServicePlaceSetListByUserIdentityAndOrgan(Long organId, String serviceType, String userIdentity) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from OrganServicePlaceSet o where 1=1");
		if(organId != null) {
			hql.append(" and o.hospitalBasicInfo.id=?");
			params.add(organId);
		}
		if(serviceType != null) {
			hql.append(" and o.serviceType=?");
			params.add(serviceType);
		}
		if(userIdentity != null) {
			hql.append(" and o.ascriptionUserUser=?");
			params.add(userIdentity);
		}
		List<OrganServicePlaceSet> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list;
		}
		return null;
	}
	
	/**
	 * 更新机构接种疫苗开放资源和明细的剩余数量
	 * @param userInoculationAppointmentInfo
	 */
	private void updateOrganInoculationOpenResourcesAndDetailNum(Long userId, UserInoculationAppointmentInfo userInoculationAppointmentInfo, String calcType) {
		//机构接种疫苗开放资源
		OrganInoculationOpenResources oior = this.getHibernateTemplate().get(OrganInoculationOpenResources.class, userInoculationAppointmentInfo.getOrganInoculationOpenResources().getId());
		//机构接种疫苗开放资源明细
		OrganInoculationOpenResourcesDetail oiord = this.getHibernateTemplate().get(OrganInoculationOpenResourcesDetail.class, userInoculationAppointmentInfo.getOrganInoculationOpenResourcesDetail().getId());

		String currentUserIdentity = "";
		//获取当前用户身份
		ArchivesInfo archivesInfo = getCurrentUserIdentity(userId, null);
		if (archivesInfo != null) {
			currentUserIdentity = archivesInfo.getUserType();
		}
		
		if (ArchivesInfo.USER_TYPE_RETAIL.equals(currentUserIdentity)) {
			if ("-".equals(calcType)) {
				//普通窗口剩余数量（减少）
				if (oior.getGeneralSurplusNum()!=null && !"0".equals(oior.getGeneralSurplusNum())) {
					oior.setGeneralSurplusNum(String.valueOf(Integer.parseInt(oior.getGeneralSurplusNum()) - 1));
					this.getHibernateTemplate().update(oior);
				}
				if (oiord.getGeneralSurplusNum()!=null && !"0".equals(oiord.getGeneralSurplusNum())) {
					oiord.setGeneralSurplusNum(String.valueOf(Integer.parseInt(oiord.getGeneralSurplusNum()) - 1));
					this.getHibernateTemplate().update(oiord);
				}
			} else if ("+".equals(calcType)) {
				//普通窗口剩余数量（增加）
				oior.setGeneralSurplusNum(String.valueOf(Integer.parseInt(oior.getGeneralSurplusNum()) + 1));
				this.getHibernateTemplate().update(oior);
				
				oiord.setGeneralSurplusNum(String.valueOf(Integer.parseInt(oiord.getGeneralSurplusNum()) + 1));
				this.getHibernateTemplate().update(oiord);
			}
		} else {
			if ("-".equals(calcType)) {
				//绿色通道剩余数量（减少）
				if (oior.getGreenChannelSurplusNum()!=null && !"0".equals(oior.getGreenChannelSurplusNum())) {
					oior.setGreenChannelSurplusNum(String.valueOf(Integer.parseInt(oior.getGreenChannelSurplusNum()) - 1));
					this.getHibernateTemplate().update(oior);
				}
				if (oiord.getGreenChannelSurplusNum()!=null && !"0".equals(oiord.getGreenChannelSurplusNum())) {
					oiord.setGreenChannelSurplusNum(String.valueOf(Integer.parseInt(oiord.getGreenChannelSurplusNum()) - 1));
					this.getHibernateTemplate().update(oiord);
				}
			} else if ("+".equals(calcType)) {
				//绿色通道剩余数量（增加）
				oior.setGreenChannelSurplusNum(String.valueOf(Integer.parseInt(oior.getGreenChannelSurplusNum()) + 1));
				this.getHibernateTemplate().update(oior);
				
				oiord.setGreenChannelSurplusNum(String.valueOf(Integer.parseInt(oiord.getGreenChannelSurplusNum()) + 1));
				this.getHibernateTemplate().update(oiord);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInoculationAppointmentInfo getFirstAppointmentByUser(Long userId, String status) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from UserInoculationAppointmentInfo u where 1=1");
		if(userId != null) {
			hql.append(" and u.userInfo.id=?");
			params.add(userId);
		}
		if (status != null) {
			hql.append(" and u.status=?");
			params.add(status);
			//params.add(ConstantManage.HASE_BOOKED_CLINIC_ORDER);
		}
		hql.append(" order by u.optTime desc ");
		List<UserInoculationAppointmentInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public UserInoculationAppointmentInfo getAppointmentById(Long id) {
		return this.getHibernateTemplate().get(UserInoculationAppointmentInfo.class, id);
	}

	@Override
	public AppointmentInitInfo getAppointmentInitInfoByUserId(Long userId,FdServicePackage fdServicePackage) {
		AppointmentInitInfo appointmentInitInfo = new AppointmentInitInfo();
		ArchivesInfo archivesInfo = new ArchivesInfo();
		if(fdServicePackage == null){
			archivesInfo = getCurrentUserIdentity(userId, null);
		}else if(fdServicePackage.getId() != null){
			FdServicePackage fdPac = this.getHibernateTemplate().get(FdServicePackage.class, fdServicePackage.getId());
			archivesInfo.setHospitalBasicInfo(fdPac.getHospitalBasicInfo());
			UserInfo user = this.getHibernateTemplate().get(UserInfo.class, userId);
			archivesInfo.setChildrenBirthday(user.getBirthday());
		}
				
		appointmentInitInfo.setHospitalBasicInfo(archivesInfo.getHospitalBasicInfo());
		
		UserInoculationAppointmentInfo uiai = getFirstAppointmentByUser(userId, null);
		//第一次预约
		if (uiai==null) {
			OrganInoculationOpenResources organInoculationOpenResources = new OrganInoculationOpenResources();
			organInoculationOpenResources.setHospitalBasicInfo(archivesInfo.getHospitalBasicInfo());
			List<OrganInoculationOpenResources> oldList = getOrganInoculationOpenResourcesList(organInoculationOpenResources);
			if(oldList != null){
				OrganInoculationOpenResources newOrganInoculationOpenResources = oldList.get(0);
				//下一次接种日期
				appointmentInitInfo.setOrganInoculationOpenResources(newOrganInoculationOpenResources);
				OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail = new OrganInoculationOpenResourcesDetail();
				organInoculationOpenResourcesDetail.setOrganInoculationOpenResources(newOrganInoculationOpenResources);
				//下一次接种时间
				appointmentInitInfo.setOrganInoculationOpenResourcesDetail(getOrganInoculationOpenResourcesDetailList(organInoculationOpenResourcesDetail).get(0));
			}
			
			UserInfo usr = this.getHibernateTemplate().get(UserInfo.class, userId);
			if(usr != null) {
				String babyMonthYear = "";
				String babyBirthday = archivesInfo.getChildrenBirthday();//usr.getBirthday();
				String rightNow = CalculationMethod.rightNowDate().toString();
				try {
					babyMonthYear = String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				VaccineInfo vaccineInfo = new VaccineInfo();
				vaccineInfo.setInoculationMonth(babyMonthYear);
				
				List<VaccineInfo> vaccineInfoList = getVaccineInfoList(vaccineInfo);
				//下一次接种疫苗
				appointmentInitInfo.setVaccineInfo(vaccineInfoList==null?null:vaccineInfoList.get(0));
			}
			
			//状态
			appointmentInitInfo.setFlagStatus(AppointmentInitInfo.FIRST_APPOINTMENT);
		} 
		//已经预约，但未完成的预约订单
		else if (uiai!=null && 
				(ConstantManage.HASE_BOOKED_VACCINE.equals(uiai.getStatus())
				//|| ConstantManage.HASE_PREVIEW_VACCINE.equals(uiai.getStatus())
				//|| ConstantManage.HASE_REGISTER_VACCINE.equals(uiai.getStatus())
				)
				) {
			//状态
			appointmentInitInfo.setFlagStatus(AppointmentInitInfo.NOT_FINISH_APPOINTMENT);
			appointmentInitInfo.setId(uiai.getId());
			appointmentInitInfo.setOrganInoculationOpenResources(uiai.getOrganInoculationOpenResources());
			appointmentInitInfo.setHospitalBasicInfo(uiai.getHospitalBasicInfo());
			appointmentInitInfo.setOrganInoculationOpenResourcesDetail(uiai.getOrganInoculationOpenResourcesDetail());
			if(uiai.getVaccineInfo() == null){
				UserInfo usr = this.getHibernateTemplate().get(UserInfo.class, userId);
				if(usr != null) {
					String babyMonthYear = "";
					String babyBirthday = archivesInfo.getChildrenBirthday();//usr.getBirthday();
					String rightNow = CalculationMethod.rightNowDate().toString();
					try {
						babyMonthYear = String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					VaccineInfo vaccineInfo = new VaccineInfo();
					vaccineInfo.setInoculationMonth(babyMonthYear);
					
					List<VaccineInfo> vaccineInfoList = getVaccineInfoList(vaccineInfo);
					//下一次接种疫苗
					appointmentInitInfo.setVaccineInfo(vaccineInfoList==null?null:vaccineInfoList.get(0));
				}
			}
		}
		//已经完成预约订单，第二次之后的预约
		else if (uiai!=null && ConstantManage.HASE_FINISHED_CLINIC_ORDER.equals(uiai.getStatus())) {
			OrganInoculationOpenResources organInoculationOpenResources = new OrganInoculationOpenResources();
			organInoculationOpenResources.setOpenDate(uiai.getNextVaccinationDate());
			organInoculationOpenResources.setHospitalBasicInfo(archivesInfo.getHospitalBasicInfo());
			List<OrganInoculationOpenResources> oldList = this.getOrganInoculationOpenResourcesList(organInoculationOpenResources);
			OrganInoculationOpenResources newOrganInoculationOpenResources = new OrganInoculationOpenResources();
			if(oldList != null){
				newOrganInoculationOpenResources = oldList.get(0);
			}else{
				newOrganInoculationOpenResources.setOpenDate(uiai.getNextVaccinationDate());
			}
			//下一次接种日期
			appointmentInitInfo.setOrganInoculationOpenResources(newOrganInoculationOpenResources);
			
			//下一次接种时间
			OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail = new OrganInoculationOpenResourcesDetail();
			organInoculationOpenResourcesDetail.setOrganInoculationOpenResources(newOrganInoculationOpenResources);
			appointmentInitInfo.setOrganInoculationOpenResourcesDetail(getOrganInoculationOpenResourcesDetailList(organInoculationOpenResourcesDetail).get(0));
			
			//下一次接种疫苗
			appointmentInitInfo.setVaccineInfo(uiai.getVaccineInfo());
			
			//状态
			appointmentInitInfo.setFlagStatus(AppointmentInitInfo.FINISH_APPOINTMENT);
		} else if (uiai!=null && ConstantManage.HASE_CANCEL_VACCINE.equals(uiai.getStatus())) {
			UserInoculationAppointmentInfo cancelUiai = getFirstAppointmentByUser(userId, ConstantManage.HASE_FINISHED_CLINIC_ORDER);
			if (cancelUiai==null) {
				/**
				 * 与第一次预约代码相同
				 */
				OrganInoculationOpenResources organInoculationOpenResources = new OrganInoculationOpenResources();
				organInoculationOpenResources.setHospitalBasicInfo(archivesInfo.getHospitalBasicInfo());
				List<OrganInoculationOpenResources> oldList = this.getOrganInoculationOpenResourcesList(organInoculationOpenResources);
				OrganInoculationOpenResources newOrganInoculationOpenResources = new OrganInoculationOpenResources();
				if(oldList != null){
					newOrganInoculationOpenResources = oldList.get(0);
				}else{
					newOrganInoculationOpenResources.setOpenDate(uiai.getNextVaccinationDate()==null?"":uiai.getNextVaccinationDate());
				}
				//OrganInoculationOpenResources newOrganInoculationOpenResources = getOrganInoculationOpenResourcesList(organInoculationOpenResources).get(0);
				
				//下一次接种日期
				appointmentInitInfo.setOrganInoculationOpenResources(newOrganInoculationOpenResources);
				
				OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail = new OrganInoculationOpenResourcesDetail();
				organInoculationOpenResourcesDetail.setOrganInoculationOpenResources(newOrganInoculationOpenResources);
				//下一次接种时间
				appointmentInitInfo.setOrganInoculationOpenResourcesDetail(getOrganInoculationOpenResourcesDetailList(organInoculationOpenResourcesDetail).get(0));
				
				UserInfo usr = this.getHibernateTemplate().get(UserInfo.class, userId);
				if(usr != null) {
					String babyMonthYear = "";
					String babyBirthday = archivesInfo.getChildrenBirthday();//usr.getBirthday();
					String rightNow = CalculationMethod.rightNowDate().toString();
					try {
						babyMonthYear = String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					VaccineInfo vaccineInfo = new VaccineInfo();
					vaccineInfo.setInoculationMonth(babyMonthYear);
					
					List<VaccineInfo> vaccineInfoList = getVaccineInfoList(vaccineInfo);
					if(!vaccineInfoList.isEmpty()) {
						//下一次接种疫苗
						appointmentInitInfo.setVaccineInfo(vaccineInfoList.get(0));
					}
				}
				
				//状态
				appointmentInitInfo.setFlagStatus(AppointmentInitInfo.FIRST_APPOINTMENT);
				
			} else {
				/**
				 * 与已经完成预约订单，第二次之后的预约的代码相同
				 */
				OrganInoculationOpenResources organInoculationOpenResources = new OrganInoculationOpenResources();
				organInoculationOpenResources.setOpenDate(cancelUiai.getNextVaccinationDate());
				organInoculationOpenResources.setHospitalBasicInfo(archivesInfo.getHospitalBasicInfo());
				//OrganInoculationOpenResources newOrganInoculationOpenResources = getOrganInoculationOpenResourcesList(organInoculationOpenResources).get(0);
				List<OrganInoculationOpenResources> oldList = this.getOrganInoculationOpenResourcesList(organInoculationOpenResources);
				OrganInoculationOpenResources newOrganInoculationOpenResources = new OrganInoculationOpenResources();
				if(oldList != null){
					newOrganInoculationOpenResources = oldList.get(0);
				}else{
					newOrganInoculationOpenResources.setOpenDate(uiai.getNextVaccinationDate());
				}
				//下一次接种日期
				appointmentInitInfo.setOrganInoculationOpenResources(newOrganInoculationOpenResources);
				
				OrganInoculationOpenResourcesDetail organInoculationOpenResourcesDetail = new OrganInoculationOpenResourcesDetail();
				organInoculationOpenResourcesDetail.setOrganInoculationOpenResources(newOrganInoculationOpenResources);
				//下一次接种时间
				appointmentInitInfo.setOrganInoculationOpenResourcesDetail(getOrganInoculationOpenResourcesDetailList(organInoculationOpenResourcesDetail).get(0));
				
				//下一次接种疫苗
				appointmentInitInfo.setVaccineInfo(cancelUiai.getVaccineInfo());
				
				//状态
				appointmentInitInfo.setFlagStatus(AppointmentInitInfo.FINISH_APPOINTMENT);
			}
			
		}
		return appointmentInitInfo;
	}
	
	@SuppressWarnings("unchecked")
	private List<VaccineInfo> getVaccineInfoList(VaccineInfo vaccineInfo) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from VaccineInfo v where 1=1");
		if(vaccineInfo != null) {
			hql.append(" and v.inoculationMonth>=?");
			params.add(vaccineInfo.getInoculationMonth());
		}
		List<VaccineInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list;
		}
		return null;
	}

	@Override
	public VaccineInfo getVaccineInfoById(Long id) {
		return this.getHibernateTemplate().get(VaccineInfo.class, id);
	}

	@Override
	public void cancelUserInoculationAppointmentInfo(Long id) {
		UserInoculationAppointmentInfo updateObject =  this.getHibernateTemplate().get(UserInoculationAppointmentInfo.class, id);
		updateObject.setOptTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
		updateObject.setStatus(ConstantManage.HASE_CANCEL_VACCINE);
		this.getHibernateTemplate().update(updateObject);
		
		//更新机构接种疫苗开放资源和明细的剩余数量
		updateOrganInoculationOpenResourcesAndDetailNum(updateObject.getUserInfo().getId(), updateObject, "+");
	}

	@Override
	public String saveRelationArchivesInfo(Long userId, ArchivesInfo archivesInfo) {
		ArchivesInfo updateArchivesInfo = 
				getRelationArchivesInfo(archivesInfo.getArchivesMobile(), archivesInfo.getChildrenBirthday(),null);
		if (updateArchivesInfo==null) {//非散户才看是否关联，散户自动初始化
			return "未关联";
		} else {
			updateArchivesInfo.setIsRelation("Y");
			updateArchivesInfo.setHospitalBasicInfo(archivesInfo.getHospitalBasicInfo());
			updateArchivesInfo.setUserInfo(this.getHibernateTemplate().get(UserInfo.class, userId));
			this.getHibernateTemplate().update(updateArchivesInfo);
			return "成功";
		}
	}

	@Override
	public List<UserInoculationAppointmentInfo> getUserInoculationAppointmentInfoList(
			Long userId) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from UserInoculationAppointmentInfo v where 1=1");
		if(userId != null) {
			hql.append(" and v.userInfo.id=?");
			params.add(userId);
		}
		hql.append(" order by v.organInoculationOpenResources.openDate desc");
		List<UserInoculationAppointmentInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list;
		}
		return null;
	}

	@Override
	public OrganInoculationOpenResourcesDetail getOrganInoculationOpenResourcesDetailById(
			Long id) {
		return this.getHibernateTemplate().get(OrganInoculationOpenResourcesDetail.class, id);
	}

	@Override
	public void saveOrUpdateArchivesInfo(ArchivesInfo archivesInfo) {
		if(archivesInfo.getId() == null){
			this.getHibernateTemplate().save(archivesInfo);
		}else{
			this.getHibernateTemplate().update(archivesInfo);
		}
	}

	@Override
	public ArchivesInfo getArchivesInfoBySomeThing(Long userId,
			String birthday, Long organId) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from ArchivesInfo a where 1=1");
		if(userId != null) {
			hql.append(" and a.userInfo.id=?");
			params.add(userId);
		}
		if(birthday != null) {
			hql.append(" and a.childrenBirthday=?");
			params.add(birthday);
		}
		if(organId != null) {
			hql.append(" and a.hospitalBasicInfo.id=?");
			params.add(organId);
		}
		List<ArchivesInfo> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
