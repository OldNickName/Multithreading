package reader;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class CSVFile {
    public void generateCSV(String generationPath, int rowCount) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(generationPath));

        csvWriter.writeNext(new String[]{"Row number", "First random", "Second random", "Third random"});

        for (int i = 0; i <= rowCount; i++) {
            csvWriter.writeNext(new String[]{i + "",
                    ThreadLocalRandom.current().nextInt() + "",
                    ThreadLocalRandom.current().nextInt() + "",
                    ThreadLocalRandom.current().nextInt() + ""});
        }
        csvWriter.close();
        System.out.println("Success creating and filling file");
    }
}
