package com.java.familydoctor.userbuyrecord.action;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.userbuyrecord.service.FdUserBuyRecordService;
import com.java.familydoctor.userbuyrecord.vo.FdUserBuyRecord;
import com.java.platform.core.Action;

public class FdUserBuyRecordAction extends Action{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 家庭医生用户购买记录服务方法
	 */
	
	private FdUserBuyRecordService fdUserBuyRecordService;
	
	/**
	 * 家庭医生用户购买记录实体类
	 */
	private FdUserBuyRecord fdUserBuyRecord;
	
	public FdUserBuyRecordService getFdUserBuyRecordService() {
		return fdUserBuyRecordService;
	}

	public void setFdUserBuyRecordService(
			FdUserBuyRecordService fdUserBuyRecordService) {
		this.fdUserBuyRecordService = fdUserBuyRecordService;
	}
	
	public FdUserBuyRecord getFdUserBuyRecord() {
		return fdUserBuyRecord;
	}

	public void setFdUserBuyRecord(FdUserBuyRecord fdUserBuyRecord) {
		this.fdUserBuyRecord = fdUserBuyRecord;
	}

	public String getFdUserBuyRecordList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(fdUserBuyRecord == null){
			fdUserBuyRecord = new FdUserBuyRecord();
		}
		List<FdUserBuyRecord> list = this.fdUserBuyRecordService.getFdUserBuyRecordByPage(psm, fdUserBuyRecord);
		this.putToRequest("list",list);
		return SUCCESS;
	}
	
}
