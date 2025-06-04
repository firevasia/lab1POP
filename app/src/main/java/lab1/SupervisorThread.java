package lab1;

public class SupervisorThread extends Thread {
    private final SequenceSumThread[] threads;
    private final long delay;

    public SupervisorThread(SequenceSumThread[] threads, long delay) {
        this.threads = threads;
        this.delay = delay;
    }

    @Override
    public void run() {
        for (int i = 0; i < threads.length; i++) {
            try {
                Thread.sleep(delay); 
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            threads[i].stopRunning(); 
        }
    }
}
