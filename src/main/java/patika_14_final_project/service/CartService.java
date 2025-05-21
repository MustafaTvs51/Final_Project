package patika_14_final_project.service;

import patika_14_final_project.dao.CartDAO;
import patika_14_final_project.dao.CartItemDAO;
import patika_14_final_project.model.Cart;
import patika_14_final_project.model.CartItem;
import patika_14_final_project.model.Customer;
import patika_14_final_project.model.Product;

import java.util.List;

public class CartService {

    private CartDAO cartDAO;

    private CartItemDAO cartItemDAO;

    public CartService() {
        cartDAO = new CartDAO();
        cartItemDAO = new CartItemDAO();
    }

    public void addToCart(Customer loginnedCustomer, Product product, int quantity) {
        Cart cart = cartDAO.findByCustomerId(loginnedCustomer.getId());

        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(loginnedCustomer);
            cartDAO.save(cart);
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);

        cartItemDAO.save(cartItem);

        System.out.print("Ürün Sepetinize Eklendi\n");
    }

    public void clear(Customer loginnedCustomer) {
        Cart cart = cartDAO.findByCustomerId(loginnedCustomer.getId());

        int effectedRow = cartItemDAO.clear(cart.getId());

        System.out.println("Sepetinizdeki "+ effectedRow + " adet ürün silindi\n");
    }
}
