package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.CaseClip;

/**
 * @ClassName:CaseClipBo
 * @Description:病历夹事物管理接口
 * @author Hoolee
 * @date 2015年9月22日下午11:21:06
 */
public interface CaseClipBo {

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某个用户的病历夹列表
	 * @data: 2015年9月22日下午11:38:59
	 * @param userId 用户的ID
	 * @return 用户的病历夹列表
	 */
	List<CaseClip> getSomeUserCaseClipList(long userId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:添加新的病历夹
	 * @data: 2015年9月22日下午11:41:29
	 * @param userId 用户的ID
	 * @param picType 上传的图片类型
	 * @param picUrl 上传图片的地址
	 * @param symptomTagIds 症状标签ID组成的字符串
	 */
	void addNewCaseClip(long userId,String picType,String picUrl,String symptomTagIds);
}
