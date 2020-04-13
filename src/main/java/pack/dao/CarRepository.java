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
        System.out.println("Лезем в БД");
        return findAll(Car.class, sessionFactory).stream().filter(car -> car.getName().equals(carName)).findFirst().get();
    }
}
