package io.ylab.intensive.lesson4.persistentmap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Класс, методы которого надо реализовать 
 */
public class PersistentMapImpl implements PersistentMap {

  private static String currentMapName;

  private DataSource dataSource;

  public PersistentMapImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void init(String name) {
    currentMapName = name;
  }

  @Override
  public boolean containsKey(String key) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement
                 = connection.prepareStatement(SQLQueryConstants.findKeyQuery)
    ) {
        preparedStatement.setString(1, currentMapName);
        preparedStatement.setString(2, key);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
          while (resultSet.next()) {
            if (key.equals(resultSet.getString("key"))) {
              return true;
            }
          }
          return false;
        }
    }
  }

  @Override
  public List<String> getKeys() throws SQLException {
    List<String> keysList = new ArrayList<>();

    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement
                 = connection.prepareStatement(SQLQueryConstants.findAllKeysQuery)
    ) {
      preparedStatement.setString(1, currentMapName);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          keysList.add(resultSet.getString(1));
        }
      }
    }
    return keysList;
  }

  @Override
  public String get(String key) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement
                 = connection.prepareStatement(SQLQueryConstants.findValueByKeyQuery)
    ) {
      preparedStatement.setString(1, currentMapName);
      preparedStatement.setString(2, key);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          return resultSet.getString(1);
        }
      }
    }
    return null;
  }

  @Override
  public void remove(String key) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement
                 = connection.prepareStatement(SQLQueryConstants.deleteByKeyQuery)
    ) {
      preparedStatement.setString(1, currentMapName);
      preparedStatement.setString(2, key);
      preparedStatement.executeUpdate();
    }
  }

  @Override
  public void put(String key, String value) throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement
                 = connection.prepareStatement(SQLQueryConstants.insertNewPairQuery)
    ) {
      preparedStatement.setString(1, currentMapName);
      preparedStatement.setString(2, key);
      preparedStatement.setString(3, value);

      preparedStatement.execute();
    }
  }

  @Override
  public void clear() throws SQLException {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement
                 = connection.prepareStatement(SQLQueryConstants.deleteAllQuery)
    ) {
      preparedStatement.setString(1, currentMapName);
      preparedStatement.executeUpdate();
    }
  }
}
