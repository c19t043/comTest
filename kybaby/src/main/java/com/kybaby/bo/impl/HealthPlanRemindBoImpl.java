package com.kybaby.bo.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kybaby.bo.HealthPlanRemindBo;
import com.kybaby.dao.HealthPlanRemindDao;
import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlanRemind;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctoroperateflow.domain.HealthPlanRemindIssued;
import com.kybaby.newbussiness.doctoroperateflow.fo.HealthPlanFo;
import com.kybaby.util.CalculationMethod;

/**
 * @ClassName:HealthPlanRemindBoImpl
 * @Description:健康提醒事物管理接口实现
 * @author Hoolee
 * @date 2015年9月29日下午3:34:36
 */
public class HealthPlanRemindBoImpl implements HealthPlanRemindBo {
	
	HealthPlanRemindDao healthPlanRemindDao;

	public List<HealthPlanRemind> todayHealthPlanRemind(long userId,String strDate) {
		return healthPlanRemindDao.todayHealthPlanRemind(userId, strDate);
	}

	public long getSomeHealthPathAmount(long userId, String orderNum,long pathId) {
		return 0;
	}

	public List<String> getDateStr(long userId, String orderNum) {
		List<HealthPlanRemind> allRemaind=healthPlanRemindDao.getDateStr(userId, orderNum);
		if(allRemaind!=null){
			List<String> dateStrResult=new ArrayList<String>();
			for(int i =0;i<allRemaind.size();i++){
				HealthPlanRemind remaind=allRemaind.get(i);
				String executeDateList=remaind.getExecuteDateList();
				String[] dateArr=executeDateList.split(",");
				//List<String> dateList=Arrays.asList(dateArr);
				//dateStrResult.addAll(dateList);
				for(int j=0;j<dateArr.length;j++){
					dateStrResult.add(dateArr[j]);
				}
			}
			//上面确定所有的日期集合，下面去重
			if(dateStrResult!=null){
				for(int i =0;i<dateStrResult.size();i++){
					boolean flag=false;
					for(int j =i+1;j<dateStrResult.size();j++){
						String dateI=dateStrResult.get(i);
						String dateJ=dateStrResult.get(j);
						if(dateI.equals(dateJ)){
							flag=true;
							break;
						}
					}
					if(flag){
						dateStrResult.remove(i);
						i--;
					}
				}
				
				//下面按照时间先后进行排序
				for(int i=0;i<dateStrResult.size();i++){
					for(int j=i+1;j<dateStrResult.size();j++){
						java.util.Date dateI=Date.valueOf(dateStrResult.get(i));
						java.util.Date dateJ =Date.valueOf(dateStrResult.get(j));
						if(dateI.compareTo(dateJ)<0){
							String replaceDate=dateStrResult.get(i);
							dateStrResult.set(i, dateStrResult.get(j));
							dateStrResult.set(j, replaceDate);
						}
					}
				}
				return dateStrResult;
			}
			return null;
		}
		return null;
	}

	public String getSomeDateResult(String orderNum,long userId, long planId, long pathId,String someDate) {
		HealthPlanRemind planRemain=healthPlanRemindDao.getSomeDateHealthPlanReamin(orderNum, userId, planId, pathId);
		if(planRemain!=null){
			String executeDateList=planRemain.getExecuteDateList();
			String executeResult=planRemain.getExecuteResult();
			
			//updated by zhong at 2015-10-23 
			//String[] dateList=executeDateList.split("::");
			String[] dateList=executeDateList.split(",");
			
			String[] resultList=executeResult.split("::");
			for(int i=0;i<dateList.length;i++){
				if(dateList[i].equals(someDate)){
					String result=resultList[i];
					return result;
				}
			}
			return null;
		}
		return null;
	}
	
	public void confirmTodayRemain(long userId, String orderNum, long pathId,long planId) {
		HealthPlanRemind planRemain=healthPlanRemindDao.getSomeDateHealthPlanReamin(orderNum, userId, planId, pathId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date rightNow=new Date(System.currentTimeMillis());
		String dateNow=sdf.format(rightNow);
		if(planRemain!=null){
			String executeDateList=planRemain.getExecuteDateList();
			String executeResult=planRemain.getExecuteResult();
			
			//updated by zhong at 2015-10-25
			//String[] dateList=executeDateList.split("::");
			String[] dateList=executeDateList.split(",");
			String[] resultList=executeResult.split("::");
			
			for(int i=0;i<dateList.length;i++){
				if(dateList[i].equals(dateNow)){
					resultList[i]="Y";
				}
			}
			String result="";
			for(int i =0;i<resultList.length;i++){
				result=result+resultList[i]+"::";
			}
			result=result.substring(0, result.length()-2);
			planRemain.setExecuteResult(result);
			healthPlanRemindDao.updateSomeRemain(planRemain);
		}
	}

	public List<Long> getSomeOrderNumPlanIdList(String orderNum, long userId) {
		return healthPlanRemindDao.getSomeOrderNumPlanIdList(orderNum, userId);
	}

	public List<Long> getSomeHealthPlanPathIdList(String orderNum, long userId,long healthPlanId) {
		return healthPlanRemindDao.getSomeHealthPlanPathIdList(orderNum, userId, healthPlanId);
	}

	public long getSomePathAmount(long userId, String orderNum, long pathId,long planId) {
		return healthPlanRemindDao.getSomePathAmount(userId, orderNum, pathId, planId);
	}
	
	public List<Long> getTodaySomeOrderNumPlanIdList(String orderNum,
			long userId, String strDate) {
		List<HealthPlanRemind> remain=healthPlanRemindDao.getDateStr(userId, orderNum);
		if(remain!=null){
			List<Long> planIdList=new ArrayList<Long>();
			for(int i=0;i<remain.size();i++){
				boolean flag=false;
				HealthPlanRemind someRemain=remain.get(i);
				String excuteDateStr=someRemain.getExecuteDateList();
				String[] excuteDateArr=excuteDateStr.split(",");
				for(int j=0;j<excuteDateArr.length;j++){
					String excuteDate=excuteDateArr[j];
					if(excuteDate.equals(strDate)){
						flag=true;
						break;
					}
				}
				if(flag){
					planIdList.add(someRemain.getHealthPlanId());
				}
			}
			return planIdList;
		}
		return null;
	}

	public HealthPlanRemindDao getHealthPlanRemindDao() {
		return healthPlanRemindDao;
	}

	public void setHealthPlanRemindDao(HealthPlanRemindDao healthPlanRemindDao) {
		this.healthPlanRemindDao = healthPlanRemindDao;
	}

	@Override
	public List<HealthPlanFo> getHealthPlanFoList(UserInfo userInfo) {
		List<HealthPlanRemindIssued> list = this.healthPlanRemindDao.getHealthPlanRemindIssuedByUser(userInfo);
		if(list == null) return null;
		Map<String,Object[]> map = new HashMap<>();
		List<HealthPlanFo> foList = new ArrayList<HealthPlanFo>();
		for(HealthPlanRemindIssued hpri : list){
			//得到月龄
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				int month = CalculationMethod.getMonthSpace(userInfo.getBirthday(), sdf.format(hpri.getCreateTime()));
				map.put(hpri.getOrderNum(), new Object[]{month,sdf.format(hpri.getCreateTime())});
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		for (Map.Entry<String,Object[]> entry : map.entrySet()) { 
			HealthPlanFo fo = new HealthPlanFo();
			fo.setOrderNumber(entry.getKey());
			fo.setMonthAge(entry.getValue()[0]==null?null:Integer.valueOf(entry.getValue()[0].toString()));
			fo.setCerateDate(entry.getValue()[1].toString());
			foList.add(fo);
		}
		return foList;
	}

	@Override
	public List<HealthPath> getHealthPathListByOrderNum(String orderNum) {
		return this.healthPlanRemindDao.getHealthPathListByOrderNum(orderNum);
	}


}
