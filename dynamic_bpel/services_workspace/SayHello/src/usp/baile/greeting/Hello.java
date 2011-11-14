package usp.baile.greeting;

import javax.jws.WebService;

@WebService(endpointInterface="usp.baile.greeting.Greeting")
public class Hello implements Greeting {

	private static final String greeting = "Hello";
	
	@Override
	public String getGreeting() {
		return greeting;
	}
}
