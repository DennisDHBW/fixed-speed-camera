package fixedspeedcamera;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

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

    public void registration(String licensePlate, String[] data) {
        ownerDatabase.merge(licensePlate, data, (a, b) -> b);
    }

    public void deregistration(String licensePlate) {
        ownerDatabase.remove(licensePlate);
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
            for (String[] data : ownerDatabase.values()) {
                writer.write(String.format("%s,%s,%s,%s\n",
                        data[0],
                        data[1],
                        data[2],
                        data[3]));
            }
        }
        log.info("Owner Database saved to {}", csvPath);
    }


}
