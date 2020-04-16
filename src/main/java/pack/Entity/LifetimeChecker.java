package pack.Entity;

import org.springframework.stereotype.Component;

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
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void filterMap(Map<Car, Long> map) {
        Map<Car, Long> varMap;
        varMap = map.entrySet().stream()
                .filter(carLongEntry -> System.currentTimeMillis() - carLongEntry.getValue() > 15000)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        varMap.keySet().forEach(map::remove);
    }

    public void setMap(Map<Car, Long> map) {
        this.map = map;
    }

}