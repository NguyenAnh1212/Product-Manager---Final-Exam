package DAO;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    List<T> findAll();
    T findById(int id);
    boolean update(T t);
    boolean save(T t);
    boolean delete(int id) throws SQLException;
}
