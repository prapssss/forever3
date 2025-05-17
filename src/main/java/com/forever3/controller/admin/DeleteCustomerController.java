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

@WebServlet("/delete-customer")
public class DeleteCustomerController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String customerIdParam = request.getParameter("customerId");

        if (customerIdParam == null || customerIdParam.isEmpty()) {
            System.out.println("Customer ID not provided.");
            response.sendRedirect(request.getContextPath() + "/customers");
            return;
        }

        int customerId = Integer.parseInt(customerIdParam);
        System.out.println("Attempting to delete customer with ID: " + customerId);

        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "DELETE FROM customer WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows deleted: " + rowsAffected);

            if (rowsAffected == 0) {
                System.out.println("No customer found with ID: " + customerId);
            }

        } catch (Exception e) {
            System.out.println("Error while deleting customer:");
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/customers");
    }
}
