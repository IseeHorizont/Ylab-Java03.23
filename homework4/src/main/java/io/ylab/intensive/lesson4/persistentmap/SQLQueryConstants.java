package io.ylab.intensive.lesson4.persistentmap;

public class SQLQueryConstants {

    public static final String findKeyQuery
            = "select KEY from persistent_map where map_name=? and KEY=?;";

    public static final String insertNewPairQuery
            = "insert into persistent_map (map_name, KEY, value) values (?, ?, ?);";

    public static final String findAllKeysQuery
            = "select KEY from persistent_map where map_name=?";

    public static final String findValueByKeyQuery
            = "select value from persistent_map where map_name=? and KEY=?;";

    public static final String deleteByKeyQuery
            = "delete from persistent_map where map_name=? and KEY=?;";

    public static final String deleteAllQuery
            = "delete from persistent_map where map_name=?;";
}
