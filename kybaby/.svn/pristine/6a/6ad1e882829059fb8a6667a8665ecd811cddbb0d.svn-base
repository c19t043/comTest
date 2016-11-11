package com.kybaby.newbussiness.familydoctor.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;






import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceItems;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceOrder;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTeams;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTimes;
import com.kybaby.newbussiness.familydoctor.domain.FdUserBuyRecord;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.MyMath;
import com.kybaby.util.SendSms;
import com.opensymphony.xwork2.ActionContext;

public class FdServiceItemsAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	
	/**
	 * 家庭医生实体类
	 */
	private FdServiceItems fdServiceItems;
	/**
	 * 家庭医生服务包
	 */
	private FdServicePackage fdServicePackage;
	/**
	 * 服务团队信息
	 */
	private FdServiceTeams fdServiceTeams;
	/**
	 * 家庭医生服务订单信息
	 */
	private FdServiceOrder fdServiceOrder;
	/**
	 * 医疗机构信息id
	 */
	private Long hospitalId;
	
	/**
	 * 服务项目列表
	 */
	private List<FdServiceItems> fdServiceItemsList = new ArrayList<>();
	/**
	 * 家庭医生服务购买记录
	 */
	private List<FdUserBuyRecord> fdUserBuyRecordList = new ArrayList<>();
	/**
	 * 家庭医生服务包列表
	 */
	private List<FdServicePackage> fdServicePackageList = new ArrayList<>();
	/**
	 * 家庭医生服务团队列表
	 */
	private List<FdServiceTeams> fdServiceTeamsList = new ArrayList<>();
	/**
	 * 家庭医生服务时间列表
	 */
	private List<FdServiceTimes> fdServiceTimesList = new ArrayList<>();
	/**
	 * 家庭医生服务时间列表
	 */
	private List<FdServiceMember> fdServiceMemberList = new ArrayList<>();

	public String execute() throws Exception {
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		/**
		 * 得到家庭医生服务项目（包含已购买或未购买的服务项目）
		 */
		if(action.equals("getAllFdServiceItems")){
			if(userId==null){//未登录，查看所有服务
				this.fdServiceItemsList = fdServiceItemsService.getAllFdServiceItems(fdServiceItems);
			}else{
				//判断当前用户是否购买了家庭医生服务，购买了进去显示套餐内容，否则显示全部服务项目
				UserInfo userInfo = this.userInfoBo.getUserById(userId);
				this.fdUserBuyRecordList = this.fdServiceItemsService.getFdUserBuyRecordList(userInfo,null);
				if(fdUserBuyRecordList == null){
					this.fdServiceItemsList = fdServiceItemsService.getAllFdServiceItems(fdServiceItems);
				}else{
					for(FdUserBuyRecord record : fdUserBuyRecordList){
						String itemIds = record.getServicePackage().getServiceItemIds();
						List<FdServiceItems> itemList  = this.getFdServiceItemsByIds(itemIds);
						record.getServicePackage().setFdServiceItemsList(itemList);
					}
				}
			}
		}
		//随时查看所有服务
		else if(action.equals("getAllFdServiceAnyThing")){
			this.fdServiceItemsList = fdServiceItemsService.getAllFdServiceItems(fdServiceItems);
		}
		/**
		 * 得到某医疗机构的服务包列表
		 */
		else if(action.equals("getFdServicePackageList")){
			if(userId == null){
				mes="请登录";
				return "fail";
			}
			if(hospitalId != null){//通过机构选服务包
				this.fdServicePackageList = this.fdServiceItemsService.getFdServicePackageList(hospitalId);
			}else if(fdServicePackage != null && fdServicePackage.getId() != null){//通过专家团第服务包
				FdServiceTeams team = this.fdServiceItemsService.getFdServiceTeamsById(fdServiceTeams.getId());
				FdServicePackage fdPackage = this.fdServiceItemsService.getFdServicePackageById(fdServicePackage.getId());
				fdPackage.getFdServiceTeamsSet().add(team);
				this.fdServicePackageList.add(fdPackage);
			}
			if(fdServicePackageList != null){
				for(FdServicePackage fdPackage : fdServicePackageList){
					//得到购买人数
					List<FdUserBuyRecord> allBuyRecordList = this.fdServiceItemsService.getFdUserBuyRecordList(null,fdPackage);
					fdPackage.setBuyCount(allBuyRecordList==null?"0":String.valueOf(allBuyRecordList.size()));
					List<FdServiceTimes> timeList = this.fdServiceItemsService.getFdServiceTimesList(fdPackage);
					fdPackage.setMinPrice(timeList==null?"200":timeList.get(0).getServicePrice());
				}
			}
		}
		/**
		 * 得到服务包下面包含的一些信息（团队，时间）
		 */
		else if(action.equals("getSomethingByPackage")){
			//服务团队
			
			if(fdServiceTeams != null && fdServiceTeams.getId() != null){//通过团队选择服务包的
				FdServiceTeams team = this.fdServiceItemsService.getFdServiceTeamsById(fdServiceTeams.getId());
				this.fdServiceTeamsList.add(team);
			}else{//通过机构来选择服务包的
				this.fdServiceTeamsList = this.fdServiceItemsService.getFdServiceTeamsList(fdServicePackage);
			}
			//服务时间
			this.fdServiceTimesList = this.fdServiceItemsService.getFdServiceTimesList(fdServicePackage);
		}
		/**
		 * 得到家庭医生服务团队成员信息
		 */
		else if(action.equals("getFdServiceMemberList")){
			this.fdServiceMemberList = this.fdServiceItemsService.getFdServiceMemberList(fdServiceTeams,null);
		}
		/**
		 * 购买处理（订单及购买记录信息）
		 */
		else if(action.equals("handleFdServiceOrder")){
			if(userId == null){
				mes="请登录";
				return "fail";
			}
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			//保存订单
			if(fdServiceOrder.getId() == null) { //保存添加
				//根据服务包和服务时间查询订单是否已存在
				FdServiceOrder fdServiceOrderCond = new FdServiceOrder();
				fdServiceOrderCond.setOrderStatus(ConstantManage.NO_PAYMENT_CLINIC_ORDER);
				List<FdServiceOrder> orderList = this.fdServiceItemsService.
						getFdServiceOrderList(fdServiceOrder.getFdServicePackage(), fdServiceOrder.getFdServiceTeams(),
								fdServiceOrder.getFdServiceTimes(),userInfo,fdServiceOrderCond);
				if(orderList == null){
					String orderNum = String.valueOf(System.currentTimeMillis());
					fdServiceOrder.setOrderNum(orderNum);
					fdServiceOrder.setUserInfo(userInfo);
					fdServiceOrder.setOrderStatus("未付款");
					fdServiceOrder.setSubmitTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
					String effectDay = fdServiceOrder.getFdServiceTimes().getEffectiveTime();
					if(effectDay == null || "".equals(effectDay.trim())){
						effectDay = null;
					}
					String serviceEndTime = DateManage.getBeforeOrAfter(effectDay==null?0:Integer.valueOf(effectDay));
					fdServiceOrder.setServiceEndTime(serviceEndTime);
					Long id = this.fdServiceItemsService.saveOrUpdateFdServiceOrder(fdServiceOrder);
					fdServiceOrder.setId(id);
				}else{
					//将已存在的未付款订单的服务结束时间进行更新（已当前时间往后算）
					String effectDay = fdServiceOrder.getFdServiceTimes().getEffectiveTime();
					if(effectDay == null || "".equals(effectDay.trim())){
						effectDay = null;
					}
					String serviceEndTime = DateManage.getBeforeOrAfter(effectDay==null?0:Integer.valueOf(effectDay));
					FdServiceOrder oldOrder = orderList.get(0);
					oldOrder.setServiceEndTime(serviceEndTime);
					Long id = this.fdServiceItemsService.saveOrUpdateFdServiceOrder(oldOrder);
					fdServiceOrder.setId(id);
				}
				
			}else{
				FdServiceOrder oldOrder = this.fdServiceItemsService.getFdServiceOrderById(fdServiceOrder.getId());
				oldOrder.setPayMethod(fdServiceOrder.getPayMethod());
				oldOrder.setUseRemainBalance(fdServiceOrder.getUseRemainBalance());
				if("付款成功".equals(fdServiceOrder.getOrderStatus())){
					if("线下支付".equals(fdServiceOrder.getPayMethod())){
						oldOrder.setOrderStatus("等待支付");
						oldOrder.setUseRemainBalance(0d);
						//发送短信通知
						SendSms ss = new SendSms();
						String orgName = "康优宝贝";
						if(oldOrder.getFdServicePackage().getHospitalBasicInfo() != null){
							orgName = oldOrder.getFdServicePackage().getHospitalBasicInfo().getHospitalLname();
						}
						String contecnt =  "亲爱的用户，您于"+DateManage.getDateStr("yyyy年MM月dd日HH时")+"在康优宝贝APP成功预定【"+
								orgName + "】服务包"+
								oldOrder.getFdServiceTimes().getEffectiveTimeShow()+"并选择线下支付，请尽快至" +
								orgName +
								"完成支付，详情请登录康优宝贝APP查看。咨询电话:400-0122-149。";
						ss.sendInfo(userInfo.getPhone(), contecnt.toString());
					}else{
						//用户的订单金额
						double totalPriceCalc = Double.valueOf(oldOrder.getTotalPrice());
						//用户使用的余额
						double useRemainBalanceCalc = oldOrder.getUseRemainBalance()==null?0d:Double.valueOf(oldOrder.getUseRemainBalance());
						//用户使用的福利金额
//						double discountMoneyCalc = orderInfoClinic.getDiscountMoney()==null?0d:Double.valueOf(orderInfoClinic.getDiscountMoney());
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
							this.userAccountBo.addNewUserAccount(currentUserInfo.getId(), useRemainBalanceCalc, "-", "余额支付家庭医生服务", oldOrder.getOrderNum());
							/**
							 * 更新订单的信息
							 */
							oldOrder.setOrderStatus("已付款");
							//oldOrder.setPayMethod("余额支付");//用户使用的支付方式
							oldOrder.setUseRemainBalance(useRemainBalanceCalc);//用户使用的余额数
							oldOrder.setRealPrice(chargeBalance);//实付金额
//							oldOrder.setDiscountMoney(String.valueOf(discountMoneyCalc));//福利优惠抵扣数
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
								this.userAccountBo.addNewUserAccount(currentUserInfo.getId(), useRemainBalanceCalc, "-", "余额支付支付家庭医生服务", oldOrder.getOrderNum());
							}
							//在线支付明细记录
							this.userAccountBo.addNewUserAccount(currentUserInfo.getId(), chargeBalance, "-", "在线支付家庭医生服务", oldOrder.getOrderNum());
							/**
							 * 更新订单的信息
							 */
							//oldOrder.setPayMethod("在线支付");
							oldOrder.setUseRemainBalance(useRemainBalanceCalc);//用户使用的余额数
							oldOrder.setRealPrice(chargeBalance);//实付金额
//							oldOrder.setDiscountMoney(String.valueOf(discountMoneyCalc));//福利优惠抵扣数
						}
						oldOrder.setOrderStatus("已付款");
						//保存购买记录（购买成功时添加购买记录）
						this.addFdUserBuyRecord(oldOrder, userInfo);
						//发送短信通知
						SendSms ss = new SendSms();
						String orgName = "康优宝贝";
						if(oldOrder.getFdServicePackage().getHospitalBasicInfo() != null){
							orgName = oldOrder.getFdServicePackage().getHospitalBasicInfo().getHospitalLname();
						}
						String contecnt = "亲爱的用户，您于"+DateManage.getDateStr("yyyy年MM月dd日")+"在"+
								orgName +
								"成功购买【"+oldOrder.getFdServicePackage().getPackageShowName()+"】服务包有效期从"+
								DateManage.getDateStr("yyyy-MM-dd")+"至"+oldOrder.getServiceEndTime()+"。详情请登录康优宝贝APP查看。咨询电话:400-0122-149";
						ss.sendInfo(userInfo.getPhone(), contecnt.toString());
					}
					oldOrder.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
					this.fdServiceItemsService.saveOrUpdateFdServiceOrder(oldOrder);
			}
		}
		}
		/**
		 * 得到订单及购买记录信息）
		 */
		else if(action.equals("getFdServiceOrder")){
			this.fdServiceOrder = this.fdServiceItemsService.getFdServiceOrderById(fdServiceOrder.getId());
		}
		/**
		 * 得到专家团队列表
		 */
		else if(action.equals("getAllExpertTeams")){
			//服务团队
			List<FdServiceTeams> fdServiceTeamsListAll = this.fdServiceItemsService.getFdServiceTeamsList(null);
			if(fdServiceTeamsListAll != null){
				for(FdServiceTeams team : fdServiceTeamsListAll){
					if(!team.getFdServicePackageSet().isEmpty()){
						Iterator<FdServicePackage> itPackage = team.getFdServicePackageSet().iterator();
						Long buyCount = 0l;
						while(itPackage.hasNext()){
							FdServicePackage fdServicePackage = itPackage.next();
							//服务时间
							List<FdServiceTimes> serviceTimesList = this.fdServiceItemsService.getFdServiceTimesList(fdServicePackage);
							if(serviceTimesList != null){
								fdServicePackage.setMinPrice(serviceTimesList.get(0).getServicePrice());
							}
							//得到购买人数
							List<FdUserBuyRecord> allBuyRecordList = this.fdServiceItemsService.getFdUserBuyRecordList(null,fdServicePackage);
							fdServicePackage.setBuyCount(allBuyRecordList==null?"0":String.valueOf(allBuyRecordList.size()));
							buyCount += allBuyRecordList==null?0L:Long.valueOf(allBuyRecordList.size());
						}
						team.setBuyCount(buyCount);
						this.fdServiceTeamsList.add(team);
					}
				}
			}
		}
		/**
		 * 得到专家团队的包列表
		 */
		else if(action.equals("getAllPackageByTeam")){
			//服务团队
			FdServiceTeams fdServiceTeamOne = this.fdServiceItemsService.getFdServiceTeamsById(fdServiceTeams.getId());
			if(fdServiceTeamOne != null){
				Iterator<FdServicePackage> itPackage = fdServiceTeamOne.getFdServicePackageSet().iterator();
				while(itPackage.hasNext()){
					FdServicePackage fdServicePackage = itPackage.next();
					//服务时间
					List<FdServiceTimes> serviceTimesList = this.fdServiceItemsService.getFdServiceTimesList(fdServicePackage);
					if(serviceTimesList != null){
						fdServicePackage.setMinPrice(serviceTimesList.get(0).getServicePrice());
					}
					fdServicePackageList.add(fdServicePackage);
				}
			}
		}
		
		return SUCCESS;
	}
	/**
	 * 得到服务项目集合
	 * @param ids 服务项目ids
	 * @return
	 */
	private List<FdServiceItems> getFdServiceItemsByIds(String ids){
		String [] idArr = ids.split(",");
		List<FdServiceItems> itemList = new ArrayList<>();
		for (int i = 0; i < idArr.length; i++) {
			FdServiceItems item = this.fdServiceItemsService.getFdServiceItemsById(Long.valueOf(idArr[i]));
			itemList.add(item);
		}
		return itemList;
	}
	/**
	 * 付款后，添加购买记录
	 * @param oldOrder
	 * @param userInfo
	 */
	private void addFdUserBuyRecord(FdServiceOrder oldOrder,UserInfo userInfo){
		FdUserBuyRecord fdUserBuyRecord = new FdUserBuyRecord();
		fdUserBuyRecord.setFdServiceOrder(oldOrder);
		fdUserBuyRecord.setPayPrice(oldOrder.getTotalPrice());
		fdUserBuyRecord.setServicePackage(oldOrder.getFdServicePackage());
		fdUserBuyRecord.setServiceStartTime(DateManage.getDateStr("yyyy-MM-dd"));
		String effectDay = oldOrder.getFdServiceTimes().getEffectiveTime();
		if(effectDay == null || "".equals(effectDay.trim())){
			effectDay = null;
		}
		String serviceEndTime = DateManage.getBeforeOrAfter(effectDay==null?0:Integer.valueOf(effectDay));
		fdUserBuyRecord.setServiceEndTime(serviceEndTime);
		fdUserBuyRecord.setUserInfo(userInfo);
		this.fdServiceItemsService.saveOrUpdateFdUserBuyRecord(fdUserBuyRecord);
	}
	public FdServiceItems getFdServiceItems() {
		return fdServiceItems;
	}

	public void setFdServiceItems(FdServiceItems fdServiceItems) {
		this.fdServiceItems = fdServiceItems;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public List<FdServiceItems> getFdServiceItemsList() {
		return fdServiceItemsList;
	}

	public void setFdServiceItemsList(List<FdServiceItems> fdServiceItemsList) {
		this.fdServiceItemsList = fdServiceItemsList;
	}

	public List<FdUserBuyRecord> getFdUserBuyRecordList() {
		return fdUserBuyRecordList;
	}

	public void setFdUserBuyRecordList(List<FdUserBuyRecord> fdUserBuyRecordList) {
		this.fdUserBuyRecordList = fdUserBuyRecordList;
	}

	public List<FdServicePackage> getFdServicePackageList() {
		return fdServicePackageList;
	}

	public void setFdServicePackageList(List<FdServicePackage> fdServicePackageList) {
		this.fdServicePackageList = fdServicePackageList;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}
	public List<FdServiceTeams> getFdServiceTeamsList() {
		return fdServiceTeamsList;
	}
	public void setFdServiceTeamsList(List<FdServiceTeams> fdServiceTeamsList) {
		this.fdServiceTeamsList = fdServiceTeamsList;
	}
	public FdServicePackage getFdServicePackage() {
		return fdServicePackage;
	}
	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}
	public List<FdServiceTimes> getFdServiceTimesList() {
		return fdServiceTimesList;
	}
	public void setFdServiceTimesList(List<FdServiceTimes> fdServiceTimesList) {
		this.fdServiceTimesList = fdServiceTimesList;
	}
	public List<FdServiceMember> getFdServiceMemberList() {
		return fdServiceMemberList;
	}
	public void setFdServiceMemberList(List<FdServiceMember> fdServiceMemberList) {
		this.fdServiceMemberList = fdServiceMemberList;
	}
	public FdServiceTeams getFdServiceTeams() {
		return fdServiceTeams;
	}
	public void setFdServiceTeams(FdServiceTeams fdServiceTeams) {
		this.fdServiceTeams = fdServiceTeams;
	}
	public FdServiceOrder getFdServiceOrder() {
		return fdServiceOrder;
	}
	public void setFdServiceOrder(FdServiceOrder fdServiceOrder) {
		this.fdServiceOrder = fdServiceOrder;
	}
}
