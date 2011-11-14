package usp.baile.greeting;

import javax.jws.WebService;

@WebService(endpointInterface="usp.baile.greeting.Greeting")
public class Oi implements Greeting {

	private static final String greeting = "Oi";
	
	@Override
	public String getGreeting() {
		return greeting;
	}
}
