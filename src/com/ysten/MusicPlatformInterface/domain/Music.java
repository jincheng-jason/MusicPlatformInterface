package com.ysten.MusicPlatformInterface.domain;

import com.ysten.MusicPlatformInterface.util.VerifyUtil;

public class Music {

	private int ID;
	
	private String code;
	
	private String name;
	
	private String singerCode;
	
	private String indate;
	
	private String productMask;
	
	private int operateType;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSingerCode() {
		return singerCode;
	}

	public void setSingerCode(String singerCode) {
		this.singerCode = singerCode;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = new VerifyUtil().string2Datetime(indate);
	}

	public String getProductMask() {
		return productMask;
	}

	public void setProductMask(String productMask) {
		this.productMask = productMask;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	
}
