package com.kybaby.newbussiness.asqtest.domain;

import java.sql.Timestamp;

import com.kybaby.domain.UserInfo;

/**
 * AsqResultScoreEx entity. @author MyEclipse Persistence Tools
 */

public class AsqResultScoreEx implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 待审阅";
	 */
	public static final String WAIT_CHECK = "待审阅";
	/**
	 * "已审阅";
	 */
	public static final String ALREAD_CHECK = "已审阅";
	/**
	 *  "已发送";
	 */
	public static final String SEND_TO_USER = "已发送";
	private Long id;
	private AsqTestUserInfo asqTestUserInfo;
	private AsqTaotiAge asqTaotiAge;
	private AsqTaoti asqTaoti;
	private UserInfo userInfo;
	private String sumScore;
	private String resultDescription;
	private String status;
	private Timestamp createTime;
	private Timestamp modifyTime;
	private Long b2cGoodsOrderId;

	// Constructors

	/** default constructor */
	public AsqResultScoreEx() {
	}

	/** full constructor */
	public AsqResultScoreEx(Long asqTestUserInfoId, Long asqTaotiAgeId,
			Long asqTaotiId, Long userId, String sumScore,
			String resultDescription, String status) {
		this.sumScore = sumScore;
		this.resultDescription = resultDescription;
		this.status = status;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSumScore() {
		return this.sumScore;
	}

	public void setSumScore(String sumScore) {
		this.sumScore = sumScore;
	}

	public String getResultDescription() {
		return this.resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AsqTestUserInfo getAsqTestUserInfo() {
		return asqTestUserInfo;
	}

	public void setAsqTestUserInfo(AsqTestUserInfo asqTestUserInfo) {
		this.asqTestUserInfo = asqTestUserInfo;
	}

	public AsqTaotiAge getAsqTaotiAge() {
		return asqTaotiAge;
	}

	public void setAsqTaotiAge(AsqTaotiAge asqTaotiAge) {
		this.asqTaotiAge = asqTaotiAge;
	}

	public AsqTaoti getAsqTaoti() {
		return asqTaoti;
	}

	public void setAsqTaoti(AsqTaoti asqTaoti) {
		this.asqTaoti = asqTaoti;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getB2cGoodsOrderId() {
		return b2cGoodsOrderId;
	}

	public void setB2cGoodsOrderId(Long b2cGoodsOrderId) {
		this.b2cGoodsOrderId = b2cGoodsOrderId;
	}

}