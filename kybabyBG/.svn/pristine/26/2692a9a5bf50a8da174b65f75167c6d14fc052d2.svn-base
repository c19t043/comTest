package com.kybaby.bo.impl;

import java.util.List;

import com.kybaby.bo.PositionBo;
import com.kybaby.dao.PositionDao;
import com.kybaby.domain.Position;

public class  PositionBoImpl implements PositionBo {

	PositionDao positionDao;
	@Override
	public List<Position> getAllPosition() {
		// TODO Auto-generated method stub
		return positionDao.getAllPosition();
	}

	@Override
	public Position getPositionById(long id) {
		// TODO Auto-generated method stub
		return positionDao.getPositionById(id);
	}

	@Override
	public Position getPositionByName(String name) {
		// TODO Auto-generated method stub
		return positionDao.getPositionByName(name);
	}

	public PositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	@Override
	public List getIdAndNameOfPosition() {
		// TODO Auto-generated method stub
		return positionDao.getIdAndNameOfPosition();
	}

	
//	//2.3.6.1医生职称管理
//	List<Position> getAllPosition(); //获取所有状态Y和N的职称列表
//	Position getPositionById(long id);//通过id实例找到该职称
//	Position getPositionByName(String name);//通过职称名字name找到该职称
}
