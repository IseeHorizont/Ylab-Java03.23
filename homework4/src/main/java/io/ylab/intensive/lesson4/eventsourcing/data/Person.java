package io.ylab.intensive.lesson4.eventsourcing.data;

import java.io.Serializable;

public class Person implements Serializable {
  private Long id;
  private String name;
  private String lastName;
  private String middleName;

  public Person() {
  }

  public Person(Long id, String name, String lastName, String middleName) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.middleName = middleName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  @Override
  public String toString() {
    return String.format("id=%s, name=%s, lastName=%s, middleName=%s",
            id, name, lastName, middleName);
  }
}
