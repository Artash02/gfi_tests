package utils;


import net.minidev.json.JSONObject;
import java.sql.*;


public class DbUtils {

    private Statement statement;

    public DbUtils() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://ARTASHESVM;databaseName=gfidb", "sa", "f6uA!cTkF2DM");
        this.statement = connection.createStatement();
    }

    public JSONObject GetSqlRowData(String zcRequestId) throws SQLException {
        JSONObject jsonObject = new JSONObject();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM [gfidb5].[dbo].[GfiUpdateRequests] WHERE CAST(AddRequestId AS varchar(48)) =" + "'" + zcRequestId + "'");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                System.out.println("11111111111");
                int numColumns = resultSetMetaData.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    String columnName = resultSetMetaData.getColumnName(i);
                    if (resultSetMetaData.getColumnType(i) == Types.ARRAY) {
                        jsonObject.put(columnName, resultSet.getArray(i));
                    } else if (resultSetMetaData.getColumnType(i) == Types.BIGINT) {
                        jsonObject.put(columnName, resultSet.getInt(i));
                    } else if (resultSetMetaData.getColumnType(i) == Types.BOOLEAN) {
                        jsonObject.put(columnName, resultSet.getBoolean(i));
                    } else if (resultSetMetaData.getColumnType(i) == Types.BLOB) {
                        jsonObject.put(columnName, resultSet.getBlob(i));
                    } else if (resultSetMetaData.getColumnType(i) == Types.DOUBLE) {
                        jsonObject.put(columnName, resultSet.getDouble(i));
                    } else if (resultSetMetaData.getColumnType(i) == Types.FLOAT) {
                        jsonObject.put(columnName, resultSet.getFloat(i));
                    } else if (resultSetMetaData.getColumnType(i) == Types.INTEGER) {
                        jsonObject.put(columnName, resultSet.getInt(i));
                    } else if (resultSetMetaData.getColumnType(i) == Types.NVARCHAR) {
                        jsonObject.put(columnName, resultSet.getNString(i));
                    } else if (resultSetMetaData.getColumnType(i) == Types.VARCHAR) {
                        jsonObject.put(columnName, resultSet.getString(i));
                    } else if (resultSetMetaData.getColumnType(i) == Types.SMALLINT) {
                        jsonObject.put(columnName, resultSet.getInt(i));
                    } else if (resultSetMetaData.getColumnType(i) == Types.DATE) {
                        jsonObject.put(columnName, resultSet.getDate(i));
                    }  else if (resultSetMetaData.getColumnType(i) == Types.REF) {
                        jsonObject.put(columnName, resultSet.getRef(i));
                    } else {
                        jsonObject.put(columnName, resultSet.getObject(i));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    public String toUpperCase(String value) {
        return value.toUpperCase();
    }

}

