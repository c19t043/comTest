package com.kybaby.newbussiness.b2cgoods.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.b2cgoods.domain.B2cAddress;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoods;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsBasicPropvalSet;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsDeliver;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrder;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrderDetail;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrderPromotion;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsPresaleModel;
import com.kybaby.newbussiness.b2cgoods.fo.GoodsPropValSetFo;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceOrder;
import com.kybaby.util.ConstantManage;
import com.kybaby.util.DateManage;
import com.kybaby.util.MyMath;
import com.kybaby.util.SendSms;
import com.opensymphony.xwork2.ActionContext;
/**
 * 商品订单处理
 * @author lihao
 *
 */
public class GoodsOrderManager extends NewBaseAction{

	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 商品信息
	 */
	private B2cGoods b2cGoods;
	/**
	 * 商品订单信息
	 */
	private B2cGoodsOrder b2cGoodsOrder;
	/**
	 * 商品订单信息
	 */
	private B2cGoodsOrderDetail b2cGoodsOrderDetail;
	/**
	 * 送货方式列表
	 */
	private List<B2cGoodsDeliver> b2cGoodsDeliverList = new ArrayList<>();
	/**
	 * 地址列表
	 */
	private List<B2cAddress> b2cAddressList = new ArrayList<>();
	/**
	 * 订单列表
	 */
	private List<B2cGoodsOrder> b2cGoodsOrderList = new ArrayList<>();
	private B2cAddress b2cAddress;
	
	public String execute(){
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		if(userId == null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 获取所有送货方式信息
		 */
		if(action.equals("getB2cGoodsDeliverList")){
			this.b2cGoodsDeliverList = this.goodsOrderService.getB2cGoodsDeliverList(null);
		}
		/**
		 * 获取收货地址列表
		 */
		else if(action.equals("getB2cAddressList")){
			UserInfo userInfo = new UserInfo();
			userInfo.setId(userId);
			this.b2cAddressList = this.goodsOrderService.getB2cAddressList(userInfo,null);
			//对地址列表排序
			if(b2cAddressList != null){
				 Collections.sort(b2cAddressList, new Comparator<B2cAddress>() {  
		            public int compare(B2cAddress arg0, B2cAddress arg1) {  
		                int hits0 = "Y".equals(arg0.getIsMain())?1:0;
		            	int hits1 = "Y".equals(arg1.getIsMain())?1:0;
		                if (hits1 > hits0) {  
		                    return 1;  
		                } else if (hits1 == hits0) {  
		                    return 0;  
		                } else {  
		                    return -1;  
		                }  
		            }  
		        });
			}
		}
		/**
		 * 获取收货地址
		 */
		else if(action.equals("getB2cAddressById")){
			this.b2cAddress = this.goodsOrderService.getB2cAddressById(b2cAddress.getId());
		}
		/**
		 * 保存更新收货地址列表
		 */
		else if(action.equals("saveOrUpdateB2cAddress")){
			UserInfo userInfo = new UserInfo();
			userInfo.setId(userId);
			if(b2cAddress != null){
				b2cAddress.setUserInfo(userInfo);
				Long id = this.goodsOrderService.saveOrUpdateB2cAddress(b2cAddress);
				//修改其他地址信息
				if("Y".equals(b2cAddress.getIsMain()) && !"Y".equals(b2cAddress.getIsDel())){
					List<B2cAddress> b2cAddressList = this.goodsOrderService.getB2cAddressList(userInfo,null);
					if(b2cAddressList != null){
						for(B2cAddress address : b2cAddressList){
							if(id.longValue() != address.getId().longValue()){
								address.setIsMain("N");
								this.goodsOrderService.saveOrUpdateB2cAddress(address);
							}
						}
					}
				}
			}
		}
		/**
		 * 购买处理（订单及详细信息）
		 */
		else if(action.equals("handleB2cGoodsOrder")){
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			//保存订单
			if(b2cGoodsOrder.getId() == null) { //保存添加
				String orderNum = String.valueOf(System.currentTimeMillis());
				b2cGoodsOrder.setOrderNum(orderNum);
				b2cGoodsOrder.setUserInfo(userInfo);
				b2cGoodsOrder.setOrderStatus("未付款");
				b2cGoodsOrder.setOrderType("单品订单");
				b2cGoodsOrder.setIsDel("N");
				b2cGoodsOrder.setSubmitTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				b2cGoodsOrder.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				if(b2cGoodsOrderDetail != null){
					b2cGoodsOrder.getB2cGoodsOrderDetailSet().add(b2cGoodsOrderDetail);
				}
				//获取用户默认地址
				List<B2cAddress> b2cAddressList = this.goodsOrderService.getB2cAddressList(userInfo,"Y");
				if(b2cAddressList != null){
					B2cAddress address = b2cAddressList.get(0);
					b2cGoodsOrder.setBaddress(address.getAddress());
					b2cGoodsOrder.setBphone(address.getPhone());
					b2cGoodsOrder.setBconsignee(address.getReceiver());
				}
				Long id = this.goodsOrderService.saveOrUpdateB2cGoodsOrder(b2cGoodsOrder);
				b2cGoodsOrder.setId(id);
				//查看商品是否有促销信息（预售）
				List<B2cGoodsPresaleModel> b2cGoodsPresaleModelList = 
						this.goodsService.getB2cGoodsPresaleModelListByGoodsId(b2cGoodsOrderDetail.getB2cGoods().getId());
				if(b2cGoodsPresaleModelList != null){//有预售信息
					B2cGoodsOrderPromotion b2cGoodsOrderPromotion = new B2cGoodsOrderPromotion();
					b2cGoodsOrderPromotion.setDiscountMoney(b2cGoodsPresaleModelList.get(0).getPrePrice());
					b2cGoodsOrderPromotion.setOrderId(id);
					b2cGoodsOrderPromotion.setPriceStatus("未付款");
					this.goodsOrderService.saveOrUpdateB2cGoodsOrderPromotion(b2cGoodsOrderPromotion);
					//更新订单类型
					B2cGoodsOrder oldOrder = this.goodsOrderService.getB2cGoodsOrderById(id);
					oldOrder.setOrderType("预售订单");
					this.goodsOrderService.saveOrUpdateB2cGoodsOrder(oldOrder);
				}
			}else{
				B2cGoodsOrder oldOrder = this.goodsOrderService.getB2cGoodsOrderById(b2cGoodsOrder.getId());
				oldOrder.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				if("paySuccess".equals(b2cGoodsOrder.getOrderStatus())){
					oldOrder.setPayMethod(b2cGoodsOrder.getPayMethod());
					oldOrder.setUseRemainBalance(b2cGoodsOrder.getUseRemainBalance());
					oldOrder.setBaddress(b2cGoodsOrder.getBaddress());
					oldOrder.setBphone(b2cGoodsOrder.getBphone());
					oldOrder.setBconsignee(b2cGoodsOrder.getBconsignee());
					oldOrder.setTotalPrice(b2cGoodsOrder.getTotalPrice());
					oldOrder.setRealPrice(b2cGoodsOrder.getRealPrice());
					oldOrder.setB2cGoodsDeliver(b2cGoodsOrder.getB2cGoodsDeliver());
					oldOrder.setPostage(b2cGoodsOrder.getPostage());
					if(b2cGoodsOrderDetail != null){
						Iterator<B2cGoodsOrderDetail> it = oldOrder.getB2cGoodsOrderDetailSet().iterator();
						while (it.hasNext()) {
							B2cGoodsOrderDetail b2cGoodsOrderDetailOld = (B2cGoodsOrderDetail) it.next();
							b2cGoodsOrderDetailOld.setGoodsNum(b2cGoodsOrderDetail.getGoodsNum());
						}
					}
					// 更新订单的信息
					if(!oldOrder.getB2cGoodsOrderPromotionSet().isEmpty() && "未付款".equals(oldOrder.getOrderStatus())){//预售订单处理
						B2cGoodsOrderPromotion b2cGoodsOrderPromotion = 
								oldOrder.getB2cGoodsOrderPromotionSet().iterator().next();
						if("未付款".equals(b2cGoodsOrderPromotion.getPriceStatus())){
							oldOrder.setOrderStatus("预付款");
							b2cGoodsOrderPromotion.setPriceStatus("已付款");
							//更新预付信息
							this.goodsOrderService.saveOrUpdateB2cGoodsOrderPromotion(b2cGoodsOrderPromotion);
						}
					}else{//正式订单处理
						oldOrder.setOrderStatus("已付款");
					}
					//用户用钱处理
					this.handleUserMoney(oldOrder, userInfo);
					this.goodsOrderService.saveOrUpdateB2cGoodsOrder(oldOrder);
					//发送短信通知
					SendSms ss = new SendSms();
					String contecnt = "亲爱的用户，您于"+DateManage.getDateStr("yyyy年MM月dd日HH时mm分")+"下单成功,订单编号为："+oldOrder.getOrderNum()+"。详情请查'商城订单'";
					ss.sendInfo(userInfo.getPhone(), contecnt.toString());
			}
				//取消订单
			else if("cancle".equals(b2cGoodsOrder.getOrderStatus())){
				//判断订单状态，已发货以后的状态不能取消订单
				if(goodsOrderService.NO_CANCLE_STATUS.indexOf(oldOrder.getOrderStatus()) > -1){
					this.mes = "【" + oldOrder.getOrderStatus() + "】订单不能取消";
					return "fail";
				}
				String orderStatus = ConstantManage.USER_CANCLE_CLINIC_ORDER;
				Double realPrice = 0D;
				//处理退款
				if("预付款".equals(oldOrder.getOrderStatus())){
					if(!oldOrder.getB2cGoodsOrderPromotionSet().isEmpty()){//预售订单处理
						B2cGoodsOrderPromotion b2cGoodsOrderPromotion = 
								oldOrder.getB2cGoodsOrderPromotionSet().iterator().next();
						b2cGoodsOrderPromotion.setPriceStatus(orderStatus);
						//更新预付信息
						this.goodsOrderService.saveOrUpdateB2cGoodsOrderPromotion(b2cGoodsOrderPromotion);
						B2cGoodsOrderDetail b2cGoodsOrderDetailOld = oldOrder.getB2cGoodsOrderDetailSet().iterator().next();
						Double prePrice = b2cGoodsOrderPromotion.getDiscountMoney()==null?0d:Double.valueOf(b2cGoodsOrderPromotion.getDiscountMoney()) ;
						//把预付定金放在余额里处理，方便退款处理
						realPrice = prePrice*b2cGoodsOrderDetailOld.getGoodsNum();
					}
				}else if("已付款".equals(oldOrder.getOrderStatus())){
					realPrice = oldOrder.getTotalPrice()==null?0d:Double.valueOf(oldOrder.getTotalPrice());
				}
				oldOrder.setOrderStatus(orderStatus);
				this.goodsOrderService.saveOrUpdateB2cGoodsOrder(oldOrder);
				//添加用户用钱记录
				if(realPrice.doubleValue() != 0D){
					Double accountBalance = userInfo.getAccountBalance();
					accountBalance = accountBalance.doubleValue()+realPrice.doubleValue();
					accountBalance = MyMath.round(accountBalance, 2);
					userInfo.setAccountBalance(accountBalance);
					this.userInfoBo.updateUser(userInfo);
					this.userAccountBo.addNewUserAccount(userId, realPrice, "+", "健康商城订单退款", oldOrder.getOrderNum());
				}
				String toDoctorSms = "亲爱的用户，您于"+DateManage.getDateStr("yyyy年MM月dd日HH时mm分")+"取消订单："+oldOrder.getOrderNum()+"。详情请查'商城订单'";
				System.out.print("cancle =="+toDoctorSms);
				SendSms sendSms = new SendSms();
				sendSms.sendInfo(userInfo.getPhone(), toDoctorSms);
			}
				//删除订单
			else if("delete".equals(b2cGoodsOrder.getOrderStatus())){
				oldOrder.setIsDel("Y");
				this.goodsOrderService.saveOrUpdateB2cGoodsOrder(oldOrder);
			}
			}
		}
		/**
		 * 订单直接支付成功
		 */
		else if(action.equals("paySuccessOrder")){
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			b2cGoodsOrder.setUserInfo(userInfo);
			b2cGoodsOrder.setIsDel("N");
			b2cGoodsOrder.setSubmitTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			b2cGoodsOrder.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			if(b2cGoodsOrderDetail != null){
				b2cGoodsOrder.getB2cGoodsOrderDetailSet().add(b2cGoodsOrderDetail);
			}
			//查看商品是否有促销信息（预售）
			List<B2cGoodsPresaleModel> b2cGoodsPresaleModelList = 
					this.goodsService.getB2cGoodsPresaleModelListByGoodsId(b2cGoodsOrderDetail.getB2cGoods().getId());
			if(b2cGoodsPresaleModelList != null){//有预售信息
				b2cGoodsOrder.setOrderType("预售订单");
				b2cGoodsOrder.setOrderStatus("预付款");
			}else{
				b2cGoodsOrder.setOrderStatus("已付款");
				b2cGoodsOrder.setOrderType("单品订单");
			}
			Long id = this.goodsOrderService.saveOrUpdateB2cGoodsOrder(b2cGoodsOrder);
			b2cGoodsOrder.setId(id);
			if(b2cGoodsPresaleModelList != null){//有预售信息
				B2cGoodsOrderPromotion b2cGoodsOrderPromotion = new B2cGoodsOrderPromotion();
				b2cGoodsOrderPromotion.setDiscountMoney(b2cGoodsPresaleModelList.get(0).getPrePrice());
				b2cGoodsOrderPromotion.setOrderId(id);
				b2cGoodsOrderPromotion.setPriceStatus("已付款");
				this.goodsOrderService.saveOrUpdateB2cGoodsOrderPromotion(b2cGoodsOrderPromotion);
			}
			//用户用钱处理
			this.handleUserMoney(b2cGoodsOrder, userInfo);
		}
		/**
		 * 获取订单信息
		 */
		else if(action.equals("getB2cGoodsOrder")){
			this.b2cGoodsOrder = this.goodsOrderService.getB2cGoodsOrderById(b2cGoodsOrder.getId());
			if(b2cGoodsOrder != null){
				List<GoodsPropValSetFo> foList = new ArrayList<>();
				Iterator<B2cGoodsOrderDetail> detailIt = b2cGoodsOrder.getB2cGoodsOrderDetailSet().iterator();
				while(detailIt.hasNext()){
					B2cGoodsOrderDetail detail = detailIt.next();
					if(StringUtils.isNotEmpty(detail.getPropVals())){
						String[] names = detail.getPropVals().split(",");
						for (int i = 0; i < names.length; i++) {
							GoodsPropValSetFo propValFo = new GoodsPropValSetFo();
							String name_prop_val = names[i];
							propValFo.setIsSku("Y");
							propValFo.setName_prop_val(name_prop_val);
							foList.add(propValFo);
						}
					}
					List<B2cGoodsBasicPropvalSet> propValSetList =
							this.goodsService.getB2cGoodsBasicPropvalSetListByGoodsId(detail.getB2cGoods().getId());
					if(propValSetList != null){
						for(B2cGoodsBasicPropvalSet propValSet : propValSetList ){
							GoodsPropValSetFo propValFo = new GoodsPropValSetFo();
							String id_prop_val = propValSet.getB2cGoodsProperty().getId() + ":" + propValSet.getB2cGoodsPropertyValue().getId();
							String name_prop_val = propValSet.getB2cGoodsProperty().getPropName() + ":" + propValSet.getB2cGoodsPropertyValue().getValName();
							propValFo.setIsSku(propValSet.getIsSku());
							propValFo.setId_prop_val(id_prop_val);
							propValFo.setName_prop_val(name_prop_val);
							if("Y".equals(propValSet.getIsSku())){
								
							}else{
								foList.add(propValFo);
							}
						}
					}
				}
				b2cGoodsOrder.setPropValFoList(foList);
				//得到当前用户的测评使用次数
				UserInfo userInfo = this.userInfoBo.getUserById(userId);
				Long usedCount = this.asqService.getTestCountByUser(userInfo,b2cGoodsOrder.getId());
				long buyCount = this.goodsService.goodsServicesCount(b2cGoodsOrder);
				long surplusCount = buyCount - usedCount;
				b2cGoodsOrder.setSurplusCount(surplusCount<=0L?0L:surplusCount);
			}
			//得到送货方式
			this.b2cGoodsDeliverList = this.goodsOrderService.getB2cGoodsDeliverList(null);
		}
		/**
		 * 获取订单列表
		 */
		else if(action.equals("getB2cGoodsOrderList")){
			UserInfo userInfo = this.userInfoBo.getUserById(userId);
			B2cGoodsOrder b2cGoodsOrder = new B2cGoodsOrder();
			b2cGoodsOrder.setUserInfo(userInfo);
			this.b2cGoodsOrderList = this.goodsOrderService.getB2cGoodsOrderList(b2cGoodsOrder);
			if(b2cGoodsOrderList != null){
				for(B2cGoodsOrder goodsOrder : b2cGoodsOrderList){
					//得到当前用户的测评使用次数
					Long usedCount = this.asqService.getTestCountByUser(userInfo,goodsOrder.getId());
					long buyCount = this.goodsService.goodsServicesCount(b2cGoodsOrder);
					long surplusCount = buyCount - usedCount;
					goodsOrder.setSurplusCount(surplusCount<=0L?0L:surplusCount);
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * 处理用户用钱明细
	 * @param b2cGoodsOrder
	 */
	private void handleUserMoney(B2cGoodsOrder oldOrder,UserInfo userInfo){
		//用户使用余额
		double useRemainBalanceCalc = oldOrder.getUseRemainBalance()==null?0D:oldOrder.getUseRemainBalance().doubleValue();
		//最终需要支付的金额
		double chargeBalance = oldOrder.getRealPrice()==null?0D:oldOrder.getRealPrice().doubleValue();
		chargeBalance = chargeBalance<=0D?0D:chargeBalance;
		chargeBalance = MyMath.round(chargeBalance, 2);
		//更新用户账户余额信息
		UserInfo currentUserInfo = this.userInfoBo.getUserById(userInfo.getId());
		double accountBalance = currentUserInfo.getAccountBalance();//用户账户的余额
		double balance = accountBalance - useRemainBalanceCalc;
		balance = MyMath.round(balance, 2);
		currentUserInfo.setAccountBalance(balance<0d?0d:balance);
		this.userInfoBo.updateUser(currentUserInfo);//更新用户余额
		//添加用户余额支付明细
		if (useRemainBalanceCalc !=  0D) {
			this.userAccountBo.addNewUserAccount(currentUserInfo.getId(), useRemainBalanceCalc, "-", "余额支付健康商城订单", oldOrder.getOrderNum());
		}
		//在线支付明细记录
		if(chargeBalance != 0D){
			this.userAccountBo.addNewUserAccount(currentUserInfo.getId(), chargeBalance, "-", "在线支付健康商城订单", oldOrder.getOrderNum());
		}
	}
	public B2cGoods getB2cGoods() {
		return b2cGoods;
	}
	public void setB2cGoods(B2cGoods b2cGoods) {
		this.b2cGoods = b2cGoods;
	}
	public String getMes() {
		return mes;
	}
	public B2cGoodsOrder getB2cGoodsOrder() {
		return b2cGoodsOrder;
	}
	public void setB2cGoodsOrder(B2cGoodsOrder b2cGoodsOrder) {
		this.b2cGoodsOrder = b2cGoodsOrder;
	}
	public List<B2cGoodsDeliver> getB2cGoodsDeliverList() {
		return b2cGoodsDeliverList;
	}
	public B2cAddress getB2cAddress() {
		return b2cAddress;
	}
	public void setB2cAddress(B2cAddress b2cAddress) {
		this.b2cAddress = b2cAddress;
	}
	public List<B2cAddress> getB2cAddressList() {
		return b2cAddressList;
	}
	public B2cGoodsOrderDetail getB2cGoodsOrderDetail() {
		return b2cGoodsOrderDetail;
	}
	public void setB2cGoodsOrderDetail(B2cGoodsOrderDetail b2cGoodsOrderDetail) {
		this.b2cGoodsOrderDetail = b2cGoodsOrderDetail;
	}
	public List<B2cGoodsOrder> getB2cGoodsOrderList() {
		return b2cGoodsOrderList;
	}
}
