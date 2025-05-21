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
        Integer orderId = (Integer) session.getAttribute("orderId");
        Double totalAmount = (Double) session.getAttribute("totalAmount");

        System.out.println("Payment GET - Customer ID: " + customerId);
        System.out.println("Payment GET - Order ID: " + orderId);
        System.out.println("Payment GET - Total Amount: " + totalAmount);

        if (customerId == null) {
            session.setAttribute("errorMessage", "Please login to proceed with payment");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (orderId == null) {
            session.setAttribute("errorMessage", "No order found for payment");
            response.sendRedirect(request.getContextPath() + "/viewCart");
            return;
        }

        // Set the total amount in the request for display
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("orderId", orderId);
        
        request.getRequestDispatcher("/WEB-INF/pages/payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");
        Integer orderId = (Integer) session.getAttribute("orderId");
        Double amount = (Double) session.getAttribute("totalAmount");

        System.out.println("Payment POST - Customer ID: " + customerId);
        System.out.println("Payment POST - Order ID: " + orderId);
        System.out.println("Payment POST - Amount: " + amount);

        if (customerId == null || orderId == null || amount == null) {
            session.setAttribute("errorMessage", "Invalid payment request");
            response.sendRedirect(request.getContextPath() + "/viewCart");
            return;
        }

        String paymentMethod = request.getParameter("paymentMethod");
        String mobileNumber = request.getParameter("mobileNumber");
        String customerName = request.getParameter("customerName");
        String location = request.getParameter("location");

        System.out.println("Payment POST - Payment Method: " + paymentMethod);
        System.out.println("Payment POST - Mobile Number: " + mobileNumber);
        System.out.println("Payment POST - Customer Name: " + customerName);
        System.out.println("Payment POST - Location: " + location);

        if (paymentMethod == null || paymentMethod.trim().isEmpty() ||
            mobileNumber == null || mobileNumber.trim().isEmpty() ||
            customerName == null || customerName.trim().isEmpty() ||
            location == null || location.trim().isEmpty()) {
            session.setAttribute("errorMessage", "Please fill in all required fields");
            response.sendRedirect(request.getContextPath() + "/payment");
            return;
        }

        Connection conn = null;
        try {
            conn = DbConfig.getDbConnection();
            conn.setAutoCommit(false);

            // Check if payment table exists
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'payment'");
                if (!rs.next()) {
                    // Create payment table if it doesn't exist
                    String createTableSQL = "CREATE TABLE IF NOT EXISTS payment (" +
                        "payment_id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "order_id INT NOT NULL, " +
                        "amount DECIMAL(10,2) NOT NULL, " +
                        "payment_method VARCHAR(50) NOT NULL, " +
                        "payment_status VARCHAR(20) NOT NULL, " +
                        "payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                        "customer_name VARCHAR(100) NOT NULL, " +
                        "delivery_location VARCHAR(255) NOT NULL, " +
                        "mobile_number VARCHAR(15) NOT NULL, " +
                        "FOREIGN KEY (order_id) REFERENCES orders(order_id))";
                    stmt.execute(createTableSQL);
                } else {
                    // Check if we need to add new columns
                    try {
                        stmt.execute("ALTER TABLE payment ADD COLUMN customer_name VARCHAR(100)");
                    } catch (SQLException e) {
                        // Column might already exist
                    }
                    try {
                        stmt.execute("ALTER TABLE payment ADD COLUMN delivery_location VARCHAR(255)");
                    } catch (SQLException e) {
                        // Column might already exist
                    }
                    try {
                        stmt.execute("ALTER TABLE payment ADD COLUMN mobile_number VARCHAR(15)");
                    } catch (SQLException e) {
                        // Column might already exist
                    }
                }
            }

            // Insert payment record
            String insertPaymentSQL = "INSERT INTO payment (order_id, amount, payment_method, payment_status, " +
                                    "customer_name, delivery_location, mobile_number) " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertPaymentSQL)) {
                pstmt.setInt(1, orderId);
                pstmt.setDouble(2, amount);
                pstmt.setString(3, paymentMethod);
                pstmt.setString(4, "Completed");
                pstmt.setString(5, customerName);
                pstmt.setString(6, location);
                pstmt.setString(7, mobileNumber);
                pstmt.executeUpdate();
                System.out.println("Payment record inserted successfully");
            }

            // Update order status
            String updateOrderSQL = "UPDATE orders SET payment_status = 'Paid' WHERE order_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateOrderSQL)) {
                pstmt.setInt(1, orderId);
                pstmt.executeUpdate();
                System.out.println("Order status updated successfully");
            }

            conn.commit();
            
            // Keep orderId for bill generation, remove other session attributes
            session.removeAttribute("totalAmount");
            session.removeAttribute("shippingName");
            session.removeAttribute("shippingAddress");
            session.removeAttribute("shippingCity");
            session.removeAttribute("shippingPostalCode");
            session.removeAttribute("shippingPhone");
            
            session.setAttribute("successMessage", "Payment completed successfully!");
            response.sendRedirect(request.getContextPath() + "/bill");
        } catch (Exception e) {
            System.out.println("Error processing payment: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            session.setAttribute("errorMessage", "Error processing payment: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/payment");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
} 