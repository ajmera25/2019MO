package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public static void main(String[] args) throws Exception {
		String strPath = System.getProperty("user.dir") + "/src/YouTubeBangalore/YouTubeFile.json"; 
		@SuppressWarnings("unchecked")
		List<String> lstString = new ArrayList();
		lstString.add("Video1");
		lstString.add("Video2");
		lstString.add("Video3");
		updateJSONFile(strPath, "TEAM-MO", "Selenium JSON", lstString);
	}
	
	public static void updateJSONFile(String strFilePath, String strTeamName, 
			String strVideoName, List<String> lstUpcomingVideos) throws Exception{
		File file = new File(strFilePath);
		BufferedReader reader = new BufferedReader(new FileReader(strFilePath));
		String line = "", oldtext = "";
		boolean isFirstLine = true;
		while((line = reader.readLine()) != null){
			if(isFirstLine){
				oldtext += line;
			}else{
				oldtext += "\r\n" + line;
			}
			isFirstLine = false;
		}
		reader.close();
		oldtext = oldtext.replaceAll("#T#", strTeamName);
		oldtext = oldtext.replaceAll("#V#", strVideoName);
		String strVideo = "";

		for(int i = 0; i < lstUpcomingVideos.size(); i++){
			strVideo = strVideo + lstUpcomingVideos.get(i);
			if(i != lstUpcomingVideos.size()-1){
				strVideo = strVideo + "\",\"";
			}
			
		}
		oldtext = oldtext.replaceAll("#U#", strVideo);
		
		FileWriter writer = new FileWriter(strFilePath);
		writer.write(oldtext);
		writer.close();
		System.out.println("Final json file is: ===> " + oldtext);
	}
	
}
