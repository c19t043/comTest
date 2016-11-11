package com.kybaby.newbussiness.familydoctor.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kybaby.action.NewBaseAction;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceMember;
import com.kybaby.newbussiness.familydoctor.domain.FdServicePackage;
import com.kybaby.newbussiness.familydoctor.domain.FdServiceTeams;
import com.kybaby.util.ConstantManage;
import com.opensymphony.xwork2.ActionContext;

public class FamilydoctorOnlineAskManage extends NewBaseAction{
	private static final long serialVersionUID = 1L;
	/**
	 * 反馈到前端的提示信息
	 */
	private String mes = "成功";
	/**
	 * 家庭医生服务包
	 */
	private FdServicePackage fdServicePackage;
	/**
	 * 服务团队信息
	 */
	private FdServiceTeams fdServiceTeams;
	/**
	 * 咨询医生页面的排序方式
	 */
	private String sortMethod;
	/**
	 * //咨询医生页面反馈到前端的
	 */
	private List<Object[]> consultationDoctorList=new ArrayList<Object[]>();
	/**
	 * //医生的专长方向列表
	 */
	private List<List<String>> doctorMajorList=new ArrayList<List<String>>();
	public String execute(){
		Long userId = (Long) ActionContext.getContext().getSession().get("userId");
		if(userId==null){
			mes="请登录";
			return "fail";
		}
		/**
		 * 得到家庭医生咨询服务列表
		 */
		if (action.equals("getConsultationDoctorList")){
			//得到当前用户的家庭医生团队
			FdServicePackage oldPackage = this.familyDoctorBo.getFdServicePackageById(fdServicePackage.getId());
			List<FdServiceMember> memberList = null;
			if(fdServiceTeams == null || fdServiceTeams.getId() == null){
				Iterator<FdServiceTeams> teamsIt = oldPackage.getFdServiceTeamsSet().iterator();  
				while(teamsIt.hasNext()) { 
		        	memberList = this.fdServiceItemsService.getFdServiceMemberList(teamsIt.next(),null);
		        	break;
		        }
			}else if(fdServiceTeams.getId() != null){
				memberList = this.fdServiceItemsService.getFdServiceMemberList(fdServiceTeams,null);
			}
			
			if(memberList == null || memberList.isEmpty()){
				mes="无咨询医生";
				return "fail";
			}
			List<Object[]> consultationDoctorList = doctorInfoBo.getConsulationDoctoSomeInfo();
			if(consultationDoctorList!=null){
				for(FdServiceMember member : memberList){
					for(int i =0;i<consultationDoctorList.size();i++){
						Object[] doctorI=consultationDoctorList.get(i);
						Long doctorId = Long.valueOf(doctorI[0].toString());
						if(doctorId.longValue() == member.getDoctorInfo().getId().longValue() &&
								member.getSkilNames() != null && member.getSkilNames().indexOf(ConstantManage.FD_zixun) > -1){
							this.consultationDoctorList.add(doctorI);
						}
					}
				}
			}
			//获取医生的专长方向名称列表
			if(consultationDoctorList!=null && !consultationDoctorList.isEmpty()){
				for(int i =0;i<consultationDoctorList.size();i++){
					Object[] doctorI=consultationDoctorList.get(i);
					String majorIds=(String)doctorI[1];
					List<String> majorNameList=majorBo.getMajorNameListByIdStr(majorIds);
					doctorMajorList.add(majorNameList);
				}
				//存在可以咨询的医生，按照sortMethod进行排序
				if(sortMethod.equals("welcome")){
					for(int i =0;i<consultationDoctorList.size();i++){
						for(int j=i+1;j<consultationDoctorList.size();j++){
							Object[] doctorI=consultationDoctorList.get(i);
							Object[] doctorJ=consultationDoctorList.get(j);
							long visitedTimesI = doctorI[4]==null?0L:(long)doctorI[4];
							long visitedTimesJ = doctorJ[4]==null?0L:(long)doctorJ[4];
							if(visitedTimesI<visitedTimesJ){
								consultationDoctorList.set(i, doctorJ);
								consultationDoctorList.set(j, doctorI);

								//专长方向排序
								List<String> doctorMajor=doctorMajorList.get(i);
								doctorMajorList.set(i, doctorMajorList.get(j));
								doctorMajorList.set(j, doctorMajor);
							}
						}
					}
				} else if(sortMethod.equals("expert")){
					for(int i =0;i<consultationDoctorList.size();i++){
						for(int j=i+1;j<consultationDoctorList.size();j++){
							Object[] doctorI=consultationDoctorList.get(i);
							Object[] doctorJ=consultationDoctorList.get(j);
							if((long)doctorI[3]<(long)doctorJ[3]){
								consultationDoctorList.set(i, doctorJ);
								consultationDoctorList.set(j, doctorI);

								//专长方向排序
								List<String> doctorMajor=doctorMajorList.get(i);
								doctorMajorList.set(i, doctorMajorList.get(j));
								doctorMajorList.set(j, doctorMajor);
							}
						}
					}
				} else if(sortMethod.equals("isOnline")){
					for(int i =0;i<consultationDoctorList.size();i++){
						Object[] doctorI=consultationDoctorList.get(i);
						if(((String)doctorI[5]).equals("N")){
							consultationDoctorList.remove(i);
							doctorMajorList.remove(i);
						}
					}
				}

				mes="操作成功";
				return "success";
			}else{
				mes="无咨询医生";
				return "fail";
			}
		}
		return SUCCESS;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public FdServicePackage getFdServicePackage() {
		return fdServicePackage;
	}
	public void setFdServicePackage(FdServicePackage fdServicePackage) {
		this.fdServicePackage = fdServicePackage;
	}
	public String getSortMethod() {
		return sortMethod;
	}
	public void setSortMethod(String sortMethod) {
		this.sortMethod = sortMethod;
	}
	public List<Object[]> getConsultationDoctorList() {
		return consultationDoctorList;
	}
	public void setConsultationDoctorList(List<Object[]> consultationDoctorList) {
		this.consultationDoctorList = consultationDoctorList;
	}
	public List<List<String>> getDoctorMajorList() {
		return doctorMajorList;
	}
	public void setDoctorMajorList(List<List<String>> doctorMajorList) {
		this.doctorMajorList = doctorMajorList;
	}
	public FdServiceTeams getFdServiceTeams() {
		return fdServiceTeams;
	}
	public void setFdServiceTeams(FdServiceTeams fdServiceTeams) {
		this.fdServiceTeams = fdServiceTeams;
	}
}
