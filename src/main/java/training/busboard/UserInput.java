package training.busboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInput {
	
	public static void Input() throws IOException {
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a postcode: ");
		
		while (reader.hasNextLine()) {
			String postcode = reader.nextLine();

			if (postcode.equals("exit")) {
				System.out.println("Bye, Bye ");
				break;
			}
			
			BusDetails details = new BusDetails();
			details.getLocation(postcode);
			
			
			ArrayList<BusStop> bus_stop_list = details.getBusStopList();
			
			for (BusStop bus_stop : bus_stop_list)
			{
				System.out.println("Bus stop: " + bus_stop.name);
				for (Bus bus : bus_stop.list_of_buses)
				{
					//System.out.println("Bus Stop : " + bus.bus_stop.name);
					System.out.println("Bus Number : " + bus.bus_number);
					System.out.println("Destination: " + bus.destination);
					System.out.println("Time: " + bus.time);
				}
			}
			
			System.out.println("=====================================");
			System.out.println("Enter a new postcode: ");
		}
		reader.close();
	}
}
