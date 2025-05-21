package patika_14_final_project.service;

import patika_14_final_project.dao.OrderDAO;
import patika_14_final_project.exception.ExceptionMessagesConstant;
import patika_14_final_project.exception.PatikaStoreException;
import patika_14_final_project.model.*;
import patika_14_final_project.model.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final OrderDAO orderDAO;

    private final CartItemService cartItemService;

    private final PaymentService paymentService;

    private final CartService cartService;

    private final OrderItemsService orderItemsService;

    private final ProductService productService;


    public OrderService() {
        this.orderDAO = new OrderDAO();
        this.cartItemService = new CartItemService();
        this.paymentService = new PaymentService();
        this.cartService = new CartService();
        this.orderItemsService = new OrderItemsService();
        this.productService = new ProductService();
    }

    public Order save(Customer customer, PaymentMethod paymentMethod) throws PatikaStoreException {

        List<CartItem> cartItems = cartItemService.getByCustomer(customer);

        if (cartItems.isEmpty()){
            throw new PatikaStoreException(ExceptionMessagesConstant.CART_ITEMS_IS_EMPTY);
        }

        BigDecimal totalAmount = BigDecimal.ZERO;

        cartItems.forEach(
                cartItem -> {
                    BigDecimal amount = new BigDecimal(cartItem.getProduct().getPrice().intValue() * cartItem.getQuantity());
                    totalAmount.add(amount);
                }
        );

        Order order = new Order();
        order.setCustomer(customer);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(LocalDateTime.now());

        long orderId = orderDAO.save(order);
        order.setId(orderId);


        List<OrderItems> orderItems = new ArrayList<>();

        cartItems.forEach(cartItem -> {
            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(new Order(orderId));
            orderItem.setProduct(new Product(cartItem.getProduct().getId()));
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItems.add(orderItem);
        });

        orderItemsService.save(orderItems);

        paymentService.save(order, paymentMethod);

        cartService.clear(customer);


        cartItems.forEach(cartItem -> {
            Product product = new Product(cartItem.getProduct().getId());

            productService.updateStock(product, cartItem.getQuantity());
        });


        System.out.println("Sipariş ve ödeme işlemi başarıyla tamamlandı.");
        return order;


    }
}
