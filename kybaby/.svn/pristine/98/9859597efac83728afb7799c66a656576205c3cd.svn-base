package com.kybaby.action.main;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlan;
import com.kybaby.domain.HealthPlanRemind;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctoroperateflow.fo.HealthPlanFo;
import com.kybaby.util.CalculationMethod;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GetHealthPlanRemainInfo
 * @Description:健康计划相关的
 * @author Hoolee
 * @date 2015年10月14日上午10:18:52
 */
public class GetHealthPlanRemainInfo extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈到前端的提示信息
	private String todayMsg;//反馈到前端的提示是否有今日健康提醒

	private String doctorImage;
	private String doctorName;
	private String doctorTitle;
	private List<String> allDateList=new ArrayList<String>();//日期列表
	private List<List<HealthPlan>> allPlanNameList=new ArrayList<List<HealthPlan>>();//健康计划名称
	private List<List<List<HealthPath>>> allPathNameList=new ArrayList<List<List<HealthPath>>>();//健康计划路劲名称
	private List<List<List<String>>> allPathResultList=new ArrayList<List<List<String>>>();//健康计划路劲名称
	
	private String latestDateStr="";
	private List<List<HealthPlan>> futurePlanNameList=new ArrayList<List<HealthPlan>>();//健康计划名称
	private List<List<List<HealthPath>>> futurePathNameList=new ArrayList<List<List<HealthPath>>>();//健康计划路劲名称
	private List<List<List<String>>> futurePathResultList=new ArrayList<List<List<String>>>();//健康计划路劲名称
	/**
	 *健康指导记录
	 */
	private List<HealthPlanFo> healthPlanFoList = new ArrayList<HealthPlanFo>();
	/**
	 * 健康路径列表
	 */
	private List<HealthPath> healthPathList = new ArrayList<HealthPath>();
	/**
	 * 订单编号
	 */
	private String orderNum;
	
	public String execute() throws ParseException{
		if(action.equals("getAllHealthPlan")){
			System.out.println("GetAllHealthPlan is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				List<HealthPlanRemind> healthPlanRemind=new ArrayList<HealthPlanRemind>();
				String rightNow=CalculationMethod.rightNowDate().toString();
				healthPlanRemind=healthPlanRemindBo.todayHealthPlanRemind(userId, rightNow);
				todayMsg="不存在";
				if(healthPlanRemind!=null){
					todayMsg="存在";
				}
				//上面部分确定了是否存在今日健康提醒，所有的今日健康提醒按照日期进行逆序排序
				//在存在今日健康提醒的情况下，第一条就是今日健康提醒，否则就不存在今日健康提醒，并且今日健康提醒可以执行
				//OrderResult表中id最大的一条记录的orderNum
				String lastOrderNum=orderResultsBo.getLastOrderNumByUserId(userId);
				List<String> allPlanIdList=orderResultsBo.getSomeOrderPlanIdList(userId, lastOrderNum);
				allDateList=healthPlanRemindBo.getDateStr(userId, lastOrderNum);
				if(allPlanIdList!=null&&allDateList!=null){

					long doctorId=orderResultsBo.getDoctorIdByOrderNum(lastOrderNum, userId);
					DoctorInfo doctor=doctorInfoBo.getDoctorInfoByDoctorId(doctorId);
					doctorImage=doctor.getDoctorImage();
					doctorName=doctor.getDoctorName();
					doctorTitle=doctor.getDoctorTitle();
					//以上是返回到页面上的医生信息，包括医生的头像、姓名、职称

					java.util.Date dateNow=new Date(System.currentTimeMillis());
					
					/*
					 * add by HooLee
					 * time 2015年12月3日14:12:00
					 * 增加实现在不存在今日健康计划的时候显示即将执行的一条健康计划 
					 * */
					if(todayMsg.equals("不存在")){
						System.out.println("获取即将执行一条健康计划开始");
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
					for(int i =0;i<allDateList.size();i++){
						String someDate=allDateList.get(i);

						java.util.Date otherDate=Date.valueOf(someDate);
						if(otherDate.compareTo(dateNow)>0){
							allDateList.remove(i);
							i--;
							continue;
						}

						List<HealthPlan> planNameList=new ArrayList<HealthPlan>();
						List<List<HealthPath>> pathNameAg=new ArrayList<List<HealthPath>>();
						List<List<String>> pathResultAg=new ArrayList<List<String>>();
						for(int j =0;j<allPlanIdList.size();j++){
							List<HealthPath> pathNameList=new ArrayList<HealthPath>();
							List<String> pathResultList=new ArrayList<String>();

							//System.out.println(allPlanIdList.get(j).getClass().toString());
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

										String result=healthPlanRemindBo.getSomeDateResult(lastOrderNum, userId, planId, pathId, someDate);
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
						allPlanNameList.add(planNameList);
						allPathNameList.add(pathNameAg);
						allPathResultList.add(pathResultAg);
					}
					mes="操作成功";
					return "success";
				}
				mes="暂无记录";
				return "fail";
			}
			mes="未登录";
			return "fail";
		}
		/**
		 * 健康指导改造，只是查看健康指导
		 */
		else if(action.equals("getHealthPlanFoList")){
			System.out.println("getHealthPlanFoList is begining...");
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			if(userId==null){
				mes="未登录";
				return "fail";
			}
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			this.healthPlanFoList = this.healthPlanRemindBo.getHealthPlanFoList(userInfo);
			mes="操作成功";
			return "success";
		}
		/**
		 * 健康指导明细列表
		 */
		else if(action.equals("getHealthPathList")){
			System.out.println("getHealthPathList is begining...");
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			if(userId==null){
				mes="未登录";
				return "fail";
			}
			this.healthPathList = this.healthPlanRemindBo.getHealthPathListByOrderNum(orderNum);
			mes="操作成功";
			return "success";
		}
		return "fail";
	}

	public String getMes() {
		return mes;
	}

	public String getTodayMsg() {
		return todayMsg;
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

	/**
	 * @return the allDateList
	 */
	public List<String> getAllDateList() {
		return allDateList;
	}

	/**
	 * @return the allPathResultList
	 */
	public List<List<List<String>>> getAllPathResultList() {
		return allPathResultList;
	}

	/**
	 * @return the allPlanNameList
	 */
	public List<List<HealthPlan>> getAllPlanNameList() {
		return allPlanNameList;
	}

	/**
	 * @return the allPathNameList
	 */
	public List<List<List<HealthPath>>> getAllPathNameList() {
		return allPathNameList;
	}

	/**
	 * @return the latestDateStr
	 */
	public String getLatestDateStr() {
		return latestDateStr;
	}

	/**
	 * @return the futurePlanNameList
	 */
	public List<List<HealthPlan>> getFuturePlanNameList() {
		return futurePlanNameList;
	}

	/**
	 * @return the futurePathNameList
	 */
	public List<List<List<HealthPath>>> getFuturePathNameList() {
		return futurePathNameList;
	}

	/**
	 * @return the futurePathResultList
	 */
	public List<List<List<String>>> getFuturePathResultList() {
		return futurePathResultList;
	}

	public List<HealthPlanFo> getHealthPlanFoList() {
		return healthPlanFoList;
	}

	public List<HealthPath> getHealthPathList() {
		return healthPathList;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}
