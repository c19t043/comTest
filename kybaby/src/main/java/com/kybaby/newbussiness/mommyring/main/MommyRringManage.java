package com.kybaby.newbussiness.mommyring.main;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kybaby.newbussiness.mommyring.action.MommyRingAction;
import com.kybaby.newbussiness.mommyring.domain.MommyPostInfo;
import com.kybaby.newbussiness.mommyring.domain.MommyPostReply;
import com.kybaby.newbussiness.mommyring.domain.MommyRingType;
import com.kybaby.newbussiness.mommyring.util.SessionManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.EncryptUtil;

/**
 * @ClassName:DoctorRringManage
 * @Description: 医生圈管理相关的类
 * @author Hoolee
 * @date 2015年12月13日上午11:46:59
 */
public class MommyRringManage extends MommyRingAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private long ringTypeId;//订阅的医生圈ID
	private long postInfoId;//前端传递的点赞/取消点赞的帖子ID
	private String postContent;//前端传递的帖子的回复内容
	private String dir="../kybabyBG/admin/images";
	private String titleContent;//医生发表帖子的标题
	private String textContent;//医生发表帖子的内容
	private String imgContent;//医生发表帖子中的图片内容
	private long categoryId;//医生发表帖子所属的医生圈ID
	private long replyId=0L;//回复帖子的ID
	private String doctorRingLabelStr;//前端传递的添加医生圈帖子
	
	public String execute(){
		System.out.println("============execute==============");
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"......");
		if(action.equals("recommend")){
			long userId=SessionManage.checkSomeSession("userId");
			if(userId>0){//用户已经登录
				mommySubscribeUserBo.addSomeUserSubscribeUser(userId, ringTypeId);
				MommyRingType doctprRingTypeInstance=mommyRingTypeBo.getSomeMommyRingTypeInstance(ringTypeId);
				if(doctprRingTypeInstance!=null){
					Long subscribeNum=doctprRingTypeInstance.getSubscribeNum();
					if(subscribeNum==null){
						subscribeNum=0L;
					}
					subscribeNum++;
					doctprRingTypeInstance.setSubscribeNum(subscribeNum);
					mommyRingTypeBo.updateSomeMommyRingType(doctprRingTypeInstance);
				}
				mes="操作成功";
				return "success";
			}
			mes="未登录";//前端提醒用户登录，并且跳转到登录页面
			return "success";
		} else if(action.equals("cancle")){
			long userId=SessionManage.checkSomeSession("userId");
			if(userId>0){//用户已经登录
				if(mommySubscribeUserBo.deleteSomeUserSubscribeUser(userId, ringTypeId)){
					MommyRingType doctprRingTypeInstance=mommyRingTypeBo.getSomeMommyRingTypeInstance(ringTypeId);
					if(doctprRingTypeInstance!=null){
						Long subscribeNum=doctprRingTypeInstance.getSubscribeNum();
						if(subscribeNum>0){
							subscribeNum--;
						}else{
							subscribeNum=0L;
						}
						doctprRingTypeInstance.setSubscribeNum(subscribeNum);
						mommyRingTypeBo.updateSomeMommyRingType(doctprRingTypeInstance);
					}
					mes="操作成功";
				}else{
					mes="取消错误";
				}
				return "success";
			}
			mes="未登录";//前端提醒用户登录，并且跳转到登录页面
			return "success";
		} else if(action.equals("giveStar")){//给帖子点赞
			long userId=SessionManage.checkSomeSession("userId");
			if(userId>0){
				//预留一个接口，实现后旗对点赞的规则进行扩展
				if(this.isCanGiveStar(postInfoId, userId)){
					//允许点赞,添加一条该用户的点赞记录
					mommyPostReplyBo.addNewPostReply(postInfoId, postInfoId, "Y", userId, "N", getIsAudit(), "Y", "");
					//点赞完成之后，就对该医生圈的
					MommyPostInfo postInfoInstance=mommyPostInfoBo.getPostInfoInstanceById(postInfoId);
					if(postInfoInstance!=null){
						Long pointNum=postInfoInstance.getPointNum();
						if(pointNum==null){
							pointNum=0L;
						}
						pointNum++;
						postInfoInstance.setPointNum(pointNum);
						mommyPostInfoBo.updatePostInfoInstance(postInfoInstance);
					}
					mes="操作成功";
					return "success";
				}
				mes="错误";
				return "fail";
			}
			mes="未登录";
			return "success";
		} else if(action.equals("cancleStar")){//取消帖子点赞
			long userId=SessionManage.checkSomeSession("userId");
			if(userId>0){
				//用户已经登录，将用户的点赞回复记录删除
				if(mommyPostReplyBo.deleteSomePostReply(postInfoId, userId)){
					MommyPostInfo postInfoInstance=mommyPostInfoBo.getPostInfoInstanceById(postInfoId);
					if(postInfoInstance!=null){
						Long pointNum=postInfoInstance.getPointNum();
						if(pointNum>0){
							pointNum--;
						}else{
							pointNum=0L;
						}
						postInfoInstance.setPointNum(pointNum);
						mommyPostInfoBo.updatePostInfoInstance(postInfoInstance);
					}
					mes="操作成功";
				}else{
					mes="取消错误";
				}
			}else{
				mes="未登录";
			}
			return  "success";
		} else if(action.equals("postReply")){
			long userId=SessionManage.checkSomeSession("userId");
			if(userId>0){
				if(this.doctorAudit(userId)){
					mommyPostReplyBo.addNewPostReply(postInfoId, replyId, "N", userId, getIsAllowReply(), getIsAudit(), "Y", postContent);
					MommyPostInfo postInfoInstance=mommyPostInfoBo.getPostInfoInstanceById(postInfoId);
					if(postInfoInstance!=null){
						String lastReplyTime=DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
						postInfoInstance.setLastReplyTime(lastReplyTime);
						postInfoInstance.setLastReplyPerson(userId);
						Long replyNum=postInfoInstance.getReplyNum();
						if(replyNum==null){
							replyNum=0L;
						}
						replyNum++;
						postInfoInstance.setReplyNum(replyNum);
						mommyPostInfoBo.updatePostInfoInstance(postInfoInstance);
					}
					mes="操作成功";
				}else{
					mes="无权限";
				}
			}else{
				mes="未登录";
			}
			return "success";
		} else if(action.equals("addNewPostInfo")){
			long userId=SessionManage.checkSomeSession("userId");
			if(userId>0){
				if(this.doctorAudit(userId)){
					//医生已经登录了
					String postImg="";
					if(!imgContent.equals("")){
						String[] imgArr=imgContent.split(splitStr);
						for(int imgSize=0;imgSize<imgArr.length;imgSize++){
							String imgName=this.creatImage(userId,imgArr[imgSize]);
							postImg=postImg+imgName+splitStr;
						}
					}
					textContent=this.contextHandle(textContent);
					textContent=this.getPostContent(textContent, postImg);
					long postInfoId=mommyPostInfoBo.addNewPostInfoInstance(categoryId, titleContent, textContent, "", "", "", "", getPostInfoIsAllowApply(), String.valueOf(userId), getIsAudit());
					/*
					 * 增加发帖时候添加帖子所关联的标签 
					 * */
					if(postInfoId!=0){
						mommyPostInfoLabelBo.addNewPostInfoLabelList(postInfoId, doctorRingLabelStr, splitStr);
					}
					mes="操作成功";
				}else{
					mes="无权限";
				}
			}else{
				mes="未登录";
			}
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:预留一个接口，实现后期对点赞规则的扩展
	 * @data: 2015年12月14日下午6:13:06
	 * @param doctorId 医生的ID
	 * @return 是否允许继续点赞
	 */
	private boolean isCanGiveStar(long postInfoId,long userId){
		boolean flag=true;
		List<MommyPostReply> postReplyList=mommyPostReplyBo.getSomeUserGiveStar(postInfoId, userId);
		if(postReplyList!=null){
			flag=false;
		}
		return flag;
	}
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:系统是否开启审核,这里加入具体的审核状态
	 * @data: 2015年12月15日上午11:03:33
	 * @return 系统的审核状态，"Y":开启审核，"N",不开启审核
	 */
	private String getIsAudit(){
		String returStr="N";
		return returStr;
	}
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:对回复是否允许继续跟帖
	 * @data: 2015年12月16日下午1:30:41
	 * @return 是否允许对回复继续跟帖
	 * N:不允许，Y：允许
	 */
	private String getIsAllowReply(){
		String isAllowReply="Y";
		return isAllowReply;
	}
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:医生发帖是否允许回复
	 * @data: 2015年12月16日下午1:41:10
	 * @return 医生发帖是否允许回复
	 */
	private String getPostInfoIsAllowApply(){
		String isAllowReply="Y";
		return isAllowReply;
	}
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:保存图片，返回图片名称
	 * @data: 2015年12月16日下午2:40:01
	 * @param doctorId 医生的ID
	 * @param dataImg 图片数据的BASE64码
	 * @return 返回存储的图片的名称
	 */
	private String creatImage(long userId,String dataImg){
		String imgName="";
		String current=DateManage.getDateStr("yyyyMMddhhmmss");
		String randomName=this.getRandomStr();
		imgName=randomName+"ring"+current+".jpg";
		String fullImgName=dir+"/"+imgName;
		String selverName = ServletActionContext.getServletContext().getRealPath(fullImgName);
		try {
			if(!EncryptUtil.uploadImage(dataImg, selverName)){
				imgName="";
			}
		} catch (IOException e) {
			imgName="";
		}
		return imgName;
	}

	private String getRandomStr(){
		String returnStr="";
		Random random = new Random();
		for(int i=0;i<6;i++){
			returnStr += random.nextInt(10);
		}
		returnStr+=String.valueOf(System.currentTimeMillis());
		return returnStr;
	}
	
	private String contextHandle(String textContent){
		textContent=textContent.replace("\n", "&lt;br/&gt;");
		return textContent;
	}
	
	private String getPostContent(String contentData,String contentImg ){
		String returnStr="";
		if(contentImg==null||contentImg.equals("")){
			return contentData;
		}else{
			String[] contentArr=contentData.split("\\[图片\\]");
			String[] imgArr=contentImg.split(splitStr);
			int misSize=0;
			for(int contentSize=0;contentSize<imgArr.length;contentSize++){
				String contentText="";
				try{
					contentText=contentArr[contentSize-misSize];
				}catch(Exception e){
					contentText="";
					misSize++;
				}finally{
					String imgText=imgArr[contentSize];
					returnStr=returnStr+contentText+"&lt;br/&gt;"+"&lt;img&nbsp;src='http://www.qiyico.com/kybabyBG/admin/images/"+imgText+"'/&gt;&lt;br/&gt;";
				}
			}
		}
		return returnStr;
	}
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生的ID，查询医生的审核情况
	 * @data: 2015年12月28日下午1:28:59
	 * @param doctorId 医生的ID
	 * @return 医生的审核情况，如果审核通过返回true，否则返回false
	 */
	private boolean doctorAudit(long userId) {
/*		boolean flag=false;
		DoctorInfo doctorInfoInstance=doctorInfoBo.getDoctorInfoByDoctorId(userId);
		if(doctorInfoInstance!=null&&doctorInfoInstance.getAuthentication().equals(doctorAuditStr)){
			flag=true;
		}
		return flag;*/
		return true;
	}
	
	
	
	public String getMes() {
		return mes;
	}

	public void setRingTypeId(long ringTypeId) {
		this.ringTypeId = ringTypeId;
	}

	public void setPostInfoId(long postInfoId) {
		this.postInfoId = postInfoId;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public void setTitleContent(String titleContent) {
		this.titleContent = titleContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public void setImgContent(String imgContent) {
		this.imgContent = imgContent;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}

	public void setDoctorRingLabelStr(String doctorRingLabelStr) {
		this.doctorRingLabelStr = doctorRingLabelStr;
	}
}
