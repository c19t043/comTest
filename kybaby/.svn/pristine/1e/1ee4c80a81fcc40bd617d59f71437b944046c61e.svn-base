package com.kybaby.operationbussiness.redpacket.bo.impl;

import java.util.List;

import com.kybaby.operationbussiness.redpacket.bo.RedPacketBo;
import com.kybaby.operationbussiness.redpacket.dao.RedPacketDao;
import com.kybaby.operationbussiness.redpacket.domain.RedPacket;
import com.kybaby.operationbussiness.redpacket.domain.RedPacketSet;

public class RedPacketBoImpl implements RedPacketBo {

	private RedPacketDao redPacketDao;

	public RedPacketDao getRedPacketDao() {
		return redPacketDao;
	}
	public void setRedPacketDao(RedPacketDao redPacketDao) {
		this.redPacketDao = redPacketDao;
	}
	
	@Override
	public String sendRedpacket(String reOpenid, int totalAmount) {
		return redPacketDao.sendRedpacket(reOpenid, totalAmount);
	}
	
	@Override
	public RedPacketSet getRedPacketSetInfo() {
		return redPacketDao.getRedPacketSetInfo();
	}
	
	@Override
	public float getGrantAmount(RedPacketSet redPacketSet) {
		return redPacketDao.getGrantAmount(redPacketSet);
	}
	
	@Override
	public List<RedPacket> getSendRedPacketListByUser(String reOpenid) {
		return redPacketDao.getSendRedPacketListByUser(reOpenid);
	}
	@Override
	public void saveUserSendRedPacketInfo(String reOpenid, float sendAmount, RedPacketSet redPacketSet) {
		redPacketDao.saveUserSendRedPacketInfo(reOpenid, sendAmount, redPacketSet);
	}
	
}
