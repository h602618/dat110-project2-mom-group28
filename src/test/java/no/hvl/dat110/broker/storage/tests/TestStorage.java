package no.hvl.dat110.broker.storage.tests;

import no.hvl.dat110.broker.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestStorage {
    private Storage storage;
    private static final String TESTUSER = "testuser";
    private static final String TESTTOPIC = "testtopic";

    @BeforeEach
    public void setUp() {
        storage = new Storage();
    }

    @Test
    public void testaddClientSession() {
        storage.addClientSession(TESTUSER, null);

        assertEquals(storage.getSessions().size(), 1);
        assertNotEquals(storage.getSession(TESTUSER), null);
    }

    @Test
    public void testremoveClientSession() {
        storage.addClientSession(TESTUSER, null);

        assertEquals(storage.getSessions().size(), 1);
        assertNotEquals(storage.getSession(TESTUSER), null);

        storage.removeClientSession(TESTUSER);

        assertEquals(storage.getSessions().size(), 0);
        assertNull(storage.getSession(TESTUSER));
    }

    @Test
    public void testcreateTopic() {
        storage.createTopic(TESTTOPIC);
        Set<String> topics = storage.getTopics();

        assertEquals(topics.size(), 1);
        assertTrue(topics.contains(TESTTOPIC));
    }

    @Test
    public void testdeleteTopic() {
        storage.createTopic(TESTTOPIC);
        storage.deleteTopic(TESTTOPIC);

        assertEquals(storage.getTopics().size(), 0);
    }

    @Test
    public void testaddSubscriber() {
        storage.createTopic(TESTTOPIC);
        storage.addSubscriber(TESTUSER, TESTTOPIC);
        Set<String> subscribers = storage.getSubscribers(TESTTOPIC);

        assertEquals(subscribers.size(), 1);
        assertTrue(subscribers.contains(TESTUSER));
    }

    @Test
    public void testaddSubscribers() {
        String TESTUSER1 = TESTUSER + "1";
        String TESTUSER2 = TESTUSER + "2";

        storage.createTopic(TESTTOPIC);
        storage.createTopic(TESTTOPIC + "1");

        storage.addSubscriber(TESTUSER1, TESTTOPIC);
        storage.addSubscriber(TESTUSER2, TESTTOPIC);
        Set<String> subscribers = storage.getSubscribers(TESTTOPIC);

        assertEquals(subscribers.size(), 2);
        assertTrue(subscribers.contains(TESTUSER1));
        assertTrue(subscribers.contains(TESTUSER2));
    }

    @Test
    public void testremoveSubscribers() {
        String TESTUSER1 = TESTUSER + "1";
        String TESTUSER2 = TESTUSER + "2";

        storage.createTopic(TESTTOPIC);

        storage.addSubscriber(TESTUSER1, TESTTOPIC);
        storage.addSubscriber(TESTUSER2, TESTTOPIC);

        storage.removeSubscriber(TESTUSER2, TESTTOPIC);
        Set<String> subscribers = storage.getSubscribers(TESTTOPIC);

        assertEquals(subscribers.size(), 1);
        assertTrue(subscribers.contains(TESTUSER1));
        assertFalse(subscribers.contains(TESTUSER2));
    }
}