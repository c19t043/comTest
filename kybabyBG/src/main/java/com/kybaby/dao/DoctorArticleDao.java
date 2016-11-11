package com.kybaby.dao;

import java.util.List;

import com.kybaby.domain.DoctorArticle;

public interface DoctorArticleDao {

	//2.7.4专栏管理
	List getAllDoctorArticle(); //返回所有医生文章，选择字段日期，医生名字，专栏标题，审核状态
	List getOneDoctorArticleById(long id);//通过文章id，返回此文章日期，姓名，文章标题，状态，文章内容
	DoctorArticle getDoctorArticleById(long id);//通过文章id找到此实例
	Long getArticleCommentsHitByStatus(Long id,String string);
}
