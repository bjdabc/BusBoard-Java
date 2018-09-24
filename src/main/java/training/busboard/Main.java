package training.busboard;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

public class Main {
    public static void main(String args[]) {
        
    }
    
    public static void createClient() {
    	Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    	List<Widget> widgets = client
    		    .path("/widgets")
    		    .request(MediaType.APPLICATION_JSON_TYPE)
    		    .get(new GenericType<List<Widget>() {});
    }
    
}	
