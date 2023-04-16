package io.ylab.intensive.lesson05.sqlquerybuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLQueryBuilderImpl implements SQLQueryBuilder {

    private final DataSource dataSource;

    @Autowired
    public SQLQueryBuilderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String queryForTable(String tableName) throws SQLException {

        List<String> columnNameList = new ArrayList<>();

        try (ResultSet resultSet = dataSource.getConnection()
                .getMetaData()
                .getColumns(null, null, tableName, null)
        ) {
            while (resultSet.next()) {
                String currentColumnName = resultSet.getString("COLUMN_NAME");
                columnNameList.add(currentColumnName);
            }
        }
        if (columnNameList.size() == 0) {
            return null;
        }
        String columnNameChain = String.join(", ", columnNameList);

        return String.format("SELECT %s FROM %s", columnNameChain, tableName);
    }

    @Override
    public List<String> getTables() throws SQLException {

        List<String> tableNameList = new ArrayList<>();

        try (ResultSet resultSet = dataSource.getConnection()
                .getMetaData()
                .getTables(null, null, null, new String[]{"TABLE"})
        ) {
            while (resultSet.next()) {
                String currentTableName = resultSet.getString("TABLE_NAME");
                tableNameList.add(currentTableName);
            }
        }
        return tableNameList;
    }
}
