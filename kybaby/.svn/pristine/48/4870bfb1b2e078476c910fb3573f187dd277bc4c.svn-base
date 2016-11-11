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
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.GrowthRecord;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GrowthRecordManage
 * @Description:成长记录相关的
 * @author Hoolee
 * @date 2015年10月13日下午11:28:30
 */
public class GrowthRecordManage extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈给前端的提示信息
	private String dir="../kybabyBG/admin/images/growthrecord";
	private SimpleDateFormat df;
	private String current;

	private String sleepHour;// 睡眠小时数
	private String everyBreastfeeding;// 每次母乳进食量
	private long BreastfeedingTimes;// 母乳进食次数
	private long assistFoodsTimes;// 辅食进食次数
	private long defecateTimes; //排便次数
	private String exerciseTimes;// 运动次数
	private String imagedata;//上传的图片文件
	/**
	 * 成长记录信息
	 */
	private GrowthRecord growthRecord;
	
	public String execute() throws IOException{
		if(action.equals("add")){
			System.out.println("ADD is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				String fileName="";
				/*if(productSmallFileElem2!=null){
					df = new SimpleDateFormat("yyyyMMddhhmmss");
					current=df.format(new Date());
					fileName=userId+ "pic" + current +".jpg";
					String tempDir = dir + "/" + fileName;
					dir = ServletActionContext.getServletContext().getRealPath(tempDir);
					di = new File(dir);
					copy(productSmallFileElem2, di); 
				}*/
				//更新图片的上传方式为BASE64 2015年10月30日22:23:17 update by HooLee
				df = new SimpleDateFormat("yyyyMMddhhmmss");
				current=df.format(new Date());
				fileName=userId +"image" + current +".jpg";
				String fileAllName=dir+"/"+fileName;
				dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
				if(imagedata.equals("false")||!EncryptUtil.uploadImage(imagedata,dir)){
					fileName="";
				}
				GrowthRecord record=new GrowthRecord();
				if(growthRecord.getId() == null){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date rightNow=new Date(System.currentTimeMillis());
					String dateNow=sdf.format(rightNow);
					record.setRecordDate(dateNow);
				}else{
					record = this.growthRecordBo.getGrowthRecordById(growthRecord.getId());
				}
				record.setUploadImage(fileName);
				record.setAssistFoodsTimes(assistFoodsTimes);
				record.setBreastfeedingTimes(BreastfeedingTimes);
				record.setDefecateTimes(defecateTimes);
				record.setEveryBreastfeeding(everyBreastfeeding);
				record.setExerciseTimes(exerciseTimes);
				record.setUserId(userId);
				record.setSleepHour(sleepHour);
				growthRecordBo.addNewRecord(record);
				mes="操作成功";
				return "success";
			}
			mes="未登录";
			return "fail";
		}
		/**
		 * 得到成长信息
		 */
		else if(action.equals("getGrowthRecordById")){
			this.growthRecord = growthRecordBo.getGrowthRecordById(growthRecord.getId());
			mes="操作成功";
			return "success";
		}
		return "fail";
	}
	
	public void setSleepHour(String sleepHour) {
		this.sleepHour = sleepHour;
	}

	public void setEveryBreastfeeding(String everyBreastfeeding) {
		this.everyBreastfeeding = everyBreastfeeding;
	}

	public void setExerciseTimes(String exerciseTimes) {
		this.exerciseTimes = exerciseTimes;
	}
	
	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}
	
	public String getMes() {
		return mes;
	}
	public void setProductSmallFileElem2(File productSmallFileElem2) {
	}
	public void setBreastfeedingTimes(long breastfeedingTimes) {
		BreastfeedingTimes = breastfeedingTimes;
	}
	public void setAssistFoodsTimes(long assistFoodsTimes) {
		this.assistFoodsTimes = assistFoodsTimes;
	}
	public void setDefecateTimes(long defecateTimes) {
		this.defecateTimes = defecateTimes;
	}

	public GrowthRecord getGrowthRecord() {
		return growthRecord;
	}

	public void setGrowthRecord(GrowthRecord growthRecord) {
		this.growthRecord = growthRecord;
	}

}
