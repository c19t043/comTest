package com.kybaby.operationbussiness.investigation.bo;

import java.util.List;

import com.kybaby.operationbussiness.investigation.domain.InvestigationMessage;
import com.kybaby.operationbussiness.investigation.domain.InvestigationOption;

public interface InvestigationBo {
	
	/**
	 * 保存留言信息
	 * @param investigationMessage
	 * @return
	 */
	Long saveInvestigationMessage(InvestigationMessage investigationMessage);
	
	/**
	 * 保存答题信息
	 * @param investigationOption
	 */
	void saveInvestigationOption(InvestigationOption investigationOption);
	
	/**
	 * 得到留言列表
	 * @param showStatus
	 * @return
	 */
	List<InvestigationMessage> getInvestigationMessageList(String showStatus);
}
