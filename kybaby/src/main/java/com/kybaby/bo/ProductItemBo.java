package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.ProductItem;

/**
 * 
 * @ClassName:ProductItemBo
 * @Description:服务产品包含的项目的事物管理接口
 * @author Hoolee
 * @date 2015年9月14日下午11:28:24
 */
public interface ProductItemBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某个服务产品服务产品包含的项目实例列表
	 * @data: 2015年9月14日下午11:29:32
	 * @param productId 服务产品的ID
	 * @return 服务产品包含的项目实例列表
	 */
	List<ProductItem> getSomeProductItemsListById(long productId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某个项目的实例
	 * @data: 2015年9月17日上午11:27:50
	 * @param itemNmae 项目的名称
	 * @return 项目的实例
	 */
	ProductItem getProductItemInstanceByName(String itemNmae);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过项目的ID获取到项目的名称
	 * @data: 2015年9月22日下午2:25:57
	 * @param itemId 项目的ID
	 * @return 项目的名称
	 */
	String getItemNameById(long itemId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据服务产品中包含的项目ID字符串，获取到该字符串中包含的项目的名称列表 
	 * @data: 2015年10月5日下午1:35:16
	 * @param itemIds 服务产品中包含的项目ID组合成的字符串
	 * @return 字符串中包含的项目的ID列表
	 */
	List<String> getSomeProductItems(String itemIds);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据服务产品中包含的项目ID字符串，获取到该字符串包含的项目的显示名称列表
	 * @data: 2015年10月29日下午9:46:03
	 * @param itemIds 服务产品中包含的项目的ID列表
	 * @return 产品中包含的项目的显示名称列表
	 */
	List<String> getSomeProductItemShowNameList(String itemIds);
}
