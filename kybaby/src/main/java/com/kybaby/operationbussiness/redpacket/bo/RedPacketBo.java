package com.kybaby.operationbussiness.redpacket.bo;

import java.util.List;

import com.kybaby.operationbussiness.redpacket.domain.RedPacket;
import com.kybaby.operationbussiness.redpacket.domain.RedPacketSet;

public interface RedPacketBo {
	
	/**
	 * 红包发送接口
	 * @param reOpenid
	 * @param totalAmount
	 */
	String sendRedpacket(String reOpenid, int totalAmount);
	
	/**
	 * 得到生效的红包配置信息
	 * @return
	 */
	RedPacketSet getRedPacketSetInfo();
	
	/**
	 * 得到发放金额
	 * @param redPacketSet
	 * @return
	 */
	float getGrantAmount(RedPacketSet redPacketSet);
	
	/**
	 * 根据用户openId得到发送红包信息
	 * @param reOpenid
	 * @return
	 */
	List<RedPacket> getSendRedPacketListByUser(String reOpenid);
	
	
	/**
	 * 保存用户发放红包记录
	 * @param reOpenid
	 * @param sendAmount
	 * @param redPacketSet
	 */
	void saveUserSendRedPacketInfo(String reOpenid, float sendAmount, RedPacketSet redPacketSet);

}
