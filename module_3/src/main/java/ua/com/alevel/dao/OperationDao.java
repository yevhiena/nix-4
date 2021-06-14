package ua.com.alevel.dao;

import ua.com.alevel.model.entity.Operation;

import java.sql.SQLException;

public interface OperationDao {

    void createOperation(Operation operation) throws SQLException;

}
