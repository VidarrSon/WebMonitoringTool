package servlets.crud;

import model.DataSourceConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPage = Integer.parseInt(request.getParameter("id"));

        deleteById(idPage, request, response);

        response.sendRedirect("/monitoring");
    }

    private void deleteById (int idPage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "DELETE FROM url_table WHERE id=?";

        try (Connection connection = new DataSourceConnection().getConnection();
             PreparedStatement removeById = connection.prepareStatement(sql)) {

            removeById.setInt(1, idPage);
            removeById.executeUpdate();

        } catch (ClassNotFoundException e) {
            request.getRequestDispatcher("/driver_error").forward(request, response);
        } catch (SQLException e) {
            request.getRequestDispatcher("/connection_error").forward(request, response);
        }
    }
}
