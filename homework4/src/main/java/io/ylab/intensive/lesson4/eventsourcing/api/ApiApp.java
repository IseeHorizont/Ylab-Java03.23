package io.ylab.intensive.lesson4.eventsourcing.api;

import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson4.DbUtil;
import io.ylab.intensive.lesson4.RabbitMQUtil;
import io.ylab.intensive.lesson4.eventsourcing.data.Person;

import javax.sql.DataSource;
import java.util.List;


public class ApiApp {

  public static void main(String[] args) throws Exception {
    DataSource dataSource = DbUtil.buildDataSource();
    ConnectionFactory connectionFactory = initMQ();

    PersonApiImpl personApi = new PersonApiImpl(dataSource, connectionFactory);

    // add new persons
    personApi.savePerson(1L, "TestPerson Name",
            "TestPerson LastName", "TestPerson MiddleName");
    personApi.savePerson(2L, "Name2",
            "LastName2", "MiddleName2");
    personApi.savePerson(3L, "Name3",
            "LastName3", "MiddleName3");
    // testing update person
    personApi.savePerson(1L, "Updated Name",
            "Updated LastName", "Updated MiddleName");

    // id exist
    personApi.deletePerson(2L);
    // id not exist
    personApi.deletePerson(13L);

    // getting id that exist
    Person foundPerson = personApi.findPerson(1L);
    System.out.printf("Get person by id#%s: %s %n",
            1, foundPerson == null ? "not found" : foundPerson);

    // getting id that not exist
    foundPerson = personApi.findPerson(2L);
    System.out.printf("Get person by id#%s: %s %n",
            2, foundPerson == null ? "not found" : foundPerson);


    // getting all persons
    List<Person> persons = personApi.findAll();
    System.out.println("List of persons:");
    persons.forEach(System.out::println);

  }

  private static ConnectionFactory initMQ() throws Exception {
    return RabbitMQUtil.buildConnectionFactory();
  }
}
