package com.ysten.MusicPlatformInterface.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClientUtil {

	private static final Log log = LogFactory.getLog(FtpClientUtil.class);
	
	FTPClient ftp = new FTPClient();
	
	public String downFiles(){
		String localPath = "";
		try{
			int reply;
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("yyyyMMddHH");
			String str_date = sdf.format(date);
			localPath = "/home/musicPack/"+str_date;
			File filePath = new File(localPath);
			filePath.mkdir();
			filePath.setWritable(true,false);
			ftp.connect("218.200.227.115",10021);
			ftp.login("mm_public", "ytd#2!qswd");
			reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return "false";  
	        } 
			
			FTPFile[] fs = ftp.listFiles();  
	        for(FTPFile ff:fs){  
                File localFile = new File(localPath+"/"+ff.getName());  
                OutputStream is = new FileOutputStream(localFile);   
                ftp.retrieveFile(ff.getName(), is);  
                is.close();  
	        }  
	          
	        ftp.logout();
	        return localPath;
		} catch (SocketException e)
		{
			e.printStackTrace();
			return "false";
		} catch (IOException e)
		{
			e.printStackTrace();
			return "false";
		}
	}
}
