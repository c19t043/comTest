package com.kybaby.kyinterface.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.OrderInfo;
import com.kybaby.domain.Product;
import com.kybaby.domain.UserInfo;
import com.kybaby.kyinterface.dao.KyInterfaceDao;
import com.kybaby.kyinterface.domain.UserInfoCustom;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResources;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.OrganInoculationOpenResourcesDetail;
import com.kybaby.newbussiness.medicalorgandbusiness.domain.UserInoculationAppointmentInfo;

public class KyInterfaceDaoImpl extends HibernateDaoSupport implements
		KyInterfaceDao {
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public UserInfo getUserInfo(Long id){
		return this.getHibernateTemplate().get(UserInfo.class, id);
	}
	
	/**
	 * 根据订单id获取上门服务订单记录
	 */
	public OrderInfo getOrderInfoById(Long orderId){
		return this.getHibernateTemplate().get(OrderInfo.class, orderId);
	}
	
	/**
	 * 根据订单编号，查找订单的来源，返回巴蜀快医上门服务订单id
	 * @param orderNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findBSKYOrderSource(final String orderNum){
		
		return this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "select a.kyid from kyOrder2MyOrder a "
						+ "left join order_info b on a.id = b.id "
						+ "where b.orderNum = ?";
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				createSQLQuery.setString(0, orderNum);
				List list = createSQLQuery.list();
				if(list.size()>0){
					return list.get(0);
				}
				return null;
			}
		});
	}

	/**
	 * 添加用户对象
	 */
	public void addUserInfo(UserInfoCustom userInfo) {
		this.getHibernateTemplate().save(userInfo);
	}

	/**
	 * 查询用户
	 */
	public UserInfoCustom findUserInfo(UserInfoCustom userInfo) {

		List list = this.getHibernateTemplate().find(
				"from UserInfoCustom where phone = ? ", userInfo.getPhone());
		if(list.size()>0){
			return (UserInfoCustom)list.get(0);
		}
		return  null;
	}

	/**
	 * 计免预约服务接口》》 创建计免订单
	 */
	public void addInoculationOrderInfo(UserInoculationAppointmentInfo info) {
		getHibernateTemplate().save(info);
	}

	/**
	 * 用户完成计免业务回执接口
	 */
	public UserInoculationAppointmentInfo userCompleteOrderByReturnReceipt(
			String orderId) {
		// 1.根据id获取订单
		UserInoculationAppointmentInfo info = getHibernateTemplate().get(
				UserInoculationAppointmentInfo.class, Long.parseLong(orderId));
		return info;
	}
	
	/**
	 * 上门服务订单创建传输接口 返回指定状态的订单信息（根据场景实现已签到、已完成等状态的转化） 保存订单状态
	 */
	public boolean orderStatusTransform(String orderId, String orderStatus) {
		// 1.根据订单id获取订单
		OrderInfo orderInfo = getHibernateTemplate().get(OrderInfo.class,
				Long.parseLong(orderId));
		if (orderInfo == null) {
			// 订单获取失败，没有该订单
			return false;
		} else {
			// 2.修改订单状态
			orderInfo.setOrderStatus(orderStatus);
			getHibernateTemplate().update(orderInfo);
			return true;
		}
	}

	/**
	 * 上门服务订单创建传输接口
	 */
	public void addOrderInfo(OrderInfo orderInfo) {
		getHibernateTemplate().save(orderInfo);
	}

	/**
	 * 根据快医上门服务订单id查找康优宝贝上门服务订单id
	 * @param kyOrderId 快医上门服务订单id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getOrderIdByKyOrderId(final String kyOrderId){
		return getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String sql = "select id from kyOrder2MyOrder where kyid = ?";
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				createSQLQuery.setString(0, kyOrderId);
				List list = createSQLQuery.list();
				if(list.size()==0){
					return null;
				}
				return list.get(0)+"";
			}
		});
	}
	
	/**
	 * 快医计免订单id与我方计免订单id对应关系表
	 * <p>
	 * 添加成功，返回true,失败返回false
	 * </p>
	 * 
	 * @param kyOrderId
	 *            快医上门服务订单id
	 * @param qycOrderId
	 *            我方上门服务订单id
	 */
	@SuppressWarnings("unchecked")
	public boolean addInocalutionOrder2myOrder(final String kyOrderId,
			final Long qycOrderId) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "insert into kyInoculationOrder2MyOrder values(?,?)";
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				createSQLQuery.setLong(0, qycOrderId);
				createSQLQuery.setString(1, kyOrderId);
				int executeUpdate = createSQLQuery.executeUpdate();
				if (executeUpdate == 0) {
					return false;
				}
				return true;
			}
		});
	}

	/**
	 * 快医订单id与我方上门服务订单id对应关系表
	 * <p>
	 * 添加成功，返回true,失败返回false
	 * </p>
	 * 
	 * @param kyOrderId
	 *            快医上门服务订单id
	 * @param qycOrderId
	 *            我方上门服务订单id
	 */
	@SuppressWarnings("unchecked")
	public boolean addKy2Order(final String kyOrderId, final Long qycOrderId) {
		return getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "insert into kyOrder2MyOrder values(?,?)";
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				createSQLQuery.setLong(0, qycOrderId);
				createSQLQuery.setString(1, kyOrderId);
				int executeUpdate = createSQLQuery.executeUpdate();
				if (executeUpdate == 0) {
					return false;
				}
				return true;
			}
		});
	}

	@Override
	public List<DoctorInfo> getDoctorInfoListByInterface() {
		// String hql = "FROM DoctorInfo where authentication='已通过' ";
		StringBuffer hql = new StringBuffer("select distinct a")
				.append(" from DoctorInfo a,DoctorServiceContent b where 1=1")
				.append(" and a.id=b.doctorInfo.id and b.doctorServiceType.parentDoctorServiceType.serviceTypeName='多点执业'")
				.append(" and b.doctorServiceType.parentDoctorServiceType.isAvailable='Y'")
				.append(" and b.doctorServiceType.isAvailable='Y'")
				.append(" and a.doctorStatus='Y' and a.authentication='已通过' ");
		List<DoctorInfo> list = getHibernateTemplate().find(hql.toString());
		if (list.isEmpty() == true) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		String hql = "from HospitalBasicInfo where hospitalType='社区医院' ";
		List<HospitalBasicInfo> list = getHibernateTemplate().find(hql);
		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public List<Product> getProductList() {
		String hql = "from Product where productType='健康产品' ";
		List<Product> list = getHibernateTemplate().find(hql);
		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public List<OrganInoculationOpenResourcesDetail> getHospitalSchedulingInfo(
			String orgId) {
		StringBuilder sb = new StringBuilder();
		String hql = "from OrganInoculationOpenResourcesDetail  a  where "
				+ " a.organInoculationOpenResources.hospitalBasicInfo.hospitalType='社区医院'";
		sb.append(hql);
		if (StringUtils.isNotBlank(orgId)) {
			sb.append(" and a.organInoculationOpenResources.hospitalBasicInfo.id = "
					+ orgId);
		}
		List<OrganInoculationOpenResourcesDetail> list = getHibernateTemplate()
				.find(sb.toString());
		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	
	public OrganInoculationOpenResources getOrgInnoculationOpenResourceByID(
			Long open_resources_id){
		return this.getHibernateTemplate().get(OrganInoculationOpenResources.class, open_resources_id);
	}

	public OrganInoculationOpenResourcesDetail getOrgInnoculationOpenResourceDetailByID(
			Long open_resources_detail_id){
		return this.getHibernateTemplate().get(OrganInoculationOpenResourcesDetail.class, open_resources_detail_id);
	}

	public HospitalBasicInfo getOrgInfoByID(Long ascription_organ){
		return this.getHibernateTemplate().get(HospitalBasicInfo.class, ascription_organ);
	}

	@Override
	public Product getProductByID(Long productId) {
		return this.getHibernateTemplate().get(Product.class, productId);
	}

	@Override
	public void updateUserInfo(UserInfoCustom userinfo) {
		this.getHibernateTemplate().update(userinfo);
	}
}
