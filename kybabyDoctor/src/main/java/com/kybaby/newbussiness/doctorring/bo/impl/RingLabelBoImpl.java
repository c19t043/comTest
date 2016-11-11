package com.kybaby.newbussiness.doctorring.bo.impl;

import java.util.List;

import com.kybaby.domain.RingLabel;
import com.kybaby.newbussiness.doctorring.bo.RingLabelBo;
import com.kybaby.newbussiness.doctorring.dao.RingLabelDao;

/**
 * @ClassName:RingLabelBoImpl
 * @Description:医生圈事物管理接口实现
 * @author Hoolee
 * @date 2015年12月28日下午5:35:23
 */
public class RingLabelBoImpl implements RingLabelBo {

	RingLabelDao ringLabelDao;

	@Override
	public List<String> getSomePostInfoRingLabelNameList(long postInfoId) {
		return ringLabelDao.getSomePostInfoRingLabelNameList(postInfoId);
	}

	@Override
	public List<Object[]> getSomePostInfoRingLabelIdAndName(long postInfoId) {
		return ringLabelDao.getSomePostInfoRingLabelIdAndName(postInfoId);
	}

	@Override
	public List<RingLabel> getSomeCategoryRingLabelList(String ringCategory) {
		return ringLabelDao.getSomeCategoryRingLabelList(ringCategory);
	}
	
	public RingLabelDao getRingLabelDao() {
		return ringLabelDao;
	}

	public void setRingLabelDao(RingLabelDao ringLabelDao) {
		this.ringLabelDao = ringLabelDao;
	}

}
