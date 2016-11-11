package com.kybaby.kyinterface.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import jxl.Sheet;
import jxl.Workbook;

public class ReadExcelByJXL {
	
/*	public static void main(String[] args) throws Exception {
		 String encode = URLEncoder.encode("中","UTF-8");
		 System.out.println(encode);
		 String decode = URLDecoder.decode(encode,"UTF-8");
		 System.out.println(decode);
	}*/
	
	public static void main(String[] args) throws Exception{
		String path = "D:/数据.xls";//需要读取的excel文件（2003版本）
		String image = "D:/doctorFaceIcon";//图片文件路径
		
		String filePath = "D:/doctorFaceIcon2";//找到的图片需要存放的路径
		
		File readFile = new File(path);

		
		if(readFile.exists()){
			InputStream in = new FileInputStream(readFile);
			Workbook book = Workbook.getWorkbook(in);
			Sheet sheet = book.getSheet(0);
			
			int rows = sheet.getRows();
	
			String[] strArr = new String[rows];
			for(int i=1;i<rows;i++){
				String id = sheet.getCell(0, i).getContents();
				String content = sheet.getCell(1, i).getContents();
				if(content!=null&&content.length()!=0){
					strArr[i-1] = content;
				}
				//System.out.println(id+":"+content);
			}
			
			for(int i=0;i<strArr.length;i++){
				//System.out.println(strArr[i]);
				if(strArr[i]!=null){
					String filename = image+"/"+strArr[i];
					System.out.println(filename);
					File file2 = new File(filename);
					if(file2.exists()){
						InputStream in2 = new FileInputStream(file2);
						filePath="/"+strArr[i];
						String filename2 = image+"2/"+strArr[i];
						File file3 = new File(filename2);
						File parent = new File(file3.getParent());
						if(!parent.exists());parent.mkdirs();
						OutputStream out = new FileOutputStream(file3);
						byte[] b = new byte[1024];
						while(in2.read(b)!=-1)
						out.write(b);
					}
				}
			}
		}
	}
}
