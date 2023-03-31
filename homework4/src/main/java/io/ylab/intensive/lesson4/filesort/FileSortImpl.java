package io.ylab.intensive.lesson4.filesort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.sql.DataSource;

public class FileSortImpl implements FileSorter {

  public static final String insertValueQuery
          = "insert into numbers (val) values (?);";

  public static final String selectSortedDataQuery
          = "select * from numbers order by val desc;";

  public final String sortedFileName = "homework4/sortedFile.txt";

  private DataSource dataSource;

  public FileSortImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public File sort(File data) {
    // ТУТ ПИШЕМ РЕАЛИЗАЦИЮ
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(insertValueQuery)
    ) {
      int batchSize = 1000;
      int lineCount = 0;
      try (Scanner scanner = new Scanner(data)) {
        while (scanner.hasNextLong()) {
          long currentLine = scanner.nextLong();
          preparedStatement.setLong(1, currentLine);
          preparedStatement.addBatch();
          lineCount++;

          // batch size restricted by 1000
          if (lineCount == batchSize) {
            preparedStatement.executeBatch();
            lineCount = 0;
          }
        }
      }
      // batch remaining lines
      preparedStatement.executeBatch();
    }
    catch (SQLException | FileNotFoundException ex) {
      System.out.println(ex.getMessage());
    }

    // select all from DB & write result file
    selectAllValueFromDBAndWriteInFile();

    return new File(sortedFileName);
  }

  public void selectAllValueFromDBAndWriteInFile() {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(selectSortedDataQuery);
         ResultSet resultSet = preparedStatement.executeQuery()
    ) {
      try (FileWriter fileWriter = new FileWriter(sortedFileName)) {
        while (resultSet.next()) {
          fileWriter.write(resultSet.getString(1) + "\n");
        }
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
