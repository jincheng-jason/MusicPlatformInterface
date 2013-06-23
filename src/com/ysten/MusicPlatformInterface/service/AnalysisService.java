package com.ysten.MusicPlatformInterface.service;

import java.io.BufferedReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
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
import com.ysten.MusicPlatformInterface.util.FileUtil;

@Service("analysisService")
@Transactional
public class AnalysisService {

	private static final Log log = LogFactory.getLog(AnalysisService.class);
	
	/**
	 * 解析音乐文件
	 * @param bufferedReader
	 * @return
	 */
	public List<Music> analysisMusic(String txtName){
		System.out.println("开始解析文件： " + txtName);
		BufferedReader bufferedReader = readTXT(txtName);
		List<Music> musicList = new ArrayList<Music>();
		try{
			FileUtil fileUtil = new FileUtil();
			String lineTXT = null;
			while((lineTXT=bufferedReader.readLine()) != null){
				if(!lineTXT.equals("")){
					String[] musicPros = fileUtil.splitString(lineTXT);
					Music music = new Music();
					for(int j = 0; j < musicPros.length; j++){
						if(j == 0){
							music.setCode(musicPros[j]);
						}
						if(j == 1){
							music.setName(musicPros[j]);
						}
						if(j == 2){
							//歌手编码可能有多个，以"|"分隔
							music.setSingerCode(musicPros[j]);
						}
						if(j == 3){
							music.setIndate(musicPros[j]);
						}
						if(j == 4){
							music.setProductMask(musicPros[j]);
						}
						if(j == 5){
							//发布时间略去
		//					music.setPubtime(musicPros[j]);
						}
						if(j == 6){
							music.setOperateType(Integer.parseInt(musicPros[j]));
						}
					}
					musicList.add(music);
				}else{
					System.out.println("此行为空！");
				}
			}
			
		}catch(Exception e){
			log.error("读取文件内容出错！--AnalysisService.analysisMusic");
			e.printStackTrace();
		}
		return musicList;
	}
	
	/**
	 * 解析歌手
	 * @param bufferedReader
	 * @return list
	 */
	public List<Singer> analysisSinger(String txtName){
		System.out.println("开始解析文件： " + txtName);
		BufferedReader bufferedReader = readTXT(txtName);
		List<Singer> singerList = new ArrayList<Singer>();
		try{
			String lineTXT = null;
			FileUtil fileUtil = new FileUtil();
			while((lineTXT=bufferedReader.readLine()) != null){
				String[] singerPros = fileUtil.splitString(lineTXT);
				Singer singer = new Singer();
				for(int j = 0; j < singerPros.length; j++){
					if(j == 0){
						singer.setSingerCode(singerPros[j]);
					}
					if(j == 1){
						singer.setInitials(singerPros[j]);
					}
					if(j == 2){
						singer.setSingerName(singerPros[j]);
					}
					if(j == 3){
						singer.setIntroduction(singerPros[j]);
					}
					if(j == 4){
						singer.setSingerPicture(singerPros[j]);
					}
					if(j == 5){
						singer.setProperty(singerPros[j]);
					}
					if(j == 6){
						singer.setOperateType(Integer.parseInt(singerPros[j]));
					}
				}
				singerList.add(singer);
			}
		}catch(Exception e){
			log.error("读取文件内容出错！--AnalysisService.analysisSinger");
			e.printStackTrace();
		}
		return singerList;
	}
	
	/**
	 * 解析专辑
	 * @param bufferedReader
	 * @return list
	 */
	public List<Album> analysisAlbum(String txtName){
		System.out.println("开始解析文件： " + txtName);
		BufferedReader bufferedReader = readTXT(txtName);
		List<Album> albumList = new ArrayList<Album>();
		try{
			String lineTXT = null;
			FileUtil fileUtil = new FileUtil();
			while((lineTXT=bufferedReader.readLine()) != null){
				String[] albumPros = fileUtil.splitString(lineTXT);
				Album album = new Album();
				for(int j = 0; j < albumPros.length; j++){
					if(j == 0){
						album.setAlbumCode(albumPros[j]);
					}
					if(j == 1){
						album.setSingerCode(albumPros[j]);
					}
					if(j == 2){
						album.setInitials(albumPros[j]);
					}
					if(j == 3){
						album.setName(albumPros[j]);
					}
					if(j == 4){
						album.setIntroduction(albumPros[j]);
					}
					if(j == 5){
						album.setAlbumPicture(albumPros[j]);
					}
					if(j == 6){
//						album.setPubtime(albumPros[j]);
					}
					if(j == 7){
						album.setOperateType(Integer.parseInt(albumPros[j]));
					}
				}
				albumList.add(album);
			}
		}catch(Exception e){
			log.error("读取文件内容出错！--AnalysisService.analysisAlbum");
			e.printStackTrace();
		}
		return albumList;
	}
	
	/**
	 * 解析专辑歌曲
	 * @param bufferedReader
	 * @return list
	 */
	public List<AlbumMusic> analysisAlbumMusic(String txtName){
		System.out.println("开始解析文件： " + txtName);
		BufferedReader bufferedReader = readTXT(txtName);
		List<AlbumMusic> albumMusicList = new ArrayList<AlbumMusic>();
		try{
			String lineTXT = null;
			FileUtil fileUtil = new FileUtil();
			while((lineTXT=bufferedReader.readLine()) != null){
				String[] albumMusicPros = fileUtil.splitString(lineTXT);
				AlbumMusic albumMusic = new AlbumMusic();
				for(int j = 0; j < albumMusicPros.length; j++){
					if(j == 0){
						albumMusic.setAlbumCode(albumMusicPros[j]);
					}
					if(j == 1){
						albumMusic.setMusicCode(albumMusicPros[j]);
					}
					if(j == 2){
						albumMusic.setSerialNum(Integer.parseInt(albumMusicPros[j]));
					}
					if(j == 3){
						albumMusic.setOperateType(Integer.parseInt(albumMusicPros[j]));
					}
				}
				albumMusicList.add(albumMusic);
			}
		}catch(Exception e){
			log.error("读取文件内容出错！--AnalysisService.analysisAlbum");
			e.printStackTrace();
		}
		return albumMusicList;
	}
	
	/**
	 * 解析榜单
	 * @param bufferedReader
	 * @return list
	 */
	public List<MusicList> analysisMusicList(String txtName){
		System.out.println("开始解析文件： " + txtName);
		BufferedReader bufferedReader = readTXT(txtName);
		List<MusicList> musicLists = new ArrayList<MusicList>();
		try{
			String lineTXT = null;
			FileUtil fileUtil = new FileUtil();
			while((lineTXT=bufferedReader.readLine()) != null){
				String[] musicListPros = fileUtil.splitString(lineTXT);
				MusicList musicList = new MusicList();
				for(int j = 0; j < musicListPros.length; j++){
					if(j == 0){
						musicList.setName(musicListPros[j]);
					}
					if(j == 1){
						musicList.setSongCode(musicListPros[j]);
					}
					if(j == 2){
						musicList.setSongName(musicListPros[j]);
					}
					if(j == 3){
						musicList.setSerialNum(Integer.parseInt(musicListPros[j]));
					}
				}
				musicLists.add(musicList);
			}
		}catch(Exception e){
			log.error("读取文件内容出错！--AnalysisService.analysisAlbum");
			e.printStackTrace();
		}
		return musicLists;
	}
	
	public BufferedReader readTXT(String txtName){
		FileUtil fileUtil = new FileUtil();
		return fileUtil.readTXTFile(txtName);
	}
	
}
