package ua.com.alevel.dao;

import ua.com.alevel.exception.DataNotFoundException;

import java.sql.SQLException;

public interface UserDao {
    long getUserIdByEmail(String email) throws SQLException, DataNotFoundException;

}
