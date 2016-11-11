package com.kybaby.newbussiness.doctorring.action;

import com.kybaby.action.BaseAction;
import com.kybaby.bo.AccountBo;
import com.kybaby.bo.DoctorInfoBo;
import com.kybaby.newbussiness.doctorclinic.bo.DoctorClinicOrderService;
import com.kybaby.newbussiness.doctorclinic.bo.DoctorClinicService;
import com.kybaby.newbussiness.doctorclinic.bo.FamilyDoctorServeService;
import com.kybaby.newbussiness.doctorring.bo.DoctorRingTypeBo;
import com.kybaby.newbussiness.doctorring.bo.PostInfoBo;
import com.kybaby.newbussiness.doctorring.bo.PostInfoLabelBo;
import com.kybaby.newbussiness.doctorring.bo.PostReplyBo;
import com.kybaby.newbussiness.doctorring.bo.RingLabelBo;
import com.kybaby.newbussiness.doctorring.bo.SubscribeUserBo;
import com.kybaby.newbussiness.doctorring.bo.TheTypeInfoBo;
import com.kybaby.newbussiness.doctorsign.bo.DoctorRegisterDataGatherService;
import com.kybaby.newbussiness.familydoctor.bo.FdServiceMemberService;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.ArchivesInfoService;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.ChildCareChargeService;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.DrugInfoService;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.OperaMedicalRecordsBo;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.OrgBusinessManageService;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.OrgClinicService;
import com.kybaby.newbussiness.ordermanager.bo.OperationFlowService;
import com.kybaby.newbussiness.ordermanager.bo.OrderManagerService;

/**
 * @ClassName:NewBaseAction
 * @Description:医生圈相关的基础BO集合
 * @author Hoolee
 * @date 2015年12月10日下午2:52:58
 */
public class NewBaseAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	protected DoctorRingTypeBo doctorRingTypeBo;
	protected TheTypeInfoBo theTypeInfoBo;
	protected SubscribeUserBo subscribeUserBo;
	protected PostInfoBo postInfoBo;
	protected PostReplyBo postReplyBo;
	protected RingLabelBo ringLabelBo;
	protected PostInfoLabelBo postInfoLabelBo;
	protected DoctorInfoBo doctorInfoBo;
	protected AccountBo accountBo;
	
	protected static final String splitStr="::";
	protected static final String startStr="admin_";
	protected static final String doctorAuditStr="已通过";
	
	protected OrderManagerService orderManagerService;
	protected OperationFlowService operationFlowService;
	/**
	 * 医生数据采集
	 */
	protected DoctorRegisterDataGatherService doctorRegisterDataGatherService;
	/**
	 * 医生门诊及多点执业业务层接口
	 */
	protected DoctorClinicService doctorClinicService;
	/**
	 * 医生门诊订单业务层接口
	 */
	protected DoctorClinicOrderService doctorClinicOrderService;
	/**
	 * 家庭医生服务接口
	 */
	protected FamilyDoctorServeService familyDoctorServeService;
	/**
	 * 机构开放业务管理
	 */
	protected OrgBusinessManageService orgBusinessManageService;
	/**
	 * 机构门诊业务
	 */
	protected OrgClinicService orgClinicService;
	/**
	 * 儿保收费业务
	 */
	protected ChildCareChargeService childCareChargeService;
	/**
	 * 药物管理
	 */
	protected DrugInfoService drugInfoService;
	/**
	 * 用户身份信息管理
	 */
	protected ArchivesInfoService archivesInfoService;
	/**
	 *成员信息
	 */
	protected FdServiceMemberService fdServiceMemberService;
	/**
	 * 就诊登记记录信息
	 */
	protected OperaMedicalRecordsBo operaMedicalRecordsBo;
	public FdServiceMemberService getFdServiceMemberService() {
		return fdServiceMemberService;
	}
	public void setFdServiceMemberService(
			FdServiceMemberService fdServiceMemberService) {
		this.fdServiceMemberService = fdServiceMemberService;
	}
	public DoctorRingTypeBo getDoctorRingTypeBo() {
		return doctorRingTypeBo;
	}
	public void setDoctorRingTypeBo(DoctorRingTypeBo doctorRingTypeBo) {
		this.doctorRingTypeBo = doctorRingTypeBo;
	}
	public TheTypeInfoBo getTheTypeInfoBo() {
		return theTypeInfoBo;
	}
	public void setTheTypeInfoBo(TheTypeInfoBo theTypeInfoBo) {
		this.theTypeInfoBo = theTypeInfoBo;
	}
	public SubscribeUserBo getSubscribeUserBo() {
		return subscribeUserBo;
	}
	public void setSubscribeUserBo(SubscribeUserBo subscribeUserBo) {
		this.subscribeUserBo = subscribeUserBo;
	}
	public PostInfoBo getPostInfoBo() {
		return postInfoBo;
	}
	public void setPostInfoBo(PostInfoBo postInfoBo) {
		this.postInfoBo = postInfoBo;
	}
	public PostReplyBo getPostReplyBo() {
		return postReplyBo;
	}
	public void setPostReplyBo(PostReplyBo postReplyBo) {
		this.postReplyBo = postReplyBo;
	}
	public RingLabelBo getRingLabelBo() {
		return ringLabelBo;
	}
	public void setRingLabelBo(RingLabelBo ringLabelBo) {
		this.ringLabelBo = ringLabelBo;
	}
	public PostInfoLabelBo getPostInfoLabelBo() {
		return postInfoLabelBo;
	}
	public void setPostInfoLabelBo(PostInfoLabelBo postInfoLabelBo) {
		this.postInfoLabelBo = postInfoLabelBo;
	}
	public OrderManagerService getOrderManagerService() {
		return orderManagerService;
	}
	public void setOrderManagerService(OrderManagerService orderManagerService) {
		this.orderManagerService = orderManagerService;
	}
	public OperationFlowService getOperationFlowService() {
		return operationFlowService;
	}
	public void setOperationFlowService(OperationFlowService operationFlowService) {
		this.operationFlowService = operationFlowService;
	}
	public DoctorClinicService getDoctorClinicService() {
		return doctorClinicService;
	}
	public void setDoctorClinicService(DoctorClinicService doctorClinicService) {
		this.doctorClinicService = doctorClinicService;
	}
	public DoctorClinicOrderService getDoctorClinicOrderService() {
		return doctorClinicOrderService;
	}
	public void setDoctorClinicOrderService(
			DoctorClinicOrderService doctorClinicOrderService) {
		this.doctorClinicOrderService = doctorClinicOrderService;
	}
	@Override
	public DoctorInfoBo getDoctorInfoBo() {
		return doctorInfoBo;
	}
	@Override
	public void setDoctorInfoBo(DoctorInfoBo doctorInfoBo) {
		this.doctorInfoBo = doctorInfoBo;
	}
	@Override
	public AccountBo getAccountBo() {
		return accountBo;
	}
	@Override
	public void setAccountBo(AccountBo accountBo) {
		this.accountBo = accountBo;
	}
	public FamilyDoctorServeService getFamilyDoctorServeService() {
		return familyDoctorServeService;
	}
	public void setFamilyDoctorServeService(
			FamilyDoctorServeService familyDoctorServeService) {
		this.familyDoctorServeService = familyDoctorServeService;
	}
	public OrgBusinessManageService getOrgBusinessManageService() {
		return orgBusinessManageService;
	}
	public void setOrgBusinessManageService(
			OrgBusinessManageService orgBusinessManageService) {
		this.orgBusinessManageService = orgBusinessManageService;
	}
	public OrgClinicService getOrgClinicService() {
		return orgClinicService;
	}
	public void setOrgClinicService(OrgClinicService orgClinicService) {
		this.orgClinicService = orgClinicService;
	}
	public ChildCareChargeService getChildCareChargeService() {
		return childCareChargeService;
	}
	public void setChildCareChargeService(
			ChildCareChargeService childCareChargeService) {
		this.childCareChargeService = childCareChargeService;
	}
	public DrugInfoService getDrugInfoService() {
		return drugInfoService;
	}
	public void setDrugInfoService(DrugInfoService drugInfoService) {
		this.drugInfoService = drugInfoService;
	}
	public ArchivesInfoService getArchivesInfoService() {
		return archivesInfoService;
	}
	public void setArchivesInfoService(ArchivesInfoService archivesInfoService) {
		this.archivesInfoService = archivesInfoService;
	}
	public OperaMedicalRecordsBo getOperaMedicalRecordsBo() {
		return operaMedicalRecordsBo;
	}
	public void setOperaMedicalRecordsBo(OperaMedicalRecordsBo operaMedicalRecordsBo) {
		this.operaMedicalRecordsBo = operaMedicalRecordsBo;
	}
	public void setDoctorRegisterDataGatherService(
			DoctorRegisterDataGatherService doctorRegisterDataGatherService) {
		this.doctorRegisterDataGatherService = doctorRegisterDataGatherService;
	}
}
