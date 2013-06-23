package com.ysten.MusicPlatformInterface.domain;

public class Album {

	private String albumCode;
	
	private String singerCode;
	
	private String initials;
	
	private String name;
	
	private String introduction;
	
	private String albumPicture;
	
	private String pubtime;
	
	private int operateType;

	

	public String getAlbumCode() {
		return albumCode;
	}

	public void setAlbumCode(String albumCode) {
		this.albumCode = albumCode;
	}

	public String getSingerCode() {
		return singerCode;
	}

	public void setSingerCode(String singerCode) {
		this.singerCode = singerCode;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAlbumPicture() {
		return albumPicture;
	}

	public void setAlbumPicture(String albumPicture) {
		this.albumPicture = albumPicture;
	}

	public String getPubtime() {
		return pubtime;
	}

	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	
}
