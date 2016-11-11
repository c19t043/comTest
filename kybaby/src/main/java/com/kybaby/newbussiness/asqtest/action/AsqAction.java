package com.kybaby.newbussiness.asqtest.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.asqtest.domain.AsqParentChildActivity;
import com.kybaby.newbussiness.asqtest.domain.AsqQuestionRecord;
import com.kybaby.newbussiness.asqtest.domain.AsqQuestions;
import com.kybaby.newbussiness.asqtest.domain.AsqResultScoreEx;
import com.kybaby.newbussiness.asqtest.domain.AsqTaoti;
import com.kybaby.newbussiness.asqtest.domain.AsqTaotiAge;
import com.kybaby.newbussiness.asqtest.domain.AsqTestUserInfo;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrder;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrderDetail;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsProperty;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsPropertyValue;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsSku;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.opensymphony.xwork2.ActionContext;

public class AsqAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 测试用户信息
	 */
	private AsqTestUserInfo asqTestUserInfo;
	private List<AsqTaoti> taotiList;
	private AsqTaotiAge asqTaotiAge;
	/**
	 * 订单结果记录信息json字符串
	 */
	private String orderResultsJson;
	/**
	 * 测评结果记录列表
	 */
	private List<AsqResultScoreEx> asqResultScoreExList;
	/**
	 * 测评结果记录
	 */
	private AsqResultScoreEx asqResultScoreEx;
	/**
	 * 亲子活动列表
	 */
	private List<AsqParentChildActivity> asqParentChildActivityList;
	private B2cGoodsOrder b2cGoodsOrder;
	private FdServicePackage fdServicePackage;
	public String execute(){
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		if(userId == null){
			this.mes = "请登录";
			return "fail";
		}
		//得到套题类型列表
		if("getParentTaotiList".equals(action)){
			this.taotiList = this.asqService.getChildAsqTaotiList(null);
		}
		//得到适合当前测试者月龄的套题
		else if("getTaotiForAge".equals(action)){
			AsqTestUserInfo asqTestUserInfoOld = this.asqTestUserInfoService.getAsqTestUserInfoById(asqTestUserInfo.getId());
			int monthAge = asqTestUserInfoOld.getCurrentMonthAge();
			List<AsqTaotiAge> taotiAgeList = this.asqService.getAsqTaotiAgeList(monthAge,asqTaotiAge.getTaoti());
			if(taotiAgeList != null){
				asqTaotiAge = taotiAgeList.get(0);//大标题显示用父级的
				taotiList = this.asqService.getChildAsqTaotiList(asqTaotiAge.getTaoti().getId());
			}
		}
		//保存用户的答题记录
		else if("saveResultRecode".equals(action)){
			JSONArray array = JSONArray.fromObject(orderResultsJson); 
			System.out.println(array);
			UserInfo userInfo = new UserInfo();
			userInfo.setId(userId);
			this.asqService.addTestQuestionRecord(array, userInfo, asqTestUserInfo,b2cGoodsOrder,fdServicePackage);
		}
		//得到用户测评结果
		else if("getAsqResultScoreExList".equals(action)){
			AsqResultScoreEx asqResultScoreEx = new AsqResultScoreEx(); 
			UserInfo userInfo = new UserInfo();
			userInfo.setId(userId);
			asqResultScoreEx.setUserInfo(userInfo);
			List<AsqResultScoreEx> asqResultScoreExList = 
					this.asqService.getAsqResultScoreExList(asqResultScoreEx,b2cGoodsOrder==null?null:b2cGoodsOrder.getId(),
							fdServicePackage==null?null:fdServicePackage.getId());
			Map<Long,AsqResultScoreEx> taotiAgeMap = new LinkedHashMap<>();
			if(asqResultScoreExList != null){
				this.asqResultScoreExList = new ArrayList<>();
				for(AsqResultScoreEx arse : asqResultScoreExList){
					taotiAgeMap.put(arse.getAsqTestUserInfo().getId(), arse);
				}
				for (Map.Entry<Long,AsqResultScoreEx> entry : taotiAgeMap.entrySet()) {  
					this.asqResultScoreExList.add(entry.getValue());
				}
			}
		}
		//得到用户测评明细信息
		else if("getAsqResultDetail".equals(action)){
			AsqResultScoreEx asqResultScoreExOld = this.asqService.getAsqResultScoreExById(asqResultScoreEx.getId());
			//基本信息
			this.asqResultScoreEx = asqResultScoreExOld;
			//发育问卷
			this.taotiList = this.asqService.getChildAsqTaotiList(asqResultScoreExOld.getAsqTaotiAge().getTaoti().getId());
			AsqQuestionRecord asqQuestionRecord = new AsqQuestionRecord(); 
			asqQuestionRecord.setAsqTestUserInfo(asqResultScoreExOld.getAsqTestUserInfo());
			for(AsqTaoti taoti : taotiList){
				Iterator<AsqQuestions> questionIt = taoti.getAsqQuestionsSet().iterator();
				while (questionIt.hasNext()) {
					AsqQuestions asqQuestions = questionIt.next();
					asqQuestionRecord.setAsqQuestionsId(asqQuestions.getId());
					List<AsqQuestionRecord> asqQuestionRecordList = this.asqService.getAsqQuestionRecordList(asqQuestionRecord);
					if(asqQuestionRecordList != null){
						asqQuestions.setAsqQuestionRecordList(asqQuestionRecordList);
					}
				}
			}
			//信息汇总
			AsqResultScoreEx asqResultScoreExCondition = new AsqResultScoreEx(); 
			asqResultScoreExCondition.setAsqTaotiAge(asqResultScoreExOld.getAsqTaotiAge());
			asqResultScoreExCondition.setAsqTestUserInfo(asqResultScoreExOld.getAsqTestUserInfo());
			this.asqResultScoreExList = this.asqService.getAsqResultScoreExList(asqResultScoreExCondition,null,null);
			//亲子活动
			AsqParentChildActivity asqParentChildActivity = new AsqParentChildActivity();
			asqParentChildActivity.setAsqTaotiId(asqResultScoreExOld.getAsqTaotiAge().getTaoti().getId());
			this.asqParentChildActivityList = 
					this.asqService.getAsqParentChildActivityList(asqParentChildActivity, asqResultScoreExOld.getAsqTestUserInfo());
		}
		//判断当前用户购买的测评次数是否使用完
		else if("checkCountisOver".equals(action)){
			B2cGoodsOrder oldOrder = this.goodsOrderService.getB2cGoodsOrderById(b2cGoodsOrder.getId());
			UserInfo userInfo = new UserInfo();
			userInfo.setId(userId);
			//得到当前用户的测评使用次数
			Long usedCount = this.asqService.getTestCountByUser(userInfo,oldOrder.getId());
			long buyCount = this.goodsService.goodsServicesCount(b2cGoodsOrder);
			if(usedCount.longValue() >= buyCount){
				this.mes = "服务次数已使用完,请重新购买";
			}
		}
		//检查答案关联性、约束关系
		else if("checkAsqAnswner".equals(action)){
			JSONArray array = JSONArray.fromObject(orderResultsJson); 
			System.out.println(array);
			this.mes = this.asqService.checkAsqAnswner(array, asqTaotiAge.getId());
		}
		return SUCCESS;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public void setAsqTestUserInfo(AsqTestUserInfo asqTestUserInfo) {
		this.asqTestUserInfo = asqTestUserInfo;
	}
	public AsqTestUserInfo getAsqTestUserInfo() {
		return asqTestUserInfo;
	}
	public List<AsqTaoti> getTaotiList() {
		return taotiList;
	}
	public AsqTaotiAge getAsqTaotiAge() {
		return asqTaotiAge;
	}
	public void setOrderResultsJson(String orderResultsJson) {
		this.orderResultsJson = orderResultsJson;
	}
	public List<AsqResultScoreEx> getAsqResultScoreExList() {
		return asqResultScoreExList;
	}
	public AsqResultScoreEx getAsqResultScoreEx() {
		return asqResultScoreEx;
	}
	public void setAsqResultScoreEx(AsqResultScoreEx asqResultScoreEx) {
		this.asqResultScoreEx = asqResultScoreEx;
	}
	public List<AsqParentChildActivity> getAsqParentChildActivityList() {
		return asqParentChildActivityList;
	}
	public void setB2cGoodsOrder(B2cGoodsOrder b2cGoodsOrder) {
		this.b2cGoodsOrder = b2cGoodsOrder;
	}
	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}
	public void setAsqTaotiAge(AsqTaotiAge asqTaotiAge) {
		this.asqTaotiAge = asqTaotiAge;
	}
	
}
