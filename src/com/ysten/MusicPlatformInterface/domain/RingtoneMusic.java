package com.ysten.MusicPlatformInterface.domain;

public class RingtoneMusic {

	private String boxID;
	
	private String musicCode;
	
	private int serialNum;
	
	private int operateType;

	public String getMusicCode() {
		return musicCode;
	}

	public void setMusicCode(String musicCode) {
		this.musicCode = musicCode;
	}

	public String getBoxID() {
		return boxID;
	}

	public void setBoxID(String boxID) {
		this.boxID = boxID;
	}


	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	
	
}
