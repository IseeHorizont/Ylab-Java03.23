package io.ylab.intensive.lesson4.movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
import javax.sql.DataSource;

public class MovieLoaderImpl implements MovieLoader {

  private static final String insertMovieQuery
          = "insert into movie (year, length, title, subject, actors, actress, director, popularity, awards) " +
                       "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

  private DataSource dataSource;

  public MovieLoaderImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void loadData(File file) {
    try(Scanner scanner = new Scanner(file)) {
      scanner.nextLine(); // skip column names line
      scanner.nextLine(); // skip datatype line
      while (scanner.hasNextLine()) {
        String[] currentReadLine = scanner.nextLine().split(";");
        Movie currentMovie = new Movie();
        currentMovie.setYear(currentReadLine[0].isEmpty() ? null : Integer.parseInt(currentReadLine[0]));
        currentMovie.setLength(currentReadLine[1].isEmpty() ? null : Integer.parseInt(currentReadLine[1]));
        currentMovie.setTitle(currentReadLine[2].isEmpty() ? null : currentReadLine[2]);
        currentMovie.setSubject(currentReadLine[3].isEmpty() ? null : currentReadLine[3]);
        currentMovie.setActors(currentReadLine[4].isEmpty() ? null : currentReadLine[4]);
        currentMovie.setActress(currentReadLine[5].isEmpty() ? null : currentReadLine[5]);
        currentMovie.setDirector(currentReadLine[6].isEmpty() ? null : currentReadLine[6]);
        currentMovie.setPopularity(currentReadLine[7].isEmpty() ? null : Integer.parseInt(currentReadLine[7]));
        currentMovie.setAwards("Yes".equals(currentReadLine[8]));

        writeMovieInDB(currentMovie);
      }
    } catch (FileNotFoundException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public void writeMovieInDB(Movie movie) {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(insertMovieQuery)
    ) {
      if (movie.getYear() != null) {
        preparedStatement.setInt(1, movie.getYear());
      } else {
        preparedStatement.setNull(1, Types.INTEGER);
      }
      if (movie.getLength() != null) {
        preparedStatement.setInt(2, movie.getLength());
      } else {
        preparedStatement.setNull(2, Types.INTEGER);
      }
      if (movie.getTitle() != null) {
        preparedStatement.setString(3, movie.getTitle());
      } else {
        preparedStatement.setNull(3, Types.VARCHAR);
      }
      if (movie.getSubject() != null) {
        preparedStatement.setString(4, movie.getSubject());
      } else {
        preparedStatement.setNull(4, Types.VARCHAR);
      }
      if (movie.getActors() != null) {
        preparedStatement.setString(5, movie.getActors());
      } else {
        preparedStatement.setNull(5, Types.VARCHAR);
      }
      if (movie.getActress() != null) {
        preparedStatement.setString(6, movie.getActress());
      } else {
        preparedStatement.setNull(6, Types.VARCHAR);
      }
      if (movie.getDirector() != null) {
        preparedStatement.setString(7, movie.getDirector());
      } else {
        preparedStatement.setNull(7, Types.VARCHAR);
      }
      if (movie.getPopularity() != null) {
        preparedStatement.setInt(8, movie.getPopularity());
      } else {
        preparedStatement.setNull(8, Types.INTEGER);
      }
      preparedStatement.setBoolean(9, movie.getAwards());
      preparedStatement.execute();
    }
    catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
