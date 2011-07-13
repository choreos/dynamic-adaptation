package util.services;

import javax.jws.WebService;


@WebService(endpointInterface = "services.HelloWorld",
        serviceName = "HelloWorld")
public class HelloWorld implements IHelloWorld {

	public Message sayHi(String text) {
	    System.out.println("sayHi called");
	    Message m = new Message();
	    m.setText("Hello " + text);
	    return m;
	}

}