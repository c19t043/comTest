package com.kybaby.newbussiness.asqtest.bo;

import java.util.List;

import net.sf.json.JSONArray;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.asqtest.domain.AsqParentChildActivity;
import com.kybaby.newbussiness.asqtest.domain.AsqQuestionRecord;
import com.kybaby.newbussiness.asqtest.domain.AsqResultScoreEx;
import com.kybaby.newbussiness.asqtest.domain.AsqTaoti;
import com.kybaby.newbussiness.asqtest.domain.AsqTaotiAge;
import com.kybaby.newbussiness.asqtest.domain.AsqTestUserInfo;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrder;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;

public interface AsqService {
	/**
	 * 得到适合当前测试者信息的试题（看月龄）
	 * @param monthAge
	 * @return
	 */
	List<AsqTaotiAge> getAsqTaotiAgeList(Integer monthAge,AsqTaoti parentTaoti);
	/**
	 * 得到试题分类
	 * @param parentTaotiId
	 * @return
	 */
	List<AsqTaoti> getChildAsqTaotiList(Long parentTaotiId);
	/**
	 * 
	 * @param array 作答题目数组
	 * @param userInfo 登录用户
	 * @param asqTestUserInfo 测评用户
	 */
	void addTestQuestionRecord(JSONArray array,UserInfo userInfo,AsqTestUserInfo asqTestUserInfo,
			B2cGoodsOrder b2cGoodsOrder,FdServicePackage fdServicePackage);
	/**
	 * 得到问题回答记录列表
	 * @param asqQuestionRecord
	 * @return
	 */
	List<AsqQuestionRecord> getAsqQuestionRecordList(AsqQuestionRecord asqQuestionRecord);
	/**
	 * 得到测评结果分类记录信息
	 * @param asqResultScoreEx
	 * @return
	 */
	List<AsqResultScoreEx> getAsqResultScoreExList(AsqResultScoreEx asqResultScoreEx,Long b2cGoodsOrderId,Long fdServicePackageId);
	/**
	 * 得到测评结果信息
	 * @param id
	 * @return
	 */
	AsqResultScoreEx getAsqResultScoreExById(Long id);
	/**
	 * 根据id得到实体
	 * @param id
	 * @return
	 */
	AsqTaotiAge getAsqTaotiAgeId(Long id);
	/**
	 * 得到测评结果亲子活动列表
	 * @param asqParentChildActivity
	 * @return
	 */
	List<AsqParentChildActivity> getAsqParentChildActivityList(AsqParentChildActivity asqParentChildActivity,AsqTestUserInfo asqTestUserInfo);
	/**
	 * 得到用户做了几次测试题
	 * @param userInfo
	 * @return
	 */
	Long getTestCountByUser(UserInfo userInfo ,Long b2cOrderId);
	/**
	 * 检查答案关联性、约束关系
	 * @param array
	 * @return
	 */
	String checkAsqAnswner(JSONArray array,Long asqTaotiAgeId);
}
