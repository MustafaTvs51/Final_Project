package patika_14_final_project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public DBUtil() {
    }

    private static String URL = "jdbc:postgresql://localhost:5432/patika_store";
    private static String PGUSER = "postgres";
    private static String PGPASSWORD = "Mustafa1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,PGUSER, PGPASSWORD);
    }
}
