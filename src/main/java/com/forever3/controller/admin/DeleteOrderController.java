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

@WebServlet("/deleteorder")
public class DeleteOrderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String orderIdParam = request.getParameter("orderId");

        if (orderIdParam == null || orderIdParam.isEmpty()) {
            request.getSession().setAttribute("errorMessage", "Order ID not provided.");
            response.sendRedirect(request.getContextPath() + "/order");
            return;
        }

        int orderId = Integer.parseInt(orderIdParam);
        System.out.println("Attempting to delete order with ID: " + orderId);

        try (Connection conn = DbConfig.getDbConnection()) {
            // Begin transaction
            conn.setAutoCommit(false);
            try {
                // First delete order items
                String deleteOrderItemsSQL = "DELETE FROM order_items WHERE order_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(deleteOrderItemsSQL)) {
                    stmt.setInt(1, orderId);
                    stmt.executeUpdate();
                }

                // Then delete payment record if exists
                String deletePaymentSQL = "DELETE FROM payment WHERE order_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(deletePaymentSQL)) {
                    stmt.setInt(1, orderId);
                    stmt.executeUpdate();
                }

                // Finally delete the order
                String deleteOrderSQL = "DELETE FROM orders WHERE order_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(deleteOrderSQL)) {
                    stmt.setInt(1, orderId);
                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        conn.commit();
                        request.getSession().setAttribute("successMessage", "Order deleted successfully.");
                    } else {
                        conn.rollback();
                        request.getSession().setAttribute("errorMessage", "Order not found.");
                    }
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (Exception e) {
            System.out.println("Error while deleting order:");
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "An error occurred while deleting the order: " + e.getMessage());
        }

        // Always redirect back to the order page
        response.sendRedirect(request.getContextPath() + "/order");
    }
} 