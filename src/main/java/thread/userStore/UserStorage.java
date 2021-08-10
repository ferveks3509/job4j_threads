package thread.userStore;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<Integer, User> data = new HashMap<>();

    public boolean add(User user) {
        data.putIfAbsent(user.getId(), user);
        return true;
    }
    public boolean update(User user, long amount) {
        data.get(user.getId()).setAmount(user.getAmount() + amount);
        return true;
    }
    public boolean delete(User user) {
        return data.remove(user.getId(), user);
    }
    public User transfer(int fromId, int toId, int amount) {
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
