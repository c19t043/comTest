package com.kybaby.action.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Banner;
import com.kybaby.domain.GuidePage;
import com.kybaby.domain.Properties;

public class BannerAndPageHandle extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mes="";
	private String action="";
	
	private String fileName="";    //相对路径文件名
	
	private List<Banner> allBanner=new ArrayList();  //返回的所有banner和内容
	private List<String> bannerHtmlStr=new ArrayList(); //返回对应的banner内容
	private List<GuidePage>   allGuidepage=new ArrayList();    //返回所有的引导页图片
	private String webpageDir="admin/webPage";
	private String guideImageDir="admin/images/guide";
	private String bannerImageDir="admin/images/banner";
	private String returnBodyConteant=""; //返回需要编辑的页面内容，需拆分
	private String comeInBodyContent=""; //传入编辑的内容
	private String modular="";           //传过来的模块内容
	private String titleName="";      //title名字
	private String dir="";
	private String pageContent="";
	private String healthManageImageName="";
	
	private File healthPlanManageImage;
	private List<File>  FileList=new ArrayList();          //上传图片文件List
	private File bannerFile1;
	private File bannerFile2;
	private File bannerFile3;
	private File bannerFile4;
	private List<Long>  updateIdList;      //修改Id List
	private List<String> statusList;       //修改状态List 
	private List<String> contentList;      //修改文件List
	
	private File guidePage1;
	private File guidePage2;
	private File guidePage3;

 	
	FileOutputStream out=null;
	
	public String execute() throws IOException
	{
		System.out.println(StringUtils.swapCase(this.getClass().getSimpleName().substring(0,1))+this.getClass().getSimpleName().substring(1,this.getClass().getSimpleName().length())+".action?action="+action+"modular="+modular+"......");
		String fileHeaderOne="";
//		        "<!DOCTYPE HTML>"+
//		        "<html>"+
//		        "<head>"+
//		        "<meta charset=\"utf-8\" />"+
//		        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\" />"+
//		        "<style>img{max-width:100%;}body{margin:0px auto;width:92%;}p{margin-top:10px;}</style>"+
//		        "<title>";
		String fileHeaderOneCopy=
		        "<!DOCTYPE HTML>"+
		        "<html>"+
		        "<head>"+
		        "<meta charset=\"utf-8\" />"+
		        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\" />"+
				"<link rel=\"stylesheet\" href=\"normalize.css\">"+
				"<link rel=\"stylesheet\" href=\"main.css\">"+
		        "<style>img{max-width:100%;}p{margin-top:0px;}</style>"+
		        "<title>";
		
		String fileHeaderTwo="";	        
//		        "</title>"+
//		        "</head><body>"+
//		        "<!--begin-->";
		String fileHeaderTwoCopy=
		        "</title>"+
		        "</head><body>"+
		        "<div id=\"header\">"+
		    	"<div class=\"header-left\" onclick=\"window.history.back()\"><img src='prev_01.png' width=25 /></div>"+
		    	"<div class=\"header-center\"></div>"+
		    	"<div class=\"header-right\"></div>"+
		    "</div>"+
		        "<!--begin-->";
		
		
	    String fileFooter="";
//	    		"<!--end--></body></html>";
	    
	    String fileFooterCopy="<!--end--></body></html>";
	    
	    String tempDir="";
	        
		if(action.equals("getAllBanner"))
		{
			
			allBanner=bannerBo.getAllBanner();
			
			if(allBanner!=null)
			{
				for (Banner a:allBanner)
				{
					String bannerHtmlFile=a.getValue();
					tempDir=webpageDir+"/"+bannerHtmlFile;
					dir=ServletActionContext.getServletContext().getRealPath(tempDir);
					File pageFile=new File(dir);
					if(pageFile.exists())
					{
						String content=getFileStr(pageFile);
						returnBodyConteant=content.substring(content.indexOf("<!--begin-->")+12, content.indexOf("<!--end-->"));
						
					}
					else
					{
						pageFile.createNewFile();
						pageContent=fileHeaderOneCopy+titleName+fileHeaderTwoCopy+comeInBodyContent+fileFooterCopy;
						writeFile(pageContent,pageFile);
						String content=getFileStr(pageFile);
						returnBodyConteant=content.substring(content.indexOf("<!--begin-->")+12, content.indexOf("<!--end-->"));
						
					}
					bannerHtmlStr.add(returnBodyConteant);
					returnBodyConteant="";
					
				}
				
				mes="成功";
				return "success";
			}
			
			mes="成功";
			return "success";
			
			
		}
		
		if(action.equals("getContent"))
		{
			if(modular.equals("userProtocol")) // 用户协议
			{
//				titleName="用户协议";
				fileName="userProtocol.html";
				tempDir=webpageDir+"/"+fileName;
				dir=ServletActionContext.getServletContext().getRealPath(tempDir);
				File pageFile=new File(dir);
				if(pageFile.exists())
				{ 
					String content=getFileStr(pageFile);
					returnBodyConteant=content.substring(content.indexOf("<section>")+9, content.indexOf("</section>"));
					mes="成功";
					return "success";
				}
				else
				{
					pageFile.createNewFile();
					pageContent=fileHeaderOne+titleName+fileHeaderTwo+comeInBodyContent+fileFooter;
					writeFile(pageContent,pageFile);
					mes="初次创建文件成功";
					return "success";
				}
			}
			
			if(modular.equals("healthPlanManage")) //健康计划管理
			{
				titleName="健康管理方法";
				Properties property=propertiesBo.getPropertiesByName("healthManageMethod");
				if(property!=null)
				{
					healthManageImageName=property.getValue();
				}
				property=propertiesBo.getPropertiesByName("healthManageMethodDetail");
				if(property!=null)
				{
					
					fileName=property.getValue();
					tempDir=webpageDir+"/"+fileName;
					dir=ServletActionContext.getServletContext().getRealPath(tempDir);
					File pageFile=new File(dir);	
					if(pageFile.exists())
					{
						String content=getFileStr(pageFile);
						returnBodyConteant=content.substring(content.indexOf("<!--begin-->")+12, content.indexOf("<!--end-->"));
						mes="成功";
						return "success";
					}
					else
					{
						pageFile.createNewFile();
			            pageContent=fileHeaderOne+titleName+fileHeaderTwo+comeInBodyContent+fileFooter;
						writeFile(pageContent,pageFile);
						mes="初次创建文件成功";
						return "success";
					}
				}
			}
			if(modular.equals("aboutUs"))  //关于我们
			{
//				titleName="关于我们";
				fileName="aboutUs.html"; 
				tempDir=webpageDir+"/"+fileName;
				dir=ServletActionContext.getServletContext().getRealPath(tempDir);
				File pageFile=new File(dir);
				if(pageFile.exists())
				{
					String content=getFileStr(pageFile);
					returnBodyConteant=content.substring(content.indexOf("<section>")+9, content.indexOf("</section>"));
					mes="成功";
					return "success";
				}
				else
				{
					pageFile.createNewFile();
					pageContent=fileHeaderOne+titleName+fileHeaderTwo+comeInBodyContent+fileFooter;
					writeFile(pageContent,pageFile);
					mes="初次创建文件成功";
					return "success";
				}
				
			}
			
			if(modular.equals("doctorProtocol"))//医生协议
			{
				fileName="doctorProtocol.html"; 
				tempDir=webpageDir+"/"+fileName;
				dir=ServletActionContext.getServletContext().getRealPath(tempDir);
				File pageFile=new File(dir);
				if(pageFile.exists())
				{
					String content=getFileStr(pageFile);
					returnBodyConteant=content.substring(content.indexOf("<section>")+9, content.indexOf("</section>"));
					mes="成功";
					return "success";
				}
				else
				{
					pageFile.createNewFile();
					pageContent=fileHeaderOne+titleName+fileHeaderTwo+comeInBodyContent+fileFooter;
					writeFile(pageContent,pageFile);
					mes="初次创建文件成功";
					return "success";
				}
			}
			
			
			
		}
		
		if(action.equals("getAllGuidePage"))
		{
			allGuidepage=bannerBo.getAllGuidePage();
			mes="成功";
			return "success";
			
		}
		
		if(action.equals("update"))
		{
			if(modular.equals("userProtocol"))
			{
//				titleName="用户协议";
				fileHeaderOne="<!doctype html>"+
				"<html class=\"no-js\" lang=\"zh-cn\">"+
				"<head>"+
					"<title>用户协议</title>"+
					"<meta charset=\"utf-8\">"+
					"<meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1\"/>"+
					"<link rel=\"stylesheet\" href=\"normalize.css\">"+
					"<link rel=\"stylesheet\" href=\"main.css\">"+
				"</head>"+
				"<style>"+
					"#content{width:100%;}"+
					"section{width:80%;margin:0px auto;}"+
				"</style>"+
				"<body>"+
				"<div class=\"markTop\" id=\"header\">"+
				  "<div class=\"header-left\" onclick=\"window.history.back()\"><img src='prev_01.png' width=25 /></div>"+
				  "<div class=\"header-center\">用户协议</div>"+
				  "<div class=\"header-right\"></div>"+
				"</div>"+
				"<div class=\"markCon\" id=\"content\">"+
					"<section>";
				
				fileFooter=
				"</section>"+
				"</div>"+
				"</body>"+
				"<script>"+
				"var nav = navigator.userAgent;"+
				"if(nav.indexOf('iPhone') != -1 && nav.indexOf('MicroMessenger') == -1){"+
					"document.querySelector('.markTop').style.paddingTop = '20px';"+
					"document.querySelector('.markCon').style.margin = '0';"+	
				"}"+
				"if(nav.indexOf('Android') != -1 && nav.indexOf('MicroMessenger') == -1){"+
					"document.querySelector('.markCon').style.margin = '0';"+
				"}"+
			"</script>"+
				"</html>";


				fileName="userProtocol.html";
				tempDir=webpageDir+"/"+fileName;
				dir=ServletActionContext.getServletContext().getRealPath(tempDir);
				File pageFile=new File(dir);
				if(pageFile.exists())
				{
					pageContent=fileHeaderOne+comeInBodyContent+fileFooter;
					writeFile(pageContent,pageFile);
					mes="成功";
					return "success";
				}
				else
				{
					pageFile.createNewFile();
					pageContent=fileHeaderOne+comeInBodyContent+fileFooter;
					writeFile(pageContent,pageFile);
					mes="初次创建文件成功";
					return "success";
				}
			}
			
			if(modular.equals("aboutUs"))
			{
//				titleName="关于我们";
				fileHeaderOne="<!doctype html>"+
						"<html class=\"no-js\" lang=\"zh-cn\">"+
						"<head>"+
						    "<title>关于我们</title>"+
						    "<meta charset=\"utf-8\">"+
						    "<meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1\"/>"+
						    "<link rel=\"stylesheet\" href=\"normalize.css\">"+
						    "<link rel=\"stylesheet\" href=\"main.css\">"+
						"</head>"+
						"<style type=\"text/css\">"+
						    "textarea{"+
						        "width: 80%;"+
						        "margin: 20px 10%;"+
						        "border-radius: 5px;"+
						        "height: 200px;"+
						        "border: 0;"+
						        "outline: none;"+
						        "padding: 5px;"+
						    "}"+
						    ".logo{"+
						        "width: 80%;"+
						        "margin: 10px auto;"+
						        "height: 200px;"+
						        "border: 1px solid #000000;"+
						        "padding: 5px;"+
						    "}"+
						    ".markCon section{"+
								"margin:10px auto;"+
						    "}"+
						"</style>"+
						"<body>"+
						"<div class=\"markTop\" id=\"header\">"+
						    "<div class=\"header-left\" onclick=\"window.history.back()\"><img src='prev_01.png' width=25 /></div>"+
						    "<div class=\"header-center\">关于我们</div>"+
						    "<div class=\"header-right\"></div>"+
						"</div>"+
						"<!--header-->"+
						"<div class=\"markCon\" id=\"container\">"+
						    "<section>";
						  fileFooter="</section>"+
						"</div>"+
						"</body>"+
						"<script>"+
						"var nav = navigator.userAgent;"+
						"if(nav.indexOf('iPhone') != -1 && nav.indexOf('MicroMessenger') == -1){"+
							"document.querySelector('.markTop').style.paddingTop = '20px';"+
							"document.querySelector('.markCon').style.margin = '0';"+
						"}"+
						"if(nav.indexOf('Android') != -1 && nav.indexOf('MicroMessenger') == -1){"+
							"document.querySelector('.markCon').style.margin = '0';"+
						"}"+
						"</script>"+
						"</html>";
				fileName="aboutUs.html";
				tempDir=webpageDir+"/"+fileName;
				dir=ServletActionContext.getServletContext().getRealPath(tempDir);
				File pageFile=new File(dir);
				if(pageFile.exists())
				{
					pageContent=fileHeaderOne+comeInBodyContent+fileFooter;
					writeFile(pageContent,pageFile);
					mes="成功";
					return "success";
				}
				else
				{
					pageFile.createNewFile();
					pageContent=fileHeaderOne+comeInBodyContent+fileFooter;
					writeFile(pageContent,pageFile);
					mes="初次创建文件成功";
					return "success";
				}
			}
			
			if(modular.equals("healthPlanManage"))
			{
				titleName="健康管理方法";
				Properties propertyImage=propertiesBo.getPropertiesByName("healthManageMethod");
				Properties propertyHtml=propertiesBo.getPropertiesByName("healthManageMethodDetail");
				if(healthPlanManageImage!=null)
				{
					if(propertyImage!=null)
					{
						fileName=propertyImage.getValue();
						tempDir=bannerImageDir+"/"+fileName;
						dir=ServletActionContext.getServletContext().getRealPath(tempDir);
						File pageFile=new File(dir);
						copy(healthPlanManageImage,pageFile);
					}
				}
				
				if(!comeInBodyContent.equals(""))
				{
					if(propertyHtml!=null)
					{
					fileName=propertyHtml.getValue();
					tempDir=webpageDir+"/"+fileName;
					dir=ServletActionContext.getServletContext().getRealPath(tempDir);
					File pageFile=new File(dir);
					pageContent=fileHeaderOneCopy+titleName+fileHeaderTwoCopy+comeInBodyContent+fileFooterCopy;
					writeFile(pageContent,pageFile);
					}
					
				}
				
				mes="成功";
				return "mysuccess";
			}
			
			if(modular.equals("guidePage"))
			{
				FileList.add(guidePage1);
				FileList.add(guidePage2);
				FileList.add(guidePage3);
				if(updateIdList!=null&&statusList!=null)
				{
					for(int i=0;i<updateIdList.size();i++)
					{
						File a=FileList.get(i);
						long updateId=updateIdList.get(i);
						String status=statusList.get(i);
						if(status.equals("Y"))
						{
							GuidePage guidePage=bannerBo.getGuidePageById(updateId);
							if(guidePage!=null)
							{
								String pageName=guidePage.getPageName();
								tempDir=guideImageDir+"/"+pageName;
								dir=ServletActionContext.getServletContext().getRealPath(tempDir);
								File pageFile=new File(dir);
								if(a!=null)
								{
								copy(a,pageFile);
								}
							}
						}
						
					}
				}
				mes="成功";
				return "mysuccess";
			}
			
			if(modular.equals("banner"))
			{
				FileList.add(bannerFile1);
				FileList.add(bannerFile2);
				FileList.add(bannerFile3);
				FileList.add(bannerFile4);
				if(FileList!=null&&updateIdList!=null&&statusList!=null&&contentList!=null)
				{
					for(int i=0;i<updateIdList.size();i++)
					{
						long updateId=updateIdList.get(i);
						String status=statusList.get(i);
						String content=contentList.get(i);

//						if(a!=null)
//						{
						if(status.equals("Y"))
						{
							Banner banner=bannerBo.getBannerById(updateId);
							if(banner!=null)
							{
								String pageName=banner.getName();
								tempDir=bannerImageDir+"/"+pageName;
								dir=ServletActionContext.getServletContext().getRealPath(tempDir);
								File pageFile=new File(dir);
								File a=FileList.get(i);
								if(a!=null)
								{
								copy(a,pageFile);	
								}
								String htmlName=banner.getValue();
								tempDir=webpageDir+"/"+htmlName;
								dir=ServletActionContext.getServletContext().getRealPath(tempDir);
								if(content!=null&&!content.equals(""))
								{
								titleName="";
								pageFile=new File(dir);
								pageContent=fileHeaderOneCopy+titleName+fileHeaderTwoCopy+content+fileFooterCopy;
								writeFile(pageContent,pageFile);
								}
								
							}
						 }
//						}
						
					}
				}
				mes="成功";
				return "mysuccess";
			}
			
			if(modular.equals("doctorProtocol"))
			{
				fileHeaderOne="<!doctype html>"+
						"<html class=\"no-js\" lang=\"zh-cn\">"+
						"<head>"+
							"<title>医生协议</title>"+
							"<meta charset=\"utf-8\">"+
							"<meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1\"/>"+
							"<link rel=\"stylesheet\" href=\"normalize.css\">"+
							"<link rel=\"stylesheet\" href=\"main.css\">"+
						"</head>"+
						"<style>"+
							"#content{width:100%;}"+
							"section{width:80%;margin:0px auto;}"+
						"</style>"+
						"<body>"+
						"<div class=\"markTop\" id=\"header\">"+
						  "<div class=\"header-left\" onclick=\"window.history.back()\"><img src='prev_01.png' width=25 /></div>"+
						  "<div class=\"header-center\">医生协议</div>"+
						  "<div class=\"header-right\"></div>"+
						"</div>"+
						"<div class=\"markCon\" id=\"content\">"+
							"<section>";
						
						fileFooter=
						"</section>"+
						"</div>"+
						"</body>"+
						"<script>"+
						"var nav = navigator.userAgent;"+
						"if(nav.indexOf('iPhone') != -1 && nav.indexOf('MicroMessenger') == -1){"+
							"document.querySelector('.markTop').style.paddingTop = '20px';"+
							"document.querySelector('.markCon').style.margin = '0';"+	
						"}"+
						"if(nav.indexOf('Android') != -1 && nav.indexOf('MicroMessenger') == -1){"+
							"document.querySelector('.markCon').style.margin = '0';"+
						"}"+
					"</script>"+
						"</html>";


						fileName="doctorProtocol.html";
						tempDir=webpageDir+"/"+fileName;
						dir=ServletActionContext.getServletContext().getRealPath(tempDir);
						File pageFile=new File(dir);
						if(pageFile.exists())
						{
							pageContent=fileHeaderOne+comeInBodyContent+fileFooter;
							writeFile(pageContent,pageFile);
							mes="成功";
							return "success";
						}
						else
						{
							pageFile.createNewFile();
							pageContent=fileHeaderOne+comeInBodyContent+fileFooter;
							writeFile(pageContent,pageFile);
							mes="初次创建文件成功";
							return "success";
						}
			}
			
		}
		
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public List<Banner> getAllBanner() {
		return allBanner;
	}



	public void setAction(String action) {
		this.action = action;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public void setComeInBodyContent(String comeInBodyContent) {
		this.comeInBodyContent = comeInBodyContent;
	}

	public String getReturnBodyConteant() {
		return returnBodyConteant;
	}

	public void setModular(String modular) {
		this.modular = modular;
	}
	
	private static String getFileStr(File src) throws IOException
	{
		InputStream is=new FileInputStream(src);
		BufferedReader br=new BufferedReader(new InputStreamReader(is,"utf8"));
		String temp="";
		String result="";
		StringBuilder pamas=new StringBuilder();
		while((temp=br.readLine())!=null)
		{
			result+=temp;
		}
		pamas.append(result);
		
		br.close();
		
		return pamas.toString();
	}
	public void writeFile(String filecontent,File filename) throws UnsupportedEncodingException, IOException {
		out = new FileOutputStream(filename);
		out.write(filecontent.getBytes("UTF-8"));  
		out.flush();
	    out.close();  
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
    

	public List<String> getBannerHtmlStr() {
		return bannerHtmlStr;
	}

	public List<GuidePage> getAllGuidepage() {
		return allGuidepage;
	}

	public void setHealthPlanManageImage(File healthPlanManageImage) {
		this.healthPlanManageImage = healthPlanManageImage;
	}

	public void setFileList(List<File> fileList) {
		FileList = fileList;
	}

	public void setUpdateIdList(List<Long> updateIdList) {
		this.updateIdList = updateIdList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public void setContentList(List<String> contentList) {
		this.contentList = contentList;
	}

	public String getHealthManageImageName() {
		return healthManageImageName;
	}

	public void setBannerFile1(File bannerFile1) {
		this.bannerFile1 = bannerFile1;
	}

	public void setBannerFile2(File bannerFile2) {
		this.bannerFile2 = bannerFile2;
	}

	public void setBannerFile3(File bannerFile3) {
		this.bannerFile3 = bannerFile3;
	}

	public void setBannerFile4(File bannerFile4) {
		this.bannerFile4 = bannerFile4;
	}

	public void setGuidePage1(File guidePage1) {
		this.guidePage1 = guidePage1;
	}

	public void setGuidePage2(File guidePage2) {
		this.guidePage2 = guidePage2;
	}

	public void setGuidePage3(File guidePage3) {
		this.guidePage3 = guidePage3;
	}
}
