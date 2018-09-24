package training.busboard;

import java.io.IOException;
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
			
			BusInfo.getLocation(postcode);
			
			System.out.println("=====================================");
			System.out.println("Enter a new postcode: ");
		}
		reader.close();
	}
}
