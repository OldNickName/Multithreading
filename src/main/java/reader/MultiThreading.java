package reader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreading {

    private int threadCount;

    public MultiThreading() {
    }

    public MultiThreading(int threadCount) {
        this.threadCount = threadCount;
    }

    public void executorReading(Reader reader) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
            long start = System.nanoTime();
            executorService.submit(reader);
    }
}
