package fixedspeedcamera;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import vehicle.Vehicle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

@Data
@Slf4j
public class OwnerDatabase {
    private final HashMap<String, String[]> ownerDatabase;

    public OwnerDatabase(HashMap<String, String[]> ownerDatabase) {
        this.ownerDatabase = ownerDatabase;
    }

    public static OwnerDatabase create(String csvPath) {
        HashMap<String, String[]> ownerDatabase = new HashMap<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(csvPath))) {
            bufferedReader.lines().skip(1).forEach(line -> {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    ownerDatabase.put(data[0], new String[]{data[1], data[2], data[3]});
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new OwnerDatabase(ownerDatabase);
    }

    public void update(String csvPath) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(csvPath))) {
            bufferedReader.lines().skip(1).forEach(line -> {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    ownerDatabase.merge(
                            data[0],
                            new String[]{data[1], data[2], data[3]},
                            (a, b) -> b);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getOwner(String licensePlate) {
        for (HashMap.Entry<String, String[]> entry : ownerDatabase.entrySet()) {
            if(entry.getKey().equals(licensePlate)) {
                return entry.getValue()[0];
            }
        }
        return null;
    }

    public void registerVehicle(Vehicle vehicle, String licensePlate) {
        String today = LocalDate.now().toString();
        String owner = vehicle.getOwner().getName();
        String[] data = new String[] { owner, today, "9999-12-31"};

        if(vehicle.isRegistrated()) {
            log.warn("Vehicle is already registered");
            return;
        }

        if (vehicle.getOwner() == null) {
            log.warn("Vehicle owner is null");
            return;
        }

        if (!ownerDatabase.containsKey(licensePlate)) {
            ownerDatabase.merge(licensePlate, data, (a, b) -> b);
            vehicle.setLicensePlate(licensePlate);
            vehicle.setRegistrated(true);
        } else {
            log.warn("Vehicle already registered with license plate: " + licensePlate);
        }
    }

    public void registerVehicle(Vehicle vehicle) {
        String licensePlate;
        do {
            licensePlate = generateLicensePlate();
        } while (ownerDatabase.containsKey(licensePlate));
        registerVehicle(vehicle, licensePlate);
    }

    public void deregisterVehicle(Vehicle vehicle) {

        String[] data = ownerDatabase.get(vehicle.getLicensePlate());
        String licensePlate = vehicle.getLicensePlate();

        if (!ownerDatabase.containsKey(licensePlate)) {
            log.warn("Vehicle is not in the database.");
            return;
        }

        if (!vehicle.isRegistrated()) { // alternative: LocalDate.now().isAfter(LocalDate.parse(data[2]))
            log.warn("Vehicle is already deregistrated.");
            return;
        }

        vehicle.setRegistrated(false);
        vehicle.setLicensePlate(null);
        data = new String[] { vehicle.getOwner().getName(), data[1] , LocalDate.now().toString()};
        ownerDatabase.merge(licensePlate, data, (a, b) -> b);

    }

    public void remove(String licensePlate) {
        if (!ownerDatabase.containsKey(licensePlate)) {
            log.warn("Vehicle is not in the database.");
            return;
        }
        ownerDatabase.remove(licensePlate);
        log.info("Removed license plate {} from Database", licensePlate);
    }

    public void remove(int entryIndex) {
        remove((String) ownerDatabase.keySet().toArray()[entryIndex]);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("\nlicensePlate\t\t\tname\t\t\t\tregistrationDate\t\tderegistrationDate\n");
        int i = 0;
        for (HashMap.Entry<String, String[]> entry : ownerDatabase.entrySet()) {
            i++;
            output
                .append(entry.getKey())
                .append("\t\t\t\t")
                .append(entry.getValue()[0])
                .append("\t\t\t\t")
                .append(entry.getValue()[1])
                .append("\t\t\t\t")
                .append(entry.getValue()[2]);
            if (i != ownerDatabase.size()) {
                output.append("\n");
            }

        }
        return output.toString();
    }

    @SneakyThrows
    public void saveDatabaseToCSV(String csvPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvPath))) {
            writer.write("licensePlate,name,registrationDate,deregistrationDate\n");
            for(HashMap.Entry<String, String[]> entry : ownerDatabase.entrySet()) {
                writer.write(String.format("%s,%s,%s,%s\n",
                        entry.getKey(),
                        entry.getValue()[0],
                        entry.getValue()[1],
                        entry.getValue()[2]));

            }
        }
        log.info("Owner Database saved to {}", csvPath);
    }

    private static String generateLicensePlate() {
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();

        int len1 = rand.nextInt(2) + 1; // 1 or 2
        StringBuilder part1 = new StringBuilder();
        for (int i = 0; i < len1; i++) {
            part1.append(LETTERS.charAt(rand.nextInt(LETTERS.length())));
        }

        int len2 = rand.nextInt(2) + 1; // 1 or 2
        StringBuilder part2 = new StringBuilder();
        for (int i = 0; i < len2; i++) {
            part2.append(LETTERS.charAt(rand.nextInt(LETTERS.length())));
        }

        int len3 = rand.nextInt(4) + 1; // 1 to 4
        StringBuilder part3 = new StringBuilder();
        part3.append(rand.nextInt(9) + 1); // First digit: 1-9
        for (int i = 1; i < len3; i++) {
            part3.append(rand.nextInt(10)); // Next digits: 0-9
        }
        return part1 + "-" + part2 + "-" + part3;
    }

}
