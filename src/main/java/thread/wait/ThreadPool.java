package thread.wait;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int sizeProcessor = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(sizeProcessor);

    public void work(Runnable job) {
        for (int i = 0; i< sizeProcessor; i++) {
           Thread thread = new Thread(
                   () -> {
                       while (!Thread.currentThread().isInterrupted()) {
                           try {
                               job.run();
                           } catch (Exception e) {
                               Thread.currentThread().interrupt();
                           }
                       }
                   }
           );
           tasks.offer(thread);
           thread.start();
        }
    }

    public void shutdown() {
        for (int i = 0 ; i < tasks.size(); i++) {
            tasks.poll();
        }
    }
}
