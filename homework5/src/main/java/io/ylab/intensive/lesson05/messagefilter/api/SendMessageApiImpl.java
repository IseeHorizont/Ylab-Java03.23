package io.ylab.intensive.lesson05.messagefilter.api;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static io.ylab.intensive.lesson05.messagefilter.constants.RabbitConstants.*;

/**
 * Implementation sending message for censoring
 */
@Component
public class SendMessageApiImpl implements SendMessageApi{

    private final ConnectionFactory connectionFactory;

    @Autowired
    public SendMessageApiImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void sendMessage(String message) {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()
        ) {
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(inputQueueName, true, false, false, null);
            channel.queueBind(inputQueueName, exchangeName, routingKeyInput);

            channel.basicPublish(exchangeName, routingKeyInput, null, message.getBytes());
        }
        catch (IOException | TimeoutException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
