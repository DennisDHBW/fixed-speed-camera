package main;

import fixedspeedcamera.FixedSpeedCamera;

import fixedspeedcamera.OwnerDatabase;
import lombok.extern.slf4j.Slf4j;
import person.Person;
import person.Technician;
import vehicle.Car;
import vehicle.Vehicle;
import vehicle.enums.CarType;
import vehicle.enums.VehicleType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class Application {
    public static void main(String[] args) {

        OwnerDatabase ownerDatabase = new OwnerDatabase();

        LocalDate dob1 = LocalDate.parse("1997-08-26", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Person person1 = Person.builder()
                .name("Dennis")
                .dateOfBirth(dob1)
                .build();

        Vehicle car1 = Car.builder()
                .vehicleType(VehicleType.CAR)
                .maxSpeed(200)
                .licensePlate("AB-CD-123")
                .type(CarType.COUPE)
                .build();
        person1.buyVehicle(car1);
        ownerDatabase.registration(car1.getLicensePlate(), person1.getName());

        FixedSpeedCamera camera1 = FixedSpeedCamera.builder()
                .location("Heilbronn")
                .build();

        LocalDate dob2 = LocalDate.parse("1970-05-11", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Technician technician1 = Technician.builder()
                .name("Johann")
                .dateOfBirth(dob2)
                .build();
        technician1.addCamera(camera1);

        log.info(ownerDatabase.getOwner(car1.getLicensePlate()));


    }
}
