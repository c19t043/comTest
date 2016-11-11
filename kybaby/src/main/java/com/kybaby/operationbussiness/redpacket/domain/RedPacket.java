package com.kybaby.operationbussiness.redpacket.domain;

public class RedPacket implements java.io.Serializable {

	private static final long serialVersionUID = 7459550776307126169L;
	private Long id; //主键
	private String openId; //用户openId
	private String amount; //发放金额
	private String isGrant; //是否发放（Y：是 N：否）
	private String optTime; //操作时间
	private String remark; //备注
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIsGrant() {
		return isGrant;
	}
	public void setIsGrant(String isGrant) {
		this.isGrant = isGrant;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOptTime() {
		return optTime;
	}
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}
	
}
