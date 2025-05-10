package patika_14_final_project.dao;

import patika_14_final_project.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private final String saveScript = """
            INSERT INTO customer (name,email,password) VALUES (?,?,?)
            """;

    private final String findByIdScript = """
            SELECT * FROM customer WHERE id = ?
            
            """;

    private final String findAllScript = """
            SELECT * FROM customer
            """;

    public void save(Customer customer) {

        String url = "jdbc:postgresql://localhost:5432/patika_store";
        String pgUser = "postgres";
        String pgPassword = "Mustafa1";

        try {
            Connection connection = DriverManager.getConnection(url, pgUser, pgPassword);
            PreparedStatement ps = connection.prepareStatement(saveScript);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Customer findById(long id) {

        String url = "jdbc:postgresql://localhost:5432/patika_store";
        String pgUser = "postgres";
        String pgPassword = "Mustafa1";
        Customer customer = null;

        try {
            Connection connection = DriverManager.getConnection(url, pgUser, pgPassword);

            PreparedStatement ps = connection.prepareStatement(findByIdScript);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                // customer.setPassword(rs.getString("password"));
                //customer.setCreatedDate(rs.getDate("createddate"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public List<Customer> findAll() {

        List<Customer> customerList = new ArrayList<>();

        String url = "jdbc:postgresql://localhost:5432/patika_store";
        String pgUser = "postgres";
        String pgPassword = "Mustafa1";

        try {
            Connection connection = DriverManager.getConnection(url, pgUser, pgPassword);

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(findAllScript);

            while (rs.next()) {

                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                // customer.setPassword(rs.getString("password"));
                //customer.setCreatedDate(rs.getDate("createddate"));
                customerList.add(customer);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
}
