package thread;

import java.io.*;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String file;

    public Wget(String url, int speed, String file) {
        this.url = url;
        this.speed = speed;
        this.file = file;
    }


    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fos = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long timeStartLoading = System.currentTimeMillis();
            long timeLoading;
            while ((bytesRead = in.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                fos.write(dataBuffer, 0, bytesRead);
                timeLoading = System.currentTimeMillis() - timeStartLoading;
                if (timeLoading < speed) {
                    Thread.sleep(speed - timeLoading);
                }
                timeStartLoading = System.currentTimeMillis();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length <= 2) {
            throw new IllegalArgumentException("args empty");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wGet = new Thread(new Wget(url, speed, "pom_tpm.xml"));
        wGet.start();
        wGet.join();
        /*
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(1000);
                            System.out.print("\rLoading :" + i + "%");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();

         */
    }
}
