package com.forever3.controller.admin;

import com.forever3.config.DbConfig;
import com.forever3.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard" })
public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UserModel> customers = new ArrayList<>();
        int totalCustomers = 0;
        int totalOrders = 0;
        int totalProducts = 0;

        try (Connection conn = DbConfig.getDbConnection()) {
            // Get total customers
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer WHERE role = 'customer'");
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
                totalCustomers = customers.size();
            }

            // Get total orders
            try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) as total FROM orders");
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalOrders = rs.getInt("total");
                }
            }

            // Get total products
            try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) as total FROM item");
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalProducts = rs.getInt("total");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("customers", customers);
        request.setAttribute("totalCustomers", totalCustomers);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("activePage", "dashboard");

        request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
    }
}
