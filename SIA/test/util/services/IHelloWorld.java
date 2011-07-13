package util.services;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IHelloWorld {
	
	public Message sayHi(@WebParam(name="text") String text);

}
