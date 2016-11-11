package com.kybaby.newbussiness.userconsult.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.Evaluate;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.userconsult.domain.ConsultBabyInfo;
import com.kybaby.util.DateManage;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ModelDriven;

public class ConsultBabyInfoAction extends NewBaseAction implements
		ModelDriven<ConsultBabyInfo> {

	private static final long serialVersionUID = 1L;

	private ConsultBabyInfo babyInfo = new ConsultBabyInfo();

	private Evaluate evaluate;

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

	private String message="成功";

	private Long parentId;

	private List<ConsultBabyInfo> babyInfoList = new ArrayList<ConsultBabyInfo>();
	
	private String dir="../kybabyBG/admin/images/consultPicture";
	
	private String imagedata;//前端传递的图片数据
	/**
	 * 添加宝宝记录
	 */
	private final String ADD_BABY = "addBaby";

	/**
	 * 修改宝宝记录
	 */
	private final String EDIT_BABY = "editBaby";

	/**
	 * 删除宝宝记录
	 */
	private final String DELETE_BABY = "deleteBaby";

	/**
	 * 获取一条宝宝记录
	 */
	private final String GET_BABY = "getBaby";

	/**
	 * 根据父母获取所有宝宝记录
	 */
	private final String GET_BABYS = "getBabys";

	@Override
	public String execute() throws Exception {
		boolean successFlag = false;

		UserInfo userInfo = null;
		String fileName = "";
		Long userId = this.userInfoBo.getLoginUserInfoId();
		if(userId == null){
			message="请登录";
			return SUCCESS;
		}
		this.parentId = userId;
		switch (this.action) {
		case ADD_BABY:
			userInfo = new UserInfo();
			userInfo.setId(parentId);
			babyInfo.setUserInfo(userInfo);
			fileName=this.uploadBabyHeadImg(null);
			babyInfo.setHeadImg(fileName);
			successFlag = this.consultBabyInfoService.addBabyInfo(babyInfo);
			break;
		case EDIT_BABY:
			ConsultBabyInfo old = this.consultBabyInfoService.getBabyInfoByID(babyInfo.getId());
			fileName = this.uploadBabyHeadImg(old.getHeadImg());
			babyInfo.setHeadImg(fileName);
			updateBabyInfo(old,babyInfo);
			successFlag = this.consultBabyInfoService.updateBabyInfo(old);
			break;
		case DELETE_BABY:
			successFlag = this.consultBabyInfoService
					.deleteBabyInfoByID(babyInfo.getId());
			break;
		case GET_BABY:
			babyInfo = this.consultBabyInfoService.getBabyInfoByID(babyInfo
					.getId());
			return SUCCESS;
		case GET_BABYS:
			List<ConsultBabyInfo> babyInfoListInit = this.consultBabyInfoService
			.getBabyInfoListByParentId(parentId);
			if(babyInfoListInit == null || babyInfoListInit.isEmpty()){
				//没有关联宝宝默认初始化当前用户数据
				UserInfo usr = this.userInfoBo.getUserById(userId);
				ConsultBabyInfo babyInfo = new ConsultBabyInfo();
				babyInfo.setBabyName(usr.getBabyName());
				babyInfo.setBirthday(usr.getBirthday());
				babyInfo.setSex(usr.getSex());
				babyInfo.setUserInfo(usr);
				successFlag = this.consultBabyInfoService.addBabyInfo(babyInfo);
			}
			babyInfoList = this.consultBabyInfoService
					.getBabyInfoListByParentId(parentId);
			return SUCCESS;
		}

		if (successFlag)
			message = "成功";
		else
			message = "失败";

		if (StringUtils.isNotBlank(message))
			return SUCCESS;
		else
			return ERROR;
	}
	
	private void updateBabyInfo(ConsultBabyInfo old,ConsultBabyInfo news){
		try {
			BeanUtils.copyProperties(news, old,new String[]{"id","userInfo"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传宝宝头像图片
	 * @return
	 * @throws IOException 
	 */
	private String uploadBabyHeadImg(String fileName) throws IOException{
		if(StringUtils.isNotBlank(imagedata)){
			if(fileName == null || "".equals(fileName)){
				fileName= "consultbaby" + DateManage.getDateStr("yyyyMMddhhmmss") +".jpg";
			}
			String fileAllName=dir+"/"+fileName;
			dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
			if(!EncryptUtil.uploadImage(imagedata,dir)){
				//fileName="";
			}
		}
		return fileName;
	}

	@Override
	public ConsultBabyInfo getModel() {
		return babyInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<ConsultBabyInfo> getBabyInfoList() {
		return babyInfoList;
	}

	public void setBabyInfoList(List<ConsultBabyInfo> babyInfoList) {
		this.babyInfoList = babyInfoList;
	}

	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}
}
