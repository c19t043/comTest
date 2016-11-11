package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.RecommendRule;

public class RecommendRuleHandle extends BaseAction {

	private String mes="";
	private String action="";
	
	private String awardMethod;//奖励方式
	private String awardValue; //奖励值
	private String whenToAward;//奖励时机
	private String ruleName;   //规则名字
	private String awardDrection;//奖励方向
	private String status;       //状态
	private long   updateId;   //修改推荐规则的id
	
	private List allRecommendRule=new ArrayList(); //  所有的推荐规则
	private List allUsefulCoupon=new ArrayList();  //  所有可用的优惠卷
	
	@SuppressWarnings("unchecked")
	public String execute()
	{
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"......");
		if(action.equals("all"))
		{
			allRecommendRule=recommendRuleBo.getAllRecommendRule();
			allUsefulCoupon=couponManageBo.getAllCoupon();
			mes="成功";
			return "success";
		}
		
		if(action.equals("getUsefulCoupon"))
		{
			allUsefulCoupon=couponManageBo.getUsefulCoupon();
			mes="成功";
			return "success";
		}
		
		if(action.equals("add"))
		{
			
			RecommendRule addRR=new RecommendRule(null, null, null, whenToAward, ruleName, status, "双向");
			if(awardMethod!=null)
			{
			if(awardMethod.equals("现金"))
			{
				addRR.setAmount(Double.valueOf(awardValue).doubleValue());
			}
			
			if(awardMethod.equals("积分"))
			{
				addRR.setPoints(Long.valueOf(awardValue).longValue());
			}
			
			if(awardMethod.equals("优惠卷"))
			{
				addRR.setCoupon(Long.valueOf(awardValue).longValue());
			}
			baseBo.saveRecommendRule(addRR);
			List<Object[]> countList=recommendRuleBo.getcountList();
			if(countList==null)
			{
				mes="成功";
				return "success";
			}
			else
			{
				boolean flag=true;
				for(Object[] a:countList)
				{
					long amountCount=Long.valueOf(String.valueOf(a[1]));
					long pointsCount=Long.valueOf(String.valueOf(a[2]));
					long couponCount=Long.valueOf(String.valueOf(a[3]));
					if(amountCount>1||pointsCount>1||couponCount>1)
					{
						flag=false;
						break;
					}
					
				}
				if(flag)
				{
					mes="成功";
					return "success";
				}
				else
				{
					baseBo.deleteRecommendRule(addRR);
					mes="每一种推荐类型的三种奖励方式只能存在一次,你已经定义了该类型的推荐规则,你可以修改原来该规则状态为N，继续添加此类规则";
					return "fail";
				}
			}
			
			
			}
			else
			{
				mes="没有指定一种奖励方式,添加失败";
				return "fail";
			}
			
			
		}
		
		if(action.equals("update"))
		{
			RecommendRule updateRR=recommendRuleBo.getRecommendRuleById(updateId);
			RecommendRule oldRR=recommendRuleBo.getRecommendRuleById(updateId);
			if(updateRR!=null)
			{
				long id=updateRR.getId();
				updateRR=new RecommendRule(null, null, null, whenToAward, ruleName, status, "双向");
				updateRR.setId(id);
				if(awardMethod!=null)
				{
				if(awardMethod.trim().equals("现金"))
				{
					updateRR.setAmount(Double.valueOf(awardValue).doubleValue());
				}
				
				if(awardMethod.trim().equals("积分"))
				{
					updateRR.setPoints(Long.valueOf(awardValue).longValue());
				}
				
				if(awardMethod.trim().equals("优惠券"))
				{
					updateRR.setCoupon(Long.valueOf(awardValue).longValue());
				}
				baseBo.updateRecommendRule(updateRR);
				List<Object[]> countList=recommendRuleBo.getcountList();
				if(countList==null)
				{
					mes="成功";
					return "success";
				}
				else
				{
					boolean flag=true;
					for(Object[] a:countList)
					{
						long amountCount=Long.valueOf(String.valueOf(a[1]));
						long pointsCount=Long.valueOf(String.valueOf(a[2]));
						long couponCount=Long.valueOf(String.valueOf(a[3]));
						if(amountCount>1||pointsCount>1||couponCount>1)
						{
							flag=false;
							break;
						}
						
					}
					if(flag)
					{
						mes="成功";
						return "success";
					}
					else
					{
						baseBo.updateRecommendRule(oldRR);
						mes="每一种推荐类型的三种奖励方式只能存在一次,你已经定义了该类型的推荐规则,你可以修改原来该规则状态为N，继续添加此类规则";
						return "fail";
					}
				}
				
				
				}
			
				else
				{
					mes="没有指定一种奖励方式,更改失败";
					return "fail";
				}
			}
			else
			{
				mes="没有找到此规则";
				return "fail";
			}
		}
		
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public List getAllRecommendRule() {
		return allRecommendRule;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setAwardMethod(String awardMethod) {
		this.awardMethod = awardMethod;
	}

	public void setAwardValue(String awardValue) {
		this.awardValue = awardValue;
	}

	public void setWhenToAward(String whenToAward) {
		this.whenToAward = whenToAward;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public void setAwardDrection(String awardDrection) {
		this.awardDrection = awardDrection;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}

	public List getAllUsefulCoupon() {
		return allUsefulCoupon;
	}
}
