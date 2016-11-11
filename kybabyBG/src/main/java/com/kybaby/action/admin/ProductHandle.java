package com.kybaby.action.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Product;

public class ProductHandle extends BaseAction {

	private String mes="";
	private String action="";
	private String uploadDir="admin/images/product";//保存上传文件的目录
	private String dir="";
	
	private String name;        // 名字
	private long serviceTime; // 上门服务时长
	private long afterServiceTime; //售后服务时长
	private long positionId;//职业名字Id
	private String whatFitForSex; //适用性别
	private String whatFitForMonth; //适合月数
	private String productProperty; //产品属性
	private String introduction; //产品介绍
	private double totalPrice; //产品价格
	private String productCategory; //产品分类
	private String isFeatures;//是否是特色产品（Y/N）
	private String itemIds; //项目id集合
	private String status;
	private long updateId; //更改产品的id
	
	private Long flowBasicId; //产品流程id
	/**
	 * 是否判断年龄（Y/N）
	 */
	private String isJudgeAge;
	/**
	 * 是否判断性别
	 */
	private String isJudgeSex;
	/**
	 * 最大年龄
	 */
	private String maxAge;
	/**
	 * 产品归属类型
	 */
	private String productType;
	/**
	 * 会员卡信息
	 */
	private Long memberTypeId;
	/**
	 * 产品简介
	 */
	private String shortIntroduction;
	
	private File smallPictureFile;//小图
	private File bigPictureOneFile;//大图1
	private File bigPictureTwoFile;//大图2
	private File bigPictureThree;//大图3
	
	private SimpleDateFormat df;
	private String current;
	private String smallFName="";
	private String bigFoneName="";
	private String bigFtwoName="";
	private String bigFthreeName="";
	private String tempDir;
	private File di;
	
	
	
	private List allProduct=new ArrayList();
	
	public String execute()
	{
		if(action.equals("all"))
		{
			System.out.println("productHandle.action?action=all......");
			allProduct=productBo.getAllProduct();
			mes="成功";
			return "mysuccess";
		}
		
		if(action.equals("add"))
		{
			System.out.println("productHandle.action?action=add......");
			
			Product pd=productBo.getProductByName(name);
			if(pd==null)
			{
			df = new SimpleDateFormat("yyyyMMddhhmmss");
		    current = df.format(new Date());
		    smallFName="pic_small" + current +".jpg";
		    bigFoneName="pic_bigOne"+current+".jpg";
		    bigFtwoName="pic_bigTwo"+current+".jpg";
		    bigFthreeName="pic_bigThree"+current+".jpg";
		    if(smallPictureFile!=null)
		    {
		    	tempDir = uploadDir + "/" + smallFName;
		    	dir = ServletActionContext.getServletContext().getRealPath(tempDir);
		    	di = new File(dir);
		    	copy(smallPictureFile, di); 
		    }
		    if(bigPictureOneFile!=null)
		    {
		    	tempDir = uploadDir + "/productDetail/" + bigFoneName;
		    	dir = ServletActionContext.getServletContext().getRealPath(tempDir);
		    	di = new File(dir);
		    	copy(bigPictureOneFile, di); 
		    }
		    if(bigPictureTwoFile!=null)
		    {
		    	tempDir = uploadDir + "/productDetail/" + bigFtwoName;
		    	dir = ServletActionContext.getServletContext().getRealPath(tempDir);
		    	di = new File(dir);
		    	copy(bigPictureTwoFile, di); 
		    }
		    if(bigPictureThree!=null)
		    {
		    	tempDir = uploadDir + "/productDetail/" + bigFthreeName;
		    	dir = ServletActionContext.getServletContext().getRealPath(tempDir);
		    	di = new File(dir);
		    	copy(bigPictureThree, di); 
		    }
		    
		    Product addProduct=new Product(null,name,itemIds,positionId,serviceTime,afterServiceTime,totalPrice,whatFitForSex,whatFitForMonth,productProperty,introduction,smallFName,bigFoneName,isFeatures,status,null,productCategory,0L,bigFtwoName,bigFthreeName);
		    addProduct.setFlowBasicId(flowBasicId);
		    addProduct.setIsJudgeAge(isJudgeAge);
		    addProduct.setIsJudgeSex(isJudgeSex);
		    addProduct.setMaxAge(maxAge);
		    addProduct.setProductType(productType);
		    addProduct.setMemberTypeId(memberTypeId);
		    addProduct.setShortIntroduction(shortIntroduction);
		    baseBo.saveProduct(addProduct);
            mes="成功";
            return "success";
		  }
			else
			{
				mes="此产品已存在";
				return "fail";
			}
		    
		}
		
		if(action.equals("update"))
		{
			System.out.println("productHandle.action?action=update......");
			Product pd=productBo.getProductByName(name);
			if(pd==null||pd.getName().equals(name))
			{
			Product updateProduct=productBo.getProductById(updateId);
			if(updateProduct!=null)
			{
				
				smallFName=updateProduct.getSmallPicture();
			    bigFoneName=updateProduct.getBigPictureOne();
			    bigFtwoName=updateProduct.getBigPictureTwo();
			    bigFthreeName=updateProduct.getBigPictureThree();
			    if(smallPictureFile!=null)
			    {
			    	tempDir = uploadDir + "/" + smallFName;
			    	dir = ServletActionContext.getServletContext().getRealPath(tempDir);
			    	di = new File(dir);
			    	copy(smallPictureFile, di); 
			    }
			    if(bigPictureOneFile!=null)
			    {
			    	tempDir = uploadDir + "/productDetail/" + bigFoneName;
			    	dir = ServletActionContext.getServletContext().getRealPath(tempDir);
			    	di = new File(dir);
			    	copy(bigPictureOneFile, di); 
			    }
			    if(bigPictureTwoFile!=null)
			    {
			    	tempDir = uploadDir + "/productDetail/" + bigFtwoName;
			    	dir = ServletActionContext.getServletContext().getRealPath(tempDir);
			    	di = new File(dir);
			    	copy(bigPictureTwoFile, di); 
			    }
			    if(bigPictureThree!=null)
			    {
			    	tempDir = uploadDir + "/productDetail/" + bigFthreeName;
			    	dir = ServletActionContext.getServletContext().getRealPath(tempDir);
			    	di = new File(dir);
			    	copy(bigPictureThree, di); 
			    }
			    
			    updateProduct.setName(name);
			    updateProduct.setServiceTime(serviceTime);
			    updateProduct.setAfterServiceTime(afterServiceTime);
			    updateProduct.setItemIds(itemIds);
			    updateProduct.setPositionId(positionId);
			    updateProduct.setTotalPrice(totalPrice);
			    updateProduct.setWhatFitForMonth(whatFitForMonth);
			    updateProduct.setWhatFitForSex(whatFitForSex);
			    updateProduct.setProductProperty(productProperty);
			    updateProduct.setIntroduction(introduction);
			    updateProduct.setFlowBasicId(flowBasicId);
			    updateProduct.setIsJudgeAge(isJudgeAge);
			    updateProduct.setIsJudgeSex(isJudgeSex);
			    updateProduct.setMaxAge(maxAge);
			    updateProduct.setProductType(productType);
			    updateProduct.setMemberTypeId(memberTypeId);
			    updateProduct.setShortIntroduction(shortIntroduction);
			    if(smallPictureFile!=null)
			    {
			    updateProduct.setSmallPicture(smallFName);
			    }
			    if(bigPictureOneFile!=null)
			    {
			    updateProduct.setBigPictureOne(bigFoneName);
			    }
			    updateProduct.setIsFeatures(isFeatures);
			    updateProduct.setProductStatus(status);
			    if(bigPictureTwoFile!=null)
			    {
			    updateProduct.setBigPictureTwo(bigFtwoName);
			    }
			    if(bigPictureThree!=null)
			    {
			    updateProduct.setBigPictureThree(bigFthreeName);
			    }
			    updateProduct.setProductCategory(productCategory);
			    baseBo.updateProduct(updateProduct);
			    mes="成功";
			    return "success";
			 }
			else
			{
				mes="此产品已存在";
				return "fail";
			}
			}
			
		}
		
		return "success";
	}
    private static void copy(File src, File dst) { 
        InputStream in = null; 
        OutputStream out = null; 
        try { 
            in = new BufferedInputStream(new FileInputStream(src), 2048); 
            out = new BufferedOutputStream(new FileOutputStream(dst),2048); 
            byte[] buffer = new byte[2048]; 
            int len = 0; 
            while ((len = in.read(buffer)) > 0) { 
                out.write(buffer, 0, len); 

            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            if (null != in) { 
                try { 
                    in.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
            if (null != out) { 
                try { 
                    out.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            } 
        } 
    } 

	public String getMes() {
		return mes;
	}

	public List getAllProduct() {
		return allProduct;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setServiceTime(long serviceTime) {
		this.serviceTime = serviceTime;
	}

	public void setAfterServiceTime(long afterServiceTime) {
		this.afterServiceTime = afterServiceTime;
	}

	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}

	public void setWhatFitForSex(String whatFitForSex) {
		this.whatFitForSex = whatFitForSex;
	}

	public void setWhatFitForMonth(String whatFitForMonth) {
		this.whatFitForMonth = whatFitForMonth;
	}

	public void setProductProperty(String productProperty) {
		this.productProperty = productProperty;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public void setIsFeatures(String isFeatures) {
		this.isFeatures = isFeatures;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public void setSmallPictureFile(File smallPictureFile) {
		this.smallPictureFile = smallPictureFile;
	}

	public void setBigPictureOneFile(File bigPictureOneFile) {
		this.bigPictureOneFile = bigPictureOneFile;
	}

	public void setBigPictureTwoFile(File bigPictureTwoFile) {
		this.bigPictureTwoFile = bigPictureTwoFile;
	}

	public void setBigPictureThree(File bigPictureThree) {
		this.bigPictureThree = bigPictureThree;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}
	public void setFlowBasicId(Long flowBasicId) {
		this.flowBasicId = flowBasicId;
	}
	public void setIsJudgeAge(String isJudgeAge) {
		this.isJudgeAge = isJudgeAge;
	}
	public void setIsJudgeSex(String isJudgeSex) {
		this.isJudgeSex = isJudgeSex;
	}
	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Long getMemberTypeId() {
		return memberTypeId;
	}
	public void setMemberTypeId(Long memberTypeId) {
		this.memberTypeId = memberTypeId;
	}
	public String getShortIntroduction() {
		return shortIntroduction;
	}
	public void setShortIntroduction(String shortIntroduction) {
		this.shortIntroduction = shortIntroduction;
	}
	
}
