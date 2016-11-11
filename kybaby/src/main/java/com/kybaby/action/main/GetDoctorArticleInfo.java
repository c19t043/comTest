package com.kybaby.action.main;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorArticle;

/**
 * @ClassName:GetDoctorArticleInfo
 * @Description:医生个人专栏相关相关的
 * @author Hoolee
 * @date 2015年10月6日下午6:28:55
 */
public class GetDoctorArticleInfo extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private String mes;//反馈给前端的提示信息
	private long articleId;//前端传递的个人专栏ID
	private DoctorArticle someArticle;//反馈给前端的个人专栏实例
	private List<Object[]> articleCommentsList=new ArrayList<Object[]>();//医生个人专栏的评论列表
	
	public String execute(){
		if(action.equals("getArticleInstance")){
			System.out.println("GetArticleInstance is begining...");
			someArticle=doctorArticleBo.getDoctorArticleInstance(articleId);
			if(someArticle!=null){
				if(someArticle.getHitCount()==null){
					someArticle.setHitCount(0L);
				}
				someArticle.setHitCount(someArticle.getHitCount()+1);
				doctorArticleBo.updateArticleInstance(someArticle);
			}
			articleCommentsList=doctorArticleCommentBo.getSomeArticleComments(articleId);
			mes="操作成功";
			return "success";
		}
		return "fail";
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @return the someArticle
	 */
	public DoctorArticle getSomeArticle() {
		return someArticle;
	}

	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	/**
	 * @return the articleCommentsList
	 */
	public List<Object[]> getArticleCommentsList() {
		return articleCommentsList;
	}
}
