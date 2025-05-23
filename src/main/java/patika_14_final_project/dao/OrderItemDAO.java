package patika_14_final_project.dao;

import patika_14_final_project.dao.Constants.SqlScriptConstants;
import patika_14_final_project.model.OrderItems;
import patika_14_final_project.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDAO implements BaseDAO<OrderItems> {


    public void saveAll(List<OrderItems> orderItems) {

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.ORDER_ITEMS_SAVE)) {

            for (OrderItems orderItems2 : orderItems) {
                ps.setLong(1, orderItems2.getOrder().getId());
                ps.setLong(2, orderItems2.getProduct().getId());
                ps.setInt(3, orderItems2.getQuantity());
                ps.setBigDecimal(4, orderItems2.getPrice());
                ps.addBatch();
            }
                ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public long save(OrderItems orderItems) {
        return 0;
    }

    @Override
    public OrderItems findById(long id) {
        return null;
    }

    @Override
    public List<OrderItems> findAll(int size) {
        return List.of();
    }

    @Override
    public void update(OrderItems orderItems) {
//a
    }
}
