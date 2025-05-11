package patika_14_final_project.dao;

import patika_14_final_project.model.Customer;
import patika_14_final_project.model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String searchByNameScript = """
            SELECT * FROM product WHERE name LIKE ?
            """;


    public List<Product> searchByName (String name){
        List<Product> products = new ArrayList<>();
        String url = "jdbc:postgresql://localhost:5432/patika_store";
        String pgUser = "postgres";
        String pgPassword = "Mustafa1";
        try (Connection connection = DriverManager.getConnection(url, pgUser, pgPassword)){
            PreparedStatement ps = connection.prepareStatement(searchByNameScript);
            ps.setString(1,"%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setStock(rs.getInt("stock"));
                p.setCreatedDate(LocalDateTime.parse(rs.getString("createddate")));
                p.setUpdatedDate(LocalDateTime.parse(rs.getString("updateddate")));
                products.add(p);
            }
    } catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
}
