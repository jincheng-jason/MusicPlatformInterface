package com.ysten.MusicPlatformInterface.main;

import java.util.List;

import com.ysten.MusicPlatformInterface.util.FileUtil;
import com.ysten.MusicPlatformInterface.util.FtpClientUtil;
import com.ysten.MusicPlatformInterface.util.VerifyUtil;

public class IncrementalPackMain {

	public void proceed(){
		FtpClientUtil ftp = new FtpClientUtil();
		FileUtil fileUtil = new FileUtil();
		VerifyUtil verfUtil = new VerifyUtil();
		
		String downPath = ftp.downFiles();
		if(!downPath.equals("false")){
			boolean verf = verfUtil.verifyFile(downPath);
			if(verf){
				
			}
			
		}
	}
	
}
