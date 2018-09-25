package training.busboard.web;

import java.io.IOException;
import java.util.ArrayList;

//import training.busboard.Bus;
import training.busboard.BusDetails;
import training.busboard.BusStop;

public class BusInfo {
    private final String postcode;
    private BusDetails details;
    private ArrayList<BusStop> bus_stop_list;

    public BusInfo(String postcode) {
        this.postcode = postcode;
        this.details = new BusDetails();
        try {
			details.getLocation(postcode);
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.bus_stop_list = details.getBusStopList();
        
        
    }
    
    public ArrayList<BusStop> getBusList() {
    	return this.bus_stop_list;
    }

    public ArrayList<BusStop> getPostcode() {
    	
		
    	
		System.out.println("About to return");
		return this.bus_stop_list;
    }
}
