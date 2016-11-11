package com.java.asqtest.action;

import java.util.List;

import com.java.asqtest.service.AsqtestService;
import com.java.asqtest.vo.AsqParentChildActivity;
import com.java.asqtest.vo.AsqTaotiAge;
import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;

public class AsqParentChildAction extends Action{
	private static final long serialVersionUID = 1L;
	private AsqtestService asqtestService;
	private AsqParentChildActivity asqParentChildActivity;
	public AsqtestService getAsqtestService() {
		return asqtestService;
	}
	public void setAsqtestService(AsqtestService asqtestService) {
		this.asqtestService = asqtestService;
	}
	public AsqParentChildActivity getAsqParentChildActivity() {
		return asqParentChildActivity;
	}
	public void setAsqParentChildActivity(
			AsqParentChildActivity asqParentChildActivity) {
		this.asqParentChildActivity = asqParentChildActivity;
	}
	/**
	 * 分页列表
	 * @return
	 */
	public String queryAsqParentChildActivityList(){
		PageSortModel model = new PageSortModel(getHttpServletRequest(), "list");
		List<AsqParentChildActivity> asqTaotiAges = asqtestService.getAsqParentChildActivityList(model, asqParentChildActivity);
		putToRequest("list", asqTaotiAges);
		return SUCCESS;
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String toAddAsqParentChildActivity(){
		return SUCCESS;
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String toEditAsqParentChildActivity(){
		this.asqParentChildActivity = asqtestService.get(asqParentChildActivity.getId(), AsqParentChildActivity.class);
		return SUCCESS;
	}
	/**
	 * 保存更新
	 * @return
	 */
	public String saveOrUpdateAsqParentChildActivity(){
		this.addActionMessage(asqtestService.saveOrUpdateAsqParentChildActivity(asqParentChildActivity));
		return this.redirectActionResult("asqParentChildActivity_list");
	}
}
