package com.java.b2cGoods.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.java.b2cGoods.service.GoodsService;
import com.java.b2cGoods.vo.B2cGoods;
import com.java.b2cGoods.vo.B2cGoodsBanner;
import com.java.ec.common.PageSortModel;
import com.java.platform.core.Action;
import com.java.util.DateManage;
import com.java.util.EncryptUtil;

public class GoodsAction extends Action{
	private static final long serialVersionUID = 1852094731493920752L;
	/**
	 * 商品业务处理类
	 */
	private GoodsService goodsService;
	/**
	 * 商品信息
	 */
	private B2cGoods b2cGoods;
	/**
	 * 商品banner图列表
	 */
	private List<B2cGoodsBanner> b2cGoodsBannerList = new ArrayList<>();
	/**
	 * 保存上传文件的目录
	 */
	private String uploadDir = "admin/images/b2cGoods";
	
	
	/**
	 * 商品信息列表方法
	 * @return
	 */
	public String getGoodsInfoList(){
		String tableId = "list";
		PageSortModel psm = new PageSortModel(this.getHttpServletRequest(),tableId);
		if(b2cGoods == null){
			b2cGoods = new B2cGoods();
		}
		List<B2cGoods> list = this.goodsService.getB2cGoodsListByPage(psm, b2cGoods);
		this.putToRequest("list", list);
		if("select".equals(this.getHttpServletRequest().getParameter("flag"))){
			return "selectList";
		}
		return SUCCESS;
	}
	/**
	 * 跳转添加或编辑页面
	 * @return
	 */
	public String toJumpGoods(){
		if(b2cGoods != null && b2cGoods.getId() != null){
			this.b2cGoods = this.goodsService.getB2cGoodsById(b2cGoods);
			this.b2cGoodsBannerList = this.goodsService.getB2cGoodsBannerList(b2cGoods);
		}
		return SUCCESS;
	}
	/**
	 * 保存信息
	 * @return
	 */
	public String saveOrUpdateB2cGoods(){
		String tempDir =  b2cGoods.getGoodsImg();
		if (StringUtils.isEmpty(tempDir)) {
			String current = DateManage.getDateStr("yyyyMMddhhmmss");
			String bannerName = "goods" + current + ".jpg";
			tempDir = uploadDir + "/" + bannerName;
			b2cGoods.setGoodsImg(tempDir);
		}
		if (StringUtils.isNotEmpty(b2cGoods.getImgBase64())) {
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
				EncryptUtil.uploadImage(b2cGoods.getImgBase64(), dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Long goodsId = this.goodsService.saveOrUpdateB2cGoods(b2cGoods);
		B2cGoods goods = new B2cGoods();
		goods.setId(goodsId);
		for(B2cGoodsBanner banner : b2cGoodsBannerList){
			banner.setB2cGoods(goods);
			this.goodsService.saveOrUpdateB2cGoodsBanner(banner);
		}
		addActionMessage("操作成功");
		return redirectActionResult("goodsInfoList");
	}
	
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}


	public B2cGoods getB2cGoods() {
		return b2cGoods;
	}


	public void setB2cGoods(B2cGoods b2cGoods) {
		this.b2cGoods = b2cGoods;
	}
	public List<B2cGoodsBanner> getB2cGoodsBannerList() {
		return b2cGoodsBannerList;
	}
	public void setB2cGoodsBannerList(List<B2cGoodsBanner> b2cGoodsBannerList) {
		this.b2cGoodsBannerList = b2cGoodsBannerList;
	}
}
