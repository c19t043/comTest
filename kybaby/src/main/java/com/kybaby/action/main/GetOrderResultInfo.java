package com.kybaby.action.main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.BabyBasicData;
import com.kybaby.domain.HealthPlan;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.UserInfo;
import com.kybaby.domain.BabyBasicData2;
import com.kybaby.util.CalculationMethod;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GetOrderResultInfo
 * @Description:健康档案相关的
 * @author Hoolee
 * @date 2015年10月13日下午3:26:19
 */
public class GetOrderResultInfo extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private String userImage;
	private String babyName;
	private String babySex;
	private String babyBirthday;
	private String babyMonthYear;
	
	private BabyBasicData basicData;
	/**
	 * 健康档案信息
	 */
	private BabyBasicData2 babyBasicData;
	private List<String> dateStrList=new ArrayList<String>();//记录日期列表
	private List<String> productNameList=new ArrayList<String>();//商品的名称列表
	private List<List<String>> prodcutItemNameList=new ArrayList<List<String>>();//产品项目名称的列表
	private List<List<String>> productItemResultList=new ArrayList<List<String>>();//产品项目结果的列表
	private List<List<String>> healthPlanNameList=new ArrayList<List<String>>();//健康计划名称列表
	private List<List<List<String>>> healthPathNameList=new ArrayList<List<List<String>>>();//健康计划路径的名称列表
	private List<List<List<String>>> healthPathAmountList=new ArrayList<List<List<String>>>();//健康计划路径执行次数的列表
	
	public String execute() throws ParseException{
		if(action.equals("healthArchive")){
			System.out.println("HealthArchive is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				UserInfo usr=userInfoBo.getUserById(userId);
				if(usr!=null){
					userImage=usr.getUserImage();
					babyName=usr.getBabyName();
					babySex=usr.getSex();
					babyBirthday=usr.getBirthday();
					String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
					babyMonthYear=String.valueOf(CalculationMethod.getMonthSpace(babyBirthday, rightNow));
				}
				//第一部分user表中的信息
				//第二部分基础信息
				basicData=userInfoBo.getBabyBasicDataByUserId(userId);
				//第三部分基础信息,统计健康档案数据
				//首先获取到用户已经完成(订单状态为：“已确认”、“已评价”)的订单的订单编号、商品ID
				List<Object[]> orderNumAndProductId=orderInfoBo.getOrderNumAndProductId(userId);
				if(orderNumAndProductId!=null){
					for(Object[] object:orderNumAndProductId){
						String orderNum=String.valueOf(object[0]);
						long productId=Long.valueOf(String.valueOf(object[1]));
						String dateStr=orderResultsBo.getWridateByOrderNumUserIdAndProdcutId(orderNum, userId, productId);
						dateStrList.add(dateStr);
						String prodcutNmame=productBo.getProductNameById(productId);
						productNameList.add(prodcutNmame);
						List<Object[]> itemIdResultValueAndUnit=orderResultsBo.getItemIdAndResultValueAndUnit(orderNum, userId, productId);
						List<String> itemNameList=new ArrayList<String>();
						List<String> itemResultList=new ArrayList<String>();
						if(itemIdResultValueAndUnit!=null){
							for(Object[] objRe:itemIdResultValueAndUnit){
								long itemId=Long.valueOf(String.valueOf(objRe[0]));
								String itemResult=String.valueOf(objRe[1])+String.valueOf(objRe[2]);
								itemResultList.add(itemResult);
								String name=productItemBo.getItemNameById(itemId);
								itemNameList.add(name);
							}
						}
						prodcutItemNameList.add(itemNameList);
						
						//updated by zhong at 2015-11-02,变量名使用错误
						//prodcutItemNameList.add(itemResultList);
						productItemResultList.add(itemResultList);
						
						//健康计划路劲的ID planId
						List<String> planIdList=orderResultsBo.getHealthPlanId(orderNum, userId, productId);
						List<String> planNameList=new ArrayList<String>();
						if(planIdList!=null){
							List<List<String>> pathNameListAgain=new ArrayList<List<String>>();
							List<List<String>> pathAmountListAgain=new ArrayList<List<String>>();
							for(int i =0;i<planIdList.size();i++){
								String planIdStr=planIdList.get(i);
								if(!planIdStr.equals(" ")){
									long planId=Long.valueOf(planIdList.get(i));
									
									
									HealthPlan healPlanInstance=healthPlanBo.getHealthPlanById(planId);
									if(healPlanInstance!=null){
										String planName=healPlanInstance.getHealthPlan();
										planNameList.add(planName);
										String planPathIds=healPlanInstance.getHealthPathIds();
										String[] pathIdsList=planPathIds.split("::");
										List<String> pathNameList=new ArrayList<String>();
										List<String> pathAmountList=new ArrayList<String>();
										for(int j =0;j<pathIdsList.length;j++){
											long pathId=Long.valueOf(pathIdsList[j]);
											String pathName=healthPathBo.getPathNameById(pathId);
											long pathAmount=healthPlanRemindBo.getSomePathAmount(userId, orderNum, pathId, planId);
											pathNameList.add(pathName);
											pathAmountList.add(String.valueOf(pathAmount));
										}
										pathNameListAgain.add(pathNameList);
										pathAmountListAgain.add(pathAmountList);
									}
								}
							}
							healthPathNameList.add(pathNameListAgain);
							healthPathAmountList.add(pathAmountListAgain);
						}
						healthPlanNameList.add(planNameList);
					}
					mes="操作成功";
					return "success";
				}
				mes="无执行情况";
				return "fail";
			}
			mes="未登录";
			return "fail";
		}
		/**
		 * 得到宝宝基础档案信息
		 */
		else if(action.equals("getBabyBasicData")){
			long userId=(Long)ActionContext.getContext().getSession().get("userId");
			List<BabyBasicData2> babyList = this.userInfoBo.getBabyBasicData2ListByUserId(userId);
			if(babyList != null){
				babyBasicData = babyList.get(0);
			}
			mes="操作成功";
			return "success";
		}
		/**
		 * 保存或更新健康档案
		 */
		else if(action.equals("saveOrUpdateBabyBasicData")){
			long userId=(Long)ActionContext.getContext().getSession().get("userId");
			babyBasicData.setUserId(userId);
			//babyBasicData.setDoctorId(null);
			userInfoBo.saveOrUpdateBabyBasicData(babyBasicData);
			mes="操作成功";
			return "success";
		}
		return "fail";
	}

	public String getMes() {
		return mes;
	}

	public String getUserImage() {
		return userImage;
	}

	public String getBabyName() {
		return babyName;
	}

	public String getBabySex() {
		return babySex;
	}

	public String getBabyBirthday() {
		return babyBirthday;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getBabyMonthYear() {
		return babyMonthYear;
	}

	public BabyBasicData getBasicData() {
		return basicData;
	}

	public List<String> getDateStrList() {
		return dateStrList;
	}

	public List<String> getProductNameList() {
		return productNameList;
	}

	public List<List<String>> getProdcutItemNameList() {
		return prodcutItemNameList;
	}

	public List<List<String>> getProductItemResultList() {
		return productItemResultList;
	}

	public List<List<String>> getHealthPlanNameList() {
		return healthPlanNameList;
	}

	public List<List<List<String>>> getHealthPathNameList() {
		return healthPathNameList;
	}

	public List<List<List<String>>> getHealthPathAmountList() {
		return healthPathAmountList;
	}

	public BabyBasicData2 getBabyBasicData() {
		return babyBasicData;
	}

	public void setBabyBasicData(BabyBasicData2 babyBasicData) {
		this.babyBasicData = babyBasicData;
	}
}
