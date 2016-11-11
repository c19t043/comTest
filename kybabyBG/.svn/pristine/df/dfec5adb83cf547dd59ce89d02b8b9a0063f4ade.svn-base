package com.kybaby.newbussiness.doctorring.action;

import com.kybaby.action.BaseAction;
import com.kybaby.kyinterface.bo.KyInterfaceBo;
import com.kybaby.newbussiness.detailrecord.bo.DetailRecordService;
import com.kybaby.newbussiness.diseasesanddrug.bo.DrugInfoService;
import com.kybaby.newbussiness.doctorclinic.bo.DoctorClinicService;
import com.kybaby.newbussiness.doctoroperateflow.bo.OperationFlowService;
import com.kybaby.newbussiness.doctorring.bo.DoctorRingTypeBo;
import com.kybaby.newbussiness.doctorring.bo.PostInfoBo;
import com.kybaby.newbussiness.doctorring.bo.PostInfoLabelBo;
import com.kybaby.newbussiness.doctorring.bo.PostReplyBo;
import com.kybaby.newbussiness.doctorring.bo.RingLabelBo;
import com.kybaby.newbussiness.doctorring.bo.SubscribeUserBo;
import com.kybaby.newbussiness.doctorring.bo.TheTypeInfoBo;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.DoctorMoneyRecordService;
import com.kybaby.newbussiness.medicalorgandbusiness.bo.OrganBusinessService;
import com.kybaby.newbussiness.member.bo.MemberService;
import com.kybaby.newbussiness.messagecenter.bo.MessageCenterBo;
import com.kybaby.newbussiness.operationstrategy.bo.OperationStrategyService;
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
	protected static final String splitStr="::";
	protected static final String startStr="admin_";
	protected static final String imgSourceUrl="http://www.qiyico.com/kybabyBG/admin/images/";
	protected static final String oldUrl="/kybabyBG/admin/images/";
	
	/**
	 * 流程操作服务类
	 */
	protected OperationFlowService operationFlowService;
	/**
	 * 门诊及多点执业服务类
	 */
	protected DoctorClinicService doctorClinicService;
	/**
	 * 奖励活动改造服务接口
	 */
	protected OperationStrategyService operationStrategyService;
	/**
	 * 机构及开展业务服务类
	 */
	protected OrganBusinessService organBusinessService;
	/**
	 * 明细记录服务类
	 */
	protected DetailRecordService detailRecordService;
	/**
	 * 医生薪酬记录服务
	 */
	protected DoctorMoneyRecordService doctorMoneyRecordService;
	/**
	 * 会员服务类
	 */
	protected MemberService memberService;
	/**
	 * 药品服务
	 */
	protected DrugInfoService drugInfoService;
	/**
	 * 消息中心
	 */
	protected MessageCenterBo messageCenterBo;
	/**
	 * 快医接口业务类
	 */
	protected KyInterfaceBo kyInterfaceBo;
	
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
	public OperationStrategyService getOperationStrategyService() {
		return operationStrategyService;
	}
	public void setOperationStrategyService(
			OperationStrategyService operationStrategyService) {
		this.operationStrategyService = operationStrategyService;
	}
	public OrganBusinessService getOrganBusinessService() {
		return organBusinessService;
	}
	public void setOrganBusinessService(OrganBusinessService organBusinessService) {
		this.organBusinessService = organBusinessService;
	}
	public DetailRecordService getDetailRecordService() {
		return detailRecordService;
	}
	public void setDetailRecordService(DetailRecordService detailRecordService) {
		this.detailRecordService = detailRecordService;
	}
	public DoctorMoneyRecordService getDoctorMoneyRecordService() {
		return doctorMoneyRecordService;
	}
	public void setDoctorMoneyRecordService(
			DoctorMoneyRecordService doctorMoneyRecordService) {
		this.doctorMoneyRecordService = doctorMoneyRecordService;
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public DrugInfoService getDrugInfoService() {
		return drugInfoService;
	}
	public void setDrugInfoService(DrugInfoService drugInfoService) {
		this.drugInfoService = drugInfoService;
	}
	public MessageCenterBo getMessageCenterBo() {
		return messageCenterBo;
	}
	public void setMessageCenterBo(MessageCenterBo messageCenterBo) {
		this.messageCenterBo = messageCenterBo;
	}
	public KyInterfaceBo getKyInterfaceBo() {
		return kyInterfaceBo;
	}
	public void setKyInterfaceBo(KyInterfaceBo kyInterfaceBo) {
		this.kyInterfaceBo = kyInterfaceBo;
	}

}
