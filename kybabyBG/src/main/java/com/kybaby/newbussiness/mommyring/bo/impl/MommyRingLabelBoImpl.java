package com.kybaby.newbussiness.mommyring.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.mommyring.bo.MommyRingLabelBo;
import com.kybaby.newbussiness.mommyring.dao.MommyRingLabelDao;
import com.kybaby.newbussiness.mommyring.domain.MommyRingLabel;

/**
 * @ClassName:MommyRingLabelBoImpl
 * @Description:医生圈事物管理接口实现
 * @author Hoolee
 * @date 2015年12月28日下午5:35:23
 */
public class MommyRingLabelBoImpl implements MommyRingLabelBo {

	MommyRingLabelDao ringLabelDao;

	public List<MommyRingLabel> getSomeCategoryMommyRingLabelList(String ringCategory) {
		return ringLabelDao.getSomeCategoryMommyRingLabelList(ringCategory);
	}

	public boolean addNewMommyRingLabel(String ringCategory, String ringLabelName,String ringLabelStatus) {
		MommyRingLabel ringLabelInstance=ringLabelDao.getMommyRingLabelInstance(ringCategory, ringLabelName);
		boolean flag=false;
		if(ringLabelInstance==null){
			ringLabelInstance=new MommyRingLabel();
			ringLabelInstance.setRingCategory(ringCategory);
			ringLabelInstance.setRingLabelName(ringLabelName);
			ringLabelInstance.setRingLabelStatus(ringLabelStatus);
			ringLabelInstance.setRingLabelClickNum(0L);
			String ringLabelCreatTime=DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
			ringLabelInstance.setRingLabelCreatTime(ringLabelCreatTime);
			ringLabelInstance.setRingLabelUpdateTime(ringLabelCreatTime);
			ringLabelDao.addNewMommyRingLabelInstance(ringLabelInstance);
			flag=true;
		}
		return flag;
	}

	public MommyRingLabel getMommyRingLabelInstance(String ringCategory,String ringLabelName) {
		return ringLabelDao.getMommyRingLabelInstance(ringCategory, ringLabelName);
	}

	public MommyRingLabel getMommyRingLabelInstanceById(long labelId) {
		return ringLabelDao.getMommyRingLabelInstanceById(labelId);
	}

	public void ringLabelInstanceUpdate(MommyRingLabel ringLabelInstance) {
		ringLabelDao.ringLabelInstanceUpdate(ringLabelInstance);
	}

	public MommyRingLabelDao getMommyRingLabelDao() {
		return ringLabelDao;
	}

	public void setMommyRingLabelDao(MommyRingLabelDao ringLabelDao) {
		this.ringLabelDao = ringLabelDao;
	}

}
