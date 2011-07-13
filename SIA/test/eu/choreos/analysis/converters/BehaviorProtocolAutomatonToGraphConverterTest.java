package eu.choreos.analysis.converters;


import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import util.factory.BehaviorProtocolAutomatonFactory;
import util.services.HelloWorld;
import util.services.IHelloWorld;

import eu.choreos.analysis.SIA;
import eu.choreos.analysis.SIA_Adapter;
import eu.choreos.analysis.entity.OverallStabilityResults;
import eu.choreos.middleware.entity.ChoreographyModel;

public class BehaviorProtocolAutomatonToGraphConverterTest {
	
	

}
