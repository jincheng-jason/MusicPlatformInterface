package com.ysten.MusicPlatformInterface.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysten.MusicPlatformInterface.domain.Album;
import com.ysten.MusicPlatformInterface.domain.AlbumMusic;
import com.ysten.MusicPlatformInterface.domain.Music;
import com.ysten.MusicPlatformInterface.domain.MusicList;
import com.ysten.MusicPlatformInterface.domain.Singer;

@Service("storageService")
@Transactional
public class StorageService {

	private static final Log log = LogFactory.getLog(StorageService.class);
	@Resource private JdbcTemplate jt;
	
	
	/**
	 * 音乐入库
	 * @param musicList
	 */
	@Transactional(rollbackFor=Exception.class)
	public void storageMusic(final List<Music> musicList){
		System.out.println("storageMusic begin...");
		for(int i = 0; i < musicList.size(); i++){
			Music music = musicList.get(i);
			int x = jt.update("insert into moms_music(ID,OutCode,Name,Singer,state,Publisher,CreateTime,PubTime) values(?,?,?,?,?,?,?,?)",new Object[]{getNextId("moms_music"),music.getCode(),music.getName(),getSingerIDBySingerCode(music.getSingerCode()),1,4,nowString(),nowString()});
			int y = jt.update("insert into moms_musicproperty(ID,code,singerCode,productMask,indate) values(?,?,?,?,?)",new Object[]{getNextId("moms_musicproperty"),music.getCode(),music.getSingerCode(),music.getProductMask(),music.getIndate()});
			if(x != 1 || y != 1){
				if(x != 1){
					log.error("storageMusic into moms_music error! music code: " + music.getCode());
				}
				if(y != 1){
					log.error("storageMusic into moms_musicproperty error! music code: " + music.getCode());
				}
			}else{
				System.out.println("storageMusic succeed! Music code :" + music.getCode());
			}
		}
		System.out.println("storageMusic end...");
	}
	
	/**
	 * 歌手入库
	 * @param singerList
	 */
	@Transactional(rollbackFor=Exception.class)
	public void storageSinger(final List<Singer> singerList){
		System.out.println("storageSinger begin...");
		for(int i = 0; i < singerList.size(); i++){
			Singer singer = singerList.get(i);
			int x = jt.update("insert into moms_singer(ID,Initials,Name,Introduction,singerCode) values(?,?,?,?,?)",new Object[]{getNextId("moms_singer"),singer.getInitials(),singer.getSingerName(),singer.getIntroduction(),singer.getSingerCode()});
			int y = jt.update("insert into moms_singerproperty(ID,singerCode,singerPicture,property) values(?,?,?,?)",new Object[]{getNextId("moms_singerproperty"),singer.getSingerCode(),singer.getSingerPicture(),singer.getProperty()});
			if(x != 1 || y != 1){
				if(x != 1){
					log.error("storageSinger into moms_singer error! SingerCode: " + singer.getSingerCode());
				}
				if(y != 1){
					log.error("storageSinger into moms_singerproperty error! SingerCode: " + singer.getSingerCode());
				}
			}else{
				System.out.println("storageSinger succeed! SingerCode :" + singer.getSingerCode());
			}
		}
		System.out.println("storageSinger end...");
	}
	
	/**
	 * 专辑入库
	 * @param albumList
	 */
	@Transactional(rollbackFor=Exception.class)
	public void storageAlbum(final List<Album> albumList){
		System.out.println("storageAlbum begin...");
		for(int i = 0; i < albumList.size(); i++){
			Album album = albumList.get(i);
			int musicSetID = getNextId("moms_musicset");
			String musics = getMusicsByAlbum(album.getAlbumCode());
			int x = jt.update("insert into moms_musicset(ID,albumCode,Title,CoverURL,PubTime,state,Musics) values(?,?,?,?,?,?,?)",new Object[]{musicSetID,album.getAlbumCode(),album.getName(),album.getAlbumPicture(),album.getPubtime(),0,musics});
			int y = jt.update("insert into moms_albumproperty(ID,albumCode,singerCode,albumInitials,introduction) values(?,?,?,?,?)",new Object[]{getNextId("moms_albumproperty"),album.getAlbumCode(),album.getSingerCode(),album.getInitials(),album.getIntroduction()});
			if(!musics.equals("")){
				String[] musicIDs = musics.split(";");
				for(int j = 0; j < musicIDs.length; j++){
					jt.update("insert into moms_musicsetcontent(ID,MusicSetID,MusicID) values(?,?,?)",new Object[]{getNextId("moms_musicsetcontent"),musicSetID,musicIDs[j]});
				}
			}
			
			if(x != 1 || y != 1){
				if(x != 1){
					log.error("storageAlbum into moms_musicset error! albumCode: " + album.getAlbumCode());
				}
				if(y != 1){
					log.error("storageAlbum into moms_albumproperty error! albumCode: " + album.getAlbumCode());
				}
			}else{
				System.out.println("storageAlbum succeed! AlbumCode :" + album.getAlbumCode());
			}
		}
		System.out.println("storageAlbum end...");
	}
	
	/**
	 * 专辑歌曲入库
	 * @param albumList
	 */
	@Transactional(rollbackFor=Exception.class)
	public void storageAlbumMusic(final List<AlbumMusic> albumMusicList){
		System.out.println("storageAlbumMusic begin...");
		for(int i = 0; i < albumMusicList.size(); i++){
			AlbumMusic albumMusic = albumMusicList.get(i);
			int x = jt.update("insert into moms_albummusic(ID,albumCode,musicCode,serialNum) values(?,?,?,?)",new Object[]{getNextId("moms_albummusic"),albumMusic.getAlbumCode(),albumMusic.getMusicCode(),albumMusic.getSerialNum()});
			if(x != 1){
				log.error("storageAlbumMusic into moms_musicset error! albumCode: " + albumMusic.getAlbumCode());
			}else{
				System.out.println("storageAlbumMusic succeed! albumCode :" + albumMusic.getAlbumCode());
			}
		}
		System.out.println("storageAlbumMusic end...");
	}
	
	/**
	 * 榜单入库
	 * @param albumList
	 */
	@Transactional(rollbackFor=Exception.class)
	public void storageMusicList(final List<MusicList> musicLists){
		System.out.println("storageMusicList begin...");
		for(int i = 0; i < musicLists.size(); i++){
			MusicList MusicList = musicLists.get(i);
			int x = jt.update("insert into moms_musiclist(ID,name,musicCode,musicName,serialNum) values(?,?,?,?,?)",new Object[]{getNextId("moms_musiclist"),MusicList.getName(),MusicList.getSongCode(),MusicList.getSongName(),MusicList.getSerialNum()});
			if(x != 1){
				log.error("storageMusicList into moms_musicset error! serialNum: " + MusicList.getSerialNum());
			}else{
				System.out.println("storageMusicList succeed! serialNum :" + MusicList.getSerialNum());
			}
		}
		System.out.println("storageAlbumMusic end...");
	}
	
	/**
	 * 根据歌手编码得到歌手ID,歌手编码可能有多个，以','分隔
	 * @return string
	 */
	public String getSingerIDBySingerCode(String singerCode){
		String[] codes = singerCode.split("\\,");
		String output = "";
		for(int i = 0; i < codes.length; i++){
			try{
				Map<String,Integer> singerIDMap = jt.queryForMap("select ID from moms_singer where singerCode = ?",new Object[]{codes[i]});
				if(i == 0){
					output = "" + singerIDMap.get("ID");
				}else{
					output += "," + singerIDMap.get("ID");
				}
			}catch(EmptyResultDataAccessException e){
				System.out.println("singerCode为"+ codes[i] + "歌手不存在！");
			}
		}
		
		return output;
	}
	
	/**
	 * 根据albumCode查询musics
	 * @param albumCode
	 * @return string
	 */
	public String getMusicsByAlbum(String albumCode){
		List<Music> musicList = jt.query("select a.ID from moms_music a INNER JOIN moms_albummusic b ON a.OutCode = b.musicCode where b.albumCode =  " + albumCode, new MusicMapper() );
		String musics = "";
		for(int i = 0; i < musicList.size(); i++){
			try{
//				Map<String,Integer> musicsMap = jt.queryForMap("select ID from moms_music where OutCode = ?", new Object[]{musicList.get(i).getCode()});
				if(i == 0){
					musics = "" + musicList.get(i).getID();
				}else{
					musics += ";" + musicList.get(i).getID();
				}
			}catch(EmptyResultDataAccessException e){
				return "";
			}
		}
		return musics;
	}
	
	/**
	 * 得到此表的最新自增ID
	 * @param tableName
	 * @return ID
	 */
	public int getNextId(final String tableName){
		final String psql = "{call pNextID(?)}"; 
		return (Integer)jt.execute(
				 new CallableStatementCreator() {    
				        @Override
						public java.sql.CallableStatement createCallableStatement(
								java.sql.Connection con) throws SQLException {
				            java.sql.CallableStatement cs =  con.prepareCall(psql);    
				            cs.setString(1, tableName);// 设置输入参数的值    
				            return cs;
						}    
				     }, new CallableStatementCallback() {    
				         @Override
						public Object doInCallableStatement(
								java.sql.CallableStatement cs)
								throws SQLException, DataAccessException {
				             ResultSet rs =  cs.executeQuery();
				             int output = 0;
				             while(rs.next()){
				            	 output = Integer.parseInt(rs.getString(1));
				             }
							 return output;
						}    
				  }
				);
	}
	
	public String nowString(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	
}
