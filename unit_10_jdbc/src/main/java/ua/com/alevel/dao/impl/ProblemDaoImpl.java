package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AbstractDao;
import ua.com.alevel.models.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblemDaoImpl implements AbstractDao<Problem> {
    private Connection connection;

    public ProblemDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Problem problem) {
        try (PreparedStatement create = connection.prepareStatement(
                "INSERT INTO Problem(id, from_id, to_id) VALUES (?,?,?)")) {

            create.setInt(1, problem.getId());
            create.setInt(2, problem.getFromId());
            create.setInt(3, problem.getToId());
            create.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Problem read(int problemId) {
        try (PreparedStatement readById = connection.prepareStatement(
                "SELECT * FROM Problem WHERE id = (?)")) {
            readById.setInt(1, problemId);
            ResultSet rs = readById.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                int idFrom = rs.getInt("from_id");
                int idTo = rs.getInt("to_id");
                return new Problem(id, idFrom, idTo);
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Problem> read() {
        try (PreparedStatement readAll = connection.prepareStatement(
                "SELECT * FROM Problem")) {
            ResultSet rs = readAll.executeQuery();
            List<Problem> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idFrom = rs.getInt("from_id");
                int idTo = rs.getInt("to_id");
                list.add(new Problem(id, idFrom, idTo));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Problem problem) {
        try (PreparedStatement update = connection.prepareStatement(
                "UPDATE Problem SET from_id = ?, to_id = ? WHERE id = ?")) {
            update.setInt(1, problem.getFromId());
            update.setInt(2, problem.getToId());
            update.setInt(3, problem.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int problemId) {
        try (PreparedStatement delete = connection.prepareStatement(
                "DELETE FROM Problem WHERE id = ?")) {
            delete.setInt(1, problemId);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
