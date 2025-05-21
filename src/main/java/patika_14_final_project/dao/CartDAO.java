package patika_14_final_project.dao;

import patika_14_final_project.dao.Constants.SqlScriptConstants;
import patika_14_final_project.model.Cart;
import patika_14_final_project.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO implements BaseDAO<Cart> {


    @Override
    public void delete(long id) {

    }

    @Override
    public long save(Cart cart) {
        return 0;
       /* try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_SAVE)) {

            ps.setLong(1, cart.getCustomer().getId());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cart.setId(rs.getLong("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
    }

    @Override
    public Cart findById(long id) {
        return null;
    }

    @Override
    public List<Cart> findAll(int size) {
        return List.of();
    }

    @Override
    public void update(Cart cart) {

    }

    public Cart findByCustomerId(Long customerId) {
        Cart cart = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_FIND_BY_CUSTOMER_ID)) {
            ps.setLong(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
              cart = new Cart(rs.getLong("id"),
                      rs.getLong("customer_id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
}
