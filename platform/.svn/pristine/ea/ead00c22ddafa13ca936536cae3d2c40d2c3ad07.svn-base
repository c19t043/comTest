 package com.java.publichealth.familyaccount.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.java.common.lang.StringUtils;
import com.java.ec.common.PageSortModel;
import com.java.medicalorgandbusiness.orgsetmeal.vo.ConsultBabyInfo;
import com.java.platform.user.service.ServiceImpl;
import com.java.publichealth.childhealth.vo.PhChildHealthExaminationRecord;
import com.java.publichealth.familyaccount.service.FamilyAccountInfoService;
import com.java.publichealth.familyaccount.vo.FamilyAccountInfo;
import com.java.publichealth.neonatalvisit.vo.PhNeonatalVisitRecord;
import com.java.publichealth.residentsfile.service.ResidentsFileService;
import com.java.publichealth.residentsfile.service.impl.ResidentsFileServiceImpl;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.publichealth.residentsfile.vo.PhPeopleBasicInfo;
import com.java.util.EncryptUtil;

public class FamilyAccountInfoServiceImpl extends ServiceImpl implements
FamilyAccountInfoService {
	protected static final Logger log = Logger.getLogger(ResidentsFileServiceImpl.class);
	
	/**
	 * 个人基本信息服务
	 */
	private ResidentsFileService residentsFileService;

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {

		return null;
	}

	/**
	 * 添加家庭开户信息
	 * @param familyAccountInfo
	 * @return 添加成功,返回true,失败,返回false
	 */
	public boolean saveFamilyAccountInfo(FamilyAccountInfo familyAccountInfo){
		
		/*
		 * 1.同步用户注册
		 * 2.保存宝宝记录
		 * 3.关联用户id，宝宝id串,保存家庭开户信息
		 */
		//同步用户注册
		KyUserInfo userInfo = packageUserInfo(familyAccountInfo);
		userInfo = addUserInfo(userInfo);
		if(userInfo.getId()==null) return false;
		
		//保存宝宝记录
		String babyIds = save_babyInfo(familyAccountInfo.getConsultBabyInfos());
		if(babyIds!=null)
			familyAccountInfo.setBabyIds(babyIds);
		/*List<ConsultBabyInfo> packageBabyInfo = packageBabyInfoList(familyAccountInfo);
		if(packageBabyInfo!=null){
			String babyIds = addBabyInfo(null,userInfo,packageBabyInfo);
			if(babyIds==null) return false;
			//关联用户id，宝宝id串,保存家庭开户信息
			familyAccountInfo.setBabyIds(babyIds);
		}*/
		
		familyAccountInfo.setUserInfo(userInfo);
		super.add(familyAccountInfo);
		if(familyAccountInfo.getId()==null) return false;
		
		return true;
	}
	
	private String save_babyInfo(List<ConsultBabyInfo> babyInfos){
		if(babyInfos==null||babyInfos.size()==0) return null;
		
		StringBuilder sb = new StringBuilder("");
		for (ConsultBabyInfo consultBabyInfo : babyInfos) {
			
			if(consultBabyInfo==null) continue;
			
			if(consultBabyInfo.getId()==null){
				super.add(consultBabyInfo);
			}else{
				update_babyInfo(consultBabyInfo);
			}
			sb.append(consultBabyInfo.getId()+",");
		}
		
		return sb.deleteCharAt(sb.length()-1).toString();
	}

	private void update_babyInfo(ConsultBabyInfo consultBabyInfo){
		ConsultBabyInfo babyInfo_query = super.get(consultBabyInfo.getId(), ConsultBabyInfo.class);
		BeanUtils.copyProperties(consultBabyInfo, babyInfo_query, new String[]{"id","headImg","userInfo"});
		super.edit(babyInfo_query);
	}
	
	private List<ConsultBabyInfo> packageBabyInfoList(FamilyAccountInfo familyAccountInfo){
		if(familyAccountInfo.getBabyNameArr()!=null&&familyAccountInfo.getBabyNameArr().length>0){
			String[] babyNameArr = familyAccountInfo.getBabyNameArr();
			String[] babySexArr = familyAccountInfo.getBabySexArr();
			String[] babyBirthdayArr = familyAccountInfo.getBabyBirthdayArr();
			
			List<ConsultBabyInfo> babyInfoList = new ArrayList<ConsultBabyInfo>();
			for(int i=0;i<babyNameArr.length;i++){
				babyInfoList.add(new ConsultBabyInfo(babyNameArr[i],babySexArr[i],babyBirthdayArr[i]));
			}
			
			return babyInfoList;
		}
		return null;
	}
	
	/**
	 * 添加宝宝信息
	 * @param babyids
	 * @param babyInfo
	 * @return
	 */
	private String addBabyInfo(String babyids,KyUserInfo userInfo,List<ConsultBabyInfo> babyInfoList){
		/*
		 * 1.根据传入的宝宝id串删除关联的宝宝
		 * 2.添加宝宝信息
		 * 3.返回添加的宝宝记录Id串
		 */
		//根据传入的宝宝id串删除关联的宝宝
		if(StringUtils.isNotBlank(babyids)){
			String[] babyidArr = null;
			if(babyids.contains(","))
				babyidArr = babyids.split(",");
			else
				babyidArr = new String[]{babyids};
			for(int i=0;i<babyidArr.length;i++){
				ConsultBabyInfo babyInfo = super.get(Long.parseLong(babyidArr[i]), ConsultBabyInfo.class);
				super.delete(babyInfo);
			}
		}
		
		//添加宝宝信息
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<babyInfoList.size();i++){
			ConsultBabyInfo consultBabyInfo = babyInfoList.get(i);
			consultBabyInfo.setUserInfo(userInfo);
			super.add(consultBabyInfo);
			sb.append(consultBabyInfo.getId()).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		//返回添加的宝宝记录Id串
		return sb.toString();
	}
	
	/**
	 * 组装用户信息
	 * @param familyAccountInfo
	 * @return
	 */
	private KyUserInfo packageUserInfo(FamilyAccountInfo familyAccountInfo){
		KyUserInfo kyUserInfo = new KyUserInfo();
		//设置默认密码
		kyUserInfo.setPassword(EncryptUtil.getMD5Str("123"));
		//电话
		kyUserInfo.setPhone(familyAccountInfo.getRegistPhone());
		//注册时间
		familyAccountInfo.setCreateTime(new Date());
		kyUserInfo.setRegisterTime(new Timestamp(familyAccountInfo.getCreateTime().getTime()));
		//用户状态
		kyUserInfo.setUserStatus("Y");
		//注册时间的经度,纬度
		kyUserInfo.setUserLng("104.06");
		kyUserInfo.setUserLat("30.67");
		//用户所在省
		kyUserInfo.setUserProvince(familyAccountInfo.getProvince());
		//用户所在市
		kyUserInfo.setUserCity(familyAccountInfo.getCity());
		//用户所在区
		kyUserInfo.setUserArea(familyAccountInfo.getArea());
		//用户所在街道
		kyUserInfo.setUserStreet(familyAccountInfo.getStreet());
		//详细地址
		kyUserInfo.setDetailAddress(familyAccountInfo.getAddress());
		//备注
		kyUserInfo.setComments("家庭开户,用户信息同步");
		kyUserInfo.setIsLogin("Y");
		//家长姓名
		kyUserInfo.setParentName(familyAccountInfo.getParentName());
		return kyUserInfo;
	}
	/**
	 * 添加用户信息
	 * 1.根据用户电话判断用户是否存在,
	 * 2.如果存在，获取用户信息，返回用户信息
	 * 3.不存在，保存用户信息，返回
	 * @param phPeopleBasicInfo
	 */
	private KyUserInfo addUserInfo(KyUserInfo kyUserInfo){
		//先判断对象是否存在
		PhPeopleBasicInfo phPeopleBasicInfo = new PhPeopleBasicInfo();
		phPeopleBasicInfo.setContactPhone(kyUserInfo.getPhone());
		List<KyUserInfo> listKyUserInfo = residentsFileService.findByHql(phPeopleBasicInfo);
		
		if(listKyUserInfo.size()>0){	
			kyUserInfo = listKyUserInfo.get(0);
		}else {
			Long KyUserInfoId = residentsFileService.saveKyUserInfo(kyUserInfo);
			kyUserInfo.setId(KyUserInfoId);
		}
		return kyUserInfo;
	}
	
	/**
	 * 修改家庭开户信息
	 * @param familyAccountInfo
	 * @return 修改成功,返回true,失败,返回false
	 */
	public boolean updateFamilyAccountInfo(FamilyAccountInfo familyAccountInfo){
		familyAccountInfo.setModifyTime(new Date());
		
		//同步用户注册
		KyUserInfo userInfo = packageUserInfo(familyAccountInfo);
		userInfo = addUserInfo(userInfo);
		if(userInfo.getId()==null) return false;
		familyAccountInfo.setUserInfo(userInfo);
		
		//保存宝宝记录
		String babyIds = save_babyInfo(familyAccountInfo.getConsultBabyInfos());
		if(babyIds!=null)
			familyAccountInfo.setBabyIds(babyIds);
		
		FamilyAccountInfo familyAccountInfo_get = this.getFamilyAccountInfo(familyAccountInfo.getId());
		BeanUtils.copyProperties(familyAccountInfo, familyAccountInfo_get, new String[]{"id","createTime","createPerson"});
		try{
			super.edit(familyAccountInfo_get);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	/**
	 * 根据id，获取家庭开户信息
	 * @param id
	 * @return
	 */
	public FamilyAccountInfo getFamilyAccountInfo(Long id){
		return super.get(id, FamilyAccountInfo.class);
	}
	
	/**
	 * 根据条件查询家庭开户信息
	 * @param model
	 * @param familyAccountInfo
	 * @return
	 */
	public List<FamilyAccountInfo> getFamilyAccountInfoList(PageSortModel model,FamilyAccountInfo familyAccountInfo){
		StringBuilder sb = new StringBuilder();
		sb.append("from FamilyAccountInfo");
		
		Map<String,Object> map = new HashMap<String,Object>();
		/*
		 * 条件查询
		 * 1.传入父母姓名，增加姓名查询条件
		 * 2.传入父母电话，增加电话查询条件
		 */
		if(familyAccountInfo!=null){
			sb.append(" where 1=1 ");
			if(StringUtils.isNotBlank(familyAccountInfo.getName())){
				sb.append(" and fatherName like :name  or motherName like :name");
				map.put("name", "%"+familyAccountInfo.getName()+"%");
			}
			if(StringUtils.isNotBlank(familyAccountInfo.getPhone())){
				sb.append(" and fatherPhone like :phone  or motherPhone like :phone");
				map.put("phone", "%"+familyAccountInfo.getPhone()+"%");
			}
		}
		
		List<FamilyAccountInfo> list = (List<FamilyAccountInfo>) super.listForEc(sb.toString(), model, map);
		return list;
	}

	public void setResidentsFileService(ResidentsFileService residentsFileService) {
		this.residentsFileService = residentsFileService;
	}

	@Override
	public List<ConsultBabyInfo> getObjectListOfBabyByPage(PageSortModel model,
			Long accountId, ConsultBabyInfo consultBabyInfo,String page_id) {
		
		List<ConsultBabyInfo> list = new ArrayList<ConsultBabyInfo>();
		
		if(accountId!=null){
			FamilyAccountInfo info_query = super.get(accountId, FamilyAccountInfo.class);
			
			String babyIds = info_query.getBabyIds();
			
			
			String ispass_babyId = isPassBabyId(babyIds, page_id);
			
			
			StringBuilder sb = new StringBuilder();
			if(StringUtils.isNotBlank(babyIds)){
				sb.append("from ConsultBabyInfo c where 1=1 and c.id in ("+ babyIds +")");
			}else{
				list = new ArrayList<ConsultBabyInfo>();
				return list;
			}
			
			if(StringUtils.isNotBlank(ispass_babyId)){
				sb.append(" and c.id not in ("+ ispass_babyId +")");
			}
			Map<String,Object> map = new HashMap<String,Object>();
			
			if(consultBabyInfo!=null){
				if(StringUtils.isNotBlank(consultBabyInfo.getBabyName())){
					sb.append(" and c.babyName like '%"+consultBabyInfo.getBabyName()+"%'");
				}
			}
			
			list = (List<ConsultBabyInfo>) super.listForEc(sb.toString(), model, map);
		}
		
		return list;
	}
	
	private String isPassBabyId(String babyIds,String page_id){
		/*
		 * 排除已建立过档案的宝宝
		 */
		String ispass_babyId = "";
		switch(page_id){
		case "bt_child_health":
			List<PhPeopleBasicInfo> basicInfoList = super.list(" from PhPeopleBasicInfo c where c.consultBabyInfo.id in ("+ babyIds +")", -1, -1, null);
			for(PhPeopleBasicInfo info : basicInfoList){
				ispass_babyId+=info.getConsultBabyInfo().getId().toString()+",";
			}
			break;
		case "bt_child_physical":
			List<PhChildHealthExaminationRecord> phChildHealthExaminationRecordList = super.list(" from PhChildHealthExaminationRecord c where c.consultBabyInfo.id in ("+ babyIds +")", -1, -1, null);
			for(PhChildHealthExaminationRecord info : phChildHealthExaminationRecordList){
				ispass_babyId+=info.getConsultBabyInfo().getId().toString()+",";
			}
			break;
		case "bt_child_visit":
			List<PhNeonatalVisitRecord> phNeonatalVisitRecordList = super.list(" from PhNeonatalVisitRecord c where c.consultBabyInfo.id in ("+ babyIds +")", -1, -1, null);
			for(PhNeonatalVisitRecord info : phNeonatalVisitRecordList){
				ispass_babyId+=info.getConsultBabyInfo().getId().toString()+",";
			}
			break;
		}
		
		if(StringUtils.isNotBlank(ispass_babyId)) ispass_babyId=ispass_babyId.substring(0, ispass_babyId.length()-1);
		return ispass_babyId;
	}

	@Override
	public List<ConsultBabyInfo> getObjectListOfBabyByPage(Long id) {
		FamilyAccountInfo familyInfo = super.get(id, FamilyAccountInfo.class);
		
		List<ConsultBabyInfo> babyInfos = new ArrayList<ConsultBabyInfo>();
		
		StringBuilder sb = new StringBuilder("");
		sb.append("from ConsultBabyInfo c where 1=1 and c.userInfo.phone in (");
		if(StringUtils.isNotBlank(familyInfo.getFatherPhone())){
			sb.append("'"+familyInfo.getFatherPhone()+"',");
		}
		if(StringUtils.isNotBlank(familyInfo.getMotherPhone())){
			sb.append("'"+familyInfo.getMotherPhone()+"',");
		}
		sb.deleteCharAt(sb.length()-1).append(")");
		
		List<ConsultBabyInfo> list = super.list(sb.toString(), -1, -1, null);
		if(!list.isEmpty()){
			babyInfos.addAll(list);
		}
		List<ConsultBabyInfo> familyBaby = getFamilyBaby(id);
		if(!familyBaby.isEmpty()){
			babyInfos.addAll(familyBaby);
		}
		
		return babyInfos;
	}
	
	private List<ConsultBabyInfo> getFamilyBaby(Long accountId){
	
		FamilyAccountInfo info_query = super.get(accountId, FamilyAccountInfo.class);
		
		String babyIds = info_query.getBabyIds();
		
		List<ConsultBabyInfo> list = new ArrayList<ConsultBabyInfo>();
		if(StringUtils.isBlank(babyIds)) return list;
		
		StringBuilder sb = new StringBuilder();
		sb.append("from ConsultBabyInfo c where 1=1 and c.id in ("+ babyIds +")");
		list = super.list(sb.toString(), -1,-1, null);
		
		return list;
	}
}
