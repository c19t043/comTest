package com.kybaby.newbussiness.familydoctor.domain;

/**
 * 聊天记录关联宝宝记录
 * @author lihao
 *
 */

public class ConsultBabySet implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long logId;
	private ConsultBabyInfo consultBabyInfo;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public ConsultBabyInfo getConsultBabyInfo() {
		return consultBabyInfo;
	}

	public void setConsultBabyInfo(ConsultBabyInfo consultBabyInfo) {
		this.consultBabyInfo = consultBabyInfo;
	}

}