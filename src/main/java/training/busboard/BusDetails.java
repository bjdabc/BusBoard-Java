package training.busboard;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BusDetails {

	private ArrayList<BusStop> bus_stop_list = new ArrayList<>();
	
	public ArrayList<BusStop> getBusStopList() {
		return bus_stop_list;
	}
	
	public void getLocation(String postcode) throws IOException {

		String url = "https://api.postcodes.io/postcodes/" + postcode;

		JSONObject postcode_details = (JSONObject) JsonManipulator.parseJson(url).get("result");

		String longitude = (String) postcode_details.get("longitude").toString();
		String latitude = (String) postcode_details.get("latitude").toString();

		getBusStopsAtLocation(longitude, latitude);
	}

	private void getBusStopsAtLocation(String longitude, String latitude) throws IOException {

		String url = "https://transportapi.com/v3/uk/places.json?app_id=83e1a79b&app_key=23f46e1c"
				+ "5f18a697a386f1800ca3740f&lat=" + latitude + "&lon=" + longitude + "&type=bus_stop";

		JSONArray bus_stops = (JSONArray) JsonManipulator.parseJson(url).get("member");

		for (int i = 0; i < 2; i++) {

			JSONObject stop_details = (JSONObject) bus_stops.get(i);
			
			BusStop bus_stop = new BusStop();

			bus_stop.id = (String) stop_details.get("atcocode");
			bus_stop.name = (String) stop_details.get("name");
			
			

			//System.out.println("Bus Stop : " + bus.bus_stop_name);

			liveBusFeed(bus_stop);
			
			bus_stop_list.add(bus_stop);
			

		}
	}

	private void liveBusFeed(BusStop bus_stop) throws IOException {

		String url = "https://transportapi.com/v3/uk/bus/stop/" + bus_stop.id
				+ "/live.json?app_id=83e1a79b&app_key=23f46e1c5f18a697a386f1800ca3740f&group=route&limit=5&nextbuses=yes";

		JSONObject bus_stop_object = (JSONObject) (JsonManipulator.parseJson(url).get("departures"));

		for (Object bus_buses : bus_stop_object.keySet()) {

			JSONArray bus_number = (JSONArray) bus_stop_object.get(bus_buses);

			//System.out.println("Bus number: " + bus_buses);

			for (Object item : bus_number) {

				Bus bus = new Bus();
				
				bus.bus_number = bus_buses.toString();
				
				JSONObject element = (JSONObject) item;

				bus.destination = (String) element.get("direction");
				bus.time = (String) element.get("best_departure_estimate");

				//System.out.println("Destination: " + destination);
				//System.out.println("Time: " + time);
				
				bus_stop.list_of_buses.add(bus);
				//bus_list.add(bus);
				
			}
		}
	}
}
