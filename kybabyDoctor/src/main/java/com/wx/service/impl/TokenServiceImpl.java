package com.wx.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.persistence.beans.Token;
import com.wx.persistence.mapper.TokenMapper;
import com.wx.service.TokenService;
import com.wx.util.exception.ServiceException;
@Service
public class TokenServiceImpl implements TokenService {
	   @Autowired
	   private TokenMapper tokenMapper;
	
	@Override
	public Integer insert(Token paramT) throws ServiceException {
		
		 return this.tokenMapper.insert(paramT);
	}


	@Override
	public boolean delete(Integer paramPK) {
	
		 try
	     {
	       this.tokenMapper.delete(paramPK);
	       return true; } catch (ServiceException e) {
	     }
	     return false;
	}

	
	@Override
	public Token load(Token paramT) throws ServiceException {
		
		return null;
	}

	

	@Override
	public boolean update(Token paramT) {
		
		 try
	     {
	       this.tokenMapper.update(paramT);
	       return true; } catch (ServiceException e) {
	     }
	     return false;
	}

	
	@Override
	public int countAll() throws ServiceException {
	
		return 0;
	}


	@Override
	public List<Token> findAll(Token paramT) throws ServiceException {
		
		return null;
	}

	
	@Override
	public List<Token> listPage(Token paramT) throws ServiceException {
		
		return null;
	}

	
	@Override
	public List<Integer> listCar(Token paramT) throws ServiceException {
	
		return null;
	}


	@Override
	public Token loadByAppId(String appId) throws ServiceException {
		
	     return this.tokenMapper.loadByAppId(appId);

	}


	@Override
	public boolean insertORupdate(Token paramT) {
	
		return false;
	}


	@Override
	public Token loadByPK(Integer paramPK) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
