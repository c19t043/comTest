package com.kybaby.newbussiness.asqtest.dao;

import java.util.List;

import com.kybaby.domain.UserInfo;
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

public interface AsqDao {
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
	 * 保存或记录答题结果记录
	 * @param asqQuestionRecord
	 * @return
	 */
	Long saveOrUpdateAsqQuestionRecord(AsqQuestionRecord asqQuestionRecord);
	/**
	 * 保存或更新测评总得分说明
	 * @param asqQuestionRecord
	 * @return
	 */
	Long saveOrUpdateAsqResultScoreEx(AsqResultScoreEx asqResultScoreEx);
	/**
	 * 保存或更新测评总得分说明
	 * @param asqQuestionRecord
	 * @return
	 */
	Long saveOrUpdateAsqResultScoreExUser(AsqResultScoreExUser asqResultScoreExUser);
	/**
	 * 得到一个选项实体
	 * @param id
	 * @return
	 */
	AsqBeenOptions getAsqBeenOptionsById(Long id);
	/**
	 * 得到一个问题
	 * @param id
	 * @return
	 */
	AsqQuestions getAsqQuestionsById(Long id);
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
	 * 得到问题回答记录列表
	 * @param asqQuestionRecord
	 * @return
	 */
	List<AsqQuestionRecord> getAsqQuestionRecordList(AsqQuestionRecord asqQuestionRecord);
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
	 * 得到约束配置列表
	 * @return
	 */
	List<AsqAnswnerChecked> getAsqAnswnerCheckedList(Long asqTaotiAgeId);
	/**
	 * 根据id得到实体
	 * @param id
	 * @return
	 */
	AsqTaotiAge getAsqTaotiAgeId(Long id);
}
