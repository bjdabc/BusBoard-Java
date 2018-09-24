package training.busboard;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
	public static void main(String args[]) {
		try {
			interactAPI("0180BAC30592");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void interactAPI(String stop) throws IOException {
		//Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

		String url = "https://transportapi.com/v3/uk/bus/stop/" + stop
				+ "/live.json?app_id=83e1a79b&app_key=23f46e1c5f18a697a386f1800ca3740f&group=route&limit=5&nextbuses=yes";

		String content = readJSONToString(url);

		JSONParser parser = new JSONParser();
		JSONObject a = null;
		try {
			a = (JSONObject) parser.parse(content.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject bus_stop = (JSONObject) (a.get("departures"));

		for (Object bus_buses : bus_stop.keySet()) {

			JSONArray bus_number = (JSONArray) bus_stop.get(bus_buses);

			// System.out.println(bus_stop.keySet());

			for (Object item : bus_number) {

				JSONObject element = (JSONObject) item;

				String destination = (String) element.get("direction");
				String time = (String) element.get("best_departure_estimate");

				System.out.println("Destination: " + destination);
				System.out.println("Time: " + time);
			}
		}
	}

	public static String readJSONToString(String url) throws IOException {

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
