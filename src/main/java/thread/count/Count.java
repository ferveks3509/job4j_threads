package thread.count;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public Count(int value) {
        this.value = value;
    }

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int getValue() {
        return value;
    }
}
