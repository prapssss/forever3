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
/**
 * @author Prapti Adhikari
 */
@WebServlet("/viewcustomer")
public class ViewCustomerController extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the customer ID from the query parameter
        String idParam = request.getParameter("id");

        // If no ID is provided, redirect to the customer list page
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/customers");
            return;
        }

        int customerId = Integer.parseInt(idParam); // Convert ID to integer
        UserModel customer = null;

        // Try-with-resources block to auto-close DB connection
        try (Connection conn = DbConfig.getDbConnection()) {

            // Prepare SQL query to fetch customer by ID
            String sql = "SELECT * FROM customer WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            // If a customer is found, populate a UserModel instance
            if (rs.next()) {
                customer = new UserModel(
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
            }

        } catch (Exception e) {
            // Log any exceptions that occur during DB access
            e.printStackTrace();
        }

        // If customer not found, redirect to customer list
        if (customer == null) {
            response.sendRedirect(request.getContextPath() + "/customers");
            return;
        }

        // Pass the customer object to the view
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/WEB-INF/pages/admin/viewcustomer.jsp").forward(request, response);
    }
}