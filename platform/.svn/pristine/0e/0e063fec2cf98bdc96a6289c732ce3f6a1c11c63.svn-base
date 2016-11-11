package com.java.operationmanage.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableFont.FontName;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.archivesinfo.vo.UserType;
import com.java.operationmanage.common.BooleanMsg;
import com.java.operationmanage.common.CBSMConstant;
import com.java.operationmanage.service.OperationmanageService;
import com.java.operationmanage.strategy.ChildCareScheduleStrategy;
import com.java.operationmanage.strategy.PeadiatricsScheduleStrategy;
import com.java.operationmanage.strategy.ScheduleStrategy;
import com.java.operationmanage.vo.Customer;
import com.java.operationmanage.vo.DoctorAccount;
import com.java.operationmanage.vo.DoctorInfo;
import com.java.operationmanage.vo.HospitalBasicInfo;
import com.java.operationmanage.vo.OpenClinicInfo;
import com.java.operationmanage.vo.OperaBaseSchedule;
import com.java.operationmanage.vo.OperaBusinessType;
import com.java.operationmanage.vo.OperaDoctorSchedule;
import com.java.operationmanage.vo.OperaWorkerSchedule;
import com.java.platform.user.service.ServiceImpl;
import com.java.platform.user.vo.User;
import com.java.util.DateManage;
import com.java.util.JsonUtil;
import com.java.util.LogUtil;

public class OperationmanageServiceImpl extends ServiceImpl implements OperationmanageService {

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1, Map<String, ?> arg2) {
		return null;
	}
	//获取用户类型
	public List<UserType> getUserTypes(){
		return super.getPersistProxy().getOrmPersistence().findByHQLQuery("from UserType");
	}
	/**
	 * 导出excel
	 * @param operaBaseSchedule
	 * @return
	 */
	public String exportDoctorSchedule2Excel(OperaBaseSchedule operaBaseSchedule){
		/*
		 * 获取,组装要导出的排班数据 
		 */
		List<OperaDoctorSchedule> all_ods = getExportExcelData(operaBaseSchedule);
		/*
		 * 制作保存excel
		 * 保存excel,返回excel保存地址
		 */
		return makeExcelAndRetSavePath(operaBaseSchedule,all_ods);
	}
	/**
	 * 制作保存excel
	 * 保存excel,返回excel保存地址
	 */
	private String makeExcelAndRetSavePath(OperaBaseSchedule operaBaseSchedule,List<OperaDoctorSchedule> all_ods){
		/*
		 * 设置导出excel头信息
		 */
		List<String> exportHeaderInfo = getExportHeaderInfo();
		/*
		 * 获取导出excel保存路径
		 */
		String exportBasePath = getExportBasePath();
		/*
		 * 设置导出文件名
		 */
		String fileName = getFileName(operaBaseSchedule);
		String fullName = exportBasePath+fileName;
		/*
		 * 写excel
		 */
		writeExcelFile(all_ods, exportHeaderInfo, fullName);
		return exportBasePath+fileName;
	}
	/**
	 * 写excel
	 */
	private void writeExcelFile(List<OperaDoctorSchedule> all_ods,List<String> exportHeaderInfo,String fullName){
		String webBasePath = ServletActionContext.getServletContext().getRealPath("/");
		File writeFile = new File(webBasePath+fullName);
		File parentFile = writeFile.getParentFile();
		if(!parentFile.exists()) {
			parentFile.mkdirs();
		}
		WritableWorkbook writeBook = null;
		try {
			writeBook = Workbook.createWorkbook(writeFile);
			WritableSheet writableSheet = writeBook.createSheet("第一页", 0);
			//给sheet电子版中所有的列设置默认的列的宽度;  
			writableSheet.getSettings().setDefaultColumnWidth(15); 
			String week = "";
			int count = 1;
			int actual_row = 0;
			for(int j=0,len=all_ods.size();j<len;j++){
				/*
				 * 设置投标题信息,和头信息
				 */
				if(j==0){
					actual_row = j+2;
					String startDate = all_ods.get(0).getOperaBaseSchedule().getOpenDate();
					String endDate = all_ods.get(len-1).getOperaBaseSchedule().getOpenDate();
					for(int i=0;i<exportHeaderInfo.size();i++){
						//new Label(col,row,content)
						writableSheet.addCell(new Label(i,0,startDate+"至"+endDate+"社区门诊排班表",formatCellStyle("title")));
						writableSheet.addCell(new Label(i,1,exportHeaderInfo.get(i).toString(),formatCellStyle("head")));
					}
				}
				OperaDoctorSchedule ods = all_ods.get(j);
				/*
				 * 开放日期转换为对应星期
				 */
				String tmp_week = setWeek(ods.getOperaBaseSchedule().getOpenDate());
				if(!week.equals(tmp_week)){
					for(int i=0;i<exportHeaderInfo.size();i++){
						writableSheet.addCell(new Label(i,actual_row,"",formatCellStyle("breakup")));
					}
					actual_row++;
					week = tmp_week;
					count = 1;
				}else{
					count++;
				}
				WritableCellFormat formatCellStyle = formatCellStyle("");
				writableSheet.addCell(new Label(0,actual_row,ods.getOperaBaseSchedule().getOpenDate(),formatCellStyle));
				writableSheet.addCell(new Label(1,actual_row,week,formatCellStyle));
				writableSheet.addCell(new Label(2,actual_row,count+"",formatCellStyle));
				writableSheet.addCell(new Label(3,actual_row,ods.getOperaBaseSchedule().getHospitalBasicInfo().getHospitalLname(),formatCellStyle));
				writableSheet.addCell(new Label(4,actual_row,ods.getWorkBeginTime()+"至"+ods.getWorkEndTime(),formatCellStyle));
				writableSheet.addCell(new Label(5,actual_row,ods.getRestBeginTime()+"至"+ods.getRestEndTime(),formatCellStyle));
				writableSheet.addCell(new Label(6,actual_row,ods.getOperaBusinessType().getName(),formatCellStyle));
				writableSheet.addCell(new Label(7,actual_row,ods.getDoctorInfo()==null?"无":ods.getDoctorInfo().getDoctorName(),formatCellStyle));
				writableSheet.addCell(new Label(8,actual_row,ods.getPublishStatus(),formatCellStyle));
				writableSheet.addCell(new Label(9,actual_row,ods.getRemarks(),formatCellStyle));
				writableSheet.addCell(new Label(10,actual_row,ods.getExportWorkerNames(),formatCellStyle));
				writableSheet.addCell(new Label(11,actual_row,ods.getExportWorkerRemarks(),formatCellStyle));
				actual_row++;
			}
			
			//优化单元格
			optimizeCells(writableSheet);
			
			writeBook.write();
		} catch (IOException e) {
			LogUtil.error("", e);
		} catch (RowsExceededException e) {
			LogUtil.error("", e);
		} catch (WriteException e) {
			LogUtil.error("", e);
		}finally{
			try {
				writeBook.close();
			} catch (WriteException | IOException e) {
				LogUtil.error("", e);
			}
		}
	}
	/**
	 * 优化单元格
	 * @param writableSheet
	 */
	private void optimizeCells(WritableSheet writableSheet){
		try {
			//合并单元格
			mergeCell(writableSheet);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 优化单元格样式
	 * @throws WriteException 
	 */
	private WritableCellFormat formatCellStyle(String styleFlag) throws WriteException{
		WritableCellFormat cellFormat = new WritableCellFormat(); 
  
        //设置边框;  
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);  
        //设置自动换行;  
        //cellFormat.setWrap(true);  
        //设置文字居中对齐方式;  
        cellFormat.setAlignment(Alignment.CENTRE);  
        //设置垂直居中;  
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE); 
		
        //设置字体;
        WritableFont font = null;
        
        FontName createFont = WritableFont.createFont("宋体");
        switch(styleFlag){
        case "title":
        	font = new WritableFont(createFont,25,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK); 
        	break;
        case "head":
        	font = new WritableFont(createFont,14,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
        	break;
        case "breakup":
            //设置背景颜色;  
            cellFormat.setBackground(Colour.YELLOW2);  
        	font = new WritableFont(createFont,14,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.YELLOW2); 
        	break;
        default:
        	font = new WritableFont(createFont,14,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
        }
        
        cellFormat.setFont(font);
        return cellFormat;
	}
	/**
	 * 合并单元格
	 */
	private void mergeCell(WritableSheet writableSheet) throws RowsExceededException, WriteException{
		int rows = writableSheet.getRows();
		String tmp_content = null;
		int tmp_row = 0;
		for(int i=0;i<rows;i++){
			if(0==i){
				Cell[] row = writableSheet.getRow(0);
				writableSheet.mergeCells(0, 0, row.length-1, 0);
			}
			String contents = writableSheet.getCell(1, i).getContents();
			//第一次初始化赋值
			if(tmp_content==null) {
				tmp_content = contents;
				tmp_row = i;
			}
			//如果tmp_content不为Null,并且这一列的值content==tmpContent不做处理
			//如果content!=tmpcontent则合并单元格
			if(!contents.equals(tmp_content)){
				/*
				 * 不是最后一行
				 * 上一行和下一行的content不一样
				 */
				writableSheet.mergeCells(0, tmp_row, 0, i-1);
				writableSheet.mergeCells(1, tmp_row, 1, i-1);
				tmp_content = contents;
				tmp_row = i;
			}else if(contents.equals(tmp_content)&&i==rows-1){
				/*
				 * 最后一行
				 * 上一行和下一行的content一样
				 */
				writableSheet.mergeCells(0, tmp_row, 0, i);
				writableSheet.mergeCells(1, tmp_row, 1, i);
			}else if(!contents.equals(tmp_content)&&i==rows-1){
				/*
				 * 最后一行
				 * 上一行和下一行的content不一样,合并同content开始row到上一行的row
				 */
				writableSheet.mergeCells(0, tmp_row, 0, i-1);
				writableSheet.mergeCells(1, tmp_row, 1, i-1);
			}
		}
	}
	/**
	 * 开放日期转换为对应星期
	 * @param openDate
	 * @return
	 */
	private String setWeek(String openDate){
		Calendar c = Calendar.getInstance();
		c.setTime(DateManage.parseStr2Date_yyyy_MM_dd(openDate));
		int i = c.get(Calendar.DAY_OF_WEEK);
		//输出对应中文星期
		return convertWeek(i);
	}
	/**
	 * 输出对应中文星期
	 * @param i
	 * @return
	 */
	private String convertWeek(int i){
		String week = "";
		switch (i) {
		case 1:week= "星期日";
			break;
		case 2: week= "星期一";
			break;
		case 3:week= "星期二";
			break;
		case 4:week= "星期三";
			break;
		case 5:week= "星期四";
			break;
		case 6:week= "星期五";
			break;
		case 7:week= "星期六";
			break;
		default:week="星期计算错误";
		}
		return week;
	}
	/**
	 * 设置导出文件名
	 */
	private String getFileName(OperaBaseSchedule operaBaseSchedule){
		String openDate = operaBaseSchedule.getOpenDate();
		String endDate = operaBaseSchedule.getEndDate();
		String fileName = "";
		String extName = ".xls";
		if(StringUtils.isNotBlank(openDate)&&StringUtils.isNotBlank(endDate)){
			fileName = openDate+"至"+endDate+"排班"+extName;
		}else{
			fileName = DateManage.formatDateStr_yyyy_MM_dd(new Date())+extName;
		}
		LogUtil.debug(fileName);
		return fileName;
	}
	/**
	 * 获取导出excel保存路径
	 */
	private String getExportBasePath(){
		String filePath = this.getClass().getResource("/").getPath()+"/com/java/operationmanage/common/cbsmConfig.properties";
		File file = new File(filePath);
		if(!file.exists()){
			throw new RuntimeException("社区排班配置文件不存在");
		}
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String basePath = prop.getProperty(CBSMConstant.EXPORT_PATH);
		if(StringUtils.isBlank(basePath)){
			throw new RuntimeException("没有配置社区排班导出文件的根路径");
		}
		return basePath;
	}
	/**
	 * 获取,组装要导出的排班数据 
	 */
	private List<OperaDoctorSchedule> getExportExcelData(OperaBaseSchedule operaBaseSchedule){
		List<OperaDoctorSchedule> all_ods = new ArrayList<OperaDoctorSchedule>();
		if(operaBaseSchedule==null) {
			operaBaseSchedule = new OperaBaseSchedule();
		}
		operaBaseSchedule.setOrderby("asc");
		//要导出的排班数据
		List<OperaBaseSchedule> operaBaseSchedules = getOperaBaseSchedules(null, operaBaseSchedule);
		for (OperaBaseSchedule obs : operaBaseSchedules) {
			//医生排班
			List<OperaDoctorSchedule> operaDoctorSchedules = obs.getOperaDoctorSchedules();
			
			//工作人员排班
			StringBuilder workerNames = new StringBuilder();
			StringBuilder workerRemarks =  new StringBuilder();
			List<OperaWorkerSchedule> operaWorkerSchedules = obs.getOperaWorkerSchedules();
			
			if(!operaWorkerSchedules.isEmpty()&&operaDoctorSchedules.isEmpty())  {
				OperaDoctorSchedule operaDoctorSchedule = new OperaDoctorSchedule();
				BeanUtils.copyProperties(operaWorkerSchedules.get(0), operaDoctorSchedule);
				operaDoctorSchedules.add(operaDoctorSchedule);
			}
			
			for (OperaWorkerSchedule operaWorkerSchedule : operaWorkerSchedules) {
				workerNames.append(operaWorkerSchedule.getUser().getUserInfo().getName())
				.append("[")
				.append(operaWorkerSchedule.getOperaBusinessType().getName())
				.append("]")
				.append(",");
				
				if(StringUtils.isNotBlank(operaWorkerSchedule.getRemarks())){
					workerRemarks.append(operaWorkerSchedule.getUser().getUserInfo().getName())
					.append("[")
					.append(operaWorkerSchedule.getRemarks())
					.append("]")
					.append(",");				
				}
			}
			for (OperaDoctorSchedule ods : operaDoctorSchedules) {
				ods.setExportWorkerNames(workerNames.toString());;
				ods.setExportWorkerRemarks(workerRemarks.toString());
			}
			
			all_ods.addAll(operaDoctorSchedules);
		}
		return all_ods;
	}
	/**
	 * 设置导出excel头信息
	 */
	private List<String> getExportHeaderInfo(){
		List<String> tmp_ls = new ArrayList<String>();
		tmp_ls.add("日期");
		tmp_ls.add("星期");
		tmp_ls.add("序号");
		tmp_ls.add("社区");
		tmp_ls.add("上班时间");
		tmp_ls.add("休息时间");
		tmp_ls.add("类型");
		tmp_ls.add("医生");
		tmp_ls.add("发布状态");
		tmp_ls.add("医生备注");
		tmp_ls.add("工作人员");
		tmp_ls.add("工作人员备注");
		return tmp_ls;
	}
	/**
	 * 发布医生排班
	 * @param doctorScheduleID 医生排班ID
	 * @return
	 */
	public BooleanMsg savePublishDoctorSchedule(Long doctorScheduleID){
		OperaDoctorSchedule operaDoctorSchedule = super.get(doctorScheduleID, OperaDoctorSchedule.class);
		BooleanMsg booleanMsg  = scheduleAdapter(operaDoctorSchedule);
		if(booleanMsg.isTrue()){
			operaDoctorSchedule.setPublishStatus(CBSMConstant.PUBLISH_STATUS_NUM_YES);
			operaDoctorSchedule.setSrcID((Long)booleanMsg.getObject());
			super.edit(operaDoctorSchedule);
			
			Long baseID = operaDoctorSchedule.getOperaBaseSchedule().getId();
			List<OperaDoctorSchedule> operaDoctorSchedules = getOperaDoctorSchedules(baseID);
			
			/*
			 * 修改基础发布状态
			 */
			StringBuilder publishStatuss = new StringBuilder();
			for (OperaDoctorSchedule ods : operaDoctorSchedules) {
				if(publishStatuss.indexOf(ods.getPublishStatus()+",")==-1){
					publishStatuss.append(ods.getPublishStatus()).append(",");
				}
			}
			String basePublishStatus = publishStatusJudge(publishStatuss.toString());
			OperaBaseSchedule operaBaseSchedule = super.get(baseID, OperaBaseSchedule.class);
			operaBaseSchedule.setPublishStatus(basePublishStatus);
			super.edit(operaBaseSchedule);
		}
		return booleanMsg;
	}
	/**
	 * 排班适配器
	 * @param operaDoctorSchedule
	 * @return
	 */
	private BooleanMsg scheduleAdapter(OperaDoctorSchedule operaDoctorSchedule){
		String scheduleType = operaDoctorSchedule.getScheduleType();
		/*
		 * 选择排班
		 */
		ScheduleStrategy ss = null;
		if(CBSMConstant.SCHEDULETYPE_CHILDCARE.equals(scheduleType)){//儿保
			ss =  new ChildCareScheduleStrategy(this);
		}else if(CBSMConstant.SCHEDULETYPE_PEADIATRICS.equals(scheduleType)){//儿科
			ss =  new PeadiatricsScheduleStrategy(this);
		}
		//验证
		BooleanMsg booleaMsg = ss.isPassWithPublishCondition(operaDoctorSchedule);
		/*
		 * 发布排班
		 */
		if(booleaMsg.isTrue()){
			Long resID = null;
			String publishStatus = operaDoctorSchedule.getPublishStatus();
			if(CBSMConstant.PUBLISH_STATUS_NUM_NO.equals(publishStatus)){
				resID = ss.addSchedule(operaDoctorSchedule);
			}else if(CBSMConstant.PUBLISH_STATUS_NUM_MODIFY.equals(publishStatus)){
				resID = ss.updateSchedule(operaDoctorSchedule);
			}
			booleaMsg.setObject(resID);
		}
		return booleaMsg;
	}
	/*
	 * 1.排班基本信息,增改查
	 */
	@SuppressWarnings("unchecked")
	public List<OperaBaseSchedule> getOperaBaseSchedules(PageSortModel model,OperaBaseSchedule operaBaseSchedule){
		StringBuilder condition = new StringBuilder("");
		Map<String,Object> params = new HashMap<String,Object>();
		
		condition.append("from OperaBaseSchedule c where 1=1 ");
		
		if(operaBaseSchedule!=null){
			if(StringUtils.isNotBlank(operaBaseSchedule.getIsEnable())){
				condition.append("and c.isEnable = :isEnable ");
				params.put("isEnable", operaBaseSchedule.getIsEnable());
			}
			if(StringUtils.isNotBlank(operaBaseSchedule.getOpenDate())){
				condition.append("and c.openDate >= :openDate ");
				params.put("openDate", operaBaseSchedule.getOpenDate());
			}
			if(StringUtils.isNotBlank(operaBaseSchedule.getEndDate())){
				condition.append("and c.openDate <= :endDate ");
				params.put("endDate", operaBaseSchedule.getEndDate());
			}
			if(operaBaseSchedule.getHospitalBasicInfo()!=null&&StringUtils.isNotBlank(operaBaseSchedule.getHospitalBasicInfo().getHospitalLname())){
				condition.append("and c.hospitalBasicInfo.hospitalLname like :orgName ");
				params.put("orgName", "%"+operaBaseSchedule.getHospitalBasicInfo().getHospitalLname()+"%");
			}
			if(StringUtils.isNotBlank(operaBaseSchedule.getPublishStatus())){
				condition.append("and c.publishStatus = :publishStatus ");
				params.put("publishStatus", operaBaseSchedule.getPublishStatus());
			}
			if(StringUtils.isNotBlank(operaBaseSchedule.getDoctorName())){
				List<OperaDoctorSchedule> ods_clect = getOperaDoctorSchedule(operaBaseSchedule.getDoctorName(),null,null);
				StringBuilder ids = new StringBuilder(); 
				for (OperaDoctorSchedule operaDoctorSchedule : ods_clect) {
					if(operaDoctorSchedule.getOperaBaseSchedule()!=null&&
							ids.indexOf(operaDoctorSchedule.getOperaBaseSchedule().getId()+",")==-1){
							ids.append(operaDoctorSchedule.getOperaBaseSchedule().getId()).append(",");
					}
				}
				
				String id = ids.length()==0?"0":ids.deleteCharAt(ids.length()-1).toString();
				condition.append("and c.id in ("+id+") ");
			}
			if(StringUtils.isNotBlank(operaBaseSchedule.getBusinessType())){
				List<OperaDoctorSchedule> ods_clect = getOperaDoctorSchedule(null,operaBaseSchedule.getBusinessType(),null);
				StringBuilder ids = new StringBuilder(); 
				for (OperaDoctorSchedule operaDoctorSchedule : ods_clect) {
					if(operaDoctorSchedule.getOperaBaseSchedule()!=null&&
							ids.indexOf(operaDoctorSchedule.getOperaBaseSchedule().getId()+",")==-1)
						ids.append(operaDoctorSchedule.getOperaBaseSchedule().getId()).append(",");
				}
				
				String id = ids.length()==0?"0":ids.deleteCharAt(ids.length()-1).toString();
				condition.append("and c.id in ("+id+") ");
			}
			if(StringUtils.isNotBlank(operaBaseSchedule.getIsFamilyDoctor())){
				List<OperaDoctorSchedule> ods_clect = getOperaDoctorSchedule(null,null,operaBaseSchedule.getIsFamilyDoctor());
				StringBuilder ids = new StringBuilder(); 
				for (OperaDoctorSchedule operaDoctorSchedule : ods_clect) {
					if(operaDoctorSchedule.getOperaBaseSchedule()!=null&&
							ids.indexOf(operaDoctorSchedule.getOperaBaseSchedule().getId()+",")==-1)
						ids.append(operaDoctorSchedule.getOperaBaseSchedule().getId()).append(",");
				}
				
				String id = ids.length()==0?"0":ids.deleteCharAt(ids.length()-1).toString();
				condition.append("and c.id in ("+id+") ");
			}
		}
		
		if(operaBaseSchedule!=null&&StringUtils.isNotBlank(operaBaseSchedule.getOrderby())){
			condition.append(" order by  c.openDate asc ");
		}else{
			condition.append(" order by  c.openDate desc ");
		}
		
		List<OperaBaseSchedule> listForEc = null;
		if(model==null){
			listForEc = super.list(condition.toString(), -1, -1, params);
		}else{
			listForEc = (List<OperaBaseSchedule>)super.listForEc(condition.toString(), model, params);
		}
		
		for (OperaBaseSchedule obs : listForEc) {
			Long baseID = obs.getId();
			List<OperaDoctorSchedule> ods_tmp = new ArrayList<OperaDoctorSchedule>();
			
			List<OperaDoctorSchedule> operaDoctorSchedules = this.getOperaDoctorSchedules(baseID);
			
			for (OperaDoctorSchedule operaDoctorSchedule : operaDoctorSchedules) {
				/*
				 * 如果筛选信息中有医生or业务类型,医生排班信息也只展示相关信息
				 */
				boolean doctor_isOK = true;
				boolean business_isOK = true;
				boolean FamilyDoctor_isOk = true;
				if(operaBaseSchedule!=null){
					if(StringUtils.isNotBlank(operaBaseSchedule.getDoctorName())){
						if(operaDoctorSchedule.getDoctorInfo()!=null&&
								!operaDoctorSchedule.getDoctorInfo().getDoctorName().contains(operaBaseSchedule.getDoctorName())){
							doctor_isOK = false;
						}
					}
					if(StringUtils.isNotBlank(operaBaseSchedule.getBusinessType())){
						if(operaDoctorSchedule.getOperaBusinessType()!=null&&
								!operaDoctorSchedule.getOperaBusinessType().getName().contains(operaBaseSchedule.getBusinessType())){
							business_isOK = false;
						}
					}
					if(StringUtils.isNotBlank(operaBaseSchedule.getIsFamilyDoctor())){
						if(!operaDoctorSchedule.getIsFamilyDoctor().equals(operaBaseSchedule.getIsFamilyDoctor())){
							FamilyDoctor_isOk = false;
						}
					}
				}
				if(doctor_isOK&&business_isOK&&FamilyDoctor_isOk) ods_tmp.add(operaDoctorSchedule);
			}
			obs.setOperaDoctorSchedules(ods_tmp);
			
			List<OperaWorkerSchedule> operaWorkerSchedules = this.getOperaWorkerSchedules(baseID);
			obs.setOperaWorkerSchedules(operaWorkerSchedules);
		}
		return listForEc;
	}
	/**
	 * 判断排班基础信息是否存在
	 * @param operaBaseSchedule
	 * @return
	 */
	public BooleanMsg isExistOfBaseSchedule(OperaBaseSchedule operaBaseSchedule){
		BooleanMsg booleanMsg = new BooleanMsg();
		String orgIDs =  operaBaseSchedule.getOrgIDs();
		List<OperaBaseSchedule> list = new ArrayList<OperaBaseSchedule>();
		if(JsonUtil.isNotEmpty(orgIDs)){
			String [] orgArr = orgIDs.split(",");
			String openDate = operaBaseSchedule.getOpenDate();
			String isEnable = operaBaseSchedule.getIsEnable();
			for (String orgID : orgArr) {
				OperaBaseSchedule obs = new  OperaBaseSchedule();
				HospitalBasicInfo hospitalBasicInfo = super.get(Long.parseLong(orgID), HospitalBasicInfo.class);
				obs.setHospitalBasicInfo(hospitalBasicInfo);
				
				obs.setOpenDate(openDate);
				obs.setIsEnable(isEnable);
				
				String hql = "from OperaBaseSchedule c where c.openDate = ? and c.hospitalBasicInfo.id = ?";
				OperaBaseSchedule obj = super.getPersistProxy().getOrmPersistence().findObjectByHQL(hql,
						new Object[]{obs.getOpenDate(),obs.getHospitalBasicInfo().getId()});
				if(obj!=null) list.add(obj);
			}
		}
		if(!list.isEmpty()){
			booleanMsg.isTrue(false);
		}else{
			booleanMsg.isTrue(true);
		}
		if(!booleanMsg.isTrue()){
			String openDate = null;
			StringBuilder sb = new StringBuilder();
			for (OperaBaseSchedule obs : list) {
				if(openDate==null ) openDate = obs.getOpenDate();
				sb.append(obs.getHospitalBasicInfo().getHospitalLname()).append(",");
			}
			booleanMsg.setMsg("在‘"+openDate+"’"+sb.toString()+"已存在排班信息,请误重复添加！");
		}
		return booleanMsg;
	}
	/**
	 * 根据医生姓名,业务类型查询对应医生排班信息
	 * @param doctorName
	 * @param businessType
	 * @param publishStatus
	 * @return
	 */
	private List<OperaDoctorSchedule> getOperaDoctorSchedule(String doctorName,String businessType,String isFamilyDoctor){
		StringBuilder hql = new StringBuilder("from OperaDoctorSchedule c where 1=1 ");
		Map<String,Object> params = new HashMap<String,Object>();
		hql.append("and c.isEnable = 'Y' ");
		
		if(StringUtils.isNotBlank(doctorName)){
			hql.append(" and c.doctorInfo.doctorName like :doctorName ");
			params.put("doctorName", "%"+doctorName+"%");
		}
		if(StringUtils.isNotBlank(businessType)){
			hql.append(" and c.operaBusinessType.name like :businessType ");
			params.put("businessType", "%"+businessType+"%");
		}
		if(StringUtils.isNotBlank(isFamilyDoctor)){
			hql.append(" and c.isFamilyDoctor = :isFamilyDoctor ");
			params.put("isFamilyDoctor", isFamilyDoctor);
		}
		hql.append(" and c.operaBaseSchedule <> null ");
		return super.list(hql.toString(), -1, -1, params);
	}
	/**
	 * 发布状态判断
	 * @param tmp_status
	 * @return 基础发布状态
	 */
	private String publishStatusJudge(String tmp_status) {
		String tmp_publishStatus = "";
		if(!tmp_status.contains(CBSMConstant.PUBLISH_STATUS_CN_YES+",")
				&&!tmp_status.contains(CBSMConstant.PUBLISH_STATUS_CN_MODIFY+",")){
			tmp_publishStatus = CBSMConstant.PUBLISH_STATUS_BASE_NO;
		}else if(!tmp_status.contains(CBSMConstant.PUBLISH_STATUS_CN_NO+",")
				&&!tmp_status.contains(CBSMConstant.PUBLISH_STATUS_CN_MODIFY+",")){
			tmp_publishStatus = CBSMConstant.PUBLISH_STATUS_BASE_ALL;
		}else{
			tmp_publishStatus = CBSMConstant.PUBLISH_STATUS_BASE_SECTION;
		}
		return tmp_publishStatus;
	}
	
	public OperaBaseSchedule saveOrUpdateOperaBaseSchedule(OperaBaseSchedule operaBaseSchedule){
		Long id = operaBaseSchedule.getId();
		if(id==null){
			super.add(operaBaseSchedule);
			return operaBaseSchedule;
		}else{
			OperaBaseSchedule obs = super.get(id, OperaBaseSchedule.class);
			BeanUtils.copyProperties(operaBaseSchedule, obs, new String[]{"id"});
			super.edit(obs);
			return obs;
		}
	}
	/*
	 * 2.排班医生信息,增改查
	 */
	public List<OperaDoctorSchedule> getOperaDoctorSchedules(Long baseID){
		StringBuilder condition = new StringBuilder("");
		condition.append("from OperaDoctorSchedule c where 1=1 ");
		condition.append("and c.isEnable = 'Y' ");
		
		if(baseID!=null) condition.append("and c.operaBaseSchedule.id = "+baseID);
		
		condition.append(" order by c.doctorInfo.doctorName desc ");
		List<OperaDoctorSchedule> ods_coll = super.getPersistProxy().getOrmPersistence().findByHQLQuery(condition.toString());
		//------------放置hibernate修改持久状态对象数据,自动保存持久化对象
		List<OperaDoctorSchedule> tmp_list = new ArrayList<OperaDoctorSchedule>();
		for (OperaDoctorSchedule operaDoctorSchedule : ods_coll) {
			OperaDoctorSchedule ods  = new  OperaDoctorSchedule();
			BeanUtils.copyProperties(operaDoctorSchedule, ods);
			String publishStatus = ods.getPublishStatus();
			switch(publishStatus){
				case CBSMConstant.PUBLISH_STATUS_NUM_NO:publishStatus=CBSMConstant.PUBLISH_STATUS_CN_NO;break;
				case CBSMConstant.PUBLISH_STATUS_NUM_YES:publishStatus=CBSMConstant.PUBLISH_STATUS_CN_YES;break;
				case CBSMConstant.PUBLISH_STATUS_NUM_MODIFY:publishStatus=CBSMConstant.PUBLISH_STATUS_CN_MODIFY;break;
			}
			ods.setPublishStatus(publishStatus);
			tmp_list.add(ods);
		}
		return tmp_list;
	}
	public OperaDoctorSchedule saveOrUpdateOperaDoctorSchedule(OperaDoctorSchedule operaDoctorSchedule){
		Long id = operaDoctorSchedule.getId();
		if(id==null){
			operaDoctorSchedule.setPublishStatus(CBSMConstant.PUBLISH_STATUS_NUM_NO);
			super.add(operaDoctorSchedule);
			if(operaDoctorSchedule.getOperaBaseSchedule()!=null){
				OperaBaseSchedule operaBaseSchedule = super.get(operaDoctorSchedule.getOperaBaseSchedule().getId(), OperaBaseSchedule.class);
				if(StringUtils.isBlank(operaBaseSchedule.getPublishStatus())) 
					operaBaseSchedule.setPublishStatus(CBSMConstant.PUBLISH_STATUS_BASE_NO);
				super.edit(operaBaseSchedule);
			}
			return operaDoctorSchedule;
		}else{
			OperaDoctorSchedule ods = super.get(id, OperaDoctorSchedule.class);
			String publishStatus = ods.getPublishStatus();
			if(CBSMConstant.PUBLISH_STATUS_NUM_YES.equals(publishStatus)){
				ods.setPublishStatus(CBSMConstant.PUBLISH_STATUS_NUM_MODIFY);
				ods.getOperaBaseSchedule().setPublishStatus(CBSMConstant.PUBLISH_STATUS_BASE_SECTION);
			}
			BeanUtils.copyProperties(operaDoctorSchedule, ods, new String[]{"id","publishStatus","srcID"});
			super.edit(ods);
			return ods;
		}
	}
	/*
	 * 3.排班工作人员信息,增改查
	 */
	public List<OperaWorkerSchedule> getOperaWorkerSchedules(Long baseID){
		StringBuilder condition = new StringBuilder("");
		condition.append("from OperaWorkerSchedule c where 1=1 ");
		condition.append("and c.isEnable = 'Y' ");
		if(baseID!=null) condition.append("and c.operaBaseSchedule.id = "+baseID);
		return super.getPersistProxy().getOrmPersistence().findByHQLQuery(condition.toString());
	}
	public OperaWorkerSchedule saveOrUpdateOperaWorkerSchedule(OperaWorkerSchedule operaWorkerSchedule){
		Long id = operaWorkerSchedule.getId();
		if(id==null){
			super.add(operaWorkerSchedule);
			return operaWorkerSchedule;
		}else{
			OperaWorkerSchedule ows = super.get(id, OperaWorkerSchedule.class);
			BeanUtils.copyProperties(operaWorkerSchedule, ows, new String[]{"id"});
			super.edit(ows);
			return ows;
		}
	}
	/*
	 * 4.排班业务类型信息,增改查
	 */
	public List<OperaBusinessType> getOperaBusinessTypes(){
		StringBuilder condition = new StringBuilder("");
		condition.append("from OperaBusinessType c where 1=1 ");
		condition.append("and c.isEnable = 'Y' ");
		return super.getPersistProxy().getOrmPersistence().findByHQLQuery(condition.toString());
	}
	public OperaBusinessType saveOrUpdateOperaBusinessType(OperaBusinessType operaBusinessType){
		Long id = operaBusinessType.getId();
		if(id==null){
			super.add(operaBusinessType);
			return operaBusinessType;
		}else{
			OperaBusinessType obt = super.get(id, OperaBusinessType.class);
			BeanUtils.copyProperties(operaBusinessType, obt, new String[]{"id"});
			super.edit(obt);
			return obt;
		}
	}
	//---------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//---------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public List<OpenClinicInfo> getOpenClinicInfoPageList(PageSortModel psm, OpenClinicInfo openClinicInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<OpenClinicInfo> list = null;
		StringBuilder hql = new StringBuilder("FROM OpenClinicInfo o WHERE 1=1 ");
		// 条件查询
		if (openClinicInfo != null) {
			if (openClinicInfo.getBusinessType() != null && !"".equals(openClinicInfo.getBusinessType())) {
				params.put("businessType", openClinicInfo.getBusinessType());
				hql.append(" AND o.businessType = :businessType");
			}
		}
		hql.append(" ORDER BY o.openClinicDate DESC");
		list = (List<OpenClinicInfo>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<DoctorInfo> getDoctorInfoList(PageSortModel psm, DoctorInfo doctorInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<DoctorInfo> list = null;
		StringBuilder hql = new StringBuilder("FROM DoctorInfo d WHERE 1=1 ");
		params.put("authentication", "已通过");
		hql.append(" AND d.authentication = :authentication");
		// 条件查询
		if (doctorInfo != null) {
			if (doctorInfo.getDoctorName() != null && !"".equals(doctorInfo.getDoctorName())) {
				params.put("doctorName", "%" + doctorInfo.getDoctorName() + "%");
				hql.append(" AND d.doctorName LIKE :doctorName");
			}
		}
		list = (List<DoctorInfo>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}

	@Override
	public String saveOpenClinicInfo(OpenClinicInfo openClinicInfo,
			DoctorInfo doctorInfo, User amDoctorHelperUser,
			User amExtensionUser, User pmDoctorHelperUser, User pmExtensionUser) {
		
		if (doctorInfo!=null && doctorInfo.getId()!=null && !"".equals(doctorInfo.getId())) {
				DoctorInfo saveDoctorInfo = this.get(doctorInfo.getId(), DoctorInfo.class);
				openClinicInfo.getDoctorSet().add(saveDoctorInfo);
		}
		if (amDoctorHelperUser!=null && amDoctorHelperUser.getUserId()!=null && !"".equals(amDoctorHelperUser.getUserId())) {
			String[] userIds = amDoctorHelperUser.getUserId().split(",");
			for (String userId : userIds) {
				User saveUser = this.get(userId, User.class);
				openClinicInfo.getAmDoctorHelperSet().add(saveUser);
			}
		}
		if (pmDoctorHelperUser!=null && pmDoctorHelperUser.getUserId()!=null && !"".equals(pmDoctorHelperUser.getUserId())) {
			String[] userIds = pmDoctorHelperUser.getUserId().split(",");
			for (String userId : userIds) {
				User saveUser = this.get(userId, User.class);
				openClinicInfo.getPmDoctorHelperSet().add(saveUser);
			}
		}
		if (amExtensionUser!=null && amExtensionUser.getUserId()!=null && !"".equals(amExtensionUser.getUserId())) {
			String[] userIds = amExtensionUser.getUserId().split(",");
			for (String userId : userIds) {
				User saveUser = this.get(userId, User.class);
				openClinicInfo.getAmExtensionSet().add(saveUser);
			}
		}
		if (pmExtensionUser!=null && pmExtensionUser.getUserId()!=null && !"".equals(pmExtensionUser.getUserId())) {
			String[] userIds = pmExtensionUser.getUserId().split(",");
			for (String userId : userIds) {
				User saveUser = this.get(userId, User.class);
				openClinicInfo.getPmExtensionSet().add(saveUser);
			}
		}
		this.add(openClinicInfo);
		return "保存信息成功！";
	}

	@Override
	public String updateOpenClinicInfo(OpenClinicInfo openClinicInfo,
			DoctorInfo doctorInfo, User amDoctorHelperUser,
			User amExtensionUser, User pmDoctorHelperUser, User pmExtensionUser) {
		
		OpenClinicInfo updateOpenClinicInfo = this.get(openClinicInfo.getId(), OpenClinicInfo.class);
		updateOpenClinicInfo.getDoctorSet().clear();
		updateOpenClinicInfo.getAmDoctorHelperSet().clear();
		updateOpenClinicInfo.getPmDoctorHelperSet().clear();
		updateOpenClinicInfo.getAmExtensionSet().clear();
		updateOpenClinicInfo.getPmExtensionSet().clear();
		
		if (doctorInfo!=null && doctorInfo.getId()!=null && !"".equals(doctorInfo.getId())) {
				DoctorInfo saveDoctorInfo = this.get(doctorInfo.getId(), DoctorInfo.class);
				updateOpenClinicInfo.getDoctorSet().add(saveDoctorInfo);
		}
		if (amDoctorHelperUser!=null && amDoctorHelperUser.getUserId()!=null && !"".equals(amDoctorHelperUser.getUserId())) {
				String[] userIds = amDoctorHelperUser.getUserId().split(",");
				for (String userId : userIds) {
					User saveUser = this.get(userId, User.class);
					updateOpenClinicInfo.getAmDoctorHelperSet().add(saveUser);
				}
		}
		if (pmDoctorHelperUser!=null && pmDoctorHelperUser.getUserId()!=null && !"".equals(pmDoctorHelperUser.getUserId())) {
				String[] userIds = pmDoctorHelperUser.getUserId().split(",");
			for (String userId : userIds) {
				User saveUser = this.get(userId, User.class);
				updateOpenClinicInfo.getPmDoctorHelperSet().add(saveUser);
			}
		}
		if (amExtensionUser!=null && amExtensionUser.getUserId()!=null && !"".equals(amExtensionUser.getUserId())) {
			String[] userIds = amExtensionUser.getUserId().split(",");
			for (String userId : userIds) {
				User saveUser = this.get(userId, User.class);
				updateOpenClinicInfo.getAmExtensionSet().add(saveUser);
			}
		}
		if (pmExtensionUser!=null && pmExtensionUser.getUserId()!=null && !"".equals(pmExtensionUser.getUserId())) {
			String[] userIds = pmExtensionUser.getUserId().split(",");
			for (String userId : userIds) {
				User saveUser = this.get(userId, User.class);
				updateOpenClinicInfo.getPmExtensionSet().add(saveUser);
			}
		}
		updateOpenClinicInfo.setBusinessType(openClinicInfo.getBusinessType());
		updateOpenClinicInfo.setIsEffective(openClinicInfo.getIsEffective());
		updateOpenClinicInfo.setOpenClinicDate(openClinicInfo.getOpenClinicDate());
		updateOpenClinicInfo.setOpenContent(openClinicInfo.getOpenContent());
		updateOpenClinicInfo.setRemark(openClinicInfo.getRemark());
		updateOpenClinicInfo.setTimeSlot(openClinicInfo.getTimeSlot());
		updateOpenClinicInfo.setState(openClinicInfo.getState());
		updateOpenClinicInfo.setMoney(openClinicInfo.getMoney());
		updateOpenClinicInfo.setPlanStartTime(openClinicInfo.getPlanStartTime());
		updateOpenClinicInfo.setPlanEndTime(openClinicInfo.getPlanEndTime());
		updateOpenClinicInfo.setActualStartTime(openClinicInfo.getActualStartTime());
		updateOpenClinicInfo.setActualEndTime(openClinicInfo.getActualEndTime());
		updateOpenClinicInfo.setHospitalBasicInfo(openClinicInfo.getHospitalBasicInfo());
		this.edit(updateOpenClinicInfo);
		
		OpenClinicInfo OpenClinicInfo = this.get(updateOpenClinicInfo.getId(), OpenClinicInfo.class);
		System.out.println("OpenClinicInfo :"+ OpenClinicInfo );
		//把医生完成任务的酬劳存到医生的余额里面去
		String state = OpenClinicInfo.getState();
		Double money = OpenClinicInfo.getMoney();
		Set<DoctorInfo> doctorSet = OpenClinicInfo.getDoctorSet();
		//把钱打到医生账户是，同时在医生交易记录表里面把记录存上
		DoctorAccount account = new DoctorAccount();
		for (DoctorInfo doctorInfo2 : doctorSet) {
			if("已完成".equals(state)){
				Double doctorBalance = doctorInfo2.getDoctorBalance();
				doctorBalance+=money;
				doctorInfo2.setDoctorBalance(doctorBalance);
				//保存交易记录表的信息
				account.setDoctorId(doctorInfo2.getId());
				account.setAmount(money);
				account.setType("+");
				account.setAccountDesc(OpenClinicInfo.getBusinessType()+"酬劳");
				account.setUpdateTime(DateManage.getDateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
				this.add(account);
			}
		}
		updateOpenClinicInfo.setDoctorSet(doctorSet);
		return "更新信息成功！";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomerPageList(PageSortModel psm, Customer customer) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Customer> list = null;
		StringBuilder hql = new StringBuilder("FROM Customer o WHERE 1=1 ");
		// 条件查询
		if (customer != null) {
			if (customer.getPhone() != null && !"".equals(customer.getPhone())) {
				params.put("phone", "%" + customer.getPhone() + "%");
				hql.append(" AND o.phone LIKE :phone");
			}
			if (customer.getSource() != null && !"".equals(customer.getSource())) {
				params.put("source", customer.getSource());
				hql.append(" AND o.source = :source");
			}
			if (customer.getBabyName() != null && !"".equals(customer.getBabyName())) {
				params.put("babyName", "%" + customer.getBabyName() + "%");
				hql.append(" AND o.babyName LIKE :babyName");
			}
			if (customer.getParentName() != null && !"".equals(customer.getParentName())) {
				params.put("parentName", "%" + customer.getParentName() + "%");
				hql.append(" AND o.parentName LIKE :parentName");
			}
		}
		list = (List<Customer>) listForEc(String.valueOf(hql), psm, params);
		return list;
	}

	@Override
	public String saveCustomer(Customer customer) {
		this.add(customer);
		return "保存信息成功！";
	}
	
	@Override
	public String updateCustomer(Customer customer) {
		//Customer updateCustomer = this.get(customer.getId(), Customer.class);
		//BeanUtils.copyProperties(customer, updateCustomer);
		//this.edit(updateCustomer);
		this.edit(customer);
		if (customer.getHospitalBasicInfo()==null || customer.getHospitalBasicInfo().getId()==null) {
			customer.setHospitalBasicInfo(null);
		}
		if (customer.getDistrict()==null || customer.getDistrict().getDistrictCode()==null) {
			customer.setDistrict(null);
		}
		return "更新信息成功！";
	}

	@Override
	public List<HospitalBasicInfo> getHospitalBasicInfoList(HospitalBasicInfo hospitalBasicInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<HospitalBasicInfo> list = null;
		StringBuilder hql = new StringBuilder("FROM HospitalBasicInfo h WHERE 1=1 ");
		params.put("hospitalType",  "社区医院");
		hql.append(" AND h.hospitalType = :hospitalType");
		// 条件查询
		if (hospitalBasicInfo != null) {
			if (hospitalBasicInfo.getHospitalLname() != null && !"".equals(hospitalBasicInfo.getHospitalLname())) {
				params.put("hospitalLname",  "%" + hospitalBasicInfo.getHospitalLname() + "%");
				hql.append(" AND h.hospitalLname LIKE :hospitalLname");
			}
		}
		list = this.list(hql.toString(), -1, -1, params);
		return list;
	}
}
