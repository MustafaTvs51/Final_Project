package patika_14_final_project.service;

import patika_14_final_project.dao.CartItemDAO;
import patika_14_final_project.model.CartItem;
import patika_14_final_project.model.Customer;

import java.util.List;

public class CartItemService {

    private CartItemDAO cartItemDAO;

    public CartItemService() {
        this.cartItemDAO = new CartItemDAO();
    }

    public List<CartItem> getByCustomer(Customer customer){
        return cartItemDAO.findByCustomerId(customer.getId());
    }
}
