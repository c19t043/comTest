package com.kybaby.newbussiness.userconsult.bo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.userconsult.bo.ConsultDoctorInfoService;
import com.kybaby.newbussiness.userconsult.dao.ConsultDoctorInfoDao;
import com.kybaby.newbussiness.userconsult.domain.ConsultBabySet;
import com.kybaby.newbussiness.userconsult.domain.ConsultDoctorInfo;
import com.kybaby.newbussiness.userconsult.domain.ConsultOrderInfo;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.MyMath;

public class ConsultDoctorInfoServiceImpl implements ConsultDoctorInfoService{
	private ConsultDoctorInfoDao consultDoctorInfoDao;

	public ConsultDoctorInfoDao getConsultDoctorInfoDao() {
		return consultDoctorInfoDao;
	}

	public void setConsultDoctorInfoDao(ConsultDoctorInfoDao consultDoctorInfoDao) {
		this.consultDoctorInfoDao = consultDoctorInfoDao;
	}

	@Override
	public List<ConsultDoctorInfo> getConsultDoctorInfoList(
			ConsultDoctorInfo consultDoctorInfo,DoctorInfo doctorInfo,String sortWay) {
		List<ConsultDoctorInfo> list = consultDoctorInfoDao.getConsultDoctorInfoList(consultDoctorInfo, doctorInfo);
		if(list != null){
			if("智能排序".equals(sortWay)){
				//按是否推荐排序
				List<ConsultDoctorInfo> recommendList = new ArrayList<>();
				List<ConsultDoctorInfo> noRecommendList = new ArrayList<>();
				for(ConsultDoctorInfo cdi : list){
					if("Y".equals(cdi.getIsOnline())){
						recommendList.add(cdi);
					}else if("Y".equals(cdi.getIsRecommend())){
						recommendList.add(cdi);
					}else{
						noRecommendList.add(cdi);
					}
				}
				//Collections.shuffle(recommendList);//随机排序
				Collections.shuffle(noRecommendList);//随机排序
				list = new ArrayList<>();
				list.addAll(recommendList);
				list.addAll(noRecommendList);
				//按是否在线排序
//				Collections.sort(list, new Comparator<ConsultDoctorInfo>() {  
//		            public int compare(ConsultDoctorInfo arg0, ConsultDoctorInfo arg1) {  
//		                int online0 =   "Y".equals(arg0.getDoctorInfo().getIsLogin())?1:0;
//		                int online1 =   "N".equals(arg1.getDoctorInfo().getIsLogin())?1:0;
//		                if (online0 < online1) {  
//		                    return -1;  
//		                } else if (online0 > online1) {  
//		                    return 1;  
//		                } else {  
//		                    return 0;  
//		                }  
//		            }  
//		        }); 
			}else if("评价最高".equals(sortWay)){
				Collections.sort(list, new Comparator<ConsultDoctorInfo>() {  
		            public int compare(ConsultDoctorInfo arg0, ConsultDoctorInfo arg1) {  
		                long star0 =   consultDoctorInfoDao.getSumConsultServiceStar(arg0.getDoctorInfo(), ConstantManage.EVALUATE_CHARGE);
		                long star1 =   consultDoctorInfoDao.getSumConsultServiceStar(arg1.getDoctorInfo(), ConstantManage.EVALUATE_CHARGE);
		                if (star0 < star1) {  
		                    return -1;  
		                } else if (star0 > star1) {  
		                    return 1;  
		                } else {  
		                    return 0;  
		                }  
		            }  
		        }); 
			}
			
		}
		return list;
	}

	@Override
	public Long saveOrUpdateConsultOrderInfo(ConsultOrderInfo consultOrderInfo) {
		return consultDoctorInfoDao.saveOrUpdateConsultOrderInfo(consultOrderInfo);
	}

	@Override
	public ConsultOrderInfo getConsultOrderInfoById(Long id) {
		return consultDoctorInfoDao.getConsultOrderInfoById(id);
	}

	@Override
	public ConsultDoctorInfo getConsultDoctorInfoById(Long consultDoctorId,Long doctorId) {
		return consultDoctorInfoDao.getConsultDoctorInfoById( consultDoctorId, doctorId);
	}

	@Override
	public void addConsultBabySet(ConsultBabySet consultBabySet) {
		consultDoctorInfoDao.addConsultBabySet(consultBabySet);
	}

	@Override
	public List<Long> getConsultDoctorIdsByUser(Long userId,String userType,String isEnd) {
		return consultDoctorInfoDao.getConsultDoctorIdsByUser(userId, userType,isEnd);
	}

	@Override
	public long countNewMessage(Long userId, Long doctorId, String time,
			String userType) {
		return consultDoctorInfoDao.countNewMessage(userId, doctorId, time, userType);
	}

	@Override
	public void closeConsultOrderPromptTask() {
		ConsultOrderInfo consultOrderInfo = new ConsultOrderInfo();
		consultOrderInfo.setOrderStatus(ConstantManage.HAS_PAYMENT);
		consultOrderInfo.setEffectiveEndTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
		List<ConsultOrderInfo> list=consultDoctorInfoDao.getConsultOrderInfoList(consultOrderInfo);
		if(list != null){
			for(ConsultOrderInfo co : list){
				co.setOrderStatus(ConstantManage.WAIT_EVALUATED);
				this.consultDoctorInfoDao.saveOrUpdateConsultOrderInfo(co);
				//修改医生余额信息
				ConsultDoctorInfo cd = co.getConsultDoctorInfo();
				DoctorInfo doctorInfo = this.consultDoctorInfoDao.getDoctorInfoById(cd.getDoctorInfo().getId());
				//计算薪酬
				double baodi =cd.getConsultMoney()  * cd.getConsultCommission();
				baodi = MyMath.round(baodi, 2);
				consultDoctorInfoDao.addNewDoctorAccount(doctorInfo.getId(), baodi, "+", "在线咨询服务费");
				//医生余额
				double doctorBalance = doctorInfo.getDoctorBalance() + baodi;
				doctorBalance = MyMath.round(doctorBalance, 2);
				doctorInfo.setDoctorBalance(doctorBalance);
				//更新医生余额信息
				this.consultDoctorInfoDao.updateDoctor(doctorInfo);
				//关闭聊天对话
				consultDoctorInfoDao.closeConsultOrderPromptTask(co.getId());
			}
		}
	}

	@Override
	public List<ConsultOrderInfo> getConsultOrderInfoList(UserInfo userInfo) {
		ConsultOrderInfo consultOrderInfo = new ConsultOrderInfo();
		consultOrderInfo.setUserInfo(userInfo);
		return consultDoctorInfoDao.getConsultOrderInfoList(consultOrderInfo);
	}
}
