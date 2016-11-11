package com.kybaby.newbussiness.doctorring.bo.impl;

import java.util.List;

import com.kybaby.domain.RingLabel;
import com.kybaby.newbussiness.doctorring.bo.RingLabelBo;
import com.kybaby.newbussiness.doctorring.dao.RingLabelDao;
import com.kybaby.newbussiness.doctorring.util.DateManage;

/**
 * @ClassName:RingLabelBoImpl
 * @Description:医生圈事物管理接口实现
 * @author Hoolee
 * @date 2015年12月28日下午5:35:23
 */
public class RingLabelBoImpl implements RingLabelBo {

	RingLabelDao ringLabelDao;

	public List<RingLabel> getSomeCategoryRingLabelList(String ringCategory) {
		return ringLabelDao.getSomeCategoryRingLabelList(ringCategory);
	}

	public boolean addNewRingLabel(String ringCategory, String ringLabelName,String ringLabelStatus) {
		RingLabel ringLabelInstance=ringLabelDao.getRingLabelInstance(ringCategory, ringLabelName);
		boolean flag=false;
		if(ringLabelInstance==null){
			ringLabelInstance=new RingLabel();
			ringLabelInstance.setRingCategory(ringCategory);
			ringLabelInstance.setRingLabelName(ringLabelName);
			ringLabelInstance.setRingLabelStatus(ringLabelStatus);
			ringLabelInstance.setRingLabelClickNum(0L);
			String ringLabelCreatTime=DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
			ringLabelInstance.setRingLabelCreatTime(ringLabelCreatTime);
			ringLabelInstance.setRingLabelUpdateTime(ringLabelCreatTime);
			ringLabelDao.addNewRingLabelInstance(ringLabelInstance);
			flag=true;
		}
		return flag;
	}

	public RingLabel getRingLabelInstance(String ringCategory,String ringLabelName) {
		return ringLabelDao.getRingLabelInstance(ringCategory, ringLabelName);
	}

	public RingLabel getRingLabelInstanceById(long labelId) {
		return ringLabelDao.getRingLabelInstanceById(labelId);
	}

	public void ringLabelInstanceUpdate(RingLabel ringLabelInstance) {
		ringLabelDao.ringLabelInstanceUpdate(ringLabelInstance);
	}

	public RingLabelDao getRingLabelDao() {
		return ringLabelDao;
	}

	public void setRingLabelDao(RingLabelDao ringLabelDao) {
		this.ringLabelDao = ringLabelDao;
	}

}
