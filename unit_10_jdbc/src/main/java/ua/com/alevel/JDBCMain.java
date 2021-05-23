package ua.com.alevel;

import ua.com.alevel.dao.impl.LocationDaoImpl;
import ua.com.alevel.dao.impl.ProblemDaoImpl;
import ua.com.alevel.dao.impl.RouteDaoImpl;
import ua.com.alevel.dao.impl.SolutionDaoImpl;
import ua.com.alevel.graph.WeightedGraph;
import ua.com.alevel.models.Location;
import ua.com.alevel.models.Problem;
import ua.com.alevel.models.Route;
import ua.com.alevel.models.Solution;
import ua.com.alevel.service.AStarPathFinder;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCMain {

    public static void main(String[] args) {
        Properties props = loadProperties();
        String url = props.getProperty("url");

        WeightedGraph weightedGraph = new WeightedGraph();
        AStarPathFinder aStarPathFinder = new AStarPathFinder();

        try(Connection connection = DriverManager.getConnection(url, props)) {
            connection.setAutoCommit(false);

            ProblemDaoImpl problemDao = new ProblemDaoImpl(connection);
            SolutionDaoImpl solutionDao = new SolutionDaoImpl(connection);
            RouteDaoImpl routeDao = new RouteDaoImpl(connection);
            LocationDaoImpl locationDao = new LocationDaoImpl(connection);

            List<Location> locations = locationDao.read();
            List<Route> routes = routeDao.read();
            for (Location lo: locations) {
                weightedGraph.addVertex(lo.getName());
            }
            for (Route route : routes) {
                String nameFrom = locationDao.read(route.getFromId()).getName();
                String nameTo = locationDao.read(route.getToId()).getName();
                int cost = route.getCost();
                weightedGraph.addVertexWithWeight(nameFrom, nameTo, cost);
            }

            List<Problem> problems = problemDao.read();
            List<Solution> solutions = new ArrayList<>();
            for (Problem problem : problems) {
                if(solutionDao.read(problem.getId()) == null){
                    String nameFrom = locationDao.read(problem.getFromId()).getName();
                    String nameTo = locationDao.read(problem.getToId()).getName();
                    double path = aStarPathFinder.getShortestPath(weightedGraph, nameFrom, nameTo);
                    solutions.add(new Solution(problem.getId(), ((int) path)));
                }
            }

            try(PreparedStatement insertSolution = connection.prepareStatement(
                    "INSERT INTO Solution (problem_id, cost) VALUES (?, ?)"
            )) {
                for (Solution solution : solutions) {
                    insertSolution.setInt(1, solution.getId());
                    insertSolution.setInt(2, solution.getCost());
                    insertSolution.addBatch();
                }
                insertSolution.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try(InputStream input = JDBCMain.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return props;
    }
}
