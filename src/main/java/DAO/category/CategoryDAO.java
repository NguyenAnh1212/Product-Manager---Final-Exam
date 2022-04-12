package DAO.category;

import config.SingletonConnection;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.SingletonConnection.getConnection;

public class CategoryDAO implements ICategoryDAO {
    Connection connection = SingletonConnection.getConnection();

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        connection = SingletonConnection.getConnection();
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

    @Override
    public Category findById(int id) {
        return ICategoryDAO.super.findById(id);
    }

    @Override
    public boolean update(Category category) {
        return ICategoryDAO.super.update(category);
    }

    @Override
    public boolean save(Category category) {
        return ICategoryDAO.super.save(category);
    }
}
