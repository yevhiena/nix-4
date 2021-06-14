package ua.com.alevel.dao;

import ua.com.alevel.model.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao{
    List<Category> findAllCategories() throws SQLException;
}
