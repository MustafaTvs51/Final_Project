package patika_14_final_project.dao;

import patika_14_final_project.dao.Constants.SqlScriptConstants;
import patika_14_final_project.model.Category;
import patika_14_final_project.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CategoryDAO implements BaseDAO<Category> {
    @Override
    public void save(Category category) {

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CATEGORY_SAVE);
        ){
            ps.setString(1,category.getName());
            ps.setLong(2,category.getCreatedUser().getId());
            ps.setLong(3,category.getUpdatedUser().getId());
            ps.executeQuery();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Category findById(long id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(long id) {

    }
}
