package patika_14_final_project.dao;

import patika_14_final_project.dao.Constants.SqlScriptConstants;
import patika_14_final_project.model.Cart;
import patika_14_final_project.model.CartItem;
import patika_14_final_project.model.Customer;
import patika_14_final_project.model.Product;
import patika_14_final_project.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    public void clear(Long customerId) {


    }

    public Cart findByCustomerId(Long customerId) {

        Cart cart = null;


        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_FIND_BY_CUSTOMER_ID)
        ) {
            ps.setLong(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cart = new Cart(new Customer(rs.getLong("customer_id")),
                        List.of(new CartItem(new Product(rs.getLong("product_id")))),
                        BigDecimal.valueOf(123L));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    public void save(Cart cart) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_SAVE)
        ) {

            ps.setLong(1, cart.getCustomer().getId());
            ps.setLong(2, cart.getItems().get(0).getProduct().getId());
            ps.setInt(3, cart.getQuantity());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cart> findAllByCustomerId(Long customerId) {
        List<Cart> carts = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_FIND_ALL_BY_CUSTOMER_ID)
        ) {
            ps.setLong(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();

                cart.setItems(List.of(new CartItem(new Product(rs.getString("product_name")))));
                int quantity = rs.getInt("quantity");
                cart.setQuantity(quantity);
                BigDecimal price = rs.getBigDecimal("price");
                cart.setTotalAmount(new BigDecimal(price.intValue() * quantity));
                carts.add(cart);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }
}
