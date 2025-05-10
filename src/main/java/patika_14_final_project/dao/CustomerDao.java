package patika_14_final_project.dao;

import patika_14_final_project.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDao {

    private final String saveScript = """
            INSERT INTO customer (name,email,password) VALUES (?,?,?)
            """;


    public void save(Customer customer) {

        String url = "jdbc:postgresql://localhost:5432/patika_store";
        String pgUser = "postgres";
        String pgPassword = "Mustafa1";

        try {
            Connection connection = DriverManager.getConnection(url, pgUser,pgPassword);
            PreparedStatement ps = connection.prepareStatement(saveScript);
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getEmail());
            ps.setString(3,customer.getPassword());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
