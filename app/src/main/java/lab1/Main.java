package lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть кількість потоків: ");
        int threadCount = scanner.nextInt();

        SequenceSumThread[] threads = new SequenceSumThread[threadCount];
        int[] delays = new int[threadCount]; 

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new SequenceSumThread(i + 1, i + 1);
            delays[i] = 200 * (i + 1); 
            threads[i].start();
        }

        ControllerThread controller = new ControllerThread(threads, delays);
        controller.start();
        controller.join(); 

        System.out.println("Всі потоки завершили роботу.");
    }
}
