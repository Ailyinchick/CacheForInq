package pack.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.Entity.Car;

@Repository
public class CarRepository implements DaoRepos<Car> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public SessionFactory getBeanToBeAutowired() {
        return sessionFactory;
    }

    public Car findCarByName(String carName) {
        return findAll(Car.class, sessionFactory).stream().filter(car -> car.getName().equals(carName)).findFirst().get();
/*        Car car = new Car();
        for (Car iteratingCar : findAll(Car.class, sessionFactory)
        ) {
            if (iteratingCar.getName().equals(carName)) car = iteratingCar;
        }
        return car;*/
    }
}
