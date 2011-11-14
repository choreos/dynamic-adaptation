package usp.baile.greeting;

import javax.xml.ws.Endpoint;

public class Launch {

	public static void main(String[] args) {

		System.out.println("Starting services...");
		
		Greeting service1 = new Hello();
		Endpoint endpoint1 = Endpoint.create(service1);
		endpoint1.publish("http://localhost:1234/hello");

		Greeting service2 = new Oi();
		Endpoint endpoint2 = Endpoint.create(service2);
		endpoint2.publish("http://localhost:1235/oi");

		Greeting service3 = new Ciao();
		Endpoint endpoint3 = Endpoint.create(service3);
		endpoint3.publish("http://localhost:1236/ciao");
	}

}
