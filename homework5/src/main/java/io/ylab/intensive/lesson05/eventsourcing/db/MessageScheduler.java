package io.ylab.intensive.lesson05.eventsourcing.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageScheduler {

    private final DataProcessor dataProcessor;

    @Autowired
    public MessageScheduler(DataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    }

    public void startProcessing() {
        while (!Thread.currentThread().isInterrupted()) {
            dataProcessor.processMessage();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
