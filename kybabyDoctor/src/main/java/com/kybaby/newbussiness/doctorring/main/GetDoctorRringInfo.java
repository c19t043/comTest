package com.kybaby.newbussiness.doctorring.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorRingType;
import com.kybaby.domain.PostInfo;
import com.kybaby.domain.PostInfoLabel;
import com.kybaby.domain.PostReply;
import com.kybaby.domain.RingLabel;
import com.kybaby.domain.SubscribeUser;
import com.kybaby.domain.TheTypeInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.SessionManage;

/**
 * @ClassName:
 * @Description:获取医生圈信息相关的类
 * @author Hoolee
 * @date 2015年12月10日下午2:50:30
 */
public class GetDoctorRringInfo extends NewBaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private List<Object[]> allRingCategory=new ArrayList<Object[]>();//返回给页面所有的医生圈分类
	private long categoryId;//前端传递的需要获取的分类ID
	private List<Object[]> someRingCategory=new ArrayList<Object[]>();//返回给页面某一个分类下的ID、图片名称、标题、描述、发帖数量、参与人数、订阅状态
	private List<Object[]> recommendRingCategory=new ArrayList<Object[]>();//返回给页面的推荐的医生圈
	private long ringTypeId;//前端传递的医生圈分类的ID
	private List<Object[]> somePostInfo=new ArrayList<Object[]>();//反馈到前端的某一个医生圈下的贴列表，每条数据包括的内容有：帖子的ID，帖子的标题、最后一次回帖人的名称、最后一次回帖的时间、帖子的浏览量、帖子的回复数
	private List<String> ringCategoryImages=new ArrayList<String>();//反馈到前端的该医生圈下的展示图片信息
	private long postInfoId;//前端反馈的帖子的ID
	private String postInfoTitle;//反馈给前端帖子的标题
	private String doctorName;//反馈给前端帖子的发帖医生姓名
	private String dateStr;//反馈给前端帖子的发布日期
	private String browseNum;//反馈给前端的帖子的浏览量
	private String pointNum;//反馈给前端的帖子的关注数
	private String replyNum;//反馈给前端的帖子的回复数
	List<Object[]> somePostInfoReply=new ArrayList<Object[]>();//反馈给前端的回复的列表
	private String isAllowReply;//反馈给前端的帖子是否允许回复
	private String doctorImg;//
	private String postInfoContent;//反馈到前端的帖子内容
	private long loginDoctorId;//当前登录用户的ID
	private List<String> postInfoLabelNameList=new ArrayList<String>();//反馈给前端的医生圈帖子关联的标签名称列表
	private List<List<Object[]>> postInfoLinkesLabels=new ArrayList<List<Object[]>>();//反馈给前端的某一医生圈下面的所有帖子与标签相关联的列表
	private List<Object[]> someDoctorRingLabels=new ArrayList<Object[]>();//反馈给前端的某一医生圈下 面的不重复标签的ID和名称
	private long doctorRingId;//前端传递的医生圈的ID
	private long doctorRingLableId;//前端传递的选中的标签的ID
	private String ringCategory="Y";//请求的标签的圈子类型，Y：医生圈
	private List<Object[]> someCategoryRingLabelObject=new ArrayList<Object[]>();//所有的医生圈标签列表
	private List<Object[]> mySendSomePostInfo=new ArrayList<Object[]>();//反馈给前端的我的发帖列表
	private List<Object[]> myReplySomePostInfo=new ArrayList<Object[]>();//反馈给前端的我的回复列表
	
	@Override
	public String execute(){
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"......");
		if(action.equals("allCategory")){
			allRingCategory=theTypeInfoBo.getAllRingCategory();
			Object[] remarkCategory=theTypeInfoBo.getRemarkRingCategory();
			if(remarkCategory!=null){
				allRingCategory.add(0, remarkCategory);
			}
			if(allRingCategory!=null){
				mes="操作成功";
			}else{
				mes="无分类";
			}
			return "success";
		} else if(action.equals("someCategory")){
			TheTypeInfo someType=theTypeInfoBo.getSomeCategoryInstanceById(categoryId);
			if(someType!=null&&someType.getIsAvailable().equals("Y")&&someType.getIsMark().equals("Y")){
				//表示该医生圈被标记，需要显示已经订阅的医生圈，和系统推荐医生圈
				long doctorId=SessionManage.checkSomeSession("Doctor");
				if(doctorId>0){//用户登录，显示订阅的医生圈,如果设置了允许推荐医生圈的话，就显示允许的医生圈，否则就不显示允许的医生圈
					List<DoctorRingType> ringTypeList=doctorRingTypeBo.getSomeUserDoctoring(doctorId);
					someRingCategory=this.someRingTypeListManage(ringTypeList);
				}
				//下面获取推荐的医生圈，
				//没登录医生的推荐医生圈包括系统中推荐的医生圈,
				//登录医生的推荐医生圈包括系统中推荐的，同时用户还没有订阅的医生圈
				List<DoctorRingType> recommendDoctorRingTypeList=this.getRecommendRingType(doctorId);
				recommendRingCategory=this.someRingTypeListManage(recommendDoctorRingTypeList);
				mes="操作成功";
				return "success";
			}else if(someType!=null&&someType.getIsAvailable().equals("Y")){
				//表示该医生圈没有被标记，但是有效，就显示该分类下的普通的医生圈
				List<DoctorRingType> ringTypeList=doctorRingTypeBo.getSomeCategoryDoctorRing(categoryId);
				someRingCategory=this.someRingTypeListManage(ringTypeList);
				if(someRingCategory!=null){
					mes="操作成功";
				}else{
					mes="无贴";
				}
				return "success";
			}else{
				//表示该医生圈无效或者不存在该医生圈，就提醒前端进行页面重新刷新
				mes="刷新";
				return "success";
			}
		} else if(action.equals("turnTo")){
			DoctorRingType someRingType=doctorRingTypeBo.getSomeDoctorRingTypeInstance(ringTypeId);
			if(someRingType!=null){
				String[] imageArr=someRingType.getTopImg().split("::");
				ringCategoryImages=Arrays.asList(imageArr);
				Long browseNum=someRingType.getBrowseNum();
				if(browseNum==null){
					browseNum=0L;
				}
				browseNum++;
				someRingType.setBrowseNum(browseNum);
				doctorRingTypeBo.updateSomeDoctorRingType(someRingType);
			}
			List<PostInfo> postInfoList=postInfoBo.getSomeCategoryPostInfo(ringTypeId);
			somePostInfo=this.getSomePostInfoList(postInfoList);
			/*
			 * 增加返回医生圈帖子下面的标签的ID和的名称的列表
			 * */
			postInfoLinkesLabels=this.getSomePostInfoLabelLinkes(postInfoList);
			/*
			 * 增加返回某一医生圈下的所有不重复的标签的ID和名称
			 * */
			someDoctorRingLabels=this.listRespect(postInfoLinkesLabels);
			mes="操作成功";
			return "success";
		} else if(action.equals("detail")){
			PostInfo postInfo=postInfoBo.getPostInfoInstanceById(postInfoId);
			if(postInfo!=null){
				postInfoLabelNameList=ringLabelBo.getSomePostInfoRingLabelNameList(postInfo.getId());
				loginDoctorId=SessionManage.checkSomeSession("Doctor");
				postInfoTitle=postInfo.getTitle();
				String contentData=postInfo.getContent();
				String contentImg=postInfo.getPostImg();
				postInfoContent=this.getPostContent(contentData,contentImg);
				doctorName="";
				doctorImg="";
				String createPerson=postInfo.getCreatePerson();
				if(createPerson.startsWith(startStr)){
					createPerson=createPerson.replace(startStr, "");
					long adminId=Long.valueOf(createPerson);
					String adminName=postInfoBo.getAdminNameById(adminId);
					if(adminName==null){
						adminName="";
					}
					doctorName=adminName;
					doctorImg=startStr;
				}else{
					DoctorInfo doctorInfo=doctorInfoBo.getDoctorInfoBoById(Long.valueOf(createPerson));
					if(doctorInfo!=null){
						doctorName=doctorInfo.getDoctorName();
						doctorImg=doctorInfo.getDoctorImage();
					}
				}
				dateStr=postInfo.getCreateTime();
				browseNum=String.valueOf(postInfo.getBrowseNum());
				pointNum=String.valueOf(postInfo.getPointNum());
				replyNum=String.valueOf(postInfo.getReplyNum());
				isAllowReply=postInfo.getIsAllowReply();
				List<PostReply> postReplyList=postReplyBo.getSomePostInfoReply(postInfoId);
				somePostInfoReply=this.getSomePostInfoReplyInfo(postReplyList);
				Long postBrowseNum=postInfo.getBrowseNum();
				if(postBrowseNum==null){
					postBrowseNum=0L;
				}
				postBrowseNum++;
				postInfo.setBrowseNum(postBrowseNum);
				postInfoBo.updatePostInfoInstance(postInfo);
				mes="操作成功";
				return "success";
			}
			mes="刷新";
			return "fail";
		}else if(action.equals("postInfoScreen")){
			List<PostInfo> postInfoList=postInfoBo.getSomeCategoryPostInfo(doctorRingId);
			postInfoList=this.deleteUnchosePostInfoInstance(postInfoList, doctorRingLableId);
			somePostInfo=this.getSomePostInfoList(postInfoList);
			postInfoLinkesLabels=this.getSomePostInfoLabelLinkes(postInfoList);
			mes="操作成功";
			return "success";
		}else if(action.equals("allDoctorRingLabelInfo")){
			List<RingLabel> doctorRingLabelList=ringLabelBo.getSomeCategoryRingLabelList(ringCategory);
			someCategoryRingLabelObject=this.ringLabelHandle(doctorRingLabelList);
			mes="操作成功";
			return "success";
		}else if(action.equals("mySendPostInfo")){
			loginDoctorId=SessionManage.checkSomeSession("Doctor");
			List<PostInfo> postInfoList=postInfoBo.getMySendPostInfoList(loginDoctorId);
			mySendSomePostInfo=this.getSomePostInfoList(postInfoList);
			mes="操作成功";
			return "success";
		}else if(action.equals("myReplyPostInfo")){
			loginDoctorId=SessionManage.checkSomeSession("Doctor");
			List<PostInfo> postInfoList=postInfoBo.getMyReplyPostInfoList(loginDoctorId);
			myReplySomePostInfo=this.getSomePostInfoList(postInfoList);
			mes="操作成功";
			return "success";
		}
		return "fail";
	}

	/**
	 * 
	 * @author HooLee
	 * @Discription:通过分类的ID，统计某一个分类的发帖数
	 * @data: 2015年12月11日下午1:52:26
	 * @param ringTypeId 分类的ID
	 * @return 某一个分类的发帖数
	 */
	private long getSomeRingTypeNum(long ringTypeId){
		long postInfoNum=0;
		List<PostInfo> postInfoList=postInfoBo.getSomeCategoryPostInfo(ringTypeId);
		if(postInfoList!=null){
			postInfoNum=postInfoList.size();
			/*for(int postSize=0;postSize<postInfoList.size();postSize++){
				PostInfo postInfo=postInfoList.get(postSize);
				if(postInfo!=null){
					Long replyNum=postInfo.getReplyNum();
					if(replyNum!=null){
						replyNum++;
					}else{
						replyNum=1L;
					}
					postInfoNum+=replyNum;
				}
			}*/
		}
		return postInfoNum;
	}

	/**
	 * 
	 * @author HooLee
	 * @Discription:通过分类的ID，统计某一个分类的参与人数
	 * @data: 2015年12月11日下午1:53:31
	 * @param ringTypeId 分类的ID
	 * @return 某一个分类的参与人数
	 */
	private long getSomeRingTypeUsr(long ringTypeId){
		long commitUserNum=0;
		DoctorRingType someRingType=doctorRingTypeBo.getSomeDoctorRingTypeInstance(ringTypeId);
		if(someRingType!=null){
			Long browseNum=someRingType.getBrowseNum();
			if(browseNum!=null){
				commitUserNum=browseNum;
			}
		}
		return commitUserNum;
	}

	/**
	 * 
	 * @author HooLee
	 * @Discription:根据朋友圈的分类ID获取当前用户的分类订阅状态
	 * @data: 2015年12月11日下午2:51:04
	 * @param ringTypeId 分类的ID
	 * @return 某一个分类的订阅状态,N表示未订阅,Y表示已订阅
	 */
	private String getSomeRingTypeMar(long ringTypeId){
		String ringTypeMar="C";
		long doctorId=SessionManage.checkSomeSession("Doctor");
		SubscribeUser someSubscribeUser=subscribeUserBo.getSomeUserSubscribeUser(doctorId, ringTypeId);
		DoctorRingType someRingType=doctorRingTypeBo.getSomeDoctorRingTypeInstance(ringTypeId);
		boolean flag=(someRingType!=null&&someRingType.getIsStart().equals("Y")&&someRingType.getIsSubscribe().equals("Y"));
		if(flag&&someSubscribeUser!=null){
			ringTypeMar="Y";
		}else if(flag){
			ringTypeMar="N";
		}
		return ringTypeMar;
	}

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某一分类下的医生圈下的有效信息
	 * @data: 2015年12月11日下午2:46:55
	 * @param ringTypeList 待提取有效信息的医生圈列表
	 * @return 某一分类下的医生圈下的有效信息
	 */
	private List<Object[]> someRingTypeListManage(List<DoctorRingType> ringTypeList){
		List<Object[]> ringTypeObj=null;
		if(ringTypeList!=null){
			ringTypeObj=new ArrayList<Object[]>();
			Object[] infoArr=null;
			for(int listLength=0;listLength<ringTypeList.size();listLength++){
				infoArr=new String[7];
				DoctorRingType someRingType=ringTypeList.get(listLength);
				long ringTypeId=someRingType.getId();
				String ringTypeImg=someRingType.getTypeImg();
				String ringTypeName=someRingType.getSubclassName();
				String ringTypeCom=someRingType.getRemark();
				long ringTypeNum=this.getSomeRingTypeNum(ringTypeId);//添加接口，便于后期修改，统计某一个分类的发帖数
				long ringTypeUsr=this.getSomeRingTypeUsr(ringTypeId);//添加接口，便于后期修改，统计某一个分类的参与人数
				String ringTypeMar=this.getSomeRingTypeMar(ringTypeId);//添加接口，便于后期修改,获取某一个医生圈对于当前用户的订阅状态
				infoArr[0]=String.valueOf(ringTypeId);
				infoArr[1]=ringTypeImg;
				infoArr[2]=ringTypeName;
				infoArr[3]=ringTypeCom;
				infoArr[4]=String.valueOf(ringTypeNum);
				infoArr[5]=String.valueOf(ringTypeUsr);
				infoArr[6]=String.valueOf(ringTypeMar);
				ringTypeObj.add(infoArr);
				infoArr=null;
			}
		}
		return ringTypeObj;
	}

	/**
	 * 
	 * @author HooLee
	 * @Discription:是否允许推荐的
	 * @data: 2015年12月13日上午10:38:40
	 * @return
	 */
	private boolean isRecommend(){
		boolean flag=false;
		if(this.userCanRecommend()&&this.osCanRecommend()){
			flag=true;
		}
		return flag;
	}

	/**
	 * 
	 * @author HooLee
	 * @Discription:用户是否允许推荐，(接口预留，便于后期扩展)
	 * @data: 2015年12月13日上午10:45:11
	 * @return 用户是否允许推荐(接口预留)
	 */
	private boolean userCanRecommend(){
		boolean flag=true;

		return flag;
	}

	/**
	 * 
	 * @author HooLee
	 * @Discription:系统是否允许推荐，(接口预留，便于后期扩展)
	 * @data: 2015年12月13日上午10:46:21
	 * @return 系统是否允许推荐，(接口预留)
	 */
	private boolean osCanRecommend(){
		boolean flag=true;

		return flag;
	}

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取推荐的医生圈
	 * @data: 2015年12月13日上午11:09:42
	 * @param doctorId
	 * @return
	 */
	private List<DoctorRingType> getRecommendRingType(long doctorId){
		List<DoctorRingType> ringTypeList=new ArrayList<DoctorRingType>();
		if(this.isRecommend()){//能够推荐
			if(doctorId>0){//医生登录的情况,返回系统推荐的，同时医生没有订阅的
				ringTypeList=doctorRingTypeBo.getSomeDoctorRingType(doctorId);
			}else{//医生未登录的情况下，返回系统中推荐的医生圈
				ringTypeList=doctorRingTypeBo.getOsRecommendRingType();
			}
		}
		return ringTypeList;
	}

	/**
	 * 
	 * @author HooLee
	 * @Discription:将某一个医生圈下展示的帖子列表的信息筛选展示出来
	 * @data: 2015年12月13日下午3:10:50
	 * @param postInfoList 某一个医生圈下展示的帖子列表
	 * @return 某一个医生圈子下展示的帖子列表筛选出来的信息
	 */
	private List<Object[]> getSomePostInfoList(List<PostInfo> postInfoList){
		List<Object[]> somePostInfoList=new ArrayList<Object[]>();
		if(postInfoList!=null){
			Object[] object=null;
			for(int listSizeI=0;listSizeI<postInfoList.size();listSizeI++){
				object=new String[6];
				PostInfo postInfo=postInfoList.get(listSizeI);
				long postInfoId=postInfo.getId();
				String postInfoTitle=postInfo.getTitle();
				//Long doctorId=postInfo.getLastReplyPerson();
				String doctorId=postInfo.getCreatePerson();
				if(doctorId==null){
					doctorId="0";
				}
				String postInfoPersonName="";
				if(doctorId.startsWith(startStr)){
					doctorId=doctorId.replace(startStr, "");
					long adminId=Long.valueOf(doctorId);
					String adminName=postInfoBo.getAdminNameById(adminId);
					if(adminName==null){
						adminName="";
					}
					postInfoPersonName=adminName;
				}else{
					DoctorInfo doctor=doctorInfoBo.getDoctorInfoBoById(Long.valueOf(doctorId));
					if(doctor!=null){
						postInfoPersonName=doctor.getDoctorName();
					}
				}
				
				//String replyTime=postInfo.getLastReplyTime();
				String replyTime=postInfo.getCreateTime();
				//replyTime=DateManage.getDateDifferInMinutes(replyTime, this.getMaxMintes());
				long browseNum=postInfo.getBrowseNum();
				long replyNum=postInfo.getReplyNum();
				object[0]=String.valueOf(postInfoId);
				object[1]=postInfoTitle;
				object[2]=postInfoPersonName;
				object[3]=replyTime;
				object[4]=String.valueOf(browseNum);
				object[5]=String.valueOf(replyNum);
				somePostInfoList.add(object);
				object=null;
			}
		}
		return somePostInfoList;
	}
	
	
	private List<List<Object[]>> getSomePostInfoLabelLinkes(List<PostInfo> postInfoList){
		List<List<Object[]>> returnListObjectArr=null;
		if(postInfoList!=null){
			returnListObjectArr=new ArrayList<List<Object[]>>();
			List<Object[]> returnList=new ArrayList<Object[]>();
			for(int postInfoSize=0;postInfoSize<postInfoList.size();postInfoSize++){
				PostInfo postInfoInstance=postInfoList.get(postInfoSize);
				long postInfoId=postInfoInstance.getId();
				returnList=ringLabelBo.getSomePostInfoRingLabelIdAndName(postInfoId);
				returnListObjectArr.add(returnList);
			}
		}
		return returnListObjectArr;
	}
	
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:后台设置的时间最大值
	 * @data: 2015年12月13日下午3:58:33
	 * @return 后台设置的时间最大值，预留接口
	 */
	private int getMaxMintes(){
		int maxMinutes=5;
		return maxMinutes;
	}

	private List<Object[]> getSomePostInfoReplyInfo(List<PostReply> postReplyList){
		List<Object[]> postInfoReply=new ArrayList<Object[]>();
		if(postReplyList!=null){
			Object[] object=null;
			for(int replySize=0;replySize<postReplyList.size();replySize++){
				object=new String[9];
				PostReply someReply=postReplyList.get(replySize);
				object[0]=String.valueOf(someReply.getId());
				String doctorName="";
				String doctorImg="";
				long doctorId=someReply.getCreatePerson();
				DoctorInfo doctor=doctorInfoBo.getDoctorInfoBoById(doctorId);
				if(doctor!=null){
					doctorName=doctor.getDoctorName();
					doctorImg=doctor.getDoctorImage();
				}
				object[1]=doctorName;
				object[2]=someReply.getReplyContent();
				String dateTime=someReply.getCreateTime();
				//object[3]=DateManage.getDateDifferInMinutes(dateTime, getMaxMintes());
				object[3]=dateTime;
				object[4]=doctorImg;
				long replyId=someReply.getReplyId();
				object[5]=String.valueOf(replyId);
				PostReply postReplyInstance=null;
				if(replyId!=0){//replyId=0表示帖子不是回复之前帖子的ID
					postReplyInstance=postReplyBo.getPostReplyInstanceById(replyId);
				}
				String replyContent="";
				String isAllowReply=someReply.getIsAllowReply();
				if(postReplyInstance!=null){
					replyContent=postReplyInstance.getReplyContent();
				}
				object[6]=replyContent;
				object[7]=isAllowReply;
				object[8]=String.valueOf(doctorId);
				postInfoReply.add(object);
				object=null;
			}
		}
		return postInfoReply;
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
					returnStr=returnStr+contentText+"&lt;br/&gt;"+"&lt;img&nbsp;src='http://www.qiyico.com/kybabyBG/admin/images/ring/"+imgText+"'/&gt;&lt;br/&gt;";
				}
			}
		}
		return returnStr;
	}

	private List<Object[]> listRespect(List<List<Object[]>> postInfoLinkesLabels){
		List<Object[]> returnList=null;
		if(postInfoLinkesLabels!=null){
			returnList=new ArrayList<Object[]>();
			for(int linkesSize=0;linkesSize<postInfoLinkesLabels.size();linkesSize++){
				List<Object[]> temporaryInstance=postInfoLinkesLabels.get(linkesSize);
				if(temporaryInstance!=null){
					for(int objSize=0;objSize<temporaryInstance.size();objSize++){
						Object[] obj=temporaryInstance.get(objSize);
						if(obj!=null){
							if(returnList!=null){
								int commenSize=0;
								for(int listSize=0;listSize<returnList.size(); listSize++ ){
									Object[] returnObj=returnList.get(listSize);
									if(!(returnObj[0].toString().equals(obj[0].toString()))&&!(returnObj[1].toString().equals(obj[1].toString()))){
										commenSize++;
									}
								}
								if(commenSize==returnList.size()){
									returnList.add(obj);
								}
							}else{
								returnList.add(obj);
							}
						}
					}
				}
			}
		}
		return returnList;
	}
	
	private List<PostInfo> deleteUnchosePostInfoInstance(List<PostInfo> postInfoList,long doctorRingLableId){
		List<PostInfo> returnPostInfoList=null;
		if(postInfoList!=null){
			returnPostInfoList=new ArrayList<PostInfo>();
			for(int postInfoSize=0;postInfoSize<postInfoList.size();postInfoSize++){
				PostInfo postInfoIntance=postInfoList.get(postInfoSize);
				if(postInfoIntance!=null){
					long postInfoId=postInfoIntance.getId();
					PostInfoLabel postInfoLabelInstance=postInfoLabelBo.checkSomePostInfoLabel(postInfoId, doctorRingLableId);
					if(postInfoLabelInstance!=null){
						returnPostInfoList.add(postInfoList.get(postInfoSize));
					}
				}
			}
		}
		return returnPostInfoList;
	}

	/**
	 * 
	 * @author HooLee
	 * @Discription:将医生圈标签进行处理
	 * @data: 2015年12月28日下午6:30:00
	 * @param doctorRingLabelList 待处理的医生圈标签列表
	 * @return 处理完成的医生圈标签
	 */
	private List<Object[]> ringLabelHandle(List<RingLabel> doctorRingLabelList){
		List<Object[]> retuurnObjectArrList=null;
		if(doctorRingLabelList!=null){
			retuurnObjectArrList=new ArrayList<Object[]>();
			for(int labelListSize=0;labelListSize<doctorRingLabelList.size();labelListSize++){
				Object[] returnObject=null;
				RingLabel ringLabelInstance=doctorRingLabelList.get(labelListSize);
				if(ringLabelInstance!=null){
					returnObject=new String[4];
					String labelId=String.valueOf(ringLabelInstance.getId());
					String labelName=ringLabelInstance.getRingLabelName();
					String labelStatus=ringLabelInstance.getRingLabelStatus();
					String labelCount=String.valueOf(ringLabelInstance.getRingLabelClickNum());
					returnObject[0]=labelId;
					returnObject[1]=labelName;
					returnObject[2]=labelStatus;
					returnObject[3]=labelCount;
					retuurnObjectArrList.add(returnObject);
				}
				returnObject=null;
			}
		}
		return retuurnObjectArrList;
	}
	
	@Override
	public String getMes() {
		return mes;
	}

	public List<Object[]> getAllRingCategory() {
		return allRingCategory;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public List<Object[]> getRecommendRingCategory() {
		return recommendRingCategory;
	}

	public void setRingTypeId(long ringTypeId) {
		this.ringTypeId = ringTypeId;
	}

	public List<Object[]> getSomeRingCategory() {
		return someRingCategory;
	}

	public List<Object[]> getSomePostInfo() {
		return somePostInfo;
	}

	public List<String> getRingCategoryImages() {
		return ringCategoryImages;
	}

	public void setPostInfoId(long postInfoId) {
		this.postInfoId = postInfoId;
	}

	public String getPostInfoTitle() {
		return postInfoTitle;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public String getDateStr() {
		return dateStr;
	}

	public String getBrowseNum() {
		return browseNum;
	}

	public String getPointNum() {
		return pointNum;
	}

	public String getReplyNum() {
		return replyNum;
	}

	public List<Object[]> getSomePostInfoReply() {
		return somePostInfoReply;
	}

	public String getIsAllowReply() {
		return isAllowReply;
	}

	public String getDoctorImg() {
		return doctorImg;
	}

	public long getLoginDoctorId() {
		return loginDoctorId;
	}

	public String getPostInfoContent() {
		return postInfoContent;
	}

	public List<String> getPostInfoLabelNameList() {
		return postInfoLabelNameList;
	}

	public List<List<Object[]>> getPostInfoLinkesLabels() {
		return postInfoLinkesLabels;
	}

	public List<Object[]> getSomeDoctorRingLabels() {
		return someDoctorRingLabels;
	}

	public void setDoctorRingId(long doctorRingId) {
		this.doctorRingId = doctorRingId;
	}

	public void setDoctorRingLableId(long doctorRingLableId) {
		this.doctorRingLableId = doctorRingLableId;
	}

	public void setRingCategory(String ringCategory) {
		this.ringCategory = ringCategory;
	}

	public List<Object[]> getSomeCategoryRingLabelObject() {
		return someCategoryRingLabelObject;
	}

	public List<Object[]> getMySendSomePostInfo() {
		return mySendSomePostInfo;
	}

	public void setMySendSomePostInfo(List<Object[]> mySendSomePostInfo) {
		this.mySendSomePostInfo = mySendSomePostInfo;
	}

	public List<Object[]> getMyReplySomePostInfo() {
		return myReplySomePostInfo;
	}

	public void setMyReplySomePostInfo(List<Object[]> myReplySomePostInfo) {
		this.myReplySomePostInfo = myReplySomePostInfo;
	}

}
