package com.kybaby.action.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.DoctorGoodField;
import com.kybaby.domain.DoctorInfo;
import com.kybaby.domain.Major;
import com.kybaby.domain.Position;
import com.kybaby.domain.SymptomTag;
import com.kybaby.newbussiness.doctorclinic.domain.HospitalBasicInfo;
import com.kybaby.newbussiness.doctorclinic.domain.RoleSelect;
import com.kybaby.newbussiness.doctorclinic.fo.DoctorServiceTypeFo;
import com.kybaby.util.EncryptUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author sujiantang
 *
 */
public class DoctorIdentify extends BaseAction{
	

	private DoctorInfo doctorInfo;
	private List<Position>  positionList;
	private String mes;
	private Long rank;
	private List<List> productNameList;
	private List<Major> majorList;
	private List<DoctorGoodField> goodFieldList;
	private List<SymptomTag> symptomTag;
	private String doctorName;
	private String doctorSex;
	private Long doctorTitle;
	private String doctorEmployer;
	private String advisoryLabelIds;
	private String serviceMode;
	private String bankAccountName;
	private String bankCard;
	private String comeMethod;
	private Long serviceArea;
	private String majorId;
	private String productIds;
	private File updateMyHeadFileElem;
	private File updateDoctorHeadFileElem;
	private String uploadDir = "../kybabyBG/admin/images/doctorCertifiedPicture";
	private String type;//上传头像的类型
	private String imagedata;
	private String idCard;
	/**
	 * 医院信息集合
	 */
	private List<HospitalBasicInfo> hospitalBasicInfoList;
	/**
	 * 医院id
	 */
	private Long hospitalBasicInfoId;
	/**
	 * '所在科室';
	 */
	private String  department; 
	/**
	 * '擅长领域';
	 */
	private String  goodAtField;
	/**
	 * 临床经验
	 */
	private String clinicalExperience;
	/**
	 * 服务内容集合
	 */
	private List<DoctorServiceTypeFo> doctorServiceTypeFoList = new ArrayList<DoctorServiceTypeFo>();
	/**
	 * 选择的角色
	 */
	private String roleName;
	
	/**
	 * 服务项目ids
	 */
	private String serviceTypeIds;
	/**
	 * 技师服务地点及类型::分割
	 */
	private String serviceAddType;
	@Override
	public String execute() throws IOException{
		
		if(action.equals("getSomething")){
			majorList = new ArrayList<Major>();
			symptomTag = new ArrayList<SymptomTag>();
			positionList = new ArrayList<Position>();
			positionList = doctorIdentifyBo.getAllPosition();
			majorList = doctorIdentifyBo.getAllMajor();
			symptomTag = doctorIdentifyBo.getAllSymptomTag();
			hospitalBasicInfoList = new ArrayList<HospitalBasicInfo>();
			hospitalBasicInfoList = doctorIdentifyBo.getHospitalBasicInfoList(null);
			this.goodFieldList = doctorIdentifyBo.getAllDoctorGoodField();
			this.doctorServiceTypeFoList = this.doctorIdentifyBo.getAllDoctorServiceTypeFo();
			mes="成功";
			return "success";
		}
		if(action.equals("getProduct")){
			productNameList = new ArrayList<List>();
			productNameList = doctorIdentifyBo.getProductNameByRank(rank);
		}
		if(action.equals("initDoctorInfo")){//初始化医生信息（申请认证以后再次进入）
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo == null){
				mes="请登录";
				return "fail";
			}
			RoleSelect roleSelect = this.roleSelectBo.getRoleSelectByPhone(doctorInfo.getDoctorPhone());
			doctorInfo.setDoctorType(roleSelect.getRoleType());
			hospitalBasicInfoList = new ArrayList<HospitalBasicInfo>();
			hospitalBasicInfoList = doctorIdentifyBo.getHospitalBasicInfoList(null);
			this.doctorServiceTypeFoList = this.doctorIdentifyBo.getAllDoctorServiceTypeFo();
			mes="成功";
			return "success";
		}
		if(action.equals("submited")){
			doctorInfo = (DoctorInfo)ActionContext.getContext().getSession().get("Doctor");
			if(doctorInfo==null){
				mes="请登录";
				return "success";
			}
			if(doctorInfo!=null){
				String title = doctorIdentifyBo.getOptionById(doctorTitle);
				doctorInfo.setAuthentication("已申请");
				doctorInfo.setDoctorName(doctorName);
				doctorInfo.setDoctorSex(doctorSex);
				doctorInfo.setDoctorTitle(title);
				doctorInfo.setDoctorEmployer(doctorEmployer);
				doctorInfo.setAdvisoryLabelIds(advisoryLabelIds);
				doctorInfo.setServiceMode(serviceMode);
				doctorInfo.setBankAccountName(bankAccountName);
				doctorInfo.setBankCard(bankCard);
				doctorInfo.setComeMethod(comeMethod);
				doctorInfo.setServiceArea(serviceArea);
				doctorInfo.setMajorId(majorId);
				doctorInfo.setProductIds(productIds);
				doctorInfo.setIdCard(idCard);
				doctorInfo.setHospitalId(hospitalBasicInfoId);
				doctorInfo.setDepartment(department);
				doctorInfo.setGoodAtField(goodAtField);
				doctorInfo.setClinicalExperience(clinicalExperience);
				doctorInfo.setServiceTypeIds(serviceTypeIds);
				doctorInfo.setServiceAddType(serviceAddType);
				doctorIdentifyBo.update(doctorInfo);
				this.doctorInfo = this.doctorInfoBo.getDoctorInfoBoById(doctorInfo.getId());
				ActionContext.getContext().getSession().put("Doctor", doctorInfo);
				//保存服务类型医生关系表
				if(serviceTypeIds != null && !"".equals(serviceTypeIds)){
					this.doctorIdentifyBo.saveDoctorServiceContent(doctorInfo, serviceTypeIds);
				}
				
				//保存角色
				if (roleName!=null && !"".equals(roleName)) {
					String doctorPhone = doctorInfo.getDoctorPhone();
					//查询之前是否保存过
					RoleSelect rs =	roleSelectBo.getRoleSelectByPhone(doctorPhone);
					if (rs==null) {
						RoleSelect roleSelect = new RoleSelect();
						roleSelect.setPhone(doctorPhone);
						roleSelect.setRoleType(roleName);
						roleSelectBo.save(roleSelect);;
					}
				}
				mes="成功";
				return "success";
			}
			mes="请登录";
			return "fail";
		}
		
		if(action.equals("image")){
			
			DoctorInfo user = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(user==null){
				mes = "重新登录";
				return "fail";
			}
			String fileName=user.getLicenseImage();
			if(null==fileName||fileName.equals(""))
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
				String current = df.format(new Date());
				fileName="DI" + current +".jpg";	
			}
			String fileAllName=uploadDir+"/"+fileName;
			String dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
			if(EncryptUtil.uploadImage(imagedata,dir))
				{
					user.setLicenseImage(fileName);
					doctorIdentifyBo.update(user);
					ActionContext.getContext().getSession().put("Doctor", user);
				    mes="成功";
				    return "success";
				}
				else
				{
					mes="失败";
					return "fail";
				}
		}
		
		if(action.equals("headImg")){
			uploadDir="../kybabyBG/admin/images/doctorFaceIcon";
			DoctorInfo user = (DoctorInfo) ActionContext.getContext().getSession().get("Doctor");
			if(user==null){
				mes = "重新登录";
				return "fail";
			}
			String fileName=user.getDoctorImage();
			if(null==fileName||fileName.equals(""))
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
				String current = df.format(new Date());
				fileName="DH" + current +".jpg";
				
			}
			String fileAllName=uploadDir+"/"+fileName;
			String dir = ServletActionContext.getServletContext().getRealPath(fileAllName);
			if(EncryptUtil.uploadImage(imagedata,dir))
				{
					user.setDoctorImage(fileName);
					doctorIdentifyBo.update(user);
					ActionContext.getContext().getSession().put("Doctor", user);
				    mes="成功";
				    return "success";
				}
				else
				{
					mes="失败";
					return "fail";
				}
		}
		return "success";
		
	}
	
	private static void copy(File src, File dst) { 
        InputStream in = null; 
        OutputStream out = null; 
        try { 
            in = new BufferedInputStream(new FileInputStream(src), 2048); 
            out = new BufferedOutputStream(new FileOutputStream(dst),2048); 
            byte[] buffer = new byte[2048]; 
            int len = 0; 
            while ((len = in.read(buffer)) > 0) { 
                out.write(buffer, 0, len); 

            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            if (null != in) { 
                try { 
                    in.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
            if (null != out) { 
                try { 
                    out.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
        } 
    }

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public List<Position> getPositionList() {
		return positionList;
	}

	@Override
	public String getMes() {
		return mes;
	}

	public List<List> getProductNameList() {
		return productNameList;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public List<Major> getMajorList() {
		return majorList;
	}

	public List<SymptomTag> getSymptomTag() {
		return symptomTag;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public void setDoctorSex(String doctorSex) {
		this.doctorSex = doctorSex;
	}

	public void setDoctorTitle(Long doctorTitle) {
		this.doctorTitle = doctorTitle;
	}

	public void setDoctorEmployer(String doctorEmployer) {
		this.doctorEmployer = doctorEmployer;
	}

	public void setAdvisoryLabelIds(String advisoryLabelIds) {
		this.advisoryLabelIds = advisoryLabelIds;
	}

	public void setServiceMode(String serviceMode) {
		this.serviceMode = serviceMode;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public void setComeMethod(String comeMethod) {
		this.comeMethod = comeMethod;
	}

	public void setServiceArea(Long serviceArea) {
		this.serviceArea = serviceArea;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public void setUpdateMyHeadFileElem(File updateMyHeadFileElem) {
		this.updateMyHeadFileElem = updateMyHeadFileElem;
	}
	
	public void setUpdateDoctorHeadFileElem(File updateDoctorHeadFileElem) {
		this.updateDoctorHeadFileElem = updateDoctorHeadFileElem;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setImagedata(String imagedata) {
		this.imagedata = imagedata;
	}
	
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public List<HospitalBasicInfo> getHospitalBasicInfoList() {
		return hospitalBasicInfoList;
	}

	public void setHospitalBasicInfoId(Long hospitalBasicInfoId) {
		this.hospitalBasicInfoId = hospitalBasicInfoId;
	}

	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}

	@Override
	public void setMes(String mes) {
		this.mes = mes;
	}

	public void setProductNameList(List<List> productNameList) {
		this.productNameList = productNameList;
	}

	public void setMajorList(List<Major> majorList) {
		this.majorList = majorList;
	}

	public void setSymptomTag(List<SymptomTag> symptomTag) {
		this.symptomTag = symptomTag;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setGoodAtField(String goodAtField) {
		this.goodAtField = goodAtField;
	}

	public void setClinicalExperience(String clinicalExperience) {
		this.clinicalExperience = clinicalExperience;
	}

	public List<DoctorServiceTypeFo> getDoctorServiceTypeFoList() {
		return doctorServiceTypeFoList;
	}

	public void setServiceTypeIds(String serviceTypeIds) {
		this.serviceTypeIds = serviceTypeIds;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setServiceAddType(String serviceAddType) {
		this.serviceAddType = serviceAddType;
	}

	public List<DoctorGoodField> getGoodFieldList() {
		return goodFieldList;
	}
}
