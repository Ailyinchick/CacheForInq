package pack.Entity;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


@Component
public class LifetimeChecker extends Thread {

    Map<Car, Long> map;

    @Override
    public void run() {
        System.out.println("Поток стартовал");
        while (true) {
            Map<Car, Long> newMap = new HashMap<>();
            for (Car object : map.keySet()
            ) {
                for (Long date : map.values()
                ) {
                    if (System.currentTimeMillis() - date <= 10000) {
                        newMap.put(object, date);
                    }
                }
            }
            refreshMap(newMap);
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addToCache(Car object) {
        if (map.size() > 9) map.remove(findTheOldest());
        map.put(object, System.currentTimeMillis());
    }

    Car findTheOldest() {
        Car object = map.keySet().stream().findFirst().get();
        Long min = map.values().stream().min(Comparator.comparingLong(Long::longValue)).get();
        for (Car iteratingObject : map.keySet()
        ) {
            if (map.get(iteratingObject).equals(min)) object = iteratingObject;
        }
        return object;
    }

    public Car findTheYoundest() {
        Car car = map.keySet().stream().findFirst().get();
        Long min = map.values().stream().max(Comparator.comparingLong(Long::longValue)).get();
        for (Car iteratingObject : map.keySet()
        ) {
            if (map.get(iteratingObject).equals(min)) car = iteratingObject;
        }
        return car;
    }

    public void setMap(Map<Car, Long> map) {
        this.map = map;
    }

    void refreshMap(Map<Car, Long> map) {
        this.map = map;
    }

    public Map<Car, Long> getMap() {
        return this.map;
    }

}
