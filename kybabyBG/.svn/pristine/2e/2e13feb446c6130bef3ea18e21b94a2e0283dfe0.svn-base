package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorArticle;
import com.kybaby.domain.DoctorArticleComment;

public class DoctorArticleCommentHandle extends BaseAction {

	private String mes="";
	private String action="";
	
	private long articleCommetId; //医生文章评论Id
	private String commentsStatus;//评论审核状态
	
	
	private List allArticleComment=new ArrayList();//所有的医生文章评论
	private List oneArticleComment=new ArrayList();//一条评论
	
	
	public String execute()
	{
		if(action.equals("getAllComment"))
		{
			System.out.println("doctorArticleCommentHandle.action?action=getAllComment......");
			allArticleComment=doctorArticleCommentBo.getAllArticleCommet();
			mes="成功";
			return "success";
		}
		
		if(action.equals("update"))
		{
			System.out.println("doctorArticleCommentHandle.action?action=update......");
			DoctorArticleComment updateDAC=doctorArticleCommentBo.getDoctorArticleCommentById(articleCommetId);
			if(updateDAC!=null)
			{
				updateDAC.setCommentsStatus(commentsStatus);
				Long articleId=updateDAC.getArticleId();
				baseBo.updateDoctorArticleComment(updateDAC);
				if(null!=articleId)
				{
					DoctorArticle DA=doctorArticleBo.getDoctorArticleById(articleId);
					if(null!=DA)
					{
					Long times=doctorArticleBo.getArticleCommentsHitByStatus(articleId,"通过");
					DA.setCommentCount(times);
					baseBo.updateDoctorArticle(DA);
					mes="成功";
					return "success";
					}
					else
					{
						mes="没有找到此文章";
						return "fail";
					}
					
					
					
				}
				else
				{
					mes="没有找到此评论";
					return "fail";
				}
			
			}
			else
			{
				mes="无此文章评论";
				return "fail";
			}
		}
		return "success";
		
	}


	public String getMes() {
		return mes;
	}


	public List getAllArticleComment() {
		return allArticleComment;
	}


	public List getOneArticleComment() {
		return oneArticleComment;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public void setArticleCommetId(long articleCommetId) {
		this.articleCommetId = articleCommetId;
	}


	public void setCommentsStatus(String commentsStatus) {
		this.commentsStatus = commentsStatus;
	}
}
