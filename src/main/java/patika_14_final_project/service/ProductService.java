package patika_14_final_project.service;

import patika_14_final_project.dao.ProductDAO;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }
}
