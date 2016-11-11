package com.kybaby.newbussiness.mommyring.bo;

import java.util.List;

import com.kybaby.newbussiness.mommyring.domain.MommyPostInfo;

/**
 * @ClassName:MommyPostInfoBo
 * @Description:用户发帖事物管理接口
 * @author Hoolee
 * @date 2015年12月13日下午1:52:06
 */
public interface MommyPostInfoBo {

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某一个医生圈下的帖子列表
	 * @data: 2015年12月13日下午2:25:50
	 * @param categoryId 医生圈或者子类的ID
	 * @return 某一个医生圈下的帖子列表
	 */
	List<MommyPostInfo> getSomeCategoryMommyPostInfo(long categoryId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过帖子的ID获取到帖子的实例
	 * @data: 2015年12月13日下午5:22:12
	 * @param mommyPostInfoId 帖子的ID
	 * @return 帖子的实例
	 */
	MommyPostInfo getMommyPostInfoInstanceById(long mommyPostInfoId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生圈发帖的实例
	 * @data: 2015年12月15日下午4:48:01
	 * @param mommyPostInfoInstance 待更新的医生圈帖子实例
	 */
	void updateMommyPostInfoInstance(MommyPostInfo mommyPostInfoInstance);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加一个新的医生发帖的实例
	 * @data: 2015年12月16日上午11:00:29
	 * @param typeId 所属医生圈的ID
	 * @param title 发帖的标题
	 * @param content 发帖的内容
	 * @param postImg 发帖的图片名称
	 * @param recordingVoiceUrl 记录声音的路径
	 * @param externalVideoUrl 外部视频链接地址
	 * @param uploadVideoUrl 上传视频链接地址
	 * @param isAllowReply 是否允许回复
	 * @param createPerson 发帖人的ID
	 * @param isAudit 是否开启审核
	 * @param isTop 是否置顶
	 * @param dataStatus 帖子的状态
	 */
	long addNewMommyPostInfoInstance(long typeId,String title,String content,String postImg,String recordingVoiceUrl,String externalVideoUrl,String uploadVideoUrl,String isAllowReply,String createPerson,String isAudit,String isTop,String dataStatus);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取平台上所有的发帖
	 * @data: 2015年12月23日下午4:40:19
	 * @return 平台上的所有发帖
	 */
	List<MommyPostInfo> 	getAllMommyPostInfo();
}
