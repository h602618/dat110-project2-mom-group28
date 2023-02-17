package no.hvl.dat110.broker.processing.tests;

import no.hvl.dat110.client.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test4CreateDelete extends Test0Base {
	private static final String TESTTOPIC = "testtopic";

	@Test
	public void test() {
		Client client = new Client("client",BROKER_TESTHOST,BROKER_TESTPORT);
		broker.setMaxAccept(1);
		
		client.connect();
		client.createTopic(TESTTOPIC);
		client.deleteTopic(TESTTOPIC);
		client.disconnect();
	
		assertTrue(true);
	}

}
