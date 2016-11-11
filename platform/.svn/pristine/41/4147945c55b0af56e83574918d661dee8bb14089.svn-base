package com.java.consultmanager.consultdoctormanager.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.java.common.lang.StringUtils;
import com.java.consultmanager.consultdoctormanager.vo.ConsultDoctorCommission;
import com.java.consultmanager.consultdoctormanager.vo.Position;
import com.java.ec.common.PageSortModel;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.user.service.Service;

public interface ConsultDoctorCommissionService extends Service {

	/**
	 * 得到医生列表
	 * @param psm
	 * @return
	 */
	List<DoctorInfo> getDoctorInfoListByPage(PageSortModel psm,DoctorInfo doctorInfo,String positionName);
	
	/**
	 * 获取所有职称描述信息
	 * 
	 * @return
	 */
	public List<Position> getPositionInfo();

	/**
	 * 增加一条咨询医生级别报酬分配配置记录
	 * 
	 * @param consultDoctorCommission
	 * @return 添加成功,返回true,失败，返回false
	 */
	public boolean addDoctorCommissionInfo(
			ConsultDoctorCommission consultDoctorCommission);

	/**
	 * 根据Id,删除一条咨询医生级别报酬分配配置记录
	 * 
	 * @param id
	 * @return 删除成功,返回true,失败，返回false
	 */
	public boolean deleteDoctorCommissionInfo(Long id);

	/**
	 * 修改一条咨询医生级别报酬分配配置记录
	 * 
	 * @param consultDoctorCommission
	 * @return 修改成功,返回true,失败，返回false
	 */
	public boolean updateDoctorCommissionInfo(
			ConsultDoctorCommission consultDoctorCommission);

	/**
	 * 根据id,获取一条咨询医生级别报酬分配配置记录
	 * 
	 * @param id
	 * @return
	 */
	public ConsultDoctorCommission getDoctorCommissionInfo(Long id);

	/**
	 * 获取所有咨询医生级别报酬分配配置记录 职称名字：commission.name 1.如果name 为空，查询所有信息 2.如果name
	 * 有值,查询对应职称的信息
	 * 
	 * @return
	 */
	public List<ConsultDoctorCommission> getDoctorCommissionInfoList(
			PageSortModel model, ConsultDoctorCommission commission);
	
	/**
	 * 批量添加医生信息,
	 * 根据医生级别，分配默认报酬
	 * @param doctorIds 医生ids
	 * @param commissionId 报酬表id
	 * @return
	 */
	public boolean addDoctorInfos(String[] doctorIds, String commissionId);
}
