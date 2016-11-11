package com.kybaby.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kybaby.bo.AccountBo;
import com.kybaby.bo.AdminBo;
import com.kybaby.bo.BannerBo;
import com.kybaby.bo.CouponBo;
import com.kybaby.bo.DoctorAccountBo;
import com.kybaby.bo.DoctorArticleBo;
import com.kybaby.bo.DoctorArticleCommentBo;
import com.kybaby.bo.DoctorAssessmentTagBo;
import com.kybaby.bo.DoctorInfoBo;
import com.kybaby.bo.DoctorPointsBo;
import com.kybaby.bo.DoctorProductBo;
import com.kybaby.bo.FeedbackBo;
import com.kybaby.bo.GrowthRecordBo;
import com.kybaby.bo.GuidePageBo;
import com.kybaby.bo.HealthPathBo;
import com.kybaby.bo.HealthPlanBo;
import com.kybaby.bo.HealthPlanRemindBo;
import com.kybaby.bo.MajorBo;
import com.kybaby.bo.OrderInfoBo;
import com.kybaby.bo.OrderResultsBo;
import com.kybaby.bo.ProductBo;
import com.kybaby.bo.ProductItemBo;
import com.kybaby.bo.PropertiesBo;
import com.kybaby.bo.RecommendRuleBo;
import com.kybaby.bo.RecommentAwardRecordBo;
import com.kybaby.bo.SubsidyBo;
import com.kybaby.bo.SymptomTagBo;
import com.kybaby.bo.UserAccountBo;
import com.kybaby.bo.UserConsultDoctorBo;
import com.kybaby.bo.UserCouponBo;
import com.kybaby.bo.UserInfoBo;
import com.kybaby.bo.UserPointsBo;
import com.kybaby.bo.VersionManageBo;
import com.kybaby.newbussiness.senddoctor.bo.SendDoctorService;
//import com.kybaby.bo.WxBo;
import com.wx.bo.WxBo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	private static final long serialVersionUID = 1222L;
//	protected HttpServletResponse response ;
	protected Map session ;
	protected Map application;
	protected static WxBo wxBo;
	protected String action;
	protected UserInfoBo userInfoBo;
	protected DoctorInfoBo doctorInfoBo;
	protected RecommendRuleBo recommendRuleBo;
	protected UserPointsBo userPointsBo;
	protected DoctorPointsBo doctorPointsBo;
	protected DoctorAccountBo doctorAccountBo;
	protected UserAccountBo userAccountBo;
	protected UserCouponBo userCouponBo;
	protected RecommentAwardRecordBo recommentAwardRecordBo;
	protected GuidePageBo guidePageBo;
	protected BannerBo bannerBo;
	protected ProductBo productBo;
	protected PropertiesBo propertiesBo;
	protected HealthPlanRemindBo healthPlanRemindBo;
	protected OrderInfoBo orderInfoBo;
	protected HealthPlanBo healthPlanBo;
	protected HealthPathBo healthPathBo;
	protected ProductItemBo productItemBo;
	protected MajorBo majorBo;
	protected DoctorAssessmentTagBo doctorAssessmentTagBo;
	protected DoctorArticleBo doctorArticleBo;
	protected DoctorArticleCommentBo doctorArticleCommentBo;
	protected CouponBo couponBo;
	protected DoctorProductBo doctorProductBo;
	protected SubsidyBo subsidyBo;
	protected UserConsultDoctorBo userConsultDoctorBo;
	protected SymptomTagBo symptomTagBo;
	protected OrderResultsBo orderResultsBo;
	protected GrowthRecordBo growthRecordBo;
	protected FeedbackBo feedbackBo;
	protected AccountBo accountBo;
	protected SendDoctorService sendDoctorService;
	protected AdminBo adminBo;
	protected VersionManageBo versionManageBo;
	public AdminBo getAdminBo() {
		return adminBo;
	}
	public void setAdminBo(AdminBo adminBo) {
		this.adminBo = adminBo;
	}
	public SendDoctorService getSendDoctorService() {
		return sendDoctorService;
	}
	public void setSendDoctorService(SendDoctorService sendDoctorService) {
		this.sendDoctorService = sendDoctorService;
	}
	public Map getSession() {
        return ActionContext.getContext().getSession();
    }
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;
	}
	public WxBo getWxBo() {
		return wxBo;
	}
	public void setWxBo(WxBo wxBo) {
		this.wxBo = wxBo;
	}
	public String getAction() {
		return action;
	}
	public UserInfoBo getUserInfoBo() {
		return userInfoBo;
	}
	public void setUserInfoBo(UserInfoBo userInfoBo) {
		this.userInfoBo = userInfoBo;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the doctorInfoBo
	 */
	public DoctorInfoBo getDoctorInfoBo() {
		return doctorInfoBo;
	}
	/**
	 * @param doctorInfoBo the doctorInfoBo to set
	 */
	public void setDoctorInfoBo(DoctorInfoBo doctorInfoBo) {
		this.doctorInfoBo = doctorInfoBo;
	}
	/**
	 * @return the recommendRuleBo
	 */
	public RecommendRuleBo getRecommendRuleBo() {
		return recommendRuleBo;
	}
	/**
	 * @param recommendRuleBo the recommendRuleBo to set
	 */
	public void setRecommendRuleBo(RecommendRuleBo recommendRuleBo) {
		this.recommendRuleBo = recommendRuleBo;
	}
	/**
	 * @return the userPointsBo
	 */
	public UserPointsBo getUserPointsBo() {
		return userPointsBo;
	}
	/**
	 * @param userPointsBo the userPointsBo to set
	 */
	public void setUserPointsBo(UserPointsBo userPointsBo) {
		this.userPointsBo = userPointsBo;
	}
	/**
	 * @return the doctorPointsBo
	 */
	public DoctorPointsBo getDoctorPointsBo() {
		return doctorPointsBo;
	}
	/**
	 * @param doctorPointsBo the doctorPointsBo to set
	 */
	public void setDoctorPointsBo(DoctorPointsBo doctorPointsBo) {
		this.doctorPointsBo = doctorPointsBo;
	}
	/**
	 * @return the doctorAccountBo
	 */
	public DoctorAccountBo getDoctorAccountBo() {
		return doctorAccountBo;
	}
	/**
	 * @param doctorAccountBo the doctorAccountBo to set
	 */
	public void setDoctorAccountBo(DoctorAccountBo doctorAccountBo) {
		this.doctorAccountBo = doctorAccountBo;
	}
	/**
	 * @return the userAccountBo
	 */
	public UserAccountBo getUserAccountBo() {
		return userAccountBo;
	}
	/**
	 * @param userAccountBo the userAccountBo to set
	 */
	public void setUserAccountBo(UserAccountBo userAccountBo) {
		this.userAccountBo = userAccountBo;
	}
	/**
	 * @return the userCouponBo
	 */
	public UserCouponBo getUserCouponBo() {
		return userCouponBo;
	}
	/**
	 * @param userCouponBo the userCouponBo to set
	 */
	public void setUserCouponBo(UserCouponBo userCouponBo) {
		this.userCouponBo = userCouponBo;
	}
	/**
	 * @return the recommentAwardRecordBo
	 */
	public RecommentAwardRecordBo getRecommentAwardRecordBo() {
		return recommentAwardRecordBo;
	}
	/**
	 * @param recommentAwardRecordBo the recommentAwardRecordBo to set
	 */
	public void setRecommentAwardRecordBo(
			RecommentAwardRecordBo recommentAwardRecordBo) {
		this.recommentAwardRecordBo = recommentAwardRecordBo;
	}
	/**
	 * @return the guidePageBo
	 */
	public GuidePageBo getGuidePageBo() {
		return guidePageBo;
	}
	/**
	 * @param guidePageBo the guidePageBo to set
	 */
	public void setGuidePageBo(GuidePageBo guidePageBo) {
		this.guidePageBo = guidePageBo;
	}
	/**
	 * @return the bannerBo
	 */
	public BannerBo getBannerBo() {
		return bannerBo;
	}
	/**
	 * @param bannerBo the bannerBo to set
	 */
	public void setBannerBo(BannerBo bannerBo) {
		this.bannerBo = bannerBo;
	}
	/**
	 * @return the propertiesBo
	 */
	public PropertiesBo getPropertiesBo() {
		return propertiesBo;
	}
	/**
	 * @param propertiesBo the propertiesBo to set
	 */
	public void setPropertiesBo(PropertiesBo propertiesBo) {
		this.propertiesBo = propertiesBo;
	}
	/**
	 * @return the healthPlanRemindBo
	 */
	public HealthPlanRemindBo getHealthPlanRemindBo() {
		return healthPlanRemindBo;
	}
	/**
	 * @param healthPlanRemindBo the healthPlanRemindBo to set
	 */
	public void setHealthPlanRemindBo(HealthPlanRemindBo healthPlanRemindBo) {
		this.healthPlanRemindBo = healthPlanRemindBo;
	}
	/**
	 * @return the orderInfoBo
	 */
	public OrderInfoBo getOrderInfoBo() {
		return orderInfoBo;
	}
	/**
	 * @param orderInfoBo the orderInfoBo to set
	 */
	public void setOrderInfoBo(OrderInfoBo orderInfoBo) {
		this.orderInfoBo = orderInfoBo;
	}
	/**
	 * @return the healthPlanBo
	 */
	public HealthPlanBo getHealthPlanBo() {
		return healthPlanBo;
	}
	/**
	 * @param healthPlanBo the healthPlanBo to set
	 */
	public void setHealthPlanBo(HealthPlanBo healthPlanBo) {
		this.healthPlanBo = healthPlanBo;
	}
	/**
	 * @return the productBo
	 */
	public ProductBo getProductBo() {
		return productBo;
	}
	/**
	 * @param productBo the productBo to set
	 */
	public void setProductBo(ProductBo productBo) {
		this.productBo = productBo;
	}
	/**
	 * @return the healthPathBo
	 */
	public HealthPathBo getHealthPathBo() {
		return healthPathBo;
	}
	/**
	 * @param healthPathBo the healthPathBo to set
	 */
	public void setHealthPathBo(HealthPathBo healthPathBo) {
		this.healthPathBo = healthPathBo;
	}
	/**
	 * @return the productItemBo
	 */
	public ProductItemBo getProductItemBo() {
		return productItemBo;
	}
	/**
	 * @param productItemBo the productItemBo to set
	 */
	public void setProductItemBo(ProductItemBo productItemBo) {
		this.productItemBo = productItemBo;
	}
	/**
	 * @return the majorBo
	 */
	public MajorBo getMajorBo() {
		return majorBo;
	}
	/**
	 * @param majorBo the majorBo to set
	 */
	public void setMajorBo(MajorBo majorBo) {
		this.majorBo = majorBo;
	}
	/**
	 * @return the doctorAssessmentTagBo
	 */
	public DoctorAssessmentTagBo getDoctorAssessmentTagBo() {
		return doctorAssessmentTagBo;
	}
	/**
	 * @param doctorAssessmentTagBo the doctorAssessmentTagBo to set
	 */
	public void setDoctorAssessmentTagBo(DoctorAssessmentTagBo doctorAssessmentTagBo) {
		this.doctorAssessmentTagBo = doctorAssessmentTagBo;
	}
	/**
	 * @return the doctorArticleBo
	 */
	public DoctorArticleBo getDoctorArticleBo() {
		return doctorArticleBo;
	}
	/**
	 * @param doctorArticleBo the doctorArticleBo to set
	 */
	public void setDoctorArticleBo(DoctorArticleBo doctorArticleBo) {
		this.doctorArticleBo = doctorArticleBo;
	}
	/**
	 * @return the doctorArticleCommentBo
	 */
	public DoctorArticleCommentBo getDoctorArticleCommentBo() {
		return doctorArticleCommentBo;
	}
	/**
	 * @param doctorArticleCommentBo the doctorArticleCommentBo to set
	 */
	public void setDoctorArticleCommentBo(
			DoctorArticleCommentBo doctorArticleCommentBo) {
		this.doctorArticleCommentBo = doctorArticleCommentBo;
	}
	/**
	 * @return the couponBo
	 */
	public CouponBo getCouponBo() {
		return couponBo;
	}
	/**
	 * @param couponBo the couponBo to set
	 */
	public void setCouponBo(CouponBo couponBo) {
		this.couponBo = couponBo;
	}
	/**
	 * @return the doctorProductBo
	 */
	public DoctorProductBo getDoctorProductBo() {
		return doctorProductBo;
	}
	/**
	 * @param doctorProductBo the doctorProductBo to set
	 */
	public void setDoctorProductBo(DoctorProductBo doctorProductBo) {
		this.doctorProductBo = doctorProductBo;
	}
	/**
	 * @return the subsidyBo
	 */
	public SubsidyBo getSubsidyBo() {
		return subsidyBo;
	}
	/**
	 * @param subsidyBo the subsidyBo to set
	 */
	public void setSubsidyBo(SubsidyBo subsidyBo) {
		this.subsidyBo = subsidyBo;
	}
	/**
	 * @return the userConsultDoctorBo
	 */
	public UserConsultDoctorBo getUserConsultDoctorBo() {
		return userConsultDoctorBo;
	}
	/**
	 * @param userConsultDoctorBo the userConsultDoctorBo to set
	 */
	public void setUserConsultDoctorBo(UserConsultDoctorBo userConsultDoctorBo) {
		this.userConsultDoctorBo = userConsultDoctorBo;
	}
	/**
	 * @return the symptomTagBo
	 */
	public SymptomTagBo getSymptomTagBo() {
		return symptomTagBo;
	}
	/**
	 * @param symptomTagBo the symptomTagBo to set
	 */
	public void setSymptomTagBo(SymptomTagBo symptomTagBo) {
		this.symptomTagBo = symptomTagBo;
	}
	/**
	 * @return the orderResultsBo
	 */
	public OrderResultsBo getOrderResultsBo() {
		return orderResultsBo;
	}
	/**
	 * @param orderResultsBo the orderResultsBo to set
	 */
	public void setOrderResultsBo(OrderResultsBo orderResultsBo) {
		this.orderResultsBo = orderResultsBo;
	}
	/**
	 * @return the growthRecordBo
	 */
	public GrowthRecordBo getGrowthRecordBo() {
		return growthRecordBo;
	}
	/**
	 * @param growthRecordBo the growthRecordBo to set
	 */
	public void setGrowthRecordBo(GrowthRecordBo growthRecordBo) {
		this.growthRecordBo = growthRecordBo;
	}
	/**
	 * @return the feedbackBo
	 */
	public FeedbackBo getFeedbackBo() {
		return feedbackBo;
	}
	/**
	 * @param feedbackBo the feedbackBo to set
	 */
	public void setFeedbackBo(FeedbackBo feedbackBo) {
		this.feedbackBo = feedbackBo;
	}
	public AccountBo getAccountBo() {
		return accountBo;
	}
	public void setAccountBo(AccountBo accountBo) {
		this.accountBo = accountBo;
	}
	public VersionManageBo getVersionManageBo() {
		return versionManageBo;
	}
	public void setVersionManageBo(VersionManageBo versionManageBo) {
		this.versionManageBo = versionManageBo;
	}
	public BaseAction() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setHeader("Access-Control-Allow-Origin", "*");
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		
	}
	
}

