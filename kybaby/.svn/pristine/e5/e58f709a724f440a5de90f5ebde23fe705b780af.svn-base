package com.kybaby.newbussiness.messagecenter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.newbussiness.messagecenter.dao.MessageCenterDao;
import com.kybaby.newbussiness.messagecenter.domain.MessageCenter;
import com.kybaby.util.DateManage;

/**
 * 消息中心Dao实现类
 * @author xiongchao
 */
public class MessageCenterDaoImpl  extends HibernateDaoSupport implements MessageCenterDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int messageNoReadCountByUser(Long userId) {
		if (userId == null) {
			return 0;
		}
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("select count(*) from MessageCenter m where m.isDel='N' and m.isRead='N' ");
		hql.append(" and m.userInfo.id = ?");
		params.add(userId);
		Long count = (Long)this.getHibernateTemplate().find(hql.toString(), params.toArray()).listIterator().next();
		return count.intValue();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<MessageCenter> getMessageCenterListByUser(Long userId) {
		if (userId == null) {
			return null;
		} else {
			List params = new ArrayList();
			StringBuffer hql = new StringBuffer(" from MessageCenter m where m.isDel='N'");
			hql.append(" and m.userInfo.id = ?");
			params.add(userId);
			hql.append(" order by m.messageSendTime desc ");
			List<MessageCenter> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
			return list;
		}
	}

	@Override
	public Long updateMessageCenterStatus(MessageCenter messageCenter, String operaFlag, String operaValue) {
		if(messageCenter == null || messageCenter.getId() == null) return null;
		MessageCenter updateMessageCenter = this.getHibernateTemplate().get(MessageCenter.class, messageCenter.getId());
		Long id = updateMessageCenter.getId();
		if (operaFlag != null) {
			if (MessageCenter.READ_OPERA.equals(operaFlag)) {
				updateMessageCenter.setIsRead(operaValue);//读操作
			} else if (MessageCenter.DEL_OPERA.equals(operaFlag)) {
				updateMessageCenter.setIsDel(operaValue);//删除操作
			}
		}
		updateMessageCenter.setOptTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
		this.getHibernateTemplate().update(updateMessageCenter);
		return id;
	}

}
