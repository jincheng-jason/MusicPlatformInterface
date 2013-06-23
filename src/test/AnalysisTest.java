package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ysten.MusicPlatformInterface.service.AnalysisService;

public class AnalysisTest {

	static AnalysisService analysisService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("mysqlBeans.xml");
		analysisService = (AnalysisService) cxt.getBean("analysisService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

//	@Test
//	public void readMusicTest(){
//		analysisService.analysisAndStorage();
//	}
	
	
	
}
