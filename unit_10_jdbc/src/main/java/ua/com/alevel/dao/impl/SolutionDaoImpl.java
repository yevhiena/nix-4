package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AbstractDao;
import ua.com.alevel.models.Solution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDaoImpl implements AbstractDao<Solution> {

    private Connection connection;

    public SolutionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Solution solution) {
        try (PreparedStatement create = connection.prepareStatement(
                "INSERT INTO Solution(problem_id, cost) VALUES (?,?)")) {

            create.setInt(1, solution.getId());
            create.setInt(2, solution.getCost());

            create.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Solution read(int solutionId) {
        try (PreparedStatement readById = connection.prepareStatement(
                "SELECT * FROM Solution WHERE problem_id = (?)")) {
            readById.setInt(1, solutionId);
            ResultSet rs = readById.executeQuery();
            if(rs.next()){
                int id = rs.getInt("problem_id");
                int cost = rs.getInt("cost");
                return new Solution(id, cost);
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Solution> read() {
        try (PreparedStatement readAll = connection.prepareStatement(
                "SELECT * FROM Solution")) {
            ResultSet rs = readAll.executeQuery();
            List<Solution> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("problem_id");
                int cost = rs.getInt("cost");
                list.add(new Solution(id, cost));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Solution solution) {
        try (PreparedStatement update = connection.prepareStatement(
                "UPDATE Solution SET cost = ? WHERE problem_id = ?")) {
            update.setInt(1, solution.getCost());
            update.setInt(2, solution.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int solutionId) {
        try (PreparedStatement delete = connection.prepareStatement(
                "DELETE FROM Solution WHERE problem_id = ?")) {
            delete.setInt(1, solutionId);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
