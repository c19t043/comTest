package com.kybaby.newbussiness.asqtest.bo.impl;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.asqtest.bo.AsqService;
import com.kybaby.newbussiness.asqtest.dao.AsqDao;
import com.kybaby.newbussiness.asqtest.domain.AsqAnswnerChecked;
import com.kybaby.newbussiness.asqtest.domain.AsqBeenOptions;
import com.kybaby.newbussiness.asqtest.domain.AsqParentChildActivity;
import com.kybaby.newbussiness.asqtest.domain.AsqQuestionRecord;
import com.kybaby.newbussiness.asqtest.domain.AsqQuestions;
import com.kybaby.newbussiness.asqtest.domain.AsqResultScoreEx;
import com.kybaby.newbussiness.asqtest.domain.AsqResultScoreExUser;
import com.kybaby.newbussiness.asqtest.domain.AsqTaoti;
import com.kybaby.newbussiness.asqtest.domain.AsqTaotiAge;
import com.kybaby.newbussiness.asqtest.domain.AsqTestUserInfo;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrder;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;

public class AsqServiceImpl implements AsqService{
	private AsqDao asqDao;

	public AsqDao getAsqDao() {
		return asqDao;
	}

	public void setAsqDao(AsqDao asqDao) {
		this.asqDao = asqDao;
	}

	@Override
	public List<AsqTaotiAge> getAsqTaotiAgeList(Integer monthAge,AsqTaoti parentTaoti) {
		return asqDao.getAsqTaotiAgeList(monthAge,parentTaoti);
	}

	@Override
	public List<AsqTaoti> getChildAsqTaotiList(Long parentTaotiId) {
		return asqDao.getChildAsqTaotiList(parentTaotiId);
	}

	@Override
	public void addTestQuestionRecord(JSONArray array, UserInfo userInfo,
			AsqTestUserInfo asqTestUserInfo,B2cGoodsOrder b2cGoodsOrder,FdServicePackage fdServicePackage) {
		if(array == null) return;
		AsqBeenOptions options = new AsqBeenOptions();
		AsqTaoti taoti = new AsqTaoti();
		Map<Long,AsqTaotiAge> taotiMap = new LinkedHashMap<>();
		Map<Long,AsqTaotiAge> taotiAgeMap = new LinkedHashMap<>();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		for(int i = 0; i < array.size(); i++){
			JSONObject jo = array.getJSONObject(i);
			String optionId = jo.get("optionId")==null?"":jo.get("optionId").toString().trim();
			String [] optionIdArr = optionId.split(",");
			for (int j = 0; j < optionIdArr.length; j++) {
				AsqQuestionRecord asqQuestionRecord = new AsqQuestionRecord();
				String questionId = jo.get("questionId")==null?"":jo.get("questionId").toString().trim();
				AsqQuestions asqQuestions = this.asqDao.getAsqQuestionsById(Long.valueOf(questionId));
				taotiMap.put(asqQuestions.getAsqTaoti().getId(), asqQuestions.getAsqTaotiAge());
				taotiAgeMap.put(asqQuestions.getAsqTaotiAge().getId(), asqQuestions.getAsqTaotiAge());
				String answerRemark = jo.get("answerRemark")==null?"":jo.get("answerRemark").toString().trim();
				asqQuestionRecord.setAsqQuestionsId(Long.valueOf(questionId));
				asqQuestionRecord.setAsqTestUserInfo(asqTestUserInfo);
				asqQuestionRecord.setAnswerRemark(answerRemark);
				asqQuestionRecord.setCreateTime( ts);
				asqQuestionRecord.setIsdelete(0);
				asqQuestionRecord.setModifyTime( ts);
				options = this.asqDao.getAsqBeenOptionsById(Long.valueOf(optionIdArr[j]));
				asqQuestionRecord.setAsqBeenOptions(options);
				asqQuestionRecord.setScore(options.getOptionScore());
				this.asqDao.saveOrUpdateAsqQuestionRecord(asqQuestionRecord);
			}
		}
		if(!taotiMap.isEmpty()){
			for (Map.Entry<Long,AsqTaotiAge> entry : taotiMap.entrySet()) {  
				AsqResultScoreEx asqResultScoreEx = new AsqResultScoreEx();
				taoti.setId(entry.getKey());
				asqResultScoreEx.setAsqTaoti(taoti);
				asqResultScoreEx.setAsqTaotiAge(entry.getValue());
				asqResultScoreEx.setAsqTestUserInfo(asqTestUserInfo);
				asqResultScoreEx.setResultDescription("");
				asqResultScoreEx.setStatus(AsqResultScoreEx.WAIT_CHECK);
				asqResultScoreEx.setSumScore("");
				asqResultScoreEx.setUserInfo(userInfo);
				asqResultScoreEx.setCreateTime( ts);
				asqResultScoreEx.setModifyTime( ts);
				asqResultScoreEx.setB2cGoodsOrderId(b2cGoodsOrder==null?null:b2cGoodsOrder.getId());
				this.asqDao.saveOrUpdateAsqResultScoreEx(asqResultScoreEx);
			}
		}
		if(!taotiAgeMap.isEmpty()){
			for (Map.Entry<Long,AsqTaotiAge> entry : taotiAgeMap.entrySet()) {  
				AsqResultScoreExUser asqResultScoreExUser = new AsqResultScoreExUser();
				asqResultScoreExUser.setAsqTaotiAge(entry.getValue());
				asqResultScoreExUser.setAsqTestUserInfo(asqTestUserInfo);
				asqResultScoreExUser.setResultDescription("");
				asqResultScoreExUser.setStatus(AsqResultScoreEx.WAIT_CHECK);
				asqResultScoreExUser.setSumScore("");
				asqResultScoreExUser.setUserInfo(userInfo);
				asqResultScoreExUser.setCreateTime( ts);
				asqResultScoreExUser.setModifyTime( ts);
				this.asqDao.saveOrUpdateAsqResultScoreExUser(asqResultScoreExUser);
			}
		}
	}

	@Override
	public List<AsqResultScoreEx> getAsqResultScoreExList(
			AsqResultScoreEx asqResultScoreEx,Long b2cGoodsOrderId,Long fdServicePackageId) {
		return this.asqDao.getAsqResultScoreExList(asqResultScoreEx, b2cGoodsOrderId, fdServicePackageId);
	}

	@Override
	public AsqResultScoreEx getAsqResultScoreExById(Long id) {
		return this.asqDao.getAsqResultScoreExById(id);
	}

	@Override
	public List<AsqQuestionRecord> getAsqQuestionRecordList(
			AsqQuestionRecord asqQuestionRecord) {
		return this.asqDao.getAsqQuestionRecordList(asqQuestionRecord);
	}

	@Override
	public List<AsqParentChildActivity> getAsqParentChildActivityList(
			AsqParentChildActivity asqParentChildActivity,
			AsqTestUserInfo asqTestUserInfo) {
		return this.asqDao.getAsqParentChildActivityList(asqParentChildActivity, asqTestUserInfo);
	}

	@Override
	public Long getTestCountByUser(UserInfo userInfo ,Long b2cOrderId) {
		return this.asqDao.getTestCountByUser(userInfo , b2cOrderId);
	}

	@Override
	public String checkAsqAnswner(JSONArray array,Long asqTaotiAgeId) {
		String returnStr = "成功";
		if(array == null) return returnStr;
		List<AsqAnswnerChecked> asqAnswnerCheckedList = asqDao.getAsqAnswnerCheckedList( asqTaotiAgeId);
		if(asqAnswnerCheckedList != null){
			for(AsqAnswnerChecked check : asqAnswnerCheckedList){
				String qc1 = check.getQc1();
				String qc2 = check.getQc2();
				String wearing = check.getRelation();
				for(int i = 0; i < array.size(); i++){
					JSONObject jo = array.getJSONObject(i);
					String questionId = jo.get("questionId")==null?"":jo.get("questionId").toString().trim();
					String optionId = jo.get("optionId")==null?"":jo.get("optionId").toString().trim();
					String oldq_c = questionId + ":" + optionId;
					if(oldq_c.equals(qc1)){
						for(int j = 0; j < array.size(); j++){
							JSONObject jo_ = array.getJSONObject(i);
							String questionId_ = jo_.get("questionId")==null?"":jo_.get("questionId").toString().trim();
							String optionId_ = jo_.get("optionId")==null?"":jo_.get("optionId").toString().trim();
							String oldq_c_ = questionId_ + ":" + optionId_;
							if(oldq_c_.equals(qc2)){
								returnStr = wearing;
								break;
							}
						}
					}
				}
			}
			
		}
		return returnStr;
	}

	@Override
	public AsqTaotiAge getAsqTaotiAgeId(Long id) {
		return this.asqDao.getAsqTaotiAgeId(id);
	}
}
