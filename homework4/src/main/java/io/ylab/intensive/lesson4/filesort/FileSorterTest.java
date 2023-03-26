package io.ylab.intensive.lesson4.filesort;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;

import io.ylab.intensive.lesson4.DbUtil;

public class FileSorterTest {
  public static void main(String[] args) throws SQLException, IOException {
    DataSource dataSource = initDb();

    int count = 1_000_000;
    File data = new Generator().generate("homework4/data.txt", count);
    FileSorter fileSorter = new FileSortImpl(dataSource);

    long startTime = System.currentTimeMillis();
    File sortedFile = fileSorter.sort(data);

    System.out.printf("Took time for sorting %s lines file: about %d sec.%n",
            count, (System.currentTimeMillis() - startTime) / 1000);
    System.out.printf("Is the file sorted in desc order after sorting? - %s %n",
            new Validator(sortedFile).isSortedDesc());
  }
  
  public static DataSource initDb() throws SQLException {
    String createSortTable = "" 
                                 + "drop table if exists numbers;" 
                                 + "CREATE TABLE if not exists numbers (\n"
                                 + "\tval bigint\n"
                                 + ");";
    DataSource dataSource = DbUtil.buildDataSource();
    DbUtil.applyDdl(createSortTable, dataSource);
    return dataSource;
  }
}
