package DAO.category;

import config.SingletonConnection;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CategoryDAO implements ICategoryDAO {
    Connection connection = SingletonConnection.getConnection();

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("select * from category;");
        )
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id= resultSet.getInt("id");
                String name= resultSet.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static void main(String[] args) {
        ICategoryDAO categoryDAO = new CategoryDAO();
        System.out.println(categoryDAO.findAll());
    }


    @Override
    public Category findById(int id) {
        Category category = null;
        connection = SingletonConnection.getConnection();
        try(
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM  category where  id =?")
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                category = new Category(id, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean update(Category category) {
        return false;
    }

    @Override
    public boolean save(Category category) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
