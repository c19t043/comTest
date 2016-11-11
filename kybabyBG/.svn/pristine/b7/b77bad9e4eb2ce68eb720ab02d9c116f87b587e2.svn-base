package com.kybaby.action.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kybaby.action.BaseAction;
import com.kybaby.domain.Balance;
import com.kybaby.domain.DoctorWithdrawals;
import com.kybaby.util.EncryptUtil;

/**
 * 
 * @author tomandcat
 * @description 2.7.3提现管理
 */
public class WithdrawalsManage extends BaseAction {

	
	private String action="";
	private String mes="";
	
	private long doctorWithdrawalsId; //医生提现记录Id
	private String applyStatus; //申请状态
	
	private List allDoctorWithdrawals=new ArrayList();//所有的提现记录
	private List DoctorWithdrawals=new ArrayList();    //某条提现记录
	
    private String tempDir="admin/pdfFile/";
    private String dir="";
    private String fileName="";
    
    private List allBalance=new ArrayList();
	
	public String execute() throws DocumentException, IOException
	{
		if(action.equals("getAllWithdrawals"))
		{
			System.out.println("withdrawalsManage.action?action=getAllWithdrawals......");
			allDoctorWithdrawals=doctorWithdrawalsBo.getAllDoctorWithdrawalsInfo();
			mes="成功";
			return "success";
		}
		
		if(action.equals("update"))
		{
			System.out.println("withdrawalsManage.action?action=update......");
			DoctorWithdrawals updateDoctorWithdrawals=doctorWithdrawalsBo.getDoctorWithdrawalsById(doctorWithdrawalsId);
			if(updateDoctorWithdrawals!=null)
			{
				updateDoctorWithdrawals.setApplyStatus(applyStatus);
				baseBo.updateDoctorWithdrawals(updateDoctorWithdrawals);
				mes="成功";
				return "success";
			}
			else
			{
				mes="查无此人";
				return "fail";
			}
		}
		
		if(action.equals("generalBalance"))
		{
			System.out.println("withdrawalsManage.action?action=generalBalance......");
			Font fontChinese = null;
			BaseFont bfChinese = null;
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			fontChinese = new Font(bfChinese, 12, Font.NORMAL);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			List<Object[]> alreadyCheckedBalance=doctorWithdrawalsBo.getCheckedBalance("已审核");
			if(alreadyCheckedBalance!=null)
			{
			double total=0L;
			for(Object[] a:alreadyCheckedBalance)
			{
				
				total=EncryptUtil.getSecondBits(total)+EncryptUtil.getSecondBits(Double.valueOf(String.valueOf(a[4])));
				long updateId=Long.valueOf(String.valueOf(a[6]));
				DoctorWithdrawals updateDW=doctorWithdrawalsBo.getDoctorWithdrawalsById(updateId);
				updateDW.setApplyStatus("已打款");
				updateDW.setUpdateTime(formatter.format(new Date()));
				baseBo.updateDoctorWithdrawals(updateDW);
			}
			Date time=new Date();
			SimpleDateFormat formatterOne = new SimpleDateFormat("(yyyy-MM-dd)HH-mm-ss");
			SimpleDateFormat formatterTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTimeOne=formatterOne.format(time);
			String nowTimeTwo=formatterTwo.format(time);
			tempDir+=nowTimeOne+".pdf";
			dir = ServletActionContext.getServletContext().getRealPath(tempDir);
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			PdfWriter.getInstance (document, new FileOutputStream (dir));
			document.open();
			Paragraph title = new Paragraph("医生提现管理结算单：",fontChinese);
			title.setAlignment(Element.ALIGN_CENTER);
			Chapter chapter = new Chapter(title, 0);
			chapter.setNumberDepth(0);
			Paragraph title1 =new Paragraph("总申请额："+String.valueOf(total),fontChinese);
			Section section1=chapter.addSection(title1);
			PdfPTable t = new PdfPTable(6);
            t.setSpacingBefore(30);
            t.setSpacingAfter(30);
            PdfPCell c1 = new PdfPCell(new Phrase("医生姓名",fontChinese));  	                
            t.addCell(c1);	                
            PdfPCell c2 = new PdfPCell(new Phrase("医生电话",fontChinese));	                
            t.addCell(c2);	                
            PdfPCell c3 = new PdfPCell(new Phrase("开户行",fontChinese));	                
            t.addCell(c3);	
            PdfPCell c4 = new PdfPCell(new Phrase("银行卡号",fontChinese));  	                
            t.addCell(c4);	                
            PdfPCell c5 = new PdfPCell(new Phrase("申请金额",fontChinese));	                
            t.addCell(c5);
            PdfPCell c6 = new PdfPCell(new Phrase("申请时间",fontChinese));	                
            t.addCell(c6);
            for(Object[] b:alreadyCheckedBalance)
            {
            	t.addCell(new PdfPCell(new Phrase(String.valueOf(b[0]),fontChinese)));
            	t.addCell(String.valueOf(b[1]));
            	t.addCell(new PdfPCell(new Phrase(String.valueOf(b[2]),fontChinese)));
            	t.addCell(String.valueOf(b[3]));
            	t.addCell(String.valueOf(b[4]));
            	t.addCell(String.valueOf(b[5]));
            }
            section1.add(t);
            document.add(chapter);
            document.close();      
            Balance balance=new Balance(nowTimeTwo,nowTimeOne+".pdf");
            fileName=nowTimeOne+".pdf";
            baseBo.saveBalance(balance);
            
            
            mes="成功";
            return "success"; 
			}
			else
			{
				mes="没有已审核的医生申请";
				return "fail";
			}
		}
		
		if(action.equals("getAllBalance"))
		{
			System.out.println("withdrawalsManage.action?action=getAllBalance......");
			allBalance=doctorWithdrawalsBo.getAllBalance();
			mes="成功";
			return "success";
		}
	
		return "success";
	}


	public String getMes() {
		return mes;
	}


	public List getAllDoctorWithdrawals() {
		return allDoctorWithdrawals;
	}




	public void setAction(String action) {
		this.action = action;
	}


	public void setDoctorWithdrawalsId(long doctorWithdrawalsId) {
		this.doctorWithdrawalsId = doctorWithdrawalsId;
	}


	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}


	public List getDoctorWithdrawals() {
		return DoctorWithdrawals;
	}


	public List getAllBalance() {
		return allBalance;
	}


	public String getFileName() {
		return fileName;
	}
	
	
}
