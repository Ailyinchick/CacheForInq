package pack.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pack.Entity.Car;
import pack.Entity.LifetimeChecker;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Component
public class Cache {

    @Autowired
    LifetimeChecker lifetimeChecker;

    private static volatile Map<Car, Long> cache;

    static {
        if (cache == null) cache = new HashMap<>();
    }

    public void initThread() {
        if (!lifetimeChecker.isAlive()) lifetimeChecker.start();
        lifetimeChecker.setMap(cache);
    }

    public static void addToCache(Car car) {
        if (cache.size() > 4) cache.remove(findTheOldest());
        cache.put(car, System.currentTimeMillis());
    }

    static Car findTheOldest() {
        Long min = cache.values().stream().min(Comparator.comparingLong(Long::longValue)).get();
        return cache.keySet().stream().filter(car -> cache.get(car).equals(min)).findFirst().get();
    }

    public static Car findTheYoundest() {
        Long max = cache.values().stream().max(Comparator.comparingLong(Long::longValue)).get();
        return cache.keySet().stream().filter(car -> cache.get(car).equals(max)).findFirst().get();
    }

    public static Map<Car, Long> getCache() {
        return cache;
    }

}
