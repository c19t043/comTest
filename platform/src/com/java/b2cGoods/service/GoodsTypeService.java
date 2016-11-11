package com.java.b2cGoods.service;

import java.util.List;

import com.java.b2cGoods.vo.B2cGoodsType;
import com.java.ec.common.PageSortModel;

public interface GoodsTypeService {
	/**
	 * 分页列表
	 * @param psm
	 * @param B2cGoodsType
	 * @return
	 */
	List<B2cGoodsType> getB2cGoodsTypeByPage(PageSortModel psm,B2cGoodsType b2cGoodsType);
	/**
	 * 根据id得到实体
	 * @param id
	 * @return
	 */
	B2cGoodsType getB2cGoodsTypeById(Long id);
	/**
	 * 保存更新实体
	 * @param b2cGoodsDeliver
	 * @return
	 */
	Long saveOrUpdateB2cGoodsType(B2cGoodsType b2cGoodsType);
}
