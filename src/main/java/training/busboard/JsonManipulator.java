package training.busboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonManipulator {
	
	public static JSONObject parseJson(String url) {

		String content = "";
		try {
			content = readJSONToString(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONParser parser = new JSONParser();
		JSONObject a = null;

		try {
			a = (JSONObject) parser.parse(content.toString());
			return a;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return a;

	}
	
	private static String readJSONToString(String url) throws IOException {

		URL tfl_url = new URL(url);
		HttpURLConnection con = (HttpURLConnection) tfl_url.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();

		return content.toString();
	}
}
