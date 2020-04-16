package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.Entity.Car;
import pack.dao.CarRepository;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getCars() {
        return carRepository.findAll(Car.class, carRepository.getBeanToBeAutowired());
    }

    public Car getCarByName(String carName) {
        return carRepository.findCarByName(carName);
    }

}
