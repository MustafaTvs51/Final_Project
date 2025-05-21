package patika_14_final_project.dao;

import patika_14_final_project.dao.Constants.SqlScriptConstants;
import patika_14_final_project.model.Order;
import patika_14_final_project.util.DBUtil;

import java.sql.*;
import java.util.List;

public class OrderDAO implements BaseDAO<Order>{



    public long save(Order order) {
        long generatedId = 0;

        try (Connection connection = DBUtil.getConnection()){

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.ORDER_SAVE,Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, order.getCustomer().getId());
            ps.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            ps.setBigDecimal(3, order.getTotalAmount());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                 generatedId = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    @Override
    public Order findById(long id) {
        return null;
    }

    @Override
    public List<Order> findAll(int page) {
        return List.of();
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(long id) {

    }
}
