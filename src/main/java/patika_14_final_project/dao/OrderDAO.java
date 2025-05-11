package patika_14_final_project.dao;

import patika_14_final_project.model.Order;

import java.sql.*;

public class OrderDAO {

    private static final String saveScript = """
            INSERT INTO \"order\" (customer_id,order_date,total_amount)
            VALUES(?,?,?,?,?)
            """;

    public void save(Order order) {
        String url = "jdbc:postgresql://localhost:5432/patika_store";
        String pgUser = "postgres";
        String pgPassword = "Mustafa1";
        try (Connection connection = DriverManager.getConnection(url, pgUser, pgPassword)) {

            PreparedStatement ps = connection.prepareStatement(saveScript);
            ps.setLong(1, order.getCustomer().getId());
            ps.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            ps.setBigDecimal(3, order.getTotalAmount());

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
