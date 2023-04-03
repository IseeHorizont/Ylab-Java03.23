package io.ylab.intensive.lesson05.messagefilter.db;

import io.ylab.intensive.lesson05.DbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static io.ylab.intensive.lesson05.messagefilter.constants.SQLQueryConstants.*;

/**
 * Initialize DB: if table not exist creates table,
 * else clears table & rewrites from a file
 */
@Component
public class DBInitializerImpl implements DBInitializer {

    private final DataSource dataSource;

    @Autowired
    public DBInitializerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void initDB() {

        if (!isTableExist(badWordsTableName)) {
            createTable();
        } else {
            clearTable();
        }
        File badWordsFile = new File(getClass().getResource("/badWords.txt").getPath());
        fillBadWordsTableFromFile(badWordsFile);
    }

    private void fillBadWordsTableFromFile(File badWordsFile) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertWordQuery)
        ) {
            int batchSize = 100;
            int lineCount = 0;
            try (Scanner scanner = new Scanner(badWordsFile)) {
                while (scanner.hasNextLine()) {
                    String currentLine = scanner.nextLine();
                    preparedStatement.setString(1, currentLine);
                    preparedStatement.addBatch();
                    lineCount++;

                    if (lineCount == batchSize) {
                        preparedStatement.executeBatch();
                        lineCount = 0;
                    }
                }
            }
            preparedStatement.executeBatch();
        }
        catch (SQLException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void createTable() {
        try {
            DbUtil.applyDdl(createTableDdl, dataSource);
        } catch (SQLException ex) {
            System.out.println("From createTable: " + ex.getMessage());
        }
    }

    private void clearTable() {
        try {
            DbUtil.applyDdl(truncateTableDdl, dataSource);
        } catch (SQLException ex) {
            System.out.println("From createTable: " + ex.getMessage());
        }
    }

    private boolean isTableExist(String tableName) {
        try (ResultSet resultSet = dataSource.getConnection()
                .getMetaData()
                .getTables(null, null, tableName, null)
        ) {
            return resultSet.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
