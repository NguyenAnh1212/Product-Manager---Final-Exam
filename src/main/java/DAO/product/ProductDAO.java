package DAO.product;

import config.SingletonConnection;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    public static final String SQL_SELECT_FROM_PRODUCT = "select product.id as id, product.name as name, price, quantity, color, description, c.id as Cat_id, c.name as CategoryName  from product join Category C on C.id = product.cat_id;";
    public static final String SQL_INSERT_INTO_PRODUCT = "insert into product (name, price, quantity, color, description, cat_id)\n" +
            "values (?,?,?,?,?,?);";
    Connection connection = SingletonConnection.getConnection();
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        connection = SingletonConnection.getConnection();
        try(
                PreparedStatement pstm = connection.prepareStatement(SQL_SELECT_FROM_PRODUCT)
                ) {
            ResultSet rs = pstm.executeQuery();
            while ((rs.next())){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int cat_id = rs.getInt("Cat_id");
                String categoryName = rs.getString("CategoryName");
                Category category = new Category(cat_id, categoryName);
                Product product = new Product(id, name, price, quantity, color, description, category);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        return IProductDAO.super.findById(id);
    }

    @Override
    public boolean update(Product product) {
        return IProductDAO.super.update(product);
    }

    @Override
    public boolean save(Product product) {
        connection = SingletonConnection.getConnection();
        try(
                PreparedStatement pstm = connection.prepareStatement(SQL_INSERT_INTO_PRODUCT)
                ) {
            pstm.setString(1, product.getName());
            pstm.setInt(2, product.getPrice());
            pstm.setInt(3, product.getQuantity());
            pstm.setString(4, product.getColor());
            pstm.setString(5, product.getDescription());
            pstm.setInt(6, product.getCategory().getId());
            return pstm.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
