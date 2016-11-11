package com.java.familydoctor.userbuyrecord.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.userbuyrecord.service.FdServiceOrderService;
import com.java.familydoctor.userbuyrecord.vo.FdServiceOrder;
import com.java.familydoctor.userbuyrecord.vo.FdUserBuyRecord;
import com.java.platform.core.Action;
import com.java.util.DateManage;

public class FdServiceOrderAction extends Action{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 家庭医生服务订单信息服务方法
	 */
	private FdServiceOrderService fdServiceOrderService;
	
	/**
	 * 家庭医生服务订单信息实体类
	 */
	private FdServiceOrder fdServiceOrder;

	public FdServiceOrderService getFdServiceOrderService() {
		return fdServiceOrderService;
	}

	public void setFdServiceOrderService(FdServiceOrderService fdServiceOrderService) {
		this.fdServiceOrderService = fdServiceOrderService;
	}

	public FdServiceOrder getFdServiceOrder() {
		return fdServiceOrder;
	}

	public void setFdServiceOrder(FdServiceOrder fdServiceOrder) {
		this.fdServiceOrder = fdServiceOrder;
	}
	

	
	/**
	 *  跳转编辑页面
	 */
	public String toJumpFdServiceOrder(){
		if(fdServiceOrder != null && fdServiceOrder.getId() != null){
			this.fdServiceOrder = fdServiceOrderService.getFdServiceOrderById(fdServiceOrder.getId());
		}
		return SUCCESS;
	}
	
	/**
	 *  家庭医生服务订单信息修改方法
	 */
	public String updateFdServiceOrder(){
		FdUserBuyRecord fdUserBuyRecord = new FdUserBuyRecord();
		List<FdUserBuyRecord> fdUserBuyRecordId = 
				fdServiceOrderService.getFdUserBuyRecordIdByFdServiceOrder(fdServiceOrder);
		if(fdUserBuyRecordId == null || fdUserBuyRecordId.isEmpty()){
			//拿到的当前时间，就是后台手动修改已支付的时间
			Date date=new Date();
			//把时间按条件转换
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			//在把时间转换为字符串
			String time=formatter.format(date);
			//设置服务开始的时间
			fdUserBuyRecord.setServiceStartTime(time);
			
			//获取订单金额
			Double totalPrice = fdServiceOrder.getTotalPrice();
			//把订单的金额设置到交易记录的金额里面去
			fdUserBuyRecord.setPayPrice(totalPrice);
			
			//先调用查询方法，把购买对象获取到，在从购买对象里面去拿到服务的时间
			FdServiceOrder fdServiceOrderById = fdServiceOrderService.getFdServiceOrderById(fdServiceOrder.getId());
			//获取有效时长
			String effectiveTime = fdServiceOrderById.getFdServiceTimes().getEffectiveTime();
			//在把字符串的数字转成int类型
			int parseInt = Integer.parseInt(effectiveTime);
			//调用工具的静态方法把支付的时间和有效时长加在一起
			String beforeOrAfter = DateManage.getBeforeOrAfter(parseInt);
			//把服务结束的时间，放入购买记录
			fdUserBuyRecord.setServiceEndTime(beforeOrAfter);
			
			this.fdServiceOrderService.updateFdServiceOrder(fdServiceOrder, fdUserBuyRecord);
		}
		return redirectActionResult("getFdServiceOrderList");
	}
	
	/**
	 * 家庭医生服务订单信息列表方法
	 */
	public String getFdServiceOrderList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(fdServiceOrder == null){
			fdServiceOrder = new FdServiceOrder();
		}
		List<FdServiceOrder> list = this.fdServiceOrderService.getFdServiceOrderByPage(psm, fdServiceOrder);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	
}
