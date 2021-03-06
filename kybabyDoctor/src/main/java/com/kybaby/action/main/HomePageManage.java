package com.kybaby.action.main;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorArticle;
import com.kybaby.domain.DoctorAssessmentTag;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Product;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class HomePageManage extends BaseAction{
	
	private DoctorInfo doctorInfo;
	private String backMes="";
	private List<Product> productList;
	private List<DoctorAssessmentTag> doctorAssessmentTagList;
	private List<DoctorArticle> doctorArticle;
	private List<String> majorNameList;
	private List<String> someProductList;
	private List<DoctorArticle> doctorArticleList;
	private List<List> tag;
	
	@Override
	public String execute(){
		if(action.equals("homePage")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			doctorInfo = this.doctorInfoBo.getDoctorInfoBoById(doctorInfo.getId());
			if(doctorInfo!=null){
				backMes = doctorInfo.getDoctorName();
				backMes += "::"+doctorInfo.getDoctorImage();
				backMes += "::"+doctorInfo.getDoctorTitle();
				backMes += "::"+doctorInfo.getDoctorEmployer();
				backMes += "::"+doctorInfo.getSeiviceStarLevel();
				backMes += "::"+doctorInfo.getDutyStarLevel();
				backMes += "::"+doctorInfo.getOnTimeStarLevel();
				backMes += "::"+doctorInfo.getVisitedTimes();
				doctorArticleList = new ArrayList<DoctorArticle>();
				tag = new ArrayList<List>();
				doctorArticleList = homePageManageBo.getDoctorArticleByDoctorId(doctorInfo.getId());
				tag = homePageManageBo.getDoctorAssessmentTagByDoctorId(doctorInfo.getId());
				if(!doctorInfo.getMajorId().equals(null)&&!doctorInfo.getMajorId().equals("")){
					String[] majorIds = (doctorInfo.getMajorId()).split("::");
					majorNameList = new ArrayList<String>();
					for(int i=0;i<majorIds.length;i++){
						String majorName = homePageManageBo.getMajorNameById(Long.valueOf(majorIds[i]));
						if(!majorName.equals("")&&!majorName.equals(null)){
							majorNameList.add(majorName);
						}
					}
					System.out.println(majorNameList);
				}
				if(!doctorInfo.getProductIds().equals(null)&&!doctorInfo.getProductIds().equals("")){
					String[] productIds = doctorInfo.getProductIds().split("::");
					someProductList = new ArrayList<String>();
					for(int j=0;j<productIds.length;j++){
						Product someProduct = homePageManageBo.getProductById(Long.valueOf(productIds[j]));
						if(someProduct!=null){
							String someProd = "";
							if(!someProduct.getName().equals(null)){
								someProd += someProduct.getName()+"::";
							}
							if(!someProduct.getSmallPicture().equals(null)&&!someProduct.getSmallPicture().equals("")){
								someProd += someProduct.getSmallPicture()+"::";
							}
							if(someProduct.getProductUseTime()!=null){
								someProd += String.valueOf(someProduct.getProductUseTime())+"::";
							}
							if(someProduct.getTotalPrice()!=null){
								someProd += String.valueOf(someProduct.getTotalPrice());
							}
							someProductList.add(someProd);
							System.out.println(someProductList);
						}
						
					}
					mes="成功";
					return "success";
				}
				mes="无数据";
				return "fail";
			}
			mes="请登录";
			return "fail";
		}
		return "success";
		
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

//	public List<Product> getProductList() {
//		return productList;
//	}

	public List<DoctorAssessmentTag> getDoctorAssessmentTagList() {
		return doctorAssessmentTagList;
	}

	public List<DoctorArticle> getDoctorArticle() {
		return doctorArticle;
	}

	public String getBackMes() {
		return backMes;
	}

	public List<String> getMajorNameList() {
		return majorNameList;
	}

	public List<String> getSomeProductList() {
		return someProductList;
	}

	public List<DoctorArticle> getDoctorArticleList() {
		return doctorArticleList;
	}

	public List<List> getTag() {
		return tag;
	}

}
