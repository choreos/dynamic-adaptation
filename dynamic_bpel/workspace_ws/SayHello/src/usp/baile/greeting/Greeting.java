package usp.baile.greeting;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Greeting {

	@WebMethod
	String getGreeting(String name);
}
