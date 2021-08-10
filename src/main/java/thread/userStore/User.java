package thread.userStore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class User {

    private int id;
    @GuardedBy("this")
    private long amount;

    public User(int id, long amount) {
        this.id = id;
        this.amount = amount;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized long getAmount() {
        return amount;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

    public synchronized void setAmount(long amount) {
        this.amount = amount;
    }
}
