package io.ylab.intensive.lesson05.messagefilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageScheduler {

    private final MessageCensor messageCensor;

    @Autowired
    public MessageScheduler(MessageCensor messageCensor) {
        this.messageCensor = messageCensor;
    }

    public void startProcessing() {
        while (!Thread.currentThread().isInterrupted()) {
            messageCensor.processMessage();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
