package io.ylab.intensive.lesson05.eventsourcing.api;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson05.eventsourcing.constants.SQLQueryConstants;
import io.ylab.intensive.lesson05.eventsourcing.data.CommandType;
import io.ylab.intensive.lesson05.eventsourcing.data.Message;
import io.ylab.intensive.lesson05.eventsourcing.data.Person;
import io.ylab.intensive.lesson05.eventsourcing.utils.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static io.ylab.intensive.lesson05.eventsourcing.constants.RabbitConstants.*;

@Component
public class PersonApiImpl implements PersonApi {

    private final DataSource dataSource;
    private final ConnectionFactory connectionFactory;

    @Autowired
    public PersonApiImpl(DataSource dataSource, ConnectionFactory connectionFactory) {
        this.dataSource = dataSource;
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void deletePerson(Long personId) {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()
        ) {
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routingKey);

            Message message = new Message();
            message.setCommandType(CommandType.DELETE);
            Person personForDelete = new Person();
            personForDelete.setId(personId);
            message.setPerson(personForDelete);

            channel.basicPublish(exchangeName, routingKey, null, MessageMapper.getBytes(message));
        }
        catch (IOException | TimeoutException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void savePerson(Long personId, String firstName, String lastName, String middleName) {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()
        ) {
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routingKey);

            Message message = new Message(
                    CommandType.SAVE,
                    new Person(personId, firstName, lastName, middleName)
            );
            channel.basicPublish(exchangeName, routingKey, null, MessageMapper.getBytes(message));
        }
        catch (IOException | TimeoutException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Person findPerson(Long personId) {
        Person foundPerson = null;
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(SQLQueryConstants.findPersonByIdQuery)
        ) {
            preparedStatement.setLong(1, personId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (personId == resultSet.getLong("person_id")) {
                        foundPerson = new Person();
                        foundPerson.setId(resultSet.getLong(1));
                        foundPerson.setName(resultSet.getString(2));
                        foundPerson.setLastName(resultSet.getString(3));
                        foundPerson.setMiddleName(resultSet.getString(4));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return foundPerson;
    }

    @Override
    public List<Person> findAll() {
        List<Person> personList = new ArrayList<>();

        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(SQLQueryConstants.findAllPersonsQuery);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong(1));
                person.setName(resultSet.getString(2));
                person.setLastName(resultSet.getString(3));
                person.setMiddleName(resultSet.getString(4));
                personList.add(person);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personList;
    }
}
