package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pack.Entity.Car;
import pack.service.Cache;
import pack.service.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    CarService carService;

    @Autowired
    Cache cache;

    @GetMapping(value = "/")
    public String homePage(ModelMap modelMap) {
        modelMap.addAttribute("cars", carService.getCars());
        cache.initThread();
        return "login";
    }

    String refreshData(ModelMap modelMap) {
        modelMap.addAttribute("cache", Cache.getCache().keySet());
        modelMap.addAttribute("count", Cache.getCache().size());
        modelMap.addAttribute("picture", Cache.findTheYoundest().getImage());
        return homePage(modelMap);
    }

    Car getCar(String carName) {
        Car ansCar;
        if (Cache.getCache().keySet().stream().anyMatch(car -> car.getName().equals(carName))) {
            ansCar = Cache.getCache().keySet().stream().filter(car -> car.getName().equals(carName)).findFirst().get();
        } else {
            ansCar = carService.getCarByName(carName);
        }
        Cache.addToCache(ansCar);
        return ansCar;
    }

    @PostMapping(value = "/sendCar")
    public String postMethod(@RequestParam String selectedCar, ModelMap modelMap) {
        modelMap.addAttribute("car", getCar(selectedCar));
        return refreshData(modelMap);
    }

    @RequestMapping(value = "/ajaxURL", method = RequestMethod.GET)
    @ResponseBody
    public String cacheHandler() {
        return Cache.getCarImages();
    }
}