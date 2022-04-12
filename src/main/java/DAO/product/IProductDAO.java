package DAO.product;

import DAO.IDAO;
import model.Product;

import java.util.List;

public interface IProductDAO extends IDAO<Product> {
    @Override
    default List<Product> findAll() {
        return null;
    }

    @Override
    default Product findById(int id) {
        return null;
    }

    @Override
    default boolean update(Product product) {
        return false;
    }

    @Override
    default boolean save(Product product) {
        return false;
    }
}
