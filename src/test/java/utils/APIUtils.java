package utils;

import java.io.File;

import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIUtils {

	public static String fetchVideoName(){
		String videoName = null;
		try {
			int rand = ((int) (Math.random()*(11 - 1))) + 1;
			
			OkHttpClient client = new OkHttpClient();
			
			HttpUrl url = new HttpUrl.Builder()
					.scheme("https")
					.host("13fc90e5.ngrok.io")
					.addPathSegments("video/" + rand)
					.build();
			
			Request request = new Request.Builder()
				      .url(url)
				      .build();
			
			 Response response = client.newCall(request).execute();
				JSONObject object = new JSONObject(response.body().string());
				videoName = (String) object.get("name");
	}catch(Exception e){
		e.printStackTrace();
	}
		return videoName;
}
	
	public static void postJsonfile(String url){
	try{
		OkHttpClient client = new OkHttpClient();
		
		 // Create upload file content mime type.
	    MediaType fileContentType = MediaType.parse("File/*");

	    String uploadFileRealPath = "test.json";

	    // Create file object.
	    File file = new File(uploadFileRealPath);

	    // Create request body.
	    RequestBody requestBody = RequestBody.create(fileContentType, file);

		Request request = new Request.Builder().url("https/c39ae9a5.ngrok.io/upload")
	                .post(requestBody).build();
	 
	        Response response = client.newCall(request).execute();
	}catch(Exception e){
		e.printStackTrace();
		
	}
}
}
