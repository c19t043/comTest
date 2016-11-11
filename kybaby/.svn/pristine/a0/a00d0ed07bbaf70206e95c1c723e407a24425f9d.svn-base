package com.kybaby.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.OrderInfoDao;
import com.kybaby.domain.AssessmentTag;
import com.kybaby.domain.DoctorAccount;
import com.kybaby.domain.DoctorAssessmentTag;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Evaluate;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.UserCoupon;
import com.kybaby.domain.UserInfo;

/**
 * @ClassName:OrderInfoDaoImpl
 * @Description:医生的数据管理接口实现
 * @author Hoolee
 * @date 2015年9月29日下午5:50:28
 */
public class OrderInfoDaoImpl extends HibernateDaoSupport implements OrderInfoDao {

	public long getDoctorIdByOrderNum(String orderNum) {
		List<Long> list=getHibernateTemplate().find("select doctorId from OrderInfo where orderNum=? ", orderNum);
		if(list.isEmpty()==true){
			return 0;
		}
		return list.get(0);
	}

	public void addNewOrderInfo(OrderInfo orderInfo) {
		getHibernateTemplate().save(orderInfo);
	}

	public OrderInfo getOrderInfoByNum(String orderNum) {
		List<OrderInfo> list=getHibernateTemplate().find("from OrderInfo where orderNum=?", orderNum);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public void udateOrderInfoInstance(OrderInfo orderInfo) {
		getHibernateTemplate().update(orderInfo);
	}

	public List getOrderNumAndProductId(long userId) {
		List list=getHibernateTemplate().find("select orderNum , productId from OrderInfo where userId=? and orderStatus in('已确认','已评价') group by orderNum order by submitTime desc ", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	//add by sjt
	public List getOrderListByUserId(Long userId) {
		/*List<Object[]> list=getHibernateTemplate().find("SELECT a.id, a.submitTime, a.orderStatus, a.usePointsAmount, a.useRemainBalance, b.name, b.totalPrice, b.smallPicture, c.doctorName, c.doctorTitle, d.couponAmount,a.orderNum FROM OrderInfo a, Product b, DoctorInfo c, Coupon d WHERE a.userId=? AND b.id=a.productId AND c.id=a.doctorId AND d.id=a.couponId AND a.couponId<>'0'ORDER BY a.submitTime DESC", userId);
		List<Object[]> lists=getHibernateTemplate().find("SELECT a.id, a.submitTime, a.orderStatus, a.usePointsAmount, a.useRemainBalance, b.name, b.totalPrice, b.smallPicture, c.doctorName, c.doctorTitle,a.orderNum FROM OrderInfo a, Product b, DoctorInfo c WHERE a.userId=? AND b.id=a.productId AND c.id=a.doctorId AND a.couponId='0' ORDER BY a.submitTime DESC", userId);
		list.addAll(lists);*/
		
		//updated by zhong at 2015-10-31
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String sqlQueryStr="SELECT A.id, A.submitTime, A.orderStatus, A.usePointsAmount, A.useRemainBalance, A.name, A.totalPrice, A.smallPicture, A.doctorName, A.doctorTitle,A.orderNum,A.couponId,d.couponAmount,A.bespokeDate,A.bespokeTime FROM "
				+ "(SELECT a.id, a.submitTime, a.orderStatus, a.usePointsAmount, a.useRemainBalance, b.name, a.totalPrice, b.smallPicture, c.doctorName, c.doctorTitle,a.orderNum,a.couponId,a.bespokeDate,a.bespokeTime "
				+ "FROM order_info a LEFT JOIN product b ON b.id = a.productId LEFT JOIN doctor_info c ON c.id = a.doctorId "
				//+ "WHERE a.userId="+userId+" OR a.doctorId IS NULL  ORDER BY a.submitTime DESC) A LEFT JOIN coupon  d ON A.couponId=d.id";
				//modify by xchao 2016-01-12  解决BUG：用户端可以查到到别人已取消和未分配医生的订单
				+ "WHERE a.userId="+userId+" ORDER BY a.submitTime DESC) A LEFT JOIN coupon  d ON A.couponId=d.id";
		Query query = session.createSQLQuery(sqlQueryStr);
		List<Object[]> list=query.list();
		//List<Object[]> list=getHibernateTemplate().find("SELECT A.*,d.couponAmount FROM (SELECT a.id, a.submitTime, a.orderStatus, a.usePointsAmount, a.useRemainBalance, b.name, b.totalPrice, b.smallPicture, c.doctorName, c.doctorTitle,a.orderNum,a.couponId FROM OrderInfo a, Product b, DoctorInfo c WHERE a.userId=? AND b.id=a.productId AND c.id=a.doctorId  ORDER BY a.submitTime DESC) A LEFT JOIN Coupon  d ON A.couponId=d.id",userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
		//update by HooLee 2015年10月30日20:38:23 因为存在没有使用优惠券订单中的couponID为0
		/*List list=getHibernateTemplate().find("SELECT a.id, a.submitTime, a.orderStatus, a.usePointsAmount, a.useRemainBalance, b.name, b.totalPrice, b.smallPicture, c.doctorName, c.doctorTitle, d.couponAmount,a.orderNum FROM OrderInfo a, Product b, DoctorInfo c, Coupon d WHERE a.userId=? AND b.id=a.productId AND c.id=a.doctorId AND d.id=a.couponId order by a.submitTime desc", userId);
		if(list.isEmpty()==true){
			List list1=getHibernateTemplate().find("SELECT a.id, a.submitTime, a.orderStatus, a.usePointsAmount, a.useRemainBalance, b.name, b.totalPrice, b.smallPicture, c.doctorName, c.doctorTitle,a.orderNum FROM OrderInfo a, Product b, DoctorInfo c WHERE a.userId=? AND b.id=a.productId AND c.id=a.doctorId order by a.submitTime desc ", userId);
			if(list1.isEmpty()==true){
				return null;
			}else{
				return list1;
			}
		}
		return list;*/
	}

	public List getOrderDetailById(Long orderId) {
		List list=getHibernateTemplate().find("SELECT a.updateTime, a.orderNum, a.orderStatus, a.totalPrice, a.usePointsAmount, a.useRemainBalance, a.payMethod, b.parentName, b.phone, b.userProvince, b.userCity, b.userArea, b.userStreet, b.detailAddress, c.name, c.smallPicture, d.doctorName, d.doctorTitle, e.couponAmount FROM OrderInfo a, UserInfo b, Product c, DoctorInfo d, Coupon e WHERE a.id=? AND b.id=a.userId AND c.id=a.productId AND d.id=a.doctorId AND e.id=a.couponId", orderId);
		if(list.isEmpty()==true){
			List list1=getHibernateTemplate().find("SELECT a.updateTime, a.orderNum, a.orderStatus, a.totalPrice, a.usePointsAmount, a.useRemainBalance, a.payMethod, b.parentName, b.phone, b.userProvince, b.userCity, b.userArea, b.userStreet, b.detailAddress, c.name, c.smallPicture, d.doctorName, d.doctorTitle FROM OrderInfo a, UserInfo b, Product c, DoctorInfo d WHERE a.id=? AND b.id=a.userId AND c.id=a.productId AND d.id=a.doctorId", orderId);
			if(list1.isEmpty()==true){
				return null;
			}
			else{
				return list1;
			}
		}
		return list;
	}

	public String getProductItemIds(Long orderId) {
		List list=getHibernateTemplate().find("SELECT b.itemIds FROM OrderInfo a, Product b WHERE a.id=? AND b.id=a.productId", orderId);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	public String getItemNameByItemId(Long id) {
		List list=getHibernateTemplate().find("SELECT itemName FROM ProductItem WHERE id=?", id);
		if(list.isEmpty()==true){
			return null;
		}
		return (String)list.get(0);
	}

	public OrderInfo getOneOrderByOrderId(Long orderId) {
		List list=getHibernateTemplate().find("FROM OrderInfo WHERE id=?", orderId);
		if(list.isEmpty()==true){
			return null;
		}
		return (OrderInfo)list.get(0);
	}

	public UserCoupon getCouponByCouponIdAndUserId(Long couponId, Long userId) {
		List list=getHibernateTemplate().find("FROM UserCoupon WHERE couponId=? AND userId=? AND couponStatus='Y'", couponId,userId);
		if(list.isEmpty()==true){
			return null;
		}
		return (UserCoupon)list.get(0);
	}

	public void updateUserCoupon(UserCoupon userCoupon) {
		getHibernateTemplate().update(userCoupon);
	}

	public UserInfo getUserInfoByUserId(Long userId) {
		List list=getHibernateTemplate().find("FROM UserInfo WHERE id=?", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return (UserInfo)list.get(0);
	}

	public void updateUserInfo(UserInfo userInfo) {
		getHibernateTemplate().update(userInfo);
	}

	public void updateOrderInfo(OrderInfo orderInfo) {
		getHibernateTemplate().update(orderInfo);
	}

	public List<AssessmentTag> getAssessmentTag() {
		List list=getHibernateTemplate().find("FROM AssessmentTag WHERE tagStatus='Y'");
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public DoctorInfo getDoctorInfoById(Long doctorId) {
		List list=getHibernateTemplate().find("FROM DoctorInfo WHERE id=?", doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return (DoctorInfo)list.get(0);
	}

	public DoctorAssessmentTag getDoctorAssessmentTagById(Long tagId, Long doctorId) {
		List list=getHibernateTemplate().find("FROM DoctorAssessmentTag WHERE tagId=? AND doctorId=?", tagId,doctorId);
		if(list.isEmpty()==true){
			return null;
		}
		return (DoctorAssessmentTag)list.get(0);
	}

	public void updateDoctorAssessmentTag(
			DoctorAssessmentTag doctorAssessmentTag) {
		getHibernateTemplate().update(doctorAssessmentTag);
	}

	public void saveDoctorAssessmentTag(DoctorAssessmentTag doctAssessmentTag) {
		getHibernateTemplate().save(doctAssessmentTag);
	}

	public void updateDoctorInfo(DoctorInfo docInf) {
		getHibernateTemplate().update(docInf);
	}

	public void saveDoctorAccount(DoctorAccount doctorAccount) {
		getHibernateTemplate().save(doctorAccount);
	}

	public void saveEvaluate(Evaluate evaluate) {
		getHibernateTemplate().save(evaluate);
	}
	
}
