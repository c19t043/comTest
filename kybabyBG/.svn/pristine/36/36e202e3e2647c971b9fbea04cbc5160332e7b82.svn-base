package com.kybaby.newbussiness.senddoctor.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.newbussiness.senddoctor.domain.RuleBasic;
import com.kybaby.newbussiness.senddoctor.domain.RulesConfigureRecord;
import com.kybaby.newbussiness.senddoctor.domain.RulesFieldBasic;
import com.opensymphony.xwork2.ActionContext;

public class SendDoctorAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	/**
	 * 规则配置信息
	 */
	private RulesConfigureRecord rulesConfigureRecord;
	/**
	 * 规则基本信息
	 */
	private RuleBasic ruleBasic;
	/**
	 * 规则基本字段
	 */
	private RulesFieldBasic rulesFieldBasic;
	/**
	 * 订单信息
	 */
	private OrderInfo orderInfo;
	
	private List<RuleBasic> ruleBasicList = new ArrayList<RuleBasic>();
	private List<RulesFieldBasic> rulesFieldBasicList = new ArrayList<RulesFieldBasic>();
	private List<RulesConfigureRecord> rulesConfigureRecordList = new ArrayList<RulesConfigureRecord>();
	/**
	 * 返回的提示信息
	 */
	private String mes="";
	public List<RuleBasic> getRuleBasicList() {
		return ruleBasicList;
	}

	public void setRuleBasicList(List<RuleBasic> ruleBasicList) {
		this.ruleBasicList = ruleBasicList;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public RuleBasic getRuleBasic() {
		return ruleBasic;
	}

	public void setRuleBasic(RuleBasic ruleBasic) {
		this.ruleBasic = ruleBasic;
	}

	public RulesConfigureRecord getRulesConfigureRecord() {
		return rulesConfigureRecord;
	}

	public void setRulesConfigureRecord(RulesConfigureRecord rulesConfigureRecord) {
		this.rulesConfigureRecord = rulesConfigureRecord;
	}

	public RulesFieldBasic getRulesFieldBasic() {
		return rulesFieldBasic;
	}

	public void setRulesFieldBasic(RulesFieldBasic rulesFieldBasic) {
		this.rulesFieldBasic = rulesFieldBasic;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public List<RulesFieldBasic> getRulesFieldBasicList() {
		return rulesFieldBasicList;
	}

	public void setRulesFieldBasicList(List<RulesFieldBasic> rulesFieldBasicList) {
		this.rulesFieldBasicList = rulesFieldBasicList;
	}

	public List<RulesConfigureRecord> getRulesConfigureRecordList() {
		return rulesConfigureRecordList;
	}

	public void setRulesConfigureRecordList(
			List<RulesConfigureRecord> rulesConfigureRecordList) {
		this.rulesConfigureRecordList = rulesConfigureRecordList;
	}

	public String execute(){
		/**
		 * 保存或更新规则设置信息
		 */
		if(action.equals("saveOrUpdateRule")){
			//ruleBasic = (RuleBasic) ActionContext.getContext().getSession().get("ruleBasic");
			if(ruleBasic == null){
				ruleBasic = new RuleBasic();
			}
			if(ruleBasic.getRuleBasicId() == null){
				ruleBasic.setCreateTime(new Date());
				ruleBasic.setModifyTime(new Date());
				sendDoctorService.saveRule(ruleBasic,rulesConfigureRecordList);
			}else{
				ruleBasic.setModifyTime(new Date());
				sendDoctorService.updateRule(ruleBasic,rulesConfigureRecordList);
			}
			mes = "操作成功";
			return "mySuccess";
		}
		/**
		 * 得到规则基本信息列表
		 */
		if(action.equals("getRuleBasicList")){
			if(ruleBasic == null){
				ruleBasic = new RuleBasic();
			}
			ruleBasicList = sendDoctorService.getRuleBasicList(ruleBasic);
			mes = "操作成功";
			if(ruleBasic.getRuleBasicId() != null && ruleBasic.getRuleBasicId().longValue() == 0L){
				StringBuffer result = new StringBuffer("");
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				if(ruleBasicList != null){
					for(int i=0;i < ruleBasicList.size();i ++){
						result.append(
						"<tr onclick='click_light(this)'>"+
							"<td style='width:10%'>"+(i+1)+"</td>"+
							"<td style='width:20%'>"+(ruleBasicList.get(i)).getRuleName()+"</td>"+
							"<td style='width:30%'>"+(ruleBasicList.get(i)).getRuleRemark()+"</td>"+
							"<td style='width:10%'>"+(ruleBasicList.get(i)).getIsStart()+"</td>"+
							"<td style='width:15%'>"+(ruleBasicList.get(i)).getModifyTime()+"</td>"+
							"<td style='width:15%'>"+
								"<div class='td_change_button' "+
									" ruleBasicId='"+(ruleBasicList.get(i)).getRuleBasicId()+"' "+
									" ruleName='"+(ruleBasicList.get(i)).getRuleName()+"' "+
									" ruleRemark='"+(ruleBasicList.get(i)).getRuleRemark()+"' "+
									" isStart='"+(ruleBasicList.get(i)).getIsStart()+"' "+
									" modifyTime='"+(ruleBasicList.get(i)).getModifyTime()+"' "+
								" onclick='read_info(this)'>修改</div>"+
							"</td>"+
						"</tr>"
						);
					}
				}
				try {
					ServletActionContext.getResponse().getWriter().print(result.toString());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			return "success";
		}
		/**
		 * 得到规则字段列表RulesFieldBasic.java
		 */
		if(action.equals("getRulesFieldBasicList")){
			if(rulesFieldBasic == null){
				rulesFieldBasic = new RulesFieldBasic();
			}
			rulesFieldBasicList = sendDoctorService.getRulesFieldBasicList(rulesFieldBasic);
			mes = "操作成功";
			return "success";
		}
		/**
		 * 得到规则配置信息
		 */
		if(action.equals("getRulesConfigureRecordList")){
			this.rulesConfigureRecordList = sendDoctorService.getRulesConfigureRecordListByRuleBasicId(ruleBasic);
			mes = "操作成功";
			return "success";
		}
		return "success";
	}
}
