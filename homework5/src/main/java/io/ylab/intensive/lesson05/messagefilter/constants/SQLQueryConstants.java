package io.ylab.intensive.lesson05.messagefilter.constants;

public class SQLQueryConstants {

    public static final String createTableDdl = ""
            + "CREATE TABLE bad_words (\n"
            + "word varchar\n"
            + ");";

    public static final String truncateTableDdl = "truncate table bad_words;";

    public static final String badWordsTableName = "bad_words";

    public static final String findBadWordIgnoreCase
            = "select word from bad_words where lower(word)=lower(?)";

    public static final String insertWordQuery
            = "insert into bad_words (word) values (?);";
}
