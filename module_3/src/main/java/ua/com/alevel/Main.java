package ua.com.alevel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.alevel.controller.Controller;
import ua.com.alevel.dao.CommonOperationDao;
import ua.com.alevel.dao.CommonReportDao;
import ua.com.alevel.dao.impl.JDBCReportDaoImpl;
import ua.com.alevel.dao.impl.JPAOperationDaoImpl;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.service.ReportService;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Main {

    // check user
    // logger
    // custom exceptions
    // only 1 row in result set !??!!?!
    // close result set in jdbc

    //        111111111
    //        2010-12-10
    //        2021-06-14

    public static void main(String[] args) {


        try (SessionFactory sessionFactory = new Configuration().configure()
                .setProperty("hibernate.connection.username", args[1])
                .setProperty("hibernate.connection.password", args[2]).buildSessionFactory();
             Session session = sessionFactory.openSession()){


            Properties props = loadProperties(args);
            String url = props.getProperty("url");

            try(Connection connection = DriverManager.getConnection(url, props)) {
                connection.setAutoCommit(false);

                CommonOperationDao operationDao = new JPAOperationDaoImpl(session);
                OperationService operationService = new OperationService(operationDao);
                CommonReportDao reportDao = new JDBCReportDaoImpl(connection);
                ReportService reportService = new ReportService(reportDao);

                Controller controller = new Controller(args[0], operationService, reportService);
                controller.run();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static Properties loadProperties(String[] args) {
        Properties props = new Properties();
        try(InputStream input = Main.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
            props.setProperty("user", args[1]);
            props.setProperty("password", args[2]);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return props;
    }

}
