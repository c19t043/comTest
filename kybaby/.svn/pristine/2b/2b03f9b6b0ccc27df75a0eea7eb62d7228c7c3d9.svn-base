package com.kybaby.action.main;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.ProductItem;

/**
 * @ClassName:GetProductItemInfo
 * @Description:服务项目相关的
 * @author Hoolee
 * @date 2015年10月7日上午11:34:28
 */
public class GetProductItemInfo extends BaseAction  {
	
	private String mes;//反馈到前端的提示信息
	private String itemName;//前端传递的项目名称
	private ProductItem someProductItem;//产品项目实例
	
	public String execute(){
		if(action.equals("itemInstance")){
			System.out.println("ItemInstance is begining...");
			someProductItem=productItemBo.getProductItemInstanceByName(itemName);
			mes="操作成功";
			return "success";
		}
		return "fail";
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @return the someProductItem
	 */
	public ProductItem getSomeProductItem() {
		return someProductItem;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
