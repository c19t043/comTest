package com.java.b2cGoods.service;

import java.util.List;

import com.java.b2cGoods.vo.B2cGoodsProperty;
import com.java.b2cGoods.vo.B2cGoodsPropertyValue;
import com.java.ec.common.PageSortModel;

public interface GoodsPropAndValService {
	/**
	 * 属性分页列表
	 * @param psm
	 * @param B2cGoodsProperty
	 * @return
	 */
	List<B2cGoodsProperty> getB2cGoodsPropertyByPage(PageSortModel psm,B2cGoodsProperty b2cGoodsProperty);
	/**
	 * 属性值分页列表
	 * @param psm
	 * @param B2cGoodsPropertyValue
	 * @return
	 */
	List<B2cGoodsPropertyValue> getB2cGoodsPropertyValueByPage(PageSortModel psm,B2cGoodsPropertyValue b2cGoodsPropertyValue);
	/**
	 * 根据id得到属性实体
	 * @param id
	 * @return
	 */
	B2cGoodsProperty getB2cGoodsPropertyById(Long id);
	/**
	 * 保存更新属性实体
	 * @param b2cGoodsDeliver
	 * @return
	 */
	Long saveOrUpdateB2cGoodsProperty(B2cGoodsProperty b2cGoodsProperty);
}
