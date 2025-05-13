package patika_14_final_project.dao;

import patika_14_final_project.dao.Constants.SqlScriptConstants;
import patika_14_final_project.model.Customer;
import patika_14_final_project.model.Product;
import patika_14_final_project.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements BaseDAO<Product>{

    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PRODUCT_SEARCH_BY_NAME);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setStock(rs.getInt("stock"));
                p.setCreatedDate(LocalDateTime.parse(rs.getString("createddate")));
                p.setUpdatedDate(LocalDateTime.parse(rs.getString("updateddate")));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void save(Product product) {
        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PRODUCT_SAVE)){
            ps.setString(1,product.getName());
            ps.setBigDecimal(2,product.getPrice());
            ps.setInt(3,product.getStock());
            ps.setLong(4,product.getCategory().getId());
            ps.setLong(5,product.getCreatedUser().getId());
            ps.setLong(6,product.getUpdatedUser().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(long id) {
        
    }
}
