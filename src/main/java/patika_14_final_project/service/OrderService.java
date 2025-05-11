package patika_14_final_project.service;

import patika_14_final_project.dao.OrderDAO;
import patika_14_final_project.model.Customer;
import patika_14_final_project.model.Order;
import patika_14_final_project.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = new OrderDAO();
    }

    public Order save(Customer customer, List<Product> products){

        BigDecimal totalAmount = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();

        order.setProducts(products);
        order.setCustomer(customer);
        order.setTotalAmount(totalAmount);

        orderDAO.save(order);
        return order;


    }
}
