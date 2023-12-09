package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDBSetup {
    private static final String URL = "jdbc:sqlite:child_care_system.db";

    public SQLiteDBSetup() {
        createTables();
    }

    private void createTables() {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {

            String createGroupsTableQuery = "CREATE TABLE IF NOT EXISTS groups (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "group_name TEXT," +
                    "group_number INTEGER)";

            String createChildrenTableQuery = "CREATE TABLE IF NOT EXISTS children (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "last_name TEXT," +
                    "age INTEGER," +
                    "gender TEXT)";

            String createGroupChildTableQuery = "CREATE TABLE IF NOT EXISTS group_children (" +
                    "group_id INTEGER," +
                    "child_id INTEGER," +
                    "FOREIGN KEY (group_id) REFERENCES groups(id)," +
                    "FOREIGN KEY (child_id) REFERENCES children(id)," +
                    "PRIMARY KEY (group_id, child_id))";

            statement.executeUpdate(createGroupsTableQuery);
            statement.executeUpdate(createChildrenTableQuery);
            statement.executeUpdate(createGroupChildTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
