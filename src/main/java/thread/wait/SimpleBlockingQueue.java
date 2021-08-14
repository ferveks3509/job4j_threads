package thread.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int size;

    public synchronized int size() {
        return queue.size();
    }

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) {
        synchronized (this) {
            while (queue.size() >= size) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            notify();
            queue.add(value);
        }
    }

    public T poll() {
        synchronized (this) {
            if (queue.isEmpty()) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            notify();
            return queue.poll();
        }
    }
}