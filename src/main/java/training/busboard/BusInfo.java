package training.busboard;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BusInfo {

	public static void getLocation(String postcode) throws IOException {

		String url = "https://api.postcodes.io/postcodes/" + postcode;

		JSONObject postcode_details = (JSONObject) JsonManipulator.parseJson(url).get("result");

		String longitude = (String) postcode_details.get("longitude").toString();
		String latitude = (String) postcode_details.get("latitude").toString();

		getBusStopsAtLocation(longitude, latitude);
	}

	private static void getBusStopsAtLocation(String longitude, String latitude) throws IOException {

		String url = "https://transportapi.com/v3/uk/places.json?app_id=83e1a79b&app_key=23f46e1c"
				+ "5f18a697a386f1800ca3740f&lat=" + latitude + "&lon=" + longitude + "&type=bus_stop";

		JSONArray bus_stop = (JSONArray) JsonManipulator.parseJson(url).get("member");

		for (int i = 0; i < 2; i++) {

			JSONObject stop_details = (JSONObject) bus_stop.get(i);

			String stop = (String) stop_details.get("atcocode");
			String name = (String) stop_details.get("name");

			System.out.println("Bus Stop : " + name);

			liveBusFeed(stop);

		}
	}

	public static void liveBusFeed(String stop) throws IOException {

		String url = "https://transportapi.com/v3/uk/bus/stop/" + stop
				+ "/live.json?app_id=83e1a79b&app_key=23f46e1c5f18a697a386f1800ca3740f&group=route&limit=5&nextbuses=yes";

		JSONObject bus_stop = (JSONObject) (JsonManipulator.parseJson(url).get("departures"));

		for (Object bus_buses : bus_stop.keySet()) {

			JSONArray bus_number = (JSONArray) bus_stop.get(bus_buses);

			System.out.println("Bus number: " + bus_buses);

			for (Object item : bus_number) {

				JSONObject element = (JSONObject) item;

				String destination = (String) element.get("direction");
				String time = (String) element.get("best_departure_estimate");

				System.out.println("Destination: " + destination);
				System.out.println("Time: " + time);
			}
		}
	}
}
