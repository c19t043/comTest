package com.kybaby.operationbussiness.investigation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.NewBaseAction;
import com.kybaby.operationbussiness.investigation.domain.InvestigationMessage;
import com.kybaby.operationbussiness.investigation.domain.InvestigationOption;
import com.kybaby.operationbussiness.investigation.domain.UserData;

public class InvestigationAction extends NewBaseAction  {
	
	private static final long serialVersionUID = 1L;
	
	private InvestigationMessage investigationMessage;
	/**
	 * 序号-答案-得分：  1-A-2&2-B-3&3-A-2
	 */
	private InvestigationOption investigationOption;
	
	private List<InvestigationMessage> investigationMessageList = new ArrayList<InvestigationMessage>();
	
	private String mes="操作成功";//反馈到前端的提示信息
	
	private String showStatus;
	
	private String ipAddress;
	
	private String userCode;
	
	private UserData userData;
	
	public String execute() {
		if("saveInvestigationMessage".equals(action)) {
			//保存留言信息
			investigationBo.saveInvestigationMessage(investigationMessage);
		} else if ("saveInvestigationOption".equals(action)) {
			//保存调查的选项和答案
			investigationBo.saveInvestigationOption(investigationOption);
		} else if ("getInvestigationMessageList".equals(action)) {
			//前端显示留言
			investigationMessageList = investigationBo.getInvestigationMessageList(showStatus);
		} else if ("getUserData".equals(action)) {
			if (userData==null) {
				userData = new UserData();
			}
			//得到IP地址
			userData.setIpAddress(getRemortIP(ServletActionContext.getRequest()));
			//得到用户编码
			userData.setUserCode(String.valueOf(System.currentTimeMillis()));
		}
		return "success";
	}
	
	/**
	 * 得到IP地址
	 * @param request
	 * @return
	 */
	public String getRemortIP(HttpServletRequest request) {
	    if (request.getHeader("x-forwarded-for") == null) {
	        return request.getRemoteAddr();  
	    }  
	    return request.getHeader("x-forwarded-for");  
	}
	
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public InvestigationMessage getInvestigationMessage() {
		return investigationMessage;
	}
	public void setInvestigationMessage(InvestigationMessage investigationMessage) {
		this.investigationMessage = investigationMessage;
	}
	public InvestigationOption getInvestigationOption() {
		return investigationOption;
	}
	public void setInvestigationOption(InvestigationOption investigationOption) {
		this.investigationOption = investigationOption;
	}
	public List<InvestigationMessage> getInvestigationMessageList() {
		return investigationMessageList;
	}
	public void setInvestigationMessageList(
			List<InvestigationMessage> investigationMessageList) {
		this.investigationMessageList = investigationMessageList;
	}
	public String getShowStatus() {
		return showStatus;
	}
	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
}
