package patika_14_final_project.model;

import patika_14_final_project.model.Category;

import java.math.BigDecimal;

public class Product extends BaseModel{

    private  String name;
    private BigDecimal price;
    private  int stock;
    private Category category;

    public Product() {
    }

    public Product(long id ) {
        this.setId(id);
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(long id, String name) {
        this.setId(id);
        this.name = name;
    }

    public Product(long id, String name, BigDecimal price) {
        this.setId(id);
        this.name = name;
        this.price = price;
    }



    public Product(Long id,String name, BigDecimal price, int stock, Category category) {
        this.setId(id);
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Product(Long id,String name, BigDecimal price, int stock) {
        this.setId(id);
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, BigDecimal price, int stock, Category category) {

        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
