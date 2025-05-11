package patika_14_final_project.dao;

import patika_14_final_project.model.Payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {
    private static final String saveScript = """
            INSERT INTO payment (order_id,payment_method,amount)
            VALUES (?,?,?)
            """;

    public void save(Payment payment) {

        String url = "jdbc:postgresql://localhost:5432/patika_store";
        String pgUser = "postgres";
        String pgPassword = "Mustafa1";
        try (Connection connection = DriverManager.getConnection(url, pgUser, pgPassword)) {
            PreparedStatement ps = connection.prepareStatement(saveScript);
            ps.setLong(1,payment.getOrder().getId());
            ps.setString(2,payment.getPaymentMethod().name());
            ps.setBigDecimal(3,payment.getAmount());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
