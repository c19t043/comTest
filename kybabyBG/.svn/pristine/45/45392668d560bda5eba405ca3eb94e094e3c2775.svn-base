package com.kybaby.newbussiness.member.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserChildcareAppointmentInfo;
import com.kybaby.newbussiness.member.bo.MemberService;
import com.kybaby.newbussiness.member.dao.MemberDao;
import com.kybaby.newbussiness.member.domain.MemberBuyInfo;
import com.kybaby.newbussiness.member.domain.MemberManage;
import com.kybaby.newbussiness.member.domain.MemberRule;
import com.kybaby.newbussiness.member.domain.MemberType;
import com.kybaby.newbussiness.member.domain.MemberTypeRule;
import com.kybaby.util.ConstantManage;

public class MemberServiceImpl implements MemberService{
	private MemberDao memberDao;

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public Long saveOrUpdateMemberBuyInfo(MemberBuyInfo memberBuyInfo) {
		memberBuyInfo.setBuyTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
		return memberDao.saveOrUpdateMemberBuyInfo(memberBuyInfo);
	}

	@Override
	public Long saveOrUpdateMemberManage(MemberManage memberManage) {
		String effectStartDate = DateManage.getDateStr("yyyy-MM-dd HH:mm:ss");
		memberManage.setEffectStartDate(effectStartDate);
		return memberDao.saveOrUpdateMemberManage(memberManage);
	}

	@Override
	public MemberBuyInfo getMemberBuyInfoById(Long id) {
		return memberDao.getMemberBuyInfoById(id);
	}

	@Override
	public MemberManage getMemberManageBySomething(Long keyId,
			UserInfo userInfo, MemberType memberType) {
		return memberDao.getMemberManageBySomething(keyId, userInfo, memberType);
	}

	@Override
	public List<MemberType> getMemberTypeList(MemberType memberType) {
		return memberDao.getMemberTypeList(memberType);
	}

	@Override
	public List<MemberManage> getMemberManageList(MemberManage memberManage) {
		return memberDao.getMemberManageList(memberManage);
	}

	@Override
	public List<MemberRule> getMemberRuleList(MemberRule memberRule) {
		return memberDao.getMemberRuleList(memberRule);
	}

	@Override
	public Long saveOrUpdateMemberRule(MemberRule memberRule) {
		return memberDao.saveOrUpdateMemberRule(memberRule);
	}

	@Override
	public Long saveOrUpdateMemberType(MemberType memberType) {
		return memberDao.saveOrUpdateMemberType(memberType);
	}

	@Override
	public List<MemberTypeRule> getMemberTypeRuleList(MemberType memberType,
			MemberRule memberRule) {
		return memberDao.getMemberTypeRuleList(memberType, memberRule);
	}

	@Override
	public void saveOrUpdateMemberTypeRule(MemberTypeRule memberTypeRule) {
		memberDao.saveOrUpdateMemberTypeRule(memberTypeRule);
	}

	@Override
	public void delMemberTypeRule(MemberType memberType) {
		memberDao.delMemberTypeRule(memberType);
	}
}
