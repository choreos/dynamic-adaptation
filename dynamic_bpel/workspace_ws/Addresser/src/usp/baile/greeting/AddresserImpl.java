package usp.baile.greeting;

import javax.jws.WebService;

@WebService(endpointInterface="usp.baile.greeting.Addresser")
public class AddresserImpl implements Addresser {

	private static int count = 0;
	private static String[] endpoints = {"http://localhost:1234/hello", 
										"http://localhost:1235/oi", 
										"http://localhost:1236/ciao"};
	
	@Override
	public String getEndpoint() {
		count++;
		return endpoints[count%3];
	}

}
