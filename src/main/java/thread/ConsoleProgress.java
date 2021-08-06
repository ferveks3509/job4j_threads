package thread;

import java.nio.charset.Charset;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        try {
            String[] data = new  String[] {"\\", "|", "/"};
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1000);
                for (String el : data) {
                    System.out.print("\r loading : " + el);
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt();
    }
}
