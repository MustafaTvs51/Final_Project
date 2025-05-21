package patika_14_final_project.dao;

import patika_14_final_project.dao.Constants.SqlScriptConstants;
import patika_14_final_project.model.Cart;
import patika_14_final_project.model.CartItem;
import patika_14_final_project.model.Product;
import patika_14_final_project.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {

    public void clear(Long customerId) {


    }

    public List<CartItem> findByCustomerId(Long customerId) {

        List<CartItem> cartItems = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_ITEM_FIND_BY_CUSTOMER_ID)
        ) {
            ps.setLong(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                long cartItemId = rs.getLong("cart_item_id");
                int quantity = rs.getInt("quantity");
                long productId = rs.getLong("product_id");
                String productName = rs.getString("product_name");
                BigDecimal price = rs.getBigDecimal("price");

                Product product = new Product(productId, productName, price);
                cartItems.add(new CartItem(cartItemId, product, new Cart(), quantity));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    public void save(CartItem cartItem) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CART_ITEM_SAVE)
        ) {

            ps.setLong(1, cartItem.getCart().getId());
            ps.setLong(2, cartItem.getProduct().getId());
            ps.setInt(3, cartItem.getQuantity());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* public List<Cart> findAllByCustomerId(Long customerId) {
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
    }*/
}
