package no.hvl.dat110.broker.processing.tests;

import no.hvl.dat110.client.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test1ConnectDisconnect extends Test0Base {
    @Test
    public void test() {
        Client client = new Client("testuser", BROKER_TESTHOST, BROKER_TESTPORT);
        broker.setMaxAccept(1); // only 1 connect in this scenario

        client.connect();
        client.disconnect();

        assertTrue(true);
    }
}