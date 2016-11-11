package com.java.b2cGoods.service;

import java.util.List;

import com.java.b2cGoods.vo.B2cGoods;
import com.java.b2cGoods.vo.B2cGoodsBanner;
import com.java.ec.common.PageSortModel;

public interface GoodsService {
	/**
	 * 保存或更新商品信息
	 * @param b2cGoods
	 * @return
	 */
	public Long saveOrUpdateB2cGoods(B2cGoods b2cGoods) ;
	/**
	 * 保存或更新banner
	 * @param banner
	 * @return
	 */
	public Long saveOrUpdateB2cGoodsBanner(B2cGoodsBanner banner) ;
	/**
	 * 商品列表分页
	 * @param psm
	 * @param b2cGoods
	 * @return
	 */
	List<B2cGoods> getB2cGoodsListByPage(PageSortModel psm,B2cGoods b2cGoods);
	/**
	 * 商品banner列表
	 * @param b2cGoods
	 * @return
	 */
	List<B2cGoodsBanner> getB2cGoodsBannerList(B2cGoods b2cGoods);
	/**
	 * 根据id得到商品信息
	 * @param b2cGoods
	 * @return
	 */
	B2cGoods getB2cGoodsById(B2cGoods b2cGoods);
}
