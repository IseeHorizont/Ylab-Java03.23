package io.ylab.intensive.lesson05.messagefilter;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;
import static io.ylab.intensive.lesson05.messagefilter.constants.RabbitConstants.*;
import static io.ylab.intensive.lesson05.messagefilter.constants.RabbitConstants.exchangeName;
import static io.ylab.intensive.lesson05.messagefilter.constants.SQLQueryConstants.findBadWordIgnoreCase;

@Component
public class MessageCensorImpl implements MessageCensor {

    private final DataSource dataSource;
    private final ConnectionFactory connectionFactory;

    @Autowired
    public MessageCensorImpl(DataSource dataSource, ConnectionFactory connectionFactory) {
        this.dataSource = dataSource;
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void processMessage() {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()
        ) {
            GetResponse response = channel.basicGet(inputQueueName, true);
            if (response != null) {
                String newMessage = new String(response.getBody());

                String censoredMessage = censorMessage(newMessage);
                System.out.println("Censored message: " + censoredMessage);

                // translate to output queue
                channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
                channel.queueDeclare(outputQueueName, true, false, false, null);
                channel.queueBind(outputQueueName, exchangeName, routingKeyOutput);
                channel.basicPublish(exchangeName, routingKeyOutput, null, censoredMessage.getBytes());
            }
        }
        catch (IOException | TimeoutException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String censorMessage(String message) {
        String censoredMessage = message;
        String[] messageByWords = message.split("[ .,;?!\n]+");
        for (String word : messageByWords) {
            if (isThisABadWord(word)) {
                censoredMessage = censoredMessage.replace(
                        word,
                        word.charAt(0) + "*".repeat(word.length() - 2) + word.charAt(word.length() - 1)
                );
            }
        }
        return censoredMessage;
    }

    private boolean isThisABadWord(String word) {
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(findBadWordIgnoreCase)
        ) {
            preparedStatement.setString(1, word);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (word.equalsIgnoreCase(resultSet.getString("word"))) {
                        return true;
                    }
                }
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
