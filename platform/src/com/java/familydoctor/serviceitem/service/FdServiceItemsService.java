package com.java.familydoctor.serviceitem.service;

import java.util.List;

import com.java.ec.common.PageSortModel;
import com.java.familydoctor.serviceitem.vo.FdServiceItems;
import com.java.platform.user.service.Service;

public interface FdServiceItemsService extends Service{
	/**
	 * 家医服务项目分页列表
	 * @param psm
	 * @param fdServiceItems
	 * @return
	 */
	List<FdServiceItems> getFdServiceItemsListByPage(PageSortModel psm,FdServiceItems fdServiceItems);
	/**
	 * 保存或更新服务项目
	 * @param fdServiceItems
	 * @return
	 */
	Long saveOrUpdateFdServiceItems(FdServiceItems fdServiceItems);
	/**
	 * 根据id得到服务项目实体
	 * @param id
	 * @return
	 */
	FdServiceItems getFdServiceItemsById(Long id);
}
