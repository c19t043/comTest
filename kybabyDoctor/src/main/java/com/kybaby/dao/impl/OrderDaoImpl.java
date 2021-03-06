package com.kybaby.dao.impl;

import java.util.List;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.OrderDao;
import com.kybaby.domain.BabyBasicData;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.DoctorPoints;
import com.kybaby.domain.HealthPath;
import com.kybaby.domain.HealthPlan;
import com.kybaby.domain.HealthPlanRemind;
import com.kybaby.domain.ItemResult;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.OrderResults;
import com.kybaby.domain.Product;
import com.kybaby.domain.ProductItem;
import com.kybaby.domain.RecommentAwardRecord;
import com.kybaby.domain.UserAccount;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;
import com.kybaby.domain.UserPoints;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author sujiantang
 *
 */
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao{

	//通过医生ID查找部分订单List
	@Override
	public List getOrderListByDoctorId(Long id) {
		List list=getHibernateTemplate().find("select a.submitTime, a.totalPrice, a.splitRatio, a.trafficSubsidyMoney, a.orderStatus, b.parentName, b.phone, b.userProvince, b.userCity, b.userArea, b.userStreet, b.detailAddress, c.name, c.smallPicture, a.id, a.bespokeDate FROM OrderInfo a, UserInfo b, Product c WHERE a.doctorId=? AND a.userId=b.id AND a.productId=c.id ORDER BY a.submitTime DESC",id);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
 
	@Override
	public List newGetOrderListByDoctorId(Long id) {
		List list=getHibernateTemplate().find("select a.submitTime, a.totalPrice, a.splitRatio, a.trafficSubsidyMoney, a.orderStatus, b.parentName, b.phone, b.userProvince, b.userCity, b.userArea, b.userStreet, b.detailAddress, c.name, c.smallPicture, a.id, a.bespokeDate FROM OrderInfo a, UserInfo b, Product c WHERE a.doctorId=? AND a.userId=b.id AND a.productId=c.id AND a.orderStatus<>'未付款' ORDER BY a.submitTime DESC",id);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}
	
	//通过订单ID查找某个订单的详情
	@Override
	public OrderInfo getOrderByOrderId(Long id) {
		List list=getHibernateTemplate().find("FROM OrderInfo WHERE id=?",id);
		if(list.isEmpty()==true){
			return null;
		}
		return (OrderInfo)list.get(0);
	}

	//更新订单
	@Override
	public void update(OrderInfo orderInfo) {
		getHibernateTemplate().update(orderInfo);
	}

	@Override
	public List<List> getOrderDetailByOrderId(Long orderId) {
		List list=getHibernateTemplate().find("SELECT a.bespokeDate,a.bespokeTime,a.orderNum,a.orderStatus,a.totalPrice,a.splitRatio,a.trafficSubsidyMoney,b.babyName,b.sex,b.birthday,b.parentName,b.phone,b.userProvince,b.userCity,b.userArea,b.userStreet,b.detailAddress,c.name,c.smallPicture,c.itemIds,a.id FROM OrderInfo a,UserInfo b,Product c WHERE a.id=? AND b.id=a.userId AND c.id=a.productId",orderId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public Long getUserIdByOrderId(Long orderId) {
		List list=getHibernateTemplate().find("select userId from OrderInfo where id=? ",orderId);
		if(list.isEmpty()==true){
			return null;
		}
		return (Long)list.get(0);
	}

	@Override
	public BabyBasicData getBabyBasicDataByUserId(Long userId) {
		List list=getHibernateTemplate().find("from BabyBasicData where userId=?",userId);
		if(list.isEmpty()==true){
			return null;
		}
		return (BabyBasicData)list.get(0);
	}

	@Override
	public ProductItem getItemById(Long id) {
		List list=getHibernateTemplate().find("from ProductItem where id=? and itemStatus='Y'",id);
		if(list.isEmpty()==true){
			return null;
		}
		return (ProductItem)list.get(0);
	}
	
	
	// 对项目进行排序,DUMMY的放在后面
	@Override
	public List<Long> getOrderedItemId(String ids){
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String sqlQueryStr="SELECT id FROM product_item WHERE id IN "+ids+" AND comments NOT LIKE 'DUMMY%' UNION SELECT id FROM product_item WHERE id IN "+ids+" AND comments LIKE 'DUMMY%'";
		Query query = session.createSQLQuery(sqlQueryStr);
		List<Long> list=query.list();
	  	return list;
	}

	@Override
	public ItemResult getItemResultById(Long id) {
		List list=getHibernateTemplate().find("from ItemResult where id=?",id);
		if(list.isEmpty()==true){
			return null;
		}
		return (ItemResult)list.get(0);
	}

	@Override
	public HealthPlan getHealthPlanById(Long id) {
		List list=getHibernateTemplate().find("from HealthPlan where id=? and healthPlanStatus='Y'",id);
		if(list.isEmpty()==true){
			return null;
		}
		return (HealthPlan)list.get(0);
	}

	@Override
	public HealthPath getHealthPathById(Long id) {
		List list=getHibernateTemplate().find("from HealthPath where id=? and healthPathStatus='Y'",id);
		if(list.isEmpty()==true){
			return null;
		}
		return (HealthPath)list.get(0);
	}

	@Override
	public void saveOrderResults(OrderResults orderResults) {
		getHibernateTemplate().save(orderResults);
	}

	@Override
	public void saveHealthPlanRemind(HealthPlanRemind healthPlanRemind) {
		getHibernateTemplate().save(healthPlanRemind);
	}

	@Override
	public void saveBabyBasicData(BabyBasicData babyBasicData) {
		getHibernateTemplate().save(babyBasicData);
	}

	@Override
	public DoctorInfo getDoctorByDoctorId(Long doctorId) {
		List list=getHibernateTemplate().find("from DoctorInfo where id=?",doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return (DoctorInfo)list.get(0);
	}

	@Override
	public void updateDoctorInfo(DoctorInfo doctorInfo) {
		getHibernateTemplate().update(doctorInfo);
	}

	@Override
	public Product getProductById(Long productId) {
		List list=getHibernateTemplate().find("from Product where id=?",productId);
		if(list.isEmpty()==true){
			return null;
		}
		return (Product)list.get(0);
	}

	@Override
	public void updateProduct(Product product) {
		getHibernateTemplate().update(product);
	}

	@Override
	public List<RecommentAwardRecord> getRecommentAwardRecordByUserId(
			Long userId) {
		List list=getHibernateTemplate().find("from RecommentAwardRecord where beenRecommendUserId=? and isGrant='N' and whenToGrant='已签到'",userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public List<RecommentAwardRecord> getRecommentAwardRecordByDoctorId(
			Long doctorId) {
		List list=getHibernateTemplate().find("from RecommentAwardRecord where beenRecommendDoctorId=? and isGrant='N' and whenToGrant='已签到'",doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	@Override
	public UserInfo getUserByUserId(Long userId) {
		List list=getHibernateTemplate().find("from UserInfo where id=?",userId);
		if(list.isEmpty()==true){
			return null;
		}
		return (UserInfo)list.get(0);
	}

	@Override
	public void saveUserAccount(UserAccount userAccount) {
		getHibernateTemplate().save(userAccount);
	}

	@Override
	public void saveUserPoints(UserPoints userPoints) {
		getHibernateTemplate().save(userPoints);
	}

	@Override
	public Long getActivityIdByCouponId(Long couponId) {
		List list=getHibernateTemplate().find("select id from Activity where couponId=? order by endTime desc",couponId);
		if(list.isEmpty()==true){
			return null;
		}
		return (Long)list.get(0);
	}

	@Override
	public void saveUserCoupon(UserCoupon userCoupon) {
		getHibernateTemplate().save(userCoupon);
	}

	@Override
	public void updateAwared(RecommentAwardRecord recommentAwardRecord) {
		getHibernateTemplate().update(recommentAwardRecord);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		getHibernateTemplate().update(userInfo);
	}

	@Override
	public void saveDoctorAccount(DoctorAccount doctorAccount) {
		getHibernateTemplate().save(doctorAccount);
	}

	@Override
	public void saveDoctorPoints(DoctorPoints doctorPoints) {
		getHibernateTemplate().save(doctorPoints);
	}

}
