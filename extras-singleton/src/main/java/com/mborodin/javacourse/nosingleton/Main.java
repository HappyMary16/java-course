package com.mborodin.javacourse.nosingleton;

import com.mborodin.javacourse.nosingleton.model.Car;
import com.mborodin.javacourse.nosingleton.service.ParkingService;
import com.mborodin.javacourse.nosingleton.service.StatisticService;

import java.time.Instant;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Без використання патернів");
        System.out.println("--------------------------");

        ParkingService parkingService = new ParkingService(10);
        StatisticService statisticService = new StatisticService();

        Car car = new Car("AA1111AA", Instant.now());
        System.out.println("Паркуємо автомобіль (в ParkingService): " + parkingService.parkCar(car));
        System.out.println("Припарковані автомобілі (з ParkingService): " + parkingService.getAllCars());
        System.out.println("Кількість автомобілів на парковці (з StatisticService): " + statisticService.getAmountOfCars());
        if (parkingService.getAllCars().size() != statisticService.getAmountOfCars()) {
            System.err.println("Сервіси мають різні дані, помилка!!!");
        }
    }
}