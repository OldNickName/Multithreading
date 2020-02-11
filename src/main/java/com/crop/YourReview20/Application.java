package com.crop.YourReview20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reader.CSVFile;
import reader.MultiThreadWriter;
import reader.MultiThreading;
import reader.Reader;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException    {
        SpringApplication.run(Application.class, args);

        Scanner in = new Scanner(System.in);
        System.out.print("Input file name: ");
        String fileName = in.next();
        System.out.print("Input row count: ");
        int rowCount = in.nextInt();
        System.out.print("Input a thread count: ");
        int threadCount = in.nextInt();
        System.out.print("Input a batch size:  ");
        int batchSize = in.nextInt();
        in.close();

        CSVFile csvFile = new CSVFile();
        csvFile.generateCSV("D:\\" + fileName + ".csv", rowCount);

        MultiThreading multiThreading = new MultiThreading(threadCount);
        Reader reader = new Reader("D:\\" + fileName + ".csv");
        multiThreading.executorReading(reader);
        long start = System.nanoTime();
        write(threadCount, reader, batchSize, rowCount);
        long finish = System.nanoTime();
        System.out.println("Your time: " + (finish - start) / 1000000 + "ms");
        System.out.println("Everything done!");

    }

    public static void write(int threadCount, Reader reader, int batchSize, int rowCount) {
        if (threadCount <= 0) {
            System.out.println("Error: Invalid Syntax. ");
            System.out.println("NoOfThreads");
            System.exit(0);
        } else if (threadCount >= 1) {
            Thread[] threadList = new Thread[threadCount];
            for (int i = 0; i < threadCount; i++) {
                threadList[i] = new MultiThreadWriter(reader, batchSize, rowCount, threadCount, i);
                threadList[i].start();
            }
            for (int i = 0; i < threadCount; i++) {
                try {
                    threadList[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
