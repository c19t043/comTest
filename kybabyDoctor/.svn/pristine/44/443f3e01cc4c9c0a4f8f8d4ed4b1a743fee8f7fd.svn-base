package com.kybaby.action.main;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorArticle;
import com.kybaby.domain.DoctorArticleComment;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class ArticleManage extends BaseAction{

	private DoctorArticle doctorArticle;
	private List<DoctorArticleComment> doctorArticleCommentList;
	private String title;
	private String text;
	private String mes;
	private Long articleId;
	private String detailList;
	
	@Override
	public String execute(){
		if(action.equals("submite")){
			if(text==""||text==null){
				mes="请完善内容";
				return "fail";
			}
			if(title==""||title==null){
				mes="请完善标题";
				return"fail";
			}
			DoctorInfo doctorInfo = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "fail";
			}
			Date date = new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			doctorArticle = new DoctorArticle();
			doctorArticle.setDoctorId(doctorInfo.getId());
			doctorArticle.setArticleTitle(title);
			doctorArticle.setArticleContent(text);
			doctorArticle.setArticleStatus("待审核");
			doctorArticle.setCommentCount(0L);
			doctorArticle.setComments("");
			doctorArticle.setHitCount(0L);
			doctorArticle.setPublishTime(time);
			articleBo.save(doctorArticle);
			mes="成功";
			return "success";
		}
		if(action.equals("get")){
			doctorArticle = articleBo.getArticleByArticleId(articleId);
			if(doctorArticle!=null){
				detailList = doctorArticle.getArticleTitle();
				detailList += "!:"+doctorArticle.getArticleContent()+"::";
				List<DoctorArticleComment> doctorArticleComment = new ArrayList<DoctorArticleComment>();
				doctorArticleComment = articleBo.getDoctorArticleCommentByArticleId(articleId);
				if(doctorArticleComment!=null){
					for(int i=0;i<doctorArticleComment.size();i++){
						detailList += doctorArticleComment.get(i).getUserComments();
						detailList += ":!:"+doctorArticleComment.get(i).getSubmitTime();
						UserInfo userInfo = articleBo.getSomeUserMesByUserId(doctorArticleComment.get(i).getUserId());
						if(userInfo!=null){
							detailList += ":!:"+userInfo.getParentName();
							detailList += ":!:"+userInfo.getUserImage()+":!!:";
						}
					}
					mes="成功";
					return "success";
				}
				mes="无评论";
				return "fail";
			}
			mes="无文章";
			return "fail";
			
		}
		return "success";
		
	}

//	public DoctorArticle getDoctorArticle() {
//		return doctorArticle;
//	}

	public List<DoctorArticleComment> getDoctorArticleCommentList() {
		return doctorArticleCommentList;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String getMes() {
		return mes;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getDetailList() {
		return detailList;
	}
}
