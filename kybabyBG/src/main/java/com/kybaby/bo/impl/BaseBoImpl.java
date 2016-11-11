package com.kybaby.bo.impl;

import com.kybaby.bo.BaseBo;
import com.kybaby.dao.BaseDao;
import com.kybaby.domain.Activity;
import com.kybaby.domain.Admin;
import com.kybaby.domain.AssessmentTag;
import com.kybaby.domain.Balance;
import com.kybaby.domain.Banner;
import com.kybaby.domain.CaseClip;
import com.kybaby.domain.Coupon;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorAddress;
import com.kybaby.domain.DoctorArticle;
import com.kybaby.domain.DoctorArticleComment;
import com.kybaby.domain.DoctorAssessmentTag;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorPoints;
import com.kybaby.domain.DoctorProduct;
import com.kybaby.domain.DoctorProductHist;
import com.kybaby.domain.DoctorWithdrawals;
import com.kybaby.domain.Evaluate;
import com.kybaby.domain.FunctionList;
import com.kybaby.domain.FunctionParent;
import com.kybaby.domain.GrowthRecord;
import com.kybaby.domain.GuidePage;
import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlan;
import com.kybaby.domain.HealthPlanRemind;
import com.kybaby.domain.HealthRecord;
import com.kybaby.domain.ItemResult;
import com.kybaby.domain.Major;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.OrderResults;
import com.kybaby.domain.Position;
import com.kybaby.domain.Product;
import com.kybaby.domain.ProductItem;
import com.kybaby.domain.Properties;
import com.kybaby.domain.RecommendRule;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.domain.RefereeUser;
import com.kybaby.domain.RoleList;
import com.kybaby.domain.Subsidy;
import com.kybaby.domain.SymptomTag;
import com.kybaby.domain.TimeInit;
import com.kybaby.domain.UserAccount;
import com.kybaby.domain.UserConsultDoctor;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;
import com.kybaby.domain.UserPoints;

public class BaseBoImpl implements BaseBo {


	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	BaseDao baseDao;
	
	
	
	///////////保存
	
	@Override
	public void saveAdmin(Admin admin) {
		baseDao.saveAdmin(admin);
	}

	@Override
	public void saveAssessmentTag(AssessmentTag assessmentTag) {
		baseDao.saveAssessmentTag(assessmentTag);
	}

	@Override
	public void saveBanner(Banner banner) {
		baseDao.saveBanner(banner);
	}

	@Override
	public void saveCaseClip(CaseClip caseClip) {
		baseDao.saveCaseClip(caseClip);
	}


	@Override
	public void saveCoupon(Coupon coupon) {
		baseDao.saveCoupon(coupon);
	}

	@Override
	public void saveDoctorAccount(DoctorAccount doctorAccount) {
		baseDao.saveDoctorAccount(doctorAccount);
	}

	@Override
	public void saveDoctorAddress(DoctorAddress doctorAddress) {
		baseDao.saveDoctorAddress(doctorAddress);
	}

	@Override
	public void saveDoctorArticle(DoctorArticle doctorArticle) {
		baseDao.saveDoctorArticle(doctorArticle);
	}

	@Override
	public void saveDoctorArticleComment(
			DoctorArticleComment doctorArticleComment) {
		baseDao.saveDoctorArticleComment(doctorArticleComment);
	}

	@Override
	public void saveDoctorAssessmentTag(DoctorAssessmentTag doctorAssessmentTag) {
		baseDao.saveDoctorAssessmentTag(doctorAssessmentTag);
	}

	@Override
	public void saveDoctorInfo(DoctorInfo doctorInfo) {
		baseDao.saveDoctorInfo(doctorInfo);
	}

	@Override
	public void saveDoctorPoints(DoctorPoints doctorPoints) {
		baseDao.saveDoctorPoints(doctorPoints);
	}

	@Override
	public void saveDoctorProduct(DoctorProduct doctorProduct) {
		baseDao.saveDoctorProduct(doctorProduct);
	}

	@Override
	public void saveDoctorProductHist(DoctorProductHist doctorProductHist) {
		baseDao.saveDoctorProductHist(doctorProductHist);
	}

	@Override
	public void saveDoctorWithdrawals(DoctorWithdrawals doctorWithdrawals) {
		baseDao.saveDoctorWithdrawals(doctorWithdrawals);
	}

	@Override
	public void saveEvaluate(Evaluate evaluate) {
		baseDao.saveEvaluate(evaluate);
	}

	@Override
	public void saveFunctionList(FunctionList functionList) {
		baseDao.saveFunctionList(functionList);
	}

	@Override
	public void saveFunctionParent(FunctionParent functionParent) {
		baseDao.saveFunctionParent(functionParent);
	}

	@Override
	public void saveGrowthRecord(GrowthRecord growthRecord) {
		baseDao.saveGrowthRecord(growthRecord);
	}

	@Override
	public void saveHealthPath(HealthPath healthPath) {
		baseDao.saveHealthPath(healthPath);
	}

	@Override
	public void saveHealthPlan(HealthPlan healthPlan) {
		baseDao.saveHealthPlan(healthPlan);
	}

	@Override
	public void saveHealthPlanRemind(HealthPlanRemind healthPlanRemind) {
		baseDao.saveHealthPlanRemind(healthPlanRemind);
	}

	@Override
	public void saveHealthRecord(HealthRecord healthRecord) {
		baseDao.saveHealthRecord(healthRecord);
	}

	@Override
	public void saveItemResult(ItemResult itemResult) {
		baseDao.saveItemResult(itemResult);
	}
	
	@Override
	public void saveMajor(Major major) {
		baseDao.saveMajor(major);
		
	}

	@Override
	public void saveOrderInfo(OrderInfo orderInfo) {
		baseDao.saveOrderInfo(orderInfo);
	}

	@Override
	public void saveOrderResults(OrderResults orderResults) {
		baseDao.saveOrderResults(orderResults);
	}

	@Override
	public void savePosition(Position position) {
		baseDao.savePosition(position);
	}

	@Override
	public void saveProduct(Product product) {
		baseDao.saveProduct(product);
	}

	@Override
	public void saveProductItem(ProductItem productItem) {
		baseDao.saveProductItem(productItem);
	}

	@Override
	public void saveProperties(Properties properties) {
		baseDao.saveProperties(properties);
	}

	@Override
	public void saveRecommentAwardRecord(
			RecommentAwardRecord recommentAwardRecord) {
		baseDao.saveRecommentAwardRecord(recommentAwardRecord);
	}

	@Override
	public void saveRefereeUser(RefereeUser refereeUser) {
		baseDao.saveRefereeUser(refereeUser);
	}

	@Override
	public void saveRoleList(RoleList roleList) {
		baseDao.saveRoleList(roleList);
	}

	@Override
	public void saveSubsidy(Subsidy subsidy) {
		baseDao.saveSubsidy(subsidy);
	}

	@Override
	public void saveSymptomTag(SymptomTag symptomTag) {
		baseDao.saveSymptomTag(symptomTag);
	}

	@Override
	public void saveUserAccount(UserAccount userAccount) {
		baseDao.saveUserAccount(userAccount);
	}

	@Override
	public void saveUserConsultDoctor(UserConsultDoctor userConsultDoctor) {
		baseDao.saveUserConsultDoctor(userConsultDoctor);
	}

	@Override
	public void saveUserCoupon(UserCoupon userCoupon) {
		baseDao.saveUserCoupon(userCoupon);
	}

	@Override
	public void saveUserInfo(UserInfo userInfo) {
		baseDao.saveUserInfo(userInfo);
	}

	@Override
	public void saveUserPoints(UserPoints userPoints) {
		baseDao.saveUserPoints(userPoints);
	}
	
	@Override
	public void saveGuidePage(GuidePage guidePage) {
		baseDao.saveGuidePage(guidePage);
	}

	///////////////////更新
	
	@Override
	public void updateAdmin(Admin admin) {
		baseDao.updateAdmin(admin);
	}

	@Override
	public void updateAssessmentTag(AssessmentTag assessmentTag) {
		baseDao.updateAssessmentTag(assessmentTag);
	}

	@Override
	public void updateBanner(Banner banner) {
		baseDao.updateBanner(banner);
	}

	@Override
	public void updateCaseClip(CaseClip caseClip) {
		baseDao.updateCaseClip(caseClip);
	}



	@Override
	public void updateCoupon(Coupon coupon) {
		baseDao.updateCoupon(coupon);
	}

	@Override
	public void updateDoctorAccount(DoctorAccount doctorAccount) {
		baseDao.updateDoctorAccount(doctorAccount);
	}

	@Override
	public void updateDoctorAddress(DoctorAddress doctorAddress) {
		baseDao.updateDoctorAddress(doctorAddress);
	}

	@Override
	public void updateDoctorArticle(DoctorArticle doctorArticle) {
		baseDao.updateDoctorArticle(doctorArticle);
	}

	@Override
	public void updateDoctorArticleComment(
			DoctorArticleComment doctorArticleComment) {
		baseDao.updateDoctorArticleComment(doctorArticleComment);
	}

	@Override
	public void updateDoctorAssessmentTag(DoctorAssessmentTag doctorAssessmentTag) {
		baseDao.updateDoctorAssessmentTag(doctorAssessmentTag);
	}

	@Override
	public void updateDoctorInfo(DoctorInfo doctorInfo) {
		baseDao.updateDoctorInfo(doctorInfo);
	}

	@Override
	public void updateDoctorPoints(DoctorPoints doctorPoints) {
		baseDao.updateDoctorPoints(doctorPoints);
	}

	@Override
	public void updateDoctorProduct(DoctorProduct doctorProduct) {
		baseDao.updateDoctorProduct(doctorProduct);
	}

	@Override
	public void updateDoctorProductHist(DoctorProductHist doctorProductHist) {
		baseDao.updateDoctorProductHist(doctorProductHist);
	}

	@Override
	public void updateDoctorWithdrawals(DoctorWithdrawals doctorWithdrawals) {
		baseDao.updateDoctorWithdrawals(doctorWithdrawals);
	}

	@Override
	public void updateEvaluate(Evaluate evaluate) {
		baseDao.updateEvaluate(evaluate);
	}

	@Override
	public void updateFunctionList(FunctionList functionList) {
		baseDao.updateFunctionList(functionList);
	}

	@Override
	public void updateFunctionParent(FunctionParent functionParent) {
		baseDao.updateFunctionParent(functionParent);
	}

	@Override
	public void updateGrowthRecord(GrowthRecord growthRecord) {
		baseDao.updateGrowthRecord(growthRecord);
	}

	@Override
	public void updateHealthPath(HealthPath healthPath) {
		baseDao.updateHealthPath(healthPath);
	}

	@Override
	public void updateHealthPlan(HealthPlan healthPlan) {
		baseDao.updateHealthPlan(healthPlan);
	}

	@Override
	public void updateHealthPlanRemind(HealthPlanRemind healthPlanRemind) {
		baseDao.updateHealthPlanRemind(healthPlanRemind);
	}

	@Override
	public void updateHealthRecord(HealthRecord healthRecord) {
		baseDao.updateHealthRecord(healthRecord);
	}

	@Override
	public void updateItemResult(ItemResult itemResult) {
		baseDao.updateItemResult(itemResult);
	}

	@Override
	public void updateOrderInfo(OrderInfo orderInfo) {
		baseDao.updateOrderInfo(orderInfo);
	}

	@Override
	public void updateOrderResults(OrderResults orderResults) {
		baseDao.updateOrderResults(orderResults);
	}

	@Override
	public void updatePosition(Position position) {
		baseDao.updatePosition(position);
	}

	@Override
	public void updateProduct(Product product) {
		baseDao.updateProduct(product);
	}

	@Override
	public void updateProductItem(ProductItem productItem) {
		baseDao.updateProductItem(productItem);
	}

	@Override
	public void updateProperties(Properties properties) {
		baseDao.updateProperties(properties);
	}

	@Override
	public void updateRecommentAwardRecord(
			RecommentAwardRecord recommentAwardRecord) {
		baseDao.updateRecommentAwardRecord(recommentAwardRecord);
	}

	@Override
	public void updateRefereeUser(RefereeUser refereeUser) {
		baseDao.updateRefereeUser(refereeUser);
	}

	@Override
	public void updateRoleList(RoleList roleList) {
		baseDao.updateRoleList(roleList);
	}

	@Override
	public void updateSubsidy(Subsidy subsidy) {
		baseDao.updateSubsidy(subsidy);
	}

	@Override
	public void updateSymptomTag(SymptomTag symptomTag) {
		baseDao.updateSymptomTag(symptomTag);
	}

	@Override
	public void updateUserAccount(UserAccount userAccount) {
		baseDao.updateUserAccount(userAccount);
	}

	@Override
	public void updateUserConsultDoctor(UserConsultDoctor userConsultDoctor) {
		baseDao.updateUserConsultDoctor(userConsultDoctor);
	}

	@Override
	public void updateUserCoupon(UserCoupon userCoupon) {
		baseDao.updateUserCoupon(userCoupon);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		baseDao.updateUserInfo(userInfo);
	}

	@Override
	public void updateUserPoints(UserPoints userPoints) {
		baseDao.updateUserPoints(userPoints);
	}
	
	@Override
	public void updateGuidePage(GuidePage guidePage) {
		baseDao.updateGuidePage(guidePage);
		
	}

	
	////////////删除

	@Override
	public void deleteAdmin(Admin admin) {
		baseDao.deleteAdmin(admin);
	}

	@Override
	public void deleteAssessmentTag(AssessmentTag assessmentTag) {
		baseDao.deleteAssessmentTag(assessmentTag);
	}

	@Override
	public void deleteBanner(Banner banner) {
		baseDao.deleteBanner(banner);
	}

	@Override
	public void deleteCaseClip(CaseClip caseClip) {
		baseDao.deleteCaseClip(caseClip);
	}


	@Override
	public void deleteCoupon(Coupon coupon) {
		baseDao.deleteCoupon(coupon);
	}

	@Override
	public void deleteDoctorAccount(DoctorAccount doctorAccount) {
		baseDao.deleteDoctorAccount(doctorAccount);
	}

	@Override
	public void deleteDoctorAddress(DoctorAddress doctorAddress) {
		baseDao.deleteDoctorAddress(doctorAddress);
	}

	@Override
	public void deleteDoctorArticle(DoctorArticle doctorArticle) {
		baseDao.deleteDoctorArticle(doctorArticle);
	}

	@Override
	public void deleteDoctorArticleComment(
			DoctorArticleComment doctorArticleComment) {
		baseDao.deleteDoctorArticleComment(doctorArticleComment);
	}

	@Override
	public void deleteDoctorAssessmentTag(DoctorAssessmentTag doctorAssessmentTag) {
		baseDao.deleteDoctorAssessmentTag(doctorAssessmentTag);
	}

	@Override
	public void deleteDoctorInfo(DoctorInfo doctorInfo) {
		baseDao.deleteDoctorInfo(doctorInfo);
	}

	@Override
	public void deleteDoctorPoints(DoctorPoints doctorPoints) {
		baseDao.deleteDoctorPoints(doctorPoints);
	}

	@Override
	public void deleteDoctorProduct(DoctorProduct doctorProduct) {
		baseDao.deleteDoctorProduct(doctorProduct);
	}

	@Override
	public void deleteDoctorProductHist(DoctorProductHist doctorProductHist) {
		baseDao.deleteDoctorProductHist(doctorProductHist);
	}

	@Override
	public void deleteDoctorWithdrawals(DoctorWithdrawals doctorWithdrawals) {
		baseDao.deleteDoctorWithdrawals(doctorWithdrawals);
	}

	@Override
	public void deleteEvaluate(Evaluate evaluate) {
		baseDao.deleteEvaluate(evaluate);
	}

	@Override
	public void deleteFunctionList(FunctionList functionList) {
		baseDao.deleteFunctionList(functionList);
	}

	@Override
	public void deleteFunctionParent(FunctionParent functionParent) {
		baseDao.deleteFunctionParent(functionParent);
	}

	@Override
	public void deleteGrowthRecord(GrowthRecord growthRecord) {
		baseDao.deleteGrowthRecord(growthRecord);
	}

	@Override
	public void deleteHealthPath(HealthPath healthPath) {
		baseDao.deleteHealthPath(healthPath);
	}

	@Override
	public void deleteHealthPlan(HealthPlan healthPlan) {
		baseDao.deleteHealthPlan(healthPlan);
	}

	@Override
	public void deleteHealthPlanRemind(HealthPlanRemind healthPlanRemind) {
		baseDao.deleteHealthPlanRemind(healthPlanRemind);
	}

	@Override
	public void deleteHealthRecord(HealthRecord healthRecord) {
		baseDao.deleteHealthRecord(healthRecord);
	}

	@Override
	public void deleteItemResult(ItemResult itemResult) {
		baseDao.deleteItemResult(itemResult);
	}
	
	@Override
	public void updateMajor(Major major) {
		baseDao.updateMajor(major);
		
	}

	@Override
	public void deleteOrderInfo(OrderInfo orderInfo) {
		baseDao.deleteOrderInfo(orderInfo);
	}

	@Override
	public void deleteOrderResults(OrderResults orderResults) {
		baseDao.deleteOrderResults(orderResults);
	}

	@Override
	public void deletePosition(Position position) {
		baseDao.deletePosition(position);
	}

	@Override
	public void deleteProduct(Product product) {
		baseDao.deleteProduct(product);
	}

	@Override
	public void deleteProductItem(ProductItem productItem) {
		baseDao.deleteProductItem(productItem);
	}

	@Override
	public void deleteProperties(Properties properties) {
		baseDao.deleteProperties(properties);
	}

	@Override
	public void deleteRecommentAwardRecord(
			RecommentAwardRecord recommentAwardRecord) {
		baseDao.deleteRecommentAwardRecord(recommentAwardRecord);
	}

	@Override
	public void deleteRefereeUser(RefereeUser refereeUser) {
		baseDao.deleteRefereeUser(refereeUser);
	}

	@Override
	public void deleteRoleList(RoleList roleList) {
		baseDao.deleteRoleList(roleList);
	}

	@Override
	public void deleteSubsidy(Subsidy subsidy) {
		baseDao.deleteSubsidy(subsidy);
	}

	@Override
	public void deleteSymptomTag(SymptomTag symptomTag) {
		baseDao.deleteSymptomTag(symptomTag);
	}

	@Override
	public void deleteUserAccount(UserAccount userAccount) {
		baseDao.deleteUserAccount(userAccount);
	}

	@Override
	public void deleteUserConsultDoctor(UserConsultDoctor userConsultDoctor) {
		baseDao.deleteUserConsultDoctor(userConsultDoctor);
	}

	@Override
	public void deleteUserCoupon(UserCoupon userCoupon) {
		baseDao.deleteUserCoupon(userCoupon);
	}

	@Override
	public void deleteUserInfo(UserInfo userInfo) {
		baseDao.deleteUserInfo(userInfo);
	}

	@Override
	public void deleteUserPoints(UserPoints userPoints) {
		baseDao.deleteUserPoints(userPoints);
	}

	@Override
	public void deleteGuidePage(GuidePage guidePage) {
		baseDao.deleteGuidePage(guidePage);
		
	}

	@Override
	public void deleteMajor(Major major) {
		baseDao.deleteMajor(major);
		
	}

	@Override
	public void saveTimeInit(TimeInit timeInit) {
		baseDao.saveTimeInit(timeInit);
		
	}

	@Override
	public void updateTimeInit(TimeInit timeInit) {
		baseDao.updateTimeInit(timeInit);
		
	}

	@Override
	public void deleteTimeInit(TimeInit timeInit) {
		baseDao.deleteTimeInit(timeInit);
		
	}

	@Override
	public void saveActivity(Activity activity) {
		baseDao.saveActivity(activity);
		
	}

	@Override
	public void updateActivity(Activity activity) {
		baseDao.updateActivity(activity);
		
	}

	@Override
	public void deleteActivity(Activity activity) {
		baseDao.deleteActivity(activity);
		
	}

	@Override
	public void saveRecommendRule(RecommendRule recommendRule) {
		baseDao.saveRecommendRule(recommendRule);
		
	}

	@Override
	public void updateRecommendRule(RecommendRule recommendRule) {
		baseDao.updateRecommendRule(recommendRule);
		
	}

	@Override
	public void deleteRecommendRule(RecommendRule recommendRule) {
		baseDao.deleteRecommendRule(recommendRule);
		
	}

	@Override
	public void saveBalance(Balance balance) {
		baseDao.saveBalance(balance);
		
	}

	@Override
	public void updateBalance(Balance balance) {
		baseDao.updateBalance(balance);
		
	}

	@Override
	public void deleteBalance(Balance balance) {
		baseDao.deleteBalance(balance);
		
	}

	@Override
	public String kyInterfaceInvoke(OrderInfo updateOrder) {
		
		return baseDao.kyInterfaceInvoke(updateOrder);
	}

	



}
