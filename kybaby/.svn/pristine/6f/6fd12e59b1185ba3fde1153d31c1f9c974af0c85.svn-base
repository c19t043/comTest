package com.kybaby.newbussiness.b2cgoods.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.UserInfo;
import com.kybaby.newbussiness.b2cgoods.dao.GoodsOrderDao;
import com.kybaby.newbussiness.b2cgoods.domain.B2cAddress;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoods;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsBasicPropvalSet;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsDeliver;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrder;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrderDetail;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsOrderPromotion;
import com.kybaby.newbussiness.b2cgoods.domain.B2cGoodsSku;

public class GoodsOrderDaoImpl extends HibernateDaoSupport implements GoodsOrderDao{

	@Override
	public B2cGoodsOrder getB2cGoodsOrderById(Long id) {
		return this.getHibernateTemplate().get(B2cGoodsOrder.class, id);
	}

	@Override
	public Long saveOrUpdateB2cGoodsOrder(B2cGoodsOrder b2cGoodsOrder) {
		Long id = null;
		if(b2cGoodsOrder == null) return id;
		if(b2cGoodsOrder.getId() == null){
			id = (Long) this.getHibernateTemplate().save(b2cGoodsOrder);
		}else{
			id = b2cGoodsOrder.getId();
			this.getHibernateTemplate().update(b2cGoodsOrder);
		}
		return id;
	}

	@Override
	public List<B2cGoodsDeliver> getB2cGoodsDeliverList(
			B2cGoodsDeliver b2cGoodsDeliver) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from B2cGoodsDeliver p where 1=1 and isEnable='Y' ");
		
		if(b2cGoodsDeliver != null){
			if(StringUtils.isNotEmpty(b2cGoodsDeliver.getIsMain())){
				hql.append(" and p.isMain=?");
				params.add(b2cGoodsDeliver.getIsMain());
			}
		}
		List<B2cGoodsDeliver> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<B2cAddress> getB2cAddressList(B2cAddress b2cAddress) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from B2cAddress p where 1=1 ");
		
		if(b2cAddress != null){
		}
		List<B2cAddress> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<B2cGoodsOrderDetail> getB2cGoodsOrderDetailList(
			B2cGoodsOrder b2cGoodsOrder, B2cGoods b2cGoods,
			B2cGoodsSku b2cGoodsSku) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from B2cGoodsOrderDetail p where 1=1 ");
		
		if(b2cGoodsOrder != null){
			if(b2cGoodsOrder.getUserInfo() != null && b2cGoodsOrder.getUserInfo().getId() != null){
				hql.append(" and p.b2cGoodsOrder.userInfo.id = ?");
				params.add(b2cGoodsOrder.getUserInfo().getId());
			}
		}
		if(b2cGoods != null){
			if(b2cGoods.getId() != null){
				hql.append(" and p.b2cGoods.id = ?");
				params.add(b2cGoods.getId());
			}
		}
		if(b2cGoodsSku != null){
		}
		hql.append(" order by p.b2cGoodsOrder.submitTime desc, p.b2cGoodsOrder.updateTime desc");
		List<B2cGoodsOrderDetail> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public List<B2cAddress> getB2cAddressList(UserInfo userInfo,String isMain) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(" from B2cAddress p where 1=1 and p.isDel <> 'Y' ");
		
		if(userInfo != null){
			if(userInfo.getId() != null){
				hql.append(" and p.userInfo.id = ?");
				params.add(userInfo.getId());
			}
		}
		if(StringUtils.isNotEmpty(isMain)){
			hql.append(" and p.isMain = ?");
			params.add(isMain);
		}
		List<B2cAddress> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateB2cAddress(B2cAddress b2cAddress) {
		Long id = null;
		if(b2cAddress == null) return id;
		if(b2cAddress.getId() == null){
			id = (Long) this.getHibernateTemplate().save(b2cAddress);
		}else{
			id = b2cAddress.getId();
			this.getHibernateTemplate().update(b2cAddress);
		}
		return id;
	}

	@Override
	public Long saveOrUpdateB2cGoodsOrderPromotion(
			B2cGoodsOrderPromotion b2cGoodsOrderPromotion) {
		Long id = null;
		if(b2cGoodsOrderPromotion == null) return id;
		if(b2cGoodsOrderPromotion.getId() == null){
			id = (Long) this.getHibernateTemplate().save(b2cGoodsOrderPromotion);
		}else{
			id = b2cGoodsOrderPromotion.getId();
			this.getHibernateTemplate().update(b2cGoodsOrderPromotion);
		}
		return id;
	}

	@Override
	public B2cAddress getB2cAddressById(Long id) {
		return this.getHibernateTemplate().get(B2cAddress.class, id);
	}

	@Override
	public List<B2cGoodsOrder> getB2cGoodsOrderList(B2cGoodsOrder b2cGoodsOrder) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("from B2cGoodsOrder p where 1=1 and p.isDel <> 'Y' ");
		
		if(b2cGoodsOrder != null){
			if(b2cGoodsOrder.getUserInfo() != null && b2cGoodsOrder.getUserInfo().getId() != null){
				hql.append(" and p.userInfo.id = ?");
				params.add(b2cGoodsOrder.getUserInfo().getId());
			}
		}
		hql.append(" order by p.submitTime desc, p.updateTime desc");
		List<B2cGoodsOrder> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

}
