package thread.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int size;

    public synchronized int size() {
        return queue.size();
    }

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) throws Exception {
        synchronized (this) {
            while (queue.size() >= size) {
                wait();
            }
            notify();
            queue.add(value);
        }
    }

    public T poll() throws Exception {
        synchronized (this) {
            if (queue.isEmpty()) {
                wait();
            }
            notify();
            return queue.poll();
        }
    }
}