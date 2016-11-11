package com.java.b2cGoods.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.java.b2cGoods.service.GoodsService;
import com.java.b2cGoods.vo.B2cGoods;
import com.java.b2cGoods.vo.B2cGoodsBanner;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;
import com.java.util.DateManage;
import com.java.util.EncryptUtil;
@SuppressWarnings("all")
public class GoodsServiceImpl extends ServiceImpl implements GoodsService{
	/**
	 * 保存上传文件的目录
	 */
	private String uploadDir = "admin/images/b2cGoods";

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Long saveOrUpdateB2cGoods(B2cGoods b2cGoods)  {
		if(b2cGoods == null){
			return null;
		}
		Long id = null;
		if(b2cGoods.getId() == null){
			id = (Long)super.add(b2cGoods);
		}else{
			id = b2cGoods.getId();
			super.edit(b2cGoods);
		}
		return id;
	}
	@Override
	public List<B2cGoods> getB2cGoodsListByPage(PageSortModel psm,
			B2cGoods b2cGoods) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM B2cGoods u where 1=1");
		if(b2cGoods != null){
			if(StringUtils.isNotEmpty(b2cGoods.getGoodsName())){
				param.put("goodsName", "%" + b2cGoods.getGoodsName() + "%");
				hql.append(" and u.goodsName LIKE :goodsName");
			}
			if(StringUtils.isNotEmpty(b2cGoods.getGoodsSn())){
				param.put("goodsSn", "%" + b2cGoods.getGoodsSn() + "%");
				hql.append(" and u.goodsSn LIKE :goodsSn");
			}
		}
		List<B2cGoods> list = null;
		if(psm != null){
			list = (List<B2cGoods>) listForEc(hql.toString(),psm, param);
		}else{
			list = this.list(hql.toString(), 0, 0, param);
		}
		
		return list;
	}

	@Override
	public B2cGoods getB2cGoodsById(B2cGoods b2cGoods) {
		return this.get(b2cGoods.getId(), B2cGoods.class);
	}

	@Override
	public Long saveOrUpdateB2cGoodsBanner(B2cGoodsBanner banner) {
		if(banner == null){
			return null;
		}
		//上传图片
		String tempDir =  banner.getImgPath();
		if (StringUtils.isEmpty(tempDir)) {
			String current = DateManage.getDateStr("yyyyMMddhhmmss");
			String bannerName = "banner" + current + ".jpg";
			tempDir = uploadDir + "/" + bannerName;
			banner.setImgPath(tempDir);
		}
		if (StringUtils.isNotEmpty(banner.getImgBase64())) {
			String directory = ServletActionContext.getServletContext()
					.getRealPath(uploadDir + "/");
			File cacheDir = new File(directory);
			// 如果文件夹不存在则创建
			if (!cacheDir.exists() && !cacheDir.isDirectory()) {
				System.out.println("//不存在");
				cacheDir.mkdirs();
			} else {
				System.out.println("//目录存在");
			}
			// 上传图片
			String dir = ServletActionContext.getServletContext()
					.getRealPath(tempDir);
			try {
				EncryptUtil.uploadImage(banner.getImgBase64(), dir);
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Long id = null;
		if(banner.getId() == null){
			id = (Long)super.add(banner);
		}else{
			id = banner.getId();
			super.edit(banner);
		}
		return id;
	}

	@Override
	public List<B2cGoodsBanner> getB2cGoodsBannerList(B2cGoods b2cGoods) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("FROM B2cGoodsBanner u where 1=1");
		if(b2cGoods != null){
			if(b2cGoods.getId() != null){
				param.put("goodId", b2cGoods.getId());
				hql.append(" and u.b2cGoods.id = :goodId");
			}
		}
		List<B2cGoodsBanner> list = list(hql.toString(),-1,-1, param);
		return list;
	}
}
