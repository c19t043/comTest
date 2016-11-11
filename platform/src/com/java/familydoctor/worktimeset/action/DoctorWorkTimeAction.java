package com.java.familydoctor.worktimeset.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.worktimeset.service.DoctorWorkTimeService;
import com.java.familydoctor.worktimeset.vo.DoctorWorkTime;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.platform.core.Action;

public class DoctorWorkTimeAction extends Action{
	private static final long serialVersionUID = 1L;
	private DoctorWorkTimeService doctorWorkTimeService;
	private DoctorWorkTime doctorWorkTime;
	/**
	 * 列表
	 * @return
	 */
	public String getDoctorWorkTimeList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(doctorWorkTime == null){
			doctorWorkTime = new DoctorWorkTime();
		}
		List<DoctorWorkTime> list = this.doctorWorkTimeService.getDoctorWorkTimeByPage(psm, doctorWorkTime);
		this.putToRequest("list", list);
		return SUCCESS;
	}
	/**
	 * 跳转添加或编辑页面
	 * @return
	 */
	public String toJumpDoctorWorkTime(){
		if(doctorWorkTime != null && doctorWorkTime.getId() != null){
			this.doctorWorkTime = this.doctorWorkTimeService.getDoctorWorkTimeById(doctorWorkTime.getId());
			if(doctorWorkTime != null){
				if(doctorWorkTime.getDoctorInfo() != null){
					doctorWorkTime.setDoctorIds(doctorWorkTime.getDoctorInfo().getId()+"");
					doctorWorkTime.setDoctorNames(doctorWorkTime.getDoctorInfo().getDoctorName());
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * 保存或更新
	 * @return
	 */
	public String saveOrUpdateDoctorWorkTime(){
		if(doctorWorkTime != null){
			if(StringUtils.isNotEmpty(doctorWorkTime.getDoctorIds())){
				String[] docyorIdArr = doctorWorkTime.getDoctorIds().split(",");
				for (int i = 0; i < docyorIdArr.length; i++) {
					DoctorWorkTime tiaojianDoctorWorkTime = new DoctorWorkTime();
					DoctorInfo doctorInfo = new DoctorInfo();
					//查询条件
					doctorInfo.setId(Long.valueOf(docyorIdArr[i].toString()));
					tiaojianDoctorWorkTime.setDoctorInfo(doctorInfo);
					DoctorWorkTime oldDoctorWorkTime = new DoctorWorkTime();
					List<DoctorWorkTime> oldDoctorWorkTimeList = this.doctorWorkTimeService.getDoctorWorkTimeByPage(null, tiaojianDoctorWorkTime);
					if(oldDoctorWorkTimeList != null && !oldDoctorWorkTimeList.isEmpty()){
						oldDoctorWorkTime = oldDoctorWorkTimeList.get(0);
						BeanUtils.copyProperties(doctorWorkTime, oldDoctorWorkTime, new String[]{"id"});
					}else{
						BeanUtils.copyProperties(doctorWorkTime, oldDoctorWorkTime);
						oldDoctorWorkTime.setId(null);
					}
					oldDoctorWorkTime.setDoctorInfo(doctorInfo);
					this.doctorWorkTimeService.saveOrUpdateDoctorWorkTime(oldDoctorWorkTime);
				}
			}
		}
		addActionMessage("操作成功");
		return redirectActionResult("doctorWorkTimeList");
	}
	public DoctorWorkTimeService getDoctorWorkTimeService() {
		return doctorWorkTimeService;
	}
	public void setDoctorWorkTimeService(DoctorWorkTimeService doctorWorkTimeService) {
		this.doctorWorkTimeService = doctorWorkTimeService;
	}
	public DoctorWorkTime getDoctorWorkTime() {
		return doctorWorkTime;
	}
	public void setDoctorWorkTime(DoctorWorkTime doctorWorkTime) {
		this.doctorWorkTime = doctorWorkTime;
	}
}
