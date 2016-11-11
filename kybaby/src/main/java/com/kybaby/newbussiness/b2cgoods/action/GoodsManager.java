package com.kybaby.newbussiness.b2cgoods.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kybaby.action.NewBaseAction;
import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoods;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsBanner;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsBasicPropvalSet;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsDeliver;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrder;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrderDetail;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsPresaleModel;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsProperty;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsSku;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsType;
import com.opensymphony.xwork2.ActionContext;
/**
 * 商品信息处理
 * @author lihao
 *
 */
public class GoodsManager extends NewBaseAction{

	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 商品信息列表
	 */
	private List<B2cGoods> goodsList = new ArrayList<>();
	/**
	 * 商品分类信息列表
	 */
	private List<B2cGoodsType> goodsTypeList = new ArrayList<>();
	/**
	 * 商品banner集合
	 */
	private List<B2cGoodsBanner> goodsBannerList = new ArrayList<>();
	/**
	 * 得到销售属性列表
	 */
	private List<B2cGoodsSku> goodsSkuList = new ArrayList<>();
	/**
	 * 商品信息
	 */
	private B2cGoods b2cGoods;
	/**
	 * 销售属性集合
	 */
	private List<B2cGoodsProperty> propertyList = new ArrayList<B2cGoodsProperty>();
	/**
	 * 普通属性集合
	 */
	private List<B2cGoodsProperty> propertyList_normal = new ArrayList<B2cGoodsProperty>();
	/**
	 * 送货方式列表
	 */
	private List<B2cGoodsDeliver> b2cGoodsDeliverList = new ArrayList<>();
	private UserInfo userInfo;
	private String orderNum;
	
	public String execute(){
		/**
		 * 获取所有在售商品集合
		 */
		if(action.equals("getAllGoods")){
			this.goodsTypeList = this.goodsService.getAllB2cGoodsType(null);
			//this.goodsList = this.goodsService.getAllB2cGoods(b2cGoods);
			if(goodsTypeList != null){
				for(B2cGoodsType type : goodsTypeList){
					Iterator<B2cGoods> b2cGoodsIt = type.getB2cGoodsSet().iterator();
					while (b2cGoodsIt.hasNext()) {
						B2cGoods goods = b2cGoodsIt.next();

						//判断是否预售商品
						List<B2cGoodsPresaleModel> b2cGoodsPresaleModelList = 
								this.goodsService.getB2cGoodsPresaleModelListByGoodsId(goods.getId());
						if(b2cGoodsPresaleModelList != null){
							goods.setB2cGoodsPresaleModel(b2cGoodsPresaleModelList.get(0));
						}
						//得到销量
						String flag = goodsOrderService.LIMIT_STATUS;
						long saleCount = 0;
						List<B2cGoodsOrderDetail> goodsOrderDetailList = this.goodsOrderService.getB2cGoodsOrderDetailList(null, goods, null);
						if(goodsOrderDetailList != null){
							for(B2cGoodsOrderDetail orderDetail : goodsOrderDetailList){
								if(flag.indexOf(orderDetail.getB2cGoodsOrder().getOrderStatus()) > -1){
									saleCount += orderDetail.getGoodsNum();
								}
							}
						}
						goods.setSaleCount(saleCount+"");
					}
				}
			}
		}
		/**
		 * 得到商品明细信息
		 */
		else if(action.equals("getGoodsSomeThing")){
			Long goodsId = b2cGoods.getId();
			//banner图列表
			this.goodsBannerList = this.goodsService.getB2cGoodsBannerListByGoodsId(goodsId);
			//商品sku列表
			this.goodsSkuList = this.goodsService.getB2cGoodsSkuListByGoodsId(goodsId);
			//商品主信息
			this.b2cGoods = this.goodsService.getB2cGoodsById(goodsId);
			//产品属性配置列表
			List<B2cGoodsBasicPropvalSet> propSetList = this.goodsService.getB2cGoodsBasicPropvalSetListByGoodsId(goodsId);
			if(propSetList != null){
				Map<Long,B2cGoodsProperty> propMap = new HashMap<>();
				for(B2cGoodsBasicPropvalSet itProp : propSetList){
					if(itProp.getB2cGoodsSku() != null){
						propMap.put(itProp.getB2cGoodsProperty().getId(), itProp.getB2cGoodsProperty());
					}else{
						this.propertyList_normal.add(itProp.getB2cGoodsProperty());
					}
				}
				for(Map.Entry<Long,B2cGoodsProperty> entry : propMap.entrySet()){
					propertyList.add(entry.getValue());
				}
			}
			//得到运费（默认运输方式价格）
			if("Y".equals(b2cGoods.getIsFreight())){
				b2cGoods.setDeliverPrice("0");
			}else{
				b2cGoods.setDeliverPrice("0");//默认弄成0元
				B2cGoodsDeliver b2cGoodsDeliver = new B2cGoodsDeliver();
				b2cGoodsDeliver.setIsMain("Y");
				List<B2cGoodsDeliver> deliverList = this.goodsOrderService.getB2cGoodsDeliverList(b2cGoodsDeliver);
				if(deliverList != null){
					B2cGoodsDeliver deliver = deliverList.get(0);
					b2cGoods.setDeliverPrice(deliver.getDmoney()==null?"0":String.valueOf(deliver.getDmoney()));
				}
			}
			//得到销量
			String flag = goodsOrderService.LIMIT_STATUS;
			long saleCount = 0;
			List<B2cGoodsOrderDetail> goodsOrderDetailList = this.goodsOrderService.getB2cGoodsOrderDetailList(null, b2cGoods, null);
			if(goodsOrderDetailList != null){
				for(B2cGoodsOrderDetail orderDetail : goodsOrderDetailList){
					if(flag.indexOf(orderDetail.getB2cGoodsOrder().getOrderStatus()) > -1){
						saleCount += orderDetail.getGoodsNum();
					}
				}
			}
			b2cGoods.setSaleCount(String.valueOf(saleCount));
			//价格区间
			if(goodsSkuList != null){
				if(goodsSkuList.size() == 1){
					b2cGoods.setPriceRange(goodsSkuList.get(0).getPrice().toString());
				}else{
					int lastOne = goodsSkuList.size() -1;
					String priceRange = goodsSkuList.get(0).getPrice().toString() + "-" +
							goodsSkuList.get(lastOne).getPrice().toString();
					b2cGoods.setPriceRange(priceRange);
				}
			}
			//商品预售信息
			//判断是否预售商品
			List<B2cGoodsPresaleModel> b2cGoodsPresaleModelList = 
					this.goodsService.getB2cGoodsPresaleModelListByGoodsId(b2cGoods.getId());
			if(b2cGoodsPresaleModelList != null){
				b2cGoods.setB2cGoodsPresaleModel(b2cGoodsPresaleModelList.get(0));
			}
			//得到送货方式
			this.b2cGoodsDeliverList = this.goodsOrderService.getB2cGoodsDeliverList(null);
			Long userId = (Long) ActionContext.getContext().getSession().get("userId");
			this.userInfo = this.userInfoBo.getUserById(userId);
			this.orderNum = String.valueOf(System.currentTimeMillis());
		}
		return SUCCESS;
	}
	public String getMes() {
		return mes;
	}
	public List<B2cGoods> getGoodsList() {
		return goodsList;
	}
	public B2cGoods getB2cGoods() {
		return b2cGoods;
	}
	public void setB2cGoods(B2cGoods b2cGoods) {
		this.b2cGoods = b2cGoods;
	}
	public List<B2cGoodsBanner> getGoodsBannerList() {
		return goodsBannerList;
	}
	public List<B2cGoodsSku> getGoodsSkuList() {
		return goodsSkuList;
	}
	public List<B2cGoodsProperty> getPropertyList() {
		return propertyList;
	}
	public List<B2cGoodsDeliver> getB2cGoodsDeliverList() {
		return b2cGoodsDeliverList;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public List<B2cGoodsProperty> getPropertyList_normal() {
		return propertyList_normal;
	}
	public List<B2cGoodsType> getGoodsTypeList() {
		return goodsTypeList;
	}
}
