package io.ylab.intensive.lesson05.eventsourcing.api;


import io.ylab.intensive.lesson05.eventsourcing.data.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;


public class ApiApp {
  public static void main(String[] args) throws Exception {
    // Тут пишем создание PersonApi, запуск и демонстрацию работы
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
    applicationContext.start();
    PersonApi personApi = applicationContext.getBean(PersonApi.class);

    // add new persons
    personApi.savePerson(1L, "TestPerson Name",
            "TestPerson LastName", "TestPerson MiddleName");
    personApi.savePerson(2L, "FirstName2",
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
}
