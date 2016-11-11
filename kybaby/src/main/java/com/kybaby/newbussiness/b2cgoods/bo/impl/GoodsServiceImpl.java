package com.kybaby.newbussiness.b2cgoods.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kybaby.newbussiness.b2cgoods.bo.GoodsService;
import com.kybaby.newbussiness.b2cgoods.dao.GoodsDao;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoods;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsBanner;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsBasicPropvalSet;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrder;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrderDetail;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsPresaleModel;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsProperty;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsPropertyValue;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsSku;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsType;
/**
 * 商品信息处理业务类
 * @author lihao
 *
 */
public class GoodsServiceImpl implements GoodsService{
	private GoodsDao goodsDao;
	public GoodsDao getGoodsDao() {
		return goodsDao;
	}
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	@Override
	public List<B2cGoods> getAllB2cGoods(B2cGoods b2cGoods) {
		return goodsDao.getAllB2cGoods(b2cGoods);
	}
	@Override
	public B2cGoods getB2cGoodsById(Long id) {
		return goodsDao.getB2cGoodsById(id);
	}
	@Override
	public List<B2cGoodsBanner> getB2cGoodsBannerListByGoodsId(Long goodsId) {
		return goodsDao.getB2cGoodsBannerListByGoodsId(goodsId);
	}
	@Override
	public List<B2cGoodsSku> getB2cGoodsSkuListByGoodsId(Long goodsId) {
		return goodsDao.getB2cGoodsSkuListByGoodsId(goodsId);
	}
	@Override
	public List<B2cGoodsBasicPropvalSet> getB2cGoodsBasicPropvalSetListByGoodsId(
			Long goodsId) {
		return goodsDao.getB2cGoodsBasicPropvalSetListByGoodsId(goodsId);
	}
	@Override
	public B2cGoodsSku getB2cGoodsSkuById(Long id) {
		return goodsDao.getB2cGoodsSkuById(id);
	}
	@Override
	public List<B2cGoodsPresaleModel> getB2cGoodsPresaleModelListByGoodsId(
			Long goodsId) {
		return goodsDao.getB2cGoodsPresaleModelListByGoodsId(goodsId);
	}
	@Override
	public List<B2cGoodsType> getAllB2cGoodsType(B2cGoodsType b2cGoodsType) {
		return goodsDao.getAllB2cGoodsType(b2cGoodsType);
	}
	@Override
	public B2cGoodsProperty getB2cGoodsPropertyById(Long id) {
		return goodsDao.getB2cGoodsPropertyById(id);
	}
	@Override
	public B2cGoodsPropertyValue getB2cGoodsPropertyValueById(Long id) {
		return goodsDao.getB2cGoodsPropertyValueById(id);
	}
	@Override
	public long goodsServicesCount(B2cGoodsOrder b2cGoodsOrder) {
		if(b2cGoodsOrder == null) return 0L;
		Iterator<B2cGoodsOrderDetail> it = b2cGoodsOrder.getB2cGoodsOrderDetailSet().iterator();
		long buyCount = 0L;
		while (it.hasNext()) {
			B2cGoodsOrderDetail b2cGoodsOrderDetailOld = (B2cGoodsOrderDetail) it.next();
			b2cGoodsOrderDetailOld.getB2cGoods();
			List<B2cGoodsSku> skuList = 
					this.goodsDao.getB2cGoodsSkuListByGoodsId(b2cGoodsOrderDetailOld.getB2cGoods().getId());
			if(skuList != null){
				for(B2cGoodsSku sku : skuList){
					String prop_val_ids = sku.getProperties();
					String[] prop_val_arr = prop_val_ids.split(",");
					String prop_val = "";
					for (int i = 0; i < prop_val_arr.length; i++) {
						String propId = prop_val_arr[i].split(":")[0];
						B2cGoodsProperty b2cGoodsProperty = this.goodsDao.getB2cGoodsPropertyById(Long.valueOf(propId));
						String propValId = prop_val_arr[i].split(":")[1];
						B2cGoodsPropertyValue b2cGoodsPropertyValue = this.goodsDao.getB2cGoodsPropertyValueById(Long.valueOf(propValId));
						prop_val += b2cGoodsProperty.getPropName() + ":" + b2cGoodsPropertyValue.getValName() + ",";
					}
					if(prop_val.equals(b2cGoodsOrderDetailOld.getPropVals()+",")){//判断销售单元吻合后，确定出购买的具体东西使用次数
						String count = sku.getNum();
						Long num = b2cGoodsOrderDetailOld.getGoodsNum();
						Long count_ = StringUtils.isEmpty(count)?1L:Long.valueOf(count);
						num = num == null?1L:num;
						buyCount = count_.longValue() * num.longValue();
						break;
					}
				}
			}
			break;
		}
		return buyCount;
	}

}
