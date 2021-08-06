package thread;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Arrays;

public class Wget implements Runnable{
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fos = new FileOutputStream("pom_tpm.xml")) {
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
        String[] temp = new String[] {"https://github.com/ferveks3509/job4j_threads/blob/master/pom.xml", "1000"};
        main(temp);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wGet = new Thread(new Wget(url, speed));
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
