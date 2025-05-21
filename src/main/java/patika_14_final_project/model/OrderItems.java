package patika_14_final_project.model;

import java.math.BigDecimal;

public class OrderItems {

    private Long id;

    private Order order;

    private Product product;

    private int quantity;

    private BigDecimal price;

    public OrderItems() {
    }

    public OrderItems(Long id, Order order, BigDecimal price, Product product, int quantity) {
        this.id = id;
        this.order = order;
        this.price = price;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
