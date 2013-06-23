package test;

import java.io.BufferedReader;
import java.util.List;

import com.ysten.MusicPlatformInterface.util.FileUtil;

public class FileUtilTest {
	public static void main(String[] args) {
		FileUtil fileUtil = new FileUtil();
//		List<String> verifyList = fileUtil.getVerifyFile();
		List<String> TXTList = fileUtil.getTXTFile();
		
		try{
			String txtName = TXTList.get(0);
			System.out.println(txtName);
			BufferedReader bufferedReader = fileUtil.readTXTFile(txtName);
			String lineTXT = null;
			while((lineTXT=bufferedReader.readLine()) != null){
				char a = 31;
				String[] musicPros = lineTXT.split(String.valueOf(a));
				for(int i = 0; i < musicPros.length; i++){
					System.out.println(musicPros[i]);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
