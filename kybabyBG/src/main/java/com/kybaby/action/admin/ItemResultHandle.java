package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.ItemResult;
import com.kybaby.domain.ProductItem;

public class ItemResultHandle extends BaseAction {
 
	private String mes="";
	private String action="";
	
	private String name;        //项目结果名字
	private String resultUnit;  //结果单位
	private String resultType;  //结果类型
	private String options;     //结果选项
	private long updateId;      //修改结果选项的Id
	private long ItemId;        //项目的Id

	
	private String isNeedRemind; //是否需要提示
	private String maxValue;     //最大值
	private String maxValueRemind; //最大值提示
	private String minValue;      //最小值
	private String minValueRemind; //最小值提示
	
	/**
	 * 是否需要备注标示
	 */
	private String resultRemarkFlag;
	
	private List allItemResult=new ArrayList();  //所有项目结果的集合
	private List unUseItem=new ArrayList();      //没有绑定项目结果的项目id，name
	private List allUsedItem=new ArrayList();    //所有项目的id,name
	
	public String execute()
	{
		if(action.equals("all"))
		{
			System.out.println("itemResultHandle.action?action=all");
			allItemResult=itemResultBo.getAllItemResult();
			mes="成功";
			return "success";
		}
		
		if(action.equals("getUnuseItem"))
		{
			System.out.println("itemResultHandle.action?action=getUnuseItem");
			unUseItem=productItemBo.getIdAndNameOfProductItemOfUnused();
			mes="成功";
			return "success";
		}
		
		if(action.equals("getAllUsedItem"))
		{
			System.out.println("itemResultHandle.action?action=getAllUsedItem");
			allUsedItem=productItemBo.getIdAndNameOfProductItemOfAll();
			mes="成功";
			return "success";
		}
		
		if(action.equals("add"))
		{
			System.out.println("itemResultHandle.action?action=add");
			ItemResult iR=itemResultBo.getItemResultByName(name);
			if(iR==null)	
			{
				ItemResult addIR=new ItemResult();
				addIR.setItemResultNum("");
				addIR.setItemResultName(name);
				addIR.setResultUnit(resultUnit);
				addIR.setResultType(resultType);
				addIR.setIsNeedRemind(isNeedRemind);
				addIR.setOptions("");
				addIR.setMaxValue("");
				addIR.setMaxValueRemind("");
				addIR.setMinValue("");
				addIR.setMinValueRemind("");
				addIR.setResultRemarkFlag(resultRemarkFlag);
				
				
				if(isNeedRemind.equals("Y"))
				{
					if(resultType.equals("输入"))
					{
						addIR.setOptions("");
						addIR.setMaxValue(maxValue);
						addIR.setMaxValueRemind(maxValueRemind);
						addIR.setMinValue(minValue);
						addIR.setMinValueRemind(minValueRemind);
						
					}
					if(resultType.equals("选择"))
					{
						addIR.setOptions("是,否");
						addIR.setMaxValue("是");
						addIR.setMaxValueRemind(maxValueRemind);
						addIR.setMinValue("否");
						addIR.setMinValueRemind(minValueRemind);
					}
				}
				
				if(isNeedRemind.equals("N"))
				{
					if(resultType.equals("选择"))
					{
						if(options.trim().contains(","))
						{
					        addIR.setOptions(options);
						}
						else
						{
							mes="选择项输入错误";
							return "fail";
						}
					}
				}
				
				baseBo.saveItemResult(addIR);
				ItemResult now=itemResultBo.getItemResultByName(name);
				if(now!=null)
				{
					ProductItem nowPI=productItemBo.getProductItemById(ItemId);
					if(nowPI!=null)
					{
						nowPI.setItemResultId(now.getId());
						baseBo.updateProductItem(nowPI);
						mes="成功";
						return "success";
					}
					else
					{
						mes="没有找到该项目";
						return "fail";
					}
				}
				else
				{
					mes="保存项目结果失败";
					return "fail";
				}
				
			}
			else
			{
				mes="已定义该项目结果";
				return "fail";
			}
		}
		
		if(action.equals("update"))
		{
			System.out.println("itemResultHandle.action?action=update");
			ItemResult iR=itemResultBo.getItemResultByName(name);
			ItemResult updateIR=itemResultBo.getItemResultById(updateId);
			if(iR==null||updateIR.getItemResultName().equals(name))
			{
				updateIR.setItemResultNum("");
				updateIR.setItemResultName(name);
				updateIR.setResultUnit(resultUnit);
				updateIR.setResultType(resultType);
				updateIR.setIsNeedRemind(isNeedRemind);
				updateIR.setOptions("");
				updateIR.setMaxValue("");
				updateIR.setMaxValueRemind("");
				updateIR.setMinValue("");
				updateIR.setMinValueRemind("");
				updateIR.setResultRemarkFlag(resultRemarkFlag);
				
				
				
				if(isNeedRemind.equals("Y"))
				{
					if(resultType.equals("输入"))
					{
						updateIR.setOptions("");
						updateIR.setMaxValue(maxValue);
						updateIR.setMaxValueRemind(maxValueRemind);
						updateIR.setMinValue(minValue);
						updateIR.setMinValueRemind(minValueRemind);
						
					}
					if(resultType.equals("选择"))
					{
						updateIR.setOptions("是,否");
						updateIR.setMaxValue("是");
						updateIR.setMaxValueRemind(maxValueRemind);
						updateIR.setMinValue("否");
						updateIR.setMinValueRemind(minValueRemind);
					}
				}
				
				if(isNeedRemind.equals("N"))
				{
					if(resultType.equals("选择"))
					{
						if(options.trim().contains(","))
						{
					        updateIR.setOptions(options);
						}
						else
						{
							mes="选择项输入错误";
							return "fail";
						}
					}
				}
				baseBo.updateItemResult(updateIR);
				ProductItem wantedBoundItem=productItemBo.getProductItemById(ItemId);
				if(wantedBoundItem!=null)
				{
					ProductItem  boundThisResultProductItem=productItemBo.getProductItemByItemResultId(updateId);
					if(boundThisResultProductItem!=null)
					{
						boundThisResultProductItem.setItemResultId(null);
						baseBo.updateProductItem(boundThisResultProductItem);
						wantedBoundItem.setItemResultId(updateIR.getId());
						baseBo.updateProductItem(wantedBoundItem);
						mes="成功";
						return "success";
					}
					else
					{
						wantedBoundItem.setItemResultId(updateIR.getId());
						baseBo.updateProductItem(wantedBoundItem);
						mes="成功";
						return "success";
					}
				}
				else
				{
					mes="没有找到该要绑定的项目";
					return "fail";
				}
				
				
				
			}
			else
			{
				mes="项目结果名字重复";
				return "fail";
			}
		}
		
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public List getAllItemResult() {
		return allItemResult;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}

	public List getUnUseItem() {
		return unUseItem;
	}

	public void setIsNeedRemind(String isNeedRemind) {
		this.isNeedRemind = isNeedRemind;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public void setMaxValueRemind(String maxValueRemind) {
		this.maxValueRemind = maxValueRemind;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public void setMinValueRemind(String minValueRemind) {
		this.minValueRemind = minValueRemind;
	}

	public List getAllUsedItem() {
		return allUsedItem;
	}

	public void setItemId(long itemId) {
		ItemId = itemId;
	}

	public void setResultRemarkFlag(String resultRemarkFlag) {
		this.resultRemarkFlag = resultRemarkFlag;
	}
}
