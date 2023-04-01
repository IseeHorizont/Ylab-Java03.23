package io.ylab.intensive.lesson05.eventsourcing.db;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import io.ylab.intensive.lesson05.eventsourcing.data.Message;
import io.ylab.intensive.lesson05.eventsourcing.data.Person;
import io.ylab.intensive.lesson05.eventsourcing.constants.SQLQueryConstants;
import io.ylab.intensive.lesson05.eventsourcing.utils.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import static io.ylab.intensive.lesson05.eventsourcing.constants.RabbitConstants.queueName;

@Component
public class DataProcessorImpl implements DataProcessor {

    private final DataSource dataSource;
    private final ConnectionFactory connectionFactory;

    @Autowired
    public DataProcessorImpl(DataSource dataSource, ConnectionFactory connectionFactory) {
        this.dataSource = dataSource;
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void processMessage() {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()
        ) {
            GetResponse response = channel.basicGet(queueName, true);
            if (response != null) {
                Message newMessage = MessageMapper.toObject(response.getBody());
                System.out.println("newMessage: " + newMessage);
                commandFilter(newMessage);
            }
        }
        catch (IOException | TimeoutException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void commandFilter(Message newMessage) {
        switch (newMessage.getCommandType()) {
            case SAVE:
                if (isExistPersonById(newMessage.getPerson().getId())) {
                    updatePersonInDB(newMessage.getPerson());
                } else {
                    savePersonInDB(newMessage.getPerson());
                }
                break;
            case DELETE:
                if (isExistPersonById(newMessage.getPerson().getId())) {
                    deletePersonFromDB(newMessage.getPerson().getId());
                } else {
                    System.out.printf("Error: person with id#%s not found & not be remove %n",
                            newMessage.getPerson().getId());
                }
                break;
            default:
                System.out.println("WRONG COMMAND!");
        }
    }

    private boolean isExistPersonById(Long id) {
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(SQLQueryConstants.findPersonByIdQuery)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (id == resultSet.getLong("person_id")) {
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

    private void savePersonInDB(Person person) {
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(SQLQueryConstants.insertPersonQuery)
        ) {
            preparedStatement.setLong(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setString(4, person.getMiddleName());

            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void updatePersonInDB(Person person) {
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(SQLQueryConstants.updatePersonQuery)
        ) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getMiddleName());
            preparedStatement.setLong(4, person.getId());

            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void deletePersonFromDB(Long id) {
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(SQLQueryConstants.deletePersonQuery)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
