package com.kybaby.dao.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.dao.UserInfoDao;
import com.kybaby.domain.BabyBasicData;
import com.kybaby.domain.BabyBasicData2;
import com.kybaby.domain.CaseClip;
import com.kybaby.domain.HeightWeightHeadRecord;
import com.kybaby.domain.NormalData;
import com.kybaby.domain.UserInfo;

/**
 * @ClassName:UserInfoDaoImpl
 * @Description:用户数据管理实现
 * @author Hoolee
 * @date 2015年9月26日上午10:21:52
 */
public class UserInfoDaoImpl extends HibernateDaoSupport implements UserInfoDao {

	public UserInfo getUserInfoByPhone(String phone) {
		List<UserInfo> list=getHibernateTemplate().find("from UserInfo where phone=?", phone);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public void updateUser(UserInfo user) {
		getHibernateTemplate().update(user);
	}

	public void addNewUserInfo(UserInfo user) {
		getHibernateTemplate().save(user);
	}

	public UserInfo getUserInfoByOpenId(String openId) {
		List<UserInfo> list=getHibernateTemplate().find("from UserInfo where openId=?", openId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public UserInfo getUserById(long userId) {
		List<UserInfo> list=getHibernateTemplate().find("from UserInfo where id=?", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public List getUserLngLatById(long userId) {
		List list=getHibernateTemplate().find("select userLng , userLat from UserInfo where id=? ", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public BabyBasicData getBabyBasicDataByUserId(long userId) {
		List<BabyBasicData> list=getHibernateTemplate().find("from BabyBasicData where userId=?", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public void addNewRecord(HeightWeightHeadRecord eightWeightHeadRecord) {
		getHibernateTemplate().save(eightWeightHeadRecord);
	}

	public List<HeightWeightHeadRecord> getHeightRecordList(long userId) {
		List<HeightWeightHeadRecord> list=getHibernateTemplate().find("from HeightWeightHeadRecord where userId=? and height!='0' order by recordTime ", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<HeightWeightHeadRecord> getHeadRecordList(long userId) {
		List<HeightWeightHeadRecord> list=getHibernateTemplate().find("from HeightWeightHeadRecord where userId=? and headLength!='0' order by recordTime  ", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<HeightWeightHeadRecord> getweightRecordList(long userId) {
		List<HeightWeightHeadRecord> list=getHibernateTemplate().find("from HeightWeightHeadRecord where userId=? and weight!='0' order by recordTime  ", userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public List<CaseClip> getHistCaseClip(long userId) {
		List<CaseClip> list=getHibernateTemplate().find("from CaseClip where userId=? order by submitTime desc ",userId);
		if(list.isEmpty()==true){
			return null;
		}
		return list;
	}

	public void addNewCaseClip(CaseClip clip) {
		getHibernateTemplate().save(clip);
	}

	public NormalData getNormalDataByBabySexAndMonth(String sex,long monthYear) {
		List<NormalData> list=getHibernateTemplate().find("from NormalData where monthYear=? and sex=?",new Object[]{monthYear,sex} );
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public HeightWeightHeadRecord getRecordById(long recordId) {
		List<HeightWeightHeadRecord> list=getHibernateTemplate().find("from HeightWeightHeadRecord where id=? ",recordId);
		if(list.isEmpty()==true){
			return null;
		}
		return list.get(0);
	}

	public void updateSomeRecord(HeightWeightHeadRecord record) {
		getHibernateTemplate().update(record);
	}

	@Override
	public List<BabyBasicData2> getBabyBasicData2ListByUserId(Long userId) {
		StringBuffer hql = new StringBuffer("from BabyBasicData2 a where a.userId=").append(userId);
		List<BabyBasicData2> list = getHibernateTemplate().find(hql.toString());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public Long saveOrUpdateBabyBasicData(BabyBasicData2 babyBasicData) {
		Long id = null;
		if (babyBasicData == null ) return null;
		if(babyBasicData.getId() == null){
			id = (Long) getHibernateTemplate().save(babyBasicData);
		}else{
			id = babyBasicData.getId();
			BabyBasicData2 old = getHibernateTemplate().get(BabyBasicData2.class, id);
			BeanUtils.copyProperties(babyBasicData, old, new String[]{"id","userId","doctorId"});
			getHibernateTemplate().update(old);
		}
		return id;
	}

}
