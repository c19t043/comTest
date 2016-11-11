package com.kybaby.operationbussiness.redpacket.dao.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kybaby.operationbussiness.redpacket.dao.RedPacketDao;
import com.kybaby.operationbussiness.redpacket.domain.RedPacket;
import com.kybaby.operationbussiness.redpacket.domain.RedPacketSet;
import com.kybaby.operationbussiness.redpacket.util.MoneyUtils;
import com.kybaby.operationbussiness.redpacket.util.RedPacketConstant;

public class RedPacketDaoImpl extends HibernateDaoSupport implements RedPacketDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public RedPacketSet getRedPacketSetInfo() {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("from RedPacketSet where isEnabled='Y' ");
		List<RedPacketSet> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public float getGrantAmount(RedPacketSet redPacketSet) {
		if (redPacketSet!=null && redPacketSet.getSurplusAmount()!=null) {
			//发放金额
			float grantAmount;
			if (RedPacketSet.GRANTTYPE_QUOTA.equals(redPacketSet.getGrantType())) {
				//定额发放方式
				grantAmount = Float.parseFloat(redPacketSet.getQuotaValue());
			} else {
				//随机发放方式
				grantAmount = Float.parseFloat(getRandom(Float.parseFloat(redPacketSet.getMinSection()), Float.parseFloat(redPacketSet.getMaxSection())));
			}
			//判定剩余金额是否大于需要发放的金额
			if (Float.parseFloat(redPacketSet.getSurplusAmount()) >= grantAmount) {
				return grantAmount;
			} else {
				return 0;
			}
		}
		return 0;
	}
	
	//得到区间随机数
	private String getRandom(float min, float max) {
        BigDecimal db = new BigDecimal(Math.random() * (max - min) + min);
        return db.setScale(1, BigDecimal.ROUND_HALF_UP).toString(); //保留1位小数并四舍五入 
    }

	@Override
	public String sendRedpacket(String reOpenid, int totalAmount) {
		String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
		String orderNNo =  MoneyUtils.getOrderNo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nonce_str", MoneyUtils.buildRandom());//随机字符串
		map.put("mch_billno", orderNNo);//商户订单
		map.put("mch_id", RedPacketConstant.partner);//商户号
		map.put("wxappid", RedPacketConstant.wxappid);//微信appid
		map.put("nick_name", "康优宝贝");//提供方名称
		map.put("send_name", "康优宝贝");//用户名
		map.put("re_openid", reOpenid);//用户openid
		map.put("total_amount", totalAmount);//付款金额(单位：分)
		map.put("min_value", totalAmount);//最小红包
		map.put("max_value", totalAmount);//最大红包
		map.put("total_num", 1);//红包发送总人数
		map.put("wishing", "恭喜发财，身体健康！");//红包祝福语
		//map.put("client_ip", "127.0.0.1");//ip地址
		map.put("client_ip", "101.204.240.249");//ip地址
		map.put("act_name", "领取红包");//活动名称
		map.put("remark", "领取红包");//备注
		map.put("sign", MoneyUtils.createSign(map));//签名
		String result = "";
		try {
			result = MoneyUtils.doSendMoney(url, MoneyUtils.createXML(map));
			System.out.println("Send Redpacket Result=============" + result);
		} catch (Exception e) {
			result = e.getMessage();
			System.out.println("Send Redpacket Error=============" + e.getMessage());
		}
		return result;
	}
	
	@Override
	public void saveUserSendRedPacketInfo(String reOpenid, float sendAmount, RedPacketSet redPacketSet) {
		RedPacket redPacket = new RedPacket();
		redPacket.setOpenId(reOpenid);
		redPacket.setAmount(String.valueOf(sendAmount));
		redPacket.setIsGrant("Y");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date(System.currentTimeMillis());
		String currentDateStr = sdf.format(currentDate);
		redPacket.setOptTime(currentDateStr);
		//保存用户的红包信息
		this.getHibernateTemplate().save(redPacket);
		
		RedPacketSet updateRedPacketSet = this.getHibernateTemplate().get(RedPacketSet.class, redPacketSet.getId());
		updateRedPacketSet.setSurplusAmount(String.valueOf(Float.parseFloat(redPacketSet.getSurplusAmount()) - sendAmount));
		//更新剩余总金额
		this.getHibernateTemplate().update(updateRedPacketSet);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<RedPacket> getSendRedPacketListByUser(String reOpenid) {
		List params = new ArrayList();
		StringBuffer hql = new StringBuffer("from RedPacket where 1=1");
		if(reOpenid != null) {
			params.add(reOpenid);
			hql.append(" and openId = ?");
		}
		List<RedPacket> list = this.getHibernateTemplate().find(hql.toString(), params.toArray());
		if(!list.isEmpty()){
			return list;
		}
		return null;
	}
	
}
