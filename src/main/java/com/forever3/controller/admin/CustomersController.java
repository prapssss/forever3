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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/customers")
public class CustomersController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UserModel> customers = new ArrayList<>();
        Map<Integer, Integer> customerOrderCounts = new HashMap<>();
        Map<Integer, String> customerPaymentStatus = new HashMap<>();

        try (Connection conn = DbConfig.getDbConnection()) {
            // Get all customers
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
            }

            // Get order counts for each customer
            String orderCountSql = "SELECT customer_id, COUNT(*) as order_count FROM orders GROUP BY customer_id";
            try (PreparedStatement stmt = conn.prepareStatement(orderCountSql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    customerOrderCounts.put(rs.getInt("customer_id"), rs.getInt("order_count"));
                }
            }

            // Get latest payment status for each customer
            String paymentStatusSql = 
                "SELECT o.customer_id, p.payment_status " +
                "FROM orders o " +
                "LEFT JOIN payment p ON o.order_id = p.order_id " +
                "WHERE o.order_id IN (SELECT MAX(order_id) FROM orders GROUP BY customer_id)";
            try (PreparedStatement stmt = conn.prepareStatement(paymentStatusSql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String status = rs.getString("payment_status");
                    if (status == null) status = "Pending";
                    customerPaymentStatus.put(rs.getInt("customer_id"), status);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("customers", customers);
        request.setAttribute("customerOrderCounts", customerOrderCounts);
        request.setAttribute("customerPaymentStatus", customerPaymentStatus);
        request.setAttribute("activePage", "customers");
        request.getRequestDispatcher("/WEB-INF/pages/admin/customers.jsp").forward(request, response);
    }
}
