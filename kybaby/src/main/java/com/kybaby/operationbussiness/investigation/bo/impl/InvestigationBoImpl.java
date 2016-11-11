package com.kybaby.operationbussiness.investigation.bo.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.kybaby.operationbussiness.investigation.bo.InvestigationBo;
import com.kybaby.operationbussiness.investigation.dao.InvestigationDao;
import com.kybaby.operationbussiness.investigation.domain.InvestigationMessage;
import com.kybaby.operationbussiness.investigation.domain.InvestigationOption;

public class InvestigationBoImpl implements InvestigationBo {
	
	private InvestigationDao investigationDao;

	public InvestigationDao getInvestigationDao() {
		return investigationDao;
	}
	public void setInvestigationDao(InvestigationDao investigationDao) {
		this.investigationDao = investigationDao;
	}
	@Override
	public Long saveInvestigationMessage(InvestigationMessage investigationMessage) {
		if (investigationMessage != null) {
			
/*			String ipAddress = "";
			try {
				InetAddress addr = InetAddress.getLocalHost();
				ipAddress = addr.getHostAddress().toString();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}*/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currentDate = new Date(System.currentTimeMillis());
			String currentDateStr = sdf.format(currentDate);
			
			investigationMessage.setOptTime(currentDateStr);//获得当前系统时间
			investigationMessage.setIsShow("1");//默认在公众号中显示
		}
		return investigationDao.saveInvestigationMessage(investigationMessage);
	}
	@Override
	public void saveInvestigationOption(InvestigationOption investigationOption) {
		if (investigationOption != null && investigationOption.getContent()!=null) {
			
/*			String ipAddress = "";
			try {
				InetAddress addr = InetAddress.getLocalHost();
				ipAddress = addr.getHostAddress().toString();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}*/
			
			String ipAddress = investigationOption.getIpAddress();
			String userCode = investigationOption.getUserCode();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currentDate = new Date(System.currentTimeMillis());
			String currentDateStr = sdf.format(currentDate);
			
			String[] arrayContent = investigationOption.getContent().split("&");
			if (arrayContent!=null && arrayContent.length > 0) {
				for (int i=0; i<arrayContent.length; i++) {
					String aloneContent = arrayContent[i];
					String[] arrayAloneContent = aloneContent.split("-");
						String sortOption = arrayAloneContent[0];
						String selectAnswer = arrayAloneContent[1];
						String answerScore = arrayAloneContent[2];
						
						InvestigationOption saveInvestigationOption = new InvestigationOption();
						saveInvestigationOption.setSortOption(sortOption);//题目序号
						saveInvestigationOption.setSelectAnswer(selectAnswer);//选择的答案，如多选，用::区分
						saveInvestigationOption.setAnswerScore(answerScore);//选择的答案得分
						
						saveInvestigationOption.setIpAddress(ipAddress);//获得本机IP
						saveInvestigationOption.setUserCode(userCode);//获得当前用户编码
						saveInvestigationOption.setOptTime(currentDateStr);//获得当前系统时间
						
						investigationDao.saveInvestigationOption(saveInvestigationOption);
				}
			}
		}
		
	}
	
	@Override
	public List<InvestigationMessage> getInvestigationMessageList(String showStatus) {
		return investigationDao.getInvestigationMessageList(showStatus);
	}

}
