package lab1;

public class SequenceSumThread extends Thread {
    private final int threadId;
    private final int step;
    private volatile boolean keepRunning = true;
    private double sum = 0;
    private int count = 0;
    private long startTime;

    public SequenceSumThread(int threadId, int step) {
        this.threadId = threadId;
        this.step = step;
    }

    public void stopRunning() {
        keepRunning = false;
    }

    @Override
    public void run() {
        double current = 0;
        startTime = System.currentTimeMillis();

        while (keepRunning) {
            sum += current;
            current += step;
            count++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("Потік #%d завершив роботу. Сума = %.2f, кількість доданків = %d, час роботи = %d мс%n",
                threadId, sum, count, endTime - startTime);
    }
}
