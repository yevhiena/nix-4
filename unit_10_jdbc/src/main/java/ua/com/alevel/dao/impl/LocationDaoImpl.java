package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.AbstractDao;
import ua.com.alevel.models.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDaoImpl implements AbstractDao<Location> {
    private Connection connection;

    public LocationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Location location) {
        try (PreparedStatement createLocation = connection.prepareStatement(
                "INSERT INTO Location(name) VALUE (?)")) {

            createLocation.setString(1, location.getName());
            createLocation.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Location read(int locationId) {
        try (PreparedStatement readLocationById = connection.prepareStatement(
                "SELECT * FROM Location WHERE id = (?)")) {
            String name;
            readLocationById.setInt(1, locationId);
            ResultSet rs = readLocationById.executeQuery();
            if(rs.next()){
                name = rs.getString("name");
                int id = rs.getInt("id");
                return new Location(id, name);
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Location> read() {
        try (PreparedStatement readAll = connection.prepareStatement(
                "SELECT * FROM Location")) {
            ResultSet rs = readAll.executeQuery();
            List<Location> loc = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                loc.add(new Location(id, name));
            }
            return loc;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Location location) {
        try (PreparedStatement update = connection.prepareStatement(
                "UPDATE Location SET name = ? WHERE id = ?")) {
            update.setString(1, location.getName());
            update.setInt(2, location.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int locationId) {
        try (PreparedStatement delete = connection.prepareStatement(
                "DELETE FROM Location WHERE id = ?")) {
            delete.setInt(1, locationId);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
