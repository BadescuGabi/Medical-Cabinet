package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AuditService {
    public static void writeToAudit(String timestamp, String option, String object) throws IOException {
        File file = new File("src/audit.csv");
        if (file.isFile()) {
            if (file.length() <= 1) {
                FileWriter csvWriter = new FileWriter("src/audit.csv", true);
                csvWriter.append("Timestamp,action name,type of object\n");
                csvWriter.close();
            }
            FileWriter csvWriter = new FileWriter("src/audit.csv", true);
            csvWriter.append(timestamp + "," + option + "," + object + "\n");
            csvWriter.close();
        }
    }
}
