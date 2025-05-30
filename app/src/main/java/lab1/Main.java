package lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть кількість потоків: ");
        int threadCount = scanner.nextInt();

        SequenceSumThread[] threads = new SequenceSumThread[threadCount];

        // Стартуємо обчислювальні потоки
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new SequenceSumThread(i + 1, i + 1);
            threads[i].start();
        }

        // Стартуємо керуючий потік, який по черзі зупиняє кожен потік
        SupervisorThread supervisor = new SupervisorThread(threads, 200); // затримка 200 мс
        supervisor.start();

        // Чекаємо завершення всіх обчислювальних потоків
        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        supervisor.join(); // Чекаємо завершення керуючого потоку
        System.out.println("Всі потоки завершили роботу.");
    }
}
