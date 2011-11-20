package usp.baile.greeting;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;

import eu.choreos.vv.clientgenerator.WSClient;
import eu.choreos.vv.exceptions.FrameworkException;
import eu.choreos.vv.exceptions.InvalidOperationNameException;
import eu.choreos.vv.exceptions.WSDLException;

public class CallOrchestration {

	public static void main(String[] args) throws WSDLException, XmlException, IOException, FrameworkException, InvalidOperationNameException, NoSuchFieldException {
		
		System.out.println("Starting...");
		WSClient client1 = new WSClient("http://localhost:8084/petals/services/GreetProcessService?wsdl");
		System.out.println("Invoking process...");
		client1.request("process", " Matias");
		System.out.println("Finished");
	}

}
