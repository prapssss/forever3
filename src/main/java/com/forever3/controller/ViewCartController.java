package com.forever3.controller;

import com.forever3.model.CartModel;
import com.forever3.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Prapti Adhikari
 */
@WebServlet("/viewCart")
public class ViewCartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// Get the current session and customer ID
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");
        
        System.out.println("ViewCart - Customer ID: " + customerId);

        // If customer is not logged in, redirect to login page
        if (customerId == null) {
            System.out.println("ViewCart - No customer ID found, redirecting to login");
            session.setAttribute("errorMessage", "Please login to view your cart");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Attempt to retrieve cart items from database
        try (Connection conn = DbConfig.getDbConnection()) {
            List<CartModel> cart = getCartItems(conn, customerId);
            System.out.println("ViewCart - Retrieved " + cart.size() + " items from cart");

            // Store cart in session and request scope
            session.setAttribute("cart", cart); // Optional: helpful for session-level access
            request.setAttribute("cart", cart); // Required for display in JSP

            // Forward to JSP view
            request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("ViewCart - Error: " + e.getMessage());
            e.printStackTrace();

            // Redirect back to cart with error message
            session.setAttribute("errorMessage", "Error loading cart: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/viewCart");
        }
    }

    /**
     * Retrieves cart items for the given customer ID from the database.
     *
     * @param conn       The active database connection.
     * @param customerId The ID of the customer whose cart is being retrieved.
     * @return A list of CartModel objects representing the cart items.
     * @throws SQLException If a database access error occurs.
     */
    private List<CartModel> getCartItems(Connection conn, int customerId) throws SQLException {
        List<CartModel> cart = new ArrayList<>();

        // SQL query to join cart and item tables
        String sql = "SELECT c.*, i.item_name, i.item_price, i.image_url " +
                     "FROM cart c " +
                     "JOIN item i ON c.item_id = i.item_id " +
                     "WHERE c.customer_id = ?";

        // Prepare and execute the SQL statement
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            // Iterate over result set and populate CartModel list
            while (rs.next()) {
                CartModel item = new CartModel();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setItemPrice(rs.getDouble("item_price"));
                item.setQuantity(rs.getInt("quantity"));
                item.setImageUrl(rs.getString("image_url"));
                cart.add(item);

                System.out.println("ViewCart - Retrieved item: " + item.getItemName() + ", quantity: " + item.getQuantity());
            }
        }
        return cart;
    }
}