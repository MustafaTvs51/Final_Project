package patika_14_final_project.service;

import patika_14_final_project.dao.CartDAO;
import patika_14_final_project.model.Cart;
import patika_14_final_project.model.CartItem;
import patika_14_final_project.model.Customer;
import patika_14_final_project.model.Product;

import java.util.List;

public class CartService {

    private CartDAO cartDAO;

    public CartService() {
        this.cartDAO = new CartDAO();
    }

    public Cart getByCustomerId(long customerid){
        return cartDAO.findByCustomerId(customerid);
    }

    public void addToCart(Customer loginnedCustomer, Product product, int quantity) {
        Cart cart = getByCustomerId(loginnedCustomer.getId());

        if (cart == null) {
            cart = new Cart();
        }

        cart.getItems().add(new CartItem(product));

        cartDAO.save(new Cart(loginnedCustomer.getId(),product.getId(),quantity));
        System.out.print("Ürün Sepetinize Eklendi\n");

    }

    public List<Cart> getAll(Customer loginnedCustomer) {

        return cartDAO.findAllByCustomerId(loginnedCustomer.getId());


    }
}
