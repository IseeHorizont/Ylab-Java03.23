package io.ylab.intensive.lesson05.eventsourcing.constants;

public class SQLQueryConstants {

    public static final String insertPersonQuery
            = "insert into person (person_id, first_name, last_name, middle_name) values (?, ?, ?, ?);";

    public static final String findPersonByIdQuery
            = "select * from person where person_id=?";

    public static final String updatePersonQuery
            = "update person set first_name=?, last_name=?, middle_name=? where person_id=?";

    public static final String deletePersonQuery
            = "delete from person where person_id=?";

    public static final String findAllPersonsQuery
            = "select * from person";
}
