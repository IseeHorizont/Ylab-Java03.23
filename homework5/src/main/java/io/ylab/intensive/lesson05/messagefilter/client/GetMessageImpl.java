package io.ylab.intensive.lesson05.messagefilter.client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import static io.ylab.intensive.lesson05.messagefilter.constants.RabbitConstants.outputQueueName;


/**
 * Implementation getting message by client
 */
@Component
public class GetMessageImpl implements GetMessageApi{

    private final ConnectionFactory connectionFactory;

    @Autowired
    public GetMessageImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void getMessage() {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()
        ) {
            GetResponse response = channel.basicGet(outputQueueName, true);
            if (response != null) {
                String newMessage = new String(response.getBody());
                System.out.println("Client got a new message: " + newMessage);
            }
        }
        catch (IOException | TimeoutException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
