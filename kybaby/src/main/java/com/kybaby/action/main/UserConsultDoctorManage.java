package com.kybaby.action.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.familydoctor.domain.DoctorWorkTime;
import com.kybaby.util.DateManage;
import com.kybaby.util.EncryptUtil;
import com.kybaby.util.SendSms;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:UserConsultDoctorManage
 * @Description:用户咨询医生相关的
 * @author Hoolee
 * @date 2015年10月12日下午5:21:00
 */
public class UserConsultDoctorManage extends BaseAction {

	

	private static final long serialVersionUID = 1L;
	private String mes;//反馈给前端的提示信息
	private File productSmallFileElem;
	private String tagIds;//选中的咨询症状标签
	private String description;//输入的症状描述
	private String dir="../kybabyBG/admin/images/consultPicture";
	private File di;
	private long doctorId;
	
	private SimpleDateFormat df;
	private String current;
	
	private String isBefore;  // add by fkn 
	
	private String imagedata;//前端传递的图片数据
	/**
	 * 用户类型（0:普通用户咨询；1：家庭医生签约用户咨询；）
	 */
	private String userType;
	/**
	 * 签约服务包id
	 */
	private Long fdPackageId;
	private Long logId;
	
	public String execute() throws IOException{
		if(action.equals("addNewTags")){//选择针状标签之后
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				symptomTagBo.updateSymptomStatusByIds(tagIds);//增加标签点击的次数'
				String fileName="";
				df = new SimpleDateFormat("yyyyMMddhhmmss");
				current=df.format(new Date());
				fileName=userId +"image" + current +".jpg";
				String fileAllName=dir+"/"+fileName;
				dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
				if(!EncryptUtil.uploadImage(imagedata,dir)){
					fileName="";
				}
				userConsultDoctorBo.addNewUserConsult(userId, doctorId, tagIds, fileName, description, "1", isBefore,userType,fdPackageId);
				List<UserConsultDoctor>  histConsultList_first = userConsultDoctorBo.
						getConsultListBySomething(userId, doctorId, 
								null, userType, "N","N", "",fdPackageId);
				if(histConsultList_first != null){
					this.logId = histConsultList_first.get(0).getLogId();
				}
				//发送短信提醒
				UserInfo usr = this.userInfoBo.getUserById(userId);
				DoctorInfo doc = this.doctorInfoBo.getDoctorInfoByDoctorId(doctorId);
				Boolean flag = this.getWorkDoctorIds(doc);
				if(flag){
					String toDoctorSms="亲爱的"+doc.getDoctorName()+"医生，用户" + (usr.getBabyName()==null ? "" : usr.getBabyName()) +"正在康优宝贝平台向您咨询问题，请尽快登录康优宝贝平台进行处理，请务必在15分钟内给予用户回复，谢谢！";
					System.out.print("咨询 toDoctor=="+toDoctorSms);
					SendSms sendSms = new SendSms();
					sendSms.sendInfo(doc.getDoctorPhone(), toDoctorSms);
				}
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		}
		return "fail";
	}
	
    private static void copy(File src, File dst) {
        InputStream in = null; 
        OutputStream out = null; 
        try { 
            in = new BufferedInputStream(new FileInputStream(src), 2048); 
            out = new BufferedOutputStream(new FileOutputStream(dst),2048); 
            byte[] buffer = new byte[2048]; 
            int len = 0; 
            while ((len = in.read(buffer)) > 0) { 
                out.write(buffer, 0, len); 

            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            if (null != in) { 
                try { 
                    in.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
            if (null != out) { 
                try { 
                    out.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
        } 
    } 
    /**
     * 得到上班时间的医生列表
     * @return
     */
	private Boolean getWorkDoctorIds(DoctorInfo doctor){
		Boolean flag = true;
		List<DoctorWorkTime> doctorWorkTimeList = this.userConsultDoctorBo.getDoctorWorkTimeList(null,doctor);
		if(doctorWorkTimeList != null){
			String nowTime = DateManage.getDateStr("HHmm");
			for(DoctorWorkTime dwt : doctorWorkTimeList){
				//看当前时间是否在工作时间内
				String startTime = dwt.getOnWorkTime();
				startTime = startTime.replaceAll(" ", "").replaceAll("-", "").replaceAll(":", "");
				String endTime = dwt.getOffWorkTime();
				endTime = endTime.replaceAll(" ", "").replaceAll("-", "").replaceAll(":", "");
				if(Long.valueOf(nowTime).longValue() >= Long.valueOf(startTime).longValue() && 
						Long.valueOf(nowTime).longValue() <= Long.valueOf(endTime).longValue() ){
					flag = true;
					break;
				}else{
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	public String getMes() {
		return mes;
	}
	public void setProductSmallFileElem(File productSmallFileElem) {
		this.productSmallFileElem = productSmallFileElem;
	}
	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setIsBefore(String isBefore) {
		this.isBefore = isBefore;
	}

	public String getIsBefore() {
		return isBefore;
	}
	
	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setFdPackageId(Long fdPackageId) {
		this.fdPackageId = fdPackageId;
	}

	public Long getLogId() {
		return logId;
	}
}
