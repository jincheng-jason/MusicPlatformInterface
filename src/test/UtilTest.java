package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ysten.MusicPlatformInterface.util.GZipUtil;

public class UtilTest {

	public static void main(String[] args){
//		GZipUtil g = new GZipUtil("D:/mm_ql_0523.tar.gz");
//		g.unTargzFile("D:/bbb");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyyMMddHH");
		String str_date = sdf.format(date);
		System.out.println(str_date);
	}
	
}
