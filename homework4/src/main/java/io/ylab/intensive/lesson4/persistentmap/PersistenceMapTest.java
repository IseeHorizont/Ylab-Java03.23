package io.ylab.intensive.lesson4.persistentmap;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import io.ylab.intensive.lesson4.DbUtil;

public class PersistenceMapTest {
  public static void main(String[] args) throws SQLException {
    DataSource dataSource = initDb();
    PersistentMap persistentMap = new PersistentMapImpl(dataSource);

    String testMapName = "test map name";
    persistentMap.init(testMapName);

    String testKeyFirst = "test key 1";
    String testValueFirst = "test value 1";
    String testKeySecond = "test key 2";
    String testValueSecond = "test value 2";


    System.out.printf("%nContains key '%s': %s%n",
            "wrong key", persistentMap.containsKey("wrong key"));

    // put test pair
    persistentMap.put(testKeyFirst, testValueFirst);
    persistentMap.put(testKeySecond, testValueSecond);

    // is there contains test key in map?
    System.out.printf("%nContains key '%s': %s%n",
            testKeyFirst, persistentMap.containsKey(testKeyFirst));

    // getting all keys
    List<String> keysList = persistentMap.getKeys();
    System.out.printf("%nList of keys from '%s':", testMapName);
    keysList.forEach(
            item -> System.out.printf("key = '%s'%n", item)
    );

    // getting value by key
    String foundValueByKey = persistentMap.get(testKeySecond);
    System.out.printf("%nValue by key '%s': '%s'%n",
            testKeySecond, foundValueByKey != null ? foundValueByKey : "not found");

    // remove by key
    persistentMap.remove(testKeyFirst);
    System.out.printf("%nAfter deleting by key '%s':%n", testKeyFirst);
    System.out.printf("Contains key '%s': %s%n",
            testKeyFirst, persistentMap.containsKey(testKeyFirst));
    foundValueByKey = persistentMap.get(testKeyFirst);
    System.out.printf("Value by key '%s': '%s'%n",
            testKeyFirst, foundValueByKey != null ? foundValueByKey : "not found");

    // clear
    persistentMap.clear();
    keysList = persistentMap.getKeys();
    System.out.printf("%nList of keys after clear: %s%n", keysList.isEmpty() ? "empty" : keysList);
  }
  
  public static DataSource initDb() throws SQLException {
    String createMapTable = "" 
                                + "drop table if exists persistent_map; " 
                                + "CREATE TABLE if not exists persistent_map (\n"
                                + "   map_name varchar,\n"
                                + "   KEY varchar,\n"
                                + "   value varchar\n"
                                + ");";
    DataSource dataSource = DbUtil.buildDataSource();
    DbUtil.applyDdl(createMapTable, dataSource);
    return dataSource;
  }
}
