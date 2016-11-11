package com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceOrder;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ArchivesInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.ChildcareProject;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.HospitalBanner;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMeal;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMealHospital;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetMeatOrder;
import com.kybaby.newbussiness.medicalorgandbusiness.organsetmeat.domain.OrganSetPro;
import com.kybaby.newbussiness.userconsult.domain.ConsultBabyInfo;
import com.kybaby.util.CalculationMethod;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.GetDistance;
import com.kybaby.util.MyMath;
import com.kybaby.util.SendSms;
import com.opensymphony.xwork2.ActionContext;

public class OrganSetMealAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 机构套餐信息
	 */
	private OrganSetMeal organSetMeal;
	/**
	 * 机构套餐信息列表
	 */
	private List<OrganSetMeal> organSetMealList = new ArrayList<OrganSetMeal>();;
	/**
	 * 医院机构列表
	 */
	private List<HospitalBasicInfo> hospitalBasicInfoList = new ArrayList<HospitalBasicInfo>();
	/**
	 * 医院机构信息
	 */
	private HospitalBasicInfo hospitalBasicInfo;
	/**
	 * 经度
	 */
	private Double lng_current;
	/**
	 * 纬度
	 */
	private Double lat_current;
	/**
	 * 套餐购买订单
	 */
	private OrganSetMeatOrder organSetMeatOrder;
	/**
	 * 套餐购买订单列表
	 */
	private List<OrganSetMeatOrder> organSetMeatOrderList;
	/**
	 * 关联宝宝信息
	 */
	private ConsultBabyInfo consultBabyInfo;
	/**
	 * 套餐产品列表
	 */
	private List<OrganSetPro> parentOrganSetProList = new ArrayList<>();
	/**
	 * 套餐产品列表
	 */
	private List<OrganSetPro> childOrganSetProList = new ArrayList<>();
	/**
	 * 产品
	 */
	private OrganSetPro organSetPro;
	
	public String execute() throws Exception{
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		//得到套餐列表
		if(action.equals("getOrganSetMealList")){
			if(hospitalBasicInfo != null && hospitalBasicInfo.getId() != null){
				List<OrganSetMealHospital> organSetMealHospitalList = this.organSetMealService.getOrganSetMealHospitalList(null, hospitalBasicInfo);
				if(organSetMealHospitalList != null){
					for(OrganSetMealHospital os : organSetMealHospitalList){
						this.organSetMealList.add(os.getOrganSetMeal());
					}
				}else{
					this.organSetMealList = null;
				}
			}else{
				this.organSetMealList = this.organSetMealService.getOrganSetMealList(organSetMeal);
			}
		}
		//得到开展机构套餐的医院
		else if(action.equals("getSetMealHospitalList")){
			if(userId==null){
				mes="请登录";
				return "fail";
			}
			UserInfo user = this.userInfoBo.getUserById(userId);
			double lng1 = Double.valueOf(user.getUserLng()==null?"0":user.getUserLng());
			double lat1 = Double.valueOf(user.getUserLat()==null?"0":user.getUserLat());
			List<OrganSetMealHospital> organSetMealHospitalList = this.organSetMealService.getOrganSetMealHospitalList(organSetMeal, null);
			if(organSetMealList != null){
				Map<Long,HospitalBasicInfo> hospitalMap = new HashMap<Long,HospitalBasicInfo>();
				for(OrganSetMealHospital os : organSetMealHospitalList){
					hospitalMap.put(os.getHospitalBasicInfo().getId(), os.getHospitalBasicInfo());
				}
				List<HospitalBasicInfo> newPackageHosList = new ArrayList<HospitalBasicInfo>();
				HospitalBanner banner = new HospitalBanner();
				banner.setIsMainImg("Y");
				List businessNameList = new ArrayList<String>();
				for (Map.Entry<Long,HospitalBasicInfo> entry : hospitalMap.entrySet()) {
					HospitalBasicInfo hospital = entry.getValue();
					//计算与当前用户的距离
					double lng2 = Double.valueOf(hospital.getHospitalLng()==null?"0":hospital.getHospitalLng());
					double lat2 = Double.valueOf(hospital.getHospitalLat()==null?"0":hospital.getHospitalLat());
					double distance = 
							GetDistance.GetDistanceMethod(lng_current==null?lng1:lng_current, lat_current==null?lat1:lat_current,
									lng2, lat2);
					hospital.setToUserDistance(distance);
					//得到默认显示图片
					List<HospitalBanner> bannerList = this.organManagerService.getHospitalBannerList(hospital, banner);
					if(bannerList != null){
						hospital.setShowImgPath(bannerList.get(0).getImgPath());
					}
					businessNameList.add("套餐服务");
					hospital.setBusinessNameList(businessNameList);
					newPackageHosList.add(hospital);
				}
				// 按距离顺序  家庭医生机构
		        Collections.sort(newPackageHosList, new Comparator<HospitalBasicInfo>() {  
		            public int compare(HospitalBasicInfo arg0, HospitalBasicInfo arg1) {  
		                double hits0 = arg0.getToUserDistance();  
		                double hits1 = arg1.getToUserDistance();  
		                if (hits1 > hits0) {  
		                    return -1;  
		                } else if (hits1 == hits0) {  
		                    return 0;  
		                } else {  
		                    return 1;  
		                }  
		            }  
		        });
		        for(HospitalBasicInfo hospital : newPackageHosList){
		        	this.hospitalBasicInfoList.add(hospital);
		        }
			}
			//得到能够展示给用户的机构
			if(hospitalBasicInfo == null)
				hospitalBasicInfo = new HospitalBasicInfo();
			hospitalBasicInfo.setIsShowForUser("Y");
			List<HospitalBasicInfo> showToUserHosList = this.organManagerService.getHospitalBasicInfoList(hospitalBasicInfo);
			if(showToUserHosList != null){
				List<HospitalBasicInfo> openServiceHosList = new ArrayList<HospitalBasicInfo>();
				HospitalBanner banner = new HospitalBanner();
				banner.setIsMainImg("Y");
				for(HospitalBasicInfo hospital : showToUserHosList){
					//计算与当前用户的距离
					double lng2 = Double.valueOf(hospital.getHospitalLng()==null?"0":hospital.getHospitalLng());
					double lat2 = Double.valueOf(hospital.getHospitalLat()==null?"0":hospital.getHospitalLat());
					double distance = 
							GetDistance.GetDistanceMethod(lng_current==null?lng1:lng_current, lat_current==null?lat1:lat_current,
									lng2, lat2);
					hospital.setToUserDistance(distance);
					//得到默认显示图片
					List<HospitalBanner> bannerList = this.organManagerService.getHospitalBannerList(hospital, banner);
					if(bannerList != null){
						hospital.setShowImgPath(bannerList.get(0).getImgPath());
					}
					if(hospitalBasicInfoList != null && !hospitalBasicInfoList.contains(hospital)){
						openServiceHosList.add(hospital);
					}
				}
				// 按距离顺序  
		        Collections.sort(openServiceHosList, new Comparator<HospitalBasicInfo>() {  
		            public int compare(HospitalBasicInfo arg0, HospitalBasicInfo arg1) {  
		                double hits0 = arg0.getToUserDistance();  
		                double hits1 = arg1.getToUserDistance();  
		                if (hits1 > hits0) {  
		                    return -1;  
		                } else if (hits1 == hits0) {  
		                    return 0;  
		                } else {  
		                    return 1;  
		                }  
		            }  
		        }); 
		        for(HospitalBasicInfo hospital : openServiceHosList){
		        	this.hospitalBasicInfoList.add(hospital);
		        }
			}
		}
		/**
		 * 得到套餐详细信息，以便购买
		 */
		else if(action.equals("getOrganSetMealDetail")){
			if(userId == null){
				mes="请登录";
				return "fail";
			}
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			//得到已选择的套餐信息
			this.organSetMeal = organSetMealService.getOrganSetMealById(organSetMeal.getId());
			//得到关联宝宝信息
			List<ConsultBabyInfo> babyInfoListInit = this.consultBabyInfoService.getBabyInfoListByParentId(userId);
			if(babyInfoListInit == null || babyInfoListInit.isEmpty()){
				//没有关联宝宝默认初始化当前用户数据
				UserInfo usr = this.userInfoBo.getUserById(userId);
				ConsultBabyInfo babyInfo = new ConsultBabyInfo();
				babyInfo.setBabyName(usr.getBabyName());
				babyInfo.setBirthday(usr.getBirthday());
				babyInfo.setSex(usr.getSex());
				babyInfo.setUserInfo(usr);
				this.consultBabyInfoService.addBabyInfo(babyInfo);
			}
			babyInfoListInit = this.consultBabyInfoService.getBabyInfoListByParentId(userId);
			this.consultBabyInfo = babyInfoListInit==null?null:babyInfoListInit.get(0);
			String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
			String  monthAge = String.valueOf(CalculationMethod.getMonthSpace(consultBabyInfo.getBirthday(), rightNow));
			consultBabyInfo.setMonthAge(monthAge);
			//得到一级产品列表
			this.parentOrganSetProList = organSetMealService.getOrganSetProList(null, null, hospitalBasicInfo,organSetMeal);
		}
		/**
		 * 得到子产品列表
		 */
		else if(action.equals("getChildOrganSetProList")){
			if(userId == null){
				mes="请登录";
				return "fail";
			}
//			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			this.consultBabyInfo = consultBabyInfoService.getBabyInfoByID(consultBabyInfo.getId());
			this.childOrganSetProList = this.organSetMealService.getOrganSetProList(organSetPro, null, hospitalBasicInfo,null);
			if(childOrganSetProList != null){
				for(OrganSetPro prod : childOrganSetProList){
					prod.setIsCanChoose("Y");
					if("0".equals(prod.getProType()) && "0".equals(prod.getPriceType())){//儿保产品,实时计算的，价格根据月龄和年龄动态算
						String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
						String monthAge = String.valueOf(CalculationMethod.getMonthSpace(consultBabyInfo.getBirthday(), rightNow));
						String maxMonthAge = prod.getMaxMonthAge()==null?"0":prod.getMaxMonthAge();
						double childCareProductPrice = this.getProductPrice(monthAge, maxMonthAge);
						prod.setProPrice(String.valueOf(childCareProductPrice));
						if(Double.parseDouble(monthAge) > Double.parseDouble(maxMonthAge)){
							prod.setIsCanChoose("N");
						}
					}
				}
			}
		}
		/**
		 * 得到购买订单信息
		 */
		else if(action.equals("getOrganSetMealOrder")){
			if(userId == null){
				mes="请登录";
				return "fail";
			}
			this.organSetMeatOrder = this.organSetMealService.getOrganSetMeatOrderById(organSetMeatOrder.getId());
		}
		/**
		 * 购买处理（订单及购买记录信息）
		 */
		else if(action.equals("handleOrganSetMealOrder")){
			if(userId == null){
				mes="请登录";
				return "fail";
			}
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			//保存订单
			if(organSetMeatOrder.getId() == null) { //保存添加
				ConsultBabyInfo baby = consultBabyInfoService.getBabyInfoByID(organSetMeatOrder.getBabyInfo().getId());
				//根据服务包和服务时间查询订单是否已存在
				OrganSetMeatOrder order = new OrganSetMeatOrder();
				order.setOrderStatus(ConstantManage.NO_PAYMENT_CLINIC_ORDER);
				List<OrganSetMeatOrder> orderList = this.organSetMealService.
						getOrganSetMeatOrderList(organSetMeatOrder.getOrganSetMeal(), organSetMeatOrder.getOrganSetPro(),userInfo,order);
				
				OrganSetPro pro = this.organSetMealService.getOrganSetProById(organSetMeatOrder.getOrganSetPro().getId());
				String serviceEndTime = null;
				if("0".equals(pro.getProType())){//儿保产品，通过月龄计算到期时间
					ConsultBabyInfo babyInfo = this.consultBabyInfoService.getBabyInfoByID(organSetMeatOrder.getBabyInfo().getId());
					String maxMonth = pro.getMaxMonthAge();
					serviceEndTime = DateManage.getBeforeOrAfterByOneDate(DateManage.getStrToDate(babyInfo.getBirthday()),
							maxMonth==null?0:Integer.valueOf(maxMonth)*30);
				}else{
					String effectDay = pro.getServiceTimeLength();
					String serviceTimeUnit = pro.getServiceTimeUnit();
					if(effectDay == null || "".equals(effectDay.trim())){
						effectDay = null;
					}
					if("天".equals(serviceTimeUnit)){
						serviceEndTime = DateManage.getBeforeOrAfter(effectDay==null?0:Integer.valueOf(effectDay));
					}else if("月".equals(serviceTimeUnit)){
						serviceEndTime = DateManage.getBeforeOrAfter(effectDay==null?0:Integer.valueOf(effectDay)*30);
					}
				}
				if(orderList == null){
					String orderNum = String.valueOf(System.currentTimeMillis());
					organSetMeatOrder.setOrderNum(orderNum);
					organSetMeatOrder.setUserInfo(userInfo);
					organSetMeatOrder.setOrderStatus("未付款");
					organSetMeatOrder.setSubmitTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
					organSetMeatOrder.setServiceEndTime(serviceEndTime);
					Long id = this.organSetMealService.saveOrUpdateOrganSetMeatOrder(organSetMeatOrder);
					organSetMeatOrder.setId(id);
				}else{
					//已存在订单重新更新服务到期时间
					OrganSetMeatOrder oldOrder = orderList.get(0);
					oldOrder.setServiceEndTime(serviceEndTime);
					oldOrder.setTotalPrice(Double.valueOf(organSetMeatOrder.getTotalPrice()));
					Long id = this.organSetMealService.saveOrUpdateOrganSetMeatOrder(oldOrder);
					organSetMeatOrder.setId(id);
				}
			}
			else{
				OrganSetMeatOrder oldOrder = this.organSetMealService.getOrganSetMeatOrderById(organSetMeatOrder.getId());
				oldOrder.setPayMethod(organSetMeatOrder.getPayMethod());
				oldOrder.setUseRemainBalance(organSetMeatOrder.getUseRemainBalance());
				if("付款成功".equals(organSetMeatOrder.getOrderStatus())){
					if("线下支付".equals(organSetMeatOrder.getPayMethod())){
						oldOrder.setOrderStatus("等待支付");
						oldOrder.setUseRemainBalance(0d);
						//保存购买订单
						oldOrder.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
						this.organSetMealService.saveOrUpdateOrganSetMeatOrder(oldOrder);
						//发送短信通知
						SendSms ss = new SendSms();
						String contecnt =  "亲爱的用户，您于"+DateManage.getDateStr("yyyy年MM月dd日HH时")+"在康优宝贝APP成功预定【"+
								oldOrder.getOrganSetPro().getHospitalBasicInfo().getHospitalLname() + "服务】"+
								oldOrder.getOrganSetPro().getProName()+"并选择线下支付，请尽快至" +
								oldOrder.getOrganSetPro().getHospitalBasicInfo().getHospitalLname() +
								"完成支付，详情请登录康优宝贝APP查看。咨询电话:400-0122-149。";
						ss.sendInfo(userInfo.getPhone(), contecnt.toString());
					}else{
						//用户的订单金额
						double totalPriceCalc = Double.valueOf(oldOrder.getTotalPrice());
						//用户使用的余额
						double useRemainBalanceCalc = oldOrder.getUseRemainBalance()==null?0d:Double.valueOf(oldOrder.getUseRemainBalance());
						//最终需要支付的金额
						double chargeBalance = totalPriceCalc - useRemainBalanceCalc - 0d;
						chargeBalance = chargeBalance<=0D?0D:chargeBalance;
						chargeBalance = MyMath.round(chargeBalance, 2);
						//用户使用账户里的余额完全支付订单金额，不需要在线支付了
						if (chargeBalance <= 0D) {
							UserInfo currentUserInfo = this.userInfoBo.getUserById(oldOrder.getUserInfo().getId());
							//用户账户的余额
							double accountBalance = currentUserInfo.getAccountBalance();
							double balance = accountBalance - useRemainBalanceCalc;
							balance = MyMath.round(balance, 2);
							currentUserInfo.setAccountBalance(balance<0d?0d:balance);
							//更新用户余额
							this.userInfoBo.updateUser(currentUserInfo);
							//添加用户余额支付明细
							this.userAccountBo.addNewUserAccount(currentUserInfo.getId(), useRemainBalanceCalc, "-", "余额支付机构套餐服务", oldOrder.getOrderNum());
							/**
							 * 更新订单的信息
							 */
							oldOrder.setOrderStatus("已付款");
							//oldOrder.setPayMethod("余额支付");//用户使用的支付方式
							oldOrder.setUseRemainBalance(useRemainBalanceCalc);//用户使用的余额数
							oldOrder.setRealPrice(chargeBalance);//实付金额
						} 
						//用户还需要在线支付
						else {
							UserInfo currentUserInfo = this.userInfoBo.getUserById(oldOrder.getUserInfo().getId());
							//用户使用了账户中部分的余额
							if (useRemainBalanceCalc !=  0D) {
								//用户账户的余额
								double accountBalance = currentUserInfo.getAccountBalance();
								double balance = accountBalance - useRemainBalanceCalc;
								balance = MyMath.round(balance, 2);
								currentUserInfo.setAccountBalance(balance<0d?0d:balance);
								//更新用户余额
								this.userInfoBo.updateUser(currentUserInfo);
								//添加用户用钱明细
								this.userAccountBo.addNewUserAccount(currentUserInfo.getId(), useRemainBalanceCalc, "-", "余额支付支付机构套餐服务", oldOrder.getOrderNum());
							}
							//在线支付明细记录
							this.userAccountBo.addNewUserAccount(currentUserInfo.getId(), chargeBalance, "-", "在线支付机构套餐服务", oldOrder.getOrderNum());
							/**
							 * 更新订单的信息
							 */
							//oldOrder.setPayMethod("在线支付");
							oldOrder.setUseRemainBalance(useRemainBalanceCalc);//用户使用的余额数
							oldOrder.setRealPrice(chargeBalance);//实付金额
						}
						oldOrder.setOrderStatus("已付款");
						//保存购买订单
						oldOrder.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
						this.organSetMealService.saveOrUpdateOrganSetMeatOrder(oldOrder);
						//发送短信通知
						SendSms ss = new SendSms();
						String contecnt = "亲爱的用户，您于"+DateManage.getDateStr("yyyy年MM月dd日")+"在"+
								oldOrder.getOrganSetPro().getHospitalBasicInfo().getHospitalLname() +
								"成功购买【"+oldOrder.getOrganSetMeal().getPackageName()+"服务】有效期从"+
								DateManage.getDateStr("yyyy-MM-dd")+"至"+oldOrder.getServiceEndTime()+"。详情请登录康优宝贝APP查看。咨询电话:400-0122-149";
						ss.sendInfo(userInfo.getPhone(), contecnt.toString());
//						//购买成功后，判断是否已经存在身份信息，没有自动添加用户身份信息
//						ArchivesInfo archivesInfo = new ArchivesInfo();
//						archivesInfo.setHospitalBasicInfo(oldOrder.getOrganSetPro().getHospitalBasicInfo());
//						archivesInfo.setArchivesMobile(userInfo.getPhone());
//						archivesInfo.setChildrenBirthday(oldOrder.getBabyInfo().getBirthday());
//						String flag = this.vaccineManageService.saveRelationArchivesInfo(userId, archivesInfo);
//						if(flag.equals("未关联")){
//							archivesInfo.setChildrenName(oldOrder.getBabyInfo().getBabyName());
//							archivesInfo.setIsRelation("Y");
//							archivesInfo.setUserInfo(userInfo);
//							archivesInfo.setUserFrom(ArchivesInfo.USER_FROM_ONLINE);
//							OrganSetPro pro = this.organSetMealService.getOrganSetProById(oldOrder.getOrganSetPro().getId());
//							archivesInfo.setUserType(pro.getParentOrganSetPro().getProCode());
//							archivesInfo.setMaxMonthAge(pro.getMaxMonthAge());
//							
//							String effectDay = pro.getServiceTimeLength();
//							String serviceTimeUnit = pro.getServiceTimeUnit();
//							if(effectDay == null || "".equals(effectDay.trim())){
//								effectDay = null;
//							}
//							String serviceEndTime = null;
//							if("天".equals(serviceTimeUnit)){
//								serviceEndTime = DateManage.getBeforeOrAfter(effectDay==null?0:Integer.valueOf(effectDay));
//							}else if("月".equals(serviceTimeUnit)){
//								serviceEndTime = DateManage.getBeforeOrAfter(effectDay==null?0:Integer.valueOf(effectDay)*30);
//							}
//							archivesInfo.setExpireTime(serviceEndTime);
//							archivesInfo.setCreatTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
//							archivesInfo.setModifyTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
//							this.vaccineManageService.saveOrUpdateArchivesInfo(archivesInfo);
//						}
					}
			}
		}
		}
		/**
		 * 得到套餐订单信息列表
		 */
		else if(action.equals("getOrganSetMeatOrderList")){
			if(userId == null){
				mes="请登录";
				return "fail";
			}
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			this.organSetMeatOrderList = this.organSetMealService.getOrganSetMeatOrderList(null, null, userInfo, organSetMeatOrder);
			//判断订单是否过期，或是否适合当前用户
			if(organSetMeatOrderList != null){
				for (OrganSetMeatOrder setOrder : organSetMeatOrderList) {
					ConsultBabyInfo baby = setOrder.getBabyInfo();
					String rightNow=CalculationMethod.rightNowDate().toString();//返回的时间格式为：2008-08-08
					String  monthAge = String.valueOf(CalculationMethod.getMonthSpace(baby.getBirthday(), rightNow));
					String maxMonth = setOrder.getOrganSetPro().getMaxMonthAge();
					//判断到期时间
					String endTime = setOrder.getServiceEndTime();
					Date aDate = DateManage.getStrToDate(DateManage.getDateStr("yyyy-MM-dd"));
					Date bDate = DateManage.getStrToDate(endTime);
					boolean flag = DateManage.isCompareDates(aDate, bDate);
					if(Double.parseDouble(monthAge) > Double.parseDouble(maxMonth) || flag){//超过最大月龄，不可用
						setOrder.setIsCanUse("N");
					}
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * 计算儿保项目总价
	 * @param monthAge
	 * @param maxMonthAge
	 * @return
	 */
	private double getProductPrice(String monthAge,String maxMonthAge){
		double proPrice = 0d;
		List<ChildcareProject> childcareProjectList = 
				this.organSetMealService.getChildcareProjectListBySome(hospitalBasicInfo, monthAge, maxMonthAge);
		if(childcareProjectList != null){
			for(ChildcareProject proj : childcareProjectList){
				proPrice += proj.getItemMoney()==null?0D:Double.valueOf(proj.getItemMoney());
				proPrice = MyMath.round(proPrice, 2);
			}
		}
		return proPrice;
	}
	
	public String getMes() {
		return mes;
	}

	public OrganSetMeal getOrganSetMeal() {
		return organSetMeal;
	}

	public void setOrganSetMeal(OrganSetMeal organSetMeal) {
		this.organSetMeal = organSetMeal;
	}

	public List<OrganSetMeal> getOrganSetMealList() {
		return organSetMealList;
	}

	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		return hospitalBasicInfoList;
	}

	public void setLng_current(Double lng_current) {
		this.lng_current = lng_current;
	}

	public void setLat_current(Double lat_current) {
		this.lat_current = lat_current;
	}

	public OrganSetMeatOrder getOrganSetMeatOrder() {
		return organSetMeatOrder;
	}

	public void setOrganSetMeatOrder(OrganSetMeatOrder organSetMeatOrder) {
		this.organSetMeatOrder = organSetMeatOrder;
	}

	public void setHospitalBasicInfo(HospitalBasicInfo hospitalBasicInfo) {
		this.hospitalBasicInfo = hospitalBasicInfo;
	}

	public ConsultBabyInfo getConsultBabyInfo() {
		return consultBabyInfo;
	}

	public List<OrganSetPro> getParentOrganSetProList() {
		return parentOrganSetProList;
	}

	public List<OrganSetPro> getChildOrganSetProList() {
		return childOrganSetProList;
	}

	public void setOrganSetPro(OrganSetPro organSetPro) {
		this.organSetPro = organSetPro;
	}
	
	public void setConsultBabyInfo(ConsultBabyInfo consultBabyInfo) {
		this.consultBabyInfo = consultBabyInfo;
	}
	public List<OrganSetMeatOrder> getOrganSetMeatOrderList() {
		return organSetMeatOrderList;
	}
	
}
