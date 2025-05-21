package patika_14_final_project.dao;

import patika_14_final_project.dao.Constants.SqlScriptConstants;
import patika_14_final_project.model.Payment;
import patika_14_final_project.util.DBUtil;

import java.sql.*;
import java.util.List;

public class PaymentDAO implements BaseDAO<Payment>{

    public long save(Payment payment) {

        long id = 0;

        try (Connection connection = DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PAYMENT_SAVE);
            ps.setLong(1,payment.getOrder().getId());
            ps.setString(2,payment.getPaymentMethod().name());
            ps.setBigDecimal(3,payment.getAmount());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                id= rs.getLong(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Payment findById(long id) {
        return null;
    }

    @Override
    public List<Payment> findAll(int page) {
        return List.of();
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public void delete(long id) {

    }
}
