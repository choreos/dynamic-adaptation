package usp.baile.greeting;

import javax.jws.WebService;

@WebService(endpointInterface="usp.baile.greeting.Greeting")
public class Ciao implements Greeting{

	private static final String greeting = "Ciao";
	
	@Override
	public String getGreeting(String name) {
		return greeting + name;
	}
}
