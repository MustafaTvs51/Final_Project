package patika_14_final_project.service;

import patika_14_final_project.dao.ProductDAO;
import patika_14_final_project.exception.ExceptionMessagesConstant;
import patika_14_final_project.exception.PatikaStoreException;
import patika_14_final_project.model.Product;
import patika_14_final_project.model.User;
import patika_14_final_project.model.enums.Role;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void save(Product product, User user) throws PatikaStoreException {

        if (!Role.ADMIN.equals(user.getRole())){
            throw new PatikaStoreException(ExceptionMessagesConstant.USER_IS_NOT_ADMIN);
        }
        product.setCreatedUser(user);
        product.setUpdatedUser(user);
        productDAO.save(product);
        System.out.println("Ürün Kaydedildi ! ");

    }
}
