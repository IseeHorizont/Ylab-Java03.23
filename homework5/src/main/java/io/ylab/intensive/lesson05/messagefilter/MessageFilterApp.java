package io.ylab.intensive.lesson05.messagefilter;

import io.ylab.intensive.lesson05.messagefilter.api.SendMessageApi;
import io.ylab.intensive.lesson05.messagefilter.client.ClientMessageScheduler;
import io.ylab.intensive.lesson05.messagefilter.db.DBInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.stream.Stream;

public class MessageFilterApp {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
    applicationContext.start();

    DBInitializer postgresInitializer = applicationContext.getBean(DBInitializer.class);
    postgresInitializer.initDB();

    // Daemon for receiving censored message
    Thread clientThread = new Thread(() -> {
              ClientMessageScheduler client = applicationContext.getBean(ClientMessageScheduler.class);
              client.getMessage();
    });
    clientThread.setDaemon(true);
    clientThread.start();

    SendMessageApi sendMessageApi = applicationContext.getBean(SendMessageApi.class);
    uncensoredMessageProvider().forEach(
            message -> sendMessageApi.sendMessage(message)
    );

    MessageScheduler messageScheduler = applicationContext.getBean(MessageScheduler.class);
    messageScheduler.startProcessing();

  }

  // test data
  public static Stream<String> uncensoredMessageProvider() {
    return Stream.of(
            "Fuck you, fucking уважаемый!",
            "Why are such пиздобол?",
            "Don't say хуета or хуетень!",
            "Word - чмырь;",
            "Who is урюк? I don't understand you...",
            "What is срака или сраку?",
            "Words like сволочь, сговнять, секель, серун are not allowed",
            "Be careful! He is a придурок."
    );
  }
}
