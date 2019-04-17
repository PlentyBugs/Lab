package Lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBC {
    public static void main(String[] args) throws SQLException {

        final String user = "zukoiuh";
        final String password = "14920356691";
        final String url = "jdbc:postgresql://localhost:5432/testdb";

        final Connection connection = DriverManager.getConnection(url, user, password);

    }
}
