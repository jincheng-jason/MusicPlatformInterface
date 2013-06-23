package com.ysten.MusicPlatformInterface.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ysten.MusicPlatformInterface.domain.Album;
import com.ysten.MusicPlatformInterface.domain.AlbumMusic;
import com.ysten.MusicPlatformInterface.domain.Music;
import com.ysten.MusicPlatformInterface.domain.MusicList;
import com.ysten.MusicPlatformInterface.domain.Singer;
import com.ysten.MusicPlatformInterface.service.AnalysisService;
import com.ysten.MusicPlatformInterface.service.StorageService;
import com.ysten.MusicPlatformInterface.util.FileUtil;

public class TotalPackMain {

	private static final Log log = LogFactory.getLog(TotalPackMain.class);
	
	static AnalysisService analysisService;
	static StorageService storageService;
	
	public static void main(String[] args) {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("mysqlBeans.xml");
		analysisService = (AnalysisService) cxt.getBean("analysisService");
		storageService = (StorageService) cxt.getBean("storageService");
		FileUtil fileUtil = new FileUtil();
		
		System.out.println("analysis begin...");
		
		List<String> TXTList = fileUtil.getTXTFile();
		
		List<String> musicList = new ArrayList<String>();
		List<String> singerList = new ArrayList<String>();
		List<String> albumMusicList = new ArrayList<String>();
		List<String> albumList = new ArrayList<String>();
		List<String> musicLists = new ArrayList<String>();
		
		for(int i = 0; i < TXTList.size(); i++){
			String txtName = TXTList.get(i);
			System.out.println("开始分拣TXT文件: " + txtName);
			//歌手
			if(txtName.startsWith("i_m-songer")){
				singerList.add(txtName);
			}
			//歌曲
			else if(txtName.startsWith("i_m-music")){
				musicList.add(txtName);
			}
			//专辑歌曲
			else if(txtName.startsWith("i_m-albumSong")){
				albumMusicList.add(txtName);
			}
			//专辑
			else if(txtName.startsWith("i_m-album")){
				albumList.add(txtName);
			}
			//榜单
			else if(txtName.startsWith("i_m-list")){
				musicLists.add(txtName);
			}
			//上述都没有
			else{
				log.error("没有可识别的文件类型！");
			}
		}
		//解析并导入txt文件
		for(int i = 0; i < singerList.size(); i++){
			List<Singer> singers = analysisService.analysisSinger(singerList.get(i));
			if(singers.size() > 0){
				storageService.storageSinger(singers);
			}
		}
		for(int j = 0; j < musicList.size(); j++){
			List<Music> musics = analysisService.analysisMusic(musicList.get(j));
			if(musics.size() > 0){
				storageService.storageMusic(musics);
			}
		}
		for(int k = 0; k < albumMusicList.size(); k++){
			List<AlbumMusic> albumMusics = analysisService.analysisAlbumMusic(albumMusicList.get(k));
			if(albumMusics.size() > 0){
				storageService.storageAlbumMusic(albumMusics);
			}
		}
		for(int x = 0; x < albumList.size(); x++){
			List<Album> albums = analysisService.analysisAlbum(albumList.get(x));
			if(albums.size() > 0){
				storageService.storageAlbum(albums);
			}
		}
		for(int y = 0; y < musicLists.size(); y++){
			List<MusicList> musicListSet = analysisService.analysisMusicList(musicLists.get(y));
			if(musicListSet.size() > 0){
				storageService.storageMusicList(musicListSet);
			}
		}
		
		System.out.println("analysis end...");
	}
	
}
