package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pack.Entity.LifetimeChecker;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    LifetimeChecker lifetimeChecker;

    Map<Object, Long> cache = new HashMap<>();

    @GetMapping(value = "/")
    public String homePage() {
        initThread();
        return "login";
    }

    void initThread() {
        if (!lifetimeChecker.isAlive()) {
            lifetimeChecker.start();
            lifetimeChecker.setMap(cache);
        }
    }

    @GetMapping(value = "/nature")
    public String nature(ModelMap modelMap) {
        lifetimeChecker.getMap().put("<img src=\"resources/images/nature.jpeg\" alt=\"nature\">", System.currentTimeMillis());
        modelMap.addAttribute("cache", lifetimeChecker.getMap().keySet());
        modelMap.addAttribute("count", lifetimeChecker.getMap().size());
        return "login";
    }

    @GetMapping(value = "/car")
    public String car(ModelMap modelMap) {
        lifetimeChecker.getMap().put("<img src=\"resources/images/jiga.jpg\" alt=\"jiga\">", System.currentTimeMillis());
        modelMap.addAttribute("cache", lifetimeChecker.getMap().keySet());
        modelMap.addAttribute("count", lifetimeChecker.getMap().size());
        return "login";
    }

    @GetMapping(value = "/bart")
    public String vart(ModelMap modelMap) {
        lifetimeChecker.getMap().put("<img src=\"resources/images/bart.jpg\" alt=\"bart\">", System.currentTimeMillis());
        modelMap.addAttribute("cache", lifetimeChecker.getMap().keySet());
        modelMap.addAttribute("count", lifetimeChecker.getMap().size());
        return "login";
    }

}
