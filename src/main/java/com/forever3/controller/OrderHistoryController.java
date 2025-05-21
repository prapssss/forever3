package com.forever3.controller;

import com.forever3.config.DbConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/orderHistory")
public class OrderHistoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            session.setAttribute("errorMessage", "Please login to view order history");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try (Connection conn = DbConfig.getDbConnection()) {
            // Get all orders for the customer
            List<Map<String, Object>> orders = getCustomerOrders(conn, customerId);
            
            // For each order, get its items
            for (Map<String, Object> order : orders) {
                int orderId = (int) order.get("orderId");
                List<Map<String, Object>> orderItems = getOrderItems(conn, orderId);
                order.put("items", orderItems);
            }

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/WEB-INF/pages/orderhistory.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error loading order history: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    private List<Map<String, Object>> getCustomerOrders(Connection conn, int customerId) throws SQLException {
        List<Map<String, Object>> orders = new ArrayList<>();
        String sql = "SELECT o.*, p.payment_method, p.payment_status, p.payment_date " +
                    "FROM orders o " +
                    "LEFT JOIN payment p ON o.order_id = p.order_id " +
                    "WHERE o.customer_id = ? " +
                    "ORDER BY o.order_date DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> order = new HashMap<>();
                order.put("orderId", rs.getInt("order_id"));
                order.put("orderDate", rs.getTimestamp("order_date"));
                order.put("totalPrice", rs.getDouble("total_price"));
                order.put("paymentStatus", rs.getString("payment_status"));
                order.put("paymentMethod", rs.getString("payment_method"));
                order.put("paymentDate", rs.getTimestamp("payment_date"));
                orders.add(order);
            }
        }
        return orders;
    }

    private List<Map<String, Object>> getOrderItems(Connection conn, int orderId) throws SQLException {
        List<Map<String, Object>> items = new ArrayList<>();
        String sql = "SELECT oi.*, i.item_name, i.image_url " +
                    "FROM order_items oi " +
                    "JOIN item i ON oi.item_id = i.item_id " +
                    "WHERE oi.order_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("itemName", rs.getString("item_name"));
                item.put("quantity", rs.getInt("quantity"));
                item.put("price", rs.getDouble("price"));
                item.put("imageUrl", rs.getString("image_url"));
                items.add(item);
            }
        }
        return items;
    }
} 