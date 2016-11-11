package com.wx.service;

import com.wx.persistence.beans.Token;
import com.wx.util.base.BaseService;

public  abstract interface TokenService extends BaseService<Token, Integer> {

 public	Token loadByAppId(String appid);


}
