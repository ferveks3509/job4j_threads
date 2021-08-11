import org.junit.Test;
import thread.wait.SimpleBlockingQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void offer() throws Exception {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            sbq.offer(i);
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        Thread consumer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {

                            sbq.poll();
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
       producer.start();
       consumer.start();
       consumer.join();
       producer.join();
        assertThat(0, is(sbq.size()));
    }
}