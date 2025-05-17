package com.forever3.controller.admin;

import com.forever3.config.DbConfig;
import com.forever3.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/customers")
public class CustomersController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UserModel> customers = new ArrayList<>();

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer WHERE role = 'customer'");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserModel user = new UserModel(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("user_name"),
                    rs.getString("email"),
                    rs.getString("number"),
                    rs.getString("password"),
                    rs.getString("image_url"),
                    rs.getString("role")
                );
                customers.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("customers", customers);
        request.setAttribute("activePage", "customers");
        request.getRequestDispatcher("/WEB-INF/pages/admin/customers.jsp").forward(request, response);
    }
}
