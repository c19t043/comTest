package com.kybaby.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.bo.SymptomTagBo;
import com.kybaby.dao.SymptomTagDao;
import com.kybaby.domain.SymptomTag;

/**
 * @ClassName:SymptomTagBoImpl
 * @Description:症状标签事物管理接口实现
 * @author Hoolee
 * @date 2015年10月12日下午3:28:35
 */
public class SymptomTagBoImpl implements SymptomTagBo {

	SymptomTagDao symptomTagDao;
	
	public List<SymptomTag> getSymptomTagInstanceList(String tagIds) {
		List<SymptomTag> list=new ArrayList<SymptomTag>();
		String[] tagId=tagIds.split("::");
		for(int i =0;i<tagId.length;i++){
			long id=Long.valueOf(tagId[i]);
			SymptomTag symptomTagInstance=symptomTagDao.getSymptomTagInstanceById(id);
			if(symptomTagInstance!=null){
				list.add(symptomTagInstance);
			}
		}
		return list;
	}

	public List<SymptomTag> getAllSymptomTag() {
		return symptomTagDao.getAllSymptomTag();
	}

	public void updateSymptomStatusByIds(String tagIds) {
		String[] tagIdArr=tagIds.split("::");
		for(int i=0;i<tagIdArr.length;i++){
			long tagId=Long.valueOf(tagIdArr[i]);
			SymptomTag symptomTag=symptomTagDao.getSymptomTagInstanceById(tagId);
			if(symptomTag!=null){
				if(symptomTag.getHitCount()==null){
					symptomTag.setHitCount(0L);
				}
				symptomTag.setHitCount(symptomTag.getHitCount()+1);
				symptomTagDao.updateSymptomTagInstance(symptomTag);
			}
		}
	}
	
	public SymptomTagDao getSymptomTagDao() {
		return symptomTagDao;
	}

	public void setSymptomTagDao(SymptomTagDao symptomTagDao) {
		this.symptomTagDao = symptomTagDao;
	}

}
