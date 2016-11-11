package com.kybaby.newbussiness.ordermanager.fo;

import java.util.List;

import com.kybaby.domain.HealthPath;

public class HealthInstructionFo {
	/**
	 * 健康指导名称
	 */
	private String healthPlan;
	/**
	 * 健康路径集合
	 */
	private List<HealthPath> healthPathList;
	public String getHealthPlan() {
		return healthPlan;
	}
	public void setHealthPlan(String healthPlan) {
		this.healthPlan = healthPlan;
	}
	public List<HealthPath> getHealthPathList() {
		return healthPathList;
	}
	public void setHealthPathList(List<HealthPath> healthPathList) {
		this.healthPathList = healthPathList;
	}
}
