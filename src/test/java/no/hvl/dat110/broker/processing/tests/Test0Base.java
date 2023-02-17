package no.hvl.dat110.broker.processing.tests;

import no.hvl.dat110.broker.Broker;
import no.hvl.dat110.broker.Dispatcher;
import no.hvl.dat110.broker.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class Test0Base {
    protected Dispatcher dispatcher;
    protected Broker broker;
    protected Storage storage;
    protected final int BROKER_TESTPORT = 8080;
    protected final String BROKER_TESTHOST = "localhost";

    @BeforeEach
    public void setUp() throws Exception {
        storage = new Storage();
        dispatcher = new Dispatcher(storage);
        broker = new Broker(dispatcher, BROKER_TESTPORT);

        dispatcher.start();
        broker.start();

        // allow broker to reaching waiting for incoming connections
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        try {
            Thread.sleep(10000); // let the system run for a while
            broker.join();
            dispatcher.doStop();
            dispatcher.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
