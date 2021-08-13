package thread.userStore;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<Integer, User> data = new HashMap<>();

    public synchronized boolean add(User user) {
        return data.putIfAbsent(user.getId(), user) == null;
    }
    public synchronized boolean update(User user) {
        return data.replace(user.getId(), user) == null;
    }
    public synchronized boolean delete(User user) {
        return data.remove(user.getId(), user);
    }
    public synchronized User transfer(int fromId, int toId, int amount) {
        if (!data.containsKey(fromId) && !data.containsKey(toId)) {
            return null;
        }
        User fromUser = data.get(fromId);
        User toUser = data.get(toId);
        if (fromUser.getAmount() > 0) {
            fromUser.setAmount(fromUser.getAmount() - amount);
            toUser.setAmount(toUser.getAmount() + amount);
        }
        return toUser;
    }
}
