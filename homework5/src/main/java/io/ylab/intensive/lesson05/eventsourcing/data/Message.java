package io.ylab.intensive.lesson05.eventsourcing.data;

import java.io.Serializable;

public class Message implements Serializable {

    private CommandType commandType;
    private Person person;

    public Message(CommandType commandType, Person person) {
        this.commandType = commandType;
        this.person = person;
    }

    public Message() {
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return String.format("Method:%s Person:%s %s %s %s",
                commandType.toString(), person.getId(), person.getName(),
                person.getLastName(), person.getMiddleName());
    }
}
