package servlets.crud;

import model.DataSourceConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/update")
public class CreateUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sqlUpdate = "UPDATE url_table SET url_address=?, status=?, url_name=?, monitoring_period=?, response_time=?, " +
                "response_code=?, response_substring=?, response_range_min=?, response_range_max=? WHERE id=?";
        String sqlInsert = "INSERT INTO url_table (url_address, status, url_name, monitoring_period, response_time, " +
                "response_code, response_substring, response_range_min, response_range_max) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int idPage = -1;
        if (request.getParameter("id") != null) {
            idPage = Integer.parseInt(request.getParameter("id"));
            executeSql(idPage, sqlUpdate, request, response);
        } else
            executeSql(idPage, sqlInsert, request, response);

        response.sendRedirect("/monitoring");
    }


    private void executeSql (int idPage, String sql, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = new DataSourceConnection().getConnection();
             PreparedStatement executeStatement = connection.prepareStatement(sql)) {

            executeStatement.setString(1, request.getParameter("url_address"));
            executeStatement.setString(2, "Unknown");
            executeStatement.setString(3, request.getParameter("url_name"));
            executeStatement.setInt(4, Integer.parseInt(request.getParameter("monitoring_period")));
            executeStatement.setInt(5, Integer.parseInt(request.getParameter("response_time")));
            executeStatement.setInt(6, Integer.parseInt(request.getParameter("response_code")));
            executeStatement.setString(7, request.getParameter("response_substring"));
            executeStatement.setInt(8, Integer.parseInt(request.getParameter("response_range_min")));
            executeStatement.setInt(9, Integer.parseInt(request.getParameter("response_range_max")));
            if (idPage != -1) executeStatement.setInt(10, Integer.parseInt(request.getParameter("id")));

            executeStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            request.getRequestDispatcher("/driver_error").forward(request, response);
        } catch (SQLException e) {
            request.getRequestDispatcher("/connection_error").forward(request, response);
        }
    }
}
