package com.kybaby.newbussiness.member.bo.impl;

import java.util.List;

import com.kybaby.domain.Product;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.member.bo.MemberService;
import com.kybaby.newbussiness.member.dao.MemberDao;
import com.kybaby.newbussiness.member.domain.MemberBuyInfo;
import com.kybaby.newbussiness.member.domain.MemberManage;
import com.kybaby.newbussiness.member.domain.MemberRule;
import com.kybaby.newbussiness.member.domain.MemberType;
import com.kybaby.newbussiness.member.domain.MemberTypeRule;
import com.kybaby.util.DateManage;

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
	public MemberType getMemberTypeById(Long id) {
		return memberDao.getMemberTypeById(id);
	}

	@Override
	public List<MemberManage> getMemberManageList(UserInfo userInfo) {
		return memberDao.getMemberManageList(userInfo);
	}

	@Override
	public List<MemberTypeRule> getMemberTypeRuleList(MemberType memberType,
			MemberRule memberRule) {
		return memberDao.getMemberTypeRuleList(memberType, memberRule);
	}

	@Override
	public MemberBuyInfo getMemberBuyInfoByOrderNum(String orderNum) {
		return memberDao.getMemberBuyInfoByOrderNum(orderNum);
	}

	@Override
	public Product getProductByMemberType(MemberType memberType) {
		return memberDao.getProductByMemberType(memberType);
	}
}
