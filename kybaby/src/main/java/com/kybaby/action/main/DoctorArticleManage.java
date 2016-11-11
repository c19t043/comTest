package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:DoctorArticleManage
 * @Description:医生个人专栏评论相关的
 * @author Hoolee
 * @date 2015年10月6日下午11:15:50
 */
public class DoctorArticleManage extends BaseAction {

	private String mes;//反馈给前端的提示信息
	private long articleId;//被评论个人专栏的ID
	private String userComments;//评论的内容
	
	public String execute(){
		if(action.equals("addComment")){
			System.out.println("AddComment is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				doctorArticleCommentBo.addNewArticleComments(userId, articleId, userComments);
				mes="操作成功";
				return "success";
			}
			mes="请登录";
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
	 * @param articleId the articleId to set
	 */
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	/**
	 * @param userComments the userComments to set
	 */
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	
}
