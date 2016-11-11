package com.kybaby.bo;

import java.util.List;

import com.kybaby.domain.Product;

/**
 * @ClassName:ProductBo
 * @Description:商品事物管理接口
 * @author Hoolee
 * @date 2015年9月14日下午10:55:29
 */
public interface ProductBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取按照用户年龄和性别“量身定制产品”的列表
	 * @data: 2015年9月14日下午10:56:44
	 * @param whatFitForSex 用户年龄
	 * @param whatFitForMonth 用户性别
	 * @return 按照用户年龄和性别“量身定制产品”的列表
	 */
	List<Product> specialProductList(String whatFitForSex,String whatFitForMonth);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取按照用户的年龄匹配的“量身定制产品”的列表
	 * @data: 2015年10月31日下午2:36:50
	 * @param whatFitForMonth 用户年龄
	 * @return 按照用户的年龄匹配的“量身定制产品”的列表
	 */
	List<Product> newSpecialProductList(String whatFitForMonth);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription: 返回未登录用户特色服务产品列表
	 * @data: 2015年9月14日下午10:58:33
	 * @param isFeatures 是否特色产品
	 * @return 未登录用户特色服务产品列表
	 */
	List<Product> featureProductList(String isFeatures,String productName);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取按照用户年龄、性别和商品分类（基础产品、增值产品）匹配的产品列表
	 * @data: 2015年9月14日下午11:05:44
	 * @param whatFitForSex 用户性别
	 * @param whatFitForMonth 用户年龄
	 * @param productCategory 商品分类
	 * @return 按照用户年龄、性别和商品分类（基础产品、增值产品）匹配的产品列表
	 */
	List<Product> someBabySpecialProductList(String whatFitForSex,String whatFitForMonth,String productCategory);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:按照用户的年龄和商品分类（基础产品、增值产品）匹配的产品列表
	 * @data: 2015年10月31日下午2:54:19
	 * @param whatFitForMonth 宝宝的年龄
	 * @param productCategory 商品分类
	 * @return 按照用户的年龄和产品分类（基础产品、增值产品）匹配的产品列表
	 */
	List<Product> newSomeBabySpecialProductList(String whatFitForMonth,String productCategory,String productName);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取未登录用户的增值服务产品列表
	 * @data: 2015年9月14日下午11:07:57
	 * @param productCategory 商品的分类
	 * @return 未登录用户的增值服务产品列表
	 */
	List<Product> someCategoryProductList(String productCategory,String productName);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据产品服务的名字获取产品实例
	 * @data: 2015年9月14日下午11:08:53
	 * @param productName 产品服务的名字
	 * @return 产品实例
	 */
	Product getProductByName(String productName);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取某医生提供的服务产品列表
	 * @data: 2015年9月14日下午11:19:30
	 * @param doctorId 医生的ID
	 * @return 某医生提供的服务产品列表
	 */
	List<Product> getSomeDoctorProdcutListByDoctorId(long doctorId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:更加服务产品的ID获取服务产品实例
	 * @data: 2015年9月17日上午10:55:01
	 * @param productId 服务产品的ID
	 * @return 服务产品实例
	 */
	Product getProductById(long productId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过产品的ID获取到产品的名称
	 * @data: 2015年9月22日下午2:16:56
	 * @param productId 产品的ID
	 * @return 产品的名称
	 */
	String getProductNameById(long productId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过医生提供的产品服务的ID字符串获取到医生的服务产品列表
	 * @data: 2015年10月6日下午4:06:10
	 * @param productIds 医生提供的服务产品ID字符串
	 * @return 医生的服务产品列表
	 */
	List<Product> getSomeDoctorServiceProdcutList(String productIds);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过商品的ID获取到商品的服务时长
	 * @data: 2015年10月8日17:24:51
	 * @param productId 商品的ID
	 * @return 商品的服务时长
	 */
	Long getProductServiceTimeById(long productId);
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:根据商品的ID获取商品的价格
	 * @data: 2015年10月9日上午10:26:09
	 * @param productId 商品的ID
	 * @return 商品的价格
	 */
	double getProductPriceById(long productId);
	/**
	 * 得到产品列表
	 * @param product
	 * @return
	 */
	List<Product> getProdcutList(Product product);
}
