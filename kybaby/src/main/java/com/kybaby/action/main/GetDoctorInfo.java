package com.kybaby.action.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorArticle;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Product;
import com.kybaby.domain.TimeInit;
import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName:GetDoctorInfo
 * @Description:医生信息相关的
 * @author Hoolee
 * @date 2015年10月6日上午9:59:05
 */
public class GetDoctorInfo extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String mes;//反馈给前端的提示信息
	private List<Object[]> doctorSomeInfoList=new ArrayList<Object[]>();//系统内医生实例的(头像、姓名、职称、服务星级综合、责任星级综合、准时星级综合、出诊次数)列表
	private List<Double> doctorDistanceList=new ArrayList<Double>();//医生与登录用户之间的距离列表
	private List<List<String>> doctorMajorList=new ArrayList<List<String>>();//医生的专长方向列表
	private long userId;//当前登录用户的ID

	private long doctorId;//医生的ID
	private DoctorInfo someDoctor;//反馈到前端的医生实例
	private List<Object[]> doctorAssessmentTagList=new ArrayList<Object[]>();//医生的社交标签以及评价的次数
	private List<Product> doctorProductList=new ArrayList<Product>();//医生服务的产品列表
	private List<DoctorArticle> doctorArticleList=new ArrayList<DoctorArticle>();//医生个人专栏列表
	private Double doctorDistance;//登录用户与医生之间的距离
	private List<String> someDoctorMajorList=new ArrayList<String>();//医生的专长方向名称列表

	private String serviceDate;//用户选择的预约服务日期
	private String serviceTime;//用户选择的预约服务时间
	private long prodcutId;//用户选择的预约服务产品
	private List<DoctorInfo> serviceDoctorList=new ArrayList<DoctorInfo>();//能够为当前登录用户在选择时间段提供该项目服务的医生列表

	private List<DoctorInfo> someProdcutServiceDoctorList=new ArrayList<DoctorInfo>();//能够为当前登录用户提供该服务项目的医生列表

	private List<String> serviceDateList=new ArrayList<String>();//医生能够提供该服务项目的日期列表
	private List<List<String>> serviceTimeList=new ArrayList<List<String>>();//医生能够提供该服务项目的时间列表

	private List<TimeInit> serviceTimeInitList=new ArrayList<TimeInit>();

	private String dateTor;//明天的日期

	//added by zhong at 2015-10-09:对医生列表排序. 
	//popular,level
	private String orderBy="";

	private String sortMethod;//咨询医生页面的排序方式
	private List<Object[]> consultationDoctorList=new ArrayList<Object[]>();//咨询医生页面反馈到前端的
	private List<String> someDoctorMsgList = new ArrayList<String>();

	public String execute(){
		if(action.equals("getDoctorList")){
			System.out.println("GetDoctorList is begining...");
			doctorSomeInfoList=doctorInfoBo.getDoctorSomeInfoList();
			if(ActionContext.getContext().getSession().get("userId")!=null){
				userId=(Long)ActionContext.getContext().getSession().get("userId");
			}
			if(doctorSomeInfoList!=null){
				for(Object[] object:doctorSomeInfoList){
					String majorIds=(String)object[1];
					List<String> majorNameList=majorBo.getMajorNameListByIdStr(majorIds);
					doctorMajorList.add(majorNameList);
					long doctorId=(Long)object[0];
					if(userId!=0){
						Double doctorDistance=doctorInfoBo.doctorDistanceList(doctorId, userId);
						doctorDistanceList.add(doctorDistance);
					}
				}
				//前面部分获取到医生信息、医生的专业方向、以及登录用户与医生之间的距离
				if(doctorDistanceList!=null){
					for(int i =0;i<doctorDistanceList.size();i++){
						Object[] doctorReplace=doctorSomeInfoList.get(i);
						Double distance=Double.valueOf(doctorReplace[9].toString());
						if(doctorDistanceList.get(i)!=null&&distance>=doctorDistanceList.get(i)){
							for(int j =i+1;j<doctorDistanceList.size();j++){
								doctorReplace=doctorSomeInfoList.get(i);
								distance=Double.valueOf((doctorSomeInfoList.get(j))[9].toString());
								if(doctorDistanceList.get(j)==null){
									doctorDistanceList.remove(j);
									doctorSomeInfoList.remove(j);
									doctorMajorList.remove(j);
									j--;
									continue;
								}else if((double)distance<(double)doctorDistanceList.get(j)){
									doctorDistanceList.remove(j);
									doctorSomeInfoList.remove(j);
									doctorMajorList.remove(j);
									j--;
									continue;
								}
								if(doctorDistanceList.get(i)>doctorDistanceList.get(j)){
									//距离调换
									double dictanceReplace=doctorDistanceList.get(i);
									doctorDistanceList.set(i, doctorDistanceList.get(j));
									doctorDistanceList.set(j, dictanceReplace);

									//医生调换

									doctorSomeInfoList.set(i, doctorSomeInfoList.get(j));
									doctorSomeInfoList.set(j, doctorReplace);

									//专业方向调换
									List<String> majorReplace=doctorMajorList.get(i);
									doctorMajorList.set(i, doctorMajorList.get(j));
									doctorMajorList.set(j, majorReplace);
								}
							}
						}else{
							doctorDistanceList.remove(i);
							doctorSomeInfoList.remove(i);
							doctorMajorList.remove(i);
							i--;
						}
					}
				}
				mes="操作成功";
				return "success";
			}
			mes="无医生";
			return "success";
		}
		//added by zhong at 2015-10-09:对医生列表按条件进行排序
		else if(action.equals("getOrderedDoctorList")){
			System.out.println("Get Ordered DoctorList is begining..."+orderBy);
			doctorSomeInfoList=doctorInfoBo.getDoctorSomeInfoList();
			if(ActionContext.getContext().getSession().get("userId")!=null){
				userId=(Long)ActionContext.getContext().getSession().get("userId");
			}

			//add by HooLee 2015年10月27日10:38:55 按照最受欢迎、专家级别进行排序的时候也是需要考虑到距离的问题，不然医生端的服务范围就无效了
			if(doctorSomeInfoList!=null){
				for(int i=0;i<doctorSomeInfoList.size();i++){
					Object[] object=doctorSomeInfoList.get(i);
					long doctorId=(Long)object[0];
					Double distance=Double.valueOf(object[9].toString());
					if(userId!=0){
						Double doctorDistance=doctorInfoBo.doctorDistanceList(doctorId, userId);
						if(doctorDistance!=null&&doctorDistance>=distance){
							doctorSomeInfoList.remove(i);
							i--;
						}else if(doctorDistance==null){
							doctorSomeInfoList.remove(i);
							i--;
						}
					}
				}
			}
			
			if(doctorSomeInfoList!=null){
				List<Long> useTimesList=new ArrayList<Long>();
				List<Long> positionLevelList=new ArrayList<Long>();
				Long doctorRank;
				Long useTime;

				if(orderBy.equals("popular")){
					for(Object[] object:doctorSomeInfoList){
						useTime=(Long)object[8];
						if(useTime==null){
							useTime=0L;
						}
						useTimesList.add(useTime);
					}
				}
				else if(orderBy.equals("level")){
					for(Object[] object:doctorSomeInfoList){
						if(object[4]!=null){
							doctorRank=doctorInfoBo.getDoctorRankByName((String)object[4]);
							if(doctorRank==null){
								doctorRank=0L;
							}
							positionLevelList.add(doctorRank);
						}
					}
				}

				//排序
				if(orderBy.equals("popular")){
					for(int i=0;i<useTimesList.size();i++){
						for(int j=i+1;j<useTimesList.size();j++){
							Long theUseTime=useTimesList.get(i);
							if(theUseTime<useTimesList.get(j)){
								//交换使用次数
								useTimesList.set(i, useTimesList.get(j));
								useTimesList.set(j, theUseTime);
								//交换医生

								Object[] doctorReplace=doctorSomeInfoList.get(i);
								doctorSomeInfoList.set(i, doctorSomeInfoList.get(j));
								doctorSomeInfoList.set(j, doctorReplace);	
							}							
						}
						//获取专业
						String majorIds=(String)doctorSomeInfoList.get(i)[1];
						List<String> majorNameList=majorBo.getMajorNameListByIdStr(majorIds);
						doctorMajorList.add(majorNameList);
						//获取距离
						long doctorId=(Long)doctorSomeInfoList.get(i)[0];
						if(userId!=0){
							Double doctorDistance=doctorInfoBo.doctorDistanceList(doctorId, userId);
							doctorDistanceList.add(doctorDistance);
						}
					}
				}

				else if(orderBy.equals("level")){
					for(int i=0;i<positionLevelList.size();i++){
						for(int j=i+1;j<positionLevelList.size();j++){
							if(positionLevelList.get(i)<positionLevelList.get(j)){
								//交换等级
								Long theLevel=positionLevelList.get(i);
								positionLevelList.set(i, positionLevelList.get(j));
								positionLevelList.set(j, theLevel);
								//交换医生
								Object[] doctorReplace=doctorSomeInfoList.get(i);
								doctorSomeInfoList.set(i, doctorSomeInfoList.get(j));
								doctorSomeInfoList.set(j, doctorReplace);
							}
						}
						//获取专业
						String majorIds=(String)doctorSomeInfoList.get(i)[1];
						List<String> majorNameList=majorBo.getMajorNameListByIdStr(majorIds);
						doctorMajorList.add(majorNameList);
						//获取距离
						long doctorId=(Long)doctorSomeInfoList.get(i)[0];
						if(userId!=0){
							Double doctorDistance=doctorInfoBo.doctorDistanceList(doctorId, userId);
							doctorDistanceList.add(doctorDistance);
						}
					}
				}
				mes="操作成功";
				return "success";
			}
			mes="无医生";
			return "success";
		}
		else if(action.equals("doctorDetail")){
			System.out.println("DoctorDetail is begining...");
			someDoctor=doctorInfoBo.getDoctorInfoByDoctorId(doctorId);
			String majorIds=someDoctor.getMajorId();
			someDoctorMajorList=majorBo.getMajorNameListByIdStr(majorIds);
			doctorAssessmentTagList=doctorAssessmentTagBo.getDoctorAssessmentTagByDoctorId(doctorId);
			String productIds=someDoctor.getProductIds();
			doctorProductList=productBo.getSomeDoctorServiceProdcutList(productIds);
			doctorArticleList=doctorArticleBo.getSomeDoctorArticleList(doctorId);
			if(ActionContext.getContext().getSession().get("userId")!=null){
				userId=(Long)ActionContext.getContext().getSession().get("userId");
				doctorDistance=doctorInfoBo.doctorDistanceList(doctorId, userId);
			}
			mes="操作成功";
			return "success";
		} else if(action.equals("getSomeDateDoctList")){
			System.out.println("GetSomeDateDoctList is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				serviceDoctorList=doctorInfoBo.getSomeDateServiceDoctorList(prodcutId, serviceDate, serviceTime, userId);
				if(serviceDoctorList!=null){
					for(int i =0;i<serviceDoctorList.size();i++){
						DoctorInfo someDoctor=serviceDoctorList.get(i);
						String majorIds=someDoctor.getMajorId();
						List<String> majorNameList=majorBo.getMajorNameListByIdStr(majorIds);
						doctorMajorList.add(majorNameList);
						long doctorId=someDoctor.getId();
						Double doctorDistance=doctorInfoBo.doctorDistanceList(doctorId, userId);
						doctorDistanceList.add(doctorDistance);
					}
					mes="操作成功";
					return "success";
				}
				mes="无医生";
				return "success";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("getSomeProdcutServiceDoctorList")){
			System.out.println("GetSomeProdcutServiceDoctorList is begining...");
			if(ActionContext.getContext().getSession().get("userId")!=null){
				long userId=(Long)ActionContext.getContext().getSession().get("userId");
				someProdcutServiceDoctorList=doctorInfoBo.getSomeProdcutServiceDoctorList(userId, prodcutId);
				if(someProdcutServiceDoctorList!=null){
					for(int i =0;i<someProdcutServiceDoctorList.size();i++){
						DoctorInfo someDoctor=someProdcutServiceDoctorList.get(i);
						String majorIds=someDoctor.getMajorId();
						List<String> majorNameList=majorBo.getMajorNameListByIdStr(majorIds);
						doctorMajorList.add(majorNameList);
						Double doctorDistance=doctorInfoBo.doctorDistanceList(doctorId, userId);
						doctorDistanceList.add(doctorDistance);
					}
					mes="操作成功";
					return "success";
				}
				mes="无医生";
				return "fail";
			}
			mes="未登录";
			return "fail";
		} else if(action.equals("getSomeDoctorServiceTimeList")){
			System.out.println("GetSomeDoctorServiceTimeList is begining...");
			serviceDateList=doctorProductBo.getServiceDate(doctorId, prodcutId);
			if(serviceDateList!=null){
				for(int i =0;i<serviceDateList.size();i++){
					String serviceDate=serviceDateList.get(i);
					List<String> serviceTimes=doctorProductBo.getServiceTime(doctorId, prodcutId, serviceDate);
					serviceTimeList.add(serviceTimes);
				}

				//明天的日期
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(new Date(System.currentTimeMillis()));
				//modify by xchao 用户端可以选择当天来服务
				//calendar.add(Calendar.DAY_OF_MONTH, 1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dateTor=sdf.format(calendar.getTime());

				mes="操作成功";
				return "success";
			}
			mes="无服务";
			return "fail";
		} else if(action.equals("getService")){
			serviceTimeInitList=doctorInfoBo.getTimeInitList();
			//serviceDateList
			//serviceTimeList
			serviceDateList=doctorProductBo.canBeUserDateList();
			if(serviceDateList!=null){
				for(int i =0;i<serviceDateList.size();i++){
					String serviceDate=serviceDateList.get(i);
					List<String> timeList=doctorProductBo.canBeUserServiceTimeList(serviceDate);
					serviceTimeList.add(timeList);
				}
				//明天的日期
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(new Date(System.currentTimeMillis()));
				//modify by xchao 用户端可以选择当天来服务
				//calendar.add(Calendar.DAY_OF_MONTH, 1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dateTor=sdf.format(calendar.getTime());
				mes="操作成功";
				return "success";
			}
			/*Calendar calendar=Calendar.getInstance();
			calendar.setTime(new Date(System.currentTimeMillis()));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dateTor=sdf.format(calendar.getTime());*/
			mes="无服务";
			return "fail";
		} else if (action.equals("getConsultationDoctorList")){
			consultationDoctorList=doctorInfoBo.getConsulationDoctoSomeInfo();
			//获取医生的专长方向名称列表
			if(consultationDoctorList!=null){
				for(int i =0;i<consultationDoctorList.size();i++){
					Object[] doctorI=consultationDoctorList.get(i);
					String majorIds=(String)doctorI[1];
					List<String> majorNameList=majorBo.getMajorNameListByIdStr(majorIds);
					doctorMajorList.add(majorNameList);
				}
				//存在可以咨询的医生，按照sortMethod进行排序
				if(sortMethod.equals("welcome")){
					for(int i =0;i<consultationDoctorList.size();i++){
						for(int j=i+1;j<consultationDoctorList.size();j++){
							Object[] doctorI=consultationDoctorList.get(i);
							Object[] doctorJ=consultationDoctorList.get(j);
							if((long)doctorI[4]<(long)doctorJ[4]){
								consultationDoctorList.set(i, doctorJ);
								consultationDoctorList.set(j, doctorI);

								//专长方向排序
								List<String> doctorMajor=doctorMajorList.get(i);
								doctorMajorList.set(i, doctorMajorList.get(j));
								doctorMajorList.set(j, doctorMajor);
							}
						}
					}
				} else if(sortMethod.equals("expert")){
					for(int i =0;i<consultationDoctorList.size();i++){
						for(int j=i+1;j<consultationDoctorList.size();j++){
							Object[] doctorI=consultationDoctorList.get(i);
							Object[] doctorJ=consultationDoctorList.get(j);
							if((long)doctorI[3]<(long)doctorJ[3]){
								consultationDoctorList.set(i, doctorJ);
								consultationDoctorList.set(j, doctorI);

								//专长方向排序
								List<String> doctorMajor=doctorMajorList.get(i);
								doctorMajorList.set(i, doctorMajorList.get(j));
								doctorMajorList.set(j, doctorMajor);
							}
						}
					}
				} else if(sortMethod.equals("isOnline")){
					for(int i =0;i<consultationDoctorList.size();i++){
						Object[] doctorI=consultationDoctorList.get(i);
						if(((String)doctorI[5]).equals("N")){
							consultationDoctorList.remove(i);
							doctorMajorList.remove(i);
						}
					}
				}

				mes="操作成功";
				return "success";
			}
			mes="无咨询医生";
			return "fail";
		}
		//add by sjt 我的医生
		else if(action.equals("doctorList")){
			if(ActionContext.getContext().getSession().get("userId")==null){
				mes="未登录";
				return "fail";
			}
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			List<Long> doctorIdCon = new ArrayList<Long>();
//			List<Long> doctorIdOrd = new ArrayList<Long>();
//			doctorIdCon = userConsultDoctorBo.getHistoryDoctorIdList(userId);
			List<Object[]> list=userConsultDoctorBo.getAllOrderNumDoctorList(userId);
			if(list!=null){
				for(int i =0;i<list.size();i++){
					Object[] object=list.get(i);
					Long someDoctorId=(Long)object[0];
					String bespokeDate=(String)object[1];
					int afterServiceTime=Integer.valueOf(object[2].toString());
					java.util.Date besDate=java.sql.Date.valueOf(bespokeDate);
					Calendar calendar=Calendar.getInstance();
					calendar.setTime(besDate);
					calendar.add(calendar.DATE,afterServiceTime);
					besDate=java.sql.Date.valueOf((new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime()));
					//上面获取预约服务的结束时间
					java.util.Date dateNow=new java.util.Date(System.currentTimeMillis());
					if(dateNow.compareTo(besDate)<0&&!(doctorIdCon.contains(someDoctorId))){//还在预约服务的时间里面
						doctorIdCon.add(someDoctorId);
					}
				}
				if(doctorIdCon!=null){
					for(int p=0;p<doctorIdCon.size();p++){
						DoctorInfo doctorInfo = doctorInfoBo.getDoctorInfoByDoctorId(doctorIdCon.get(p));
						if(doctorInfo!=null){
							String isInService = doctorInfoBo.checkIsInService(userId,doctorIdCon.get(p));
							String[] majorIds = doctorInfo.getMajorId().split("::");
							String MajorName = "";
							for(int q=0;q<majorIds.length;q++){
								MajorName += doctorInfoBo.getMajorNameByMajorId(Long.valueOf(majorIds[q]));
								MajorName += "::";
							}
							String someDoctorMsg = isInService+"::";
							someDoctorMsg += MajorName;
							someDoctorMsg += doctorInfo.getDoctorName();
							someDoctorMsg += "&"+doctorInfo.getDoctorImage();
							someDoctorMsg += "&"+doctorInfo.getDoctorTitle();
							//						someDoctorMsg += "&"+doctorInfo.getMajorId();
							someDoctorMsg += "&"+doctorInfo.getDutyStarLevel();
							someDoctorMsg += "&"+doctorInfo.getSeiviceStarLevel();
							someDoctorMsg += "&"+doctorInfo.getOnTimeStarLevel();
							someDoctorMsg += "&"+doctorInfo.getId();
							someDoctorMsg += "&"+doctorInfo.getIsLogin();
							someDoctorMsgList.add(someDoctorMsg);
						}
					}
				}
			}
			mes="成功";
			return "success";
			
			/*if(doctorIdCon!=null){
				Long doctId = doctorIdCon.get(0);
				for(int i=1;i<doctorIdCon.size();i++){
					if(doctId.equals(doctorIdCon.get(i))){
						doctorIdCon.remove(i);
						i--;
					}else{
						doctId=doctorIdCon.get(i);
					}
				}
				doctorIdOrd = doctorInfoBo.getDoctorIdByUserId(userId);
				if(doctorIdOrd!=null){
					doctId = doctorIdOrd.get(0);
					for(int j=1;j<doctorIdOrd.size();j++){
						if(doctId.equals(doctorIdOrd.get(j))){
							doctorIdOrd.remove(j);
							j--;
						}else{
							doctId = doctorIdOrd.get(j);
						}
					}
					for(int m=0;m<doctorIdCon.size();m++){
						for(int n=0;n<doctorIdOrd.size();n++){
							if(doctorIdCon.get(m).equals(doctorIdOrd.get(n))){
								doctorIdOrd.remove(n);
								n--;
							}
						}
					}
					if(doctorIdOrd!=null){
						for(int k=0;k<doctorIdOrd.size();k++){
							doctorIdCon.add(doctorIdOrd.get(k));
						}
						for(int p=0;p<doctorIdCon.size();p++){
							DoctorInfo doctorInfo = doctorInfoBo.getDoctorInfoByDoctorId(doctorIdCon.get(p));
							if(doctorInfo!=null){
								String isInService = doctorInfoBo.checkIsInService(userId,doctorIdCon.get(p));
								String[] majorIds = doctorInfo.getMajorId().split("::");
								String MajorName = "";
								for(int q=0;q<majorIds.length;q++){
									MajorName += doctorInfoBo.getMajorNameByMajorId(Long.valueOf(majorIds[q]));
									MajorName += "::";
								}
								String someDoctorMsg = isInService+"::";
								someDoctorMsg += MajorName;
								someDoctorMsg += doctorInfo.getDoctorName();
								someDoctorMsg += "&"+doctorInfo.getDoctorImage();
								someDoctorMsg += "&"+doctorInfo.getDoctorTitle();
								//								someDoctorMsg += "&"+doctorInfo.getMajorId();
								someDoctorMsg += "&"+doctorInfo.getDutyStarLevel();
								someDoctorMsg += "&"+doctorInfo.getSeiviceStarLevel();
								someDoctorMsg += "&"+doctorInfo.getOnTimeStarLevel();
								someDoctorMsg += "&"+doctorInfo.getId();
								someDoctorMsg += "&"+doctorInfo.getIsLogin();
								someDoctorMsgList.add(someDoctorMsg);
							}
						}
						mes="成功";
						return "success";
					}
				}
				for(int p=0;p<doctorIdCon.size();p++){
					DoctorInfo doctorInfo = doctorInfoBo.getDoctorInfoByDoctorId(doctorIdCon.get(p));
					if(doctorInfo!=null){
						String isInService = doctorInfoBo.checkIsInService(userId,doctorIdCon.get(p));
						String[] majorIds = doctorInfo.getMajorId().split("::");
						String MajorName = "";
						for(int q=0;q<majorIds.length;q++){
							MajorName += doctorInfoBo.getMajorNameByMajorId(Long.valueOf(majorIds[q]));
							MajorName += "::";
						}
						String someDoctorMsg = isInService+"::";
						someDoctorMsg += MajorName;
						someDoctorMsg += doctorInfo.getDoctorName();
						someDoctorMsg += "&"+doctorInfo.getDoctorImage();
						someDoctorMsg += "&"+doctorInfo.getDoctorTitle();
						//						someDoctorMsg += "&"+doctorInfo.getMajorId();
						someDoctorMsg += "&"+doctorInfo.getDutyStarLevel();
						someDoctorMsg += "&"+doctorInfo.getSeiviceStarLevel();
						someDoctorMsg += "&"+doctorInfo.getOnTimeStarLevel();
						someDoctorMsg += "&"+doctorInfo.getId();
						someDoctorMsg += "&"+doctorInfo.getIsLogin();
						someDoctorMsgList.add(someDoctorMsg);
					}
				}
				mes="成功";
				return "success";
			}
			else{
				doctorIdOrd = doctorInfoBo.getDoctorIdByUserId(userId);
				if(doctorIdOrd!=null){
					Long doctId = doctorIdOrd.get(0);
					for(int j=1;j<doctorIdOrd.size();j++){
						if(doctId.equals(doctorIdOrd.get(j))){
							doctorIdOrd.remove(j);
							j--;
						}else{
							doctId = doctorIdOrd.get(j);
						}
					}
					for(int p=0;p<doctorIdOrd.size();p++){
						DoctorInfo doctorInfo = doctorInfoBo.getDoctorInfoByDoctorId(doctorIdOrd.get(p));
						if(doctorInfo!=null){
							String isInService = doctorInfoBo.checkIsInService(userId,doctorIdOrd.get(p));
							String[] majorIds = doctorInfo.getMajorId().split("::");
							String MajorName = "";
							for(int q=0;q<majorIds.length;q++){
								if(q == majorIds.length-1){
									MajorName += doctorInfoBo.getMajorNameByMajorId(Long.valueOf(majorIds[q]));
								}
								MajorName += doctorInfoBo.getMajorNameByMajorId(Long.valueOf(majorIds[q]))+",";
								//								MajorName += "::";
							}
							String someDoctorMsg = isInService+"::";
							someDoctorMsg += MajorName;
							someDoctorMsg += "::"+doctorInfo.getDoctorName();
							someDoctorMsg += "&"+doctorInfo.getDoctorImage();
							someDoctorMsg += "&"+doctorInfo.getDoctorTitle();
							//							someDoctorMsg += "&"+doctorInfo.getMajorId();
							someDoctorMsg += "&"+doctorInfo.getDutyStarLevel();
							someDoctorMsg += "&"+doctorInfo.getSeiviceStarLevel();
							someDoctorMsg += "&"+doctorInfo.getOnTimeStarLevel();
							someDoctorMsg += "&"+doctorInfo.getId();
							someDoctorMsg += "&"+doctorInfo.getIsLogin();
							someDoctorMsgList.add(someDoctorMsg);
						}
					}
					mes="成功";
					return "success";

				}
				mes="无数据";
				return "fail";
			}*/
		}
		//add by sjt 我的医生
		return "fail";
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @return the doctorSomeInfoList
	 */
	public List<Object[]> getDoctorSomeInfoList() {
		return doctorSomeInfoList;
	}

	/**
	 * @return the doctorDistanceList
	 */
	public List<Double> getDoctorDistanceList() {
		return doctorDistanceList;
	}

	/**
	 * @return the doctorMajorList
	 */
	public List<List<String>> getDoctorMajorList() {
		return doctorMajorList;
	}

	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @return the someDoctor
	 */
	public DoctorInfo getSomeDoctor() {
		return someDoctor;
	}

	/**
	 * @return the doctorAssessmentTagList
	 */
	public List<Object[]> getDoctorAssessmentTagList() {
		return doctorAssessmentTagList;
	}

	/**
	 * @return the doctorProductList
	 */
	public List<Product> getDoctorProductList() {
		return doctorProductList;
	}

	/**
	 * @return the doctorArticleList
	 */
	public List<DoctorArticle> getDoctorArticleList() {
		return doctorArticleList;
	}

	/**
	 * @return the doctorDistance
	 */
	public Double getDoctorDistance() {
		return doctorDistance;
	}

	/**
	 * @return the someDoctorMajorList
	 */
	public List<String> getSomeDoctorMajorList() {
		return someDoctorMajorList;
	}

	/**
	 * @return the serviceDoctorList
	 */
	public List<DoctorInfo> getServiceDoctorList() {
		return serviceDoctorList;
	}

	/**
	 * @param serviceDate the serviceDate to set
	 */
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	/**
	 * @param serviceTime the serviceTime to set
	 */
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	/**
	 * @param prodcutId the prodcutId to set
	 */
	public void setProdcutId(long prodcutId) {
		this.prodcutId = prodcutId;
	}

	/**
	 * @return the someProdcutServiceDoctorList
	 */
	public List<DoctorInfo> getSomeProdcutServiceDoctorList() {
		return someProdcutServiceDoctorList;
	}

	/**
	 * @return the serviceDateList
	 */
	public List<String> getServiceDateList() {
		return serviceDateList;
	}

	/**
	 * @return the serviceTimeList
	 */
	public List<List<String>> getServiceTimeList() {
		return serviceTimeList;
	}

	/**
	 * @return the serviceTimeInitList
	 */
	public List<TimeInit> getServiceTimeInitList() {
		return serviceTimeInitList;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the consultationDoctorList
	 */
	public List<Object[]> getConsultationDoctorList() {
		return consultationDoctorList;
	}

	/**
	 * @param sortMethod the sortMethod to set
	 */
	public void setSortMethod(String sortMethod) {
		this.sortMethod = sortMethod;
	}

	/**
	 * @return the dateTor
	 */
	public String getDateTor() {
		return dateTor;
	}

	public List<String> getSomeDoctorMsgList() {
		return someDoctorMsgList;
	}

}
