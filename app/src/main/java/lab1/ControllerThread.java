package lab1;

public class ControllerThread extends Thread {
    private final SequenceSumThread[] threads;
    private final int[] delays;

    public ControllerThread(SequenceSumThread[] threads, int[] delays) {
        this.threads = threads;
        this.delays = delays;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        boolean[] stopped = new boolean[threads.length];

        while (true) {
            long currentTime = System.currentTimeMillis();
            boolean allStopped = true;

            for (int i = 0; i < threads.length; i++) {
                if (!stopped[i]) {
                    allStopped = false;
                    if (currentTime - startTime >= delays[i]) {
                        threads[i].stopRunning();
                        stopped[i] = true;
                    }
                }
            }

            if (allStopped) {
                break;
            }

            try {
                Thread.sleep(50); 
            } catch (InterruptedException e) {
                break;
            }
        }

        for (SequenceSumThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Не вдалося дочекатися завершення потоку");
            }
        }

        System.out.println("Усі потоки завершено.");
    }
}
