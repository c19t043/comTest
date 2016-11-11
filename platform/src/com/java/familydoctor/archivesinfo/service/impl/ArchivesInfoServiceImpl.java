package com.java.familydoctor.archivesinfo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.service.ArchivesInfoService;
import com.java.familydoctor.archivesinfo.vo.ArchivesInfo;
import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.platform.user.service.ServiceImpl;
import com.java.util.DateManage;

public class ArchivesInfoServiceImpl extends ServiceImpl implements ArchivesInfoService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		
		return null;
	}

	@Override
	public Long saveOrUpdateArchivesInfo(ArchivesInfo archivesInfo) {
		if(archivesInfo == null){
			return null;
		}
		Long id = null;
		if(archivesInfo.getId() == null){
			if(archivesInfo.getEffectiveTime() != null && !"".equals(archivesInfo.getEffectiveTime())){
				//通过当前时间和获取有效时长算除到期时间
				String effectiveTime = archivesInfo.getEffectiveTime();
				int parseInt = Integer.parseInt(effectiveTime);
				String beforeOrAfter = DateManage.getBeforeOrAfter(parseInt);
				archivesInfo.setExpireTime(beforeOrAfter);
			}
			//保存创建时间
			archivesInfo.setCreatTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			id = (Long) super.add(archivesInfo);
			
			//一个拷贝属性的工具类
//			BeanUtils.copyProperties(source, target, ignoreProperties);
		}else{
			id = archivesInfo.getId();
			if(archivesInfo.getEffectiveTime() != null && !"".equals(archivesInfo.getEffectiveTime())){
				//通过当前时间和获取有效时长算除到期时间
				String effectiveTime = archivesInfo.getEffectiveTime();
				int parseInt = Integer.parseInt(effectiveTime);
				String beforeOrAfter = DateManage.getBeforeOrAfter(parseInt);
				archivesInfo.setExpireTime(beforeOrAfter);
			}
			//保存修改时间
			archivesInfo.setModifyTime(DateManage.getDateStr("yyyy-MM-dd HH:mm:ss"));
			super.edit(archivesInfo);
		}
		return id;
	}

	@Override
	public ArchivesInfo getArchivesInfoById(Long id) {
		return super.get(id,ArchivesInfo.class);
	}

	@Override
	public List<ArchivesInfo> getArchivesInfoByPage(PageSortModel psm,
			ArchivesInfo archivesInfo) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("from ArchivesInfo a where 1=1");
		if(archivesInfo != null){
			if(archivesInfo.getChildrenName() != null && !"".equals(archivesInfo.getChildrenName())){
				param.put("childrenName","%" +archivesInfo.getChildrenName() +"%");
				hql.append(" and a.childrenName LIKE :childrenName");
			}
			if(archivesInfo.getArchivesMobile() != null && !"".equals(archivesInfo.getArchivesMobile())){
				param.put("archivesMobile", "%" + archivesInfo.getArchivesMobile() + "%");
				hql.append(" and a.archivesMobile LIKE :archivesMobile");
			}
			if(archivesInfo.getHospitalBasicInfo() != null && 
					StringUtils.isNotEmpty(archivesInfo.getHospitalBasicInfo().getHospitalLname())){
				param.put("hospitalLname", "%" + archivesInfo.getHospitalBasicInfo().getHospitalLname() + "%");
				hql.append(" and a.hospitalBasicInfo.hospitalLname LIKE :hospitalLname");
			}
		}
		
		List<ArchivesInfo> list = (List<ArchivesInfo>) listForEc(hql.toString(),psm,param);
		return list;
	}

	@Override
	public List<UserType> getUserTypeList(PageSortModel psm, UserType userType) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("from UserType u where 1=1");
		hql.append(" and u.isEnable = 'Y'");
		if(userType != null){
			if(userType.getTypeName() != null && !"".equals(userType.getTypeName())){
				param.put("typeName","%" +userType.getTypeName() +"%");
				hql.append(" and u.typeName LIKE :typeName");
			}
		}
		List<UserType> list = (List<UserType>) listForEc(hql.toString(),psm,param);
		return list;
	}
}