package com.kybaby.newbussiness.mommyring.main;

import org.apache.commons.lang.StringUtils;

import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.mommyring.action.MommyRingAction;
import com.kybaby.newbussiness.mommyring.domain.MommyPostInfo;
import com.kybaby.newbussiness.mommyring.domain.MommyRingLabel;
import com.kybaby.newbussiness.mommyring.util.SessionManage;

public class MommyRringManage extends MommyRingAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private String mommyPostInfoTitle;//前端传递的帖子的标题
	private String mommyPostInfoContent;//前端传递的帖子包括的内容
	private String ringTypeId;//前端传递的帖子所属医生圈的ID
	private String isAllowReply="Y";//帖子是否允许回复
	private String isTop="Y";//帖子是否置顶
	private String mommyPostInfoStatus="Y";//帖子的状态
	private long mommyPostInfoId;//待更新帖子的ID
	private String ringCategory="Y";
	private String ringLabelName;//前端传递的医生圈标签名称
	private String ringLabelStatus;//前端传递的医生圈标签状态
	private long ringLabelId;//前端传递的医生圈标签ID
	private String userMommyRingLabelStr;//前端传递的添加医生圈帖子
	
	public String execute(){
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"......");
		long adminId=SessionManage.checkSomeSession("landUser");
		if(adminId==0){
			mes="未登录";
			return "fail";
		}
		if(action.equals("addNewMommyPostInfo")){
			mommyPostInfoContent=mommyPostInfoContent.replace(oldUrl, imgSourceUrl);
			Long instanceId=mommyPostInfoBo.addNewMommyPostInfoInstance(Long.valueOf(ringTypeId), mommyPostInfoTitle, mommyPostInfoContent, "", "", "", "", isAllowReply, startStr+adminId, "N",isTop,mommyPostInfoStatus);
			if(instanceId!=0){
				mommyPostInfoLabelBo.addNewMommyPostInfoLabelList(instanceId, userMommyRingLabelStr, splitStr);
			}
			mes="操作成功";
			return "success";
		}else if(action.equals("updateMommyPostInfo")){
			MommyPostInfo mommyPostInfoInstance=mommyPostInfoBo.getMommyPostInfoInstanceById(mommyPostInfoId);
			if(mommyPostInfoInstance!=null){
				mommyPostInfoInstance.setTypeId(Long.valueOf(ringTypeId));
				mommyPostInfoInstance.setTitle(mommyPostInfoTitle);
				//mommyPostInfoContent=mommyPostInfoContent.replace(oldUrl, imgSourceUrl);
				mommyPostInfoInstance.setContent(mommyPostInfoContent);
				mommyPostInfoInstance.setPostImg("");
				mommyPostInfoInstance.setPostTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				mommyPostInfoInstance.setIsTop(isTop);
				mommyPostInfoInstance.setIsAllowReply(isAllowReply);
				mommyPostInfoInstance.setDataStatus(mommyPostInfoStatus);
				mommyPostInfoInstance.setIsAudit("N");
				mommyPostInfoInstance.setAuditStatus("Y");
				mommyPostInfoBo.updateMommyPostInfoInstance(mommyPostInfoInstance);
				mommyPostInfoLabelBo.deleteSomeMommyPostInfoLabels(mommyPostInfoInstance.getId(), userMommyRingLabelStr, splitStr);
				mes="操作成功";
			}else{
				mes="错误";
			}
			return "success";
		}else if(action.equals("addNewLabel")){
			boolean addFlag=mommyRingLabelBo.addNewMommyRingLabel(ringCategory, ringLabelName, ringLabelStatus);
			if(addFlag){
				mes="操作成功";
			}else{
				mes="重复";
			}
			return "success";
		}else if(action.equals("updateSomeLabel")){
			MommyRingLabel ringLabelInstance=mommyRingLabelBo.getMommyRingLabelInstance(ringCategory, ringLabelName);
			if(ringLabelInstance==null||(ringLabelInstance!=null&&ringLabelInstance.getId()==ringLabelId)){
				ringLabelInstance=mommyRingLabelBo.getMommyRingLabelInstanceById(ringLabelId);
				if(ringLabelInstance!=null){
					ringLabelInstance.setRingCategory(ringCategory);
					ringLabelInstance.setRingLabelName(ringLabelName);
					ringLabelInstance.setRingLabelStatus(ringLabelStatus);
					ringLabelInstance.setRingLabelUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
					mommyRingLabelBo.ringLabelInstanceUpdate(ringLabelInstance);
					if("Y".equals(ringLabelStatus)){//不再使用标签，将使用了该标签的帖子的相关管理取消掉，即删除post_info_label表中的记录
						mommyPostInfoLabelBo.deleteSomeMommyPostInfoLabelLinkes(ringLabelInstance.getId());
					}
					mes="操作成功";
				}else{
					mes="错误";
				}
			}else{
				mes="重复";
			}
			return "success";
		}
		return "fail";
	}

	public String getMes() {
		return mes;
	}

	public void setMommyPostInfoTitle(String mommyPostInfoTitle) {
		this.mommyPostInfoTitle = mommyPostInfoTitle;
	}

	public void setMommyPostInfoContent(String mommyPostInfoContent) {
		this.mommyPostInfoContent = mommyPostInfoContent;
	}
	
	public void setRingTypeId(String ringTypeId) {
		this.ringTypeId = ringTypeId;
	}

	public void setIsAllowReply(String isAllowReply) {
		this.isAllowReply = isAllowReply;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public void setMommyPostInfoStatus(String mommyPostInfoStatus) {
		this.mommyPostInfoStatus = mommyPostInfoStatus;
	}

	public void setMommyPostInfoId(long mommyPostInfoId) {
		this.mommyPostInfoId = mommyPostInfoId;
	}

	public void setRingCategory(String ringCategory) {
		this.ringCategory = ringCategory;
	}

	public void setMommyRingLabelName(String ringLabelName) {
		this.ringLabelName = ringLabelName;
	}

	public void setMommyRingLabelStatus(String ringLabelStatus) {
		this.ringLabelStatus = ringLabelStatus;
	}

	public void setMommyRingLabelId(long ringLabelId) {
		this.ringLabelId = ringLabelId;
	}

	public void setDoctorMommyRingLabelStr(String userMommyRingLabelStr) {
		this.userMommyRingLabelStr = userMommyRingLabelStr;
	}
}
