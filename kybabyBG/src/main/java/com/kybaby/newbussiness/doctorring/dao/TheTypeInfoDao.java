package com.kybaby.newbussiness.doctorring.dao;

import java.util.List;

import com.kybaby.domain.DoctorRingType;
import com.kybaby.domain.TheTypeInfo;

/**
 * @ClassName:TheTypeInfoDao
 * @Description:医生圈分类数据管理接口
 * @author Hoolee
 * @date 2015年12月10日下午4:41:51
 */
public interface TheTypeInfoDao {

	/**
	 * 
	 * @author HooLee
	 * @Discription:获取系统中所有有效的、按照分类typeSort字段从小到大排序的医生圈的分类名称和ID
	 * @data: 2015年12月10日16:57:01
	 * @return 系统中所有有效的、按照分类typeSort字段从小到大排序的医生圈的分类名称和ID
	 */
	List<Object[]> getAllRingCategory();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:获取系统中被标记的医生圈
	 * @data: 2015年12月10日下午5:33:47
	 * @return 系统中被标记的医生圈
	 */
	Object[] getRemarkRingCategory();
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:通过分类的ID获取分类的实例
	 * @data: 2015年12月10日19:15:39
	 * @param categoryId 医生圈分类的ID
	 * @return 医生圈分类的实例
	 */
	TheTypeInfo getSomeCategoryInstanceById(long categoryId);
	/**
	 * 得到所有的医生圈一级分类标签集合
	 * @param theTypeInfo
	 * @return
	 */
	List<TheTypeInfo> getTheTypeInfoList(TheTypeInfo theTypeInfo);
	/**
	 * 保存或更新一级标签信息
	 * @param theTypeInfo
	 * @return
	 */
	Long saveOrUpdateTheTypeInfo(TheTypeInfo theTypeInfo);
	/**
	 * 得到所有的医生圈二级分类标签集合
	 * @param theTypeInfo
	 * @return
	 */
	List<DoctorRingType> getDoctorRingTypeListByTheTypeInfoId(TheTypeInfo theTypeInfo);
	/**
	 * 得到所有的医生圈二级分类标签
	 * @param theTypeInfo
	 * @return
	 */
	Long saveOrUpdateDoctorRingType(DoctorRingType doctorRingType);
}
