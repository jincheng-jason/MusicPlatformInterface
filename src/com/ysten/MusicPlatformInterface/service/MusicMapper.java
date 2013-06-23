package com.ysten.MusicPlatformInterface.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ysten.MusicPlatformInterface.domain.Music;

public class MusicMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		Music music = new Music();
		music.setID(rs.getInt("ID"));
		return music;
	}
	
	
	public String checkNull(String input){
		if(input == null || input.isEmpty()){
			return "";
		}else{
			return input;
		}
	}
	
}
