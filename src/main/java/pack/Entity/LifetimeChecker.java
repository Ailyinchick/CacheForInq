package pack.Entity;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class LifetimeChecker extends Thread {

    Map<Car, Long> map;

    @Override
    public void run() {
        while (true) {
            filterMap(map);
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void filterMap(Map<Car, Long> map) {
        Map<Car, Long> varMap = new HashMap<>();
        varMap.putAll(
                map.entrySet().stream()
                        .filter(carLongEntry -> System.currentTimeMillis() - carLongEntry.getValue() <= 15000)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );
        this.map = varMap;
    }

    public void addToCache(Car object) {
        if (map.size() > 4) map.remove(findTheOldest());
        map.put(object, System.currentTimeMillis());
    }

    Car findTheOldest() {
        Long min = map.values().stream().min(Comparator.comparingLong(Long::longValue)).get();
        return map.keySet().stream().filter(car -> map.get(car).equals(min)).findFirst().get();

    }

    public Car findTheYoundest() {
        Long max = map.values().stream().max(Comparator.comparingLong(Long::longValue)).get();
        return map.keySet().stream().filter(car -> map.get(car).equals(max)).findFirst().get();
    }

    public void setMap(Map<Car, Long> map) {
        this.map = map;
    }

    public Map<Car, Long> getMap() {
        return this.map;
    }

}
