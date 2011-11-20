package usp.baile.greeting;

import javax.xml.ws.Endpoint;

public class LaunchAddress {

	public static void main(String[] args) {

		System.out.println("Starting addresser service...");
		
		Addresser service1 = new AddresserImpl();
		Endpoint endpoint1 = Endpoint.create(service1);
		endpoint1.publish("http://localhost:1237/addresser");
	}

}
