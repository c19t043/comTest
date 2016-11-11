package com.kybaby.bo;

/**
 * @ClassName:DoctorAccountBo
 * @Description:医生账户余额的事务管理接口
 * @author Hoolee
 * @date 2015年9月21日下午2:56:54
 */
public interface DoctorAccountBo {
	
	/**
	 * 
	 * @author HooLee
	 * @Discription:增加一条新的医生账户变动记录
	 * @data: 2015年9月21日下午2:58:57
	 * @param doctorId 医生的ID
	 * @param amount 金额
	 * @param type 变动的类型
	 * @param accountDesc 变动的描述
	 */
	void addNewDoctorAccount(long doctorId,double amount,String type,String accountDesc);
}
