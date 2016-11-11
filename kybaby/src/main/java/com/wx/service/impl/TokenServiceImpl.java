package com.wx.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.persistence.beans.SysMenu;
import com.wx.persistence.beans.Token;
import com.wx.persistence.mapper.*;
import com.wx.persistence.mapper.TokenMapper;
import com.wx.service.TokenService;
import com.wx.util.exception.ServiceException;
@Service
public class TokenServiceImpl implements TokenService {
	   @Autowired
	   private TokenMapper tokenMapper;
	
	public Integer insert(Token paramT) throws ServiceException {
		
		 return (Integer)this.tokenMapper.insert(paramT);
	}


	public boolean delete(Integer paramPK) {
	
		 try
	     {
	       this.tokenMapper.delete(paramPK);
	       return true; } catch (ServiceException e) {
	     }
	     return false;
	}

	
	public Token load(Token paramT) throws ServiceException {
		
		return null;
	}

	

	public boolean update(Token paramT) {
		
		 try
	     {
	       this.tokenMapper.update(paramT);
	       return true; } catch (ServiceException e) {
	     }
	     return false;
	}

	
	public int countAll() throws ServiceException {
	
		return 0;
	}


	public List<Token> findAll(Token paramT) throws ServiceException {
		
		return null;
	}

	
	public List<Token> listPage(Token paramT) throws ServiceException {
		
		return null;
	}

	
	public List<Integer> listCar(Token paramT) throws ServiceException {
	
		return null;
	}


	public Token loadByAppId(String appId) throws ServiceException {
		
	     return (Token)this.tokenMapper.loadByAppId(appId);

	}


	public boolean insertORupdate(Token paramT) {
	
		return false;
	}


	public Token loadByPK(Integer paramPK) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
