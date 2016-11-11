package com.kybaby.newbussiness.b2cgoods.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.b2cgoods.dao.GoodsDao;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoods;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsBanner;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsBasicPropvalSet;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsPresaleModel;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsProperty;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsPropertyValue;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsSku;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsType;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalPosition;
import com.kybaby.util.ConstantManage;
@SuppressWarnings("all")
public class GoodsDaoImpl extends HibernateDaoSupport implements GoodsDao{

	@Override
	public List<B2cGoods> getAllB2cGoods(B2cGoods b2cGoods) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from B2cGoods p where 1=1 and p.isUp='Y' ");
		
		if(b2cGoods != null){
//			hql.append(" and p.doctorMorePracticeOrgInfo.id =?");
//			params.add(Long.valueOf(orderInfoClinic.getClinicAddress()));
		}
		hql.append("order by p.createDate desc");
		List<B2cGoods> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public B2cGoods getB2cGoodsById(Long id) {
		return this.getHibernateTemplate().get(B2cGoods.class, id);
	}

	@Override
	public List<B2cGoodsBanner> getB2cGoodsBannerListByGoodsId(Long goodsId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from B2cGoodsBanner p where 1=1 and p.isEnable='Y' ");
		
		if(goodsId != null){
			hql.append(" and p.b2cGoods.id =?");
			params.add(goodsId);
		}
		List<B2cGoodsBanner> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<B2cGoodsSku> getB2cGoodsSkuListByGoodsId(Long goodsId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from B2cGoodsSku p where 1=1 and p.status='Y' ");
		
		if(goodsId != null){
			hql.append(" and p.b2cGoods.id =?");
			params.add(goodsId);
		}
		hql.append(" order by p.price");
		List<B2cGoodsSku> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<B2cGoodsBasicPropvalSet> getB2cGoodsBasicPropvalSetListByGoodsId(
			Long goodsId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from B2cGoodsBasicPropvalSet p where 1=1 ");
		
		if(goodsId != null){
			hql.append(" and p.b2cGoods.id =?");
			params.add(goodsId);
		}
		List<B2cGoodsBasicPropvalSet> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public B2cGoodsSku getB2cGoodsSkuById(Long id) {
		return this.getHibernateTemplate().get(B2cGoodsSku.class, id);
	}

	@Override
	public List<B2cGoodsPresaleModel> getB2cGoodsPresaleModelListByGoodsId(
			Long goodsId) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from B2cGoodsPresaleModel p where 1=1 and p.isEnable='Y'");
		hql.append(" and p.startSaleDate > NOW() ");
		if(goodsId != null){
			hql.append(" and p.goodsId =?");
			params.add(goodsId);
		}
		List<B2cGoodsPresaleModel> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<B2cGoodsType> getAllB2cGoodsType(B2cGoodsType b2cGoodsType) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from B2cGoodsType p where 1=1 and p.isEnable='Y' ");
		
		if(b2cGoodsType != null){
//			hql.append(" and p.b2cGoods.id =?");
//			params.add(goodsId);
		}
		hql.append("order by p.sort");
		List<B2cGoodsType> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public B2cGoodsProperty getB2cGoodsPropertyById(Long id) {
		return this.getHibernateTemplate().get(B2cGoodsProperty.class, id);
	}

	@Override
	public B2cGoodsPropertyValue getB2cGoodsPropertyValueById(Long id) {
		return this.getHibernateTemplate().get(B2cGoodsPropertyValue.class, id);
	}

}
