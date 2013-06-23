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
	 * �õ���ѹ·��
	 */
	public String getUnzipPath(){
		String dirPath = "";
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(getClass().getResource("/application.properties"));
			dirPath = config.getString("unzipPath");
			
		} catch (ConfigurationException e) {
			log.error("��ȡ�����ļ�application.properties�쳣��");
			e.printStackTrace();
		}
		return dirPath;
	}
	
	/**
	 * �õ�ѹ���ļ�·���������ļ���
	 */
	public String getTarPath(){
		String dirPath = "";
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(getClass().getResource("/application.properties"));
			dirPath = config.getString("tarPath");
			
		} catch (ConfigurationException e) {
			log.error("��ȡ�����ļ�application.properties�쳣��");
			e.printStackTrace();
		}
		return dirPath;
	}
	
	/**
	 * �õ���֤�ļ�����
	 */
	public List<String> getVerifyFile(String path){
		List<String> verifyList = new ArrayList<String>();
		File file = new File(path);
		String[] fileList = file.list();
		//�����ļ����ƣ��ó���֤�ļ�����
		for(int i = 0; i < fileList.length; i++){
			if(fileList[i].endsWith(".verf")){
				verifyList.add(fileList[i]);
			}
		}
		return verifyList;
	}
	
	/**
	 * �õ�TXT�ļ�����
	 */
	public List<String> getTXTFile(){
		String path = getUnzipPath();
		List<String> TXTList = new ArrayList<String>();
		File file = new File(path);
		String[] fileList = file.list();
		//�����ļ����ƣ��ó���֤�ļ�����
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
		//�����ļ����ƣ��ó���֤�ļ�����
		for(int i = 0; i < fileList.length; i++){
			if(fileList[i].endsWith(".txt")){
				TXTList.add(fileList[i]);
			}
		}
		return TXTList;
	}
	
	/**
	 * ��ȡTXT�ļ�
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
				log.error("�Ҳ���ָ���ļ���");
				System.out.println("�Ҳ���ָ���ļ���");
			}
		}catch(Exception e){
			log.error("��ȡ�ļ����ݳ���--FileUtil");
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
				log.error("�Ҳ���ָ���ļ���");
				System.out.println("�Ҳ���ָ���ļ���");
			}
		}catch(Exception e){
			log.error("��ȡ�ļ����ݳ���--FileUtil");
			e.printStackTrace();
		}
		return bufferedReader;
	}
	
	/**
	 * ����ʮ������0x1F�ָ��ַ���
	 * @param input
	 * @return
	 */
	public String[] splitString(String input){
		char c = 31;
		String[] output = input.split(String.valueOf(c));
		return output;
	}
	
	/**
	 * �����ļ���С
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
			System.out.println("�ļ���"+ path + "/" + fileName + "�����ڣ�");
		}
		return String.valueOf(s);
	}
	
}
