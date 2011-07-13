package eu.choreos.middleware.adaptation;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import util.services.HelloWorld;
import util.services.IHelloWorld;

public class ChoreographyReconfiguratorTest {

private static Server server;
	
	@BeforeClass
	public static void setUp() throws Exception {
		HelloWorld helloWorldService = new HelloWorld();
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(IHelloWorld.class);
		svrFactory.setAddress("http://localhost:9000/helloWorld");
		svrFactory.setServiceBean(helloWorldService);
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		server = svrFactory.create();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		server.stop();
		server.destroy();
	}
	
}
