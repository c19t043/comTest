package com.kybaby.newbussiness.doctorring.main;

import org.apache.commons.lang.StringUtils;

import com.kybaby.domain.PostInfo;
import com.kybaby.domain.RingLabel;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.doctorring.util.SessionManage;

public class DoctorRringManage extends NewBaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private String postInfoTitle;//前端传递的帖子的标题
	private String postInfoContent;//前端传递的帖子包括的内容
	private String ringTypeId;//前端传递的帖子所属医生圈的ID
	private String isAllowReply="Y";//帖子是否允许回复
	private String isTop="Y";//帖子是否置顶
	private String postInfoStatus="Y";//帖子的状态
	private long postInfoId;//待更新帖子的ID
	private String ringCategory="Y";
	private String ringLabelName;//前端传递的医生圈标签名称
	private String ringLabelStatus;//前端传递的医生圈标签状态
	private long ringLabelId;//前端传递的医生圈标签ID
	private String doctorRingLabelStr;//前端传递的添加医生圈帖子
	
	public String execute(){
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"......");
		long adminId=SessionManage.checkSomeSession("landUser");
		if(adminId==0){
			mes="未登录";
			return "fail";
		}
		if(action.equals("addNewPostInfo")){
			postInfoContent=postInfoContent.replace(oldUrl, imgSourceUrl);
			Long instanceId=postInfoBo.addNewPostInfoInstance(Long.valueOf(ringTypeId), postInfoTitle, postInfoContent, "", "", "", "", isAllowReply, startStr+adminId, "N",isTop,postInfoStatus);
			if(instanceId!=0){
				postInfoLabelBo.addNewPostInfoLabelList(instanceId, doctorRingLabelStr, splitStr);
			}
			mes="操作成功";
			return "success";
		}else if(action.equals("updatePostInfo")){
			PostInfo postInfoInstance=postInfoBo.getPostInfoInstanceById(postInfoId);
			if(postInfoInstance!=null){
				postInfoInstance.setTypeId(Long.valueOf(ringTypeId));
				postInfoInstance.setTitle(postInfoTitle);
				//postInfoContent=postInfoContent.replace(oldUrl, imgSourceUrl);
				postInfoInstance.setContent(postInfoContent);
				postInfoInstance.setPostImg("");
				postInfoInstance.setPostTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				postInfoInstance.setIsTop(isTop);
				postInfoInstance.setIsAllowReply(isAllowReply);
				postInfoInstance.setDataStatus(postInfoStatus);
				postInfoInstance.setIsAudit("N");
				postInfoInstance.setAuditStatus("Y");
				postInfoBo.updatePostInfoInstance(postInfoInstance);
				postInfoLabelBo.deleteSomePostInfoLabels(postInfoInstance.getId(), doctorRingLabelStr, splitStr);
				mes="操作成功";
			}else{
				mes="错误";
			}
			return "success";
		}else if(action.equals("addNewLabel")){
			boolean addFlag=ringLabelBo.addNewRingLabel(ringCategory, ringLabelName, ringLabelStatus);
			if(addFlag){
				mes="操作成功";
			}else{
				mes="重复";
			}
			return "success";
		}else if(action.equals("updateSomeLabel")){
			RingLabel ringLabelInstance=ringLabelBo.getRingLabelInstance(ringCategory, ringLabelName);
			if(ringLabelInstance==null||(ringLabelInstance!=null&&ringLabelInstance.getId()==ringLabelId)){
				ringLabelInstance=ringLabelBo.getRingLabelInstanceById(ringLabelId);
				if(ringLabelInstance!=null){
					ringLabelInstance.setRingCategory(ringCategory);
					ringLabelInstance.setRingLabelName(ringLabelName);
					ringLabelInstance.setRingLabelStatus(ringLabelStatus);
					ringLabelInstance.setRingLabelUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
					ringLabelBo.ringLabelInstanceUpdate(ringLabelInstance);
					if("Y".equals(ringLabelStatus)){//不再使用标签，将使用了该标签的帖子的相关管理取消掉，即删除post_info_label表中的记录
						postInfoLabelBo.deleteSomePostInfoLabelLinkes(ringLabelInstance.getId());
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

	public void setPostInfoTitle(String postInfoTitle) {
		this.postInfoTitle = postInfoTitle;
	}

	public void setPostInfoContent(String postInfoContent) {
		this.postInfoContent = postInfoContent;
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

	public void setPostInfoStatus(String postInfoStatus) {
		this.postInfoStatus = postInfoStatus;
	}

	public void setPostInfoId(long postInfoId) {
		this.postInfoId = postInfoId;
	}

	public void setRingCategory(String ringCategory) {
		this.ringCategory = ringCategory;
	}

	public void setRingLabelName(String ringLabelName) {
		this.ringLabelName = ringLabelName;
	}

	public void setRingLabelStatus(String ringLabelStatus) {
		this.ringLabelStatus = ringLabelStatus;
	}

	public void setRingLabelId(long ringLabelId) {
		this.ringLabelId = ringLabelId;
	}

	public void setDoctorRingLabelStr(String doctorRingLabelStr) {
		this.doctorRingLabelStr = doctorRingLabelStr;
	}
}
