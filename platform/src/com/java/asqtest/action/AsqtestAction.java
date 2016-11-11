package com.java.asqtest.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.java.asqtest.service.AsqtestService;
import com.java.asqtest.vo.AsqBeenOptions;
import com.java.asqtest.vo.AsqQuestionRecord;
import com.java.asqtest.vo.AsqQuestions;
import com.java.asqtest.vo.AsqResultScoreEx;
import com.java.asqtest.vo.AsqResultScoreExUser;
import com.java.asqtest.vo.AsqTaoti;
import com.java.asqtest.vo.AsqTaotiAge;
import com.java.asqtest.vo.AsqTestUserInfo;
import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;
import com.java.publichealth.residentsfile.vo.KyUserInfo;
import com.java.util.EncryptUtil;

@SuppressWarnings("serial")
public class AsqtestAction extends Action{

	private AsqtestService asqtestService;

	private AsqTaotiAge asqTaotiAge;
	private AsqTaoti asqTaoti;
	private AsqQuestions asqQuestions;
	private List<AsqQuestions> asqQuestionslist;
	private AsqBeenOptions asqBeenOptions;
	private List<AsqBeenOptions> asqBeenOptionslist;
	private AsqResultScoreEx asqResultScoreEx;
	private AsqTestUserInfo asqTestUserInfo;
	private List<AsqQuestionRecord> asqQuestionRecordlist;
	private List<AsqTaoti> asqTaotislist;
	private List<AsqResultScoreEx> asqResultScoreExlist;
	private AsqResultScoreExUser asqResultScoreExUser;
	
	private List<AsqBeenOptions> asqBeenOptionsList;
	private final String uploadDir = "admin/images/asq";// 保存上传文件的目录
	/*
	 * 测评项目-适用月龄
	 * ASQ-3 	2月-3月
	 */
	public String queryAsqTaotiAges(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		List<AsqTaotiAge> asqTaotiAges = asqtestService.getAsqTaotiAges(model, asqTaotiAge);
		putToRequest("list", asqTaotiAges);
		return SUCCESS;
	}
	public String toAsqTaotiAgeSelectPage(){
		return queryAsqTaotiAges();
	}
	public String saveOrUpdateAsqTaotiAge(){
		asqTaotiAge.setTaoti(asqTaoti);
		asqtestService.saveOrUpdateAsqTaotiAge(asqTaotiAge);
		return redirectActionResult("AsqTaotiAge");
	}
	public String toAddOfAsqTaotiAge(){
		if(asqTaotiAge!=null&&asqTaotiAge.getId()!=null){
			asqTaotiAge = asqtestService.get(asqTaotiAge.getId(), AsqTaotiAge.class);
			asqTaoti = asqTaotiAge.getTaoti();
		}
		return SUCCESS;
	}
	/*
	 * 测评项目-标题
	 * ASQ-3年龄与发育进程问卷  沟通  
	 */
	public String queryAsqTaotis(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		List<AsqTaoti> asqTaotis = asqtestService.getAsqTaotis(model, asqTaoti);
		putToRequest("list", asqTaotis);
		return SUCCESS;
	}
	public String toAsqTaotiSelectPage(){
		return queryAsqTaotis();
	}
	public String toAddOfAsqTaoti(){
		if(asqTaoti!=null&&asqTaoti.getId()!=null){
			asqTaoti = asqtestService.get(asqTaoti.getId(), AsqTaoti.class);
		}
		return SUCCESS;
	}
	public String saveOrUpdateAsqTaoti(){
		asqTaoti.setImgPath(updateImage(asqTaoti.getId(), asqTaoti.getImgPath(), asqTaoti.getImgBase64()));
		asqtestService.saveOrUpdateAsqTaoti(asqTaoti);
		return redirectActionResult("AsqTaoti");
	}
	/*
	 * 测评项目-问题信息
	 * 沟通	宝宝会轻声咯咯笑吗？     单选     答案A
	 */
	public String queryAsqQuestions(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		List<AsqQuestions> asqQuestions_colls = asqtestService.getAsqQuestions(model, asqQuestions);
		putToRequest("list", asqQuestions_colls);
		return SUCCESS;
	}
	public String toAddOfAsqQuestions(){
		if(asqQuestions!=null&&asqQuestions.getId()!=null){
			asqQuestions = asqtestService.get(asqQuestions.getId(), AsqQuestions.class);
			asqBeenOptionsList = asqtestService.getAsqBeenOptions(null, new AsqBeenOptions(asqQuestions));
		}
		return SUCCESS;
	}
	public String toAsqQuestionsSelectPage(){
		return queryAsqQuestions();
	}
	public String saveOrUpdateAsqQuestions(){
		if(asqBeenOptionsList!=null){
			asqQuestions.setAsqBeenOptionsList(asqBeenOptionsList);
		}
		asqQuestions.setPicture(updateImage(asqQuestions.getId(), asqQuestions.getPicture(), asqQuestions.getImgBase64()));
		asqtestService.saveOrUpdateAsqQuestions(asqQuestions);
		return redirectActionResult("AsqQuestions");
	}
	/*
	 * 测评项目-问题信息-带选项
	 * 宝宝会轻声咯咯笑吗？     是       A	1分
	 */
	public String queryAsqBeenOptions(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		List<AsqBeenOptions> asqBeenOptions_colls = asqtestService.getAsqBeenOptions(model, asqBeenOptions);
		putToRequest("list", asqBeenOptions_colls);
		return SUCCESS;
	}
	public String toAddOfAsqBeenOptions(){
		if(asqBeenOptions!=null&&asqBeenOptions.getId()!=null){
			asqBeenOptions = asqtestService.get(asqBeenOptions.getId(), AsqBeenOptions.class);
		}
		return SUCCESS;
	}
	public String saveOrUpdateAsqBeenOptions(){
		asqtestService.saveOrUpdateAsqBeenOptions(asqBeenOptions);
		return redirectActionResult("AsqBeenOptions");
	}
	/*
	 * 测评阅卷
	 */
	public String queryAsqResultScoreEx(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		List<AsqResultScoreExUser> asqResultScoreExUser_colls = asqtestService.getAsqResultScoreExUser(model, asqResultScoreExUser);
		putToRequest("list", asqResultScoreExUser_colls);
		return SUCCESS;
	}
	public String toAddOfAsqResultScoreEx(){
		Long id = asqResultScoreExUser.getId();
		KyUserInfo userInfo = asqResultScoreExUser.getUserInfo();
		AsqResultScoreExUser asqResultScoreExuser = asqtestService.get(id, AsqResultScoreExUser.class);
		asqTaotiAge = asqResultScoreExuser.getAsqTaotiAge();//适用月龄
		asqTestUserInfo = asqResultScoreExuser.getAsqTestUserInfo();//测试用户信息
		/*
		 * 所有关联问题
		 * 所有关联配置子标题==>通过配置的问题,传入父标题,获取关联配置的子标题
		 */
		asqQuestionslist = asqtestService.getAsqQuestions(null, new AsqQuestions(asqTaotiAge));
		asqTaotislist = new ArrayList<AsqTaoti>();
		//所有关联问题选项
		StringBuilder qtIDs = new StringBuilder();
		for (AsqQuestions asqQuestions : asqQuestionslist) {
			//获取配置的子标题
			if(!asqTaotislist.contains(asqQuestions.getAsqTaoti())) asqTaotislist.add(asqQuestions.getAsqTaoti());
			qtIDs.append(asqQuestions.getId()).append(",");
		}
		if(qtIDs.toString().contains(",")) qtIDs = qtIDs.deleteCharAt(qtIDs.length()-1);
		asqBeenOptionslist = asqtestService.getAsqBeenOptions(null, new AsqBeenOptions(qtIDs.toString()));
		/*
		 * 对子标题排序
		 */
		Integer min_sort = null;
		for(int i=0;i<asqTaotislist.size();i++){
			AsqTaoti tt = asqTaotislist.get(i);
			if(min_sort==null) { min_sort = tt.getSort(); }
			for(int j=i;j<asqTaotislist.size();j++){
				AsqTaoti tmp_tt = asqTaotislist.get(j);
				Integer tmp_sort = tmp_tt.getSort();
				if(min_sort>tmp_sort){
					AsqTaoti tmp_obj = tt;
					tt = tmp_tt;
					tmp_tt = tmp_obj;
				}
			}
		}
		//所有问题选项记录
		asqQuestionRecordlist = asqtestService.getAsqQuestionRecord(new AsqQuestionRecord(asqTestUserInfo.getId()));
		/*
		 * 优化性能
		 */
		//子标题对象中包含对应所有问题
		for(AsqTaoti tt : asqTaotislist){
			List<AsqQuestions> qtList = new ArrayList<AsqQuestions>();
			for (AsqQuestions qt : asqQuestionslist) {
				if(tt.getId()==qt.getAsqTaoti().getId()){
					qtList.add(qt);
				}
			}
			tt.setAsqQuestionslist(qtList);
		}
		//问题关联对应选项
		for (AsqQuestions qt : asqQuestionslist) {
			List<AsqBeenOptions> boList = new ArrayList<AsqBeenOptions>();
			for(AsqBeenOptions abo : asqBeenOptionslist){
				if(qt.getId()==abo.getAsqQuestions().getId()){
					boList.add(abo);
				}
			}
			qt.setAsqBeenOptionsList(boList);
		}
		//关联问题是否是用户选择的选项
		for(AsqBeenOptions abo : asqBeenOptionslist){
			for(AsqQuestionRecord qr : asqQuestionRecordlist){
				if(abo.getAsqQuestions()!=null&&
						abo.getAsqQuestions().getId().toString().equals(qr.getAsqQuestionsId().toString())&&
						abo.getId().toString().equals(qr.getAsqBeenOptions().getId().toString())){
					abo.setIsOptionRecord("Y");
				}
			}
		}
		//获取测评结果记录
		asqResultScoreExlist = asqtestService.getAsqResultScoreEx(null, new AsqResultScoreEx(asqTestUserInfo,asqTaotiAge, userInfo));
		return SUCCESS;
	}
	public String saveOrUpdateAsqResultScoreEx(){
		if(asqResultScoreEx==null)asqResultScoreEx = new AsqResultScoreEx();
		if(asqTestUserInfo!=null){//医生解读
			asqResultScoreEx.setPage_asqTestUserInfo(asqTestUserInfo);
		}
		if(asqResultScoreExlist!=null){//对应子标题总评分
			asqResultScoreEx.setPage_asqResultScoreExlist(asqResultScoreExlist);
		}
		if(asqResultScoreExUser!=null){//总评价
			asqResultScoreEx.setPage_asqResultScoreExUser(asqResultScoreExUser);
		}
		asqtestService.saveOrUpdateAsqResultScoreEx(asqResultScoreEx);
		return redirectActionResult("AsqResultScore");
	}
	/**
	 * 上传图片
	 * @param id 对象ID
	 * @param imagePath 已有的图片路径
	 * @param imgBase64 base64编码后的图片
	 * @return
	 */
	private String updateImage(Long id,String imagePath,String imgBase64){
		String tempDir = "";
		if (id != null && StringUtils.isNotBlank(imagePath)) {
			tempDir = imagePath;
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String current = df.format(new Date());
			String bannerName = "fd" + current + ".jpg";
			tempDir = uploadDir + "/" + bannerName;
			imagePath = tempDir;
		}
		if (StringUtils.isNotBlank(imgBase64)) {
			String directory = ServletActionContext.getServletContext()
					.getRealPath(uploadDir + "/");
			File cacheDir = new File(directory);
			// 如果文件夹不存在则创建
			if (!cacheDir.exists() && !cacheDir.isDirectory()) {
				System.out.println("//不存在");
				cacheDir.mkdirs();
			} else {
				System.out.println("//目录存在");
			}
			// 上传图片
			String dir = ServletActionContext.getServletContext()
					.getRealPath(tempDir);
			try {
				EncryptUtil.uploadImage(imgBase64, dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imagePath;
	}
	
	
	
	
	
	
	
	
	
	

	public List<AsqTaoti> getAsqTaotislist() {
		return asqTaotislist;
	}
	public void setAsqTaotislist(List<AsqTaoti> asqTaotislist) {
		this.asqTaotislist = asqTaotislist;
	}
	public List<AsqBeenOptions> getAsqBeenOptionsList() {
		return asqBeenOptionsList;
	}
	public void setAsqBeenOptionsList(List<AsqBeenOptions> asqBeenOptionsList) {
		this.asqBeenOptionsList = asqBeenOptionsList;
	}
	public void setAsqtestService(AsqtestService asqtestService) {
		this.asqtestService = asqtestService;
	}
	public AsqTaotiAge getAsqTaotiAge() {
		return asqTaotiAge;
	}
	public void setAsqTaotiAge(AsqTaotiAge asqTaotiAge) {
		this.asqTaotiAge = asqTaotiAge;
	}
	public AsqTaoti getAsqTaoti() {
		return asqTaoti;
	}
	public void setAsqTaoti(AsqTaoti asqTaoti) {
		this.asqTaoti = asqTaoti;
	}
	public AsqQuestions getAsqQuestions() {
		return asqQuestions;
	}
	public void setAsqQuestions(AsqQuestions asqQuestions) {
		this.asqQuestions = asqQuestions;
	}
	public AsqBeenOptions getAsqBeenOptions() {
		return asqBeenOptions;
	}
	public void setAsqBeenOptions(AsqBeenOptions asqBeenOptions) {
		this.asqBeenOptions = asqBeenOptions;
	}
	public AsqResultScoreEx getAsqResultScoreEx() {
		return asqResultScoreEx;
	}
	public void setAsqResultScoreEx(AsqResultScoreEx asqResultScoreEx) {
		this.asqResultScoreEx = asqResultScoreEx;
	}
	public List<AsqQuestions> getAsqQuestionslist() {
		return asqQuestionslist;
	}
	public void setAsqQuestionslist(List<AsqQuestions> asqQuestionslist) {
		this.asqQuestionslist = asqQuestionslist;
	}
	public AsqTestUserInfo getAsqTestUserInfo() {
		return asqTestUserInfo;
	}
	public void setAsqTestUserInfo(AsqTestUserInfo asqTestUserInfo) {
		this.asqTestUserInfo = asqTestUserInfo;
	}
	public List<AsqBeenOptions> getAsqBeenOptionslist() {
		return asqBeenOptionslist;
	}
	public void setAsqBeenOptionslist(List<AsqBeenOptions> asqBeenOptionslist) {
		this.asqBeenOptionslist = asqBeenOptionslist;
	}
	public List<AsqQuestionRecord> getAsqQuestionRecordlist() {
		return asqQuestionRecordlist;
	}
	public void setAsqQuestionRecordlist(
			List<AsqQuestionRecord> asqQuestionRecordlist) {
		this.asqQuestionRecordlist = asqQuestionRecordlist;
	}
	public List<AsqResultScoreEx> getAsqResultScoreExlist() {
		return asqResultScoreExlist;
	}
	public void setAsqResultScoreExlist(List<AsqResultScoreEx> asqResultScoreExlist) {
		this.asqResultScoreExlist = asqResultScoreExlist;
	}
	public AsqResultScoreExUser getAsqResultScoreExUser() {
		return asqResultScoreExUser;
	}
	public void setAsqResultScoreExUser(AsqResultScoreExUser asqResultScoreExUser) {
		this.asqResultScoreExUser = asqResultScoreExUser;
	}
}
