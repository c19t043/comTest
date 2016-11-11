package com.kybaby.newbussiness.ordermanager.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;












import net.sf.json.JSONArray;
import net.sf.json.JSONObject;















import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.HealthPath;
import com.kybaby.domain.ItemResult;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.OrderResults;
import com.kybaby.domain.Product;
import com.kybaby.domain.ProductItem;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.doctorring.action.NewBaseAction;
import com.kybaby.newbussiness.doctorring.util.DateManage;
import com.kybaby.newbussiness.ordermanager.domain.BabyBasicData2;
import com.kybaby.newbussiness.ordermanager.domain.DoctorSignRecord;
import com.kybaby.newbussiness.ordermanager.domain.HealthPlanRemindIssued;
import com.kybaby.newbussiness.ordermanager.domain.OperationFlowNode;
import com.kybaby.newbussiness.ordermanager.domain.OrderNodeTrack;
import com.kybaby.newbussiness.ordermanager.fo.FlowNodeFo;
import com.kybaby.newbussiness.ordermanager.fo.ItemAndResultFo;
import com.opensymphony.xwork2.ActionContext;
/**
 * 重构的订单管理类
 * @author lihao
 *
 */
public class OrderManagerAction extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	private String mes;
	/**
	 * 医生信息
	 */
	private DoctorInfo doctorInfo;
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 医生签到记录
	 */
	private DoctorSignRecord doctorSignRecord;
	/**
	 * 订单信息
	 */
	private OrderInfo orderInfo;
	/**
	 * 产品信息
	 */
	private Product product;
	/**
	 * 健康档案信息
	 */
	private BabyBasicData2 babyBasicData;
	/**
	 * 订单执行节点记录信息
	 */
	private OrderNodeTrack orderNodeTrack;
	/**
	 * 订单结果记录信息json字符串
	 */
	private String orderResultsJson;
	/**
	 * 流程节点，项目结果集表单对象
	 */
	private List<FlowNodeFo> flowNodeFoList = new ArrayList<FlowNodeFo>();
	/**
	 * 项目结果集表单对象
	 */
	private List<ItemAndResultFo>  itemAndResultFoList = new ArrayList<ItemAndResultFo>();;
	/**
	 * 流程节点集合
	 */
	List<OperationFlowNode> operationFlowNodeList = new ArrayList<OperationFlowNode>(); 
	/**
	 * 健康指导集合
	 */
	private List<HealthPath> healthPathList = new ArrayList<HealthPath>();
	/**
	 * 健康指导记录json字符串
	 */
	private String healthPlanJson;
	@Override
	public String execute(){
		doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
		if(doctorInfo==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 进入订单（开始判断是否签到，是否录了项目结果，从而展示不同的数据）
		 */
		if(action.equals("toOrder")){
		
			//判断是否签到
			Long orderId = orderInfo.getId();
			OrderInfo order = this.orderBo.getOrderByOrderId(orderId);
			this.orderInfo = order;
			this.userInfo = this.orderBo.getUserByUserId(order.getUserId());
			String orderStatus = order.getOrderStatus();
			this.product = this.orderBo.getProductById(order.getProductId());
			//doctorSignRecord = orderManagerService.getDoctorSignRecordByOrderId(orderId);
			if("已接单".equals(orderStatus)){
				if(product.getFlowBasicId() == null){//没有挂流程的产品处理
					//this.getItemAndResultFoNoFlow();
				}else{
					operationFlowNodeList = this.operationFlowService.getOperationFlowNodeListByBasicId(product.getFlowBasicId());
				}
				mes="进签到";
			}else if("已签到,操作中".indexOf(orderStatus) > -1){
				if(product.getFlowBasicId() == null){//没有挂流程的产品处理
					//this.getItemAndResultFoNoFlow();
				}
				//是否录了项目结果
				//流程节点记录里查询
				List<OrderNodeTrack> list = operationFlowService.getOrderNodeTrackListByOrderId(orderId);
				if(list == null){
					Long userId = order.getUserId();
					List<BabyBasicData2> babyList = orderManagerService.getBabyBasicData2ListByUserId(userId);
					if(babyList != null){
						babyBasicData = babyList.get(0);
					}
					mes="进健康档案";
				}else{//定位到上次操作节点
					orderNodeTrack = list.get(0);
					Product pro = this.orderBo.getProductById(order.getProductId());
					List<OperationFlowNode> flowNodeList = this.operationFlowService.getOperationFlowNodeListByBasicId(pro.getFlowBasicId());
					this.flowNodeFoList = this.getItemResultFo(flowNodeList, order);
					mes="进流程节点";
				}
			}else if("已完成".equals(orderStatus)){
				
			}else if("已确认".equals(orderStatus)){
				
			}else if("已评价".equals(orderStatus)){
				
			}
		}
		/**
		 * 保存完健康档案后进结果录入
		 */
		else if(action.equals("getItemResult")){
			Long orderId = orderInfo.getId();
			OrderInfo order = this.orderBo.getOrderByOrderId(orderId);
			Product pro = this.orderBo.getProductById(order.getProductId());
			List<OperationFlowNode> flowNodeList = this.operationFlowService.getOperationFlowNodeListByBasicId(pro.getFlowBasicId());
			this.flowNodeFoList = this.getItemResultFo(flowNodeList, order);
			mes="成功";
		}
		/**
		 * 得到宝宝基础档案信息
		 */
		else if(action.equals("getBabyBasicData")){
			Long orderId = orderInfo.getId();
			OrderInfo order = this.orderBo.getOrderByOrderId(orderId);
			Long userId = order.getUserId();
			List<BabyBasicData2> babyList = orderManagerService.getBabyBasicData2ListByUserId(userId);
			if(babyList != null){
				babyBasicData = babyList.get(0);
			}
			mes="成功";
		}
		/**
		 * 医生取消
		 */
		else if(action.equals("cancel")){
			Long orderId = orderInfo.getId();
			OrderInfo orderInf = orderBo.getOrderByOrderId(orderId);
			if(orderInf!=null){
				orderInf.setOrderStatus("医生取消");
				orderInf.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				orderBo.update(orderInf);
				mes="成功";
				return "success";
			}
			mes="无数据";
			return "fail";
		}
		/**
		 * 医生签到
		 */
		else if(action.equals("doctorSign")){
			Long orderId = orderInfo.getId();
			OrderInfo order = this.orderBo.getOrderByOrderId(orderId);
			UserInfo userInfo = new UserInfo();
			userInfo.setId(order.getUserId());
			//保存签到信息
			doctorSignRecord.setOrderInfo(order);
			doctorSignRecord.setDoctorInfo(doctorInfo);
			doctorSignRecord.setUserInfo(userInfo);
			orderManagerService.saveOrUpdateDoctorSignRecord(doctorSignRecord);
			//更新订单信息
			order.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			order.setOrderStatus("已签到");
			orderBo.update(order);
			mes="签到成功";
		}
		/**
		 * 保存或更新健康档案
		 */
		else if(action.equals("saveOrUpdateBabyBasicData")){
			Long orderId = orderInfo.getId();
			OrderInfo order = this.orderBo.getOrderByOrderId(orderId);
			babyBasicData.setUserId(order.getUserId());
			babyBasicData.setDoctorId(order.getDoctorId());
			orderManagerService.saveOrUpdateBabyBasicData(babyBasicData);
			order.setOrderStatus("操作中");
			orderBo.update(order);
			mes="成功";
		}
		/**
		 * 跳转流程节点（保存项目结果信息）
		 */
		else if(action.equals("jumpFlowNode")){
			Long orderId = orderInfo.getId();
			OrderInfo orderInf = this.orderBo.getOrderByOrderId(orderId);
			JSONArray array = JSONArray.fromObject(orderResultsJson); 
			System.out.println(array);
			List<OrderResults> orderResults = new ArrayList<OrderResults>();
			for(int i = 0; i < array.size(); i++){ 
				OrderResults orderResult = new OrderResults();
				JSONObject jo = array.getJSONObject(i);
				String resultValueId = jo.get("resultValueId")==null?"":jo.get("resultValueId").toString().trim();
				orderResult.setId("".equals(resultValueId)?null:Long.valueOf(resultValueId));
				orderResult.setComments("");
				orderResult.setDoctorId(orderInf.getDoctorId());
				Long itemId = jo.get("itemId")==null?null:Long.valueOf(jo.get("itemId").toString().trim());
				orderResult.setItemId(itemId);
				orderResult.setItemResultName(jo.get("itemResultName")==null?"":String.valueOf(jo.get("itemResultName")));
				orderResult.setOrderNum(orderInf.getOrderNum());
				//orderResults.setPlanId(someStr[5]);
				orderResult.setProductId(orderInf.getProductId());
				orderResult.setResultUnit(jo.get("resultUnit")==null?"":jo.get("resultUnit").toString().trim());//结果单位
				orderResult.setResultValue(jo.get("resultValue")==null?"":jo.get("resultValue").toString().trim());//结果值
				orderResult.setResultRemark(jo.get("resultRemark")==null?"":jo.get("resultRemark").toString().trim());//结果备注
				orderResult.setUserId(orderInf.getUserId());
				orderResult.setWriteDate(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
				orderResults.add(orderResult);
			}
			//保存项目结果信息
			this.orderManagerService.saveOrUpdateOrderResults(orderResults,orderInf);
			this.orderManagerService.saveOrUpdateOrderNodeTrack(orderNodeTrack);
			orderInf.setOrderStatus("操作中");
			orderBo.update(orderInf);
			mes="成功";
		}
		/**
		 * 医生完成订单
		 */
		else if(action.equals("finishOrder")){
			Long orderId = orderInfo.getId();
			OrderInfo orderInf = this.orderBo.getOrderByOrderId(orderId);
			System.out.println("orderInf======"+orderInf.getId() + "num=="+orderInf.getOrderNum());
			orderInf.setOrderStatus("已完成");
			orderInf.setUpdateTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			orderBo.update(orderInf);
			mes="成功";
		}
		/**
		 * 得到流程节点信息
		 */
		else if(action.equals("getFlowNode")){
			System.out.println("begain getFlowNode...");
			Long orderId = orderInfo.getId();
			OrderInfo orderInf = this.orderBo.getOrderByOrderId(orderId);
			Product pro = this.orderBo.getProductById(orderInf.getProductId());
			List<OperationFlowNode> flowNodeList = this.operationFlowService.getOperationFlowNodeListByBasicId(pro.getFlowBasicId());
			this.flowNodeFoList = this.getItemResultFo(flowNodeList, orderInf);
			
			System.out.println("begain getFlowNode...==="+pro.getFlowBasicId()+"   flowNodeFoList.size="+flowNodeFoList.size());
			mes="成功";
		}
		/**
		 * 进健康指导
		 */
		else if(action.equals("getHealthInstruction")){
			Long orderId = orderInfo.getId(); 
			OrderInfo orderInf = this.orderBo.getOrderByOrderId(orderId);
			UserInfo userInfo = this.orderBo.getUserByUserId(orderInf.getUserId());
			this.healthPathList = this.orderManagerService.getHealthPathByUserInfo(userInfo);
			mes="成功";
		}
		/**
		 * 保存健康指导
		 */
		else if(action.equals("saveOrUpdateHealthInstruction")){
			Long orderId = orderInfo.getId();
			OrderInfo orderInf = this.orderBo.getOrderByOrderId(orderId);
			this.orderManagerService.delHealthPlanRemindIssued(orderInf.getOrderNum());
			JSONArray array = JSONArray.fromObject(this.healthPlanJson); 
			if(array.size() == 0){
				mes="成功";
				return "fail";
			}
			for(int i = 0; i < array.size(); i++){ 
				JSONObject jo = array.getJSONObject(i);
				HealthPlanRemindIssued healthPlanRemindIssued = 
						new HealthPlanRemindIssued(orderInf.getUserId(), orderInf.getOrderNum(), null, new Date(), 0L, null);
				String id = jo.get("id")==null?null:jo.get("id").toString().trim();
				if(id == null || "".equals(id)){
					healthPlanRemindIssued.setId(null);
				}else{
					healthPlanRemindIssued.setId(Long.valueOf(id));
				}
				String healthPathId = jo.get("healthPathId")==null?"":jo.get("healthPathId").toString().trim();
				String healthPlanId = jo.get("healthPlanId")==null?"":jo.get("healthPlanId").toString().trim();
				healthPlanRemindIssued.setHealthPathId("".equals(healthPathId)?null:Long.valueOf(healthPathId));
				healthPlanRemindIssued.setHealthPlanId("".equals(healthPlanId)?null:Long.valueOf(healthPlanId));
				this.orderManagerService.saveOrUpdateHealthPlanRemindIssued(healthPlanRemindIssued);
			}
			mes="成功";
		}
		return "success";
	}
	/**
	 * 得到流程节点，项目结果集合表单对象
	 * @param flowNodeList
	 * @param order
	 * @return
	 */
	private List<FlowNodeFo> getItemResultFo(List<OperationFlowNode> flowNodeList,OrderInfo order){
		UserInfo userInfo = this.orderBo.getUserByUserId(order.getUserId());
		List<FlowNodeFo>  foList = new ArrayList<FlowNodeFo>();
		for(OperationFlowNode flowNode : flowNodeList){
			List<ItemAndResultFo>  irList = new ArrayList<ItemAndResultFo>();
			FlowNodeFo flowNodeFo = new FlowNodeFo();
			flowNodeFo.setFlowNodeId(flowNode.getFlowNodeId());
			flowNodeFo.setFlowNodeName(flowNode.getFlowNodeName());
			flowNodeFo.setFlowNodeRemark(flowNode.getFlowNodeRemark());
			//得到节点下的项目结果录入信息
			List<OrderResults> orderResultsList_ = operationFlowService.
					getOrderResultByOrderNumAndNodeId(order.getOrderNum(), flowNode.getFlowNodeId());
				//根据流程节点id得到该节点下的项目结果录入信息
			List<ItemResult> itemResultList_ = operationFlowService.
						getItemResultByProductIdAndNodeId(flowNode.getFlowNodeId(),userInfo);
			if(itemResultList_ != null){
				for(ItemResult ir : itemResultList_){
					ItemAndResultFo itemFo = new ItemAndResultFo();
					Long resultId = ir.getId();
					ProductItem item = this.orderManagerService.getItemByResultId(resultId);
					String itemId = item.getId().toString();
					itemFo.setItemResultName(ir.getItemResultName());
					itemFo.setResultRemarkFlag(ir.getResultRemarkFlag());
					itemFo.setResultType(ir.getResultType());
					itemFo.setOptions(ir.getOptions());
					itemFo.setResultUnit(ir.getResultUnit());
					itemFo.setItemId(itemId);
					if(orderResultsList_ != null){//有结果值的将需要的值重置回去
						for(OrderResults or : orderResultsList_){
							if(or.getItemId().toString().equals(itemId)){
								itemFo.setResultRemark(or.getResultRemark());
								itemFo.setResultValue(or.getResultValue());
								itemFo.setResultValueId(or.getId().toString());
							}
						}
					}
					irList.add(itemFo);
				}
			}
		
			flowNodeFo.setItemAndResultList(irList);
			foList.add(flowNodeFo);
		}
		return foList;
	}
	/**
	 * 没有挂流程的产品处理
	 */
	private void getItemAndResultFoNoFlow(){
		String[] itemIdListTemp = product.getItemIds().split("::");
		for(int k=0;k<itemIdListTemp.length;k++){
			ProductItem productItem = orderBo.getItemById(Long.valueOf(itemIdListTemp[k]));
			ItemResult ir= orderBo.getItemResultById(productItem.getItemResultId());
			ItemAndResultFo itemFo = new ItemAndResultFo();
			itemFo.setItemId(itemIdListTemp[k]);
			itemFo.setItemResultName(ir.getItemResultName());
			itemFo.setResultRemarkFlag(ir.getResultRemarkFlag());
			itemFo.setResultType(ir.getResultType());
			itemFo.setOptions(ir.getOptions());
			itemFo.setResultUnit(ir.getResultUnit());
			this.itemAndResultFoList.add(itemFo);
		}
	}
	
	
	
	@Override
	public String getMes() {
		return mes;
	}
	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}
	public DoctorSignRecord getDoctorSignRecord() {
		return doctorSignRecord;
	}
	public void setDoctorSignRecord(DoctorSignRecord doctorSignRecord) {
		this.doctorSignRecord = doctorSignRecord;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public BabyBasicData2 getBabyBasicData() {
		return babyBasicData;
	}
	public void setBabyBasicData(BabyBasicData2 babyBasicData) {
		this.babyBasicData = babyBasicData;
	}
	public OrderNodeTrack getOrderNodeTrack() {
		return orderNodeTrack;
	}
	public void setOrderNodeTrack(OrderNodeTrack orderNodeTrack) {
		this.orderNodeTrack = orderNodeTrack;
	}
	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}
	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}
	public String getOrderResultsJson() {
		return orderResultsJson;
	}
	public void setOrderResultsJson(String orderResultsJson) {
		this.orderResultsJson = orderResultsJson;
	}
	public List<FlowNodeFo> getFlowNodeFoList() {
		return flowNodeFoList;
	}
	public void setFlowNodeFoList(List<FlowNodeFo> flowNodeFoList) {
		this.flowNodeFoList = flowNodeFoList;
	}
	public String getHealthPlanJson() {
		return healthPlanJson;
	}
	public void setHealthPlanJson(String healthPlanJson) {
		this.healthPlanJson = healthPlanJson;
	}
	public List<HealthPath> getHealthPathList() {
		return healthPathList;
	}
	public void setHealthPathList(List<HealthPath> healthPathList) {
		this.healthPathList = healthPathList;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public List<OperationFlowNode> getOperationFlowNodeList() {
		return operationFlowNodeList;
	}
	public void setOperationFlowNodeList(
			List<OperationFlowNode> operationFlowNodeList) {
		this.operationFlowNodeList = operationFlowNodeList;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
