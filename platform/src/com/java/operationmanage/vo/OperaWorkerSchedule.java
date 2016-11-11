package com.java.operationmanage.vo;

import com.java.platform.core.BaseDomain;
import com.java.platform.user.vo.User;

/**
 * OperaWorkerSchedule entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class OperaWorkerSchedule extends BaseDomain  implements java.io.Serializable {

	// Fields

	private Long id;
	private OperaBaseSchedule operaBaseSchedule;
	private String workBeginTime;
	private String workEndTime;
	private String restBeginTime;
	private String restEndTime;
	private User user;
	private String remarks;
	private String isEnable;
	private OperaBusinessType operaBusinessType;
	
	private String workTime;
	private String restTime;
	// Constructors

	/** default constructor */
	public OperaWorkerSchedule() {
	}

	public String getRestTime() {
		return restTime;
	}

	public void setRestTime(String restTime) {
		this.restTime = restTime;
	}

	public Long getId() {
		return id;
	}

	public OperaBusinessType getOperaBusinessType() {
		return operaBusinessType;
	}

	public void setOperaBusinessType(OperaBusinessType operaBusinessType) {
		this.operaBusinessType = operaBusinessType;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public OperaBaseSchedule getOperaBaseSchedule() {
		return operaBaseSchedule;
	}

	public void setOperaBaseSchedule(OperaBaseSchedule operaBaseSchedule) {
		this.operaBaseSchedule = operaBaseSchedule;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getWorkBeginTime() {
		return workBeginTime;
	}

	public void setWorkBeginTime(String workBeginTime) {
		this.workBeginTime = workBeginTime;
	}

	public String getWorkEndTime() {
		return workEndTime;
	}

	public void setWorkEndTime(String workEndTime) {
		this.workEndTime = workEndTime;
	}

	public String getRestBeginTime() {
		return restBeginTime;
	}

	public void setRestBeginTime(String restBeginTime) {
		this.restBeginTime = restBeginTime;
	}

	public String getRestEndTime() {
		return restEndTime;
	}

	public void setRestEndTime(String restEndTime) {
		this.restEndTime = restEndTime;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
}