package thread.cashe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (key, value) -> {
            Base tempBase = memory.get(key);
            if (tempBase.getVersion() != model.getVersion()) {
                throw new OptimisticException("Model was changed");
            }
            Base base = new Base(key, value.getVersion() + 1);
            base.setName(model.getName());
            return base;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }
}
