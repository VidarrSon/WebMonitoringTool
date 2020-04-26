package servlets;

import model.DataSourceConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/change_state")
public class ChangeMonitoringStateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPage = Integer.parseInt(request.getParameter("id"));

        changeState(idPage, request, response);

        response.sendRedirect("/monitoring");
    }

    private void changeState (int idPage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String statusUpdate;

        try (Connection connection = new DataSourceConnection().getConnection();
             Statement selectStatus = connection.createStatement()) {

            ResultSet resultSelectStatus = selectStatus.executeQuery("SELECT status FROM url_table WHERE id=" + idPage);
            while (resultSelectStatus.next()) {
                if (resultSelectStatus.getString("status").equals("NOT TRACKED")) {
                    statusUpdate = "Unknown";
                } else {
                    statusUpdate = "NOT TRACKED";
                }

                PreparedStatement updateStatus = connection.prepareStatement("UPDATE url_table SET status=? WHERE id=?");
                updateStatus.setString(1, statusUpdate);
                updateStatus.setInt(2, idPage);
                updateStatus.executeUpdate();
            }

        } catch (ClassNotFoundException e) {
            request.getRequestDispatcher("/driver_error").forward(request, response);
        } catch (SQLException e) {
            request.getRequestDispatcher("/connection_error").forward(request, response);
        }
    }
}
