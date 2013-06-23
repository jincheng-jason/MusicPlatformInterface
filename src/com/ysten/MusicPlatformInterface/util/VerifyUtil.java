package com.ysten.MusicPlatformInterface.util;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class VerifyUtil {

	private static final Log log = LogFactory.getLog(VerifyUtil.class);
	
	
	public boolean verifyFile(String path){
		try{
			FileUtil fileUtil = new FileUtil();
			List<String> verifyList = fileUtil.getVerifyFile(path);
			List<String> TXTList = fileUtil.getTXTFile(path);
			for(int i = 0; i < verifyList.size(); i++){
				String lineTXT = null;
				BufferedReader bufferedReader = fileUtil.readTXTFile(path,verifyList.get(i));
				while((lineTXT=bufferedReader.readLine()) != null){
					if(lineTXT!="999999"){
						boolean nameFlag = false;
						boolean sizeFlag = false;
						String[] verfs = lineTXT.split("|");
						for(int j = 0; j < verfs.length; j++){
							if(j == 0){
								for(int k = 0; k < TXTList.size(); k++){
									if(verfs[0].equals(TXTList.get(k))){
										nameFlag = true;
										break;
									}
								}
							}else if(j == 1){
								if(verfs[j].equals(fileUtil.fileSize(path,verfs[0]))){
									sizeFlag = true;
								}
							}else{
								break;
							}
						}
						
						if(!(nameFlag && sizeFlag)){
							System.out.println("文件："+ path + "/" + verifyList.get(i) + "没有通过验证!");
							return false;
						}
						
					}
				}
			}
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
			
	}
	
	public String string2Datetime(String input){
		if(input.isEmpty() || input == null || input == " "){
			return "";
		}else{
			String year = input.substring(0, 4);
			String month = input.substring(4, 6);
			String day = input.substring(6, 8);
			String output = year + "-" + month + "-" + day + " 00:00:00";
			return output;
		}
	}
	
}
