package io.ylab.intensive.lesson05.messagefilter.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Launcher for getting client's messages
 */
@Component
public class ClientMessageScheduler {

    private final GetMessageApi getMessageApi;

    @Autowired
    public ClientMessageScheduler(GetMessageApi getMessageApi) {
        this.getMessageApi = getMessageApi;
    }

    public void getMessage() {
        while (!Thread.currentThread().isInterrupted()) {
            getMessageApi.getMessage();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
