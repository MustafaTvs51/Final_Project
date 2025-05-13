package patika_14_final_project.service;


import patika_14_final_project.dao.CategoryDAO;
import patika_14_final_project.exception.ExceptionMessagesConstant;
import patika_14_final_project.exception.PatikaStoreException;
import patika_14_final_project.model.Category;
import patika_14_final_project.model.User;
import patika_14_final_project.model.enums.Role;

import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    public void save(String name, User user) throws PatikaStoreException {

        if (!Role.ADMIN.equals(user.getRole())) {
            throw new PatikaStoreException(ExceptionMessagesConstant.USER_IS_NOT_ADMIN);
        }

        categoryDAO.save(new Category(name, user, user));

        System.out.println("Kategori Oluşturuldu ! ");
    }

    public List<Category> getAll() {
        return categoryDAO.findAll();
    }

    public void deleteById(long id) {
        categoryDAO.delete(id);

        System.out.println("Kategori silindi");

    }

    public Category getById(Long categoryId) throws PatikaStoreException {

        Category foundCategory = categoryDAO.findById(categoryId);

        if (categoryId == null) {
            throw new PatikaStoreException(ExceptionMessagesConstant.CATEGORY_NOT_FOUND);
        }
        System.out.println("Kategori Bulundu ! : " + foundCategory);

        return foundCategory;

    }
}
