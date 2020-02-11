package reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader implements Runnable {
    private String filePath = "";

    private final List<String[]> randomList = new ArrayList<>();

    public Reader() {
    }

    public Reader(String filePath) {
        this.filePath = filePath;
    }

    public boolean fileExist(String filePath) {
        File file = new File(filePath);
        System.out.println(file.isFile());
        return file.isFile();
    }

    public List<String[]> getRandomList() {
        return randomList;
    }

    @Override
    public void run() {
        if (fileExist(filePath)) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    String[] modData = line.split(",");
                    randomList.add(modData);
                }
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("IOException");
            }
        }
    }
}
