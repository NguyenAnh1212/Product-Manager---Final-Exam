package DAO.category;

import DAO.IDAO;
import model.Category;

import java.util.List;

public interface ICategoryDAO extends IDAO<Category> {
    @Override
    default List<Category> findAll() {
        return null;
    }

    @Override
    default Category findById(int id) {
        return null;
    }

    @Override
    default boolean update(Category category) {
        return false;
    }

    @Override
    default boolean save(Category category) {
        return false;
    }
}
