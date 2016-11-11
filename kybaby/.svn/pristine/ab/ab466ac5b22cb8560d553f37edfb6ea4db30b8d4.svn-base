package com.kybaby.newbussiness.mommyring.bo;

import java.util.List;

import com.kybaby.newbussiness.mommyring.domain.MommyPostInfo;

/**
 * @ClassName:PostInfoBo
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
	List<MommyPostInfo> getSomeCategoryPostInfo(long categoryId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过帖子的ID获取到帖子的实例
	 * @data: 2015年12月13日下午5:22:12
	 * @param postInfoId 帖子的ID
	 * @return 帖子的实例
	 */
	MommyPostInfo getPostInfoInstanceById(long postInfoId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更新医生圈发帖的实例
	 * @data: 2015年12月15日下午4:48:01
	 * @param postInfoInstance 待更新的医生圈帖子实例
	 */
	void updatePostInfoInstance(MommyPostInfo postInfoInstance);
	
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
	 */
	long addNewPostInfoInstance(long typeId,String title,String content,String postImg,String recordingVoiceUrl,String externalVideoUrl,String uploadVideoUrl,String isAllowReply,String createPerson,String isAudit);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过管理员的ID获取到管理员的名称
	 * @data: 2015年12月22日下午3:54:03
	 * @param adminId 管理员的ID
	 * @return 管理员的名称
	 */
	String getAdminNameById(long adminId);
	
	/**
	 * 
	 * @author xiongchao
	 * @Discription:通过用户ID获取到该用户的发帖列表
	 * @data: 2016年01月21日下午3:54:03
	 * @param userId 用户ID
	 * @return 该用户的发帖列表
	 */
	List<MommyPostInfo> getMySendPostInfoList(Long userId);
	
	/**
	 * 
	 * @author xiongchao
	 * @Discription:通过用户ID获取到该用户的回复帖子列表
	 * @data: 2016年01月21日下午3:54:03
	 * @param userId 用户ID
	 * @return 该用户的回复帖子列表
	 */
	List<MommyPostInfo> getMyReplyPostInfoList(Long userId);
}
