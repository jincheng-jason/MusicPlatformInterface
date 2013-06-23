package com.ysten.MusicPlatformInterface.domain;

public class MusicList {

	private String listID;
	
	private String name;
	
	private String songCode;
	
	private String songName;
	
	private int serialNum;

	public String getListID() {
		return listID;
	}

	public void setListID(String listID) {
		this.listID = listID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSongCode() {
		return songCode;
	}

	public void setSongCode(String songCode) {
		this.songCode = songCode;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	
}
