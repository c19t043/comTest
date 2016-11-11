package com.java.asqtest.service;

import java.util.List;

import com.java.asqtest.vo.AsqBeenOptions;
import com.java.asqtest.vo.AsqParentChildActivity;
import com.java.asqtest.vo.AsqQuestionRecord;
import com.java.asqtest.vo.AsqQuestions;
import com.java.asqtest.vo.AsqResultScoreEx;
import com.java.asqtest.vo.AsqResultScoreExUser;
import com.java.asqtest.vo.AsqTaoti;
import com.java.asqtest.vo.AsqTaotiAge;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.Service;

public interface AsqtestService extends Service{
	
	/*
	 * 测评项目-适用月龄
	 * ASQ-3 	2月-3月
	 */
	/**
	 * 获取测评项目-适用月龄列表
	 * @param model 分页Model
	 * @param asqTaotiAge 测评项目-适用月龄对象
	 * @return
	 */
	List<AsqTaotiAge> getAsqTaotiAges(PageSortModel model,AsqTaotiAge asqTaotiAge);
	/**
	 * 查询亲子活动列表
	 * @param model
	 * @param asqParentChildActivity
	 * @return
	 */
	List<AsqParentChildActivity> getAsqParentChildActivityList(PageSortModel model,AsqParentChildActivity asqParentChildActivity);
	/** 保存或更新活动
	 * @param asqParentChildActivity
	 * @return
	 */
	String saveOrUpdateAsqParentChildActivity(AsqParentChildActivity asqParentChildActivity);
	/**
	 * 保存or更新测评项目-适用月龄列表
	 * @param asqTaotiAge
	 * @return
	 */
	AsqTaotiAge saveOrUpdateAsqTaotiAge(AsqTaotiAge asqTaotiAge);
	/*
	 * 测评项目-标题
	 * ASQ-3年龄与发育进程问卷  沟通  
	 */
	/**
	 * 获取测评项目-标题列表  
	 * @param model
	 * @param asqTaoti
	 * @return
	 */
	List<AsqTaoti> getAsqTaotis(PageSortModel model,AsqTaoti asqTaoti);
	/**
	 * 保存or更新测评项目-标题
	 * @param asqTaotiAge
	 * @return
	 */
	AsqTaoti saveOrUpdateAsqTaoti(AsqTaoti asqTaoti);
	/*
	 * 测评项目-问题信息
	 * 沟通	宝宝会轻声咯咯笑吗？     单选     答案A
	 */
	/**
	 * 获取测评项目-问题信息列表
	 * @param model
	 * @param asqQuestions
	 * @return
	 */
	List<AsqQuestions> getAsqQuestions(PageSortModel model,AsqQuestions asqQuestions);
	/**
	 * 保存or更新测评项目-问题信息
	 * @param asqQuestions
	 * @return
	 */
	AsqQuestions saveOrUpdateAsqQuestions(AsqQuestions asqQuestions);
	/*
	 * 测评项目-问题信息-带选项
	 * 宝宝会轻声咯咯笑吗？     是       A	1分
	 */
	/**
	 * 获取测评项目-问题信息-带选项列表
	 * @param model
	 * @param asqBeenOptions
	 * @return
	 */
	List<AsqBeenOptions> getAsqBeenOptions(PageSortModel model,AsqBeenOptions asqBeenOptions);
	/**
	 * 保存or更新测评项目-问题信息-带选项
	 * @param asqBeenOptions
	 * @return
	 */
	AsqBeenOptions saveOrUpdateAsqBeenOptions(AsqBeenOptions asqBeenOptions);
	/*
	 * 测评阅卷
	 */
	/**
	 * 获取用户答题选项
	 * @param asqQuestionRecord
	 * @return
	 */
	List<AsqQuestionRecord> getAsqQuestionRecord(AsqQuestionRecord asqQuestionRecord);
	/**
	 * 获取测评得分列表
	 * @param model
	 * @param asqResultScoreEx
	 * @return
	 */
	List<AsqResultScoreEx> getAsqResultScoreEx(PageSortModel model,AsqResultScoreEx asqResultScoreEx);
	/**
	 * 获取测评得分按测试人分的列表
	 * @param model
	 * @param asqResultScoreEx
	 * @return
	 */
	List<AsqResultScoreExUser> getAsqResultScoreExUser(PageSortModel model,AsqResultScoreExUser asqResultScoreExUser);
	/**
	 * 保存or更新测评得分
	 * @param asqResultScoreEx
	 * @return
	 */
	AsqResultScoreEx saveOrUpdateAsqResultScoreEx(AsqResultScoreEx asqResultScoreEx);
}
