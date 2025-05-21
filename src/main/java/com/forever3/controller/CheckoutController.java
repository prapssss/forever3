package com.forever3.controller;

import com.forever3.model.CartModel;
import com.forever3.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<CartModel> cart = (List<CartModel>) session.getAttribute("cart");
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            session.setAttribute("errorMessage", "Please login to proceed with checkout");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (cart == null || cart.isEmpty()) {
            session.setAttribute("errorMessage", "Your cart is empty");
            response.sendRedirect(request.getContextPath() + "/viewCart");
            return;
        }

        // Create order with Pending status
        Connection conn = null;
        PreparedStatement orderStmt = null;
        PreparedStatement orderItemStmt = null;

        try {
            conn = DbConfig.getDbConnection();
            conn.setAutoCommit(false);

            // Calculate total price
            double totalPrice = 0;
            for (CartModel item : cart) {
                totalPrice += item.getItemPrice() * item.getQuantity();
            }

            // Create order with Pending status
            String insertOrderSQL = "INSERT INTO orders (customer_id, order_date, total_price, payment_status) " +
                                  "VALUES (?, NOW(), ?, ?)";
            orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, customerId);
            orderStmt.setDouble(2, totalPrice);
            orderStmt.setString(3, "Pending");
            orderStmt.executeUpdate();

            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }

            // Insert order items
            String insertOrderItemSQL = "INSERT INTO order_items (order_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
            orderItemStmt = conn.prepareStatement(insertOrderItemSQL);

            for (CartModel item : cart) {
                orderItemStmt.setInt(1, orderId);
                orderItemStmt.setInt(2, item.getItemId());
                orderItemStmt.setInt(3, item.getQuantity());
                orderItemStmt.setDouble(4, item.getItemPrice());
                orderItemStmt.addBatch();
            }
            orderItemStmt.executeBatch();

            // Clear the cart
            String clearCartSQL = "DELETE FROM cart WHERE customer_id = ?";
            try (PreparedStatement clearCartStmt = conn.prepareStatement(clearCartSQL)) {
                clearCartStmt.setInt(1, customerId);
                clearCartStmt.executeUpdate();
            }

            conn.commit();
            session.removeAttribute("cart");

            // Store order details in session for payment page
            session.setAttribute("orderId", orderId);
            session.setAttribute("totalAmount", totalPrice);

            request.getRequestDispatcher("/WEB-INF/pages/checkout.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            session.setAttribute("errorMessage", "Error processing checkout: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/viewCart");
        } finally {
            try {
                if (orderStmt != null) orderStmt.close();
                if (orderItemStmt != null) orderItemStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This method is no longer needed as we're handling everything in doGet
        response.sendRedirect(request.getContextPath() + "/checkout");
    }
}
