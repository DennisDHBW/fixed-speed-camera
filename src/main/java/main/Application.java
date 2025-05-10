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
import java.util.Random;

@Slf4j
public class Application {
    public static void main(String[] args) {

        final String OWNER_DATABASE_CSV_PATH = "src/owner_database.csv";
        final String OWNER_DATABASE_CSV_PATH_UPDATE = "src/owner_database_update.csv";
        final String OWNER_DATABASE_CSV_PATH_SAVE = "src/owner_database_save.csv";

        // update system: owner database
        OwnerDatabase ownerDatabase = OwnerDatabase.create(OWNER_DATABASE_CSV_PATH);
        log.info(ownerDatabase.toString());
        ownerDatabase.update(OWNER_DATABASE_CSV_PATH_UPDATE);
        log.info(ownerDatabase.toString());
        log.info(ownerDatabase.getOwner("AB-CD-123"));
        ownerDatabase.remove("HN-AB-1234");
        ownerDatabase.saveDatabaseToCSV(OWNER_DATABASE_CSV_PATH_SAVE);

        // clear database for better testing
        while(ownerDatabase.getOwnerDatabase().size() > 3)  {
            ownerDatabase.remove(ownerDatabase.getOwnerDatabase().size() - 1);
        }

        // create person and vehicle
        Person person1 = Person.builder()
                .name("Dennis")
                .dateOfBirth(generateRandomLocalDate(1990, 2000))
                .build();

        Vehicle car1 = Car.builder()
                .vehicleType(VehicleType.CAR)
                .maxSpeed(200)
                .type(CarType.COUPE)
                .build();

        // buy and register vehicle
        person1.buyVehicle(car1);
        ownerDatabase.registerVehicle(car1, "HN-DE-1996");

        // create person and vehicle
        Person person2 = Person.builder()
                .name("Joachim")
                .dateOfBirth(generateRandomLocalDate(1970, 2000))
                .build();

        Vehicle car2 = Car.builder()
                .vehicleType(VehicleType.CAR)
                .maxSpeed(180)
                .type(CarType.SUV)
                .build();

        // buy and register vehicle
        person2.buyVehicle(car2);
        ownerDatabase.registerVehicle(car2);

        // deregister and sell vehicle
        ownerDatabase.deregisterVehicle(car2);
        person2.sellVehicle(car2);


        FixedSpeedCamera camera1 = FixedSpeedCamera.builder()
                .location("Heilbronn")
                .build();

        LocalDate dob2 = LocalDate.parse("1970-05-11", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Technician technician1 = Technician.builder()
                .name("Johann")
                .dateOfBirth(dob2)
                .build();
        technician1.addCamera(camera1);

        //log.info(ownerDatabase.getOwner(car1.getLicensePlate()));


    }

    public static LocalDate generateRandomLocalDate(int minYear, int maxYear) {
        Random rand = new Random();

        int year = rand.nextInt(maxYear - minYear + 1) + minYear; // Random year
        int month = rand.nextInt(12) + 1; // 1-12

        // Days in month (February always 28 days)
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int day = rand.nextInt(daysInMonth[month - 1]) + 1;

        // Format as YYYY-MM-DD
        return LocalDate.parse(String.format("%04d-%02d-%02d", year, month, day));
    }
}


