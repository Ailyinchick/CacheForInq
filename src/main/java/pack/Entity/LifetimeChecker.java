package pack.Entity;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class LifetimeChecker extends Thread {

    Map<Object, Long> map;

    @Override
    public void run() {
        System.out.println("Поток стартовал");
        while (true) {
            Map<Object, Long> newMap = new HashMap<Object, Long>();
            for (Object object : map.keySet()
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

    public void setMap(Map<Object, Long> map) {
        this.map = map;
    }

    void refreshMap(Map<Object, Long> map) {
        this.map = map;
    }

    public Map<Object, Long> getMap() {
        return this.map;
    }

}
