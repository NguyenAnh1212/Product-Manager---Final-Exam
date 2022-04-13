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
    public static final String SQL_UPDATE_PRODUCT = "update product p join category c on c.id = p.cat_id set p.name = ?, price =?, quantity = ?, color = ?, description =?, cat_id = ? where p.id = ?;";
    public static final String SQL_FIND_PRODUCT_BY_ID = "select product.id as id, product.name as name, price, quantity, color, description, c.id as Cat_id, c.name as CategoryName  from product join Category C on C.id = product.cat_id where product.id =?;";
    public static final String SQL_DELETE_PRODUCT_BY_ID = "delete from product where id =?";
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

//    public static void main(String[] args) {
//        IProductDAO productDAO = new ProductDAO();
//        System.out.println(productDAO.findById(1));
//    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try(
                PreparedStatement pstm = connection.prepareStatement(SQL_FIND_PRODUCT_BY_ID
                ))
                 {
                     pstm.setInt(1,id);
                     ResultSet rs = pstm.executeQuery();
                     while (rs.next()){
                         String name = rs.getString("name");
                         int price = rs.getInt("price");
                         int quantity = rs.getInt("quantity");
                         String color = rs.getString("color");
                         String description = rs.getString("description");
                         int cat_id = rs.getInt("Cat_id");
                         String categoryName = rs.getString("CategoryName");
                         Category category = new Category(cat_id, categoryName);
                         product = new Product(id, name, price, quantity, color, description, category);
                     }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean update(Product product) {
        try ( PreparedStatement pstm = connection.prepareStatement(SQL_UPDATE_PRODUCT)) {
        pstm.setString(1, product.getName());
        pstm.setInt(2, product.getPrice());
        pstm.setInt(3, product.getQuantity());
        pstm.setString(4, product.getColor());
        pstm.setString(5, product.getDescription());
        pstm.setInt(6, product.getCategory().getId());
        pstm.setInt(7, product.getId());
         return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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


    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try(
                PreparedStatement pstm = connection.prepareStatement(SQL_DELETE_PRODUCT_BY_ID)
                ){
            pstm.setInt(1, id);
            rowDeleted = pstm.executeUpdate() > 0;
        }
        return rowDeleted;
    }


}
