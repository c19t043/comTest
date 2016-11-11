package com.kybaby.action.main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlan;
import com.kybaby.domain.HealthPlanRemind;
import com.kybaby.domain.Product;
import com.kybaby.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.ConstantManage;

/**
 * @ClassName:GetProductInfo
 * @Description:商品相关的
 * @author Hoolee
 * @date 2015年9月29日下午1:46:22
 */
public class GetProductInfo extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String mes;//反馈给页面的提示信息
	private List<Product> specialProductList=new ArrayList<Product>();//登录用户按照年龄和性别“量身定制产 品”的列表
	private List<Product> featureProductList=new ArrayList<Product>();//未登录用户特色服务产品列表
	private List<HealthPlanRemind> todayHealthPlanRemind=new ArrayList<HealthPlanRemind>();//今日健康提醒列表
	private String healthMethodStr;
	private List<DoctorInfo> doctorList=new ArrayList<DoctorInfo>();
	private List<List<HealthPlan>> healthPlanList=new ArrayList<List<HealthPlan>>();
	private List<List<List<HealthPath>>> healthPathList=new ArrayList<List<List<HealthPath>>>();
	private List<List<List<String>>> healthPathResultList=new ArrayList<List<List<String>>>();
	private long userId;
	private List<String> orderNumberList=new ArrayList<String>();

	private String doctorImage;
	private String doctorName;
	private String doctorTitle;
	private String latestDateStr="";
	private List<List<HealthPlan>> futurePlanNameList=new ArrayList<List<HealthPlan>>();//健康计划名称
	private List<List<List<HealthPath>>> futurePathNameList=new ArrayList<List<List<HealthPath>>>();//健康计划路劲名称
	private List<List<List<String>>> futurePathResultList=new ArrayList<List<List<String>>>();//健康计划路劲名称

	private List<Product> basicSpecialProductList=new ArrayList<Product>();//基础服务产品列表
	private List<Product> extraSpecialProductList=new ArrayList<Product>();//增值服务产品列表
	private List<Product> characterProductList=new ArrayList<Product>();//特色服务产品列表
	/**
	 * 会员产品列表
	 */
	private List<Product> memberProductList=new ArrayList<Product>();

	//added by zhong at 2015-10-06
	private List<Product> extraProductList=new ArrayList<Product>();//不针对特定宝宝的增值服务产品列表

	private String babyName;//登录用户的宝宝姓名

	private String productName;//商品的名称
	private Product someProduct;//反馈到前端的商品实例
	private List<String> productItemNameList=new ArrayList<String>();//反馈到前端的商品实例中包含的项目名称列表
	private List<String> productItemShowNameList=new ArrayList<String>();//反馈到前端的项目的显示名称列表

	public String execute() throws ParseException{
		if(action.equals("getProductSep")){
			System.out.println("GetProductSep is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){//用户登录
				userId=(Long)ActionContext.getContext().getSession().get("userId");
				UserInfo user=userInfoBo.getUserById(userId);
				if(user!=null){
					String babySex=user.getSex();
					String babyBirthday=user.getBirthday();
					String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
					String babyMonthYear=String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
					//登录用户按照年龄和性别“量身定制产 品”的列表
					//specialProductList=productBo.specialProductList(babySex, babyMonthYear);
					/*
					 * 根据客户要求，量身定制产品只需要宝宝的年龄进行匹配
					 * update by HooLee
					 * 2015年10月31日14:34:41
					 * */
					specialProductList=productBo.newSpecialProductList(babyMonthYear);
					List<Product> newList=productBo.someCategoryProductList("增值产品",productName);
					if(specialProductList==null){
						specialProductList=newList;
					}else{
						specialProductList.addAll(newList);
					}

					//下面代码用户获取用户的今日健康提醒
					todayHealthPlanRemind= healthPlanRemindBo.todayHealthPlanRemind(userId, rightNow);
					if(todayHealthPlanRemind!=null){
						//取得了今日健康提醒列表之后
						List<String> orderNumList=new ArrayList<String>();
						String orderNum="";
						for(int i=0;i<todayHealthPlanRemind.size();i++){
							String someOrderNum=todayHealthPlanRemind.get(i).getOrderNum();
							if(someOrderNum.equals(orderNum)){
								continue;
							}else{
								orderNumList.add(someOrderNum);
								orderNum=someOrderNum;
							}
						}
						//上面部分确定了不重复的订单编号
						for(int i =0;i<orderNumList.size();i++){
							String newOrderNum=orderNumList.get(i);
							long doctorId=orderInfoBo.getDoctorIdByOrderNum(newOrderNum);
							if(doctorId!=0){
								DoctorInfo someDoctor=doctorInfoBo.getDoctorInfoByDoctorId(doctorId);
								doctorList.add(someDoctor);
							}

							//List<Long> planaIdList=healthPlanRemindBo.getSomeOrderNumPlanIdList(newOrderNum, userId);
							/*
							 * update by HooLee
							 * time 2015年10月24日23:16:00
							 * 首页只是需要当天的健康计划，不需要其他时候的健康计划
							 * */
							List<Long> planaIdList=healthPlanRemindBo.getTodaySomeOrderNumPlanIdList(newOrderNum, userId, rightNow);
							List<HealthPlan> someHealthPlan=new ArrayList<HealthPlan>();
							List<List<HealthPath>> someHealthpath=new ArrayList<List<HealthPath>>();
							List<List<String>> somePathResult=new ArrayList<List<String>>();

							if(planaIdList!=null){
								for(int k=0;k<planaIdList.size();k++){
									long healthPlanId=planaIdList.get(k);
									HealthPlan healthPlan=healthPlanBo.getHealthPlanById(healthPlanId);
									someHealthPlan.add(healthPlan);
									//健康计划中健康计划路劲ID
									List<Long> pathIdList=healthPlanRemindBo.getSomeHealthPlanPathIdList(newOrderNum, userId, healthPlanId);
									List<HealthPath> healPath=new ArrayList<HealthPath>();
									List<String> healthPathResult=new ArrayList<String>();
									if(pathIdList!=null){
										for(int j=0;j<pathIdList.size();j++){
											long pathId=pathIdList.get(j);
											HealthPath path=healthPathBo.getHealthPathById(pathId);
											healPath.add(path);

											//updated by zhong at 2015-10-25
											//String result=healthPlanRemindBo.getSomeDateResult(orderNum, userId, healthPlanId, pathId, rightNow);
											String result=healthPlanRemindBo.getSomeDateResult(newOrderNum, userId, healthPlanId, pathId, rightNow);

											healthPathResult.add(result);
										}
									}
									someHealthpath.add(healPath);
									somePathResult.add(healthPathResult);
								}
								healthPlanList.add(someHealthPlan);
								healthPathList.add(someHealthpath);
								healthPathResultList.add(somePathResult);
								orderNumberList.add(newOrderNum);
							}
						}
						mes="操作成功";
						return "success";
					}
					//没有今日健康提醒的用户就给用户返回健康管理方法图片地址
					healthMethodStr=propertiesBo.getHealthMethodStr("healthManageMethod");
					/*
					 * update by HooLee
					 * time 2015年12月4日11:09:59
					 * 如果存在即将执行的健康计划的情况下，就在首页的健康计划区域显示出来
					 * */
					List<String> allDateList=new ArrayList<String>();
					String lastOrderNum=orderResultsBo.getLastOrderNumByUserId(userId);
					allDateList=healthPlanRemindBo.getDateStr(userId, lastOrderNum);
					List<String> allPlanIdList=orderResultsBo.getSomeOrderPlanIdList(userId, lastOrderNum);

					if(allPlanIdList!=null&&allDateList!=null){
						
						long doctorId=orderResultsBo.getDoctorIdByOrderNum(lastOrderNum, userId);
						DoctorInfo doctor=doctorInfoBo.getDoctorInfoByDoctorId(doctorId);
						doctorImage=doctor.getDoctorImage();
						doctorName=doctor.getDoctorName();
						doctorTitle=doctor.getDoctorTitle();
						
						//首先获取距离今天最近的一个日期
						latestDateStr=CalculationMethod.getLatestDateStr(allDateList);
						if(!latestDateStr.equals("")){
							List<HealthPlan> planNameList=new ArrayList<HealthPlan>();
							List<List<HealthPath>> pathNameAg=new ArrayList<List<HealthPath>>();
							List<List<String>> pathResultAg=new ArrayList<List<String>>();
							for(int j =0;j<allPlanIdList.size();j++){
								List<HealthPath> pathNameList=new ArrayList<HealthPath>();
								List<String> pathResultList=new ArrayList<String>();
								if((!(allPlanIdList.get(j).equals("  ")))&&(!(allPlanIdList.get(j).equals(" ")))){
									long planId=Long.valueOf(allPlanIdList.get(j));
									HealthPlan plan=healthPlanBo.getHealthPlanById(planId);
									planNameList.add(plan);//计划名称列表
									List<Long> pathIds=healthPlanBo.getSomePlanPathIdList(planId);
									if(pathIds!=null){
										for(int k =0;k<pathIds.size();k++){
											long pathId=pathIds.get(k);
											HealthPath path= healthPathBo.getHealthPathById(pathId);
											pathNameList.add(path);
											String result=healthPlanRemindBo.getSomeDateResult(lastOrderNum, userId, planId, pathId, latestDateStr);
											if(result==null){
												result="X";
											}
											pathResultList.add(result);
										}
									}
									pathNameAg.add(pathNameList);
									pathResultAg.add(pathResultList);
								}
							}
							futurePlanNameList.add(planNameList);
							futurePathNameList.add(pathNameAg);
							futurePathResultList.add(pathResultAg);
						}
						System.out.println("获取即将执行一条健康计划完成");
					}
				}
			}else{
				//用户未登录,返回特色服务产品列表
				featureProductList=productBo.featureProductList("Y",productName);
				//健康管理方法图片地址
				healthMethodStr=propertiesBo.getHealthMethodStr("healthManageMethod");
			}
			mes="操作成功";
			return "success";
		} else if(action.equals("getProductInfo")){
			System.out.println("GetProductInfo is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){//登录用户，获取用户名、     “基础服务产品”和“增值服务产品”
				userId=(Long)ActionContext.getContext().getSession().get("userId");
				UserInfo user=userInfoBo.getUserById(userId);
				babyName=user.getBabyName();
				String babySex=user.getSex();
				String babyBirthday=user.getBirthday();
				String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
				String babyMonthYear=String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
				System.out.println("babyMonthYear:"+babyMonthYear);
				/*basicSpecialProductList=productBo.someBabySpecialProductList(babySex, babyMonthYear, "基础产品");

				extraSpecialProductList=productBo.someBabySpecialProductList(babySex, babyMonthYear, "增值产品");*/
				/*
				 * 应客户需求，登录用户战士的基础产品只需要匹配年龄，增值产品则不需要区分年龄和性别
				 * update by HooLee 
				 * 2015年10月31日14:50:19
				 * 以前的产品都是健康产品
				 * */
				extraSpecialProductList=productBo.someCategoryProductList("增值产品", productName);
				basicSpecialProductList=productBo.newSomeBabySpecialProductList(null, "基础产品",productName);
				//会员产品列表
				Product pro = new Product();
				pro.setProductType(ConstantManage.PRODUCT_TYPE_MEMBER);
				pro.setName(productName);
				this.memberProductList = productBo.getProdcutList(pro);
				if(basicSpecialProductList!=null&&extraSpecialProductList!=null){
					mes="操作成功";
					return "success";
				}else if(basicSpecialProductList==null&&extraSpecialProductList==null){
					characterProductList=productBo.featureProductList("Y", productName);

					//updated by zhong at 2015-10-06:区别成不同的列表,便于前端处理
					//extraSpecialProductList=productBo.someCategoryProductList("增值产品");
					extraProductList=productBo.someCategoryProductList("增值产品",productName);

					if(characterProductList!=null&&extraProductList!=null){
						mes="操作成功";
						return "success";
					}else if(characterProductList==null&&extraProductList==null){
						mes="无服务";
						return "fail";
					}else if(characterProductList==null){
						mes="无特色服务";
						return "fail";
					}else if(extraProductList==null){
						mes="无增值服务";
						return "fail";
					}
				}else if(basicSpecialProductList==null){
					mes="无基础服务";
					return "fail";
				}else if(extraSpecialProductList==null){
					mes="无增值服务";
					return "fail";
				}
			}else{//未登录用户
				characterProductList=productBo.featureProductList("Y",productName);
				extraSpecialProductList=productBo.someCategoryProductList("增值产品",productName);
				//会员产品列表
				Product pro = new Product();
				pro.setProductType(ConstantManage.PRODUCT_TYPE_MEMBER);
				this.memberProductList = productBo.getProdcutList(pro);
				if(characterProductList!=null&&extraSpecialProductList!=null){
					mes="操作成功";
					return "success";
				}else if(characterProductList==null&&extraSpecialProductList==null){
					mes="无服务";
					return "fail";
				}else if(characterProductList==null){
					mes="无特色服务";
					return "fail";
				}else if(extraSpecialProductList==null){
					mes="无增值服务";
					return "fail";
				}
			}
		} else if(action.equals("someProductDetail")){
			System.out.println("SomeProductDetail is beginging...");
			someProduct=productBo.getProductByName(productName);
			if(someProduct!=null){
				String itemIds=someProduct.getItemIds();
				productItemNameList=productItemBo.getSomeProductItems(itemIds);
				productItemShowNameList=productItemBo.getSomeProductItemShowNameList(itemIds);
				mes="操作成功";
				return "success";
			}
			mes="下架";
			return "fail";
		}
		return "fail";
	}

	public String getMes() {
		return mes;
	}

	public List<Product> getSpecialProductList() {
		return specialProductList;
	}

	public List<Product> getFeatureProductList() {
		return featureProductList;
	}

	public List<HealthPlanRemind> getTodayHealthPlanRemind() {
		return todayHealthPlanRemind;
	}

	public String getHealthMethodStr() {
		return healthMethodStr;
	}

	public List<DoctorInfo> getDoctorList() {
		return doctorList;
	}

	public List<List<HealthPlan>> getHealthPlanList() {
		return healthPlanList;
	}

	public List<List<List<String>>> getHealthPathResultList() {
		return healthPathResultList;
	}

	public List<List<List<HealthPath>>> getHealthPathList() {
		return healthPathList;
	}

	public long getUserId() {
		return userId;
	}

	public List<Product> getBasicSpecialProductList() {
		return basicSpecialProductList;
	}

	public List<Product> getExtraSpecialProductList() {
		return extraSpecialProductList;
	}

	public List<Product> getCharacterProductList() {
		return characterProductList;
	}

	public String getBabyName() {
		return babyName;
	}

	public Product getSomeProduct() {
		return someProduct;
	}

	public List<String> getProductItemNameList() {
		return productItemNameList;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Product> getExtraProductList() {
		return extraProductList;
	}

	public void setExtraProductList(List<Product> extraProductList) {
		this.extraProductList = extraProductList;
	}

	public List<String> getProductItemShowNameList() {
		return productItemShowNameList;
	}
	public List<String> getOrderNumberList() {
		return orderNumberList;
	}

	public void setOrderNumberList(List<String> orderNumberList) {
		this.orderNumberList = orderNumberList;
	}

	public String getLatestDateStr() {
		return latestDateStr;
	}

	public List<List<HealthPlan>> getFuturePlanNameList() {
		return futurePlanNameList;
	}

	public List<List<List<HealthPath>>> getFuturePathNameList() {
		return futurePathNameList;
	}

	public List<List<List<String>>> getFuturePathResultList() {
		return futurePathResultList;
	}

	/**
	 * @return the doctorImage
	 */
	public String getDoctorImage() {
		return doctorImage;
	}

	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * @return the doctorTitle
	 */
	public String getDoctorTitle() {
		return doctorTitle;
	}

	public List<Product> getMemberProductList() {
		return memberProductList;
	}

	public void setMemberProductList(List<Product> memberProductList) {
		this.memberProductList = memberProductList;
	}

}
