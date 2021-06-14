package ua.com.alevel.dao;

import java.sql.SQLException;

public interface UserDao {
    long getUserIdByEmail(String email) throws SQLException;

}
