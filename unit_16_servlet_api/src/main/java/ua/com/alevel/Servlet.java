package ua.com.alevel;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "app-servlet", urlPatterns = "/app")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = -8948379822734246956L;
    private static final Set<String> addresses = ConcurrentHashMap.newKeySet();

    private static final Logger log = LoggerFactory.getLogger(Servlet.class);

    @Override
    public void init() {
        log.info(getServletName() + " initialized");
    }

   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String currentClientAddress =req.getRemoteAddr() + "::" + req.getHeader("User-Agent");
        addresses.add(currentClientAddress);

        PrintWriter responseBody = resp.getWriter();
        resp.setContentType("text/html");
        responseBody.println("<h1 align=\"center\">Sample Servlet GET method processing</h1>");
       for (String s : addresses) {
           if(s.equals(currentClientAddress)) responseBody.println("<p> Request from:<b> " + s + "</b></p>");
           else responseBody.println("<p> Request from: " + s + "</p>");
       }
    }

    @Override
    public void destroy() {
        log.info(getServletName() + " destroyed");
    }
}
