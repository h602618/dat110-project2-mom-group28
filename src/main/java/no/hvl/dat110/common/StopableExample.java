package no.hvl.dat110.common;

public class StopableExample extends Stopable {
    private int i = 0;

    public StopableExample() {
        super("stopable thread");
    }

    @Override
    public void doProcess() {
        System.out.println("stopable thread working:" + i);

        try {
            // simulate some processing time
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Stopable thread " + ex.getMessage());
            ex.printStackTrace();
        }

        i++;
    }
}