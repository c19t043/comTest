package com.kybaby.newbussiness.b2cgoods.bo;

import java.util.List;

import com.kybaby.newbussiness.b2cgoods.domain.B2cGoods;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsBanner;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsBasicPropvalSet;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrder;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsPresaleModel;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsProperty;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsPropertyValue;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsSku;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsType;

public interface GoodsService {
	/**
	 * 得到商品列表
	 * @param b2cGoods
	 * @return
	 */
	List<B2cGoods> getAllB2cGoods(B2cGoods b2cGoods);
	/**
	 * 得到商品分类列表
	 * @param b2cGoodsType
	 * @return
	 */
	List<B2cGoodsType> getAllB2cGoodsType(B2cGoodsType b2cGoodsType);
	/**
	 * 根据id得到商品信息
	 * @param id
	 * @return
	 */
	B2cGoods getB2cGoodsById(Long id);
	/**
	 * 得到商品banner图集合
	 * @return
	 */
	List<B2cGoodsBanner> getB2cGoodsBannerListByGoodsId(Long goodsId);
	/**
	 * 得到商品销售属性集合
	 * @param goodsId
	 * @return
	 */
	List<B2cGoodsSku> getB2cGoodsSkuListByGoodsId(Long goodsId);
	/**
	 * 得到商品销售单元
	 * @param id
	 * @return
	 */
	B2cGoodsSku getB2cGoodsSkuById(Long id);
	/**
	 * 得到产品属性配置集合
	 * @param goodsId
	 * @return
	 */
	List<B2cGoodsBasicPropvalSet> getB2cGoodsBasicPropvalSetListByGoodsId(Long goodsId);
	/**
	 * 预售模型列表
	 * @param goodsId
	 * @return
	 */
	List<B2cGoodsPresaleModel> getB2cGoodsPresaleModelListByGoodsId(Long goodsId );
	/**
	 * 得到属性
	 * @param id
	 * @return
	 */
	B2cGoodsProperty getB2cGoodsPropertyById(Long id);
	/**
	 * 得到属性值
	 * @param id
	 * @return
	 */
	B2cGoodsPropertyValue getB2cGoodsPropertyValueById(Long id);
	/**
	 * 得到商品订单里的总服务次数
	 * @param b2cGoodsOrder
	 * @return
	 */
	long goodsServicesCount(B2cGoodsOrder b2cGoodsOrder);
}
