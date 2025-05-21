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

@WebServlet("/bill")
public class BillController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer orderId = (Integer) session.getAttribute("orderId");

        if (orderId == null) {
            session.setAttribute("errorMessage", "No order found");
            response.sendRedirect(request.getContextPath() + "/viewCart");
            return;
        }

        try (Connection conn = DbConfig.getDbConnection()) {
            // Get order details
            Map<String, Object> order = getOrderDetails(conn, orderId);
            if (order == null) {
                session.setAttribute("errorMessage", "Order not found");
                response.sendRedirect(request.getContextPath() + "/viewCart");
                return;
            }

            // Get order items
            List<Map<String, Object>> orderItems = getOrderItems(conn, orderId);

            // Get payment details
            Map<String, Object> payment = getPaymentDetails(conn, orderId);

            // Set attributes for the JSP
            request.setAttribute("order", order);
            request.setAttribute("orderItems", orderItems);
            request.setAttribute("payment", payment);

            // Clear orderId from session after successful bill generation
            session.removeAttribute("orderId");

            // Forward to bill.jsp
            request.getRequestDispatcher("/WEB-INF/pages/bill.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error generating bill: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/viewCart");
        }
    }

    private Map<String, Object> getOrderDetails(Connection conn, int orderId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Map<String, Object> order = new HashMap<>();
                order.put("orderId", rs.getInt("order_id"));
                order.put("orderDate", rs.getTimestamp("order_date"));
                order.put("totalPrice", rs.getDouble("total_price"));
                order.put("paymentStatus", rs.getString("payment_status"));
                return order;
            }
        }
        return null;
    }

    private List<Map<String, Object>> getOrderItems(Connection conn, int orderId) throws SQLException {
        List<Map<String, Object>> items = new ArrayList<>();
        String sql = "SELECT oi.*, i.item_name FROM order_items oi " +
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
                items.add(item);
            }
        }
        return items;
    }

    private Map<String, Object> getPaymentDetails(Connection conn, int orderId) throws SQLException {
        String sql = "SELECT * FROM payment WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Map<String, Object> payment = new HashMap<>();
                payment.put("paymentMethod", rs.getString("payment_method"));
                payment.put("paymentStatus", rs.getString("payment_status"));
                payment.put("paymentDate", rs.getTimestamp("payment_date"));
                payment.put("customerName", rs.getString("customer_name"));
                payment.put("mobileNumber", rs.getString("mobile_number"));
                payment.put("deliveryLocation", rs.getString("delivery_location"));
                return payment;
            }
        }
        return null;
    }
} 