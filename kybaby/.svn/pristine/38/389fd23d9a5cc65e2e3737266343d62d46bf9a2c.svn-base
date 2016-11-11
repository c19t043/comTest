package com.kybaby.action.main;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Banner;

/**
 * @ClassName:GetBannerInfo
 * @Description:banner区域图片管理
 * @author Hoolee
 * @date 2015年9月29日上午11:51:57
 */
public class GetBannerInfo extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String mes;//反馈给页面的提示信息
	
	private List<Banner> bannerPictureList=new ArrayList<Banner>();
	
	public String execute(){
		if(action.equals("getBannerPicture")){
			bannerPictureList=bannerBo.getBannerPictureList();
			if(bannerPictureList==null){
				mes="无banner";
				return "fail";
			}
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
	 * @return the bannerPictureList
	 */
	public List<Banner> getBannerPictureList() {
		return bannerPictureList;
	}
}
