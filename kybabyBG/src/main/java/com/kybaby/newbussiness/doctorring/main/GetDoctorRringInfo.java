package com.kybaby.newbussiness.doctorring.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kybaby.domain.Admin;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorRingType;
import com.kybaby.domain.PostInfo;
import com.kybaby.domain.RingLabel;
import com.kybaby.domain.TheTypeInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.doctorring.util.SessionManage;
import com.kybaby.util.EncryptUtil;

public class GetDoctorRringInfo extends NewBaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private List<Object[]> allCategory=new ArrayList<Object[]>();//反馈到前端的所有分类的ID和名称
	private long someCategoryId;//某个医生圈分类的ID
	private List<Object[]> someDoctorRingType=new ArrayList<Object[]>();//某个医生圈分类下的所有医生圈的ID和子分类名称列表
	private List<Object[]> allPostInfo=new ArrayList<Object[]>();//所有医生圈的部分信息
	private String ringCategory="Y";//请求的标签的圈子类型，Y：医生圈
	private List<Object[]> someCategoryRingLabelObject=new ArrayList<Object[]>();//所有的医生圈标签列表
	private long postInfoLabelId;//前端传递的医生圈帖子的Id
	private List<Long> somePostInfoLabelIdList=new ArrayList<Long>();
	/**
	 * 一级分类标签集合
	 */
	private List<TheTypeInfo> theTypeInfoList=new ArrayList<TheTypeInfo>();
	/**
	 * 一级分类标签信息
	 */
	private TheTypeInfo theTypeInfo;
	/**
	 * 二级分类标签集合
	 */
	private List<DoctorRingType> doctorRingTypeList=new ArrayList<DoctorRingType>();
	/**
	 * 二级分类标签信息
	 */
	private DoctorRingType doctorRingType;
	private String dir="/admin/images/ring";
	
	
	public String execute(){
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"......");
		long adminId=SessionManage.checkSomeSession("landUser");
		if(adminId==0){
			mes="未登录";
			return "fail";
		}
		if(action.equals("allCategory")){
			allCategory=theTypeInfoBo.getAllRingCategory();
			mes="操作成功";
			return "success";
		}else if(action.equals("someCategory")){
			List<DoctorRingType> someDoctorRingTypeList=doctorRingTypeBo.getSomeCategoryDoctorRing(someCategoryId);
			someDoctorRingType=this.getSomeDoctorRingTypeInfoList(someDoctorRingTypeList);
			mes="操作成功";
			return "success";
		}else if(action.equals("allPostInfo")){
			List<PostInfo> allPostInfoList=postInfoBo.getAllPostInfo();
			allPostInfo=this.postInfoListManage(allPostInfoList);
			mes="操作成功";
			return "success";
		}else if(action.equals("allTypeInfo")){//一级分类列表
			this.theTypeInfoList = this.theTypeInfoBo.getTheTypeInfoList(theTypeInfo);
			mes="操作成功";
			return "success";
		}else if(action.equals("saveOrUpdateTypeInfo")){//一级分类保存更新
			Long id = this.theTypeInfoBo.saveOrUpdateTheTypeInfo(theTypeInfo);
			if(id != null && id == 0L){
				mes="重复";
			}else{
				mes="操作成功";
			}
			return "success";
		}else if(action.equals("allDoctorRingType")){//二级分类列表
			this.doctorRingTypeList = this.theTypeInfoBo.getDoctorRingTypeListByTheTypeInfoId(theTypeInfo);
			mes="操作成功";
			return "success";
		}else if(action.equals("saveOrUpdateDoctorRingType")){//二级分类保存更新
			if(doctorRingType != null){
				doctorRingType.setCreatePerson(adminId);
				//图片处理
				String typeImg = doctorRingType.getTypeImg();
				if(typeImg != null && !"".equals(typeImg)){
					String imgName=this.creatImage(typeImg);
					doctorRingType.setTypeImg(imgName);
				}
			}
			this.theTypeInfoBo.saveOrUpdateDoctorRingType(doctorRingType);
			mes="操作成功";
			return "success";
		}else if(action.equals("allDoctorRingLabelInfo")){
			List<RingLabel> doctorRingLabelList=ringLabelBo.getSomeCategoryRingLabelList(ringCategory);
			someCategoryRingLabelObject=this.ringLabelHandle(doctorRingLabelList);
			mes="操作成功";
			return "success";
		}else if(action.equals("getSomePostInfoLabelIdList")){
			somePostInfoLabelIdList=postInfoLabelBo.getSomePostInfoLabelList(postInfoLabelId);
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
	 * @param someDoctorRingTypeList 需要进行信息提权的医生圈对象列表
	 * @return 医生圈对象的ID和名称字段的列表
	 */
	private List<Object[]> getSomeDoctorRingTypeInfoList(List<DoctorRingType> someDoctorRingTypeList){
		List<Object[]> returnObjectList=null;
		Object[] returnObjectArr=null;
		if(someDoctorRingTypeList!=null){
			returnObjectList=new ArrayList<Object[]>();
			for(int listSize=0;listSize<someDoctorRingTypeList.size();listSize++){
				returnObjectArr=new String[2];
				DoctorRingType doctorRingTypeInstance=someDoctorRingTypeList.get(listSize);
				if(doctorRingTypeInstance!=null){
					long doctorRingTypeId=doctorRingTypeInstance.getId();
					returnObjectArr[0]=String.valueOf(doctorRingTypeId);
					String doctorRingTypeName=doctorRingTypeInstance.getSubclassName();
					returnObjectArr[1]=doctorRingTypeName;
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
	 * @param allPostInfoList 获取到发帖的列表
	 * @return 将获取到的发帖列表处理之后的列表
	 */
	private List<Object[]> postInfoListManage(List<PostInfo> allPostInfoList){
		List<Object[]> returnObjectList=null;
		if(allPostInfoList!=null){
			returnObjectList=new ArrayList<Object[]>();
			Object[] returnObject=null;
			for(int objectListSize=0;objectListSize<allPostInfoList.size();objectListSize++){
				returnObject=new String[12];
				PostInfo  postInfoInstance=allPostInfoList.get(objectListSize);
				String postInfoTitle=postInfoInstance.getTitle();
				String ringTypeName="";
				Long typeId=postInfoInstance.getTypeId();
				Long theTypeInfoId=0L;
				String theTypeName="";
				if(typeId!=null){
					DoctorRingType doctorRingTypeInstance=doctorRingTypeBo.getSomeDoctorRingTypeInstance(typeId);
					if(doctorRingTypeInstance!=null){
						ringTypeName=doctorRingTypeInstance.getSubclassName();
						theTypeInfoId=doctorRingTypeInstance.getTypeId();
						TheTypeInfo theTypeInfoInstance=theTypeInfoBo.getSomeCategoryInstanceById(theTypeInfoId);
						if(theTypeInfoInstance!=null){
							theTypeName=theTypeInfoInstance.getTypeName();
						}
					}
				}
				String createPerson=postInfoInstance.getCreatePerson();
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
					Long doctorId=Long.valueOf(createPerson);
					DoctorInfo doctorInstance=doctorInfoBo.getDoctorInfoById(doctorId);
					if(doctorInstance!=null){
						returnObject[4]=doctorInstance.getDoctorName();
					}else{
						returnObject[4]="";
					}
				}
				String postTime=postInfoInstance.getPostTime();
				String isAllowReply=postInfoInstance.getIsAllowReply();
				String dataStatus=postInfoInstance.getDataStatus();
				String textContent=postInfoInstance.getContent();
				String imgContent=postInfoInstance.getPostImg();
				String content=this.getPostContent(textContent,imgContent);
				content=content.replace("\'", "\"");
				String isTop=postInfoInstance.getIsTop();
				
				returnObject[0]=String.valueOf(postInfoInstance.getId());//帖子的ID
				returnObject[1]=postInfoTitle;//帖子的标题
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

	public List<Object[]> getSomeDoctorRingType() {
		return someDoctorRingType;
	}

	public List<Object[]> getAllPostInfo() {
		return allPostInfo;
	}

	public void setRingCategory(String ringCategory) {
		this.ringCategory = ringCategory;
	}

	public List<Object[]> getSomeCategoryRingLabelObject() {
		return someCategoryRingLabelObject;
	}

	public List<Long> getSomePostInfoLabelIdList() {
		return somePostInfoLabelIdList;
	}

	public void setPostInfoLabelId(long postInfoLabelId) {
		this.postInfoLabelId = postInfoLabelId;
	}

	public List<TheTypeInfo> getTheTypeInfoList() {
		return theTypeInfoList;
	}

	public void setTheTypeInfoList(List<TheTypeInfo> theTypeInfoList) {
		this.theTypeInfoList = theTypeInfoList;
	}

	public TheTypeInfo getTheTypeInfo() {
		return theTypeInfo;
	}

	public void setTheTypeInfo(TheTypeInfo theTypeInfo) {
		this.theTypeInfo = theTypeInfo;
	}

	public List<DoctorRingType> getDoctorRingTypeList() {
		return doctorRingTypeList;
	}

	public void setDoctorRingTypeList(List<DoctorRingType> doctorRingTypeList) {
		this.doctorRingTypeList = doctorRingTypeList;
	}

	public DoctorRingType getDoctorRingType() {
		return doctorRingType;
	}

	public void setDoctorRingType(DoctorRingType doctorRingType) {
		this.doctorRingType = doctorRingType;
	}

}