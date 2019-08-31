package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import stepdefinition.SetUpSteps;

public class JsonUtil {

	public static void createJson(List<String> upcomingVideosName) throws IOException{
		  JSONObject videoDetails = new JSONObject();
		  videoDetails.put("team", "ThreadPool");
		  videoDetails.put("video", "search-video-name");
		  
		  JSONArray videosName = new JSONArray();
		  for(String upcomingVideoName : upcomingVideosName)
		  videosName.add(upcomingVideoName);
		  
		  videoDetails.put("upcoming-videos" ,videosName);
		  
		  
		  System.out.println("System Path " +  SetUpSteps.getTestDataPath());
		  clearTheFile();
		  FileWriter file = null;
		  try {
			  file = new FileWriter(SetUpSteps.getTestDataPath() + "db1.json");
		 		file.write(videoDetails.toJSONString());
				 
			System.out.println("JSON Object: " + videoDetails);
			}catch (IOException e) {
	            e.printStackTrace();
	}finally {
		file.flush();
		file.close();
	}
	}
	
	public static void clearTheFile() {
        FileWriter fwOb;
		try {
			fwOb = new FileWriter(SetUpSteps.getTestDataPath() + "db1.json", false);
			PrintWriter pwOb = new PrintWriter(fwOb, false);
	        pwOb.flush();
	        pwOb.close();
	        fwOb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
    }
}
