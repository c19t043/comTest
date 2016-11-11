package com.kybaby.newbussiness.mommyring.bo.impl;

import java.util.List;

import com.kybaby.newbussiness.mommyring.bo.MommyRingLabelBo;
import com.kybaby.newbussiness.mommyring.dao.MommyRingLabelDao;
import com.kybaby.newbussiness.mommyring.domain.MommyRingLabel;

/**
 * @ClassName:RingLabelBoImpl
 * @Description:医生圈事物管理接口实现
 * @author Hoolee
 * @date 2015年12月28日下午5:35:23
 */
public class MommyRingLabelBoImpl implements MommyRingLabelBo {

	MommyRingLabelDao mommyRingLabelDao;

	public List<String> getSomePostInfoRingLabelNameList(long postInfoId) {
		return mommyRingLabelDao.getSomePostInfoRingLabelNameList(postInfoId);
	}

	public List<Object[]> getSomePostInfoRingLabelIdAndName(long postInfoId) {
		return mommyRingLabelDao.getSomePostInfoRingLabelIdAndName(postInfoId);
	}

	public List<MommyRingLabel> getSomeCategoryRingLabelList(String ringCategory) {
		return mommyRingLabelDao.getSomeCategoryRingLabelList(ringCategory);
	}
	
	public MommyRingLabelDao getMommyRingLabelDao() {
		return mommyRingLabelDao;
	}

	public void setMommyRingLabelDao(MommyRingLabelDao mommyRingLabelDao) {
		this.mommyRingLabelDao = mommyRingLabelDao;
	}

}
