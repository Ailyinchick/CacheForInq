package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pack.Entity.Car;
import pack.Entity.LifetimeChecker;
import pack.dao.CarRepository;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    LifetimeChecker lifetimeChecker;

    Map<Car, Long> cache = new HashMap<>();

    @GetMapping(value = "/")
    public String homePage(ModelMap modelMap) {
        modelMap.addAttribute("cars", carRepository.findAll(Car.class, carRepository.getBeanToBeAutowired()));
        initThread();
        return "login";
    }

    void initThread() {
        if (!lifetimeChecker.isAlive()) {
            lifetimeChecker.start();
            lifetimeChecker.setMap(cache);
        }
    }

    String refreshData(ModelMap modelMap) {
        modelMap.addAttribute("picture", lifetimeChecker.findTheYoundest().getImage());
        modelMap.addAttribute("cache", lifetimeChecker.getMap().keySet());
        modelMap.addAttribute("count", lifetimeChecker.getMap().size());
        return homePage(modelMap);
    }

    @GetMapping(value = "/audi_quattro")
    public String quattro(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("audi_quattro"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/x6")
    public String x6(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("x6"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/bmw_x5")
    public String bmwx5(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("bmw_x5"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/camaro_ss")
    public String camaro(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("camaro_ss"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/eclipse")
    public String eclipse(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("eclipse"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/eleanor")
    public String eleanor(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("eleanor"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/EXO_X")
    public String evo_x(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("EXO_X"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/focus")
    public String focus(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("focus"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/gt500")
    public String gt500(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("gt500"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/lanciaB")
    public String lanciaB(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("lanciaB"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/loncolnTC")
    public String loncolnTC(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("loncolnTC"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/r34")
    public String r34(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("r34"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/rx8")
    public String rx8(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("rx8"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/WRX_STI")
    public String WRX(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("WRX_STI"));
        return refreshData(modelMap);
    }

    @GetMapping(value = "/xc90")
    public String xc90(ModelMap modelMap) {
        lifetimeChecker.addToCache(carRepository.findCarByName("xc90"));
        return refreshData(modelMap);
    }


}