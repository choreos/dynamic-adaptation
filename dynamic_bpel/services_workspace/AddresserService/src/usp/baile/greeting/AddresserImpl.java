package usp.baile.greeting;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

@WebService(endpointInterface="usp.baile.greeting.Addresser")
public class AddresserImpl implements Addresser {

	private Map<String, String> endpoints; // the language is the key
	private String language;
	
	public AddresserImpl() {
		
		// TODO: set real endpoints
		endpoints = new HashMap<String, String>();
		endpoints.put("en", "http://...");
		endpoints.put("it", "http://...");
		endpoints.put("pt", "http://...");
		
		language = "en"; // default language
	}

	@Override
	public String getServiceEndpoint() {

		return endpoints.get(language);
	}

	@Override
	public void setLanguage(String language) {

		this.language = language;
	}

	
}
