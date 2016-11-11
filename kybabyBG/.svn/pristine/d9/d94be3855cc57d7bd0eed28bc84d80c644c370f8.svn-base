package com.kybaby.action;

import com.opensymphony.xwork2.ActionSupport;
import com.kybaby.bo.AdminBo;
import com.kybaby.bo.AssessmentTagBo;
import com.kybaby.bo.BannerBo;
import com.kybaby.bo.BaseBo;
import com.kybaby.bo.CaseClipBo;
import com.kybaby.bo.CouponManageBo;
import com.kybaby.bo.DoctorAddressBo;
import com.kybaby.bo.DoctorArticleBo;
import com.kybaby.bo.DoctorArticleCommentBo;
import com.kybaby.bo.DoctorInfoBo;
import com.kybaby.bo.DoctorProductBo;
import com.kybaby.bo.DoctorProductHistBo;
import com.kybaby.bo.DoctorWithdrawalsBo;
import com.kybaby.bo.EvaluateBo;
import com.kybaby.bo.FunctionBo;
import com.kybaby.bo.GrowthRecordBo;
import com.kybaby.bo.HealthPathBo;
import com.kybaby.bo.HealthPlanBo;
import com.kybaby.bo.ItemResultBo;
import com.kybaby.bo.MajorBo;
import com.kybaby.bo.OrderInfoBo;
import com.kybaby.bo.OrderResultsBo;
import com.kybaby.bo.PositionBo;
import com.kybaby.bo.ProductBo;
import com.kybaby.bo.ProductItemBo;
import com.kybaby.bo.PropertiesBo;
import com.kybaby.bo.RecommendRuleBo;
import com.kybaby.bo.SubsidyBo;
import com.kybaby.bo.SymptomTagBo;
import com.kybaby.bo.TimeInitBo;
import com.kybaby.bo.UserConsultDoctorBo;
import com.kybaby.bo.UserInfoBo;
import com.kybaby.newbussiness.senddoctor.bo.SendDoctorService;

public class BaseAction extends ActionSupport{
	public BaseBo getBaseBo() {
		return baseBo;
	}
	public void setBaseBo(BaseBo baseBo) {
		this.baseBo = baseBo;
	}
	private static final long serialVersionUID = 1222L;
	protected String action;
	protected FunctionBo functionBo;
	protected AdminBo adminBo;
	protected BaseBo baseBo;
	
	protected AssessmentTagBo assessmentTagBo;
	protected BannerBo bannerBo;
	protected CaseClipBo caseClipBo;
	protected CouponManageBo couponManageBo;
	protected DoctorAddressBo doctorAddressBo;
	protected DoctorArticleBo doctorArticleBo;
	protected DoctorArticleCommentBo doctorArticleCommentBo;
	protected DoctorInfoBo doctorInfoBo;
	protected DoctorProductBo doctorProductBo;
	protected DoctorProductHistBo doctorProductHistBo;
	protected DoctorWithdrawalsBo doctorWithdrawalsBo;
	protected EvaluateBo evaluateBo;
	protected GrowthRecordBo growthRecordBo;
	protected HealthPathBo healthPathBo;
	protected HealthPlanBo healthPlanBo;
	protected ItemResultBo itemResultBo;
	protected MajorBo majorBo;
	protected OrderInfoBo orderInfoBo;
	protected OrderResultsBo orderResultsBo;
	protected PositionBo positionBo;
	protected ProductBo productBo;
	protected ProductItemBo productItemBo;
	protected PropertiesBo  propertiesBo;
	protected RecommendRuleBo  recommendRuleBo;
	protected SubsidyBo subsidyBo;
	protected SymptomTagBo symptomTagBo;
	protected TimeInitBo timeInitBo;
	protected UserConsultDoctorBo userConsultDoctorBo;
	protected UserInfoBo userInfoBo;
	/**
	 * 派医生服务类
	 */
	protected SendDoctorService sendDoctorService;

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the functionBo
	 */
	public FunctionBo getFunctionBo() {
		return functionBo;
	}
	/**
	 * @param functionBo the functionBo to set
	 */
	public void setFunctionBo(FunctionBo functionBo) {
		this.functionBo = functionBo;
	}
	public AdminBo getAdminBo() {
		return adminBo;
	}
	public void setAdminBo(AdminBo adminBo) {
		this.adminBo = adminBo;
	}
	public void setAssessmentTagBo(AssessmentTagBo assessmentTagBo) {
		this.assessmentTagBo = assessmentTagBo;
	}
	public void setBannerBo(BannerBo bannerBo) {
		this.bannerBo = bannerBo;
	}
	public void setCaseClipBo(CaseClipBo caseClipBo) {
		this.caseClipBo = caseClipBo;
	}
	public void setCouponManageBo(CouponManageBo couponManageBo) {
		this.couponManageBo = couponManageBo;
	}
	public void setDoctorAddressBo(DoctorAddressBo doctorAddressBo) {
		this.doctorAddressBo = doctorAddressBo;
	}
	public void setDoctorArticleBo(DoctorArticleBo doctorArticleBo) {
		this.doctorArticleBo = doctorArticleBo;
	}
	public void setDoctorArticleCommentBo(
			DoctorArticleCommentBo doctorArticleCommentBo) {
		this.doctorArticleCommentBo = doctorArticleCommentBo;
	}
	public void setDoctorInfoBo(DoctorInfoBo doctorInfoBo) {
		this.doctorInfoBo = doctorInfoBo;
	}
	public void setDoctorProductBo(DoctorProductBo doctorProductBo) {
		this.doctorProductBo = doctorProductBo;
	}
	public void setDoctorProductHistBo(DoctorProductHistBo doctorProductHistBo) {
		this.doctorProductHistBo = doctorProductHistBo;
	}
	public void setDoctorWithdrawalsBo(DoctorWithdrawalsBo doctorWithdrawalsBo) {
		this.doctorWithdrawalsBo = doctorWithdrawalsBo;
	}
	public void setEvaluateBo(EvaluateBo evaluateBo) {
		this.evaluateBo = evaluateBo;
	}
	public void setGrowthRecordBo(GrowthRecordBo growthRecordBo) {
		this.growthRecordBo = growthRecordBo;
	}
	public void setHealthPathBo(HealthPathBo healthPathBo) {
		this.healthPathBo = healthPathBo;
	}
	public void setHealthPlanBo(HealthPlanBo healthPlanBo) {
		this.healthPlanBo = healthPlanBo;
	}
	public void setItemResultBo(ItemResultBo itemResultBo) {
		this.itemResultBo = itemResultBo;
	}
	public void setMajorBo(MajorBo majorBo) {
		this.majorBo = majorBo;
	}
	public void setOrderInfoBo(OrderInfoBo orderInfoBo) {
		this.orderInfoBo = orderInfoBo;
	}
	public void setOrderResultsBo(OrderResultsBo orderResultsBo) {
		this.orderResultsBo = orderResultsBo;
	}
	public void setPositionBo(PositionBo positionBo) {
		this.positionBo = positionBo;
	}
	public void setProductBo(ProductBo productBo) {
		this.productBo = productBo;
	}
	public void setProductItemBo(ProductItemBo productItemBo) {
		this.productItemBo = productItemBo;
	}
	public void setPropertiesBo(PropertiesBo propertiesBo) {
		this.propertiesBo = propertiesBo;
	}
	public void setRecommendRuleBo(RecommendRuleBo recommendRuleBo) {
		this.recommendRuleBo = recommendRuleBo;
	}
	public void setSubsidyBo(SubsidyBo subsidyBo) {
		this.subsidyBo = subsidyBo;
	}
	public void setSymptomTagBo(SymptomTagBo symptomTagBo) {
		this.symptomTagBo = symptomTagBo;
	}
	public void setTimeInitBo(TimeInitBo timeInitBo) {
		this.timeInitBo = timeInitBo;
	}
	public void setUserConsultDoctorBo(UserConsultDoctorBo userConsultDoctorBo) {
		this.userConsultDoctorBo = userConsultDoctorBo;
	}
	public void setUserInfoBo(UserInfoBo userInfoBo) {
		this.userInfoBo = userInfoBo;
	}
	public AssessmentTagBo getAssessmentTagBo() {
		return assessmentTagBo;
	}
	public BannerBo getBannerBo() {
		return bannerBo;
	}
	public CaseClipBo getCaseClipBo() {
		return caseClipBo;
	}
	public CouponManageBo getCouponManageBo() {
		return couponManageBo;
	}
	public DoctorAddressBo getDoctorAddressBo() {
		return doctorAddressBo;
	}
	public DoctorArticleBo getDoctorArticleBo() {
		return doctorArticleBo;
	}
	public DoctorArticleCommentBo getDoctorArticleCommentBo() {
		return doctorArticleCommentBo;
	}
	public DoctorInfoBo getDoctorInfoBo() {
		return doctorInfoBo;
	}
	public DoctorProductBo getDoctorProductBo() {
		return doctorProductBo;
	}
	public DoctorProductHistBo getDoctorProductHistBo() {
		return doctorProductHistBo;
	}
	public DoctorWithdrawalsBo getDoctorWithdrawalsBo() {
		return doctorWithdrawalsBo;
	}
	public EvaluateBo getEvaluateBo() {
		return evaluateBo;
	}
	public GrowthRecordBo getGrowthRecordBo() {
		return growthRecordBo;
	}
	public HealthPathBo getHealthPathBo() {
		return healthPathBo;
	}
	public HealthPlanBo getHealthPlanBo() {
		return healthPlanBo;
	}
	public ItemResultBo getItemResultBo() {
		return itemResultBo;
	}
	public MajorBo getMajorBo() {
		return majorBo;
	}
	public OrderInfoBo getOrderInfoBo() {
		return orderInfoBo;
	}
	public OrderResultsBo getOrderResultsBo() {
		return orderResultsBo;
	}
	public PositionBo getPositionBo() {
		return positionBo;
	}
	public ProductBo getProductBo() {
		return productBo;
	}
	public ProductItemBo getProductItemBo() {
		return productItemBo;
	}
	public PropertiesBo getPropertiesBo() {
		return propertiesBo;
	}
	public RecommendRuleBo getRecommendRuleBo() {
		return recommendRuleBo;
	}
	public SubsidyBo getSubsidyBo() {
		return subsidyBo;
	}
	public SymptomTagBo getSymptomTagBo() {
		return symptomTagBo;
	}
	public TimeInitBo getTimeInitBo() {
		return timeInitBo;
	}
	public UserConsultDoctorBo getUserConsultDoctorBo() {
		return userConsultDoctorBo;
	}
	public UserInfoBo getUserInfoBo() {
		return userInfoBo;
	}
	public SendDoctorService getSendDoctorService() {
		return sendDoctorService;
	}
	public void setSendDoctorService(SendDoctorService sendDoctorService) {
		this.sendDoctorService = sendDoctorService;
	}
}

