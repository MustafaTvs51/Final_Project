package patika_14_final_project.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order extends BaseModel {


    private Customer customer;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private List<OrderItems> orderItems;

    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(Long id) {
        this.setId(id);
    }


    public Order(Customer customer) {
        this.customer = customer;
        this.orderDate = LocalDateTime.now();

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }
}
