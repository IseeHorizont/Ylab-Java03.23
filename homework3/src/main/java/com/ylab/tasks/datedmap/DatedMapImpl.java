package homework3.src.main.java.com.ylab.tasks.datedmap;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DatedMapImpl implements DatedMap {

    private Map<String, String> keyValueMap = new HashMap<>();
    private Map<String, Date> datesMap = new HashMap<>();

    @Override
    public void put(String key, String value) {
        keyValueMap.put(key, value);
        datesMap.put(key, new Date(System.currentTimeMillis()));
    }

    @Override
    public String get(String key) {
        return keyValueMap.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return keyValueMap.containsKey(key);
    }

    @Override
    public void remove(String key) {
        keyValueMap.remove(key);
        datesMap.remove(key);
    }

    @Override
    public Set<String> keySet() {
        return keyValueMap.keySet();
    }

    @Override
    public Date getKeyLastInsertionDate(String key) {
        return datesMap.get(key);
    }
}
