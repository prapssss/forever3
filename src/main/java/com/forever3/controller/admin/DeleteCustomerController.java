package com.forever3.controller.admin;

import com.forever3.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/deletecustomer")
public class DeleteCustomerController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String customerIdParam = request.getParameter("customerId");

        if (customerIdParam == null || customerIdParam.isEmpty()) {
            request.getSession().setAttribute("errorMessage", "Customer ID not provided.");
            response.sendRedirect(request.getContextPath() + "/customers");
            return;
        }

        int customerId = Integer.parseInt(customerIdParam);
        System.out.println("Attempting to delete customer with ID: " + customerId);

        try (Connection conn = DbConfig.getDbConnection()) {
            // First check if customer exists
            if (!customerExists(conn, customerId)) {
                request.getSession().setAttribute("errorMessage", "Customer not found.");
                response.sendRedirect(request.getContextPath() + "/customers");
                return;
            }

            // Check if customer has any orders
            if (customerHasOrders(conn, customerId)) {
                request.getSession().setAttribute("errorMessage", "Cannot delete customer with existing orders. Please delete their orders first.");
                response.sendRedirect(request.getContextPath() + "/customers");
                return;
            }

            // Begin transaction
            conn.setAutoCommit(false);
            try {
                // Delete customer
                String sql = "DELETE FROM customer WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, customerId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    conn.commit();
                    request.getSession().setAttribute("successMessage", "Customer deleted successfully.");
                } else {
                    conn.rollback();
                    request.getSession().setAttribute("errorMessage", "Failed to delete customer.");
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (Exception e) {
            System.out.println("Error while deleting customer:");
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "An error occurred while deleting the customer: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/customers");
    }

    private boolean customerExists(Connection conn, int customerId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM customer WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private boolean customerHasOrders(Connection conn, int customerId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM orders WHERE customer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
