package com.kybaby.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.bo.MajorBo;
import com.kybaby.dao.MajorDao;
import com.kybaby.domain.DoctorGoodField;

/**
 * @ClassName:MajorBoImpl
 * @Description:专长方向事物管理接口实现
 * @author Hoolee
 * @date 2015年10月6日上午10:41:37
 */
public class MajorBoImpl implements MajorBo {
	
	MajorDao majorDao;
	
	public List<String> getMajorNameListByIdStr(String idStr) {
		List<String> majorNameList=new ArrayList<String>();
		String[] majorIdsList=idStr.split("::");
		for(int i =0;i<majorIdsList.length;i++){
			long majorId=Long.valueOf(majorIdsList[i]);
			String majorName=majorDao.getMajorNameById(majorId);
			if(majorName!=null){
				majorNameList.add(majorName);
			}
		}
		return majorNameList;
	}

	public MajorDao getMajorDao() {
		return majorDao;
	}

	public void setMajorDao(MajorDao majorDao) {
		this.majorDao = majorDao;
	}

	@Override
	public List<String> getGoodFieldNameListByIdStr(String idStr) {
		if(idStr == null || "".equals(idStr)){
			return null;
		}
		List<String> goodFieldNameList=new ArrayList<String>();
		String[] goodFieldIdsList=idStr.split("::");
		for(int i =0;i<goodFieldIdsList.length;i++){
			long id=Long.valueOf(goodFieldIdsList[i]);
			DoctorGoodField dg = majorDao.getDoctorGoodFieldById(id);
			String goodFieldName = dg==null?"":dg.getName();
			goodFieldNameList.add(goodFieldName);
		}
		return goodFieldNameList;
	}

}
