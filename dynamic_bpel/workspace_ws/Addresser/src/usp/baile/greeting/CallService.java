package usp.baile.greeting;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;

import eu.choreos.vv.clientgenerator.WSClient;
import eu.choreos.vv.exceptions.FrameworkException;
import eu.choreos.vv.exceptions.InvalidOperationNameException;
import eu.choreos.vv.exceptions.WSDLException;

public class CallService {

	public static void main(String[] args) throws WSDLException, XmlException, IOException, FrameworkException, InvalidOperationNameException {

		System.out.println("Starting...");
		
		WSClient client1 = new WSClient("http://localhost:1237/addresser?wsdl");

		System.out.println("Invoking addresser...");
		client1.request("getEndpoint");
		System.out.println("Finished");
	}
	
}
