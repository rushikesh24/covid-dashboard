
package coviddashboard.bin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Sample {
	public static void main(String[] args) throws IOException {
		JSONArray json = (JSONArray) new JSONObject(getJSON("https://api.covid19india.org/data.json")).get("statewise");
		System.out.println(json);
		System.out.println(json.get(0));
		System.out.println(json.length());
	}
	
	public static String getJSON(String url) {
	    HttpURLConnection c = null;
	    try {
	        URL u = new URL(url);
	        c = (HttpURLConnection) u.openConnection();
	        c.setRequestMethod("GET");
	        c.connect();
	        int status = c.getResponseCode();

	        switch (status) {
	            case 200:
	            case 201:
	                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
	                StringBuilder sb = new StringBuilder();
	                String line;
	                while ((line = br.readLine()) != null) {
	                    sb.append(line+"\n");
	                }
	                br.close();
	                return sb.toString();
	        }

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	       if (c != null) {
	          try {
	              c.disconnect();
	          } catch (Exception ex) {
	             ex.printStackTrace();
	          }
	       }
	    }
	    return null;
	}
}
