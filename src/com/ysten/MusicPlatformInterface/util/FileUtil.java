package com.ysten.MusicPlatformInterface.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {

	private static final Log log = LogFactory.getLog(FileUtil.class);
	
	/**
	 * 得到解压路径
	 */
	public String getUnzipPath(){
		String dirPath = "";
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(getClass().getResource("/application.properties"));
			dirPath = config.getString("unzipPath");
			
		} catch (ConfigurationException e) {
			log.error("获取配置文件application.properties异常！");
			e.printStackTrace();
		}
		return dirPath;
	}
	
	/**
	 * 得到压缩文件路径，包括文件名
	 */
	public String getTarPath(){
		String dirPath = "";
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(getClass().getResource("/application.properties"));
			dirPath = config.getString("tarPath");
			
		} catch (ConfigurationException e) {
			log.error("获取配置文件application.properties异常！");
			e.printStackTrace();
		}
		return dirPath;
	}
	
	/**
	 * 得到验证文件名称
	 */
	public List<String> getVerifyFile(String path){
		List<String> verifyList = new ArrayList<String>();
		File file = new File(path);
		String[] fileList = file.list();
		//遍历文件名称，拿出验证文件名称
		for(int i = 0; i < fileList.length; i++){
			if(fileList[i].endsWith(".verf")){
				verifyList.add(fileList[i]);
			}
		}
		return verifyList;
	}
	
	/**
	 * 得到TXT文件名称
	 */
	public List<String> getTXTFile(){
		String path = getUnzipPath();
		List<String> TXTList = new ArrayList<String>();
		File file = new File(path);
		String[] fileList = file.list();
		//遍历文件名称，拿出验证文件名称
		for(int i = 0; i < fileList.length; i++){
			if(fileList[i].endsWith(".txt")){
				TXTList.add(fileList[i]);
			}
		}
		return TXTList;
	}
	
	public List<String> getTXTFile(String path){
		List<String> TXTList = new ArrayList<String>();
		File file = new File(path);
		String[] fileList = file.list();
		//遍历文件名称，拿出验证文件名称
		for(int i = 0; i < fileList.length; i++){
			if(fileList[i].endsWith(".txt")){
				TXTList.add(fileList[i]);
			}
		}
		return TXTList;
	}
	
	/**
	 * 读取TXT文件
	 */
	public BufferedReader readTXTFile(String fileName){
		BufferedReader bufferedReader = null;
		try{
			String path = getUnzipPath()+"\\"+fileName;
			File file = new File(path);
			if(file.isFile() && file.exists()){
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file));
				bufferedReader = new BufferedReader(read);
			}else{
				log.error("找不到指定文件！");
				System.out.println("找不到指定文件！");
			}
		}catch(Exception e){
			log.error("读取文件内容出错！--FileUtil");
			e.printStackTrace();
		}
		return bufferedReader;
	}
	
	public BufferedReader readTXTFile(String path,String fileName){
		BufferedReader bufferedReader = null;
		try{
			File file = new File(path + "/" + fileName);
			if(file.isFile() && file.exists()){
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file));
				bufferedReader = new BufferedReader(read);
			}else{
				log.error("找不到指定文件！");
				System.out.println("找不到指定文件！");
			}
		}catch(Exception e){
			log.error("读取文件内容出错！--FileUtil");
			e.printStackTrace();
		}
		return bufferedReader;
	}
	
	/**
	 * 按照十六进制0x1F分割字符串
	 * @param input
	 * @return
	 */
	public String[] splitString(String input){
		char c = 31;
		String[] output = input.split(String.valueOf(c));
		return output;
	}
	
	/**
	 * 返回文件大小
	 * @return
	 */
	public String fileSize(String path,String fileName) throws Exception{
		File file = new File(path + "/" + fileName);
		long s = 0;
		if(file.exists()){
			 FileInputStream fis = null;
	         fis = new FileInputStream(file);
	         s= fis.available();
		}else{
			System.out.println("文件："+ path + "/" + fileName + "不存在！");
		}
		return String.valueOf(s);
	}
	
}
