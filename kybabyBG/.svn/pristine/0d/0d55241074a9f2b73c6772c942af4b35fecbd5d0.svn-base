package com.kybaby.newbussiness.mommyring.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kybaby.domain.Admin;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.mommyring.action.MommyRingAction;
import com.kybaby.newbussiness.mommyring.domain.MommyPostInfo;
import com.kybaby.newbussiness.mommyring.domain.MommyRingLabel;
import com.kybaby.newbussiness.mommyring.domain.MommyRingType;
import com.kybaby.newbussiness.mommyring.domain.MommyTheTypeInfo;
import com.kybaby.newbussiness.mommyring.util.SessionManage;
import com.kybaby.util.EncryptUtil;

public class GetMommyRringInfo extends MommyRingAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private List<Object[]> allCategory=new ArrayList<Object[]>();//反馈到前端的所有分类的ID和名称
	private long someCategoryId;//某个医生圈分类的ID
	private List<Object[]> someMommyRingType=new ArrayList<Object[]>();//某个医生圈分类下的所有医生圈的ID和子分类名称列表
	private List<Object[]> allMommyPostInfo=new ArrayList<Object[]>();//所有医生圈的部分信息
	private String ringCategory="Y";//请求的标签的圈子类型，Y：医生圈
	private List<Object[]> someCategoryMommyRingLabelObject=new ArrayList<Object[]>();//所有的医生圈标签列表
	private long mommyPostInfoLabelId;//前端传递的医生圈帖子的Id
	private List<Long> someMommyPostInfoLabelIdList=new ArrayList<Long>();
	/**
	 * 一级分类标签集合
	 */
	private List<MommyTheTypeInfo> theTypeInfoList=new ArrayList<MommyTheTypeInfo>();
	/**
	 * 一级分类标签信息
	 */
	private MommyTheTypeInfo theTypeInfo;
	/**
	 * 二级分类标签集合
	 */
	private List<MommyRingType> userRingTypeList=new ArrayList<MommyRingType>();
	/**
	 * 二级分类标签信息
	 */
	private MommyRingType userRingType;
	private String dir="/admin/images/ring";
	
	
	public String execute(){
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"......");
		long adminId=SessionManage.checkSomeSession("landUser");
		if(adminId==0){
			mes="未登录";
			return "fail";
		}
		if(action.equals("allCategory")){
			allCategory=mommyTheTypeInfoBo.getAllRingCategory();
			mes="操作成功";
			return "success";
		}else if(action.equals("someCategory")){
			List<MommyRingType> someMommyRingTypeList=mommyRingTypeBo.getSomeCategoryDoctorRing(someCategoryId);
			someMommyRingType=this.getSomeMommyRingTypeInfoList(someMommyRingTypeList);
			mes="操作成功";
			return "success";
		}else if(action.equals("allMommyPostInfo")){
			List<MommyPostInfo> allMommyPostInfoList=mommyPostInfoBo.getAllMommyPostInfo();
			allMommyPostInfo=this.mommyPostInfoListManage(allMommyPostInfoList);
			mes="操作成功";
			return "success";
		}else if(action.equals("allTypeInfo")){//一级分类列表
			this.theTypeInfoList = this.mommyTheTypeInfoBo.getMommyTheTypeInfoList(theTypeInfo);
			mes="操作成功";
			return "success";
		}else if(action.equals("saveOrUpdateTypeInfo")){//一级分类保存更新
			Long id = this.mommyTheTypeInfoBo.saveOrUpdateMommyTheTypeInfo(theTypeInfo);
			if(id != null && id == 0L){
				mes="重复";
			}else{
				mes="操作成功";
			}
			return "success";
		}else if(action.equals("allMommyRingType")){//二级分类列表
			this.userRingTypeList = this.mommyTheTypeInfoBo.getMommyRingTypeListByMommyTheTypeInfoId(theTypeInfo);
			mes="操作成功";
			return "success";
		}else if(action.equals("saveOrUpdateMommyRingType")){//二级分类保存更新
			if(userRingType != null){
				userRingType.setCreatePerson(adminId);
				//图片处理
				String typeImg = userRingType.getTypeImg();
				if(typeImg != null && !"".equals(typeImg)){
					String imgName=this.creatImage(typeImg);
					userRingType.setTypeImg(imgName);
				}
			}
			this.mommyTheTypeInfoBo.saveOrUpdateMommyRingType(userRingType);
			mes="操作成功";
			return "success";
		}else if(action.equals("allDoctorMommyRingLabelInfo")){
			List<MommyRingLabel> userMommyRingLabelList=mommyRingLabelBo.getSomeCategoryMommyRingLabelList(ringCategory);
			someCategoryMommyRingLabelObject=this.ringLabelHandle(userMommyRingLabelList);
			mes="操作成功";
			return "success";
		}else if(action.equals("getSomeMommyPostInfoLabelIdList")){
			someMommyPostInfoLabelIdList=mommyPostInfoLabelBo.getSomeMommyPostInfoLabelList(mommyPostInfoLabelId);
			mes="操作成功";
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:该方法实现将医生圈对象列表，提取其中的ID和名称字段
	 * @data: 2015年12月22日下午5:00:57
	 * @param someMommyRingTypeList 需要进行信息提权的医生圈对象列表
	 * @return 医生圈对象的ID和名称字段的列表
	 */
	private List<Object[]> getSomeMommyRingTypeInfoList(List<MommyRingType> someMommyRingTypeList){
		List<Object[]> returnObjectList=null;
		Object[] returnObjectArr=null;
		if(someMommyRingTypeList!=null){
			returnObjectList=new ArrayList<Object[]>();
			for(int listSize=0;listSize<someMommyRingTypeList.size();listSize++){
				returnObjectArr=new String[2];
				MommyRingType userRingTypeInstance=someMommyRingTypeList.get(listSize);
				if(userRingTypeInstance!=null){
					long userRingTypeId=userRingTypeInstance.getId();
					returnObjectArr[0]=String.valueOf(userRingTypeId);
					String userRingTypeName=userRingTypeInstance.getSubclassName();
					returnObjectArr[1]=userRingTypeName;
					returnObjectList.add(returnObjectArr);
				}
				returnObjectArr=null;
			}
		}
		return returnObjectList;
	}
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:将获取到的发帖列表进行处理
	 * @data: 2015年12月23日下午4:46:23
	 * @param allMommyPostInfoList 获取到发帖的列表
	 * @return 将获取到的发帖列表处理之后的列表
	 */
	private List<Object[]> mommyPostInfoListManage(List<MommyPostInfo> allMommyPostInfoList){
		List<Object[]> returnObjectList=null;
		if(allMommyPostInfoList!=null){
			returnObjectList=new ArrayList<Object[]>();
			Object[] returnObject=null;
			for(int objectListSize=0;objectListSize<allMommyPostInfoList.size();objectListSize++){
				returnObject=new String[12];
				MommyPostInfo  mommyPostInfoInstance=allMommyPostInfoList.get(objectListSize);
				String mommyPostInfoTitle=mommyPostInfoInstance.getTitle();
				String ringTypeName="";
				Long typeId=mommyPostInfoInstance.getTypeId();
				Long theTypeInfoId=0L;
				String theTypeName="";
				if(typeId!=null){
					MommyRingType userRingTypeInstance=mommyRingTypeBo.getSomeMommyRingTypeInstance(typeId);
					if(userRingTypeInstance!=null){
						ringTypeName=userRingTypeInstance.getSubclassName();
						theTypeInfoId=userRingTypeInstance.getTypeId();
						MommyTheTypeInfo theTypeInfoInstance=mommyTheTypeInfoBo.getSomeCategoryInstanceById(theTypeInfoId);
						if(theTypeInfoInstance!=null){
							theTypeName=theTypeInfoInstance.getTypeName();
						}
					}
				}
				String createPerson=mommyPostInfoInstance.getCreatePerson();
				returnObject[4]="";//帖子发布者的名称
				if(createPerson==null||createPerson.equals("")){
					returnObject[4]="";
				}else if(createPerson.startsWith(startStr)){
					createPerson=createPerson.replace(startStr, "");
					Long adminId=Long.valueOf(createPerson);
					Admin adminInstance=adminBo.findAdminById(adminId);
					if(adminInstance!=null){
						returnObject[4]=adminInstance.getName();
					}else{
						returnObject[4]="";
					}
				}else{
					Long userId=Long.valueOf(createPerson);
					UserInfo userInstance=userInfoBo.getUserInfoById(userId);
					if(userInstance!=null){
						returnObject[4]=(userInstance.getParentName()==null||"".equals(userInstance.getParentName()) ? "用户" : userInstance.getParentName());
					}else{
						returnObject[4]="";
					}
				}
				String postTime=mommyPostInfoInstance.getPostTime();
				String isAllowReply=mommyPostInfoInstance.getIsAllowReply();
				String dataStatus=mommyPostInfoInstance.getDataStatus();
				String textContent=mommyPostInfoInstance.getContent();
				String imgContent=mommyPostInfoInstance.getPostImg();
				String content=this.getPostContent(textContent,imgContent);
				content=content.replace("\'", "\"");
				String isTop=mommyPostInfoInstance.getIsTop();
				
				returnObject[0]=String.valueOf(mommyPostInfoInstance.getId());//帖子的ID
				returnObject[1]=mommyPostInfoTitle;//帖子的标题
				returnObject[2]=String.valueOf(typeId);//帖子所属医生圈的ID
				returnObject[3]=ringTypeName;//帖子所属医生圈的名称
				returnObject[5]=postTime;//帖子发布的时间
				returnObject[6]=isAllowReply;//帖子是否允许回复
				returnObject[7]=dataStatus;//帖子的状态
				returnObject[8]=isTop;//帖子是否置顶
				returnObject[9]=content;//帖子的内容
				returnObject[10]=String.valueOf(theTypeInfoId);//一级分类的ID
				returnObject[11]=theTypeName;//一级分类的名称
				
				returnObjectList.add(returnObject);
				returnObject=null;
			}
		}
		return returnObjectList;
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

	/**
	 * 
	 * @author HooLee
	 * @Discription:将医生圈标签进行处理
	 * @data: 2015年12月28日下午6:30:00
	 * @param userMommyRingLabelList 待处理的医生圈标签列表
	 * @return 处理完成的医生圈标签
	 */
	private List<Object[]> ringLabelHandle(List<MommyRingLabel> userMommyRingLabelList){
		List<Object[]> retuurnObjectArrList=null;
		if(userMommyRingLabelList!=null){
			retuurnObjectArrList=new ArrayList<Object[]>();
			for(int labelListSize=0;labelListSize<userMommyRingLabelList.size();labelListSize++){
				Object[] returnObject=null;
				MommyRingLabel ringLabelInstance=userMommyRingLabelList.get(labelListSize);
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
	/**
	 * 上传图片
	 * @param dataImg
	 * @return
	 */
	private String creatImage(String dataImg){
		String imgName="";
		String current=DateManage.getDateStr("yyyyMMddhhmmss");
		String randomName=this.getRandomStr();
		imgName="ringBG"+current+".jpg";
		String fullImgName=dir+"/"+imgName;
		String selverName = ServletActionContext.getServletContext().getRealPath(dir);
		//判断文件夹是否存在
		File file = new File(selverName);
		//如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    System.out.println("//不存在");  
		    file .mkdir();    
		} else   
		{  
		    System.out.println("//目录存在");  
		} 
		selverName = ServletActionContext.getServletContext().getRealPath(fullImgName);
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
	
	public String getMes() {
		return mes;
	}

	public List<Object[]> getAllCategory() {
		return allCategory;
	}

	public void setSomeCategoryId(long someCategoryId) {
		this.someCategoryId = someCategoryId;
	}

	public List<Object[]> getSomeMommyRingType() {
		return someMommyRingType;
	}

	public List<Object[]> getAllMommyPostInfo() {
		return allMommyPostInfo;
	}

	public void setRingCategory(String ringCategory) {
		this.ringCategory = ringCategory;
	}

	public List<Object[]> getSomeCategoryMommyRingLabelObject() {
		return someCategoryMommyRingLabelObject;
	}

	public List<Long> getSomeMommyPostInfoLabelIdList() {
		return someMommyPostInfoLabelIdList;
	}

	public void setMommyPostInfoLabelId(long mommyPostInfoLabelId) {
		this.mommyPostInfoLabelId = mommyPostInfoLabelId;
	}

	public List<MommyTheTypeInfo> getMommyTheTypeInfoList() {
		return theTypeInfoList;
	}

	public void setMommyTheTypeInfoList(List<MommyTheTypeInfo> theTypeInfoList) {
		this.theTypeInfoList = theTypeInfoList;
	}

	public MommyTheTypeInfo getMommyTheTypeInfo() {
		return theTypeInfo;
	}

	public void setMommyTheTypeInfo(MommyTheTypeInfo theTypeInfo) {
		this.theTypeInfo = theTypeInfo;
	}

	public List<MommyRingType> getMommyRingTypeList() {
		return userRingTypeList;
	}

	public void setMommyRingTypeList(List<MommyRingType> userRingTypeList) {
		this.userRingTypeList = userRingTypeList;
	}

	public MommyRingType getMommyRingType() {
		return userRingType;
	}

	public void setMommyRingType(MommyRingType userRingType) {
		this.userRingType = userRingType;
	}

}