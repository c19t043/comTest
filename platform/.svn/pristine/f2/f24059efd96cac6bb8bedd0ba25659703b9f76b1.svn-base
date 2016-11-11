package com.java.asqtest.service.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.java.asqtest.service.AsqtestService;
import com.java.asqtest.vo.AsqBeenOptions;
import com.java.asqtest.vo.AsqParentChildActivity;
import com.java.asqtest.vo.AsqQuestionRecord;
import com.java.asqtest.vo.AsqQuestions;
import com.java.asqtest.vo.AsqResultScoreEx;
import com.java.asqtest.vo.AsqResultScoreExUser;
import com.java.asqtest.vo.AsqTaoti;
import com.java.asqtest.vo.AsqTaotiAge;
import com.java.asqtest.vo.AsqTestUserInfo;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;

public class AsqtestServiceImpl extends ServiceImpl implements AsqtestService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}

	@Override
	public List<AsqTaotiAge> getAsqTaotiAges(PageSortModel model,
			AsqTaotiAge asqTaotiAge) {
		StringBuilder conditions = new StringBuilder("");
		Map<String,Object> params = new HashMap<String,Object>();
		
		conditions.append("from AsqTaotiAge c where 1=1 ");
		if(asqTaotiAge!=null){
			if(asqTaotiAge.getTaoti()!=null&&StringUtils.isNotBlank(asqTaotiAge.getTaoti().getTitalName())){
				conditions.append(" and c.taoti.titalName like :titalName");
				params.put("titalName", "%"+asqTaotiAge.getTaoti().getTitalName()+"%");
			}
			if(StringUtils.isNotBlank(asqTaotiAge.getShowName())){
				conditions.append(" and c.showName like :showName");
				params.put("showName", "%"+asqTaotiAge.getShowName()+"%");
			}
		}
		
		conditions.append(" order by c.id desc");
		List<AsqTaotiAge> ttAge = new ArrayList<AsqTaotiAge>();
		
		
		if(model==null){
			ttAge = super.list(conditions.toString(), -1, -1, params);
		}else{
			ttAge = (List<AsqTaotiAge>) super.listForEc(conditions.toString(), model, params);
		}
		return ttAge;
	}

	@Override
	public AsqTaotiAge saveOrUpdateAsqTaotiAge(AsqTaotiAge asqTaotiAge) {
		Long id = asqTaotiAge.getId();
		if(id==null){
			super.add(asqTaotiAge);
		}else{
			AsqTaotiAge ttAge = super.get(id, AsqTaotiAge.class);
			BeanUtils.copyProperties(asqTaotiAge, ttAge, new String[]{"id"});
			super.edit(ttAge);
			return ttAge;
		}
		return asqTaotiAge;
	}

	@Override
	public List<AsqTaoti> getAsqTaotis(PageSortModel model, AsqTaoti asqTaoti) {
		StringBuilder conditions = new StringBuilder("");
		Map<String,Object> params = new HashMap<String,Object>();
		
		conditions.append("from AsqTaoti c where 1=1 ");
		
		if(asqTaoti!=null){
			if(StringUtils.isNotBlank(asqTaoti.getTitalName())){
				conditions.append(" and c.titalName like :titalName");
				params.put("titalName", "%"+asqTaoti.getTitalName()+"%");
			}
			if(asqTaoti.getParent()!=null&&StringUtils.isNotBlank(asqTaoti.getParent().getTitalName())){
					conditions.append(" and c.parent.titalName like :parentName");
					params.put("parentName", "%"+asqTaoti.getParent().getTitalName()+"%");
			}
			if(StringUtils.isNotBlank(asqTaoti.getFlag())){
				String flag = asqTaoti.getFlag();
				if("parent".equals(flag))
					conditions.append(" and c.parent is null  ");
				else
					conditions.append(" and c.parent is not null  ");
			}
			if(asqTaoti.getParent()!=null&&asqTaoti.getParent().getId()!=null){
				conditions.append(" and c.parent.id = :pid");
				params.put("pid", asqTaoti.getParent().getId());
			}
		}
		
		if(asqTaoti!=null){
			if(asqTaoti.getParent()!=null&&asqTaoti.getParent().getId()!=null){
				conditions.append(" order by c.sort");
			}
		}else{
			conditions.append(" order by c.id desc");
		}
		
		
		
		List<AsqTaoti> tt = new ArrayList<AsqTaoti>();
		
		if(model==null){
			tt = super.list(conditions.toString(), -1, -1, params);
		}else{
			tt =  (List<AsqTaoti>) super.listForEc(conditions.toString(), model, params);
		}
		return tt;
	}

	@Override
	public AsqTaoti saveOrUpdateAsqTaoti(AsqTaoti asqTaoti) {
		Long id = asqTaoti.getId();
		if(id==null){
			AsqTaoti parent = asqTaoti.getParent();
			if(parent.getId()==null){
				asqTaoti.setParent(null);
			}
			super.add(asqTaoti);
		}else{
			AsqTaoti tt = super.get(id, AsqTaoti.class);
			AsqTaoti parent = asqTaoti.getParent();
			if(parent!=null&&parent.getId()==null){
				asqTaoti.setParent(null);
			}
			BeanUtils.copyProperties(asqTaoti, tt, new String[]{"id"});
			super.edit(tt);
			return tt;
		}
		return asqTaoti;
	}

	@Override
	public List<AsqQuestions> getAsqQuestions(PageSortModel model,
			AsqQuestions asqQuestions) {
		StringBuilder conditions = new StringBuilder("");
		Map<String,Object> params = new HashMap<String,Object>();
		
		conditions.append("from AsqQuestions c where 1=1 ");
		
		if(asqQuestions!=null){
			if(asqQuestions.getAsqTaotiAge()!=null&&StringUtils.isNotBlank(asqQuestions.getAsqTaotiAge().getShowName())){
				conditions.append(" and c.asqTaotiAge.showName like :showName");
				params.put("showName", "%"+asqQuestions.getAsqTaotiAge().getShowName()+"%");
			}
			if(asqQuestions.getAsqTaoti()!=null&&
					asqQuestions.getAsqTaoti().getParent()!=null&&StringUtils.isNotBlank(asqQuestions.getAsqTaoti().getParent().getTitalName())){
				conditions.append(" and c.asqTaoti.parent.titalName like :parentName");
				params.put("parentName", "%"+asqQuestions.getAsqTaoti().getParent().getTitalName()+"%");
			}
			if(asqQuestions.getAsqTaoti()!=null&&StringUtils.isNotBlank(asqQuestions.getAsqTaoti().getTitalName())){
				conditions.append(" and c.asqTaoti.titalName like :titalName");
				params.put("titalName", "%"+asqQuestions.getAsqTaoti().getTitalName()+"%");
			}
			if(StringUtils.isNotBlank(asqQuestions.getSubject())){
				conditions.append(" and c.subject like :subject");
				params.put("subject", "%"+asqQuestions.getSubject()+"%");
			}
			if(asqQuestions.getAsqTaotiAge()!=null&&asqQuestions.getAsqTaotiAge().getId()!=null){
				conditions.append(" and c.asqTaotiAge.id = :ttaID");
				params.put("ttaID", asqQuestions.getAsqTaotiAge().getId());
			}
		}
		
		conditions.append(" order by c.asqTaotiAge.showName,c.asqTaoti.parent.titalName,c.asqTaoti.titalName,c.sort");
		
		List<AsqQuestions> qst = new ArrayList<AsqQuestions>();
		
		if(model==null){
			qst = super.list(conditions.toString(), -1, -1, params);
		}else{
			qst =  (List<AsqQuestions>) super.listForEc(conditions.toString(), model, params);
		}
		for (AsqQuestions qts : qst) {
			StringBuilder sb = new StringBuilder();
			List<AsqBeenOptions> asqBeenOptions = getAsqBeenOptions(null, new AsqBeenOptions(qts));
			for (AsqBeenOptions bo : asqBeenOptions) {
				sb.append(bo.getOptionContent()).append("/");
			}
			qts.setOptionContents(sb.toString());
		}
		
		return qst;
	}

	@Override
	public AsqQuestions saveOrUpdateAsqQuestions(AsqQuestions asqQuestions) {
		List<AsqBeenOptions> pre_asqBeenOptionsList = asqQuestions.getAsqBeenOptionsList();
		Long id = asqQuestions.getId();
		if(id==null){
			super.add(asqQuestions);
		}else{
			AsqQuestions qst = super.get(id, AsqQuestions.class);
			BeanUtils.copyProperties(asqQuestions, qst,new String[]{"id"});
			super.edit(qst);
			asqQuestions = qst;
		}
		addOrUpdateAsqBeenOptions(pre_asqBeenOptionsList, asqQuestions);
		return asqQuestions;
	}
	
	private void addOrUpdateAsqBeenOptions(List<AsqBeenOptions> pre_asqBeenOptionsList,AsqQuestions asqQuestions){
		for (AsqBeenOptions asqBeenOptions : pre_asqBeenOptionsList) {
			asqBeenOptions.setAsqQuestions(asqQuestions);
			saveOrUpdateAsqBeenOptions(asqBeenOptions);
		}
	}
	
	@Override
	public List<AsqBeenOptions> getAsqBeenOptions(PageSortModel model,
			AsqBeenOptions asqBeenOptions) {
		StringBuilder conditions = new StringBuilder("");
		Map<String,Object> params = new HashMap<String,Object>();
		
		conditions.append("from AsqBeenOptions c where 1=1 ");
		
		if(asqBeenOptions!=null){
			/*if(asqBeenOptions.getAsqQuestions()!=null&&StringUtils.isNotBlank(asqBeenOptions.getAsqQuestions().getSubject())){
				conditions.append(" and c.asqQuestions.subject like :subject");
				params.put("subject", "%"+asqBeenOptions.getAsqQuestions().getSubject()+"%");
			}*/
			if(asqBeenOptions.getAsqQuestions()!=null&&asqBeenOptions.getAsqQuestions().getId()!=null){
				conditions.append(" and c.asqQuestions.id = :qtid");
				params.put("qtid", asqBeenOptions.getAsqQuestions().getId());
			}
			if(StringUtils.isNotBlank(asqBeenOptions.getQtIDs())){
				conditions.append(" and c.asqQuestions.id in ("+asqBeenOptions.getQtIDs()+")");
			}
		}
		
		List<AsqBeenOptions> bos = new ArrayList<AsqBeenOptions>();
		
		if(model==null){
			bos = super.list(conditions.toString(), -1, -1, params);
		}else{
			bos =  (List<AsqBeenOptions>) super.listForEc(conditions.toString(), model, params);
		}
		return bos;
	}
	
	@Override
	public AsqBeenOptions saveOrUpdateAsqBeenOptions(
			AsqBeenOptions asqBeenOptions) {
		Long id = asqBeenOptions.getId();
		if(id==null){
			super.add(asqBeenOptions);
		}else{
			AsqBeenOptions bos = super.get(id, AsqBeenOptions.class);
			BeanUtils.copyProperties(asqBeenOptions, bos);
			super.edit(bos);
			return bos;
		}
		return asqBeenOptions;
	}

	@Override
	public List<AsqResultScoreEx> getAsqResultScoreEx(PageSortModel model,
			AsqResultScoreEx asqResultScoreEx) {
		StringBuilder conditions = new StringBuilder("");
		Map<String,Object> params = new HashMap<String,Object>();
		
		conditions.append("from AsqResultScoreEx c where 1=1 ");
		
		if(asqResultScoreEx!=null){
			if(asqResultScoreEx.getAsqTestUserInfo() != null && asqResultScoreEx.getAsqTestUserInfo().getId() != null ){
				conditions.append(" and c.asqTestUserInfo.id = :testUserId");
				params.put("testUserId", asqResultScoreEx.getAsqTestUserInfo().getId());
			}
			if(asqResultScoreEx.getAsqTaotiAge() != null && asqResultScoreEx.getAsqTaotiAge().getId() != null ){
				conditions.append(" and c.asqTaotiAge.id = :ageId");
				params.put("ageId", asqResultScoreEx.getAsqTaotiAge().getId());
			}
			if(asqResultScoreEx.getUserInfo() != null && asqResultScoreEx.getUserInfo().getId() != null ){
				conditions.append(" and c.userInfo.id = :userId");
				params.put("userId", asqResultScoreEx.getUserInfo().getId());
			}
		}
		conditions.append(" order by c.asqTaoti.id");
		
		List<AsqResultScoreEx> rss = new ArrayList<AsqResultScoreEx>();
		
		if(model==null){
			rss = super.list(conditions.toString(), -1, -1, params);
		}else{
			rss =  (List<AsqResultScoreEx>) super.listForEc(conditions.toString(), model, params);
		}
		return rss;
	}

	@Override
	public AsqResultScoreEx saveOrUpdateAsqResultScoreEx(
			AsqResultScoreEx asqResultScoreEx) {
		//医生解读
		AsqTestUserInfo page_asqTestUserInfo = asqResultScoreEx.getPage_asqTestUserInfo();
		//对应子标题总评分
		List<AsqResultScoreEx> page_asqResultScoreExlist = asqResultScoreEx.getPage_asqResultScoreExlist();
		//总评价
		AsqResultScoreExUser page_asqResultScoreExUser = asqResultScoreEx.getPage_asqResultScoreExUser();
		//阅卷
		if(page_asqTestUserInfo!=null){
			Long testuserid = page_asqTestUserInfo.getId();
			if(testuserid!=null){
				AsqTestUserInfo asqTestUserInfo = super.get(testuserid, AsqTestUserInfo.class);
				asqTestUserInfo.setDoctorReading(page_asqTestUserInfo.getDoctorReading());
				super.edit(asqTestUserInfo);
			}
		}
		if(page_asqResultScoreExlist!=null){
			for(AsqResultScoreEx rs :page_asqResultScoreExlist){
				Long rs_id = rs.getId();
				AsqResultScoreEx tmp_rs = super.get(rs_id, AsqResultScoreEx.class);
				tmp_rs.setSumScore(rs.getSumScore());
				tmp_rs.setResultDescription(rs.getResultDescription());
				tmp_rs.setStatus(AsqResultScoreEx.ALREAD_CHECK);
				super.edit(tmp_rs);
			}
		}
		if(page_asqResultScoreExUser!=null){
			Long rs_id = page_asqResultScoreExUser.getId();
			AsqResultScoreExUser rs = super.get(rs_id, AsqResultScoreExUser.class);
			rs.setResultDescription(page_asqTestUserInfo.getDoctorReading());
			rs.setStatus(AsqResultScoreEx.ALREAD_CHECK);
			super.edit(rs);
		}
		//发送
		if(asqResultScoreEx!=null&&asqResultScoreEx.getId()!=null){
			AsqResultScoreExUser rs = super.get(asqResultScoreEx.getId(), AsqResultScoreExUser.class);
			rs.setStatus(AsqResultScoreEx.SEND_TO_USER);
			super.edit(rs);
		}
		return null;
	}

	@Override
	public List<AsqQuestionRecord> getAsqQuestionRecord(
			AsqQuestionRecord asqQuestionRecord) {
		StringBuilder conditions = new StringBuilder("");
		Map<String,Object> params = new HashMap<String,Object>();
		
		conditions.append("from AsqQuestionRecord c where 1=1 ");
		
		if(asqQuestionRecord!=null){
			if(asqQuestionRecord.getAsqTestUserInfo()!=null&&asqQuestionRecord.getAsqTestUserInfo().getId()!=null){
				conditions.append(" and c.asqTestUserInfo.id = :asqTestUserId");
				params.put("asqTestUserId", asqQuestionRecord.getAsqTestUserInfo().getId());
			}
		}
		return super.list(conditions.toString(), -1, -1, params);
	}

	@Override
	public List<AsqResultScoreExUser> getAsqResultScoreExUser(
			PageSortModel model, AsqResultScoreExUser asqResultScoreExUser) {
		StringBuilder conditions = new StringBuilder("");
		Map<String,Object> params = new HashMap<String,Object>();
		
		conditions.append("from AsqResultScoreExUser c where 1=1 ");
		
		if(asqResultScoreExUser!=null){
			if(asqResultScoreExUser.getAsqTaotiAge()!=null&&StringUtils.isNotBlank(asqResultScoreExUser.getAsqTaotiAge().getShowName())){
				conditions.append(" and c.asqTaotiAge.showName like :showName");
				params.put("showName", "%"+asqResultScoreExUser.getAsqTaotiAge().getShowName()+"%");
			}
			if(StringUtils.isNotBlank(asqResultScoreExUser.getUserInfo().getPhone())){
				conditions.append(" and c.userInfo.phone like :phone");
				params.put("phone", "%"+asqResultScoreExUser.getUserInfo().getPhone()+"%");
			}
			if(StringUtils.isNotBlank(asqResultScoreExUser.getStatus())){
				conditions.append(" and c.status = :status");
				params.put("status", asqResultScoreExUser.getStatus());
			}
		}
		conditions.append(" order by c.asqTaotiAge.showName,c.modifyTime desc");
		
		List<AsqResultScoreExUser> rss = new ArrayList<AsqResultScoreExUser>();
		
		if(model==null){
			rss = super.list(conditions.toString(), -1, -1, params);
		}else{
			rss =  (List<AsqResultScoreExUser>) super.listForEc(conditions.toString(), model, params);
		}
		return rss;
	}

	@Override
	public List<AsqParentChildActivity> getAsqParentChildActivityList(
			PageSortModel model, AsqParentChildActivity asqParentChildActivity) {
		StringBuilder hql = new StringBuilder("from AsqParentChildActivity a where 1=1");
		Map<String,Object> params = new HashMap<String,Object>();
		if(asqParentChildActivity != null){
			if(asqParentChildActivity.getTaoti() != null && StringUtils.isNotEmpty(asqParentChildActivity.getTaoti().getTitalName())){
				hql.append(" and a.taoti.titalName like :titalName");
				params.put("titalName", "%"+asqParentChildActivity.getTaoti().getTitalName()+"%");
			}
		}
		hql.append(" order by a.taoti.id,a.applyMinMonthAge");
		List<AsqParentChildActivity> list =  (List<AsqParentChildActivity>) super.listForEc(hql.toString(), model, params);
		return list;
	}

	@Override
	public String saveOrUpdateAsqParentChildActivity(
			AsqParentChildActivity asqParentChildActivity) {
		String mes = "操作成功";
		if(asqParentChildActivity == null)  return "没有数据";
		if(asqParentChildActivity.getId() == null){
			this.add(asqParentChildActivity);
		}else{
			this.edit(asqParentChildActivity);
		}
		return mes;
	}

}
