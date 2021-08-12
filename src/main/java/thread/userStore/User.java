package thread.userStore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;


public class User {

    private int id;
    private long amount;

    public User(int id, long amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
