package com.forever3.controller;

import com.forever3.config.DbConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/payment")
public class PaymentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");
        
        if (customerId == null) {
            session.setAttribute("errorMessage", "Please login to proceed with payment");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get the latest order for the customer
        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "SELECT o.*, SUM(oi.quantity * oi.price) as total_amount " +
                        "FROM orders o " +
                        "JOIN order_items oi ON o.order_id = oi.order_id " +
                        "WHERE o.customer_id = ? AND o.payment_status = 'Pending' " +
                        "GROUP BY o.order_id " +
                        "ORDER BY o.order_date DESC LIMIT 1";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, customerId);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    request.setAttribute("orderId", rs.getInt("order_id"));
                    request.setAttribute("totalAmount", rs.getDouble("total_amount"));
                    request.getRequestDispatcher("/WEB-INF/pages/payment.jsp").forward(request, response);
                } else {
                    session.setAttribute("errorMessage", "No pending orders found");
                    response.sendRedirect(request.getContextPath() + "/viewCart");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error processing payment: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/viewCart");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");
        
        if (customerId == null) {
            session.setAttribute("errorMessage", "Please login to proceed with payment");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String paymentMethod = request.getParameter("paymentMethod");
        double amount = Double.parseDouble(request.getParameter("amount"));

        try (Connection conn = DbConfig.getDbConnection()) {
            conn.setAutoCommit(false);
            try {
                // Insert payment record
                String paymentSql = "INSERT INTO payment (order_id, amount, payment_method, payment_status) VALUES (?, ?, ?, ?)";
                try (PreparedStatement paymentStmt = conn.prepareStatement(paymentSql)) {
                    paymentStmt.setInt(1, orderId);
                    paymentStmt.setDouble(2, amount);
                    paymentStmt.setString(3, paymentMethod);
                    paymentStmt.setString(4, "Completed");
                    paymentStmt.executeUpdate();
                }

                // Update order status
                String orderSql = "UPDATE orders SET payment_status = 'Paid' WHERE order_id = ?";
                try (PreparedStatement orderStmt = conn.prepareStatement(orderSql)) {
                    orderStmt.setInt(1, orderId);
                    orderStmt.executeUpdate();
                }

                conn.commit();
                session.setAttribute("successMessage", "Payment completed successfully!");
                
                // Redirect to admin order page
                response.sendRedirect(request.getContextPath() + "/order");
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error processing payment: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/payment");
        }
    }
} 