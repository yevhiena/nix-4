package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AbstractDao;
import ua.com.alevel.models.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements AbstractDao<Route> {

    private Connection connection;

    public RouteDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Route route) {
        try (PreparedStatement create = connection.prepareStatement(
                "INSERT INTO Route(id, from_id, to_id, cost) VALUES (?,?,?,?)")) {

            create.setInt(1, route.getId());
            create.setInt(2, route.getFromId());
            create.setInt(3, route.getToId());
            create.setInt(4, route.getCost());
            create.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Route read(int routeId) {
        try (PreparedStatement readById = connection.prepareStatement(
                "SELECT * FROM Route WHERE id = (?)")) {
            readById.setInt(1, routeId);
            ResultSet rs = readById.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                int idFrom = rs.getInt("from_id");
                int idTo = rs.getInt("to_id");
                int cost = rs.getInt("cost");
                return new Route(id, idFrom, idTo, cost);
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Route> read() {
        try (PreparedStatement readAll = connection.prepareStatement(
                "SELECT * FROM Route")) {
            ResultSet rs = readAll.executeQuery();
            List<Route> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idFrom = rs.getInt("from_id");
                int idTo = rs.getInt("to_id");
                int cost = rs.getInt("cost");
                list.add(new Route(id, idFrom, idTo, cost));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Route route) {
        try (PreparedStatement update = connection.prepareStatement(
                "UPDATE Route SET from_id = ?, to_id = ?, cost = ? WHERE id = ?")) {
            update.setInt(1, route.getFromId());
            update.setInt(2, route.getToId());
            update.setInt(3, route.getCost());
            update.setInt(4, route.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int routeId) {
        try (PreparedStatement delete = connection.prepareStatement(
                "DELETE FROM Route WHERE id = ?")) {
            delete.setInt(1, routeId);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
