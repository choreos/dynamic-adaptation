package usp.baile.greeting;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;

import eu.choreos.vv.clientgenerator.Item;
import eu.choreos.vv.clientgenerator.WSClient;
import eu.choreos.vv.exceptions.FrameworkException;
import eu.choreos.vv.exceptions.InvalidOperationNameException;
import eu.choreos.vv.exceptions.WSDLException;

public class CallServices {

	public static void main(String[] args) throws WSDLException, XmlException, IOException, FrameworkException, InvalidOperationNameException {

		System.out.println("Starting...");
		
		WSClient client1 = new WSClient("http://localhost:1234/hello?wsdl");
		WSClient client2 = new WSClient("http://localhost:1235/oi?wsdl");
		WSClient client3 = new WSClient("http://localhost:1236/ciao?wsdl");

		System.out.println("Invoking methods...");
		
		Item item = client1.request("getGreeting");
		System.out.println("Item: " + item.toString());
		item = client2.request("getGreeting");
		System.out.println("Item: " + item.toString());
		item = client3.request("getGreeting");
		System.out.println("Item: " + item.toString());
		
		System.out.println("Finished");
	}
	
}
