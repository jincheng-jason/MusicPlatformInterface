package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ysten.MusicPlatformInterface.service.StorageService;
import com.ysten.MusicPlatformInterface.util.VerifyUtil;

public class StorageTest {

	static StorageService storageService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("mysqlBeans.xml");
		storageService = (StorageService) cxt.getBean("storageService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
//	@Test
//	public void musicStorageTest(){
//		List<Music> musicList = new ArrayList<Music>();
//		Music music = new Music();
//		music.setCode("60050200001");
//		music.setName("和你一样");
//		music.setSingerID("320");
//		music.setIndate("20140420");
//		music.setProductMask("1111");
//		musicList.add(music);
//		
//		Music music1 = new Music();
//		music1.setCode("60050200002");
//		music1.setName("不能怕");
//		music1.setSingerID("124236");
//		music1.setIndate("20140420");
//		music1.setProductMask("1111");
//		musicList.add(music1);
//		
//		Music music2 = new Music();
//		music2.setCode("60050200003");
//		music2.setName("新家");
//		music2.setSingerID("147");
//		music2.setIndate("20140420");
//		music2.setProductMask("1111");
//		musicList.add(music2);
//		
//		storageService.storageMusic(musicList);
//	}
	
	@Test
	public void Test(){
		String date = "20140420";
		VerifyUtil verify = new VerifyUtil();
		System.out.println(verify.string2Datetime(date));
	}
	
	@Test
	public void getNextIdTest(){
		int id = storageService.getNextId("moms_music");
		System.out.println(id);
	}

	@Test
	public void getSingerIDBySingerCodeTest(){
		String ids = storageService.getSingerIDBySingerCode("473469,319134,126532");
		System.out.println(ids);
	}
	
	@Test
	public void getMusicsByAlbumTest(){
		String musics = storageService.getMusicsByAlbum("24286");
		System.out.println(musics);
	}
	
}
